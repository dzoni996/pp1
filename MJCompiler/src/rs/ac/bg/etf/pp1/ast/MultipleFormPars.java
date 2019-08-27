// generated with ast extension for cup
// version 0.8
// 27/7/2019 20:33:58


package rs.ac.bg.etf.pp1.ast;

public class MultipleFormPars extends FormPars {

    private FormPars FormPars;
    private FormParsItem FormParsItem;

    public MultipleFormPars (FormPars FormPars, FormParsItem FormParsItem) {
        this.FormPars=FormPars;
        if(FormPars!=null) FormPars.setParent(this);
        this.FormParsItem=FormParsItem;
        if(FormParsItem!=null) FormParsItem.setParent(this);
    }

    public FormPars getFormPars() {
        return FormPars;
    }

    public void setFormPars(FormPars FormPars) {
        this.FormPars=FormPars;
    }

    public FormParsItem getFormParsItem() {
        return FormParsItem;
    }

    public void setFormParsItem(FormParsItem FormParsItem) {
        this.FormParsItem=FormParsItem;
    }

    public void accept(Visitor visitor) {
        visitor.visit(this);
    }

    public void childrenAccept(Visitor visitor) {
        if(FormPars!=null) FormPars.accept(visitor);
        if(FormParsItem!=null) FormParsItem.accept(visitor);
    }

    public void traverseTopDown(Visitor visitor) {
        accept(visitor);
        if(FormPars!=null) FormPars.traverseTopDown(visitor);
        if(FormParsItem!=null) FormParsItem.traverseTopDown(visitor);
    }

    public void traverseBottomUp(Visitor visitor) {
        if(FormPars!=null) FormPars.traverseBottomUp(visitor);
        if(FormParsItem!=null) FormParsItem.traverseBottomUp(visitor);
        accept(visitor);
    }

    public String toString(String tab) {
        StringBuffer buffer=new StringBuffer();
        buffer.append(tab);
        buffer.append("MultipleFormPars(\n");

        if(FormPars!=null)
            buffer.append(FormPars.toString("  "+tab));
        else
            buffer.append(tab+"  null");
        buffer.append("\n");

        if(FormParsItem!=null)
            buffer.append(FormParsItem.toString("  "+tab));
        else
            buffer.append(tab+"  null");
        buffer.append("\n");

        buffer.append(tab);
        buffer.append(") [MultipleFormPars]");
        return buffer.toString();
    }
}
