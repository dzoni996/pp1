// generated with ast extension for cup
// version 0.8
// 22/7/2019 17:31:49


package rs.ac.bg.etf.pp1.ast;

public class ReturnStmt extends Statement {

    private OptRetExpr OptRetExpr;

    public ReturnStmt (OptRetExpr OptRetExpr) {
        this.OptRetExpr=OptRetExpr;
        if(OptRetExpr!=null) OptRetExpr.setParent(this);
    }

    public OptRetExpr getOptRetExpr() {
        return OptRetExpr;
    }

    public void setOptRetExpr(OptRetExpr OptRetExpr) {
        this.OptRetExpr=OptRetExpr;
    }

    public void accept(Visitor visitor) {
        visitor.visit(this);
    }

    public void childrenAccept(Visitor visitor) {
        if(OptRetExpr!=null) OptRetExpr.accept(visitor);
    }

    public void traverseTopDown(Visitor visitor) {
        accept(visitor);
        if(OptRetExpr!=null) OptRetExpr.traverseTopDown(visitor);
    }

    public void traverseBottomUp(Visitor visitor) {
        if(OptRetExpr!=null) OptRetExpr.traverseBottomUp(visitor);
        accept(visitor);
    }

    public String toString(String tab) {
        StringBuffer buffer=new StringBuffer();
        buffer.append(tab);
        buffer.append("ReturnStmt(\n");

        if(OptRetExpr!=null)
            buffer.append(OptRetExpr.toString("  "+tab));
        else
            buffer.append(tab+"  null");
        buffer.append("\n");

        buffer.append(tab);
        buffer.append(") [ReturnStmt]");
        return buffer.toString();
    }
}
