// generated with ast extension for cup
// version 0.8
// 22/7/2019 17:31:50


package rs.ac.bg.etf.pp1.ast;

public class BlockStmt extends Statement {

    private OptStmt OptStmt;

    public BlockStmt (OptStmt OptStmt) {
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
        buffer.append("BlockStmt(\n");

        if(OptStmt!=null)
            buffer.append(OptStmt.toString("  "+tab));
        else
            buffer.append(tab+"  null");
        buffer.append("\n");

        buffer.append(tab);
        buffer.append(") [BlockStmt]");
        return buffer.toString();
    }
}
