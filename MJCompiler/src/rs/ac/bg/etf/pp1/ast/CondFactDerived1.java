// generated with ast extension for cup
// version 0.8
// 17/7/2019 20:52:6


package rs.ac.bg.etf.pp1.ast;

public class CondFactDerived1 extends CondFact {

    private Expr Expr;
    private OptRelExpr OptRelExpr;

    public CondFactDerived1 (Expr Expr, OptRelExpr OptRelExpr) {
        this.Expr=Expr;
        if(Expr!=null) Expr.setParent(this);
        this.OptRelExpr=OptRelExpr;
        if(OptRelExpr!=null) OptRelExpr.setParent(this);
    }

    public Expr getExpr() {
        return Expr;
    }

    public void setExpr(Expr Expr) {
        this.Expr=Expr;
    }

    public OptRelExpr getOptRelExpr() {
        return OptRelExpr;
    }

    public void setOptRelExpr(OptRelExpr OptRelExpr) {
        this.OptRelExpr=OptRelExpr;
    }

    public void accept(Visitor visitor) {
        visitor.visit(this);
    }

    public void childrenAccept(Visitor visitor) {
        if(Expr!=null) Expr.accept(visitor);
        if(OptRelExpr!=null) OptRelExpr.accept(visitor);
    }

    public void traverseTopDown(Visitor visitor) {
        accept(visitor);
        if(Expr!=null) Expr.traverseTopDown(visitor);
        if(OptRelExpr!=null) OptRelExpr.traverseTopDown(visitor);
    }

    public void traverseBottomUp(Visitor visitor) {
        if(Expr!=null) Expr.traverseBottomUp(visitor);
        if(OptRelExpr!=null) OptRelExpr.traverseBottomUp(visitor);
        accept(visitor);
    }

    public String toString(String tab) {
        StringBuffer buffer=new StringBuffer();
        buffer.append(tab);
        buffer.append("CondFactDerived1(\n");

        if(Expr!=null)
            buffer.append(Expr.toString("  "+tab));
        else
            buffer.append(tab+"  null");
        buffer.append("\n");

        if(OptRelExpr!=null)
            buffer.append(OptRelExpr.toString("  "+tab));
        else
            buffer.append(tab+"  null");
        buffer.append("\n");

        buffer.append(tab);
        buffer.append(") [CondFactDerived1]");
        return buffer.toString();
    }
}
