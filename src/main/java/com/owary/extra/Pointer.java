package com.owary.extra;

public class Pointer {

    private final int MAX;
    private int current;

    public Pointer(int max){
        this.MAX = max;
        this.current = 1;
    }

    public void next(){
        if (current == MAX){
            current = 1;
            return;
        }
        current++;
    }

    public int getCurrent(){
        return current;
    }

}
