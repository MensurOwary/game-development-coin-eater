package com.owary.model.player;

import com.owary.Game;
import com.owary.action.PlayerControlMapper;
import com.owary.handler.Handler;
import com.owary.model.GameObject;
import com.owary.model.coin.Coin;
import com.owary.model.enemy.Enemy;
import com.owary.model.health.Health;
import com.owary.model.types.ID;

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
    private final Handler<GameObject> handler;

    private final boolean[] keyPressed = new boolean[5];

    private int health = 100;
    private int score = 0;

    private final PlayerControlMapper controlMapper;

    public Player(int x, int y, Handler<GameObject> handler, PlayerControlMapper controlMapper) {
        super(x, y, ID.Player);
        this.handler = handler;
        this.controlMapper = controlMapper;
        this.characterImage = "assets/images/players/pig.png";
        this.defaultColor = Color.WHITE;
        this.height = 50;
        this.width = 50;

        this.handler.setPlayer(this);
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

        x = Game.clamp( x, 0, (Game.WIDTH - (width + 5)));
        y = Game.clamp( y, 0, (Game.HEIGHT - (height + 18)));
    }

    @Override
    public Rectangle getBounds() {
        return new Rectangle( x,  y,  width,  height);
    }

    public int getHealth() {
        return health;
    }

    public int getScore() {
        return score;
    }

    public PlayerControlMapper getControlMapper() {
        return controlMapper;
    }

    public void keyPressed(int index){
        keyPressed[index] = true;
    }

    public void keyReleased(int index){
        keyPressed[index] = false;
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

                if (!coins.isEmpty()) {
                    Coin coin = (Coin) coins.get(0);
                    score += coin.getWeight();
                    this.handler.removeObject(coin);
                }
            }

            if (collect.containsKey(ID.Health)) {
                List<GameObject> healths = collect.get(ID.Health);

                if (!healths.isEmpty()) {
                    Health healthObj = (Health) healths.get(0);
                    health += healthObj.getWeight();
                    if (health > 100) {health = 100;}
                    this.handler.removeObject(healthObj);
                }
            }

            if (collect.containsKey(ID.Enemy)) {
                List<GameObject> enemies = collect.get(ID.Enemy);

                if (!enemies.isEmpty()) {
                    Enemy enemy = (Enemy) enemies.get(0);
                    health -= enemy.getWeight();
                    if (health <= 0) {health = 0;}
                    this.handler.removeObject(enemy);
                }
            }
        }
    }

}
