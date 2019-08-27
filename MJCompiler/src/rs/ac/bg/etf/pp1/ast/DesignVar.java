// generated with ast extension for cup
// version 0.8
// 27/7/2019 20:33:59


package rs.ac.bg.etf.pp1.ast;

public class DesignVar extends Designator {

    private DesigName DesigName;

    public DesignVar (DesigName DesigName) {
        this.DesigName=DesigName;
        if(DesigName!=null) DesigName.setParent(this);
    }

    public DesigName getDesigName() {
        return DesigName;
    }

    public void setDesigName(DesigName DesigName) {
        this.DesigName=DesigName;
    }

    public void accept(Visitor visitor) {
        visitor.visit(this);
    }

    public void childrenAccept(Visitor visitor) {
        if(DesigName!=null) DesigName.accept(visitor);
    }

    public void traverseTopDown(Visitor visitor) {
        accept(visitor);
        if(DesigName!=null) DesigName.traverseTopDown(visitor);
    }

    public void traverseBottomUp(Visitor visitor) {
        if(DesigName!=null) DesigName.traverseBottomUp(visitor);
        accept(visitor);
    }

    public String toString(String tab) {
        StringBuffer buffer=new StringBuffer();
        buffer.append(tab);
        buffer.append("DesignVar(\n");

        if(DesigName!=null)
            buffer.append(DesigName.toString("  "+tab));
        else
            buffer.append(tab+"  null");
        buffer.append("\n");

        buffer.append(tab);
        buffer.append(") [DesignVar]");
        return buffer.toString();
    }
}
