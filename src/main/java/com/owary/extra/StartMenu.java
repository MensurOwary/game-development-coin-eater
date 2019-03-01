package com.owary.extra;

import com.owary.utils.ResourceUtils;

import java.awt.*;
import java.io.IOException;

import static com.owary.utils.Utils.drawCenteredString;

public class StartMenu implements Menu{

    private Rectangle singlePlayer;
    private Rectangle twoPlayers;

    private final int height;
    private final int width;

    private static boolean isSingle = true;

    public StartMenu(Dimension dimension) {
        this.height = dimension.height;
        this.width = dimension.width;

        singlePlayer = new Rectangle(width/2 - 150, height/2 - 50, 300, 100);
        twoPlayers = new Rectangle(width/2 - 150, height/2 + 100, 300, 100);
    }

    @Override
    public void tick() {
    }

    @Override
    public void render(Graphics g1) {
        Graphics2D g = (Graphics2D) g1;

        Image resource;
        try {
            resource = ResourceUtils.getResourceOf("assets/images/bg/bg.jpg", height, width);
            g.drawImage(resource, 0, 0, null);
        } catch (IOException e) {
            g.setColor(new Color(128, 128, 128, 100));
            g.fillRect(0, 0, width, height);
            e.printStackTrace();
        }
        // put title
        g.setColor(Color.WHITE);
        drawCenteredString(g, "Coin Eater", height/3, width);

        // put Single Player Mode
        g.setColor(isSingle ? Color.YELLOW : Color.WHITE);
        g.draw(singlePlayer);
        drawCenteredString(g, "1 Player", singlePlayer);

        // put Two Players Mode
        g.setColor(!isSingle ? Color.YELLOW : Color.WHITE);
        g.draw(twoPlayers);
        drawCenteredString(g, "2 Players", twoPlayers);
    }

    public static void switchMode(){
        isSingle = !isSingle;
    }

    public static boolean isSingleMode(){
        return isSingle;
    }

}
