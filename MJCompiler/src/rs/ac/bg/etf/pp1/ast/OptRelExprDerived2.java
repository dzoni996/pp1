// generated with ast extension for cup
// version 0.8
// 16/7/2019 15:15:37


package rs.ac.bg.etf.pp1.ast;

public class OptRelExprDerived2 extends OptRelExpr {

    public OptRelExprDerived2 () {
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
        buffer.append("OptRelExprDerived2(\n");

        buffer.append(tab);
        buffer.append(") [OptRelExprDerived2]");
        return buffer.toString();
    }
}
