package design_pattern.mediator.mediator1;

/**
 * @description: 抽象同事
 * @author: 黎清许
 * @create: 2019-12-11 16:06
 * <p>
 * CopyRight &copy; All rights reserved.
 **/
public abstract class Colleague {
    protected Mediator mediator;

    public void setMediator(Mediator mediator) {
        this.mediator = mediator;
    }

    public abstract void receive();

    public abstract void send();
}
