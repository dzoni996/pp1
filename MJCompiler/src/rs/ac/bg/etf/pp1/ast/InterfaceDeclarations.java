// generated with ast extension for cup
// version 0.8
// 18/7/2019 22:44:16


package rs.ac.bg.etf.pp1.ast;

public class InterfaceDeclarations extends InterfaceDecl {

    private String I1;
    private OptInterfaceMethodDeclList OptInterfaceMethodDeclList;

    public InterfaceDeclarations (String I1, OptInterfaceMethodDeclList OptInterfaceMethodDeclList) {
        this.I1=I1;
        this.OptInterfaceMethodDeclList=OptInterfaceMethodDeclList;
        if(OptInterfaceMethodDeclList!=null) OptInterfaceMethodDeclList.setParent(this);
    }

    public String getI1() {
        return I1;
    }

    public void setI1(String I1) {
        this.I1=I1;
    }

    public OptInterfaceMethodDeclList getOptInterfaceMethodDeclList() {
        return OptInterfaceMethodDeclList;
    }

    public void setOptInterfaceMethodDeclList(OptInterfaceMethodDeclList OptInterfaceMethodDeclList) {
        this.OptInterfaceMethodDeclList=OptInterfaceMethodDeclList;
    }

    public void accept(Visitor visitor) {
        visitor.visit(this);
    }

    public void childrenAccept(Visitor visitor) {
        if(OptInterfaceMethodDeclList!=null) OptInterfaceMethodDeclList.accept(visitor);
    }

    public void traverseTopDown(Visitor visitor) {
        accept(visitor);
        if(OptInterfaceMethodDeclList!=null) OptInterfaceMethodDeclList.traverseTopDown(visitor);
    }

    public void traverseBottomUp(Visitor visitor) {
        if(OptInterfaceMethodDeclList!=null) OptInterfaceMethodDeclList.traverseBottomUp(visitor);
        accept(visitor);
    }

    public String toString(String tab) {
        StringBuffer buffer=new StringBuffer();
        buffer.append(tab);
        buffer.append("InterfaceDeclarations(\n");

        buffer.append(" "+tab+I1);
        buffer.append("\n");

        if(OptInterfaceMethodDeclList!=null)
            buffer.append(OptInterfaceMethodDeclList.toString("  "+tab));
        else
            buffer.append(tab+"  null");
        buffer.append("\n");

        buffer.append(tab);
        buffer.append(") [InterfaceDeclarations]");
        return buffer.toString();
    }
}
