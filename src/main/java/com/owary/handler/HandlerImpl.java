package com.owary.handler;

import com.owary.model.GameObject;

import java.awt.*;
import java.io.Serializable;
import java.util.*;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Our best friend in Game which will handle updating and rendering all GameObjects existing in the game scene and bunch of other functions.
 *
 * @author Sayid Akhundov
 */
public class HandlerImpl implements Handler, Serializable {
    private final List<GameObject> objects;

    public HandlerImpl(){
        this.objects = new LinkedList<>();
    }

    @Override
    public void tick() {
        objects
                .stream()
                .filter(Objects::nonNull)
                .collect(Collectors.toList())
                .forEach(GameObject::tick);
    }

    @Override
    public void render(Graphics g) {
        objects
                .stream()
                .filter(Objects::nonNull)
                .collect(Collectors.toList())
                .forEach(e->e.render(g));
    }

    @Override
    public void addObject(GameObject object) {
        if (object != null)
            this.objects.add(object);
    }

    @Override
    public void removeObject(GameObject object) {
        if (object != null)
            this.objects.removeIf(next -> next == object);
    }

    @Override
    public List<GameObject> getObjects() {
        return objects;
    }
}
