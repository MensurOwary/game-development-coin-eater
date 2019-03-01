package com.owary.handler;

import com.owary.model.player.Player;

import java.awt.*;
import java.util.List;

public interface Handler<T> {

    void tick();
    void render(Graphics g);
    void addObject(T object);
    void removeObject(T object);
    List<T> getObjects();

    void setPlayer(Player player);

}
