// generated with ast extension for cup
// version 0.8
// 28/7/2019 10:50:46


package rs.ac.bg.etf.pp1.ast;

public class ForStmt extends Statement {

    private OptForDesignStmt OptForDesignStmt;
    private OptCond OptCond;
    private CondEnd CondEnd;
    private OptForDesignStmt2 OptForDesignStmt2;
    private ForStatement ForStatement;

    public ForStmt (OptForDesignStmt OptForDesignStmt, OptCond OptCond, CondEnd CondEnd, OptForDesignStmt2 OptForDesignStmt2, ForStatement ForStatement) {
        this.OptForDesignStmt=OptForDesignStmt;
        if(OptForDesignStmt!=null) OptForDesignStmt.setParent(this);
        this.OptCond=OptCond;
        if(OptCond!=null) OptCond.setParent(this);
        this.CondEnd=CondEnd;
        if(CondEnd!=null) CondEnd.setParent(this);
        this.OptForDesignStmt2=OptForDesignStmt2;
        if(OptForDesignStmt2!=null) OptForDesignStmt2.setParent(this);
        this.ForStatement=ForStatement;
        if(ForStatement!=null) ForStatement.setParent(this);
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

    public CondEnd getCondEnd() {
        return CondEnd;
    }

    public void setCondEnd(CondEnd CondEnd) {
        this.CondEnd=CondEnd;
    }

    public OptForDesignStmt2 getOptForDesignStmt2() {
        return OptForDesignStmt2;
    }

    public void setOptForDesignStmt2(OptForDesignStmt2 OptForDesignStmt2) {
        this.OptForDesignStmt2=OptForDesignStmt2;
    }

    public ForStatement getForStatement() {
        return ForStatement;
    }

    public void setForStatement(ForStatement ForStatement) {
        this.ForStatement=ForStatement;
    }

    public void accept(Visitor visitor) {
        visitor.visit(this);
    }

    public void childrenAccept(Visitor visitor) {
        if(OptForDesignStmt!=null) OptForDesignStmt.accept(visitor);
        if(OptCond!=null) OptCond.accept(visitor);
        if(CondEnd!=null) CondEnd.accept(visitor);
        if(OptForDesignStmt2!=null) OptForDesignStmt2.accept(visitor);
        if(ForStatement!=null) ForStatement.accept(visitor);
    }

    public void traverseTopDown(Visitor visitor) {
        accept(visitor);
        if(OptForDesignStmt!=null) OptForDesignStmt.traverseTopDown(visitor);
        if(OptCond!=null) OptCond.traverseTopDown(visitor);
        if(CondEnd!=null) CondEnd.traverseTopDown(visitor);
        if(OptForDesignStmt2!=null) OptForDesignStmt2.traverseTopDown(visitor);
        if(ForStatement!=null) ForStatement.traverseTopDown(visitor);
    }

    public void traverseBottomUp(Visitor visitor) {
        if(OptForDesignStmt!=null) OptForDesignStmt.traverseBottomUp(visitor);
        if(OptCond!=null) OptCond.traverseBottomUp(visitor);
        if(CondEnd!=null) CondEnd.traverseBottomUp(visitor);
        if(OptForDesignStmt2!=null) OptForDesignStmt2.traverseBottomUp(visitor);
        if(ForStatement!=null) ForStatement.traverseBottomUp(visitor);
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

        if(CondEnd!=null)
            buffer.append(CondEnd.toString("  "+tab));
        else
            buffer.append(tab+"  null");
        buffer.append("\n");

        if(OptForDesignStmt2!=null)
            buffer.append(OptForDesignStmt2.toString("  "+tab));
        else
            buffer.append(tab+"  null");
        buffer.append("\n");

        if(ForStatement!=null)
            buffer.append(ForStatement.toString("  "+tab));
        else
            buffer.append(tab+"  null");
        buffer.append("\n");

        buffer.append(tab);
        buffer.append(") [ForStmt]");
        return buffer.toString();
    }
}
