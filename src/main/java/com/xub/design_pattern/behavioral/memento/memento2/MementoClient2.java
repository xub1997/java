package com.xub.design_pattern.behavioral.memento.memento2;

/**
 * @description:
 * @author: 黎清许
 * @create: 2019-12-11 09:41
 * <p>
 * CopyRight &copy; All rights reserved.
 **/
public class MementoClient2 {

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
     * 黑箱模式：
     * 备忘录角色对发起人（Originator）角色对象提供一个宽接口，而为其他对象提供一个窄接口。
     * 这样的实现叫做“黑箱实现”。 简单说就是备忘录是一个接口，实现类放到发起人里面去了，外界看不见。
     * @param args
     */
    public static void main(String[] args) {
        Originator originator = new Originator();
        originator.setState(new State("开始状态"));
        System.out.println(String.format("对象的状态： %s",originator.getState()));
        Caretaker caretaker = new Caretaker();
        caretaker.setMemento(originator.createMemento());
        originator.setState(new State("游戏状态"));
        System.out.println("开始回滚");
        System.out.println(String.format("回滚前对象的状态： %s",originator.getState()));
        originator.restore(caretaker.getMemento());
        System.out.println(String.format("回滚后对象的状态： %s",originator.getState()));
    }
}
