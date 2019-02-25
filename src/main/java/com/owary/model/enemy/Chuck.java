package com.owary.model.enemy;

public class Chuck extends Enemy {

    public Chuck(int x, int y) {
        super(x, y);
        velX = 5;
        velY = 5;
        characterImage = "assets/images/enemies/chuck.png";
    }

    @Override
    public int getWeight() {
        return 10;
    }
}
