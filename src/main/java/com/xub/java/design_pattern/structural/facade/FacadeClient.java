package com.xub.java.design_pattern.structural.facade;/**
 * @description: ${description}
 * @author: 黎清许
 * @create: 2019-12-26 11:38
 * <p>
 * CopyRight &copy; All rights reserved.
 **/

/**
 * @Name: FacadeClient
 * @Description: TODO
 * @author xub
 * @date 2019/12/26  11:38
 */
public class FacadeClient {
    public static void main(String[] args){
        Facade facade = new Facade();
        facade.method();
    }
}
