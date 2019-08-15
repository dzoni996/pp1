// generated with ast extension for cup
// version 0.8
// 15/7/2019 20:59:26


package rs.ac.bg.etf.pp1.ast;

public class Positive extends OptMinus {

    public Positive () {
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
        buffer.append("Positive(\n");

        buffer.append(tab);
        buffer.append(") [Positive]");
        return buffer.toString();
    }
}
