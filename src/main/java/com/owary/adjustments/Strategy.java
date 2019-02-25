package com.owary.adjustments;

import com.owary.handler.Handler;
import com.owary.model.GameObject;
import com.owary.model.types.ID;

import java.util.Collections;
import java.util.List;
import java.util.Map;

import static com.owary.utils.Utils.getRandomGameObject;
import static java.util.stream.Collectors.groupingBy;

public class Strategy {

    public static void generateGameObject(ID type, Level level, Handler handler){
        List<GameObject> objects = handler.getObjects();
        Map<ID, List<GameObject>> collect = objects
                .stream()
                .collect(groupingBy(GameObject::getId));

        int currentCount = collect.getOrDefault(type, Collections.emptyList()).size();

        int enemyCount = 0;
        int coinCount = 0;
        int healthCount = 0;

        if (level.equals(Level.ONE)){
            enemyCount = 1;
            coinCount = 3;
            healthCount = 1;
        }else if(level.equals(Level.TWO)){
            enemyCount = 3;
            coinCount = 2;
            healthCount = 2;
        }else if(level.equals(Level.THREE)){
            enemyCount = 5;
            coinCount = 1;
            healthCount = 2;
        }

        int counter;
        switch (type) {
            case Coin:
                counter = coinCount;
                break;
            case Health:
                counter = healthCount;
                break;
            case Enemy:
                counter = enemyCount;
                break;
            default:
                counter = 0;
        }
        for (int i = 0; i < counter-currentCount; i++) {
            handler.addObject(getRandomGameObject(type, level));
        }


    }

}
