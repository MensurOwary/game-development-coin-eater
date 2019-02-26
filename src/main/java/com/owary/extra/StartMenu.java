package com.owary.extra;

import java.awt.*;

public class StartMenu {

    private final Dimension dimension;

    public StartMenu(Dimension dimension) {
        this.dimension = dimension;
    }

    public void tick() {
    }

    public void render(Graphics g) {
        Font font = new Font("arial", Font.BOLD, 50);

        int height = dimension.height;
        int width = dimension.width;

        g.setColor(new Color(128, 128, 128, 100));
        g.fillRect(0, 0, width, height);

        g.setColor(Color.WHITE);
        g.drawString("Coin Eater", width/2, height/2);
    }

}
