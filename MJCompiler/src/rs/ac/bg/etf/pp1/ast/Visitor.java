// generated with ast extension for cup
// version 0.8
// 18/7/2019 22:9:24


package rs.ac.bg.etf.pp1.ast;

public interface Visitor { 

    public void visit(EnumDecl EnumDecl);
    public void visit(Mulop Mulop);
    public void visit(OptVarDecl OptVarDecl);
    public void visit(InterfaceMethodDeclList InterfaceMethodDeclList);
    public void visit(InterfaceDecl InterfaceDecl);
    public void visit(Relop Relop);
    public void visit(OptStmt OptStmt);
    public void visit(Initializer Initializer);
    public void visit(Assignop Assignop);
    public void visit(OptFormPars OptFormPars);
    public void visit(OptMinus OptMinus);
    public void visit(Addop Addop);
    public void visit(EnumDeclList EnumDeclList);
    public void visit(Factor Factor);
    public void visit(CondTerm CondTerm);
    public void visit(SideEffect SideEffect);
    public void visit(DeclList DeclList);
    public void visit(Designator Designator);
    public void visit(InterfaceMethodDecl InterfaceMethodDecl);
    public void visit(OptMethodDecl OptMethodDecl);
    public void visit(Term Term);
    public void visit(OptRetExpr OptRetExpr);
    public void visit(OptDesign OptDesign);
    public void visit(Condition Condition);
    public void visit(OptElse OptElse);
    public void visit(VarItem VarItem);
    public void visit(ConstDeclList ConstDeclList);
    public void visit(ConstItem ConstItem);
    public void visit(IfCondition IfCondition);
    public void visit(OptMethodCall OptMethodCall);
    public void visit(DeclItem DeclItem);
    public void visit(OptArraySq OptArraySq);
    public void visit(OptNumConst OptNumConst);
    public void visit(FormParsItem FormParsItem);
    public void visit(InterfaceList InterfaceList);
    public void visit(OptAddTerms OptAddTerms);
    public void visit(OptRelExpr OptRelExpr);
    public void visit(EnumItem EnumItem);
    public void visit(VarDeclList VarDeclList);
    public void visit(OptDesignStmt OptDesignStmt);
    public void visit(Expr Expr);
    public void visit(ActPars ActPars);
    public void visit(Extend Extend);
    public void visit(OptMulTerms OptMulTerms);
    public void visit(DesignatorStatement DesignatorStatement);
    public void visit(OptInterfaceMethodDeclList OptInterfaceMethodDeclList);
    public void visit(Statement Statement);
    public void visit(VarDecl VarDecl);
    public void visit(ClassDecl ClassDecl);
    public void visit(ConstDecl ConstDecl);
    public void visit(DesignItem DesignItem);
    public void visit(CondFact CondFact);
    public void visit(OptActPars OptActPars);
    public void visit(MethodDeclList MethodDeclList);
    public void visit(Implement Implement);
    public void visit(TypeIdent TypeIdent);
    public void visit(OptExpr OptExpr);
    public void visit(OptCond OptCond);
    public void visit(FormPars FormPars);
    public void visit(ClassMethods ClassMethods);
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
    public void visit(OptRelExprDerived2 OptRelExprDerived2);
    public void visit(OptRelExprDerived1 OptRelExprDerived1);
    public void visit(CondFactDerived1 CondFactDerived1);
    public void visit(CondTermDerived2 CondTermDerived2);
    public void visit(CondTermDerived1 CondTermDerived1);
    public void visit(ConditionDerived2 ConditionDerived2);
    public void visit(ConditionDerived1 ConditionDerived1);
    public void visit(NoExpr NoExpr);
    public void visit(WithExpr WithExpr);
    public void visit(NoMethodCall NoMethodCall);
    public void visit(WithMethodCall WithMethodCall);
    public void visit(ExprFactor ExprFactor);
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
    public void visit(Negative Negative);
    public void visit(Positive Positive);
    public void visit(Expression Expression);
    public void visit(DesignArr DesignArr);
    public void visit(DesignField DesignField);
    public void visit(NoDesignator NoDesignator);
    public void visit(OptDesignator OptDesignator);
    public void visit(Design Design);
    public void visit(SingleActPars SingleActPars);
    public void visit(MultiActPars MultiActPars);
    public void visit(NoActPars NoActPars);
    public void visit(WithActPars WithActPars);
    public void visit(AssignError AssignError);
    public void visit(AssignErr AssignErr);
    public void visit(MinusMinusSideEff MinusMinusSideEff);
    public void visit(PlusPlusSideEff PlusPlusSideEff);
    public void visit(ActParsSideEff ActParsSideEff);
    public void visit(AddSideEff AddSideEff);
    public void visit(DesignStmt DesignStmt);
    public void visit(OptCondDerived2 OptCondDerived2);
    public void visit(OptCondDerived1 OptCondDerived1);
    public void visit(OptDesignStmtDerived2 OptDesignStmtDerived2);
    public void visit(OptDesignStmtDerived1 OptDesignStmtDerived1);
    public void visit(OptElseDerived2 OptElseDerived2);
    public void visit(OptElseDerived1 OptElseDerived1);
    public void visit(OptNumConstDerived2 OptNumConstDerived2);
    public void visit(OptNumConstDerived1 OptNumConstDerived1);
    public void visit(OptRetExprDerived2 OptRetExprDerived2);
    public void visit(OptRetExprDerived1 OptRetExprDerived1);
    public void visit(IfError IfError);
    public void visit(IfCond IfCond);
    public void visit(BlockStmt BlockStmt);
    public void visit(PrintStmt PrintStmt);
    public void visit(ReadStmt ReadStmt);
    public void visit(ReturnStmt ReturnStmt);
    public void visit(ContinueStmt ContinueStmt);
    public void visit(BreakStmt BreakStmt);
    public void visit(ForStmt ForStmt);
    public void visit(IfStmt IfStmt);
    public void visit(DesignatorStmt DesignatorStmt);
    public void visit(FormParamFormParam FormParamFormParam);
    public void visit(FormParArray FormParArray);
    public void visit(FormParamError FormParamError);
    public void visit(SingleFormPars SingleFormPars);
    public void visit(MultipleFormPars MultipleFormPars);
    public void visit(NoOptionalStmt NoOptionalStmt);
    public void visit(OptionalStmt OptionalStmt);
    public void visit(MethodDeclTypeName MethodDeclTypeName);
    public void visit(MethodDeclarations MethodDeclarations);
    public void visit(NoOptionalFormPars NoOptionalFormPars);
    public void visit(OptionalFormPars OptionalFormPars);
    public void visit(VoidIdentificator VoidIdentificator);
    public void visit(TypeIdentificator TypeIdentificator);
    public void visit(InterfaceMethodDeclaration InterfaceMethodDeclaration);
    public void visit(SingleInterfaceMethodDeclaration SingleInterfaceMethodDeclaration);
    public void visit(InterfaceMethodDeclarations InterfaceMethodDeclarations);
    public void visit(NoOptInterMethodDeclList NoOptInterMethodDeclList);
    public void visit(OptInterMethodDeclList OptInterMethodDeclList);
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
    public void visit(EnumDeclarations EnumDeclarations);
    public void visit(InitializerBool InitializerBool);
    public void visit(InitializerChar InitializerChar);
    public void visit(InitializerNum InitializerNum);
    public void visit(ConstInit ConstInit);
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
    public void visit(ProgName ProgName);
    public void visit(Program Program);

}
