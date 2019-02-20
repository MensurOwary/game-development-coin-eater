package com.owary.model;

import com.owary.Game;
import com.owary.handler.Handler;
import com.owary.handler.HandlerImpl;

import java.awt.*;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;


/**
 * Realization of Player GameObject
 *
 * @author Sayid Akhundov
 */
public class Player extends GameObject {
    private Handler handler;

    private boolean[] keyPressed = new boolean[5];

    private int width = 50;
    private int height = 50;
    private int health = 100;
    private int score = 0;

    public Player(int x, int y, ID id, Handler handler) {
        super(x, y, id);
        this.handler = handler;
    }

    @Override
    public void tick() {
        handleCollision();
        if (keyPressed[1]) {
            setVelX(-10);
        }else if (keyPressed[2]) {
            setVelX(10);
        }else {
            velX = 0;
        }

        if (keyPressed[3]) {
            setVelY(-10);
        }else if (keyPressed[4]) {
            setVelY(10);
        }else {
            velY = 0;
        }
        x += velX;
        y += velY;

        x = Game.clamp(x, 0, Game.WIDTH - (width + 5));
        y = Game.clamp(y, 0, Game.HEIGHT - (height + 18));
    }

    @Override
    public void render(Graphics g) {
        g.setColor(Color.white);
        g.fillOval(x, y, width, height);
    }

    @Override
    public Rectangle getBounds() {
        return new Rectangle(x, y, width, height);
    }

    public int getHealth() {
        return health;
    }

    public int getScore() {
        return score;
    }

    public boolean[] getKeyPressed() {
        return keyPressed;
    }

    private List<GameObject> getCollidedObjects(){
        return this.handler
                .getObjects()
                .parallelStream()
                .filter(e -> e.getBounds().intersects(this.getBounds()))
                .filter(e->e!=this)
                .collect(Collectors.toList());
    }

    private void handleCollision(){
        final List<GameObject> gameObjects = getCollidedObjects();
        if(!gameObjects.isEmpty()){

            Map<ID, List<GameObject>> collect = gameObjects
                    .stream()
                    .collect(Collectors.groupingBy(GameObject::getId));

            if (collect.containsKey(ID.Coin)) {
                List<GameObject> coins = collect.get(ID.Coin);
                int size = coins.size();
                score += size*10;
                if (score>100){
                    score = 100;
                }
                if (!coins.isEmpty()) {
                    this.handler.removeObject(coins.get(0));
                }
            }

            if (collect.containsKey(ID.Enemy)) {
                List<GameObject> enemies = collect.get(ID.Enemy);
                int size = enemies.size();
                health -= size*5;
                if (health < 0){
                    health = 0;
                }
                if (!enemies.isEmpty()) {
                    this.handler.removeObject(enemies.get(0));
                }
            }
        }
    }
}
