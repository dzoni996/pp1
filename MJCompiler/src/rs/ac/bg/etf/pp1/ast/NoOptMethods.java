// generated with ast extension for cup
// version 0.8
// 17/7/2019 20:52:6


package rs.ac.bg.etf.pp1.ast;

public class NoOptMethods extends OptMethodDecl {

    public NoOptMethods () {
    }

    public void accept(Visitor visitor) {
        visitor.visit(this);
    }

    public void childrenAccept(Visitor visitor) {
    }

    public void traverseTopDown(Visitor visitor) {
        accept(visitor);
    }

    public void traverseBottomUp(Visitor visitor) {
        accept(visitor);
    }

    public String toString(String tab) {
        StringBuffer buffer=new StringBuffer();
        buffer.append(tab);
        buffer.append("NoOptMethods(\n");

        buffer.append(tab);
        buffer.append(") [NoOptMethods]");
        return buffer.toString();
    }
}
