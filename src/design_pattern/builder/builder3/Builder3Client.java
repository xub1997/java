package design_pattern.builder.builder3;

/**
 * @description: 接口调用
 * @author: 黎清许
 * @create: 2019-12-09 11:43
 * <p>
 * CopyRight &copy; All rights reserved.
 **/
public class Builder3Client {

    /**
     * 适用场景：
     *
     * 需要生成的产品对象有复杂的内部结构，这些产品对象通常包含多个成员属性。
     * 需要生成的产品对象的属性相互依赖，需要指定其生成顺序。
     * 对象的创建过程独立于创建该对象的类。在建造者模式中通过引入了指挥者类，将创建过程封装在指挥者类中，而不在建造者类和客户类中。
     * 隔离复杂对象的创建和使用，并使得相同的创建过程可以创建不同的产品。
     * @param args
     */
    public static void main(String[] args) {
        System.out.println("-------------构造器模式变种-------------------------");
        BuilderDemo builderDemo = new BuilderDemo.Builder().build();
        System.out.println(builderDemo.toString());
        BuilderDemo builderDemo1 = new BuilderDemo.Builder().partA("测试partA").build();
        System.out.println(builderDemo1.toString());
        BuilderDemo BuilderDemo = new BuilderDemo.Builder().partA("测试partA").partB("测试partB").build();
        System.out.println(BuilderDemo.toString());
        BuilderDemo builderDemo3 = new BuilderDemo.Builder().partB("测试partB").partC("测试partC").build();
        System.out.println(builderDemo3.toString());
        BuilderDemo builderDemo4 = new BuilderDemo.Builder().partA("测试partA").partC("测试partC").build();
        System.out.println(builderDemo4.toString());
        BuilderDemo builderDemo5 = new BuilderDemo.Builder().partA("测试partA").partB("测试partB").partC("测试partC").build();
        System.out.println(builderDemo5.toString());
    }
}
