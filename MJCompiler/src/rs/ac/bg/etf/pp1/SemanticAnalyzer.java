package rs.ac.bg.etf.pp1;

import java.util.Collection;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;

import org.apache.log4j.Logger;

import rs.ac.bg.etf.pp1.ast.*;
import rs.etf.pp1.symboltable.Tab;
import rs.etf.pp1.symboltable.concepts.Obj;
import rs.etf.pp1.symboltable.concepts.Struct;
import rs.etf.pp1.symboltable.factory.SymbolTableFactory;
import rs.etf.pp1.symboltable.structure.SymbolDataStructure;

public class SemanticAnalyzer extends VisitorAdaptor {
	
	/*
	 * LOCAL VARS ***********************************************************************
	 */

	protected Obj enumNode;
	protected Obj currentTypeObj = null;
	protected Obj currentMethod = null;
	protected Obj currentDesignator = null;
	protected Struct currentFactor = null;
	protected Struct currentType = noType;
	protected boolean returnFound = false;
	protected boolean errorDetected = false;
	protected boolean classDecl = false;
	protected boolean mainFound = false;
	private boolean inForLoop = false;
	protected int currentLevel = -1;
	protected int enumInit = -1;
	protected int errorsNum = 0;
	protected static int nVars;
	
    protected int lvlNum = 0;
    protected LinkedList<Obj> actParams = new LinkedList<Obj>();
	
	Logger log = Logger.getLogger(getClass());
	
	/*
	 * TYPES ****************************************************************************
	 */
	
	protected static Struct intType = null;
	protected static Struct charType = null;
	protected static Struct boolType = null;
	protected static Struct nullType = null;
	protected static Struct noType = null;
	
	public static void initTypes() {
		intType = Tab.intType;
		charType = Tab.charType;
		if (boolType == null) {
			boolType = new Struct(Struct.Bool);
			Tab.currentScope.addToLocals(new Obj(Obj.Type, "bool", boolType));
		}
		nullType = Tab.nullType;
		noType = Tab.noType;
	}
	
	/*
	 * METHODS FOR ERROR INFO ***********************************************************
	 */
	
	public void report_error(String message, SyntaxNode info) {
		errorDetected = true;
		StringBuilder msg = new StringBuilder(message);
		int line = (info == null) ? 0: info.getLine();
		if (line != 0)
			msg.append (" na liniji ").append(line);
		msg.append("		<--------------- ERROR ");
		log.error(msg.toString());
		
		errorsNum++;
	}
	
	public void report_error(String message, int info) {
		errorDetected = true;
		StringBuilder msg = new StringBuilder(message);
		int line = info;
		if (line != 0)
			msg.append (" na liniji ").append(line);
		msg.append("		<--------------- ERROR ");
		log.error(msg.toString());
	}

	public void report_info(String message, SyntaxNode info) {
		StringBuilder msg = new StringBuilder(message); 
		int line = (info == null) ? 0: info.getLine();
		if (line != 0)
			msg.append (" na liniji ").append(line);
		log.info(msg.toString());
	}
	
	/*
	 * PROGRAM **************************************************************************
	 */
	
	@Override
	public void  visit(ProgName pname) {
		report_info("INFO:  Pocetak programa "+ pname.getPName(), pname);
		pname.obj = Tab.insert(Obj.Prog, pname.getPName(), noType);
		Tab.openScope();
		currentLevel++;
		
		this.mainFound = false;
	}
	
	@Override
	public void visit(Program prog) {
		nVars = Tab.currentScope.getnVars();
		Tab.chainLocalSymbols(prog.getProgName().obj);
		Tab.closeScope();
		currentLevel--;
		
		if (!this.mainFound) {
			report_error("ERROR: Nije definisan main metod!", null);
		}
	}
	
	@Override
	public void visit(GlobalMeths glbm) {
		
		if (this.currentMethod != null)
			if (this.currentMethod.getName().equals("main")) {
				this.mainFound = true;
				//report_info("main lvl: "+this.currentMethod.getLevel(), null);
			}
	}
	
	/*
	 * TYPE *****************************************************************************
	 */
	
