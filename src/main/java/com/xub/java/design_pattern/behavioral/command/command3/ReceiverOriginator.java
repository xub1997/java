package com.xub.java.design_pattern.behavioral.command.command3;


/**
 * @description: 命令接收者原始对象
 * @author: 黎清许
 * @create: 2019-12-11 11:22
 * <p>
 * CopyRight &copy; All rights reserved.
 **/
public class ReceiverOriginator {

    private Memento memento;

    private State state;

    public State getState() {
        return state;
    }

    public void setState(State state) {
        this.state = state;
    }

    public Memento getMemento() {
        return memento;
    }

    public void setMemento(Memento memento) {
        this.memento = memento;
    }

    public Memento createMemento() {
        return new Memento(this.state);
    }

    public void restore(Memento memento) {
        this.setState(memento.getState());
    }

    @Override
    public String toString() {
        return "ReceiverOriginator{" +
                "state=" + state +
                '}';
    }
}
