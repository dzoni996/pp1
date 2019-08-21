// generated with ast extension for cup
// version 0.8
// 21/7/2019 2:28:51


package rs.ac.bg.etf.pp1.ast;

public class OptMethods extends OptMethodDecl {

    private OptMethodDecl OptMethodDecl;
    private MethodDeclList MethodDeclList;

    public OptMethods (OptMethodDecl OptMethodDecl, MethodDeclList MethodDeclList) {
        this.OptMethodDecl=OptMethodDecl;
        if(OptMethodDecl!=null) OptMethodDecl.setParent(this);
        this.MethodDeclList=MethodDeclList;
        if(MethodDeclList!=null) MethodDeclList.setParent(this);
    }

    public OptMethodDecl getOptMethodDecl() {
        return OptMethodDecl;
    }

    public void setOptMethodDecl(OptMethodDecl OptMethodDecl) {
        this.OptMethodDecl=OptMethodDecl;
    }

    public MethodDeclList getMethodDeclList() {
        return MethodDeclList;
    }

    public void setMethodDeclList(MethodDeclList MethodDeclList) {
        this.MethodDeclList=MethodDeclList;
    }

    public void accept(Visitor visitor) {
        visitor.visit(this);
    }

    public void childrenAccept(Visitor visitor) {
        if(OptMethodDecl!=null) OptMethodDecl.accept(visitor);
        if(MethodDeclList!=null) MethodDeclList.accept(visitor);
    }

    public void traverseTopDown(Visitor visitor) {
        accept(visitor);
        if(OptMethodDecl!=null) OptMethodDecl.traverseTopDown(visitor);
        if(MethodDeclList!=null) MethodDeclList.traverseTopDown(visitor);
    }

    public void traverseBottomUp(Visitor visitor) {
        if(OptMethodDecl!=null) OptMethodDecl.traverseBottomUp(visitor);
        if(MethodDeclList!=null) MethodDeclList.traverseBottomUp(visitor);
        accept(visitor);
    }

    public String toString(String tab) {
        StringBuffer buffer=new StringBuffer();
        buffer.append(tab);
        buffer.append("OptMethods(\n");

        if(OptMethodDecl!=null)
            buffer.append(OptMethodDecl.toString("  "+tab));
        else
            buffer.append(tab+"  null");
        buffer.append("\n");

        if(MethodDeclList!=null)
            buffer.append(MethodDeclList.toString("  "+tab));
        else
            buffer.append(tab+"  null");
        buffer.append("\n");

        buffer.append(tab);
        buffer.append(") [OptMethods]");
        return buffer.toString();
    }
}