	public void visit(Type type) {
		Obj typeNode = Tab.find(type.getTypeName());
		
		// if this type does not exist
    	if(typeNode == Tab.noObj){
    		report_error("ERROR: Nije pronadjen tip " + type.getTypeName() + " u tabeli simbola! ", null);
    		type.struct = Tab.noType;
    	}
    	else {
//    		if (type.getTypeName().equals("A")) {
//    			int n=0;
//    		}
    		
        	// if type is found in table
    		if(Obj.Type == typeNode.getKind()){
    			type.struct = typeNode.getType();
    			// for later checks
    			currentType = typeNode.getType();
    			this.currentTypeObj = typeNode;
    		// if this is not type
    		}else{
    			report_error("ERROR: Ime " + type.getTypeName() + " ne predstavlja tip!", type);
    			type.struct = Tab.noType;
    			this.currentTypeObj = null;
    		}
    	}
	}
	
	public void visit(VoidIdentificator v) {
		currentType = noType;
	}
	
	/*
	 * CONST ****************************************************************************
	 */
	
	@Override
	public void visit(ConstItem con) {
		Obj node = Tab.find(con.getId());
		// symbol is NOT in table - new constant
		if (node == Tab.noObj) {
			if (currentType.isRefType()) {
				report_error("ERROR: Konstante ne mogu biti tipa referenci ", con);
			} else {
				Struct type = con.getInitializer().obj.getType();
				if (!type.assignableTo(currentType)) {
					report_error("ERROR: Nekompatibilnost tipova ", con);
				} else {
					node = Tab.insert(Obj.Con, con.getId(), currentType);
					node.setAdr(con.getInitializer().obj.getAdr());
					//node.setLevel(currentLevel); 
					report_info("INFO:  Deklarisana konstanta " + con.getId(), con);
				}
			}
		} 
		// symbol is already defined - error
		else {
			report_error("ERROR: Konstanta " + con.getId() + " je vec deklarisana!", null);
		}
	}
	
	@Override
	public void visit(InitializerBool bool) {
		bool.obj = new Obj(Obj.Con, "", boolType);
		int adr = 0;
		if (bool.getValue().equals("true")) adr = 1;
		bool.obj.setAdr(adr);
		
	}

	@Override
	public void visit(InitializerChar chr) {
		chr.obj = new Obj(Obj.Con, "", charType);
		chr.obj.setAdr(chr.getValue()); 
	}

	@Override
	public void visit(InitializerNum num) {
		num.obj = new Obj(Obj.Con, "", intType);
		num.obj.setAdr(num.getValue());
	}


	/*
	 * ENUM *****************************************************************************
	 */

	public void visit(EnumNamed name) {
		Obj enumNode = Tab.find(name.getName());
		if (enumNode != Tab.noObj) {
			report_error("ERROR: Enum tip sa imenom " + name.getName() + " je vec deklarisan!", null); 
		}
		else {
			enumInit = -1;
			Struct str = new Struct(Struct.Enum, intType);
			enumNode = Tab.insert(Obj.Type, name.getName(), str);
			name.obj = enumNode;
			this.enumNode = enumNode;
			
			Tab.openScope();
			currentLevel++;
		}
	}
	
	@Override
	public void visit(InitEnum init) {
		if (Tab.currentScope.findSymbol(init.getName()) != null) {
			report_error("ERROR: Vec je deklarisana konstanta sa imenom " + init.getName(), init);
			return;
		}
		
		enumInit = init.getValue();
		Obj node = Tab.insert(Obj.Con, init.getName(), intType);
		node.setAdr(enumInit);
		
		//node.setLevel(currentLevel);
	}
	
	@Override
	public void visit(NoInitEnum init) {		
		if (Tab.currentScope.findSymbol(init.getName()) != null) {
			report_error("ERROR: Vec je deklarisana konstanta sa imenom " + init.getName(), init);
			return;
		}
		
		++enumInit;
		Obj node = Tab.insert(Obj.Con, init.getName(), intType);
		node.setAdr(enumInit);
		
		//node.setLevel(currentLevel);
	}


	@Override
	public void visit(EnumDeclarations EnumDeclarations) {
		Tab.chainLocalSymbols(enumNode);
		
		report_info("INFO:  Definisan enum tip " + this.enumNode.getName(), EnumDeclarations);
		Tab.closeScope();
		currentLevel--;
		this.enumNode = null;
		this.enumInit = -1;
	}
	
	/*
	 * VAR ******************************************************************************
	 */
	
