package com.owary.model.coin;

import com.owary.model.GameObject;
import com.owary.model.types.ID;
import com.owary.model.Weighted;
import com.owary.utils.ResourceUtils;

import java.awt.*;
import java.io.IOException;

/**
 * GameObject Example for Coin.
 *
 * @author Sayid Akhundov
 */
public class Coin extends GameObject implements Weighted {

    public Coin(int x, int y) {
        super(x, y, ID.Coin);
        velX = 0;
        velY = 0;
        height = 16;
        width = 16;
        characterImage = "assets/images/coins/coin.png";
        defaultColor = Color.BLACK;
    }

    public void makeMovable(){
        velX = 1;
        velY = 1;
    }

    @Override
    public Rectangle getBounds() {
        return new Rectangle(x, y, width, height);
    }

    @Override
    public int getWeight() {
        return 5;
    }
}
