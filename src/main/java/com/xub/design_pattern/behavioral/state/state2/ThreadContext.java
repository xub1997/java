package com.xub.design_pattern.behavioral.state.state2;/**
 * @description: ${description}
 * @author: 黎清许
 * @create: 2019-12-24 17:59
 * <p>
 * CopyRight &copy; All rights reserved.
 **/

import javafx.scene.paint.Stop;

/**
 * @Name: ThreadContext
 * @Description: TODO
 * @author xub
 * @date 2019/12/24  17:59
 */
public class ThreadContext {

    private ThreadState state;

    public ThreadContext() {
        state = new New(this);
    }

    public ThreadState getState() {
        return state;
    }

    public void setState(ThreadState state) {
        this.state = state;
    }

    public void start(){
        state = new java.lang.Runnable(this);
    }

    public void getCPU(){
        state = new Running(this);
    }

    public void stop(){
        state = new Dead(this);
    }

    public void suspend(){
        state = new Blocked(this);
    }

    public void resume(){
        state = new java.lang.Runnable(this);
    }
}