	@Override
	public void visit(Var var) {
		Obj varNode = Tab.find(var.getVarName());
		int kind = (this.classDecl)? Obj.Fld : Obj.Var;
		
		if (varNode == Tab.noObj) {
			if (var.getOptArraySq() instanceof ArrayVar) {
				Struct array = new Struct(Struct.Array, currentType);
				varNode = Tab.insert(kind, var.getVarName(), array);
				report_info("INFO:  Deklarisan niz " + var.getVarName(), var);
			}
			else {
				Struct str = (currentType.getKind() == Struct.Enum)? intType : currentType;
				varNode = Tab.insert(kind, var.getVarName(), str);
				report_info("INFO:  Deklarisana"+((this.currentLevel==0)?" globalna":"")+" promenljiva " + var.getVarName(), var);
			}
		}
		else {
			//if (varNode.getLevel() == currentLevel) {
			if (Tab.currentScope.findSymbol(var.getVarName()) != null) {
				report_error("ERROR: Postoji definisana promenljiva sa imenom " + var.getVarName(), var);
			} else {
				// redefinisanje simbola?
				if (var.getOptArraySq() instanceof ArrayVar) {
					Struct array = new Struct(Struct.Array, currentType);
					varNode = Tab.insert(kind, var.getVarName(), array);
					report_info("INFO:  Deklarisan niz " + var.getVarName(), var);
				}
				else {
					Struct str = (currentType.getKind() == Struct.Enum)? intType : currentType;
					varNode = Tab.insert(kind, var.getVarName(), str);
					report_info("INFO:  Deklarisana"+((this.currentLevel==0)?"globalna ":"")+" promenljiva " + var.getVarName(), var);
				}
			}
		}
	}


	
	/*
	 * CLASS ****************************************************************************
	 */
		
	@Override
	public void visit(ClassName name) {
		if (Tab.find(name.getName()) != Tab.noObj) {
			report_error("ERROR: Klasa " + name.getName() + " je vec deklarisana!", null);
			return;
		}

		Obj classNode = Tab.insert(Obj.Type, name.getName(), new Struct(Struct.Class));
		name.obj = classNode;
		
		Tab.openScope();
		currentLevel++;
		this.classDecl = true;
		
	}

	@Override
	public void visit(ClassDeclaration classtDeclaration) {
		Tab.chainLocalSymbols(classtDeclaration.getClassName().obj);
		
		report_info("INFO:  Definisana klasa " +classtDeclaration.getClassName().obj.getName(), classtDeclaration);
		Tab.closeScope();
		currentLevel--;
		this.classDecl = false;
	}
	
	// 1. TODO: provera klase koja se implemetira:
	//			- da li postoji i proveriti level
	// 2. TODO: provera instefejsa koji se nasledjuju
	//			- da li su intefejsi
	//			- da se ne ponovi neki...
	
	
	/*
	 * INTERFACE ************************************************************************
	 */
	
    @Override
	public void visit(InterName name) {
		
    	if (Tab.find(name.getName()) != Tab.noObj) {
			report_error("ERROR: Interfejs " + name.getName() + " je vec deklarisan!", null);
			return;
		}

		Obj interNode = Tab.insert(Obj.Type, name.getName(), new Struct(Struct.Interface));
		name.obj = interNode;
		
		Tab.openScope();
		currentLevel++;
	}
	
    @Override
	public void visit(InterfaceDeclarations inter) {		
    	
		Tab.chainLocalSymbols(inter.getInterfaceName().obj);
		
		report_info("INFO:  Definisan interfejs " + inter.getInterfaceName().obj.getName(), inter);
		Tab.closeScope();
		currentLevel--;
	}
	
    @Override
	public void visit(InterfaceMethodDeclaration method ) {		
    	
    	if (Tab.currentScope.findSymbol(method.getName()) != null) {
			report_error("ERROR: Metoda " + method.getName() + " je vec deklarisana u interfejsu!", null);
			return;    	
		}
    	
    	Obj node = Tab.insert(Obj.Meth, method.getName(), currentType);
    	//this.currentMethod = node;

    	report_info("INFO:  Deklarisan metod "+ method.getName() + " u interfejsu", method);
    	
    	// TODO: FORM PARS???
	}
    
	/*
	 * METHOD DECLARATION ***************************************************************
	 */

    @Override	
    public void visit(MethodDeclTypeName methodTypeName) {
    	if (Tab.find(methodTypeName.getMethodName()) != Tab.noObj) {
			report_error("ERROR: Metoda " + methodTypeName.getMethodName() + " je vec deklarisana!", null);
			return;    	
		}
    	
    	this.currentMethod = Tab.insert(Obj.Meth, methodTypeName.getMethodName(), currentType);
    	methodTypeName.obj = currentMethod;
    	methodTypeName.obj.setLevel(0);
    	if (methodTypeName.getTypeIdent() instanceof VoidIdentificator){
    		this.returnFound = true;
    	} else {
        	this.returnFound = false;
    	}
    	
    	Tab.openScope();
    	this.currentLevel++;

    	// TODO: Povratna vrednost funkcije???
		report_info("INFO:  Obradjuje se funkcija " + methodTypeName.getMethodName(), methodTypeName);
    }
 
