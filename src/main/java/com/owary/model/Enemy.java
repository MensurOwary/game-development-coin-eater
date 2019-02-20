package com.owary.model;

import java.awt.*;

import static com.owary.utils.Utils.getRandomColor;

/**
 * GameObject Object Example for an Enemy.
 *
 * @author Sayid Akhundov
 */
public class Enemy extends GameObject {

    private int size = 16;
    private Color color;

    public Enemy(int x, int y) {
        super(x, y, ID.Enemy);
        velX = 5;
        velY = 5;
        this.color = getRandomColor();
    }

    @Override
    public void render(Graphics g) {
        g.setColor(color);
        g.fillRect(x, y, size, size);
    }

    @Override
    public Rectangle getBounds() {
        return new Rectangle(x, y, size, size);
    }

}
