// generated with ast extension for cup
// version 0.8
// 18/7/2019 22:44:16


package rs.ac.bg.etf.pp1.ast;

public class Design extends Designator {

    private String I1;
    private OptDesign OptDesign;

    public Design (String I1, OptDesign OptDesign) {
        this.I1=I1;
        this.OptDesign=OptDesign;
        if(OptDesign!=null) OptDesign.setParent(this);
    }

    public String getI1() {
        return I1;
    }

    public void setI1(String I1) {
        this.I1=I1;
    }

    public OptDesign getOptDesign() {
        return OptDesign;
    }

    public void setOptDesign(OptDesign OptDesign) {
        this.OptDesign=OptDesign;
    }

    public void accept(Visitor visitor) {
        visitor.visit(this);
    }

    public void childrenAccept(Visitor visitor) {
        if(OptDesign!=null) OptDesign.accept(visitor);
    }

    public void traverseTopDown(Visitor visitor) {
        accept(visitor);
        if(OptDesign!=null) OptDesign.traverseTopDown(visitor);
    }

    public void traverseBottomUp(Visitor visitor) {
        if(OptDesign!=null) OptDesign.traverseBottomUp(visitor);
        accept(visitor);
    }

    public String toString(String tab) {
        StringBuffer buffer=new StringBuffer();
        buffer.append(tab);
        buffer.append("Design(\n");

        buffer.append(" "+tab+I1);
        buffer.append("\n");

        if(OptDesign!=null)
            buffer.append(OptDesign.toString("  "+tab));
        else
            buffer.append(tab+"  null");
        buffer.append("\n");

        buffer.append(tab);
        buffer.append(") [Design]");
        return buffer.toString();
    }
}
