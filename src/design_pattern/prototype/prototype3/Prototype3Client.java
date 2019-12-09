package design_pattern.prototype.prototype3;


import java.io.IOException;

/**
 * @description:
 * @author: 黎清许
 * @create: 2019-12-09 15:46
 * <p>
 * CopyRight &copy; All rights reserved.
 **/
public class Prototype3Client {
    public static void main(String[] args) throws IOException, ClassNotFoundException {
        /**
         * 深拷贝：对值类型的成员变量进行值的复制,对引用类型的成员变量也进行引用对象的复制
         * 第二种实现方式：实现Serializable接口,原型对象里面所有的引用对象均需要实现Serializable接口
         * 在深拷贝的原型模式中，除了复制值类型还能复制引用对象。修改引用对象的值，不会对原来的数据造成影响
         */
        System.out.println("-------------原型模式（深拷贝）----------------");
        Prototype original = new Prototype();
        original.setPrototype1(1);
        original.setPrototype2("测试1");
        PropetypeParam params1 = new PropetypeParam();
        params1.setParam1("测试参数1");
        original.setParam(params1);
        System.out.println(String.format("克隆前: original= %s , original.hasCode = %s, original.param.hasCode = %s", original.toString(), original.hashCode(),original.getParam().hashCode()));
        System.out.println("------------------------");
        Prototype clone = (Prototype) original.deepClone();
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
