package rs.ac.bg.etf.pp1;

import org.apache.log4j.Logger;

import rs.ac.bg.etf.pp1.ast.*;
import rs.etf.pp1.symboltable.Tab;
import rs.etf.pp1.symboltable.concepts.Obj;
import rs.etf.pp1.symboltable.concepts.Struct;

public class SemanticAnalyzer extends VisitorAdaptor {
	
	/*
	 * LOCAL VARS ***********************************************************************
	 */
	
	protected static int nVars;
	protected int currentLevel = -1;
	protected Struct currentType = noType;
	protected int enumInit = -1;
	protected Obj enumNode;
	protected Obj currentMethod = null;
	protected boolean returnFound = false;
	
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
		StringBuilder msg = new StringBuilder(message);
		int line = (info == null) ? 0: info.getLine();
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
					node.setAdr(con.getInitializer().obj.getAdr()); // TODO: check!
					node.setLevel(currentLevel); // TODO: check!
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
		bool.obj.setAdr(0); // TODO: dohvatiti vrednost
	}

	@Override
	public void visit(InitializerChar chr) {
		chr.obj = new Obj(Obj.Con, "", charType);
		chr.obj.setAdr(0); // TODO: dohvatiti vrednost
	}

	@Override
	public void visit(InitializerNum num) {
		num.obj = new Obj(Obj.Con, "", intType);
		num.obj.setAdr(0); // TODO: dohvatiti vrednost
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
			enumNode = Tab.insert(Obj.Type, name.getName(), intType);
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
	}
	
	/*
	 * VAR ******************************************************************************
	 */
	
	@Override
	public void visit(Var var) {
		Obj varNode = Tab.find(var.getVarName());
		if (varNode == Tab.noObj) {
			if (var.getOptArraySq() instanceof ArrayVar) {
				Struct array = new Struct(Struct.Array, currentType);
				// TODO: Tab.currentScope.addToLocals(new Obj(Obj.Type, "", array)); ???
				varNode = Tab.insert(Obj.Var, var.getVarName(), array);
				report_info("INFO:  Deklarisan niz " + var.getLine(), var);
			}
			else {
				varNode = Tab.insert(Obj.Var, var.getVarName(), currentType);
				report_info("INFO:  Deklarisana promenljiva " + var.getLine(), var);
			}
		}
		else {
			if (varNode.getLevel() == currentLevel) {
				report_error("ERROR: Postoji definisana promenljiva sa imenom " + var.getVarName(), var);
			} else {
				// TODO: redefinisanje simbola?
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

		Obj classNode = Tab.insert(Obj.Type, name.getName(), intType);
		name.obj = classNode;
		
		Tab.openScope();
		currentLevel++;
		
	}

	@Override
	public void visit(ClassDeclaration classtDeclaration) {
		Tab.chainLocalSymbols(classtDeclaration.getClassName().obj);
		
		report_info("INFO:  Definisana klasa " +classtDeclaration.getClassName().obj.getName(), classtDeclaration);
		Tab.closeScope();
		currentLevel--;
	}
	
	// 1. TODO: provera klase koja se implemetira:
	//			- da li postoji i proveriti level
	// 2. TODO: provera instefejsa koji se nasledjuju
	//			- da li su intefejsi
	//			- da se ne ponovi neki...
	
	
	/*
	 * INTERFACE ************************************************************************
	 */
	
	
	
	
	/*
	 * METHOD DECLARATION ***************************************************************
	 */

	
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
		//report_info("Obradjuje se funkcija " + methodTypeName.getMethodName(), methodTypeName);
    }
 
   
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
    
    public void visit(ReturnStmt returnExpr){
 //   	this.returnFound = true;
    	Struct currMethType = currentMethod.getType();
    	// TODO:
//    	if(!currMethType.compatibleWith(returnExpr.getExpr().struct)){
//			report_error("Greska na liniji " + returnExpr.getLine() + " : " + "tip izraza u return naredbi ne slaze se sa tipom povratne vrednosti funkcije " + currentMethod.getName(), null);
//    	}
    }
    
}