package com.xub.java.design_pattern.behavioral.memento.memento3;

/**
 * @description: 对象属性对象
 * @author: 黎清许
 * @create: 2019-12-11 10:29
 * <p>
 * CopyRight &copy; All rights reserved.
 **/
public class State {

    private String userId;

    private String version;

    private String state;

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getVersion() {
        return version;
    }

    public void setVersion(String version) {
        this.version = version;
    }

    public State() {
    }

    @Override
    public String toString() {
        return "State{" +
                "userId='" + userId + '\'' +
                ", version='" + version + '\'' +
                ", state='" + state + '\'' +
                '}';
    }
}
