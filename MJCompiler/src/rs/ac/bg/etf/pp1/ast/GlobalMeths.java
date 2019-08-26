// generated with ast extension for cup
// version 0.8
// 26/7/2019 23:12:34


package rs.ac.bg.etf.pp1.ast;

public class GlobalMeths extends GlobalMethDecl {

    private OptMethodDecl OptMethodDecl;

    public GlobalMeths (OptMethodDecl OptMethodDecl) {
        this.OptMethodDecl=OptMethodDecl;
        if(OptMethodDecl!=null) OptMethodDecl.setParent(this);
    }

    public OptMethodDecl getOptMethodDecl() {
        return OptMethodDecl;
    }

    public void setOptMethodDecl(OptMethodDecl OptMethodDecl) {
        this.OptMethodDecl=OptMethodDecl;
    }

    public void accept(Visitor visitor) {
        visitor.visit(this);
    }

    public void childrenAccept(Visitor visitor) {
        if(OptMethodDecl!=null) OptMethodDecl.accept(visitor);
    }

    public void traverseTopDown(Visitor visitor) {
        accept(visitor);
        if(OptMethodDecl!=null) OptMethodDecl.traverseTopDown(visitor);
    }

    public void traverseBottomUp(Visitor visitor) {
        if(OptMethodDecl!=null) OptMethodDecl.traverseBottomUp(visitor);
        accept(visitor);
    }

    public String toString(String tab) {
        StringBuffer buffer=new StringBuffer();
        buffer.append(tab);
        buffer.append("GlobalMeths(\n");

        if(OptMethodDecl!=null)
            buffer.append(OptMethodDecl.toString("  "+tab));
        else
            buffer.append(tab+"  null");
        buffer.append("\n");

        buffer.append(tab);
        buffer.append(") [GlobalMeths]");
        return buffer.toString();
    }
}
