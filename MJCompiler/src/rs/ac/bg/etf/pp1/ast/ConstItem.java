// generated with ast extension for cup
// version 0.8
// 22/7/2019 18:1:15


package rs.ac.bg.etf.pp1.ast;

public class ConstItem implements SyntaxNode {

    private SyntaxNode parent;
    private int line;
    private String id;
    private Initializer Initializer;

    public ConstItem (String id, Initializer Initializer) {
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

    public SyntaxNode getParent() {
        return parent;
    }

    public void setParent(SyntaxNode parent) {
        this.parent=parent;
    }

    public int getLine() {
        return line;
    }

    public void setLine(int line) {
        this.line=line;
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
        buffer.append("ConstItem(\n");

        buffer.append(" "+tab+id);
        buffer.append("\n");

        if(Initializer!=null)
            buffer.append(Initializer.toString("  "+tab));
        else
            buffer.append(tab+"  null");
        buffer.append("\n");

        buffer.append(tab);
        buffer.append(") [ConstItem]");
        return buffer.toString();
    }
}