    @Override
	public void visit(MethodDeclarations methodDecl){
    	if(!this.returnFound && currentMethod.getType() != Tab.noType){
			report_error("ERROR: Funkcija " + currentMethod.getName() + " nema return iskaz!", null);
    	}
    	Tab.chainLocalSymbols(this.currentMethod);
    	
    	report_info("INFO:  Deklarisan metod "+ currentMethod.getName(), methodDecl);
    	
    	Tab.closeScope();    	
    	this.returnFound = false;
    	if (!currentMethod.getName().equals("main")) this.currentMethod = null;
    	this.currentLevel--;
    }
	
    @Override
    public void visit(ReturnStmt returnExpr){
    	
    	if (this.currentMethod == null) {
			report_error("ERROR: Return naredbna se nalazi izvan tela funkcije/procedure " + currentMethod.getName(), returnExpr);
			return;
    	}
    	
    	this.returnFound = true;
    	Struct currMethType = currentMethod.getType();
    	
    	
    	if (returnExpr.getOptRetExpr() instanceof RetExpr) { // must return something
    		if(!currMethType.compatibleWith(returnExpr.getOptRetExpr().struct) || currMethType == Tab.noType) {
    			report_error("ERROR: Tip izraza u return naredbi ne slaze se sa tipom povratne vrednosti funkcije " + currentMethod.getName(), returnExpr);
    		}
    	} else {
    		if (currMethType != Tab.noType) {
    			report_error("ERROR: Tip funkcije " + currentMethod.getName() + " je void", returnExpr);
    		}
    	}
    }
    
    @Override
    public void visit(RetExpr ret) {
    	ret.struct = ret.getExpr().struct;
    }
    
    @Override
    public void visit(NoRet ret) {
    	ret.struct = Tab.noType;
    }
    /*
     * FORMAL PARS **********************************************************************
     */
    
    @Override
    public void visit(FormParItem item) {
		Obj node = Tab.currentScope.findSymbol(item.getParamName());
    	if (node != null) {
			report_error("ERROR: Vec je deklarisan formalni parametar sa imenom " + item.getParamName(), item);
			return;
		}
    	Struct str;
		if (item.getOptArraySq() instanceof ArrayVar) {
			str = new Struct(Struct.Array, currentType);
		} else {
			str = currentType;
		}
//		if ("mul".equals(this.currentMethod.getName())) {
//			int n = 0;
//		}
		
		
		node = Tab.insert(Obj.Var, item.getParamName(), str);
		item.obj = node;
		// TODO: check! Tab.currentScope.addToLocals(node);
		node.setFpPos(currentMethod.getLevel());
		currentMethod.setLevel(currentMethod.getLevel() + 1);

		report_info("INFO:  Deklarisan formalni parametar " + item.getParamName(), item);
    }
    

    /*
     * EXPR *****************************************************************************
     */
    
    
    @Override
    public void visit(Expression expr) {
    	
    	if ((expr.getOptMinus() instanceof Negative) && expr.getTerm().struct != intType) {
    		report_error("ERROR: Odgovarajuci tip mora biti int ", expr);
    		return;
    	}
    	
    	Struct str1 = expr.getTerm().struct;
    	Struct str2 = expr.getOptAddTerms().struct;
    	if (expr.getOptAddTerms() instanceof WithAddTerms)
    		if (str1 != intType || str2 != intType) {
    			report_error("ERROR: Odgovarajuci tipovi kod sabiranja moraju biti int", expr);
    			return;
    	}
    	
    	expr.struct = expr.getTerm().struct;
    }
    
    @Override
    public void visit(WithAddTerms add) {
    	add.struct = add.getTerm().struct;
    }
    
    /*
     * TERM *****************************************************************************
     */
    
    @Override
    public void visit(Terms term) {

    	if (term.getOptMulTerms() instanceof WithMulFacts)
    		if (term.getOptMulTerms().struct != intType) {
    			report_error("ERROR: Odgovarajuci tipovi kod mnozenja moraju biti int", term);
    			term.struct = Tab.noType;
    			return;
    	}
    	
    	term.struct = term.getFactor().struct;
    }
    
    @Override
    public void visit(WithMulFacts mul) {
    	mul.struct = mul.getFactor().struct; 
    }
    
