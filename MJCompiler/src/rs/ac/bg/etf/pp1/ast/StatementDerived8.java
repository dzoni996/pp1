// generated with ast extension for cup
// version 0.8
// 15/7/2019 20:59:26


package rs.ac.bg.etf.pp1.ast;

public class StatementDerived8 extends Statement {

    private OptStmt OptStmt;

    public StatementDerived8 (OptStmt OptStmt) {
        this.OptStmt=OptStmt;
        if(OptStmt!=null) OptStmt.setParent(this);
    }

    public OptStmt getOptStmt() {
        return OptStmt;
    }

    public void setOptStmt(OptStmt OptStmt) {
        this.OptStmt=OptStmt;
    }

    public void accept(Visitor visitor) {
        visitor.visit(this);
    }

    public void childrenAccept(Visitor visitor) {
        if(OptStmt!=null) OptStmt.accept(visitor);
    }

    public void traverseTopDown(Visitor visitor) {
        accept(visitor);
        if(OptStmt!=null) OptStmt.traverseTopDown(visitor);
    }

    public void traverseBottomUp(Visitor visitor) {
        if(OptStmt!=null) OptStmt.traverseBottomUp(visitor);
        accept(visitor);
    }

    public String toString(String tab) {
        StringBuffer buffer=new StringBuffer();
        buffer.append(tab);
        buffer.append("StatementDerived8(\n");

        if(OptStmt!=null)
            buffer.append(OptStmt.toString("  "+tab));
        else
            buffer.append(tab+"  null");
        buffer.append("\n");

        buffer.append(tab);
        buffer.append(") [StatementDerived8]");
        return buffer.toString();
    }
}
