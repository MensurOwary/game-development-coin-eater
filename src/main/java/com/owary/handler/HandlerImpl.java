/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.owary.handler;

import com.owary.model.GameObject;

import java.awt.*;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * Our best friend in Game which will handle updating and rendering all GameObjects existing in the game scene and bunch of other functions.
 *
 * @author Sayid Akhundov
 */
public class HandlerImpl implements Handler, Serializable {
    private List<GameObject> objects;

    public HandlerImpl(){
        this.objects = new ArrayList<>();
    }

    public void tick() {
        for (int i = 0; i < objects.size(); i++) {
            GameObject tempObject = objects.get(i);
            if (tempObject != null) tempObject.tick();
        }
    }

    public void render(Graphics g) {
        for (int i = 0; i < objects.size(); i++) {
            GameObject tempObject = objects.get(i);
            if (tempObject != null) tempObject.render(g);
        }
    }

    public void addObject(GameObject object) {
        this.objects.add(object);
    }

    public void removeObject(GameObject object) {
        this.objects.remove(object);
    }
}
