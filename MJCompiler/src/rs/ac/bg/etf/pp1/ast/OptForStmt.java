// generated with ast extension for cup
// version 0.8
// 27/7/2019 23:17:7


package rs.ac.bg.etf.pp1.ast;

public class OptForStmt extends OptForDesignStmt {

    private OptDesignStmt OptDesignStmt;

    public OptForStmt (OptDesignStmt OptDesignStmt) {
        this.OptDesignStmt=OptDesignStmt;
        if(OptDesignStmt!=null) OptDesignStmt.setParent(this);
    }

    public OptDesignStmt getOptDesignStmt() {
        return OptDesignStmt;
    }

    public void setOptDesignStmt(OptDesignStmt OptDesignStmt) {
        this.OptDesignStmt=OptDesignStmt;
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
        buffer.append("OptForStmt(\n");

        if(OptDesignStmt!=null)
            buffer.append(OptDesignStmt.toString("  "+tab));
        else
            buffer.append(tab+"  null");
        buffer.append("\n");

        buffer.append(tab);
        buffer.append(") [OptForStmt]");
        return buffer.toString();
    }
}
