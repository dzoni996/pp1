// generated with ast extension for cup
// version 0.8
// 17/7/2019 13:16:32


package rs.ac.bg.etf.pp1.ast;

public class SingleEnum extends EnumDeclList {

    private EnumItem EnumItem;

    public SingleEnum (EnumItem EnumItem) {
        this.EnumItem=EnumItem;
        if(EnumItem!=null) EnumItem.setParent(this);
    }

    public EnumItem getEnumItem() {
        return EnumItem;
    }

    public void setEnumItem(EnumItem EnumItem) {
        this.EnumItem=EnumItem;
    }

    public void accept(Visitor visitor) {
        visitor.visit(this);
    }

    public void childrenAccept(Visitor visitor) {
        if(EnumItem!=null) EnumItem.accept(visitor);
    }

    public void traverseTopDown(Visitor visitor) {
        accept(visitor);
        if(EnumItem!=null) EnumItem.traverseTopDown(visitor);
    }

    public void traverseBottomUp(Visitor visitor) {
        if(EnumItem!=null) EnumItem.traverseBottomUp(visitor);
        accept(visitor);
    }

    public String toString(String tab) {
        StringBuffer buffer=new StringBuffer();
        buffer.append(tab);
        buffer.append("SingleEnum(\n");

        if(EnumItem!=null)
            buffer.append(EnumItem.toString("  "+tab));
        else
            buffer.append(tab+"  null");
        buffer.append("\n");

        buffer.append(tab);
        buffer.append(") [SingleEnum]");
        return buffer.toString();
    }
}
