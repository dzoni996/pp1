package rs.ac.bg.etf.pp1;

import static rs.ac.bg.etf.pp1.SemanticAnalyzer.*;

import java.util.Collection;
import java.util.LinkedList;
import java.util.Stack;

import rs.ac.bg.etf.pp1.CounterVisitor.*;
import rs.ac.bg.etf.pp1.ast.*;
import rs.etf.pp1.mj.runtime.Code;
import rs.etf.pp1.symboltable.Tab;
import rs.etf.pp1.symboltable.concepts.Obj;
import rs.etf.pp1.symboltable.concepts.Struct;

public class CodeGenerator extends VisitorAdaptor{
	
	/*
	 * LOCALS **************************************************************************
	 */
	
	private int mainPC;
	
	public CodeGenerator() {
		currentType = Tab.noType;
		currentDesign = Tab.noObj;
		this.one = new Obj(Obj.Con, "one", intType);
		one.setAdr(1);
	}
	
	public int getMainPC() {
		return this.mainPC;
	}
	
	protected Obj currentDesign;
	protected Obj one;
	protected Struct currentType;
	protected Relop currentRelop = null;
	private int currentOp = 0;
	
	protected Stack<Integer> currentfixupStack = new Stack<Integer>();

	
	/*
	 * AUXILIARY METHODS ***************************************************************
	 */
	
	class fixupAdr {
		int fixupAdr;
		int level;
		fixupAdr(int adr, int lvl) {
			level = lvl; fixupAdr = adr;
		}
	};
	
	protected int createLenFunc() { // return start adr of func
		int adr = Code.pc;
		Code.put(Code.load_n);
		Code.put(Code.arraylength);		
		return adr;
	}
	
	/*
	 * TYPE ***************************************************************************** 
	 */
	
	public void visit(Type type) {
		this.currentType = Tab.find(type.getTypeName()).getType();
	}
	
	/*
	 * PRINT & READ STMT ****************************************************************
	 */
	
	public void visit(PrintNewLine print) {
		Code.loadConst('\n');
		Code.put(Code.bprint);
	}
	
	public void visit(PrintStmt print) {
		Code.loadConst(print.getOptNumConst().obj.getAdr()); // width
		if (print.getExpr().struct == charType) {
			Code.put(Code.bprint);
		} else {
			Code.put(Code.print);
		}
	}
	
	public void visit(PrintWidth pw) {
		pw.obj = new Obj(Obj.Con, "width", intType, pw.getWidth(), -1);
	}
	
	public void visit(DefaultWidth df) {
		df.obj = new Obj(Obj.Con, "width", intType, 4, -1);
	}
	
	public void visit(ReadStmt read) {
		Obj node = read.getDesignator().obj;
		if (node.getType() == charType) {
			Code.put(Code.bread);
		} else {
			Code.put(Code.read); 
		}
		Code.store(node);
	}
	/*
	 * METHODS **************************************************************************
	 */
	
	public void visit(MethodDeclTypeName type) {
		
		if (type.getMethodName().equals("main")) {
			this.mainPC = Code.pc; 
		}
		type.obj.setAdr(Code.pc);
		
		// Collect arguments and local variables
//		SyntaxNode node = type.getParent();
//		
//		VarCounter varCnt = new VarCounter();
//		type.traverseTopDown(varCnt);
//		
//		FormParamCounter fpCnt = new FormParamCounter();
//		type.traverseTopDown(fpCnt);
		
		
		// Generate the entry
		Code.put(Code.enter);
//		Code.put(fpCnt.getCount());
//		Code.put(fpCnt.getCount() + varCnt.getCount());
		Code.put(type.obj.getLevel());
		Code.put(type.obj.getLocalSymbols().size());
		
	}
	
	public void visit(MethodDeclarations meth) {
		if (meth.getMethDeclTypeName().obj.getType() == noType)
			this.returnFromMeth();
	}
	
	

	/*
	 * FACTOR ***************************************************************************
	 */
	
	public void visit(NumFactor num) {
		Obj con = Tab.insert(Obj.Con, "$", num.struct);
		con.setLevel(0);
		con.setAdr(num.getN1());
	
		Code.load(con); // put Obj con on expr stack
	}
	
	public void visit(CharFactor chr) {
		Obj con = Tab.insert(Obj.Con, "$", chr.struct);
		con.setLevel(0);
		con.setAdr(chr.getC1());
	
		Code.load(con); // put Obj con on expr stack
	}
	
	public void visit(BoolFactor bool) {
		Obj con = Tab.insert(Obj.Con, "$", Tab.intType);
		con.setLevel(0);
		con.setAdr("true".equals(bool.getB1())? 1 : 0);
	
		Code.load(con); // put Obj con on expr stack
	}
	
