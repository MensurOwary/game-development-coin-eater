package com.owary.action;

import java.util.HashMap;

public abstract class AbstractControlMapper {

    protected HashMap<Integer, Integer> controlMap;

    public AbstractControlMapper() {
        this.controlMap = new HashMap<>();
    }

    public void addControlMapping(int key, int index){
        controlMap.put(key, index);
    }

    public int getControl(int key){
        if (controlMap.containsKey(key)) {
            return controlMap.get(key);
        }
        return -1;
    }
}
