// generated with ast extension for cup
// version 0.8
// 28/7/2019 3:33:21


package rs.ac.bg.etf.pp1.ast;

public class InitListDerived1 extends InitList {

    private InitList InitList;
    private InitExpr InitExpr;

    public InitListDerived1 (InitList InitList, InitExpr InitExpr) {
        this.InitList=InitList;
        if(InitList!=null) InitList.setParent(this);
        this.InitExpr=InitExpr;
        if(InitExpr!=null) InitExpr.setParent(this);
    }

    public InitList getInitList() {
        return InitList;
    }

    public void setInitList(InitList InitList) {
        this.InitList=InitList;
    }

    public InitExpr getInitExpr() {
        return InitExpr;
    }

    public void setInitExpr(InitExpr InitExpr) {
        this.InitExpr=InitExpr;
    }

    public void accept(Visitor visitor) {
        visitor.visit(this);
    }

    public void childrenAccept(Visitor visitor) {
        if(InitList!=null) InitList.accept(visitor);
        if(InitExpr!=null) InitExpr.accept(visitor);
    }

    public void traverseTopDown(Visitor visitor) {
        accept(visitor);
        if(InitList!=null) InitList.traverseTopDown(visitor);
        if(InitExpr!=null) InitExpr.traverseTopDown(visitor);
    }

    public void traverseBottomUp(Visitor visitor) {
        if(InitList!=null) InitList.traverseBottomUp(visitor);
        if(InitExpr!=null) InitExpr.traverseBottomUp(visitor);
        accept(visitor);
    }

    public String toString(String tab) {
        StringBuffer buffer=new StringBuffer();
        buffer.append(tab);
        buffer.append("InitListDerived1(\n");

        if(InitList!=null)
            buffer.append(InitList.toString("  "+tab));
        else
            buffer.append(tab+"  null");
        buffer.append("\n");

        if(InitExpr!=null)
            buffer.append(InitExpr.toString("  "+tab));
        else
            buffer.append(tab+"  null");
        buffer.append("\n");

        buffer.append(tab);
        buffer.append(") [InitListDerived1]");
        return buffer.toString();
    }
}
