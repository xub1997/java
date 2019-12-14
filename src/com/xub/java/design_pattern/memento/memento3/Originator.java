package com.xub.java.design_pattern.memento.memento3;

/**
 * @description: 原始对象
 * @author: 黎清许
 * @create: 2019-12-10 17:48
 * <p>
 * CopyRight &copy; All rights reserved.
 **/
public class Originator {

    private State state;



    public State getState() {
        return state;
    }

    public void setState(State state) {
        this.state = state;
    }

    public Memento createMemento() {
        return new Memento(this.state);
    }

    public void restore(Memento memento) {
        this.setState(memento.getState());
    }


}
