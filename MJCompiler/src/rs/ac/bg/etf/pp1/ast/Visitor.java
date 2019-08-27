// generated with ast extension for cup
// version 0.8
// 27/7/2019 23:17:7


package rs.ac.bg.etf.pp1.ast;

public interface Visitor { 

    public void visit(FormPars FormPars);
    public void visit(Factor Factor);
    public void visit(Statement Statement);
    public void visit(DeclItem DeclItem);
    public void visit(ClassMethods ClassMethods);
    public void visit(OptDesignStmt OptDesignStmt);
    public void visit(ConstDeclList ConstDeclList);
    public void visit(Implement Implement);
    public void visit(EnumDeclList EnumDeclList);
    public void visit(MethDeclTypeName MethDeclTypeName);
    public void visit(Relop Relop);
    public void visit(DeclList DeclList);
    public void visit(EnumDecl EnumDecl);
    public void visit(OptInterfaceMethodDeclList OptInterfaceMethodDeclList);
    public void visit(OptMinus OptMinus);
    public void visit(DesigName DesigName);
    public void visit(IfCondition IfCondition);
    public void visit(Expr Expr);
    public void visit(InterfaceList InterfaceList);
    public void visit(Initializer Initializer);
    public void visit(VarDecl VarDecl);
    public void visit(OptMethodDecl OptMethodDecl);
    public void visit(OptCond OptCond);
    public void visit(OptActPars OptActPars);
    public void visit(FormParsItem FormParsItem);
    public void visit(EnumItem EnumItem);
    public void visit(Condition Condition);
    public void visit(Mulop Mulop);
    public void visit(DesignatorStatement DesignatorStatement);
    public void visit(GlobalMethDecl GlobalMethDecl);
    public void visit(OptMulTerms OptMulTerms);
    public void visit(Addop Addop);
    public void visit(InterfaceName InterfaceName);
    public void visit(Assignop Assignop);
    public void visit(ConstDecl ConstDecl);
    public void visit(VarItem VarItem);
    public void visit(OptElse OptElse);
    public void visit(OptVarDecl OptVarDecl);
    public void visit(OptStmt OptStmt);
    public void visit(Extend Extend);
    public void visit(OptArraySq OptArraySq);
    public void visit(CondTerm CondTerm);
    public void visit(ClassDecl ClassDecl);
    public void visit(MethodDeclList MethodDeclList);
    public void visit(OptForDesignStmt OptForDesignStmt);
    public void visit(TypeIdent TypeIdent);
    public void visit(OptFormPars OptFormPars);
    public void visit(OptAddTerms OptAddTerms);
    public void visit(OptNumConst OptNumConst);
    public void visit(InterfaceDecl InterfaceDecl);
    public void visit(ActPars ActPars);
    public void visit(InterfaceMethodDecl InterfaceMethodDecl);
    public void visit(Designator Designator);
    public void visit(VarDeclList VarDeclList);
    public void visit(CondFact CondFact);
    public void visit(InterfaceMethodDeclList InterfaceMethodDeclList);
    public void visit(Term Term);
    public void visit(OptMethodCall OptMethodCall);
    public void visit(OptRetExpr OptRetExpr);
    public void visit(EnumName EnumName);
    public void visit(ModOp ModOp);
    public void visit(DivOp DivOp);
    public void visit(MultOp MultOp);
    public void visit(MinusOp MinusOp);
    public void visit(PlusOp PlusOp);
    public void visit(RelLessEq RelLessEq);
    public void visit(RelLess RelLess);
    public void visit(RelGreatherEq RelGreatherEq);
    public void visit(RelGreather RelGreather);
    public void visit(RelDif RelDif);
    public void visit(RelSame RelSame);
    public void visit(AssignOperation AssignOperation);
    public void visit(CondFactSingle CondFactSingle);
    public void visit(CondFacts CondFacts);
    public void visit(CondTermSingle CondTermSingle);
    public void visit(CondTerms CondTerms);
    public void visit(ConditionSingle ConditionSingle);
    public void visit(Conditions Conditions);
    public void visit(NoMethodCall NoMethodCall);
    public void visit(WithMethodCall WithMethodCall);
    public void visit(ExprFactor ExprFactor);
    public void visit(NewArrFactor NewArrFactor);
    public void visit(NewFactor NewFactor);
    public void visit(BoolFactor BoolFactor);
    public void visit(CharFactor CharFactor);
    public void visit(NumFactor NumFactor);
    public void visit(DesignFactor DesignFactor);
    public void visit(NoMulFacts NoMulFacts);
    public void visit(WithMulFacts WithMulFacts);
    public void visit(Terms Terms);
    public void visit(NoAddTerms NoAddTerms);
    public void visit(WithAddTerms WithAddTerms);
    public void visit(Positive Positive);
    public void visit(Negative Negative);
    public void visit(Expression Expression);
    public void visit(DesignatorName DesignatorName);
    public void visit(DesignError DesignError);
    public void visit(DesignFld DesignFld);
    public void visit(DesignArr DesignArr);
    public void visit(DesignVar DesignVar);
    public void visit(SingleActPars SingleActPars);
    public void visit(MultiActPars MultiActPars);
    public void visit(NoActPars NoActPars);
    public void visit(WithActPars WithActPars);
    public void visit(AssignError AssignError);
    public void visit(AssignErr AssignErr);
    public void visit(MinusMinusSideEff MinusMinusSideEff);
    public void visit(PlusPlusSideEff PlusPlusSideEff);
    public void visit(ProcCall ProcCall);
    public void visit(AssignOper AssignOper);
    public void visit(OptDesignStmtDerived2 OptDesignStmtDerived2);
    public void visit(OptDesignStmtDerived1 OptDesignStmtDerived1);
    public void visit(DefaultWidth DefaultWidth);
    public void visit(PrintWidth PrintWidth);
    public void visit(NoRet NoRet);
    public void visit(RetExpr RetExpr);
    public void visit(ElseEnd ElseEnd);
    public void visit(ElseStart ElseStart);
    public void visit(IfStart IfStart);
    public void visit(NoElseStmt NoElseStmt);
    public void visit(ElseStmt ElseStmt);
    public void visit(IfError IfError);
    public void visit(IfCond IfCond);
    public void visit(ForStatement ForStatement);
    public void visit(OptForDesignStmt2 OptForDesignStmt2);
    public void visit(OptForStmt OptForStmt);
    public void visit(CondEnd CondEnd);
    public void visit(NoForCond NoForCond);
    public void visit(ForCond ForCond);
    public void visit(ErrorStmt ErrorStmt);
    public void visit(BlockStmt BlockStmt);
    public void visit(PrintStmt PrintStmt);
    public void visit(PrintNewLine PrintNewLine);
    public void visit(ReadStmt ReadStmt);
    public void visit(ReturnStmt ReturnStmt);
    public void visit(ContinueStmt ContinueStmt);
    public void visit(BreakStmt BreakStmt);
    public void visit(ForStmt ForStmt);
    public void visit(IfStmt IfStmt);
    public void visit(DesignatorStmt DesignatorStmt);
    public void visit(FormParItem FormParItem);
    public void visit(FormParamError FormParamError);
    public void visit(SingleFormPars SingleFormPars);
    public void visit(MultipleFormPars MultipleFormPars);
    public void visit(NoOptionalFormPars NoOptionalFormPars);
    public void visit(OptionalFormPars OptionalFormPars);
    public void visit(NoOptionalStmt NoOptionalStmt);
    public void visit(OptionalStmt OptionalStmt);
    public void visit(MethodDeclTypeName MethodDeclTypeName);
    public void visit(MethodDeclarations MethodDeclarations);
    public void visit(VoidIdentificator VoidIdentificator);
    public void visit(TypeIdentificator TypeIdentificator);
    public void visit(InterfaceMethodDeclaration InterfaceMethodDeclaration);
    public void visit(SingleInterfaceMethodDeclaration SingleInterfaceMethodDeclaration);
    public void visit(InterfaceMethodDeclarations InterfaceMethodDeclarations);
    public void visit(NoOptInterMethodDeclList NoOptInterMethodDeclList);
    public void visit(OptInterMethodDeclList OptInterMethodDeclList);
    public void visit(InterName InterName);
    public void visit(InterfaceDeclarations InterfaceDeclarations);
    public void visit(NoOptMethods NoOptMethods);
    public void visit(OptMethods OptMethods);
    public void visit(NoOptVars NoOptVars);
    public void visit(OptVars OptVars);
    public void visit(NoInterfList NoInterfList);
    public void visit(InterfList InterfList);
    public void visit(NoImplInteraces NoImplInteraces);
    public void visit(ImplInteraces ImplInteraces);
    public void visit(NoSuperClass NoSuperClass);
    public void visit(ExtendsError ExtendsError);
    public void visit(SuperClass SuperClass);
    public void visit(NoClassMethodDecl NoClassMethodDecl);
    public void visit(ClassMethodDecl ClassMethodDecl);
    public void visit(ClassName ClassName);
    public void visit(ClassDeclaration ClassDeclaration);
    public void visit(NoArrayVar NoArrayVar);
    public void visit(ArrayVar ArrayVar);
    public void visit(VarDefError VarDefError);
    public void visit(Var Var);
    public void visit(SingleVar SingleVar);
    public void visit(MultipleVars MultipleVars);
    public void visit(VarDeclarations VarDeclarations);
    public void visit(NoInitEnum NoInitEnum);
    public void visit(InitEnum InitEnum);
    public void visit(SingleEnum SingleEnum);
    public void visit(MultipleEnums MultipleEnums);
    public void visit(EnumNamed EnumNamed);
    public void visit(EnumDeclarations EnumDeclarations);
    public void visit(InitializerBool InitializerBool);
    public void visit(InitializerChar InitializerChar);
    public void visit(InitializerNum InitializerNum);
    public void visit(ConstItem ConstItem);
    public void visit(SingleConst SingleConst);
    public void visit(MultipleConsts MultipleConsts);
    public void visit(ConstDeclarations ConstDeclarations);
    public void visit(Type Type);
    public void visit(InterfaceDeclaration InterfaceDeclaration);
    public void visit(ClasstDeclaration ClasstDeclaration);
    public void visit(VarDeclaration VarDeclaration);
    public void visit(EnumDeclaration EnumDeclaration);
    public void visit(ConstDeclaration ConstDeclaration);
    public void visit(NoVarDecl NoVarDecl);
    public void visit(VarDeclLists VarDeclLists);
    public void visit(GlobalMeths GlobalMeths);
    public void visit(ProgName ProgName);
    public void visit(Program Program);

}