    /*
     * FACTOR *************************************************************************** 
     */
    
  
    @Override
    public void visit(SingleActPars par) {
    	Obj obj = new Obj(Obj.Var, "par", par.getExpr().struct);
    	obj.setLevel(this.lvlNum); // zbog ugnezdavanja poziva!!!
    	this.actParams.addFirst(obj);
    }
    
    @Override
    public void visit(MultiActPars par) {
    	Obj obj = new Obj(Obj.Var, "par", par.getExpr().struct);
    	obj.setLevel(this.lvlNum); // zbog ugnezdavanja poziva!!!
    	this.actParams.addFirst(obj);
    }
    
	@Override
	public void visit(DesignFactor design) {
		
		if (design.getOptMethodCall() instanceof NoMethodCall) {
			 design.struct = design.getDesignator().obj.getType();
		} else {
			
			Obj node = design.getDesignator().obj;
			this.lvlNum--;
			
			// 1. if it is method
			if (node.getKind() != Obj.Meth) {
				report_error("ERROR: Objekat "+node.getName()+" nije metoda", design);
				design.struct = Tab.noType;
				this.actParams.clear();
				return;
			} 
			
			// && MUST NOT BE VOID!
			if (node.getType() == Tab.noType /*void*/) {
				report_error("ERROR: Metoda " + node.getName() + " ne moze da bude void", design);

				this.clearParams();
				return;
			}
			
			// 2. number of parameters
			int numOfPar = node.getLevel();
			int actPar = 0;
			for (Obj o: this.actParams) {
				if (o.getLevel() > this.lvlNum)
					actPar++;
			}
			if (numOfPar != actPar) {
				report_error("ERROR: Broj prosledjenih parametara ne odgovara stvarnom broju", design);
				design.struct = Tab.noType;
				this.actParams.clear();
				return;
			}
			
			// 3. types of parameters
			Collection<Obj> pars = node.getLocalSymbols();
			int size = pars.size();
			boolean comp = true;
			for (int i=0; i<size; i++) {
				Obj actp = this.actParams.get(size - i - 1); // in this list, act pars are in reverse order
				for (Obj o: pars) {
					if (o.getFpPos() == (i)) {
						if (!actp.getType().compatibleWith(o.getType()))
							comp = false;
							break;
					}
				}
			}
			
//			for (Iterator<Obj> iter = pars.iterator(); iter.hasNext();) {
//				Obj cur = iter.next();
//				//boolean found = false;
//				for (Obj o: this.actParams) {
//					if (o.getLevel() > this.lvlNum) {
//						
//						if (o.getType().compatibleWith(cur.getType()))
//							comp = true;
//					}
//				}
//			}
			
			if (!comp) {
				report_error("ERROR: Nekompatibilnost sa stvarnim parametrima", design);
				design.struct = Tab.noType;
				this.actParams.clear();
				return;
			}
			
			report_info("INFO:  Pozvana metoda "+node.getName(), design);
			design.struct = node.getType();

			Iterator<Obj> iter = this.actParams.iterator();
			while (iter.hasNext()) {
				Obj o = iter.next();
				if (o.getLevel()>this.lvlNum)
					iter.remove();
			}
			
		}
	}
    
	@Override
	public void visit(ExprFactor expr) {
		expr.struct = expr.getExpr().struct;
	}
	
	@Override
	public void visit(NumFactor num) {
		num.struct = intType;
	}
	
	@Override
	public void visit(CharFactor num) {
		num.struct = charType;
	}
	
	@Override
	public void visit(BoolFactor num) {
		num.struct = boolType;
	}

	@Override
	public void visit(NewArrFactor newFactor) {
		
		if (newFactor.getExpr().struct != intType) {
			report_error("ERROR: Odgovarajuci tip velicine niza mora biti int ", newFactor);
			return;
		}
		newFactor.struct = new Struct(Struct.Array, newFactor.getType().struct);
		newFactor.struct.setElementType( newFactor.getType().struct);
	}
	
	@Override
	public void visit(NewFactor newFactor) {
		
		if (!this.currentType.isRefType()) {
			report_error("ERROR: Nije moguce alociranje objekata prostog tipa", newFactor);
			return;
		}
		
		if (newFactor.getType().struct.getKind() != Struct.Class || 
			Tab.find(newFactor.getType().getTypeName()) == Tab.noObj) {
				report_error("ERROR: Nije uspelo alociranje pokazivaca na tip "+newFactor.getType().getTypeName(), newFactor);
				return;
			}
		//////////////////////////////////////////////////ADDED
		Obj node = Tab.find(newFactor.getType().getTypeName());	;
		SymbolDataStructure st = SymbolTableFactory.instance().createSymbolTableDataStructure();
		for (Obj o: node.getLocalSymbols())
			st.insertKey(o);
		
		////////////////////////////////////////////////
		
		newFactor.struct = newFactor.getType().struct;
		
		/////////////////////////////////////////////// ADDED
		newFactor.struct.setMembers(st);
		////////////////////////////////////////////////
	}
    
