package design_pattern.chainOfResponsibility;

/**
 * @description:
 * @author: 黎清许
 * @create: 2019-12-05 17:57
 * <p>
 * CopyRight &copy; All rights reserved.
 **/
public class Main {
    public static void main(String[] args) {
        AbstractChain topChain = new TopChain();
        topChain.handle(1,"等级一");
        topChain.handle(2,"等级二");
        topChain.handle(3,"等级三");
        topChain.handle(4,"等级四");
        System.out.println("---------------------");
        topChain.process(1,"从一级处理");
        topChain.process(2,"从二级处理");
        topChain.process(3,"从三级处理");
        topChain.process(4,"从四级处理");
    }
}
