package design_pattern.builder.builder3;

/**
 * @description: 接口调用
 * @author: 黎清许
 * @create: 2019-12-09 11:43
 * <p>
 * CopyRight &copy; All rights reserved.
 **/
public class Builder3Client {
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
