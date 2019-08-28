// generated with ast extension for cup
// version 0.8
// 28/7/2019 3:33:21


package rs.ac.bg.etf.pp1.ast;

public class NewArrFactor extends Factor {

    private Type Type;
    private Expr Expr;
    private OptInit OptInit;

    public NewArrFactor (Type Type, Expr Expr, OptInit OptInit) {
        this.Type=Type;
        if(Type!=null) Type.setParent(this);
        this.Expr=Expr;
        if(Expr!=null) Expr.setParent(this);
        this.OptInit=OptInit;
        if(OptInit!=null) OptInit.setParent(this);
    }

    public Type getType() {
        return Type;
    }

    public void setType(Type Type) {
        this.Type=Type;
    }

    public Expr getExpr() {
        return Expr;
    }

    public void setExpr(Expr Expr) {
        this.Expr=Expr;
    }

    public OptInit getOptInit() {
        return OptInit;
    }

    public void setOptInit(OptInit OptInit) {
        this.OptInit=OptInit;
    }

    public void accept(Visitor visitor) {
        visitor.visit(this);
    }

    public void childrenAccept(Visitor visitor) {
        if(Type!=null) Type.accept(visitor);
        if(Expr!=null) Expr.accept(visitor);
        if(OptInit!=null) OptInit.accept(visitor);
    }

    public void traverseTopDown(Visitor visitor) {
        accept(visitor);
        if(Type!=null) Type.traverseTopDown(visitor);
        if(Expr!=null) Expr.traverseTopDown(visitor);
        if(OptInit!=null) OptInit.traverseTopDown(visitor);
    }

    public void traverseBottomUp(Visitor visitor) {
        if(Type!=null) Type.traverseBottomUp(visitor);
        if(Expr!=null) Expr.traverseBottomUp(visitor);
        if(OptInit!=null) OptInit.traverseBottomUp(visitor);
        accept(visitor);
    }

    public String toString(String tab) {
        StringBuffer buffer=new StringBuffer();
        buffer.append(tab);
        buffer.append("NewArrFactor(\n");

        if(Type!=null)
            buffer.append(Type.toString("  "+tab));
        else
            buffer.append(tab+"  null");
        buffer.append("\n");

        if(Expr!=null)
            buffer.append(Expr.toString("  "+tab));
        else
            buffer.append(tab+"  null");
        buffer.append("\n");

        if(OptInit!=null)
            buffer.append(OptInit.toString("  "+tab));
        else
            buffer.append(tab+"  null");
        buffer.append("\n");

        buffer.append(tab);
        buffer.append(") [NewArrFactor]");
        return buffer.toString();
    }
}
