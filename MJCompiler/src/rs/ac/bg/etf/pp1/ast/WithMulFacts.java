// generated with ast extension for cup
// version 0.8
// 16/7/2019 1:53:29


package rs.ac.bg.etf.pp1.ast;

public class WithMulFacts extends OptMulTerms {

    private OptMulTerms OptMulTerms;
    private Mulop Mulop;
    private Factor Factor;

    public WithMulFacts (OptMulTerms OptMulTerms, Mulop Mulop, Factor Factor) {
        this.OptMulTerms=OptMulTerms;
        if(OptMulTerms!=null) OptMulTerms.setParent(this);
        this.Mulop=Mulop;
        if(Mulop!=null) Mulop.setParent(this);
        this.Factor=Factor;
        if(Factor!=null) Factor.setParent(this);
    }

    public OptMulTerms getOptMulTerms() {
        return OptMulTerms;
    }

    public void setOptMulTerms(OptMulTerms OptMulTerms) {
        this.OptMulTerms=OptMulTerms;
    }

    public Mulop getMulop() {
        return Mulop;
    }

    public void setMulop(Mulop Mulop) {
        this.Mulop=Mulop;
    }

    public Factor getFactor() {
        return Factor;
    }

    public void setFactor(Factor Factor) {
        this.Factor=Factor;
    }

    public void accept(Visitor visitor) {
        visitor.visit(this);
    }

    public void childrenAccept(Visitor visitor) {
        if(OptMulTerms!=null) OptMulTerms.accept(visitor);
        if(Mulop!=null) Mulop.accept(visitor);
        if(Factor!=null) Factor.accept(visitor);
    }

    public void traverseTopDown(Visitor visitor) {
        accept(visitor);
        if(OptMulTerms!=null) OptMulTerms.traverseTopDown(visitor);
        if(Mulop!=null) Mulop.traverseTopDown(visitor);
        if(Factor!=null) Factor.traverseTopDown(visitor);
    }

    public void traverseBottomUp(Visitor visitor) {
        if(OptMulTerms!=null) OptMulTerms.traverseBottomUp(visitor);
        if(Mulop!=null) Mulop.traverseBottomUp(visitor);
        if(Factor!=null) Factor.traverseBottomUp(visitor);
        accept(visitor);
    }

    public String toString(String tab) {
        StringBuffer buffer=new StringBuffer();
        buffer.append(tab);
        buffer.append("WithMulFacts(\n");

        if(OptMulTerms!=null)
            buffer.append(OptMulTerms.toString("  "+tab));
        else
            buffer.append(tab+"  null");
        buffer.append("\n");

        if(Mulop!=null)
            buffer.append(Mulop.toString("  "+tab));
        else
            buffer.append(tab+"  null");
        buffer.append("\n");

        if(Factor!=null)
            buffer.append(Factor.toString("  "+tab));
        else
            buffer.append(tab+"  null");
        buffer.append("\n");

        buffer.append(tab);
        buffer.append(") [WithMulFacts]");
        return buffer.toString();
    }
}
