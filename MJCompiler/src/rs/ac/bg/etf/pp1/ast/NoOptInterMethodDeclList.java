// generated with ast extension for cup
// version 0.8
// 22/7/2019 18:1:15


package rs.ac.bg.etf.pp1.ast;

public class NoOptInterMethodDeclList extends OptInterfaceMethodDeclList {

    public NoOptInterMethodDeclList () {
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
        buffer.append("NoOptInterMethodDeclList(\n");

        buffer.append(tab);
        buffer.append(") [NoOptInterMethodDeclList]");
        return buffer.toString();
    }
}
