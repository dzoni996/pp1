// generated with ast extension for cup
// version 0.8
// 20/7/2019 23:23:20


package rs.ac.bg.etf.pp1.ast;

public class InterfaceMethodDeclaration extends InterfaceMethodDecl {

    private TypeIdent TypeIdent;
    private String name;
    private OptFormPars OptFormPars;

    public InterfaceMethodDeclaration (TypeIdent TypeIdent, String name, OptFormPars OptFormPars) {
        this.TypeIdent=TypeIdent;
        if(TypeIdent!=null) TypeIdent.setParent(this);
        this.name=name;
        this.OptFormPars=OptFormPars;
        if(OptFormPars!=null) OptFormPars.setParent(this);
    }

    public TypeIdent getTypeIdent() {
        return TypeIdent;
    }

    public void setTypeIdent(TypeIdent TypeIdent) {
        this.TypeIdent=TypeIdent;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name=name;
    }

    public OptFormPars getOptFormPars() {
        return OptFormPars;
    }

    public void setOptFormPars(OptFormPars OptFormPars) {
        this.OptFormPars=OptFormPars;
    }

    public void accept(Visitor visitor) {
        visitor.visit(this);
    }

    public void childrenAccept(Visitor visitor) {
        if(TypeIdent!=null) TypeIdent.accept(visitor);
        if(OptFormPars!=null) OptFormPars.accept(visitor);
    }

    public void traverseTopDown(Visitor visitor) {
        accept(visitor);
        if(TypeIdent!=null) TypeIdent.traverseTopDown(visitor);
        if(OptFormPars!=null) OptFormPars.traverseTopDown(visitor);
    }

    public void traverseBottomUp(Visitor visitor) {
        if(TypeIdent!=null) TypeIdent.traverseBottomUp(visitor);
        if(OptFormPars!=null) OptFormPars.traverseBottomUp(visitor);
        accept(visitor);
    }

    public String toString(String tab) {
        StringBuffer buffer=new StringBuffer();
        buffer.append(tab);
        buffer.append("InterfaceMethodDeclaration(\n");

        if(TypeIdent!=null)
            buffer.append(TypeIdent.toString("  "+tab));
        else
            buffer.append(tab+"  null");
        buffer.append("\n");

        buffer.append(" "+tab+name);
        buffer.append("\n");

        if(OptFormPars!=null)
            buffer.append(OptFormPars.toString("  "+tab));
        else
            buffer.append(tab+"  null");
        buffer.append("\n");

        buffer.append(tab);
        buffer.append(") [InterfaceMethodDeclaration]");
        return buffer.toString();
    }
}
