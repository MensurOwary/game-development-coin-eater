package com.owary.model.enemy;

public class Red extends Enemy{

    public Red(int x, int y) {
        super(x, y);
        velX = 3;
        velY = 3;
        characterImage = "assets/images/enemies/red.png";
    }

    @Override
    public int getWeight() {
        return 5;
    }
}
