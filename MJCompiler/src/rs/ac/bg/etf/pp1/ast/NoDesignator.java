// generated with ast extension for cup
// version 0.8
// 19/7/2019 0:33:4


package rs.ac.bg.etf.pp1.ast;

public class NoDesignator extends OptDesign {

    public NoDesignator () {
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
        buffer.append("NoDesignator(\n");

        buffer.append(tab);
        buffer.append(") [NoDesignator]");
        return buffer.toString();
    }
}