	/*
	 * DESIGNATOR ***********************************************************************
	 */ 
    
	@Override
	public void visit(DesignatorName name) {

		Obj design = Tab.find(name.getId());
		if (design == Tab.noObj) {
			report_error("ERROR: Objekat "+name.getId()+ " nije definisan", name);
			name.obj = Tab.noObj;
			return;
		}
		
		if (design.getName().equals("A")) {
			int i = 0;
		}
		
		name.obj = design;
		this.currentDesignator = design;
		if (design.getKind() == Obj.Meth) {
			report_info("INFO:  Pristup metodi "+name.getId(), name);
			this.lvlNum++;
		} else 
			report_info("INFO:  Pristup simbolu "+name.getId(), name);
		
		if ("nizch".equals(name.obj.getName())) {
			int n=0;
		}
	}

	@Override
	public void visit(DesignFld field) {		
		if (this.currentDesignator.getType().getKind() != Struct.Enum &&
				this.currentDesignator.getType().getKind() != Struct.Class &&
					this.currentDesignator.getType().getKind() != Struct.Interface) {
			report_error("ERROR: Objekat "+field.getId()+ " nije struktura", field);
			field.obj = Tab.noObj;
			return;
		}
		
		if (this.currentDesignator.getType().getKind() == Struct.Enum) {			
			Collection<Obj> coll = this.currentDesignator.getLocalSymbols();
			boolean found = false;
			Obj obj = null;
			for (Obj node: coll) {
				if (node.getName().equals(field.getId())){
						found = true;
						obj = node;
						break;
					}
			}
			if (!found) {
				report_error("ERROR: Nije pronadjeno polje "+field.getId()+ 
						" tipa "+currentDesignator.getName(), field);
				this.currentDesignator = Tab.noObj;
				field.obj = Tab.noObj;
				return;
			} else {
				report_info("INFO:  Pristup konstanti "+currentDesignator.getName()+"."+field.getId(), field);
				// TODO check this ***************************
				int lvl = obj.getLevel(), adr = obj.getAdr();
				obj = new Obj(Obj.Con, obj.getName(), intType);
				obj.setAdr(adr); obj.setLevel(lvl);
				// *********************************************
				field.obj = obj;
				this.currentDesignator = obj;
			}		
			
		} else { // class 
			
			//Obj classType = Tab.find(this.currentDesignator.getName());
			Collection<Obj> coll = this.currentDesignator.getType().getMembers();
			boolean found = false;
			Obj fnd = null;
			for (Obj o: coll) {
				if (o.getName().equals(field.getId())) {
					//if (field.getDesignator().obj.getType().compatibleWith(o.getType())) {
						found = true;
						fnd = o;
						break;
					//}
				}
			}
			
			if (!found) {
				report_error("ERROR: Struktura "+field.getDesignator().obj.getName()+" ne sadrzi polje "+field.getId(), field);
				this.currentDesignator = Tab.noObj;
				field.obj = Tab.noObj;
			} else {
				report_info("INFO: Pristup strukturi "+field.getDesignator().obj.getName()+", polju "+field.getId(), field);
				this.currentDesignator = fnd;
				field.obj = fnd;
			}

			// TODO: interface fields
		}
		
	}

	@Override
	public void visit(DesignArr arr) {
		
		//if (this.currentDesignator.getType().getKind() != Struct.Array
		if (arr.getDesignator().obj.getType().getKind() != Struct.Array || arr.getExpr().struct != intType) {
				report_error("ERROR: Objekat "+this.currentDesignator.getName()+ " nije niz", arr);
				arr.obj = Tab.noObj;
				return;			
			}
		
//		Struct type1 = arr.getDesignator().obj.getType().getElemType(),
//				type2 = arr.getExpr().struct;
//		if (!type1.compatibleWith(type2)) {
//			report_error("ERROR: Nekompatibilnost tipova "+type1.getKind()+ " i "+type2.getKind(), arr);
//			arr.obj = Tab.noObj;
//			return;	
//		}
		
		//Struct str = currentDesignator.getType().getElemType();
		//Struct str = arr.getExpr().struct;
		Struct str = arr.getDesignator().obj.getType().getElemType();
		arr.obj = new Obj(Obj.Elem, arr.getDesignator().obj.getName(), str);
		
		this.currentDesignator = arr.obj;
		report_info("INFO:  Pristup elementu niza "+this.currentDesignator.getName(), arr);

	}

