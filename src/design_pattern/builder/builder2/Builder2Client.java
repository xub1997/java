package design_pattern.builder.builder2;

/**
 * @description:
 * @author: 黎清许
 * @create: 2019-12-09 14:56
 * <p>
 * CopyRight &copy; All rights reserved.
 **/
public class Builder2Client {
    public static void main(String[] args) {
        System.out.println("-------------构造器2模式-------------------------");
        BuilderDemo2 demo1 = new ConcreteBuilder().partA("第一个partA").partB("第一个partB").partC("第一个partC").build();
        System.out.println(demo1.toString());
        BuilderDemo2 demo2 =  new ConcreteBuilder().partA("第二个partA").partB("第二个partB").partC("第二个partC").build();
        System.out.println(demo2.toString());
    }
}
