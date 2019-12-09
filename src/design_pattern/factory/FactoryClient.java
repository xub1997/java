package design_pattern.factory;

/**
 * @description: 客户调用
 * @author: 黎清许
 * @create: 2019-12-09 11:02
 * <p>
 * CopyRight &copy; All rights reserved.
 **/
public class FactoryClient {
    /**
     * 要对应产品就得新建对应的工厂，产品与工厂一一对应
     * @param args
     */
    public static void main(String[] args) {
        Factory factory = new ProductAFactory();
        System.out.println(String.format("工厂的具体实现类： %s",factory.getClass()));
        Product product = factory.factoryMethod();
        System.out.println(String.format("产品的具体实现类： %s",product.getClass()));
        System.out.println("-----------------------------------");
        factory = new ProductBFactory();
        System.out.println(String.format("工厂的具体实现类： %s",factory.getClass()));
        product = factory.factoryMethod();
        System.out.println(String.format("产品的具体实现类： %s",product.getClass()));
        System.out.println("-----------------------------------");
        factory = new ProductCFactory();
        System.out.println(String.format("工厂的具体实现类： %s",factory.getClass()));
        product = factory.factoryMethod();
        System.out.println(String.format("产品的具体实现类： %s",product.getClass()));
        System.out.println("-----------------------------------");
    }
}
