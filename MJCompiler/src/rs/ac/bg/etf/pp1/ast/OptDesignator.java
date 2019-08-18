// generated with ast extension for cup
// version 0.8
// 18/7/2019 22:9:23


package rs.ac.bg.etf.pp1.ast;

public class OptDesignator extends OptDesign {

    private OptDesign OptDesign;
    private DesignItem DesignItem;

    public OptDesignator (OptDesign OptDesign, DesignItem DesignItem) {
        this.OptDesign=OptDesign;
        if(OptDesign!=null) OptDesign.setParent(this);
        this.DesignItem=DesignItem;
        if(DesignItem!=null) DesignItem.setParent(this);
    }

    public OptDesign getOptDesign() {
        return OptDesign;
    }

    public void setOptDesign(OptDesign OptDesign) {
        this.OptDesign=OptDesign;
    }

    public DesignItem getDesignItem() {
        return DesignItem;
    }

    public void setDesignItem(DesignItem DesignItem) {
        this.DesignItem=DesignItem;
    }

    public void accept(Visitor visitor) {
        visitor.visit(this);
    }

    public void childrenAccept(Visitor visitor) {
        if(OptDesign!=null) OptDesign.accept(visitor);
        if(DesignItem!=null) DesignItem.accept(visitor);
    }

    public void traverseTopDown(Visitor visitor) {
        accept(visitor);
        if(OptDesign!=null) OptDesign.traverseTopDown(visitor);
        if(DesignItem!=null) DesignItem.traverseTopDown(visitor);
    }

    public void traverseBottomUp(Visitor visitor) {
        if(OptDesign!=null) OptDesign.traverseBottomUp(visitor);
        if(DesignItem!=null) DesignItem.traverseBottomUp(visitor);
        accept(visitor);
    }

    public String toString(String tab) {
        StringBuffer buffer=new StringBuffer();
        buffer.append(tab);
        buffer.append("OptDesignator(\n");

        if(OptDesign!=null)
            buffer.append(OptDesign.toString("  "+tab));
        else
            buffer.append(tab+"  null");
        buffer.append("\n");

        if(DesignItem!=null)
            buffer.append(DesignItem.toString("  "+tab));
        else
            buffer.append(tab+"  null");
        buffer.append("\n");

        buffer.append(tab);
        buffer.append(") [OptDesignator]");
        return buffer.toString();
    }
}
