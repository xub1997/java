package design_pattern.builder.builder1;

/**
 * @description:
 * @author: 黎清许
 * @create: 2019-12-09 14:55
 * <p>
 * CopyRight &copy; All rights reserved.
 **/
public class Builder1Client {
    public static void main(String[] args) {
        System.out.println("-------------构造器1模式-------------------------");
        BuilderDemo1 demo1 = new Director(new BuilderA()).build();
        System.out.println(demo1.toString());
        BuilderDemo1 demo2 = new Director(new BuilderB()).build();
        System.out.println(demo2.toString());
    }
}
