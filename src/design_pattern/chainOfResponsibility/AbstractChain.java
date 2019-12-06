package design_pattern.chainOfResponsibility;

/**
 * @description: 抽象责任链
 * @author: 黎清许
 * @create: 2019-12-05 17:10
 * <p>
 * CopyRight &copy; All rights reserved.
 **/
public abstract class AbstractChain {

    public static final int LEVEL1 = 1;
    public static final int LEVEL2 = 2;
    public static final int LEVEL3 = 3;

    protected int level;

    protected AbstractChain nextChain;


    protected void handle(int level, String msg) {
        if (this.level == level) {
            System.out.println(String.format("当前等级：%d 处理，消息为：%s", this.level, msg));
        } else {
            if (nextChain == null) {
                System.out.println("没有下一级处理");
                return;
            }
            nextChain.handle(level, msg);
        }
    }

    protected void process(int level, String msg) {
        if (this.level >= level) {
            System.out.println(String.format("当前等级：%d 处理，消息为：%s", this.level, msg));
        }
        if (nextChain != null) {
            nextChain.process(level, msg);
        }
    }
}
