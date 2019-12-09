package design_pattern.abstractFactory;

/**
 * @description: 客户调用
 * @author: 黎清许
 * @create: 2019-12-09 11:09
 * <p>
 * CopyRight &copy; All rights reserved.
 **/
public class AbstractFactoryClient {
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
