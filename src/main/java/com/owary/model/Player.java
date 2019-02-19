package com.owary.model;

import com.owary.Game;
import com.owary.handler.Handler;
import com.owary.handler.HandlerImpl;

import java.awt.*;


/**
 * Realization of Player GameObject
 *
 * @author Sayid Akhundov
 */
public class Player extends GameObject {
    private Handler handler;

    private boolean[] keyPressed = new boolean[5];

    private int width = 50;
    private int height = 50;
    private int health = 0;
    private int score = 0;

    public Player(int x, int y, ID id, Handler handler) {
        super(x, y, id);

        this.handler = handler;

        this.keyPressed[0] = false;
        this.keyPressed[1] = false;
        this.keyPressed[2] = false;
        this.keyPressed[3] = false;
        this.keyPressed[4] = false;

    }

    @Override
    public void tick() {

        if (keyPressed[1])
            setVelX(-10);
        else if (keyPressed[2])
            setVelX(10);
        else
            velX = 0;

        if (keyPressed[3])
            setVelY(-10);
        else if (keyPressed[4])
            setVelY(10);
        else
            velY = 0;

        x += velX;
        y += velY;

        x = Game.clamp(x, 0, Game.WIDTH - (width + 5));
        y = Game.clamp(y, 0, Game.HEIGHT - (height + 18));
    }

    @Override
    public void render(Graphics g) {

        g.setColor(Color.white);
        g.fillOval(x, y, width, height);

    }

    @Override
    public Rectangle getBounds() {
        return new Rectangle(x, y, width, height);
    }

    public int getHealth() {
        return health;
    }

    public int getScore() {
        return score;
    }

    public boolean[] getKeyPressed() {
        return keyPressed;
    }
}
