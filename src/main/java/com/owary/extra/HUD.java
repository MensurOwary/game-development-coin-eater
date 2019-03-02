package com.owary.extra;

import com.owary.model.player.Player;

import java.awt.*;

/**
 * Creating a simple information panel for our game to display.
 *
 * @author Sayid Akhundov
 */
public class HUD {

    private final Player player;

    private boolean isSingle;

    private int x;
    private int y;

    private int scoreTextLocation;

    public static int screenWidth;
    public static int screenHeight;

    public HUD(Player player) {
        this(player, true);
    }

    public HUD(Player player, boolean isSingle) {
        this.player = player;
        this.isSingle = isSingle;

        this.x = 15;
        this.y = 15;
        this.scoreTextLocation = 300;
        if (!isSingle) {
            this.x = screenWidth - this.x - 200;
            this.scoreTextLocation = screenWidth - 300;
        }
    }

    public void tick() {
    }

    public void render(Graphics g) {
        int health = player.getHealth();
        int score = player.getScore();

        g.setColor(new Color(128, 128, 128, 50));
        g.fillRect(x, y, 200, 32);
        g.setColor(new Color(255 - health * 2, health * 2, 0, 50));

        g.fillRect(x, y, health * 2, 32);
        g.setColor(Color.white);
        g.drawRect(x, y, 200, 32);

        g.drawString("Score : " + score, scoreTextLocation, 20);
    }


}
