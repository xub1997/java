package com.xub.design_pattern.behavioral.state.state1;/**
 * @description: ${description}
 * @author: 黎清许
 * @create: 2019-12-24 17:43
 * <p>
 * CopyRight &copy; All rights reserved.
 **/

/**
 * @Name: Context
 * @Description: TODO
 * @author xub
 * @date 2019/12/24  17:43
 */
public class Context {
    private State state;

    public Context() {
        this.state=new ConcreteStateA();
    }

    public State getState() {
        return state;
    }

    public void setState(State state) {
        this.state = state;
    }

    public void handle(){
        state.handle(this);
    }
}
