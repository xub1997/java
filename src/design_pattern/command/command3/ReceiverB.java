package design_pattern.command.command3;

/**
 * @description: 接收者
 * @author: 黎清许
 * @create: 2019-12-10 15:46
 * <p>
 * CopyRight &copy; All rights reserved.
 **/
public class ReceiverB extends ReceiverOriginator{

    private Memento memento;

    public ReceiverB() {
        this.setState(new State("CommandB process init"));
    }

    public void action() {
        System.out.println(String.format("原本的状态： %s",toString()));
        System.out.println("接收者B的action()方法被调用...");
        this.memento = createMemento();
        setState(new State("CommandB process success"));
        System.out.println(String.format("执行后的状态： %s",toString()));
    }

    public void undo() {
        System.out.println("接收者B的undo()方法被调用...");
        restore(this.memento);
        System.out.println(String.format("回滚后的状态： %s",toString()));
    }
}
