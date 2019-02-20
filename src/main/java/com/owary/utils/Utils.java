package com.owary.utils;

import com.owary.action.PlayerControlMapper;

import java.awt.*;
import java.awt.event.KeyEvent;
import java.util.Random;

public class Utils {

    public static Color getRandomColor(){
        Random random = new Random();
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
