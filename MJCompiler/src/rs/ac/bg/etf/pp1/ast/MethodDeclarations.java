// generated with ast extension for cup
// version 0.8
// 20/7/2019 23:23:20


package rs.ac.bg.etf.pp1.ast;

public class MethodDeclarations extends MethodDeclList {

    private MethodDeclTypeName MethodDeclTypeName;
    private OptFormPars OptFormPars;
    private OptVarDecl OptVarDecl;
    private OptStmt OptStmt;

    public MethodDeclarations (MethodDeclTypeName MethodDeclTypeName, OptFormPars OptFormPars, OptVarDecl OptVarDecl, OptStmt OptStmt) {
        this.MethodDeclTypeName=MethodDeclTypeName;
        if(MethodDeclTypeName!=null) MethodDeclTypeName.setParent(this);
        this.OptFormPars=OptFormPars;
        if(OptFormPars!=null) OptFormPars.setParent(this);
        this.OptVarDecl=OptVarDecl;
        if(OptVarDecl!=null) OptVarDecl.setParent(this);
        this.OptStmt=OptStmt;
        if(OptStmt!=null) OptStmt.setParent(this);
    }

    public MethodDeclTypeName getMethodDeclTypeName() {
        return MethodDeclTypeName;
    }

    public void setMethodDeclTypeName(MethodDeclTypeName MethodDeclTypeName) {
        this.MethodDeclTypeName=MethodDeclTypeName;
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
        if(MethodDeclTypeName!=null) MethodDeclTypeName.accept(visitor);
        if(OptFormPars!=null) OptFormPars.accept(visitor);
        if(OptVarDecl!=null) OptVarDecl.accept(visitor);
        if(OptStmt!=null) OptStmt.accept(visitor);
    }

    public void traverseTopDown(Visitor visitor) {
        accept(visitor);
        if(MethodDeclTypeName!=null) MethodDeclTypeName.traverseTopDown(visitor);
        if(OptFormPars!=null) OptFormPars.traverseTopDown(visitor);
        if(OptVarDecl!=null) OptVarDecl.traverseTopDown(visitor);
        if(OptStmt!=null) OptStmt.traverseTopDown(visitor);
    }

    public void traverseBottomUp(Visitor visitor) {
        if(MethodDeclTypeName!=null) MethodDeclTypeName.traverseBottomUp(visitor);
        if(OptFormPars!=null) OptFormPars.traverseBottomUp(visitor);
        if(OptVarDecl!=null) OptVarDecl.traverseBottomUp(visitor);
        if(OptStmt!=null) OptStmt.traverseBottomUp(visitor);
        accept(visitor);
    }

    public String toString(String tab) {
        StringBuffer buffer=new StringBuffer();
        buffer.append(tab);
        buffer.append("MethodDeclarations(\n");

        if(MethodDeclTypeName!=null)
            buffer.append(MethodDeclTypeName.toString("  "+tab));
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
