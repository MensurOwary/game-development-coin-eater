package com.owary.utils;

import com.owary.action.PlayerControlMapper;
import com.owary.adjustments.Level;
import com.owary.model.coin.Coin;
import com.owary.model.enemy.Bomb;
import com.owary.model.enemy.Chuck;
import com.owary.model.GameObject;
import com.owary.model.enemy.Terence;
import com.owary.model.health.Health;
import com.owary.model.types.ID;
import com.owary.model.enemy.Red;

import java.awt.*;
import java.awt.event.KeyEvent;
import java.util.Random;

import static com.owary.Game.HEIGHT;
import static com.owary.Game.WIDTH;

public class Utils {

    private static final Random random = new Random();

    public static GameObject getRandomGameObject(ID key, Level level) {
        int randomX = random.nextInt(WIDTH - 20);
        int randomY = random.nextInt(HEIGHT - 40);
        switch (key){
            case Coin:
                return new Coin(randomX, randomY);
            case Health:
                return new Health(randomX, randomY);
            case Enemy:
                if (level.equals(Level.ONE)){
                    return new Red(randomX, randomY);
                }else if(level.equals(Level.TWO)){
                    return new Chuck(randomX, randomY);
                }else if(level.equals(Level.THREE)){
                    return new Bomb(randomX, randomY);
                }else if(level.equals(Level.FOUR)){
                    return new Terence(randomX, randomY);
                }
        }
        return null;
    }

    public static Color getRandomColor(){
        float r = random.nextFloat();
        float g = random.nextFloat();
        float b = random.nextFloat();

        return new Color(r, g, b);
    }

    public static PlayerControlMapper getWASDControl(){
        PlayerControlMapper mapper = new PlayerControlMapper();
        mapper.addControlMapping(KeyEvent.VK_W, 3);
        mapper.addControlMapping(KeyEvent.VK_A, 1);
        mapper.addControlMapping(KeyEvent.VK_S, 4);
        mapper.addControlMapping(KeyEvent.VK_D, 2);
        return mapper;
    }

    public static PlayerControlMapper getArrowControl(){
        PlayerControlMapper mapper = new PlayerControlMapper();
        mapper.addControlMapping(KeyEvent.VK_UP, 3);
        mapper.addControlMapping(KeyEvent.VK_LEFT, 1);
        mapper.addControlMapping(KeyEvent.VK_DOWN, 4);
        mapper.addControlMapping(KeyEvent.VK_RIGHT, 2);
        return mapper;
    }

    public static void drawCenteredString(Graphics2D g, String text, int height, int width) {
        // Font
        Font font = new Font("Arial", Font.BOLD, 50);
        // Get the FontMetrics
        FontMetrics metrics = g.getFontMetrics(font);
        // Determine the X coordinate for the text
        int x = (width - metrics.stringWidth(text)) / 2;
        // Determine the Y coordinate for the text (note we add the ascent, as in java 2d 0 is top of the screen)
        int y = ((height - metrics.getHeight()) / 2) + metrics.getAscent();
        // Set the font
        g.setFont(font);
        // Draw the String
        g.drawString(text, x, y);
    }

    public static void drawCenteredString(Graphics2D g, String text, Rectangle rect) {
        // Font
        Font font = new Font("Arial", Font.BOLD, 50);
        // Get the FontMetrics
        FontMetrics metrics = g.getFontMetrics(font);
        // Determine the X coordinate for the text
        int x = rect.x + (rect.width - metrics.stringWidth(text)) / 2;
        // Determine the Y coordinate for the text (note we add the ascent, as in java 2d 0 is top of the screen)
        int y = rect.y + ((rect.height - metrics.getHeight()) / 2) + metrics.getAscent();
        // Set the font
        g.setFont(font);
        // Draw the String
        g.drawString(text, x, y);
    }


}
