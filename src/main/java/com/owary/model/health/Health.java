package com.owary.model.health;

import com.owary.model.GameObject;
import com.owary.model.types.ID;
import com.owary.model.Weighted;

import java.awt.*;

public class Health extends GameObject implements Weighted {

    public Health(int x, int y) {
        super(x, y, ID.Health);
        velX = 0;
        velY = 0;
        height = 16;
        width = 16;
        characterImage = "assets/images/health/heart.png";
        defaultColor = Color.RED;
    }

    @Override
    public Rectangle getBounds() {
        return new Rectangle((int)x, (int)y, (int)width, (int)height);
    }

    @Override
    public int getWeight() {
        return 5;
    }
}
