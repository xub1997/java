package design_pattern.memento.memento1;

/**
 * @description: 管理人
 * @author: 黎清许
 * @create: 2019-12-10 17:51
 * <p>
 * CopyRight &copy; All rights reserved.
 **/
public class Caretaker {
    private Memento memento;

    public Memento getMemento() {
        return memento;
    }

    public void setMemento(Memento memento) {
        this.memento = memento;
    }
}
