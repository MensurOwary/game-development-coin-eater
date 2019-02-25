package com.owary.utils;

import com.owary.action.PlayerControlMapper;
import com.owary.adjustments.Level;
import com.owary.model.coin.Coin;
import com.owary.model.enemy.Bomb;
import com.owary.model.enemy.Chuck;
import com.owary.model.GameObject;
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
        switch (key){
            case Coin:
                return new Coin(random.nextInt(WIDTH - 20), random.nextInt(HEIGHT - 40));
            case Health:
                return new Health(random.nextInt(WIDTH - 20), random.nextInt(HEIGHT - 40));
            case Enemy:
                if (level.equals(Level.ONE)){
                    return new Red(random.nextInt(WIDTH - 20), random.nextInt(HEIGHT - 40));
                }else if(level.equals(Level.TWO)){
                    return new Chuck(random.nextInt(WIDTH - 20), random.nextInt(HEIGHT - 40));
                }else if(level.equals(Level.THREE)){
                    return new Bomb(random.nextInt(WIDTH - 20), random.nextInt(HEIGHT - 40));
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

}
