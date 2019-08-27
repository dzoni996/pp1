// generated with ast extension for cup
// version 0.8
// 27/7/2019 23:17:6


package rs.ac.bg.etf.pp1.ast;

public class MethodDeclarations extends MethodDeclList {

    private MethDeclTypeName MethDeclTypeName;
    private OptFormPars OptFormPars;
    private OptVarDecl OptVarDecl;
    private OptStmt OptStmt;

    public MethodDeclarations (MethDeclTypeName MethDeclTypeName, OptFormPars OptFormPars, OptVarDecl OptVarDecl, OptStmt OptStmt) {
        this.MethDeclTypeName=MethDeclTypeName;
        if(MethDeclTypeName!=null) MethDeclTypeName.setParent(this);
        this.OptFormPars=OptFormPars;
        if(OptFormPars!=null) OptFormPars.setParent(this);
        this.OptVarDecl=OptVarDecl;
        if(OptVarDecl!=null) OptVarDecl.setParent(this);
        this.OptStmt=OptStmt;
        if(OptStmt!=null) OptStmt.setParent(this);
    }

    public MethDeclTypeName getMethDeclTypeName() {
        return MethDeclTypeName;
    }

    public void setMethDeclTypeName(MethDeclTypeName MethDeclTypeName) {
        this.MethDeclTypeName=MethDeclTypeName;
    }

    public OptFormPars getOptFormPars() {
        return OptFormPars;
    }

    public void setOptFormPars(OptFormPars OptFormPars) {
        this.OptFormPars=OptFormPars;
    }

    public OptVarDecl getOptVarDecl() {
        return OptVarDecl;
    }

    public void setOptVarDecl(OptVarDecl OptVarDecl) {
        this.OptVarDecl=OptVarDecl;
    }

    public OptStmt getOptStmt() {
        return OptStmt;
    }

    public void setOptStmt(OptStmt OptStmt) {
        this.OptStmt=OptStmt;
    }

    public void accept(Visitor visitor) {
        visitor.visit(this);
    }

    public void childrenAccept(Visitor visitor) {
        if(MethDeclTypeName!=null) MethDeclTypeName.accept(visitor);
        if(OptFormPars!=null) OptFormPars.accept(visitor);
        if(OptVarDecl!=null) OptVarDecl.accept(visitor);
        if(OptStmt!=null) OptStmt.accept(visitor);
    }

    public void traverseTopDown(Visitor visitor) {
        accept(visitor);
        if(MethDeclTypeName!=null) MethDeclTypeName.traverseTopDown(visitor);
        if(OptFormPars!=null) OptFormPars.traverseTopDown(visitor);
        if(OptVarDecl!=null) OptVarDecl.traverseTopDown(visitor);
        if(OptStmt!=null) OptStmt.traverseTopDown(visitor);
    }

    public void traverseBottomUp(Visitor visitor) {
        if(MethDeclTypeName!=null) MethDeclTypeName.traverseBottomUp(visitor);
        if(OptFormPars!=null) OptFormPars.traverseBottomUp(visitor);
        if(OptVarDecl!=null) OptVarDecl.traverseBottomUp(visitor);
        if(OptStmt!=null) OptStmt.traverseBottomUp(visitor);
        accept(visitor);
    }

    public String toString(String tab) {
        StringBuffer buffer=new StringBuffer();
        buffer.append(tab);
        buffer.append("MethodDeclarations(\n");

        if(MethDeclTypeName!=null)
            buffer.append(MethDeclTypeName.toString("  "+tab));
        else
            buffer.append(tab+"  null");
        buffer.append("\n");

        if(OptFormPars!=null)
            buffer.append(OptFormPars.toString("  "+tab));
        else
            buffer.append(tab+"  null");
        buffer.append("\n");

        if(OptVarDecl!=null)
            buffer.append(OptVarDecl.toString("  "+tab));
        else
            buffer.append(tab+"  null");
        buffer.append("\n");

        if(OptStmt!=null)
            buffer.append(OptStmt.toString("  "+tab));
        else
            buffer.append(tab+"  null");
        buffer.append("\n");

        buffer.append(tab);
        buffer.append(") [MethodDeclarations]");
        return buffer.toString();
    }
}
