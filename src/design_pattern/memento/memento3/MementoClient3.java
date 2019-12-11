package design_pattern.memento.memento3;

/**
 * @description:
 * @author: 黎清许
 * @create: 2019-12-11 10:26
 * <p>
 * CopyRight &copy; All rights reserved.
 **/
public class MementoClient3 {

    /**
     * 备忘录模式角色：
     *   Originator:负责创建一个备忘录,可以记录、恢复自身的内部状态。同时Originator还可以根据需要决定Memento存储自身的那些内部状态。
     *   Memento:备忘录角色，用于存储Originator的内部状态，并且可以防止Originator以外的对象访问Memento。
     *   Caretaker：负责存储备忘录，不能对备忘录的内容进行操作和访问，只能够将备忘录传递给其他对象。
     *
     * 备忘录模式中的相关概念
     *   1. 窄接口：
     *     只允许它把备忘录对象传给其他的对象。针对的是负责人对象和其他除发起人对象之外的任何对象。
     *
     *   2. 宽接口:
     *     允许它读取所有的数据，以便根据这些数据恢复这个发起人对象的内部状态。针对发起人。
     *
     * 多重检查点
     *   常见的系统往往需要存储不止一个状态，而是需要存储多个状态，或者叫做有多个检查点。用list或者map存储，这种叫做多重检查点
     * @param args
     */
    public static void main(String[] args) {
        Originator originator = new Originator();
        String userId = "123";
        State originatorState1 = new State();
        originatorState1.setUserId(userId);
        originatorState1.setState("开始状态");
        originatorState1.setVersion("version1");
        originator.setState(originatorState1);
        System.out.println(String.format("对象的状态： %s",originator.getState()));
        Caretaker caretaker = new Caretaker();
        caretaker.setMemento(userId+originator.createMemento().getState().getVersion(),originator.createMemento());
        State originatorState2 = new State();
        originatorState2.setUserId(userId);
        originatorState2.setState("游戏状态");
        originatorState2.setVersion("version2");
        originator.setState(originatorState2);
        caretaker.setMemento(userId+originator.createMemento().getState().getVersion(),originator.createMemento());
        State originatorState3 = new State();
        originatorState3.setUserId(userId);
        originatorState3.setState("大boss状态");
        originatorState3.setVersion("version3");
        originator.setState(originatorState3);
        caretaker.setMemento(userId+originator.createMemento().getState().getVersion(),originator.createMemento());
        System.out.println("开始回滚到version1");
        System.out.println(String.format("回滚前对象的状态： %s",originator.getState()));
        originator.restore(caretaker.getMemento(userId+"version1"));
        System.out.println(String.format("回滚后对象的状态： %s",originator.getState()));
        System.out.println("开始回滚到version2");
        System.out.println(String.format("回滚前对象的状态： %s",originator.getState()));
        originator.restore(caretaker.getMemento(userId+"version2"));
        System.out.println(String.format("回滚后对象的状态： %s",originator.getState()));
        System.out.println("打印所有备份");
        caretaker.printAllMementos();
    }
}
