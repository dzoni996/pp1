// generated with ast extension for cup
// version 0.8
// 28/7/2019 10:50:47


package rs.ac.bg.etf.pp1.ast;

public class WithInitArr extends OptInit {

    private IniStart IniStart;
    private IniStart2 IniStart2;
    private InitList InitList;

    public WithInitArr (IniStart IniStart, IniStart2 IniStart2, InitList InitList) {
        this.IniStart=IniStart;
        if(IniStart!=null) IniStart.setParent(this);
        this.IniStart2=IniStart2;
        if(IniStart2!=null) IniStart2.setParent(this);
        this.InitList=InitList;
        if(InitList!=null) InitList.setParent(this);
    }

    public IniStart getIniStart() {
        return IniStart;
    }

    public void setIniStart(IniStart IniStart) {
        this.IniStart=IniStart;
    }

    public IniStart2 getIniStart2() {
        return IniStart2;
    }

    public void setIniStart2(IniStart2 IniStart2) {
        this.IniStart2=IniStart2;
    }

    public InitList getInitList() {
        return InitList;
    }

    public void setInitList(InitList InitList) {
        this.InitList=InitList;
    }

    public void accept(Visitor visitor) {
        visitor.visit(this);
    }

    public void childrenAccept(Visitor visitor) {
        if(IniStart!=null) IniStart.accept(visitor);
        if(IniStart2!=null) IniStart2.accept(visitor);
        if(InitList!=null) InitList.accept(visitor);
    }

    public void traverseTopDown(Visitor visitor) {
        accept(visitor);
        if(IniStart!=null) IniStart.traverseTopDown(visitor);
        if(IniStart2!=null) IniStart2.traverseTopDown(visitor);
        if(InitList!=null) InitList.traverseTopDown(visitor);
    }

    public void traverseBottomUp(Visitor visitor) {
        if(IniStart!=null) IniStart.traverseBottomUp(visitor);
        if(IniStart2!=null) IniStart2.traverseBottomUp(visitor);
        if(InitList!=null) InitList.traverseBottomUp(visitor);
        accept(visitor);
    }

    public String toString(String tab) {
        StringBuffer buffer=new StringBuffer();
        buffer.append(tab);
        buffer.append("WithInitArr(\n");

        if(IniStart!=null)
            buffer.append(IniStart.toString("  "+tab));
        else
            buffer.append(tab+"  null");
        buffer.append("\n");

        if(IniStart2!=null)
            buffer.append(IniStart2.toString("  "+tab));
        else
            buffer.append(tab+"  null");
        buffer.append("\n");

        if(InitList!=null)
            buffer.append(InitList.toString("  "+tab));
        else
            buffer.append(tab+"  null");
        buffer.append("\n");

        buffer.append(tab);
        buffer.append(") [WithInitArr]");
        return buffer.toString();
    }
}
