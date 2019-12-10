package design_pattern.memento.memento1;

/**
 * @description: 备忘录
 * @author: 黎清许
 * @create: 2019-12-10 17:50
 * <p>
 * CopyRight &copy; All rights reserved.
 **/
public class Memento {

    private String state;

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public Memento(String state) {
        this.state = state;
    }

}
