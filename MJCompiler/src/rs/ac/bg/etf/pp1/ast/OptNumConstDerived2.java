// generated with ast extension for cup
// version 0.8
// 16/7/2019 15:15:36


package rs.ac.bg.etf.pp1.ast;

public class OptNumConstDerived2 extends OptNumConst {

    public OptNumConstDerived2 () {
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
        buffer.append("OptNumConstDerived2(\n");

        buffer.append(tab);
        buffer.append(") [OptNumConstDerived2]");
        return buffer.toString();
    }
}
