package design_pattern.prototype.prototype1;

/**
 * @description:
 * @author: 黎清许
 * @create: 2019-12-09 15:14
 * <p>
 * CopyRight &copy; All rights reserved.
 **/
public class Prototype1Client {
    public static void main(String[] args) throws CloneNotSupportedException {
        /**
         * 浅拷贝：对值类型的成员变量进行值的复制,对引用类型的成员变量仅仅复制引用,不复制引用的对象
         * 在浅拷贝的原型模式中，对象只能对值类型进行复制，引用类型还是原来的对象，对引用对象进行操作会改变原来对象的值
         */
        System.out.println("-------------原型模式（浅拷贝）----------------");
        Prototype original = new Prototype();
        original.setPrototype1(1);
        original.setPrototype2("测试1");
        PropetypeParam params1 = new PropetypeParam();
        params1.setParam1("测试参数1");
        original.setParam(params1);
        System.out.println(String.format("克隆前: original= %s , original.hasCode = %s, original.param.hasCode = %s", original.toString(), original.hashCode(),original.getParam().hashCode()));
        System.out.println("------------------------");
        Prototype clone = (Prototype) original.clone();
        System.out.println(String.format("克隆后: original= %s , original.hasCode = %s, original.param.hasCode = %s", original.toString(), original.hashCode(),original.getParam().hashCode()));
        System.out.println(String.format("克隆后: clone= %s , clone.hasCode = %s, clone.param.hasCode = %s", clone.toString(), clone.hashCode(),clone.getParam().hashCode()));
        System.out.println("---------修改引用对象：测试参数1 ->  修改测试参数---------------");
        PropetypeParam cloneParam = clone.getParam();
        cloneParam.setParam1("修改测试参数");
        System.out.println(String.format("修改后: prototype1= %s , prototype1.hasCode = %s, prototype1.param.hasCode = %s", original.toString(), original.hashCode(),original.getParam().hashCode()));
        System.out.println(String.format("修改后: prototype1= %s , prototype1.hasCode = %s, prototype1.param.hasCode = %s", clone.toString(), clone.hashCode(),clone.getParam().hashCode()));
        System.out.println("------------------------");
    }
}
