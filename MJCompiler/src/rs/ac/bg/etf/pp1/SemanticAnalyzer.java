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

public class SemanticAnalyzer extends VisitorAdaptor {
	
	/*
	 * LOCAL VARS ***********************************************************************
	 */

	protected Obj enumNode;
	protected Obj currentMethod = null;
	protected Obj currentDesignator = null;
	protected Struct currentFactor = null;
	protected Struct currentType = noType;
	protected boolean returnFound = false;
	protected boolean errorDetected = false;
	protected boolean classDecl = false;
	protected int currentLevel = -1;
	protected int enumInit = -1;
	protected int errorsNum = 0;
	protected static int nVars;
	
    protected int numOfParams = 0;
    protected List<Struct> params = new LinkedList<Struct>();
	
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
	}
	
	@Override
	public void visit(Program prog) {
		nVars = Tab.currentScope.getnVars();
		Tab.chainLocalSymbols(prog.getProgName().obj);
		Tab.closeScope();
		currentLevel--;
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
        	// if type is found in table
    		if(Obj.Type == typeNode.getKind()){
    			type.struct = typeNode.getType();
    			// for later checks
    			currentType = typeNode.getType();
    		// if this is not type
    		}else{
    			report_error("ERROR: Ime " + type.getTypeName() + " ne predstavlja tip!", type);
    			type.struct = Tab.noType;
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
		
		node.setLevel(currentLevel);
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
		
		node.setLevel(currentLevel);
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
				varNode = Tab.insert(kind, var.getVarName(), currentType);
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
					varNode = Tab.insert(kind, var.getVarName(), currentType);
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
    	if (methodTypeName.getTypeIdent() instanceof VoidIdentificator){
    		this.returnFound = true;
    	} else {
        	this.returnFound = false;
    	}
    	
    	Tab.openScope();
    	this.currentLevel++;

    	// TODO: Povratna vrednost funkcije???
		report_info("INFO: Obradjuje se funkcija " + methodTypeName.getMethodName(), methodTypeName);
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
    	this.currentMethod = null;
    	this.currentLevel--;
    }
	
    @Override
    public void visit(ReturnStmt returnExpr){
    	//report_info("RETURN", returnExpr);
    	
    	if (this.currentMethod == null) {
			report_error("ERROR: Return naredbna se nalazi izvan tela funkcije/procedure " + currentMethod.getName(), returnExpr);
			return;
    	}
    	
    	this.returnFound = true;
    	Struct currMethType = currentMethod.getType();
    	
    	if (returnExpr.getOptRetExpr() instanceof RetExpr) {
    		if(!currMethType.compatibleWith(returnExpr.getOptRetExpr().struct)) {
    			report_error("ERROR: Tip izraza u return naredbi ne slaze se sa tipom povratne vrednosti funkcije " + currentMethod.getName(), returnExpr);
    		}
    	} else {
    		if (currMethType == Tab.noType && returnExpr.getOptRetExpr() instanceof NoRet) {
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
		
		node = Tab.insert(Obj.Var, item.getParamName(), str);
		item.obj = node;
		// TODO: Tab.currentScope.addToLocals()???
		currentMethod.setLevel(currentMethod.getLevel() + 1);
		node.setFpPos(currentMethod.getLevel());

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
//    	if (term.getFactor().struct != intType) {
//			report_error("ERROR: Odgovarajuci tip mora biti int", term);
//			term.struct = Tab.noType;
//			return;
//    	}
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
    	numOfParams += 1;
    	params.add(par.getExpr().struct);
    }
    
    @Override
    public void visit(MultiActPars par) {
    	numOfParams += 1;
    	params.add(par.getExpr().struct);
    }
    
	@Override
	public void visit(DesignFactor design) {
		
		if (design.getOptMethodCall() instanceof NoMethodCall) {
			 design.struct = design.getDesignator().obj.getType();
		} else {
			
			Obj node = design.getDesignator().obj;
			
			// 1. if it is method
			if (node.getKind() != Obj.Meth) {
				report_error("ERROR: Objekat "+node.getName()+" nije metoda ", design);
				design.struct = Tab.noType;
				numOfParams = 0;
				params.clear();
				return;
			} 
			
			// 2. number of parameters
			int numOfPar = node.getLevel();
			if (numOfPar != this.numOfParams) {
				report_error("ERROR: Broj prosledjenih parametara ne odgovara stvarnom broju", design);
				design.struct = Tab.noType;
				numOfParams = 0;
				params.clear();
				return;
			}
			
			// 3. types of parameters
			Collection<Obj> pars = node.getLocalSymbols();
			boolean comp = true;
			int i = 0;
			for (Iterator<Obj> iter = pars.iterator(); iter.hasNext(); ) {
				Obj cur = iter.next();
				if (!params.get(i++).compatibleWith(cur.getType())) {
					comp = false; break;
				}
			}
			
			if (!comp) {
				report_error("ERROR: Nekompatibilnost sa stvarnim parametrima", design);
				design.struct = Tab.noType;
				numOfParams = 0;
				params.clear();
				return;
			}
			
			report_info("INFO:  Pozvana metoda "+node.getName(), design);
			design.struct = node.getType();
			numOfParams = 0;
			params.clear();
			
		}
	}
    
	@Override
	public void visit(ExprFactor expr) {
		expr.struct = expr.getExpr().struct;
	}
	
	@Override
	public void visit(NumFactor num) {
		num.struct = intType;
		// TODO VALUE
	}
	
	@Override
	public void visit(CharFactor num) {
		num.struct = charType;
		// TODO VALUE
	}
	
	@Override
	public void visit(BoolFactor num) {
		num.struct = boolType;
		// TODO VALUE
	}

	@Override
	public void visit(NewArrFactor newFactor) {
		
		if (newFactor.getExpr().struct != intType) {
			report_error("ERROR: Odgovarajuci tip velicine niza mora biti int ", newFactor);
			return;
		}
		newFactor.struct = new Struct(Struct.Array, newFactor.getType().struct);
	}
	
	@Override
	public void visit(NewFactor newFactor) {
		
		if (newFactor.getType().struct.getKind() != Struct.Class || 
			Tab.find(newFactor.getType().getTypeName()) == Tab.noObj) {
				report_error("ERROR: Nije uspelo alociranje pokazivaca na tip "+newFactor.getType().getTypeName(), newFactor);
				return;
			}
			
		newFactor.struct = newFactor.getType().struct;
			
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
		
		name.obj = design;
		this.currentDesignator = design;
		report_info("INFO:  Pristup simbolu "+name.getId(), name);
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
			
		} else {
			
			Collection<Obj> coll = this.currentDesignator.getType().getMembers();// this.currentDesignator.getLocalSymbols();
			report_info("Pristup elementima klase (nije impl)", field);
			field.obj = Tab.noObj;
			this.currentDesignator = Tab.noObj;
			// TODO: class & interface fields
		}
		
	}

	@Override
	public void visit(DesignArr arr) {
		
		if (this.currentDesignator.getType().getKind() != Struct.Array
				|| arr.getExpr().struct != intType) {
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
		
		Struct str = currentDesignator.getType().getElemType();
		arr.obj = new Obj(Obj.Elem, arr.getDesignator().obj.getName(), str);
		this.currentDesignator = arr.obj;
		report_info("INFO:  Pristup elementu niza "+this.currentDesignator.getName(), arr);

	}

	@Override
	public void visit(DesignVar var) {
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
    
	@Override
	public void visit(ProcCall proc) {
		
		
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