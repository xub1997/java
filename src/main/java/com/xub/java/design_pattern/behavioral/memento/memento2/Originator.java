package com.xub.java.design_pattern.behavioral.memento.memento2;


/**
 * @description: 原始对象
 * @author: 黎清许
 * @create: 2019-12-10 17:50
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

    public void restore(MementoIF memento) {
        this.setState(((Memento)memento).getState());
    }

    private class Memento implements MementoIF {
        private State state;

        public State getState() {
            return state;
        }

        public void setState(State state) {
            this.state = state;
        }

        public Memento(State state) {
            this.state = state;
        }
    }
}
