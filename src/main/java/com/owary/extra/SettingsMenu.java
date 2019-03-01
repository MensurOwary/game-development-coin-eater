package com.owary.extra;

import com.owary.utils.ResourceUtils;

import java.awt.*;
import java.io.IOException;

import static com.owary.utils.Utils.drawButtons;
import static com.owary.utils.Utils.drawCenteredString;

public class SettingsMenu implements Menu {

    private Rectangle demo;

    private int height, width;

    private static Pointer pointer = new Pointer(1);

    public SettingsMenu(Dimension dimension){
        this.height = dimension.height;
        this.width = dimension.width;

        demo = new Rectangle(width/2 - 150, height/2 - 25, 300, 100);
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
        drawCenteredString(g, "Settings", height/3, width);

        // put Menu
        drawButtons(g, "Settings", demo, pointer, 1);

    }

}
