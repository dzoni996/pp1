// generated with ast extension for cup
// version 0.8
// 18/7/2019 22:44:16


package rs.ac.bg.etf.pp1.ast;

public class ExtendsError extends Extend {

    public ExtendsError () {
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
        buffer.append("ExtendsError(\n");

        buffer.append(tab);
        buffer.append(") [ExtendsError]");
        return buffer.toString();
    }
}
