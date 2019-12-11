package design_pattern.memento.memento2;


/**
 * @description: 管理人
 * @author: 黎清许
 * @create: 2019-12-10 17:51
 * <p>
 * CopyRight &copy; All rights reserved.
 **/
public class Caretaker {
    private MementoIF memento;

    public MementoIF getMemento() {
        return memento;
    }

    public void setMemento(MementoIF memento) {
        this.memento = memento;
    }
}
