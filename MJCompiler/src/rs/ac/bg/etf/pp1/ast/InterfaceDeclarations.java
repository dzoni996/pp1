// generated with ast extension for cup
// version 0.8
// 22/7/2019 17:31:49


package rs.ac.bg.etf.pp1.ast;

public class InterfaceDeclarations extends InterfaceDecl {

    private InterfaceName InterfaceName;
    private OptInterfaceMethodDeclList OptInterfaceMethodDeclList;

    public InterfaceDeclarations (InterfaceName InterfaceName, OptInterfaceMethodDeclList OptInterfaceMethodDeclList) {
        this.InterfaceName=InterfaceName;
        if(InterfaceName!=null) InterfaceName.setParent(this);
        this.OptInterfaceMethodDeclList=OptInterfaceMethodDeclList;
        if(OptInterfaceMethodDeclList!=null) OptInterfaceMethodDeclList.setParent(this);
    }

    public InterfaceName getInterfaceName() {
        return InterfaceName;
    }

    public void setInterfaceName(InterfaceName InterfaceName) {
        this.InterfaceName=InterfaceName;
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
        if(InterfaceName!=null) InterfaceName.accept(visitor);
        if(OptInterfaceMethodDeclList!=null) OptInterfaceMethodDeclList.accept(visitor);
    }

    public void traverseTopDown(Visitor visitor) {
        accept(visitor);
        if(InterfaceName!=null) InterfaceName.traverseTopDown(visitor);
        if(OptInterfaceMethodDeclList!=null) OptInterfaceMethodDeclList.traverseTopDown(visitor);
    }

    public void traverseBottomUp(Visitor visitor) {
        if(InterfaceName!=null) InterfaceName.traverseBottomUp(visitor);
        if(OptInterfaceMethodDeclList!=null) OptInterfaceMethodDeclList.traverseBottomUp(visitor);
        accept(visitor);
    }

    public String toString(String tab) {
        StringBuffer buffer=new StringBuffer();
        buffer.append(tab);
        buffer.append("InterfaceDeclarations(\n");

        if(InterfaceName!=null)
            buffer.append(InterfaceName.toString("  "+tab));
        else
            buffer.append(tab+"  null");
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
