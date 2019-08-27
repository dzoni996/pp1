// generated with ast extension for cup
// version 0.8
// 27/7/2019 23:17:7


package rs.ac.bg.etf.pp1.ast;

public class Terms extends Term {

    private Factor Factor;
    private OptMulTerms OptMulTerms;

    public Terms (Factor Factor, OptMulTerms OptMulTerms) {
        this.Factor=Factor;
        if(Factor!=null) Factor.setParent(this);
        this.OptMulTerms=OptMulTerms;
        if(OptMulTerms!=null) OptMulTerms.setParent(this);
    }

    public Factor getFactor() {
        return Factor;
    }

    public void setFactor(Factor Factor) {
        this.Factor=Factor;
    }

    public OptMulTerms getOptMulTerms() {
        return OptMulTerms;
    }

    public void setOptMulTerms(OptMulTerms OptMulTerms) {
        this.OptMulTerms=OptMulTerms;
    }

    public void accept(Visitor visitor) {
        visitor.visit(this);
    }

    public void childrenAccept(Visitor visitor) {
        if(Factor!=null) Factor.accept(visitor);
        if(OptMulTerms!=null) OptMulTerms.accept(visitor);
    }

    public void traverseTopDown(Visitor visitor) {
        accept(visitor);
        if(Factor!=null) Factor.traverseTopDown(visitor);
        if(OptMulTerms!=null) OptMulTerms.traverseTopDown(visitor);
    }

    public void traverseBottomUp(Visitor visitor) {
        if(Factor!=null) Factor.traverseBottomUp(visitor);
        if(OptMulTerms!=null) OptMulTerms.traverseBottomUp(visitor);
        accept(visitor);
    }

    public String toString(String tab) {
        StringBuffer buffer=new StringBuffer();
        buffer.append(tab);
        buffer.append("Terms(\n");

        if(Factor!=null)
            buffer.append(Factor.toString("  "+tab));
        else
            buffer.append(tab+"  null");
        buffer.append("\n");

        if(OptMulTerms!=null)
            buffer.append(OptMulTerms.toString("  "+tab));
        else
            buffer.append(tab+"  null");
        buffer.append("\n");

        buffer.append(tab);
        buffer.append(") [Terms]");
        return buffer.toString();
    }
}
