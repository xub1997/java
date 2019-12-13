package design_pattern.adapter.classAdapter;

/**
 * @description: 设配器
 * @author: 黎清许
 * @create: 2019-12-13 16:15
 * <p>
 * CopyRight &copy; All rights reserved.
 **/
public class Adapter extends Adaptee implements Target {
    /**
     * 通过继承被适配者，调用父类的具体方法实现适配器模式
     */
    @Override
    public void request() {
        System.out.println("适配器调用");
        super.specificRequest();
    }
}
