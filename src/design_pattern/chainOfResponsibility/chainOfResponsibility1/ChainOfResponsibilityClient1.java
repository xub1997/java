package design_pattern.chainOfResponsibility.chainOfResponsibility1;

/**
 * @description:
 * @author: 黎清许
 * @create: 2019-12-05 17:57
 * <p>
 * CopyRight &copy; All rights reserved.
 **/
public class ChainOfResponsibilityClient1 {
    public static void main(String[] args) {
        Chain chain1 = new ConcreteChain1(null);
        Chain chain2 = new ConcreteChain2(chain1);
        Chain chain3 = new ConcreteChain3(chain2);
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
