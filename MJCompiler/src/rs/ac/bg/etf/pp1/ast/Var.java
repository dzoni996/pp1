// generated with ast extension for cup
// version 0.8
// 28/7/2019 10:50:46


package rs.ac.bg.etf.pp1.ast;

public class Var extends VarItem {

    private String varName;
    private OptArraySq OptArraySq;

    public Var (String varName, OptArraySq OptArraySq) {
        this.varName=varName;
        this.OptArraySq=OptArraySq;
        if(OptArraySq!=null) OptArraySq.setParent(this);
    }

    public String getVarName() {
        return varName;
    }

    public void setVarName(String varName) {
        this.varName=varName;
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
        if(OptArraySq!=null) OptArraySq.accept(visitor);
    }

    public void traverseTopDown(Visitor visitor) {
        accept(visitor);
        if(OptArraySq!=null) OptArraySq.traverseTopDown(visitor);
    }

    public void traverseBottomUp(Visitor visitor) {
        if(OptArraySq!=null) OptArraySq.traverseBottomUp(visitor);
        accept(visitor);
    }

    public String toString(String tab) {
        StringBuffer buffer=new StringBuffer();
        buffer.append(tab);
        buffer.append("Var(\n");

        buffer.append(" "+tab+varName);
        buffer.append("\n");

        if(OptArraySq!=null)
            buffer.append(OptArraySq.toString("  "+tab));
        else
            buffer.append(tab+"  null");
        buffer.append("\n");

        buffer.append(tab);
        buffer.append(") [Var]");
        return buffer.toString();
    }
}
