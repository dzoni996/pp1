// generated with ast extension for cup
// version 0.8
// 22/7/2019 21:49:53


package rs.ac.bg.etf.pp1.ast;

public class OptElseDerived2 extends OptElse {

    public OptElseDerived2 () {
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
        buffer.append("OptElseDerived2(\n");

        buffer.append(tab);
        buffer.append(") [OptElseDerived2]");
        return buffer.toString();
    }
}
