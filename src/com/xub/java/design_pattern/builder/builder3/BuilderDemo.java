package com.xub.java.design_pattern.builder.builder3;

/**
 * @description: 构造器模式
 * @author: 黎清许
 * @create: 2019-12-09 11:37
 * <p>
 * CopyRight &copy; All rights reserved.
 * 属于构造器模式变种，属于开发中常用的模式
 **/
public class BuilderDemo {

    private String partA;
    private String partB;
    private String partC;

    private BuilderDemo(Builder builder) {
        this.partA = builder.partA;
        this.partB = builder.partB;
        this.partC = builder.partC;
    }

    @Override
    public String toString() {
        return "BuilderDemo{" +
                "partA='" + partA + '\'' +
                ", partB='" + partB + '\'' +
                ", partC='" + partC + '\'' +
                '}';
    }

    public static class Builder {
        private String partA;
        private String partB;
        private String partC;

        public Builder() {
        }

        public Builder partA(String partA){
            this.partA = partA;
            return this;
        }

        public Builder partB(String partB){
            this.partB = partB;
            return this;
        }

        public Builder partC(String partC){
            this.partC = partC;
            return this;
        }

        public BuilderDemo build(){
            return new BuilderDemo(this);
        }
    }
}
