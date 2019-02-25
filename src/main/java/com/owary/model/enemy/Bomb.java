package com.owary.model.enemy;

import com.owary.Game;

import java.util.Random;

import static com.owary.Game.HEIGHT;
import static com.owary.Game.WIDTH;

public class Bomb extends Enemy {

    public Bomb(int x, int y) {
        super(x, y);
        velX = 4;
        velY = 4;
        characterImage = "assets/images/enemies/bomb.png";
    }

    @Override
    public void tick() {
        x += velX;
        y += velY;
        Random random = new Random();

        if (y >= Game.HEIGHT - 40 || y <= 0) {
            x = random.nextInt(WIDTH - 20);
            y = random.nextInt(HEIGHT - 40);
            velY *= -1;
        }
        if (x >= Game.WIDTH - 16 || x <= 0) {
            x = random.nextInt(WIDTH - 20);
            y = random.nextInt(HEIGHT - 40);
            velX *= -1;
        }
    }

    @Override
    public int getWeight() {
        return 15;
    }
}