	@Override
	public void visit(DesignVar var) {
//		if (this.currentDesignator.getType().isRefType()) {
//			int x = 1;
//		}
		var.obj = var.getDesigName().obj;
		var.obj = this.currentDesignator;
	}
    
    @Override
    public void visit(Designator designator) {
    	// TODO: check!
    	designator.obj = this.currentDesignator;
    }
    
    /*
     * DESIGNATOR STATEMENTS ************************************************************
     */
    
	@Override
	public void visit(AssignOper designator) {
		
		Struct str1 = designator.getDesignator().obj.getType();
		Struct str2 = designator.getExpr().struct;

		if (designator.getDesignator().obj.getKind() != Obj.Var 
				&& designator.getDesignator().obj.getKind() != Obj.Elem
				&& designator.getDesignator().obj.getKind() != Obj.Fld ) {
			report_error("ERROR: Neodgovarajuci tip sa leve trane dodele vrednosti", designator);
			return;
		}
		
		if (str1 == null || str2 == null) {
			report_error("ERROR: Greska prilokom provere tipova kod = (null)", designator);
			return;
		}
		
		if (!str1.compatibleWith(str2)) {
			report_error("ERROR: Nekompatibilnost sa stvarnim parametrima", designator);
			return;
		}
		// TODO: provera lvalue

	}
    
    @Override
	public void visit(MinusMinusSideEff designator) {
		if (designator.getDesignator().obj.getKind() != Obj.Var 
				&& designator.getDesignator().obj.getKind() != Obj.Elem
				&& designator.getDesignator().obj.getKind() != Obj.Fld 
				&& designator.getDesignator().obj.getType() != intType) {
			report_error("ERROR: Neodgovarajuci tip za operatror --", designator);
			return;
		}
		// TODO: provera lvalue
	}

	@Override
	public void visit(PlusPlusSideEff designator) {
		if (designator.getDesignator().obj.getKind() != Obj.Var 
				&& designator.getDesignator().obj.getKind() != Obj.Elem
				&& designator.getDesignator().obj.getKind() != Obj.Fld 
				&& designator.getDesignator().obj.getType() != intType) {
			report_error("ERROR: Neodgovarajuci tip za operatror ++", designator);
			return;
		}
		// TODO: provera lvalue
	}
	
	
	void clearParams() {
		Iterator<Obj> iter = this.actParams.iterator();
		while (iter.hasNext()) {
			Obj o = iter.next();
			if (o.getLevel()>this.lvlNum)
				iter.remove();
		}
	}
    
	@Override
	public void visit(ProcCall proc) {

		Obj node = proc.getDesignator().obj;
		this.lvlNum--;

		// 1. if it is method 
		if (node.getKind() != Obj.Meth) {
			report_error("ERROR: Objekat " + node.getName() + " nije metoda", proc);

			this.clearParams();
			return;
		}
		
		// && MUST BE VOID!
		if (node.getType() != Tab.noType /*void*/) {
			report_error("ERROR: Metoda " + node.getName() + " ne moze da ima povratnu vrednost", proc);

			//this.clearParams();
			//return;
		}

		// 2. number of parameters
		int numOfPar = node.getLevel();
		int actPar = 0;
		for (Obj o: this.actParams) {
			if (o.getLevel() > this.lvlNum)
				actPar++;
		}
		if (numOfPar != actPar) {
			report_error("ERROR: Broj prosledjenih parametara ne odgovara stvarnom broju", proc);

			this.clearParams();
			return;
		}

		// 3. types of parameters
		Collection<Obj> pars = node.getLocalSymbols();
		int size = pars.size();
		boolean comp = true;
		for (int i=0; i<size; i++) {
			Obj actp = this.actParams.get(size - i - 1); // in this list, act pars are in reverse order
			for (Obj o: pars) {
				if (o.getFpPos() == (i)) {
					if (!actp.getType().compatibleWith(o.getType()))
						comp = false;
						break;
				}
			}
		}

		if (!comp && (pars.size() > 0 || this.actParams.size() > 0)) {
			report_error("ERROR: Nekompatibilnost sa stvarnim parametrima", proc);

			this.clearParams();
			return;
		}

		report_info("INFO:  Pozvana metoda " + node.getName(), proc);

		this.clearParams();

	}
	
	/*
	 * CONDITIONS ***********************************************************************
	 */

