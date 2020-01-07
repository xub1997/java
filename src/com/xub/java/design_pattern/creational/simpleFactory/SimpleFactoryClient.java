package com.xub.java.design_pattern.creational.simpleFactory;

/**
 * @description: 客户调用
 * @author: 黎清许
 * @create: 2019-12-09 11:01
 * <p>
 * CopyRight &copy; All rights reserved.
 **/
public class SimpleFactoryClient {
    /**
     * 简单工厂模式的主要优点如下：
     *
     * 工厂类包含必要的判断逻辑，可以决定在什么时候创建哪一个产品类的实例，
     * 客户端可以免除直接创建产品对象的职责，而仅仅“消费”产品，简单工厂模式实现了对象创建和使用的分离。
     * 客户端无须知道所创建的具体产品类的类名，只需要知道具体产品类所对应的参数即可，
     * 对于一些复杂的类名，通过简单工厂模式可以在一定程度减少使用者的记忆量。
     * 通过引入配置文件，可以在不修改任何客户端代码的情况下更换和增加新的具体产品类，在一定程度上提高了系统的灵活性。
     *
     * 简单工厂模式的主要缺点如下：
     *
     * 由于工厂类集中了所有产品的创建逻辑，职责过重，一旦不能正常工作，整个系统都要受到影响。
     * 使用简单工厂模式势必会增加系统中类的个数（引入了新的工厂类），增加了系统的复杂度和理解难度。
     * 系统扩展困难，一旦添加新产品就不得不修改工厂逻辑，在产品类型较多时，有可能造成工厂逻辑过于复杂，不利于系统的扩展和维护，且违背开闭原则。
     * 简单工厂模式由于使用了静态工厂方法，造成工厂角色无法形成基于继承的等级结构。
     *
     * 适用场景：
     *
     * 工厂类负责创建的对象比较少，由于创建的对象较少，不会造成工厂方法中的业务逻辑太过复杂。
     * 客户端只知道传入工厂类的参数，对于如何创建对象并不关心
     * @param args
     */
    public static void main(String[] args) {
        Product product = SimpleFactory.createProduct(1);
        System.out.println(product);
        if (product != null) {
            System.out.println(String.format("对应的class类名： %s", product.getClass()));
        }
        System.out.println("-----------------------------------");
        product = SimpleFactory.createProduct(ProductB.class);
        System.out.println(product);
        if (product != null) {
            System.out.println(String.format("对应的class类名： %s", product.getClass()));
        }
        System.out.println("-----------------------------------");
        product = SimpleFactory.createProduct("com.xub.java.design_pattern.creational.simpleFactory.ProductC");
        System.out.println(product);
        if (product != null) {
            System.out.println(String.format("对应的class类名： %s", product.getClass()));
        }
        System.out.println("-----------------------------------");
        product = SimpleFactory.createProduct(4);
        System.out.println(product);
        if (product != null) {
            System.out.println(String.format("对应的class类名： %s", product.getClass()));
        }
        System.out.println("-----------------------------------");
    }
}