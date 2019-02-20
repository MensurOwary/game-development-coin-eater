package com.owary.utils;

import java.awt.*;
import java.util.Random;

public class Utils {

    public static Color getRandomColor(){
        Random random = new Random();
        float r = random.nextFloat();
        float g = random.nextFloat();
        float b = random.nextFloat();

        return new Color(r, g, b);
    }

}
