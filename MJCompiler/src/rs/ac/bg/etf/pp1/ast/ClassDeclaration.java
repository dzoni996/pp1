// generated with ast extension for cup
// version 0.8
// 19/7/2019 1:24:44


package rs.ac.bg.etf.pp1.ast;

public class ClassDeclaration extends ClassDecl {

    private ClassName ClassName;
    private Extend Extend;
    private Implement Implement;
    private OptVarDecl OptVarDecl;
    private ClassMethods ClassMethods;

    public ClassDeclaration (ClassName ClassName, Extend Extend, Implement Implement, OptVarDecl OptVarDecl, ClassMethods ClassMethods) {
        this.ClassName=ClassName;
        if(ClassName!=null) ClassName.setParent(this);
        this.Extend=Extend;
        if(Extend!=null) Extend.setParent(this);
        this.Implement=Implement;
        if(Implement!=null) Implement.setParent(this);
        this.OptVarDecl=OptVarDecl;
        if(OptVarDecl!=null) OptVarDecl.setParent(this);
        this.ClassMethods=ClassMethods;
        if(ClassMethods!=null) ClassMethods.setParent(this);
    }

    public ClassName getClassName() {
        return ClassName;
    }

    public void setClassName(ClassName ClassName) {
        this.ClassName=ClassName;
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
        if(ClassName!=null) ClassName.accept(visitor);
        if(Extend!=null) Extend.accept(visitor);
        if(Implement!=null) Implement.accept(visitor);
        if(OptVarDecl!=null) OptVarDecl.accept(visitor);
        if(ClassMethods!=null) ClassMethods.accept(visitor);
    }

    public void traverseTopDown(Visitor visitor) {
        accept(visitor);
        if(ClassName!=null) ClassName.traverseTopDown(visitor);
        if(Extend!=null) Extend.traverseTopDown(visitor);
        if(Implement!=null) Implement.traverseTopDown(visitor);
        if(OptVarDecl!=null) OptVarDecl.traverseTopDown(visitor);
        if(ClassMethods!=null) ClassMethods.traverseTopDown(visitor);
    }

    public void traverseBottomUp(Visitor visitor) {
        if(ClassName!=null) ClassName.traverseBottomUp(visitor);
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

        if(ClassName!=null)
            buffer.append(ClassName.toString("  "+tab));
        else
            buffer.append(tab+"  null");
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
