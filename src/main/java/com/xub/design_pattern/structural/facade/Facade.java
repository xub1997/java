package com.xub.design_pattern.structural.facade;/**
 * @description: ${description}
 * @author: 黎清许
 * @create: 2019-12-26 11:35
 * <p>
 * CopyRight &copy; All rights reserved.
 **/

/**
 * @Name: Facade
 * @Description: TODO
 * @author xub
 * @date 2019/12/26  11:35
 */
public class Facade {
    private SubSystemA subSystemA = new SubSystemA();
    private SubSystemB subSystemB = new SubSystemB();
    private SubSystemC subSystemC = new SubSystemC();

    public void method(){
        subSystemA.method();
        subSystemB.method();
        subSystemC.method();
    }
}
