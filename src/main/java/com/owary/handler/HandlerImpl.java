package com.owary.handler;

import com.owary.model.GameObject;
import com.owary.model.enemy.Terence;
import com.owary.model.player.Player;

import java.awt.*;
import java.io.Serializable;
import java.util.*;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Our best friend in Game which will handle updating and rendering all GameObjects existing in the game scene and bunch of other functions.
 *
 * @author Sayid Akhundov
 */
public class HandlerImpl implements Handler, Serializable {
    private final List<GameObject> objects;
    private Player player;

    public HandlerImpl(){
        this.objects = new LinkedList<>();
    }

    @Override
    public void setPlayer(Player player) {
        this.player = player;
    }

    @Override
    public void tick() {
        objects
                .stream()
                .filter(Objects::nonNull)
                .peek(this::modifyTowardsMovingEnemy)
                .collect(Collectors.toList())
                .forEach(GameObject::tick);
    }

    @Override
    public void render(Graphics g) {
        objects
                .stream()
                .filter(Objects::nonNull)
                .collect(Collectors.toList())
                .forEach(e->e.render(g));
    }

    @Override
    public void addObject(GameObject object) {
        if (object != null)
            this.objects.add(object);
    }

    @Override
    public void removeObject(GameObject object) {
        if (object != null)
            this.objects.removeIf(next -> next == object);
    }

    @Override
    public List<GameObject> getObjects() {
        return objects;
    }

    private void modifyTowardsMovingEnemy (GameObject object) {
        if (object instanceof Terence){
            int[] velocities = calculateVelocity(player, (Terence) object);
            object.setVelX(velocities[0]);
            object.setVelY(velocities[1]);
        }
    }

    private int[] calculateVelocity(Player player, Terence terence){
        int playerX = player.getX();
        int playerY = player.getY();

        int terenceX = terence.getX();
        int terenceY = terence.getY();

        int velX = (playerX - terenceX) / 100;
        int velY = (playerY - terenceY) / 100;

        velX = getCoordinate(playerX, terenceX, velX);
        velY = getCoordinate(playerY, terenceY, velY);

        return new int[] {velX, velY};
    }

    private int getCoordinate(int pc, int ec, int coordinate){
        if (pc > ec) {
            coordinate = coordinate == 0 ? 1 : coordinate;
        }else if(pc == ec) {
            coordinate = 0;
        }else{
            coordinate = coordinate == 0 ? -1 : coordinate;
        }
        return coordinate;
    }



}
