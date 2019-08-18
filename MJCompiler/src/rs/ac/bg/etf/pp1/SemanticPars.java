package rs.ac.bg.etf.pp1;

import org.apache.log4j.Logger;

import rs.ac.bg.etf.pp1.ast.MethodDeclList;
import rs.ac.bg.etf.pp1.ast.MethodDeclTypeName;
import rs.ac.bg.etf.pp1.ast.ProgName;
import rs.ac.bg.etf.pp1.ast.Program;
import rs.ac.bg.etf.pp1.ast.SyntaxNode;
import rs.ac.bg.etf.pp1.ast.Type;
import rs.ac.bg.etf.pp1.ast.Var;
import rs.ac.bg.etf.pp1.ast.VisitorAdaptor;
import rs.etf.pp1.symboltable.Tab;
import rs.etf.pp1.symboltable.concepts.Obj;

public class SemanticPars extends VisitorAdaptor {
	
	/*
	 * LOCAL VARS ***********************************************************************
	 */
	Obj currentMethod = null;
	
	private int curLevel = -1;
	
	
	Logger log = Logger.getLogger(getClass());
	
	/*
	 * METHODS FOR ERROR INFO ***********************************************************
	 */
	
	public void report_error(String message, SyntaxNode info) {
		StringBuilder msg = new StringBuilder(message);
		int line = (info == null) ? 0: info.getLine();
		if (line != 0)
			msg.append (" na liniji ").append(line);
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
		report_info("Pocetak programa "+pname.getPName(), pname);
		pname.obj = Tab.insert(Obj.Prog, pname.getPName(), Tab.noType);
		Tab.openScope();
		curLevel++;
	}
	
	@Override
	public void visit(Program prog) {
		// TODO: broj promenljivih - azurirati: nVars = Tab.currentScope.getnVars();
		Tab.chainLocalSymbols(prog.getProgName().obj);
		Tab.closeScope();
		curLevel--;
	}
	
	/*
	 * TYPE *****************************************************************************
	 */
	
	public void visit(Type type) {
		Obj typeNode = Tab.find(type.getTypeName());
		
		// if this type does not exist
    	if(typeNode == Tab.noObj){
    		report_error("Nije pronadjen tip " + type.getTypeName() + " u tabeli simbola! ", null);
    		type.struct = Tab.noType;
    	}else{
        	// if type is found in table
    		if(Obj.Type == typeNode.getKind()){
    			type.struct = typeNode.getType();
    		// if this is not type
    		}else{
    			report_error("GREASKA: Ime " + type.getTypeName() + " ne predstavlja tip!", type);
    			type.struct = Tab.noType;
    		}
    	}
	}
	
	/*
	 * METHOD DECLARATION ***************************************************************
	 */

	/*
    public void visit(MethodDeclTypeName methodTypeName){
    	//currentMethod = Tab.insert(Obj.Meth, methodTypeName.getMethodName(), methodTypeName.getTypeIdent().);
    	methodTypeName.obj = currentMethod;
    	Tab.openScope();
		report_info("Obradjuje se funkcija " + methodTypeName.getMethodName(), methodTypeName);
    }
 
    public void visit(MethodDeclList methodDecl){
 //   	if(!returnFound && currentMethod.getType() != Tab.noType){
//			report_error("Semanticka greska na liniji " + methodDecl.getLine() + ": funkcija " + currentMethod.getName() + " nema return iskaz!", null);
  //  	}
    	Tab.chainLocalSymbols(currentMethod);
    	Tab.closeScope();
    	
  //  	returnFound = false;
    	currentMethod = null;
   
	*/
}
