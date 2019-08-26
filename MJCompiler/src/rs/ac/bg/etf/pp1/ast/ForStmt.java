// generated with ast extension for cup
// version 0.8
// 26/7/2019 23:12:35


package rs.ac.bg.etf.pp1.ast;

public class ForStmt extends Statement {

    private OptForDesignStmt OptForDesignStmt;
    private OptCond OptCond;
    private OptDesignStmt OptDesignStmt;
    private Statement Statement;

    public ForStmt (OptForDesignStmt OptForDesignStmt, OptCond OptCond, OptDesignStmt OptDesignStmt, Statement Statement) {
        this.OptForDesignStmt=OptForDesignStmt;
        if(OptForDesignStmt!=null) OptForDesignStmt.setParent(this);
        this.OptCond=OptCond;
        if(OptCond!=null) OptCond.setParent(this);
        this.OptDesignStmt=OptDesignStmt;
        if(OptDesignStmt!=null) OptDesignStmt.setParent(this);
        this.Statement=Statement;
        if(Statement!=null) Statement.setParent(this);
    }

    public OptForDesignStmt getOptForDesignStmt() {
        return OptForDesignStmt;
    }

    public void setOptForDesignStmt(OptForDesignStmt OptForDesignStmt) {
        this.OptForDesignStmt=OptForDesignStmt;
    }

    public OptCond getOptCond() {
        return OptCond;
    }

    public void setOptCond(OptCond OptCond) {
        this.OptCond=OptCond;
    }

    public OptDesignStmt getOptDesignStmt() {
        return OptDesignStmt;
    }

    public void setOptDesignStmt(OptDesignStmt OptDesignStmt) {
        this.OptDesignStmt=OptDesignStmt;
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
        if(OptForDesignStmt!=null) OptForDesignStmt.accept(visitor);
        if(OptCond!=null) OptCond.accept(visitor);
        if(OptDesignStmt!=null) OptDesignStmt.accept(visitor);
        if(Statement!=null) Statement.accept(visitor);
    }

    public void traverseTopDown(Visitor visitor) {
        accept(visitor);
        if(OptForDesignStmt!=null) OptForDesignStmt.traverseTopDown(visitor);
        if(OptCond!=null) OptCond.traverseTopDown(visitor);
        if(OptDesignStmt!=null) OptDesignStmt.traverseTopDown(visitor);
        if(Statement!=null) Statement.traverseTopDown(visitor);
    }

    public void traverseBottomUp(Visitor visitor) {
        if(OptForDesignStmt!=null) OptForDesignStmt.traverseBottomUp(visitor);
        if(OptCond!=null) OptCond.traverseBottomUp(visitor);
        if(OptDesignStmt!=null) OptDesignStmt.traverseBottomUp(visitor);
        if(Statement!=null) Statement.traverseBottomUp(visitor);
        accept(visitor);
    }

    public String toString(String tab) {
        StringBuffer buffer=new StringBuffer();
        buffer.append(tab);
        buffer.append("ForStmt(\n");

        if(OptForDesignStmt!=null)
            buffer.append(OptForDesignStmt.toString("  "+tab));
        else
            buffer.append(tab+"  null");
        buffer.append("\n");

        if(OptCond!=null)
            buffer.append(OptCond.toString("  "+tab));
        else
            buffer.append(tab+"  null");
        buffer.append("\n");

        if(OptDesignStmt!=null)
            buffer.append(OptDesignStmt.toString("  "+tab));
        else
            buffer.append(tab+"  null");
        buffer.append("\n");

        if(Statement!=null)
            buffer.append(Statement.toString("  "+tab));
        else
            buffer.append(tab+"  null");
        buffer.append("\n");

        buffer.append(tab);
        buffer.append(") [ForStmt]");
        return buffer.toString();
    }
}
