package design_pattern.chainOfResponsibility.chainOfResponsibility2;

/**
 * @description:
 * @author: 黎清许
 * @create: 2019-12-05 17:57
 * <p>
 * CopyRight &copy; All rights reserved.
 **/
public class ChainOfResponsibilityClient2 {
    public static void main(String[] args) {
        Chain chain1 = ResponsibilityType.TYPE1.getChain();
        Chain chain2 = ResponsibilityType.TYPE2.getChain();
        chain2.setNextChain(chain1);
        Chain chain3 = ResponsibilityType.TYPE3.getChain();
        chain3.setNextChain(chain2);
        Responsibility responsibility = new Responsibility();
        responsibility.setType(ResponsibilityType.TYPE1.getCode());
        chain1.handle(responsibility);
        System.out.println();
        System.out.println();
        chain2.handle(responsibility);
        System.out.println();
        System.out.println();
        chain3.handle(responsibility);
        System.out.println();
        System.out.println();
        System.out.println("-----------------------");
        responsibility.setType(ResponsibilityType.TYPE2.getCode());
        chain1.handle(responsibility);
        System.out.println();
        System.out.println();
        chain2.handle(responsibility);
        System.out.println();
        System.out.println();
        chain3.handle(responsibility);
        System.out.println();
        System.out.println();
        System.out.println("-----------------------");
        responsibility.setType(ResponsibilityType.TYPE3.getCode());
        chain1.handle(responsibility);
        System.out.println();
        System.out.println();
        chain2.handle(responsibility);
        System.out.println();
        System.out.println();
        chain3.handle(responsibility);
        System.out.println();
        System.out.println();
        System.out.println("-----------------------");
    }
}
