package com.owary.extra;

import com.owary.model.Player;

import java.awt.*;

/**
 * Creating a simple information panel for our game to display.
 *
 * @author Sayid Akhundov
 */
public class HUD {

    private Player player;

    public HUD(Player player) {
        this.player = player;
    }


    public void tick() {
    }

    public void render(Graphics g) {
        int health = player.getHealth();
        int score = player.getScore();

        g.setColor(Color.gray);
        g.fillRect(15, 15, 200, 32);
        g.setColor(new Color(255 - health * 2, health * 2, 0));

        g.fillRect(15, 15, health * 2, 32);
        g.setColor(Color.white);
        g.drawRect(15, 15, 200, 32);

        g.drawString("Score : " + score, 300, 20);

    }


}