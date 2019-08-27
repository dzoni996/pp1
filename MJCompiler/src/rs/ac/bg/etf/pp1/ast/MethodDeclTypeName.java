// generated with ast extension for cup
// version 0.8
// 27/7/2019 20:33:58


package rs.ac.bg.etf.pp1.ast;

public class MethodDeclTypeName extends MethDeclTypeName {

    private TypeIdent TypeIdent;
    private String methodName;

    public MethodDeclTypeName (TypeIdent TypeIdent, String methodName) {
        this.TypeIdent=TypeIdent;
        if(TypeIdent!=null) TypeIdent.setParent(this);
        this.methodName=methodName;
    }

    public TypeIdent getTypeIdent() {
        return TypeIdent;
    }

    public void setTypeIdent(TypeIdent TypeIdent) {
        this.TypeIdent=TypeIdent;
    }

    public String getMethodName() {
        return methodName;
    }

    public void setMethodName(String methodName) {
        this.methodName=methodName;
    }

    public void accept(Visitor visitor) {
        visitor.visit(this);
    }

    public void childrenAccept(Visitor visitor) {
        if(TypeIdent!=null) TypeIdent.accept(visitor);
    }

    public void traverseTopDown(Visitor visitor) {
        accept(visitor);
        if(TypeIdent!=null) TypeIdent.traverseTopDown(visitor);
    }

    public void traverseBottomUp(Visitor visitor) {
        if(TypeIdent!=null) TypeIdent.traverseBottomUp(visitor);
        accept(visitor);
    }

    public String toString(String tab) {
        StringBuffer buffer=new StringBuffer();
        buffer.append(tab);
        buffer.append("MethodDeclTypeName(\n");

        if(TypeIdent!=null)
            buffer.append(TypeIdent.toString("  "+tab));
        else
            buffer.append(tab+"  null");
        buffer.append("\n");

        buffer.append(" "+tab+methodName);
        buffer.append("\n");

        buffer.append(tab);
        buffer.append(") [MethodDeclTypeName]");
        return buffer.toString();
    }
}
