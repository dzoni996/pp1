// generated with ast extension for cup
// version 0.8
// 21/7/2019 23:52:34


package rs.ac.bg.etf.pp1.ast;

public class DesignFactor extends Factor {

    private Designator Designator;
    private OptMethodCall OptMethodCall;

    public DesignFactor (Designator Designator, OptMethodCall OptMethodCall) {
        this.Designator=Designator;
        if(Designator!=null) Designator.setParent(this);
        this.OptMethodCall=OptMethodCall;
        if(OptMethodCall!=null) OptMethodCall.setParent(this);
    }

    public Designator getDesignator() {
        return Designator;
    }

    public void setDesignator(Designator Designator) {
        this.Designator=Designator;
    }

    public OptMethodCall getOptMethodCall() {
        return OptMethodCall;
    }

    public void setOptMethodCall(OptMethodCall OptMethodCall) {
        this.OptMethodCall=OptMethodCall;
    }

    public void accept(Visitor visitor) {
        visitor.visit(this);
    }

    public void childrenAccept(Visitor visitor) {
        if(Designator!=null) Designator.accept(visitor);
        if(OptMethodCall!=null) OptMethodCall.accept(visitor);
    }

    public void traverseTopDown(Visitor visitor) {
        accept(visitor);
        if(Designator!=null) Designator.traverseTopDown(visitor);
        if(OptMethodCall!=null) OptMethodCall.traverseTopDown(visitor);
    }

    public void traverseBottomUp(Visitor visitor) {
        if(Designator!=null) Designator.traverseBottomUp(visitor);
        if(OptMethodCall!=null) OptMethodCall.traverseBottomUp(visitor);
        accept(visitor);
    }

    public String toString(String tab) {
        StringBuffer buffer=new StringBuffer();
        buffer.append(tab);
        buffer.append("DesignFactor(\n");

        if(Designator!=null)
            buffer.append(Designator.toString("  "+tab));
        else
            buffer.append(tab+"  null");
        buffer.append("\n");

        if(OptMethodCall!=null)
            buffer.append(OptMethodCall.toString("  "+tab));
        else
            buffer.append(tab+"  null");
        buffer.append("\n");

        buffer.append(tab);
        buffer.append(") [DesignFactor]");
        return buffer.toString();
    }
}
