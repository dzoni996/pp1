// generated with ast extension for cup
// version 0.8
// 18/7/2019 13:56:53


package rs.ac.bg.etf.pp1.ast;

public class InitializerBool extends Initializer {

    public InitializerBool () {
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
        buffer.append("InitializerBool(\n");

        buffer.append(tab);
        buffer.append(") [InitializerBool]");
        return buffer.toString();
    }
}