	@Override
	public void visit(CondFacts fact) {
		Struct str1 = fact.getExpr().struct;
		if (str1.getKind() == Struct.Enum) {
			str1 = intType;
		}
		Struct str2 = fact.getExpr1().struct;
		if (str2.getKind() == Struct.Enum) {
			str2 = intType;
		}
		if (!str1.compatibleWith(str2)) {
			report_error("ERROR: Nekompatibilni tipovi kod relacionog operatora", fact);
			fact.struct = Tab.noType;
		} else 
			if ((str1.isRefType() || str2.isRefType()) 
					&& !(fact.getRelop() instanceof RelSame)
					&& !(fact.getRelop() instanceof RelDif)) {
				report_error("ERROR: Kod tipova referenci se koriste samo relacioni operatori za jednakost ili razlicitost", fact);
				fact.struct = Tab.noType;
			}
			else{
				fact.struct = boolType;
			}
	}
	
	@Override
	public void visit(CondFactSingle fact) {
		if (fact.getExpr().struct != boolType) {
			report_error("ERROR: Odgovarajuci tip mora biti bool", fact);
			fact.struct = Tab.noType;
		} else {
			fact.struct = boolType;
		}
	}
	
	@Override
	public void visit(CondTerms fact) {
		fact.struct = fact.getCondFact().struct;
	}
	
	@Override
	public void visit(CondTermSingle fact) {
		fact.struct = fact.getCondFact().struct;
	}
	
	@Override
	public void visit(Conditions fact) {
		fact.struct = fact.getCondTerm().struct;
	}
	
	@Override
	public void visit(ConditionSingle fact) {
		fact.struct = fact.getCondTerm().struct;
	}
	
	/*
	 * STATEMENTS ***********************************************************************
	 */
	
	@Override
	public void visit(PrintStmt stmt) {
		if (stmt.getExpr().struct.isRefType()) {
			report_error("ERROR: Izraz u PRINT funkciji mora biti prostog tipa", stmt);
			return;
		}
		report_info("INFO:  Poziv PRINT funkcije", stmt);
	}
	
	@Override
	public void visit(ReadStmt stmt) {
		if (stmt.getDesignator().obj.getKind() != Obj.Var && 
				stmt.getDesignator().obj.getKind() != Obj.Fld &&
				stmt.getDesignator().obj.getKind() != Obj.Elem) {
			report_error("ERROR: Objekat u READ funkciji mora biti prostog tipa", stmt);
			return;
		}
		if (stmt.getDesignator().obj.getType().isRefType()) {
			report_error("ERROR: Objekat u READ funkciji mora biti prostog tipa", stmt);
			return;
		}
		report_info("INFO:  Poziv READ funkcije", stmt);
	}
		
    
	@Override
	public void visit(IfCond cond) {
		if (cond.getCondition().struct != boolType) {
			report_error("ERROR: Uslov i if naredni mora biti tipa bool", cond);
			return;
		}
		cond.struct = cond.getCondition().struct;
	}
	
	@Override
	public void visit(IfStmt cond) {
		if (cond.getIfCondition().struct != boolType && cond.getIfCondition() instanceof IfCond) {
			report_error("ERROR: Uslov u if naredni mora biti tipa bool", cond);
			return;
		}
		
	}
	
	@Override
	public void visit(ForCond cond) {
		if (cond.getCondition().struct != boolType) {
			report_error("ERROR: Uslov u for naredni mora biti tipa bool", cond);
			return;
		}
		cond.struct = cond.getCondition().struct;
	}
	
	@Override
	public void visit(ForStmt stmt) {
		if (stmt.getOptCond() instanceof ForCond && stmt.getOptCond().struct != boolType) {
			report_error("ERROR: Uslov u for naredni mora biti tipa bool", stmt);
			return;
		}
		
		this.inForLoop = false;
		
	}
	
	@Override
	public void visit(OptForStmt stmt) {
		this.inForLoop = true;
	}
	
	@Override
	public void visit(BreakStmt stmt) {
		if (!this.inForLoop) {
			report_error("ERROR: Break naredba pozvana izvan for petlje", stmt);
		}
	}
	
	@Override
	public void visit(ContinueStmt stmt) {
		if (!this.inForLoop) {
			report_error("ERROR: Continue nareba pozvana izvan for petlje", stmt);
		}
	}
	
    /*
     * OTHRER METHODS *******************************************************************
     */
	
	public int getErrNum() {
		return this.errorsNum;
	}
    

	public boolean passed(){
    	return !errorDetected;
    }
    
}