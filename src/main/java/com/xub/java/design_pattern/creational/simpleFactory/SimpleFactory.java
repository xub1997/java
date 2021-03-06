package com.xub.java.design_pattern.creational.simpleFactory;

/**
 * @description:
 * @author: 黎清许
 * @create: 2019-12-09 09:43
 * <p>
 * CopyRight &copy; All rights reserved.
 * 简单工厂模式
 **/
public class SimpleFactory {
    /**
     * 在创建一个对象时不向客户暴露内部细节，并提供一个创建对象的通用接口。
     *
     * 简单工厂把实例化的操作单独放到一个类中，这个类就成为简单工厂类，让简单工厂类来决定应该用哪个具体子类来实例化。
     *
     * 这样做能把客户类和具体子类的实现解耦，客户类不再需要知道有哪些子类以及应当实例化哪个子类。客户类往往有多个，
     * 如果不使用简单工厂，那么所有的客户类都要知道所有子类的细节。而且一旦子类发生改变，例如增加子类，那么所有的客户类都要进行修改。
     * @param type
     * @return
     */
    public static Product createProduct(int type) {
        Product product = null;
        if (type == 1) {
            product = new ProductA();
        } else if (type == 2) {
            product = new ProductB();
        } else if (type == 3) {
            product = new ProductC();
        }
        return product;
    }

    //利用反射创建实例
    public static Product createProduct(Class clazz){
        Product product=null;
        try {
            product = (Product) clazz.newInstance();
        } catch (InstantiationException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }
        return product;
    }

//    public static <T extends Product> T createProduct(Class<T> clazz){
//        Product product=null;
//        try {
//            product= (Product) clazz.newInstance();
//        } catch (InstantiationException e) {
//            e.printStackTrace();
//        } catch (IllegalAccessException e) {
//            e.printStackTrace();
//        }
//        return (T)product;
//    }


    //利用反射创建实例
    public static Product createProduct(String className){
        try {
            Product product= (Product) Class.forName(className).newInstance();
            return product;
        } catch (InstantiationException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        return null;
    }

}
