package com.owary.action;

import java.util.HashMap;

public class PlayerControlMapper {

    private final HashMap<Integer, Integer> controlMap;

    public PlayerControlMapper(){
        controlMap = new HashMap<>();
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
