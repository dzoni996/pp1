// generated with ast extension for cup
// version 0.8
// 18/7/2019 22:9:22


package rs.ac.bg.etf.pp1.ast;

public class EnumDeclarations extends EnumDecl {

    private String name;
    private EnumDeclList EnumDeclList;

    public EnumDeclarations (String name, EnumDeclList EnumDeclList) {
        this.name=name;
        this.EnumDeclList=EnumDeclList;
        if(EnumDeclList!=null) EnumDeclList.setParent(this);
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name=name;
    }

    public EnumDeclList getEnumDeclList() {
        return EnumDeclList;
    }

    public void setEnumDeclList(EnumDeclList EnumDeclList) {
        this.EnumDeclList=EnumDeclList;
    }

    public void accept(Visitor visitor) {
        visitor.visit(this);
    }

    public void childrenAccept(Visitor visitor) {
        if(EnumDeclList!=null) EnumDeclList.accept(visitor);
    }

    public void traverseTopDown(Visitor visitor) {
        accept(visitor);
        if(EnumDeclList!=null) EnumDeclList.traverseTopDown(visitor);
    }

    public void traverseBottomUp(Visitor visitor) {
        if(EnumDeclList!=null) EnumDeclList.traverseBottomUp(visitor);
        accept(visitor);
    }

    public String toString(String tab) {
        StringBuffer buffer=new StringBuffer();
        buffer.append(tab);
        buffer.append("EnumDeclarations(\n");

        buffer.append(" "+tab+name);
        buffer.append("\n");

        if(EnumDeclList!=null)
            buffer.append(EnumDeclList.toString("  "+tab));
        else
            buffer.append(tab+"  null");
        buffer.append("\n");

        buffer.append(tab);
        buffer.append(") [EnumDeclarations]");
        return buffer.toString();
    }
}
