package com.owary.model;

import com.owary.Game;
import com.owary.model.types.ID;
import com.owary.utils.ResourceUtils;

import java.awt.*;
import java.io.IOException;
import java.io.Serializable;

/**
 * An abstract class for realization of our Game Objects.
 *
 * @author Sayid Akhundov
 */
public abstract class GameObject implements Serializable {
    protected int x;
    protected int y;
    protected int velX;
    protected int velY;

    protected int height;
    protected int width;

    protected ID id;
    protected Color defaultColor;

    protected String characterImage;

    public GameObject(int x, int y, ID id) {
        this.x = x;
        this.y = y;
        this.id = id;
    }

    public int getVelX() {
        return velX;
    }

    protected void setVelX(int velX) {
        this.velX = velX;
    }

    public int getVelY() {
        return velY;
    }

    protected void setVelY(int velY) {
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

    public String getCharacterImage() {
        return characterImage;
    }

    public void setCharacterImage(String characterImage) {
        this.characterImage = characterImage;
    }

    public int getHeight() {
        return height;
    }

    public void setHeight(int height) {
        this.height = height;
    }

    public int getWidth() {
        return width;
    }

    public void setWidth(int width) {
        this.width = width;
    }

    public void tick() {
        x += velX;
        y += velY;

        if (y >= Game.HEIGHT - 40 || y <= 0) velY *= -1;
        if (x >= Game.WIDTH - 16 || x <= 0) velX *= -1;
    }

    public void render(Graphics g) {
        try {
            g.setColor(new Color(0,0,0,0));
            g.fillOval(x, y, width, height);
            Image resource = ResourceUtils.getResourceOf(this);
            g.drawImage(resource, x, y, null);
        } catch (IOException | NullPointerException e) {
            g.setColor(defaultColor);
            g.fillRect(x, y, width, height);
            System.out.println("Resource is null, fallback to a color");
        }
    }

    public abstract Rectangle getBounds();
}
