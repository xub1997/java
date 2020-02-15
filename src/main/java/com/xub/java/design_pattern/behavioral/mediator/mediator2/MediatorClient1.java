package com.xub.java.design_pattern.behavioral.mediator.mediator2;

/**
 * @description:
 * @author: 黎清许
 * @create: 2019-12-11 17:06
 * <p>
 * CopyRight &copy; All rights reserved.
 **/
public class MediatorClient1 {
    /**
     * 中介者模式包含以下主要角色。
     * 抽象中介者（Mediator）角色：它是中介者的接口，提供了同事对象注册与转发同事对象信息的抽象方法。
     * 具体中介者（ConcreteMediator）角色：实现中介者接口，定义一个 List 来管理同事对象，协调各个同事角色之间的交互关系，因此它依赖于同事角色。
     * 抽象同事类（Colleague）角色：定义同事类的接口，保存中介者对象，提供同事对象交互的抽象方法，实现所有相互影响的同事类的公共功能。
     * 具体同事类（Concrete Colleague）角色：是抽象同事类的实现者，当需要与其他同事对象交互时，由中介者对象负责后续的交互。
     *
     * 中介者模式的主要优点：
     *
     * 1、中介者模式简化了对象之间的交互，它用中介者和同事的一对多交互代替了原来同事之间的多对多交互，
     * 一对多关系更容易理解、维护和扩展，将原本难以理解的网状结构转换成相对简单的星型结构。
     *
     * 2、中介者模式可将各同事对象解耦。中介者有利于各同事之间的松耦合，我们可以独立的改变和复用每一个同事和中介者，
     * 增加新的中介者和新的同事类都比较方便，更好地符合 “开闭原则”。
     *
     * 3、可以减少子类生成，中介者将原本分布于多个对象间的行为集中在一起，改变这些行为只需生成新的中介者子类即可，这使各个同事类可被重用，无须对同事类进行扩展。
     *
     * 中介者模式的主要缺点：
     *
     * 在具体中介者类中包含了大量同事之间的交互细节，可能会导致具体中介者类非常复杂，使得系统难以维护。
     * （也就是把具体同事类之间的交互复杂性集中到了中介者类中，结果中介者成了最复杂的类）
     *
     *
     * 适用场景
     *
     * 1、系统中对象之间存在复杂的引用关系，系统结构混乱且难以理解。
     *
     * 2、一个对象由于引用了其他很多对象并且直接和这些对象通信，导致难以复用该对象。
     *
     * 3、想通过一个中间类来封装多个类中的行为，而又不想生成太多的子类。可以通过引入中介者类来实现，
     * 在中介者中定义对象交互的公共行为，如果需要改变行为则可以增加新的具体中介者类。
     *
     *  JDK(用到的地方)：
     *  All scheduleXXX() methods of java.util.Timer
     * java.util.concurrent.Executor#execute()
     * submit() and invokeXXX() methods of java.util.concurrent.ExecutorService
     * scheduleXXX() methods of java.util.concurrent.ScheduledExecutorService
     * java.lang.reflect.Method#invoke()
     *
     * @param args
     */
    public static void main(String[] args) {
        ConcreteColleagueA concreteColleagueA = new ConcreteColleagueA();
        ConcreteColleagueB concreteColleagueB = new ConcreteColleagueB();
        concreteColleagueA.send();
        System.out.println("---------------");
        concreteColleagueB.send();
    }
}