	public void visit(NewFactor newFactor) {
		// instanciranje objekta klase
		int n = newFactor.struct.getMembers().size();
		Code.put(Code.new_);
		Code.put2(n);
	}
	
	public void visit(NewArrFactor newArr) {
		// instanciranje objekta niza
		
		// 1. array size is already on expr stack (because it is Expr)
		
		// 2. check elem type (decause of elem width)
		int b = 0;
		if (newArr.getType().struct == charType) b=0;
		else if (newArr.getType().struct == intType) b=1;
		
		// 3. set instructions on program stack
		Code.put(Code.newarray);
		Code.put(b);
		
		// 4. as result, array adr is on expr stack
		// Code.store(); -> we will store later (in assign oper)
		
	}
	
	/*
	 * EXPR *****************************************************************************
	 */
	
	public void visit(Expression expr) {
		if (expr.getOptMinus() instanceof Negative)
			Code.put(Code.neg);
		
	}

	public void visit(WithAddTerms add) {
		if (add.getAddop() instanceof PlusOp)
			Code.put(Code.add);
		else
			Code.put(Code.sub);
	}
	
	
	public void visit(WithMulFacts mul) {
		if (mul.getMulop() instanceof MultOp)
			Code.put(Code.mul);
		else if (mul.getMulop() instanceof DivOp)
			Code.put(Code.div);
		else 
			Code.put(Code.rem);
		
	}	
	
	/*
	 * DESIGNATOR ***********************************************************************
	 */
		
	public void visit(DesignFactor fact) {
		// methods call
		if (fact.getOptMethodCall() instanceof WithMethodCall) {
			int offset = fact.getDesignator().obj.getAdr() - Code.pc;
			Code.put(Code.call);
			Code.put2(offset);
		}
		
		// var access
		if (fact.getOptMethodCall() instanceof NoMethodCall) {
			if (fact.getDesignator() instanceof DesignVar){
				Obj node = new Obj(fact.getDesignator().obj.getKind(), "", fact.getDesignator().obj.getType());
				node.setAdr(fact.getDesignator().obj.getAdr());
				node.setLevel(fact.getDesignator().obj.getLevel());
				Code.load(node);
			}
			if (fact.getDesignator() instanceof DesignFld) {
				DesignFld fld = (DesignFld) fact.getDesignator();
				
				//Code.load(fld.getDesignator().obj);	// adr of struct
				Code.load(fld.obj);
				
			}
		} 
	}
	
	
	public void visit(DesignatorName name ) {
		//name.obj = Tab.find(name.getId()); tabela vraca null, a obj je vec ucitan!		
	}
	
	public void visit(DesignVar var) {		
		Obj node = var.getDesigName().obj;
		if (var.getParent() instanceof DesignArr) {
			//node = new Obj(Obj.Elem, "elem", node.getType().getElemType(), node.getAdr(), node.getAdr());
			//Code.load(node);
			Code.load(node);
		}
		var.obj = node;
	}
	
	public void visit(DesignArr arr) {
		
		// designator.obj = arr[index]
		// adr & index already on stack
		
		if (arr.getParent() instanceof DesignFactor) {  // r-value
			Struct elemType = arr.getDesignator().obj.getType().getElemType();
			int adr = arr.getDesignator().obj.getAdr();
			int lvl = arr.getDesignator().obj.getLevel();
			arr.obj = new Obj(Obj.Elem, "elem", elemType, adr, lvl);
			Code.load(arr.obj);
		} else {										// l-value
			//Code.store(arr.obj); --> in Assign!
		}
		
		
	}
	
	
	public void visit(DesignFld fld) {
		if (fld.getDesignator().obj.getType().getKind() == Struct.Enum) {
			Collection<Obj> coll = fld.getDesignator().obj.getLocalSymbols();
			for (Obj cur: coll) {
				if (fld.getId().equals(cur.getName())) {
					Obj obj = new Obj(Obj.Con, "enumconst", intType, cur.getAdr(), cur.getLevel());
					//Code.load(obj);
					fld.obj = obj;
					break;
				}
			}
		} else { // CLASS & INTERFACES
			// fields
			Code.load(fld.getDesignator().obj);	// adr of struct
			//Code.load(fld.obj);					// field

		}
	}
	
	/*
	 * STATEMENTS ***********************************************************************
	 */
	
