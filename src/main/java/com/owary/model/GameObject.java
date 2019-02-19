package com.owary.model;

import com.owary.Game;
import com.owary.model.ID;

import java.awt.*;
import java.io.Serializable;

/**
 * An abstract class for realization of our Game Objects.
 *
 * @author Sayid Akhundov
 */
public abstract class GameObject implements Serializable {
    protected int x, y;
    protected ID id;
    protected int velX, velY;

    public GameObject(int x, int y, ID id) {
        this.x = x;
        this.y = y;
        this.id = id;
    }

    public int getVelX() {
        return velX;
    }

    public void setVelX(int velX) {
        this.velX = velX;
    }

    public int getVelY() {
        return velY;
    }

    public void setVelY(int velY) {
        this.velY = velY;
    }

    public ID getId() {
        return id;
    }

    public void setId(ID id) {
        this.id = id;
    }

    public int getX() {
        return x;
    }

    public void setX(int x) {
        this.x = x;
    }

    public int getY() {
        return y;
    }

    public void setY(int y) {
        this.y = y;
    }

    public abstract Rectangle getBounds();

    public void tick() {
        x += velX;
        y += velY;

        if (y >= Game.HEIGHT - 40 || y <= 0) velY *= -1;
        if (x >= Game.WIDTH - 16 || x <= 0) velX *= -1;
    }

    public abstract void render(Graphics g);
}
