// generated with ast extension for cup
// version 0.8
// 28/7/2019 10:50:46


package rs.ac.bg.etf.pp1.ast;

public class OptDesignStmtDerived2 extends OptDesignStmt {

    public OptDesignStmtDerived2 () {
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
        buffer.append("OptDesignStmtDerived2(\n");

        buffer.append(tab);
        buffer.append(") [OptDesignStmtDerived2]");
        return buffer.toString();
    }
}
