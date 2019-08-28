// generated with ast extension for cup
// version 0.8
// 28/7/2019 3:33:20


package rs.ac.bg.etf.pp1.ast;

public class Negative extends OptMinus {

    public Negative () {
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
        buffer.append("Negative(\n");

        buffer.append(tab);
        buffer.append(") [Negative]");
        return buffer.toString();
    }
}
