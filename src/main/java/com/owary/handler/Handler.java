package com.owary.handler;

import com.owary.model.GameObject;

import java.awt.*;

public interface Handler {

    void tick();
    void render(Graphics g);
    void addObject(GameObject object);
    void removeObject(GameObject object);

}
