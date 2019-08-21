// generated with ast extension for cup
// version 0.8
// 21/7/2019 2:28:51


package rs.ac.bg.etf.pp1.ast;

public class OptionalStmt extends OptStmt {

    private OptStmt OptStmt;
    private Statement Statement;

    public OptionalStmt (OptStmt OptStmt, Statement Statement) {
        this.OptStmt=OptStmt;
        if(OptStmt!=null) OptStmt.setParent(this);
        this.Statement=Statement;
        if(Statement!=null) Statement.setParent(this);
    }

    public OptStmt getOptStmt() {
        return OptStmt;
    }

    public void setOptStmt(OptStmt OptStmt) {
        this.OptStmt=OptStmt;
    }

    public Statement getStatement() {
        return Statement;
    }

    public void setStatement(Statement Statement) {
        this.Statement=Statement;
    }

    public void accept(Visitor visitor) {
        visitor.visit(this);
    }

    public void childrenAccept(Visitor visitor) {
        if(OptStmt!=null) OptStmt.accept(visitor);
        if(Statement!=null) Statement.accept(visitor);
    }

    public void traverseTopDown(Visitor visitor) {
        accept(visitor);
        if(OptStmt!=null) OptStmt.traverseTopDown(visitor);
        if(Statement!=null) Statement.traverseTopDown(visitor);
    }

    public void traverseBottomUp(Visitor visitor) {
        if(OptStmt!=null) OptStmt.traverseBottomUp(visitor);
        if(Statement!=null) Statement.traverseBottomUp(visitor);
        accept(visitor);
    }

    public String toString(String tab) {
        StringBuffer buffer=new StringBuffer();
        buffer.append(tab);
        buffer.append("OptionalStmt(\n");

        if(OptStmt!=null)
            buffer.append(OptStmt.toString("  "+tab));
        else
            buffer.append(tab+"  null");
        buffer.append("\n");

        if(Statement!=null)
            buffer.append(Statement.toString("  "+tab));
        else
            buffer.append(tab+"  null");
        buffer.append("\n");

        buffer.append(tab);
        buffer.append(") [OptionalStmt]");
        return buffer.toString();
    }
}
