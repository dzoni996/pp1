// generated with ast extension for cup
// version 0.8
// 22/7/2019 17:31:49


package rs.ac.bg.etf.pp1.ast;

public class VoidIdentificator extends TypeIdent {

    public VoidIdentificator () {
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
        buffer.append("VoidIdentificator(\n");

        buffer.append(tab);
        buffer.append(") [VoidIdentificator]");
        return buffer.toString();
    }
}
