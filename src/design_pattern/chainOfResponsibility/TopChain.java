package design_pattern.chainOfResponsibility;

/**
 * @description:
 * @author: 黎清许
 * @create: 2019-12-05 17:54
 * <p>
 * CopyRight &copy; All rights reserved.
 **/
public class TopChain extends AbstractChain {
    public TopChain() {
        this.level = AbstractChain.LEVEL1;
        nextChain = new SecondChain();
    }
}
