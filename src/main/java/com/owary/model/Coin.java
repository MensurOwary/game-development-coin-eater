/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.owary.model;

import com.owary.Game;

import java.awt.*;

/**
 * GameObject Example for Coin.
 *
 * @author Sayid Akhundov
 */
public class Coin extends GameObject {

    public Coin(int x, int y, ID id) {
        super(x, y, id);
        velX = 3;
        velY = 3;
    }

    @Override
    public void render(Graphics g) {
        if (id == ID.Player) g.setColor(Color.red);
        else g.setColor(Color.BLACK);
        g.fillOval(x, y, 16, 16);

    }

    @Override
    public Rectangle getBounds() {
        return new Rectangle(x, y, 16, 16);
    }

}