	public void visit(AssignOper assign) {
//		if (assign.getDesignator() instanceof DesignVar) {
//			Obj node = assign.getDesignator().obj;
//			Code.store(node);
//		}
//		else if (assign.getDesignator() instanceof DesignArr) {
//			// new array is already alloc and array adr is on expr stack
//			Code.store(assign.getDesignator().obj);
//		} else if (assign.getDesignator() instanceof DesignFld) { /* DesignFld */
//			Code.store((assign.getDesignator()).obj);
//		}
		Code.store(assign.getDesignator().obj);
	}
	
	public void visit(PlusPlusSideEff pp) {
		
		if (pp.getDesignator() instanceof DesignVar) {
			Obj node = pp.getDesignator().obj;
			Code.load(node);
			Code.load(this.one);
			Code.put(Code.add);
			Code.store(node);
			pp.getDesignator().obj = node;
		}
		
		if (pp.getDesignator() instanceof DesignArr) {
			DesignArr arr = (DesignArr)pp.getDesignator();		
			
			Code.put(Code.dup2); // double adr & index for later store!
			
			// 1.load
			Code.load(arr.obj);
			// 2. const1
			Code.loadConst(1);
			// 3. add
			Code.put(Code.add);
			// 4. store
			Code.store(arr.obj);
		}
		
		if (pp.getDesignator() instanceof DesignFld) {
			// class filelds
		}
		
	}
	
	public void visit(MinusMinusSideEff pp) {

		if (pp.getDesignator() instanceof DesignVar) {
			Obj node = pp.getDesignator().obj;
			Code.load(node);
			Code.load(this.one);
			Code.put(Code.sub);
			Code.store(node);
			pp.getDesignator().obj = node;
		}
		
		if (pp.getDesignator() instanceof DesignArr) {
			DesignArr arr = (DesignArr)pp.getDesignator();		
			
			Code.put(Code.dup2); // double adr & index for later store!
			
			// 1.load
			Code.load(arr.obj);
			// 2. const1
			Code.loadConst(1);
			// 3. add
			Code.put(Code.sub);
			// 4. store
			Code.store(arr.obj);
		}
		
		if (pp.getDesignator() instanceof DesignFld) {
			// class filelds
		}
	}
	
	/*
	 * IF STMT **************************************************************************
	 */
	
	public int getRelOp(Relop op) {
		
		if (op instanceof RelSame) {
			return Code.eq;
			
		}
		
		if (op instanceof RelDif)
			return Code.ne;	
		
		if (op instanceof RelGreather)
			return Code.gt;
		
		if (op instanceof RelGreatherEq)
			return Code.ge;
		
		if (op instanceof RelLess)
			return Code.lt;
		
		if (op instanceof RelLessEq)
			return Code.le;
		
		return -1; // error
		
	}
	
	public void visit(IfStart ifstart) {
		
		Code.loadConst(0);
		Code.putFalseJump(Code.gt, 0);  // TODO: fix this
		// purpose -> to save adr which later need fix
//		ifstart.obj = new Obj(Obj.Con, "ifadr", intType, Code.pc - 2, -1);  // adr1
		this.currentfixupStack.add(Code.pc-2);
		
	}
	
	public void visit(NoElseStmt noelse) {
		// I nacin
		Code.fixup(this.currentfixupStack.pop());
		
		// II nacin
//		Obj node = ((IfStmt)noelse.getParent()).getIfStart().obj;
//		int adr = node.getAdr();
//		Code.fixup(adr); // adr1		
	}
	
	public void visit(ElseStart es) {
//		Code.putJump(0);
//		es.obj = new Obj(Obj.Con, "elsestart", intType, Code.pc - 2, -1); // adr2
//		
//		Obj node = ((IfStmt)es.getParent().getParent()).getIfStart().obj;
//		int adr = node.getAdr();
//		Code.fixup(adr); // adr1
		
		Code.putJump(0);
		Code.fixup(this.currentfixupStack.pop());
		this.currentfixupStack.add(Code.pc-2);
	}
	
	public void visit(ElseEnd end) {
//		Obj node = ((ElseStmt)end.getParent()).getElseStart().obj;
//		int adr2 = node.getAdr();
//		Code.fixup(adr2);	// adr2
		Code.fixup(this.currentfixupStack.pop());
	}
	
	/*
	 * CONDITION ************************************************************************
	 */
	
	public void visit(CondFacts facts) {
		// 2 bool consts are on expr stack
		
		// I nacin
//		currentOp = this.getRelOp(facts.getRelop());
//		Code.putFalseJump(currentOp, 0);
//		this.currentfixupStack.push(Code.pc-2);
		
		// II nacin
		int relop = this.getRelOp(facts.getRelop());
			
			Code.putFalseJump(relop,0);
			int if_false = Code.pc - 2;
			Code.loadConst(1); 
			Code.putJump(0);
			int if_true = Code.pc - 2;
			
			Code.fixup(if_false);
			Code.loadConst(0);
			Code.fixup(if_true);

	}
	
