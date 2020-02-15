package com.xub.design_pattern.behavioral.command.command3;

/**
 * @description: 备忘录
 * @author: 黎清许
 * @create: 2019-12-10 17:50
 * <p>
 * CopyRight &copy; All rights reserved.
 * 白箱模式
 * 将发起人角色的状态存储在一个大家都看得到的地方，
 * 备忘录角色的内部所存储的状态就对所有对象公开。因此这个实现又叫做“白箱实现”。
 **/
public class Memento {

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
