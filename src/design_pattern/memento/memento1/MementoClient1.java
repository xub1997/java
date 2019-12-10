package design_pattern.memento.memento1;

/**
 * @description:
 * @author: 黎清许
 * @create: 2019-12-10 18:01
 * <p>
 * CopyRight &copy; All rights reserved.
 **/
public class MementoClient1 {

    public static void main(String[] args) {
        Originator originator = new Originator();
        originator.setState("开始状态");
        System.out.println(String.format("对象的状态： %s",originator.getState()));
        Memento memento = originator.createMemento();
        Caretaker caretaker = new Caretaker();
        caretaker.setMemento(memento);
        originator.setState("游戏状态");
        System.out.println(String.format("回滚前对象的状态： %s",originator.getState()));
        System.out.println("开始回滚");
        originator.restore(caretaker.getMemento());
        System.out.println(String.format("回滚后对象的状态： %s",originator.getState()));
    }
}
