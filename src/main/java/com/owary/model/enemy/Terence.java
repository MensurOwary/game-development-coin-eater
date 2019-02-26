package com.owary.model.enemy;

import com.owary.model.Weighted;

public class Terence extends Enemy implements Weighted {

    public Terence(int x, int y) {
        super(x, y);
        velX = 0;
        velY = 0;
        characterImage = "assets/images/enemies/terence.png";
    }

    @Override
    public int getWeight() {
        return 40;
    }
}
