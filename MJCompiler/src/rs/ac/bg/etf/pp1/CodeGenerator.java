package rs.ac.bg.etf.pp1;

import static rs.ac.bg.etf.pp1.SemanticAnalyzer.*;

import java.util.Collection;

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
	
	protected Struct currentType;
	protected Obj currentDesign;
	protected Obj one;
	
	/*
	 * AUXILIARY METHODS ***************************************************************
	 */
	
	
	
	/*
	 * TYPE ***************************************************************************** 
	 */
	
	public void visit(Type type) {
		this.currentType = Tab.find(type.getTypeName()).getType();
	}
	
	/*
	 * PRINT STMT ***********************************************************************
	 */
	
	public void visit(PrintStmt print) {
		if (print.getExpr().struct == Tab.intType) {
			Code.loadConst(5);
			Code.put(Code.print);
		} else {
			Code.loadConst(1);
			Code.put(Code.print);
		}
	}
	
	public void visit(ReadStmt read) {
		Obj node = read.getDesignator().obj;
		if (node.getType() == intType) {
			Code.put(Code.read);
			// Code.put(Code.pop); ???
			Code.store(node);
		} else {
			Code.put(Code.bread);
			// Code.put(Code.pop); ???
			Code.store(node);
		}
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
		SyntaxNode node = type.getParent();
		
		VarCounter varCnt = new VarCounter();
		type.traverseTopDown(varCnt);
		
		FormParamCounter fpCnt = new FormParamCounter();
		type.traverseTopDown(fpCnt);
		
		
		// Generate the entry
		Code.put(Code.enter);
		Code.put(fpCnt.getCount());
		Code.put(fpCnt.getCount() + varCnt.getCount());
		
	}
	
	public void visit(MethodDeclarations meth) {
		Code.put(Code.exit);
		Code.put(Code.return_);
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
		if (assign.getDesignator() instanceof DesignVar) {
			Obj node = assign.getDesignator().obj;
			Code.store(node);
		}
		else if (assign.getDesignator() instanceof DesignArr) {
			// new array is already alloc and array adr is on expr stack
			Code.store(assign.getDesignator().obj);
		} else if (assign.getDesignator() instanceof DesignFld) { /* DesignFld */
			Code.store((assign.getDesignator()).obj);
		}
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
	 * PROCEDURES ***********************************************************************
	 */
	
/*	
	public void visit(ProcCall proc) {
		int offset = proc.getDesignator().obj.getAdr() - Code.pc;
		Code.put(Code.call);
		Code.put2(offset);
		
		if (proc.getDesignator().obj.getType() != Tab.noType) {
			Code.put(Code.pop); // leaves empty expr stack
		}
	}
	
	public void visit(RetExpr ret) {
		Code.put(Code.exit);
		Code.put(Code.return_);
	}
	
	public void visit(NoRet ret) {
		Code.put(Code.exit);
		Code.put(Code.return_);
	}
	
	
	
	*/
	
}
