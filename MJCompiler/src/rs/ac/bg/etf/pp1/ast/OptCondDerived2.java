// generated with ast extension for cup
// version 0.8
// 16/7/2019 1:53:29


package rs.ac.bg.etf.pp1.ast;

public class OptCondDerived2 extends OptCond {

    public OptCondDerived2 () {
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
        buffer.append("OptCondDerived2(\n");

        buffer.append(tab);
        buffer.append(") [OptCondDerived2]");
        return buffer.toString();
    }
}