package com.owary.model;

import com.owary.Game;
import com.owary.handler.Handler;

import java.awt.*;

/**
 * GameObject Object Example for an Enemy.
 *
 * @author Sayid Akhundov
 */
public class Enemy extends GameObject {

    private Handler handler;
    private Color color;

    public Enemy(int x, int y, ID id, Handler handler, Color color) {
        super(x, y, id);
        this.handler = handler;
        velX = 5;
        velY = 5;
        this.color = color;
    }

    @Override
    public void render(Graphics g) {
        if (id == ID.Player) g.setColor(Color.red);
        else g.setColor(color);
        g.fillRect(x, y, 16, 16);
    }

    @Override
    public Rectangle getBounds() {
        return new Rectangle(x, y, 16, 16);
    }

}
