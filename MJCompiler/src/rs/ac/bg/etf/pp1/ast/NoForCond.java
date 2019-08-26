// generated with ast extension for cup
// version 0.8
// 26/7/2019 20:47:6


package rs.ac.bg.etf.pp1.ast;

public class NoForCond extends OptCond {

    public NoForCond () {
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
        buffer.append("NoForCond(\n");

        buffer.append(tab);
        buffer.append(") [NoForCond]");
        return buffer.toString();
    }
}
