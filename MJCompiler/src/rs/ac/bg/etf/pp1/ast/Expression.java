// generated with ast extension for cup
// version 0.8
// 17/7/2019 20:52:6


package rs.ac.bg.etf.pp1.ast;

public class Expression extends Expr {

    private OptMinus OptMinus;
    private Term Term;
    private OptAddTerms OptAddTerms;

    public Expression (OptMinus OptMinus, Term Term, OptAddTerms OptAddTerms) {
        this.OptMinus=OptMinus;
        if(OptMinus!=null) OptMinus.setParent(this);
        this.Term=Term;
        if(Term!=null) Term.setParent(this);
        this.OptAddTerms=OptAddTerms;
        if(OptAddTerms!=null) OptAddTerms.setParent(this);
    }

    public OptMinus getOptMinus() {
        return OptMinus;
    }

    public void setOptMinus(OptMinus OptMinus) {
        this.OptMinus=OptMinus;
    }

    public Term getTerm() {
        return Term;
    }

    public void setTerm(Term Term) {
        this.Term=Term;
    }

    public OptAddTerms getOptAddTerms() {
        return OptAddTerms;
    }

    public void setOptAddTerms(OptAddTerms OptAddTerms) {
        this.OptAddTerms=OptAddTerms;
    }

    public void accept(Visitor visitor) {
        visitor.visit(this);
    }

    public void childrenAccept(Visitor visitor) {
        if(OptMinus!=null) OptMinus.accept(visitor);
        if(Term!=null) Term.accept(visitor);
        if(OptAddTerms!=null) OptAddTerms.accept(visitor);
    }

    public void traverseTopDown(Visitor visitor) {
        accept(visitor);
        if(OptMinus!=null) OptMinus.traverseTopDown(visitor);
        if(Term!=null) Term.traverseTopDown(visitor);
        if(OptAddTerms!=null) OptAddTerms.traverseTopDown(visitor);
    }

    public void traverseBottomUp(Visitor visitor) {
        if(OptMinus!=null) OptMinus.traverseBottomUp(visitor);
        if(Term!=null) Term.traverseBottomUp(visitor);
        if(OptAddTerms!=null) OptAddTerms.traverseBottomUp(visitor);
        accept(visitor);
    }

    public String toString(String tab) {
        StringBuffer buffer=new StringBuffer();
        buffer.append(tab);
        buffer.append("Expression(\n");

        if(OptMinus!=null)
            buffer.append(OptMinus.toString("  "+tab));
        else
            buffer.append(tab+"  null");
        buffer.append("\n");

        if(Term!=null)
            buffer.append(Term.toString("  "+tab));
        else
            buffer.append(tab+"  null");
        buffer.append("\n");

        if(OptAddTerms!=null)
            buffer.append(OptAddTerms.toString("  "+tab));
        else
            buffer.append(tab+"  null");
        buffer.append("\n");

        buffer.append(tab);
        buffer.append(") [Expression]");
        return buffer.toString();
    }
}
