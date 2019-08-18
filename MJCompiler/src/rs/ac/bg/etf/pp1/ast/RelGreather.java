// generated with ast extension for cup
// version 0.8
// 18/7/2019 22:9:24


package rs.ac.bg.etf.pp1.ast;

public class RelGreather extends Relop {

    public RelGreather () {
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
        buffer.append("RelGreather(\n");

        buffer.append(tab);
        buffer.append(") [RelGreather]");
        return buffer.toString();
    }
}
