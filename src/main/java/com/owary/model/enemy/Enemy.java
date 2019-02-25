package com.owary.model.enemy;

import com.owary.model.GameObject;
import com.owary.model.types.ID;
import com.owary.model.Weighted;

import java.awt.*;

import static com.owary.utils.Utils.getRandomColor;

/**
 * GameObject Object Example for an Enemy.
 *
 * @author Sayid Akhundov
 */
public abstract class Enemy extends GameObject implements Weighted {

    public Enemy(int x, int y) {
        super(x, y, ID.Enemy);
        velX = 1;
        velY = 1;
        height = 32;
        width = 32;
        defaultColor = getRandomColor();
    }

    @Override
    public Rectangle getBounds() {
        return new Rectangle(x, y, width, height);
    }

}
