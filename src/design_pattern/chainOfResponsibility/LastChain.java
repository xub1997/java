package design_pattern.chainOfResponsibility;

/**
 * @description:
 * @author: 黎清许
 * @create: 2019-12-05 17:56
 * <p>
 * CopyRight &copy; All rights reserved.
 **/
public class LastChain extends AbstractChain {
    public LastChain() {
        this.level = AbstractChain.LEVEL3;
    }
}
