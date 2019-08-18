// generated with ast extension for cup
// version 0.8
// 18/7/2019 22:9:22


package rs.ac.bg.etf.pp1.ast;

public class ClassDeclaration extends ClassDecl {

    private String I1;
    private Extend Extend;
    private Implement Implement;
    private OptVarDecl OptVarDecl;
    private ClassMethods ClassMethods;

    public ClassDeclaration (String I1, Extend Extend, Implement Implement, OptVarDecl OptVarDecl, ClassMethods ClassMethods) {
        this.I1=I1;
        this.Extend=Extend;
        if(Extend!=null) Extend.setParent(this);
        this.Implement=Implement;
        if(Implement!=null) Implement.setParent(this);
        this.OptVarDecl=OptVarDecl;
        if(OptVarDecl!=null) OptVarDecl.setParent(this);
        this.ClassMethods=ClassMethods;
        if(ClassMethods!=null) ClassMethods.setParent(this);
    }

    public String getI1() {
        return I1;
    }

    public void setI1(String I1) {
        this.I1=I1;
    }

    public Extend getExtend() {
        return Extend;
    }

    public void setExtend(Extend Extend) {
        this.Extend=Extend;
    }

    public Implement getImplement() {
        return Implement;
    }

    public void setImplement(Implement Implement) {
        this.Implement=Implement;
    }

    public OptVarDecl getOptVarDecl() {
        return OptVarDecl;
    }

    public void setOptVarDecl(OptVarDecl OptVarDecl) {
        this.OptVarDecl=OptVarDecl;
    }

    public ClassMethods getClassMethods() {
        return ClassMethods;
    }

    public void setClassMethods(ClassMethods ClassMethods) {
        this.ClassMethods=ClassMethods;
    }

    public void accept(Visitor visitor) {
        visitor.visit(this);
    }

    public void childrenAccept(Visitor visitor) {
        if(Extend!=null) Extend.accept(visitor);
        if(Implement!=null) Implement.accept(visitor);
        if(OptVarDecl!=null) OptVarDecl.accept(visitor);
        if(ClassMethods!=null) ClassMethods.accept(visitor);
    }

    public void traverseTopDown(Visitor visitor) {
        accept(visitor);
        if(Extend!=null) Extend.traverseTopDown(visitor);
        if(Implement!=null) Implement.traverseTopDown(visitor);
        if(OptVarDecl!=null) OptVarDecl.traverseTopDown(visitor);
        if(ClassMethods!=null) ClassMethods.traverseTopDown(visitor);
    }

    public void traverseBottomUp(Visitor visitor) {
        if(Extend!=null) Extend.traverseBottomUp(visitor);
        if(Implement!=null) Implement.traverseBottomUp(visitor);
        if(OptVarDecl!=null) OptVarDecl.traverseBottomUp(visitor);
        if(ClassMethods!=null) ClassMethods.traverseBottomUp(visitor);
        accept(visitor);
    }

    public String toString(String tab) {
        StringBuffer buffer=new StringBuffer();
        buffer.append(tab);
        buffer.append("ClassDeclaration(\n");

        buffer.append(" "+tab+I1);
        buffer.append("\n");

        if(Extend!=null)
            buffer.append(Extend.toString("  "+tab));
        else
            buffer.append(tab+"  null");
        buffer.append("\n");

        if(Implement!=null)
            buffer.append(Implement.toString("  "+tab));
        else
            buffer.append(tab+"  null");
        buffer.append("\n");

        if(OptVarDecl!=null)
            buffer.append(OptVarDecl.toString("  "+tab));
        else
            buffer.append(tab+"  null");
        buffer.append("\n");

        if(ClassMethods!=null)
            buffer.append(ClassMethods.toString("  "+tab));
        else
            buffer.append(tab+"  null");
        buffer.append("\n");

        buffer.append(tab);
        buffer.append(") [ClassDeclaration]");
        return buffer.toString();
    }
}
