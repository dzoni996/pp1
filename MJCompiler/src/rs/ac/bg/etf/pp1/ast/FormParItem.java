// generated with ast extension for cup
// version 0.8
// 26/7/2019 20:47:6


package rs.ac.bg.etf.pp1.ast;

public class FormParItem extends FormParsItem {

    private Type Type;
    private String paramName;
    private OptArraySq OptArraySq;

    public FormParItem (Type Type, String paramName, OptArraySq OptArraySq) {
        this.Type=Type;
        if(Type!=null) Type.setParent(this);
        this.paramName=paramName;
        this.OptArraySq=OptArraySq;
        if(OptArraySq!=null) OptArraySq.setParent(this);
    }

    public Type getType() {
        return Type;
    }

    public void setType(Type Type) {
        this.Type=Type;
    }

    public String getParamName() {
        return paramName;
    }

    public void setParamName(String paramName) {
        this.paramName=paramName;
    }

    public OptArraySq getOptArraySq() {
        return OptArraySq;
    }

    public void setOptArraySq(OptArraySq OptArraySq) {
        this.OptArraySq=OptArraySq;
    }

    public void accept(Visitor visitor) {
        visitor.visit(this);
    }

    public void childrenAccept(Visitor visitor) {
        if(Type!=null) Type.accept(visitor);
        if(OptArraySq!=null) OptArraySq.accept(visitor);
    }

    public void traverseTopDown(Visitor visitor) {
        accept(visitor);
        if(Type!=null) Type.traverseTopDown(visitor);
        if(OptArraySq!=null) OptArraySq.traverseTopDown(visitor);
    }

    public void traverseBottomUp(Visitor visitor) {
        if(Type!=null) Type.traverseBottomUp(visitor);
        if(OptArraySq!=null) OptArraySq.traverseBottomUp(visitor);
        accept(visitor);
    }

    public String toString(String tab) {
        StringBuffer buffer=new StringBuffer();
        buffer.append(tab);
        buffer.append("FormParItem(\n");

        if(Type!=null)
            buffer.append(Type.toString("  "+tab));
        else
            buffer.append(tab+"  null");
        buffer.append("\n");

        buffer.append(" "+tab+paramName);
        buffer.append("\n");

        if(OptArraySq!=null)
            buffer.append(OptArraySq.toString("  "+tab));
        else
            buffer.append(tab+"  null");
        buffer.append("\n");

        buffer.append(tab);
        buffer.append(") [FormParItem]");
        return buffer.toString();
    }
}
