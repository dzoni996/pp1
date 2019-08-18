// generated with ast extension for cup
// version 0.8
// 18/7/2019 22:9:22


package rs.ac.bg.etf.pp1.ast;

public class ConstInit extends ConstItem {

    private String id;
    private Initializer Initializer;

    public ConstInit (String id, Initializer Initializer) {
        this.id=id;
        this.Initializer=Initializer;
        if(Initializer!=null) Initializer.setParent(this);
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id=id;
    }

    public Initializer getInitializer() {
        return Initializer;
    }

    public void setInitializer(Initializer Initializer) {
        this.Initializer=Initializer;
    }

    public void accept(Visitor visitor) {
        visitor.visit(this);
    }

    public void childrenAccept(Visitor visitor) {
        if(Initializer!=null) Initializer.accept(visitor);
    }

    public void traverseTopDown(Visitor visitor) {
        accept(visitor);
        if(Initializer!=null) Initializer.traverseTopDown(visitor);
    }

    public void traverseBottomUp(Visitor visitor) {
        if(Initializer!=null) Initializer.traverseBottomUp(visitor);
        accept(visitor);
    }

    public String toString(String tab) {
        StringBuffer buffer=new StringBuffer();
        buffer.append(tab);
        buffer.append("ConstInit(\n");

        buffer.append(" "+tab+id);
        buffer.append("\n");

        if(Initializer!=null)
            buffer.append(Initializer.toString("  "+tab));
        else
            buffer.append(tab+"  null");
        buffer.append("\n");

        buffer.append(tab);
        buffer.append(") [ConstInit]");
        return buffer.toString();
    }
}
