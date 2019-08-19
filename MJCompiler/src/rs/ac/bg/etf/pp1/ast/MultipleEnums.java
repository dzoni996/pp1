// generated with ast extension for cup
// version 0.8
// 19/7/2019 3:15:52


package rs.ac.bg.etf.pp1.ast;

public class MultipleEnums extends EnumDeclList {

    private EnumDeclList EnumDeclList;
    private EnumItem EnumItem;

    public MultipleEnums (EnumDeclList EnumDeclList, EnumItem EnumItem) {
        this.EnumDeclList=EnumDeclList;
        if(EnumDeclList!=null) EnumDeclList.setParent(this);
        this.EnumItem=EnumItem;
        if(EnumItem!=null) EnumItem.setParent(this);
    }

    public EnumDeclList getEnumDeclList() {
        return EnumDeclList;
    }

    public void setEnumDeclList(EnumDeclList EnumDeclList) {
        this.EnumDeclList=EnumDeclList;
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
        if(EnumDeclList!=null) EnumDeclList.accept(visitor);
        if(EnumItem!=null) EnumItem.accept(visitor);
    }

    public void traverseTopDown(Visitor visitor) {
        accept(visitor);
        if(EnumDeclList!=null) EnumDeclList.traverseTopDown(visitor);
        if(EnumItem!=null) EnumItem.traverseTopDown(visitor);
    }

    public void traverseBottomUp(Visitor visitor) {
        if(EnumDeclList!=null) EnumDeclList.traverseBottomUp(visitor);
        if(EnumItem!=null) EnumItem.traverseBottomUp(visitor);
        accept(visitor);
    }

    public String toString(String tab) {
        StringBuffer buffer=new StringBuffer();
        buffer.append(tab);
        buffer.append("MultipleEnums(\n");

        if(EnumDeclList!=null)
            buffer.append(EnumDeclList.toString("  "+tab));
        else
            buffer.append(tab+"  null");
        buffer.append("\n");

        if(EnumItem!=null)
            buffer.append(EnumItem.toString("  "+tab));
        else
            buffer.append(tab+"  null");
        buffer.append("\n");

        buffer.append(tab);
        buffer.append(") [MultipleEnums]");
        return buffer.toString();
    }
}
