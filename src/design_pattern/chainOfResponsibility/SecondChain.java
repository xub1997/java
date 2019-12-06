package design_pattern.chainOfResponsibility;

/**
 * @description:
 * @author: 黎清许
 * @create: 2019-12-05 17:56
 * <p>
 * CopyRight &copy; All rights reserved.
 **/
public class SecondChain extends AbstractChain {
    public SecondChain() {
        this.level = AbstractChain.LEVEL2;
        nextChain = new LastChain();
    }
}
