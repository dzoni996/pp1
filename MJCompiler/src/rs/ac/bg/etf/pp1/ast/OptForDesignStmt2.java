// generated with ast extension for cup
// version 0.8
// 28/7/2019 3:33:20


package rs.ac.bg.etf.pp1.ast;

public class OptForDesignStmt2 implements SyntaxNode {

    private SyntaxNode parent;
    private int line;
    private OptDesignStmt OptDesignStmt;

    public OptForDesignStmt2 (OptDesignStmt OptDesignStmt) {
        this.OptDesignStmt=OptDesignStmt;
        if(OptDesignStmt!=null) OptDesignStmt.setParent(this);
    }

    public OptDesignStmt getOptDesignStmt() {
        return OptDesignStmt;
    }

    public void setOptDesignStmt(OptDesignStmt OptDesignStmt) {
        this.OptDesignStmt=OptDesignStmt;
    }

    public SyntaxNode getParent() {
        return parent;
    }

    public void setParent(SyntaxNode parent) {
        this.parent=parent;
    }

    public int getLine() {
        return line;
    }

    public void setLine(int line) {
        this.line=line;
    }

    public void accept(Visitor visitor) {
        visitor.visit(this);
    }

    public void childrenAccept(Visitor visitor) {
        if(OptDesignStmt!=null) OptDesignStmt.accept(visitor);
    }

    public void traverseTopDown(Visitor visitor) {
        accept(visitor);
        if(OptDesignStmt!=null) OptDesignStmt.traverseTopDown(visitor);
    }

    public void traverseBottomUp(Visitor visitor) {
        if(OptDesignStmt!=null) OptDesignStmt.traverseBottomUp(visitor);
        accept(visitor);
    }

    public String toString(String tab) {
        StringBuffer buffer=new StringBuffer();
        buffer.append(tab);
        buffer.append("OptForDesignStmt2(\n");

        if(OptDesignStmt!=null)
            buffer.append(OptDesignStmt.toString("  "+tab));
        else
            buffer.append(tab+"  null");
        buffer.append("\n");

        buffer.append(tab);
        buffer.append(") [OptForDesignStmt2]");
        return buffer.toString();
    }
}
