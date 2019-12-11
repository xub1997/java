package design_pattern.mediator.mediator1;

/**
 * @description: 抽象中介
 * @author: 黎清许
 * @create: 2019-12-11 16:05
 * <p>
 * CopyRight &copy; All rights reserved.
 **/
public abstract class Mediator {
    public abstract void register(Colleague colleague);
    public abstract void relay(Colleague colleague);
}
