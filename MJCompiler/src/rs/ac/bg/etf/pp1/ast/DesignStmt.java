// generated with ast extension for cup
// version 0.8
// 16/7/2019 1:53:29


package rs.ac.bg.etf.pp1.ast;

public class DesignStmt extends DesignatorStatement {

    private Designator Designator;
    private SideEffect SideEffect;

    public DesignStmt (Designator Designator, SideEffect SideEffect) {
        this.Designator=Designator;
        if(Designator!=null) Designator.setParent(this);
        this.SideEffect=SideEffect;
        if(SideEffect!=null) SideEffect.setParent(this);
    }

    public Designator getDesignator() {
        return Designator;
    }

    public void setDesignator(Designator Designator) {
        this.Designator=Designator;
    }

    public SideEffect getSideEffect() {
        return SideEffect;
    }

    public void setSideEffect(SideEffect SideEffect) {
        this.SideEffect=SideEffect;
    }

    public void accept(Visitor visitor) {
        visitor.visit(this);
    }

    public void childrenAccept(Visitor visitor) {
        if(Designator!=null) Designator.accept(visitor);
        if(SideEffect!=null) SideEffect.accept(visitor);
    }

    public void traverseTopDown(Visitor visitor) {
        accept(visitor);
        if(Designator!=null) Designator.traverseTopDown(visitor);
        if(SideEffect!=null) SideEffect.traverseTopDown(visitor);
    }

    public void traverseBottomUp(Visitor visitor) {
        if(Designator!=null) Designator.traverseBottomUp(visitor);
        if(SideEffect!=null) SideEffect.traverseBottomUp(visitor);
        accept(visitor);
    }

    public String toString(String tab) {
        StringBuffer buffer=new StringBuffer();
        buffer.append(tab);
        buffer.append("DesignStmt(\n");

        if(Designator!=null)
            buffer.append(Designator.toString("  "+tab));
        else
            buffer.append(tab+"  null");
        buffer.append("\n");

        if(SideEffect!=null)
            buffer.append(SideEffect.toString("  "+tab));
        else
            buffer.append(tab+"  null");
        buffer.append("\n");

        buffer.append(tab);
        buffer.append(") [DesignStmt]");
        return buffer.toString();
    }
}