	public void visit(CondFactSingle fact) {
		// I nacin
//		currentOp = Code.ne;
//		Code.put(Code.const_n + 0);
//		
//		Code.putFalseJump(currentOp, 0);
//		this.currentfixupStack.push(Code.pc-2);
		
		// II nacin
		
		// vec je 1 ili 0 na steku
	}
		
	public void visit(CondTerms terms) {
		Code.put(Code.mul);
	}
	
	public void visit(Conditions terms) {
		Code.put(Code.add);
	}
	
	
	/*
	 * FOR ******************************************************************************
	 */
	
	private LinkedList<Integer> forFixUpList = null;
	private Stack<LinkedList<Integer>> allLists = new Stack<LinkedList<Integer>>();
	int currentList = -1;
	
	private Stack<Integer> forFixUpStack = new Stack<Integer>();
	
	private Stack<Integer> continueStack = new Stack<>();
	private Stack<Integer> breakStack = new Stack<>();
	
	private void newForList() {
		currentList++;
		forFixUpList = new LinkedList<>();
		allLists.push(forFixUpList);
	}
	
	private void removeCurrentForList() {
		currentList--;
		forFixUpList = allLists.pop();
		if (allLists.size() > 0) 
			forFixUpList = allLists.lastElement();
	}
	
	
	
	// 1. before condition - save adr
	
	public void visit(OptForStmt opt) {
		newForList();
		
		this.forFixUpList.add(Code.pc);
	}
	
	// 2. after condition 
	// - solve cond
	// - putFalseJmp -> exit for
	// - putJmp -> to Statement
	
	public void visit(NoForCond nofor) { // always true
		// just jump on statement
		Code.putJump(0);
		this.forFixUpStack.push(Code.pc - 2);
	}
	
	public void visit(ForCond forcond) {
		// cond: 0 or 1 is on stack already
		Code.loadConst(0);
		Code.putFalseJump(Code.gt, 0);  
		this.forFixUpStack.add(Code.pc-2);
		
		// if cond is true
		Code.putJump(0);
		this.forFixUpStack.add(Code.pc-2);
	}
	
	// 3. save adr before second opt designator
	
	public void visit(CondEnd end) {
		this.forFixUpList.add(Code.pc);
		
		// this is place where CONTINUE jump
		this.continueStack.push(Code.pc);
	}
	
	// 4. after second opt designator -> jmp on cond
	// 5. resolve putJmp after cond
	
	public void visit(OptForDesignStmt2 stmt) 
	{
		Code.putJump(this.forFixUpList.removeFirst());
		
		Code.fixup(this.forFixUpStack.pop());
	}
	
	// 6. after stmt putJmp to second design stmt
	// 7. resolve false jump after cond - just if cond is not empty!
	
	public void visit(ForStatement stmt) {
		Code.putJump(this.forFixUpList.removeFirst());
		
		ForStmt st = (ForStmt) stmt.getParent();
		if (st.getOptCond() instanceof ForCond)
			Code.fixup(this.forFixUpStack.pop());
		
		// this is place where BREAK jumps
		while (this.breakStack.size() > 0)
			Code.fixup(this.breakStack.pop());
		
		removeCurrentForList();
		
		this.continueStack.pop();
	}
	
	public void visit(ContinueStmt stmt) {
		if (this.continueStack.size()>0)
			Code.putJump(this.continueStack.lastElement());
		else {
			Code.put(Code.trap);
			System.out.println("Stek kod CONTINUE prazan!");
		}
	}
	
	public void visit(BreakStmt stmt) {
		Code.putJump(0);
		this.breakStack.push(Code.pc - 2);
	}
	
	/*
	 * PROCEDURES ***********************************************************************
	 */
	
	
	public void visit(ProcCall proc) {
		int offset = proc.getDesignator().obj.getAdr() - Code.pc;
		Code.put(Code.call);
		Code.put2(offset);
		
		if (proc.getDesignator().obj.getType() != Tab.noType) {
			Code.put(Code.pop); // leaves empty expr stack
		}
	}
	
	
	public void visit(RetExpr ret) {
		this.returnFromMeth();		
	}
	
	public void visit(NoRet ret) {
		this.returnFromMeth();
	}
	
	protected void returnFromMeth() {
		Code.put(Code.exit);
		Code.put(Code.return_);
	}
	
}
