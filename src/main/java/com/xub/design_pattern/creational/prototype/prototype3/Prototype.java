package com.xub.design_pattern.creational.prototype.prototype3;

import java.io.*;

/**
 * @description: 原型类
 * @author: 黎清许
 * @create: 2019-12-09 15:45
 * <p>
 * CopyRight &copy; All rights reserved.
 **/
public class Prototype implements Serializable {
    private Integer prototype1;
    private String prototype2;
    private PropetypeParam param;

    public Integer getPrototype1() {
        return prototype1;
    }

    public void setPrototype1(Integer prototype1) {
        this.prototype1 = prototype1;
    }

    public String getPrototype2() {
        return prototype2;
    }

    public void setPrototype2(String prototype2) {
        this.prototype2 = prototype2;
    }

    public PropetypeParam getParam() {
        return param;
    }

    public void setParam(PropetypeParam param) {
        this.param = param;
    }

    /**
     * 深拷贝：第一种方式：实现Serializable接口
     *
     * @return
     */
    public Object deepClone() throws IOException, ClassNotFoundException {
        /**
         *  写入当前对象的二进制流
         */
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        ObjectOutputStream objectOutputStream = new ObjectOutputStream(byteArrayOutputStream);
        objectOutputStream.writeObject(this);

        /**
         * 写出当前对象二进制流
         */
        ByteArrayInputStream byteArrayInputStream = new ByteArrayInputStream(byteArrayOutputStream.toByteArray());
        ObjectInputStream objectInputStream;
        objectInputStream = new ObjectInputStream(byteArrayInputStream);
        return objectInputStream.readObject();

    }

    @Override
    public String toString() {
        return "Prototype{" +
                "prototype1=" + prototype1 +
                ", prototype2='" + prototype2 + '\'' +
                ", param=" + param +
                '}';
    }
}
