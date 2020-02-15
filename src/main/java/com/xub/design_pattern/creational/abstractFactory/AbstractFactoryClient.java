package com.xub.design_pattern.creational.abstractFactory;

/**
 * @description: 客户调用
 * @author: 黎清许
 * @create: 2019-12-09 11:09
 * <p>
 * CopyRight &copy; All rights reserved.
 **/
public class AbstractFactoryClient {
    /**
     * 抽象工厂模式的主要优点如下：
     *
     * 抽象工厂模式隔离了具体类的生成，使得客户并不需要知道什么被创建。由于这种隔离，更换一个具体工厂就变得相对容易，
     * 所有的具体工厂都实现了抽象工厂中定义的那些公共接口，因此只需改变具体工厂的实例，就可以在某种程度上改变整个软件系统的行为。
     * 当一个产品族中的多个对象被设计成一起工作时，它能够保证客户端始终只使用同一个产品族中的对象。
     * 增加新的产品族很方便，无须修改已有系统，符合”开闭原则”。
     *
     * 抽象工厂模式的主要缺点如下：
     *
     * 增加新的产品等级结构麻烦，需要对原有系统进行较大的修改，甚至需要修改抽象层代码，这显然会带来较大的不便，违背了\”开闭原则”。
     *
     * 适用场景：
     *
     * 一个系统不应当依赖于产品类实例如何被创建、组合和表达的细节，这对于所有类型的工厂模式都是很重要的，用户无须关心对象的创建过程，将对象的创建和使用解耦。
     * 系统中有多于一个的产品族，而每次只使用其中某一产品族。可以通过配置文件等方式来使得用户可以动态改变产品族，也可以很方便地增加新的产品族。
     * 属于同一个产品族的产品将在一起使用，这一约束必须在系统的设计中体现出来。同一个产品族中的产品可以是没有任何关系的对象，
     * 但是它们都具有一些共同的约束，如同一操作系统下的按钮和文本框，按钮与文本框之间没有直接关系，但它们都是属于某一操作系统的，此时具有一个共同的约束条件：操作系统的类型。
     * 产品等级结构稳定，设计完成之后，不会向系统中增加新的产品等级结构或者删除已有的产品等级结构。
     *
     * @param args
     */
    public static void main(String[] args) {
        AbstractFactory factory = new ConcreteFactory1();
        System.out.println(String.format("工厂的具体实现类： %s",factory.getClass()));
        AbstractProductA productA = factory.getProductA();
        System.out.println(String.format("产品A的具体实现类： %s",productA.getClass()));
        AbstractProductB productB = factory.getProductB();
        System.out.println(String.format("产品B的具体实现类： %s",productB.getClass()));
        System.out.println("-----------------------------------");
        factory = new ConcreteFactory2();
        System.out.println(String.format("工厂的具体实现类： %s",factory.getClass()));
        productA = factory.getProductA();
        System.out.println(String.format("产品A的具体实现类： %s",productA.getClass()));
        productB = factory.getProductB();
        System.out.println(String.format("产品B的具体实现类： %s",productB.getClass()));
    }
}
