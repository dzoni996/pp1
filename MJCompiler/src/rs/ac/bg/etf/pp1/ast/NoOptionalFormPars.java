// generated with ast extension for cup
// version 0.8
// 16/7/2019 15:15:36


package rs.ac.bg.etf.pp1.ast;

public class NoOptionalFormPars extends OptFormPars {

    public NoOptionalFormPars () {
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
        buffer.append("NoOptionalFormPars(\n");

        buffer.append(tab);
        buffer.append(") [NoOptionalFormPars]");
        return buffer.toString();
    }
}
