package com.xub.design_pattern.behavioral.memento.memento3;

import java.util.HashMap;
import java.util.Map;

/**
 * @description: 管理人
 * @author: 黎清许
 * @create: 2019-12-10 17:51
 * <p>
 * CopyRight &copy; All rights reserved.
 **/
public class Caretaker {
    private Map<String, Memento> mementoMap = new HashMap<>();

    public Memento getMemento(String key) {
        if (mementoMap.size() == 0) {
            throw new RuntimeException("暂无备份");
        }
        Memento memento = mementoMap.get(key);
        if (memento == null) {
            throw new RuntimeException("无对应备份");
        }
        return memento;
    }

    public void setMemento(String key, Memento memento) {
        if (mementoMap.containsKey(key)) {
            throw new RuntimeException("已存在对应备份");
        }
        mementoMap.put(key, memento);
    }

    public void removeMemento(String key) {
        if (mementoMap.size() == 0) {
            throw new RuntimeException("暂无备份");
        }
        Memento memento = mementoMap.remove(key);
        if (memento == null) {
            throw new RuntimeException("无对应备份");
        }
    }

    public void printAllMementos() {
        if (mementoMap.size() > 0) {
            for (Memento value : mementoMap.values()) {
                System.out.println(value.toString());
            }
        }
    }
}
