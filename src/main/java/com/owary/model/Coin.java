package com.owary.model;

import java.awt.*;

/**
 * GameObject Example for Coin.
 *
 * @author Sayid Akhundov
 */
public class Coin extends GameObject {

    private int size = 16;

    public Coin(int x, int y) {
        super(x, y, ID.Coin);
        velX = 1;
        velY = 1;
    }

    @Override
    public void render(Graphics g) {
        g.setColor(Color.BLACK);
        g.fillOval(x, y, size, size);
    }

    @Override
    public Rectangle getBounds() {
        return new Rectangle(x, y, size, size);
    }

}
