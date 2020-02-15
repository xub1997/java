package com.xub.design_pattern.behavioral.visitor;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/**
 * @description: 对象结构
 * @author: 黎清许
 * @create: 2019-12-13 11:50
 * <p>
 * CopyRight &copy; All rights reserved.
 **/
public class ObjectStructure {
    private List<Element> elementList = new ArrayList<>();

    public void accept(Visitor visitor) {
        Iterator<Element> iterator = elementList.iterator();
        while (iterator.hasNext()) {
            Element element = iterator.next();
            element.accept(visitor);
        }
    }

    public void addElement(Element element){
        if(!elementList.contains(element)){
            elementList.add(element);
        }
    }

    public void removeElement(Element element){
        elementList.remove(element);
    }
}
