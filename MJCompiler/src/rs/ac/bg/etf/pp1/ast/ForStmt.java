// generated with ast extension for cup
// version 0.8
// 20/7/2019 22:59:17


package rs.ac.bg.etf.pp1.ast;

public class ForStmt extends Statement {

    private OptDesignStmt OptDesignStmt;
    private OptCond OptCond;
    private OptDesignStmt OptDesignStmt1;
    private Statement Statement;

    public ForStmt (OptDesignStmt OptDesignStmt, OptCond OptCond, OptDesignStmt OptDesignStmt1, Statement Statement) {
        this.OptDesignStmt=OptDesignStmt;
        if(OptDesignStmt!=null) OptDesignStmt.setParent(this);
        this.OptCond=OptCond;
        if(OptCond!=null) OptCond.setParent(this);
        this.OptDesignStmt1=OptDesignStmt1;
        if(OptDesignStmt1!=null) OptDesignStmt1.setParent(this);
        this.Statement=Statement;
        if(Statement!=null) Statement.setParent(this);
    }

    public OptDesignStmt getOptDesignStmt() {
        return OptDesignStmt;
    }

    public void setOptDesignStmt(OptDesignStmt OptDesignStmt) {
        this.OptDesignStmt=OptDesignStmt;
    }

    public OptCond getOptCond() {
        return OptCond;
    }

    public void setOptCond(OptCond OptCond) {
        this.OptCond=OptCond;
    }

    public OptDesignStmt getOptDesignStmt1() {
        return OptDesignStmt1;
    }

    public void setOptDesignStmt1(OptDesignStmt OptDesignStmt1) {
        this.OptDesignStmt1=OptDesignStmt1;
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
        if(OptDesignStmt!=null) OptDesignStmt.accept(visitor);
        if(OptCond!=null) OptCond.accept(visitor);
        if(OptDesignStmt1!=null) OptDesignStmt1.accept(visitor);
        if(Statement!=null) Statement.accept(visitor);
    }

    public void traverseTopDown(Visitor visitor) {
        accept(visitor);
        if(OptDesignStmt!=null) OptDesignStmt.traverseTopDown(visitor);
        if(OptCond!=null) OptCond.traverseTopDown(visitor);
        if(OptDesignStmt1!=null) OptDesignStmt1.traverseTopDown(visitor);
        if(Statement!=null) Statement.traverseTopDown(visitor);
    }

    public void traverseBottomUp(Visitor visitor) {
        if(OptDesignStmt!=null) OptDesignStmt.traverseBottomUp(visitor);
        if(OptCond!=null) OptCond.traverseBottomUp(visitor);
        if(OptDesignStmt1!=null) OptDesignStmt1.traverseBottomUp(visitor);
        if(Statement!=null) Statement.traverseBottomUp(visitor);
        accept(visitor);
    }

    public String toString(String tab) {
        StringBuffer buffer=new StringBuffer();
        buffer.append(tab);
        buffer.append("ForStmt(\n");

        if(OptDesignStmt!=null)
            buffer.append(OptDesignStmt.toString("  "+tab));
        else
            buffer.append(tab+"  null");
        buffer.append("\n");

        if(OptCond!=null)
            buffer.append(OptCond.toString("  "+tab));
        else
            buffer.append(tab+"  null");
        buffer.append("\n");

        if(OptDesignStmt1!=null)
            buffer.append(OptDesignStmt1.toString("  "+tab));
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
