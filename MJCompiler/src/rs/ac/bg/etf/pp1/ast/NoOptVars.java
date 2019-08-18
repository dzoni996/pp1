// generated with ast extension for cup
// version 0.8
// 18/7/2019 22:44:16


package rs.ac.bg.etf.pp1.ast;

public class NoOptVars extends OptVarDecl {

    public NoOptVars () {
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
        buffer.append("NoOptVars(\n");

        buffer.append(tab);
        buffer.append(") [NoOptVars]");
        return buffer.toString();
    }
}
