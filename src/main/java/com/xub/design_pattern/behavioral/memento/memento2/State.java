package com.xub.design_pattern.behavioral.memento.memento2;

/**
 * @description: 对象属性对象
 * @author: 黎清许
 * @create: 2019-12-11 10:29
 * <p>
 * CopyRight &copy; All rights reserved.
 **/
public class State {

    private String state;

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public State(String state) {
        this.state = state;
    }

    @Override
    public String toString() {
        return "State{" +
                "state='" + state + '\'' +
                '}';
    }
}
