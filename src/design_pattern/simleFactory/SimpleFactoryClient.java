package design_pattern.simleFactory;

/**
 * @description: 客户调用
 * @author: 黎清许
 * @create: 2019-12-09 11:01
 * <p>
 * CopyRight &copy; All rights reserved.
 **/
public class SimpleFactoryClient {
    public static void main(String[] args) {
        SimpleFactory simpleFactory = new SimpleFactory();
        Product product = simpleFactory.createProduct(1);
        System.out.println(product);
        if (product != null) {
            System.out.println(String.format("对应的class类名： %s", product.getClass()));
        }
        System.out.println("-----------------------------------");
        product = simpleFactory.createProduct(2);
        System.out.println(product);
        if (product != null) {
            System.out.println(String.format("对应的class类名： %s", product.getClass()));
        }
        System.out.println("-----------------------------------");
        product = simpleFactory.createProduct(3);
        System.out.println(product);
        if (product != null) {
            System.out.println(String.format("对应的class类名： %s", product.getClass()));
        }
        System.out.println("-----------------------------------");
        product = simpleFactory.createProduct(4);
        System.out.println(product);
        if (product != null) {
            System.out.println(String.format("对应的class类名： %s", product.getClass()));
        }
        System.out.println("-----------------------------------");
    }
}
