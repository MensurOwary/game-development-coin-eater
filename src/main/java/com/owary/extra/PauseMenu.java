package com.owary.extra;

import com.owary.adjustments.State;
import com.owary.utils.ResourceUtils;

import java.awt.*;
import java.io.IOException;

import static com.owary.utils.Utils.drawButtons;
import static com.owary.utils.Utils.drawCenteredString;

public class PauseMenu implements Menu{

    private Rectangle resume;
    private Rectangle menu;
    private Rectangle settings;
    private Rectangle exit;

    private int height, width;

    private static Pointer pointer = new Pointer(4);

    public PauseMenu(Dimension dimension) {
        this.height = dimension.height;
        this.width = dimension.width;

        resume = new Rectangle(width/2 - 150, height/2 - 175, 300, 100);
        menu = new Rectangle(width/2 - 150, height/2 - 25, 300, 100);
        settings = new Rectangle(width/2 - 150, height/2 + 125, 300, 100);
        exit = new Rectangle(width/2 - 150, height/2 + 275, 300, 100);
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
        drawCenteredString(g, "Paused", height/3, width);

        // put Resume
        drawButtons(g, "Resume", resume, pointer, 1);

        // put Menu
        drawButtons(g, "Main Menu", menu, pointer, 2);

        // put Settings
        drawButtons(g, "Settings", settings, pointer, 3);

        // put Exit
        drawButtons(g, "Exit Game", exit, pointer, 4);
    }

    public static void switchModeVertical(boolean isUp){
        if (isUp)
            pointer.previous();
        else
            pointer.next();
    }

    public static State getCurrentMode(){
        switch (pointer.getCurrent()){
            case 1:
                return State.GAME;
            case 2:
                return State.MENU;
            case 3:
                return State.SETTINGS;
            case 4:
                return State.EXIT;
            default:
                return State.NO_OP;
        }
    }
}
