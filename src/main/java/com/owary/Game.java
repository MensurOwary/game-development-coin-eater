package com.owary;

import com.owary.action.KeyInput;
import com.owary.extra.HUD;
import com.owary.handler.Handler;
import com.owary.handler.HandlerImpl;
import com.owary.model.*;
import com.owary.utils.Utils;

import java.awt.*;
import java.awt.image.BufferStrategy;
import java.util.List;
import java.util.Map;
import java.util.Random;
import java.util.logging.Level;
import java.util.logging.Logger;

import static java.util.stream.Collectors.groupingBy;

/**
 * @author Grama
 */
public class Game extends Canvas implements Runnable {

    public static final int WIDTH = 1024, HEIGHT = 768;  // Your game Canvas dimensions.
    private Thread thread;
    private Handler handler;
    private HUD hud;
    private Random random;
    private STATE gameState = STATE.GAME;
    private boolean running = false;

    public static void main(String[] args) {
        new Game();
    }

    private Game() {
        handler = new HandlerImpl();
        Player playerOne = new Player(400, 200, handler, Utils.getWASDControl());
        Player playerTwo = new Player(100, 200, handler, Utils.getArrowControl());

        random = new Random();
        hud = new HUD(playerOne);

        this.addKeyListener(new KeyInput(this, playerOne, playerTwo));

        Window.start(WIDTH, HEIGHT, "GD by Sayid Akhundov - Our First game ;)", this);

        if (gameState == STATE.GAME) {
            handler.addObject(playerOne);
            handler.addObject(playerTwo);
//            handler.addObject(getRandomGameObject(ID.Coin));
//            handler.addObject(getRandomGameObject(ID.Enemy));
        }

    }


    public static int clamp(int number, int min, int max) {
        if (number >= max) return max;
        else if (number <= min) return min;
        return number;
    }

    public synchronized void start() {
        thread = new Thread(this);
        thread.start();
        running = true;
    }

    private synchronized void stop() {
        try {
            thread.join();
            running = false;
        } catch (InterruptedException ex) {
            Logger.getLogger(Game.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    @Override
    public void run() {
        this.requestFocus();
        long lastTime = System.nanoTime();
        double amountOfTicks = 60.0;
        double ns = 1000000000 / amountOfTicks;
        double delta = 0;

        long timer = System.currentTimeMillis();
        int frames = 0;

        while (running) {
            long now = System.nanoTime();
            delta += (now - lastTime) / ns;
            lastTime = now;
            while (delta >= 1) {
                tick();
                delta--;
            }
            if (running) {
                render();
                frames++;
            }
            if ((System.currentTimeMillis() - timer) > 1000) {
                timer += 1000;
                System.out.println("FPS :" + frames);
                frames = 0;
            }
        }
        stop();
    }

    private void tick() {
        // get current objects
        List<GameObject> objects = handler.getObjects();
        // get objects based on their IDs
        Map<ID, List<GameObject>> collect = objects
                .stream()
                .collect(groupingBy(GameObject::getId));

        // for each ID category, if there's no object in the field, generate one
        for (ID key : ID.values()) {
            if (!collect.containsKey(key) || (collect.containsKey(key) && collect.get(key).isEmpty())){
                handler.addObject(getRandomGameObject(key));
            }
        }

        handler.tick();
        if (gameState == STATE.GAME) {
            hud.tick();
        }
    }

    private GameObject getRandomGameObject(ID key) {
        switch (key){
            case Coin:
                return new Coin(random.nextInt(WIDTH - 20), random.nextInt(HEIGHT - 40));
            case Enemy:
                return new Enemy(random.nextInt(WIDTH - 20), random.nextInt(HEIGHT - 40));
        }
        return null;
    }

    private void render() {

        BufferStrategy bs = this.getBufferStrategy();
        if (bs == null) {
            this.createBufferStrategy(3);
            return;
        }
        Graphics g = bs.getDrawGraphics();
        g.setColor(Color.PINK);
        g.fillRect(0, 0, WIDTH, HEIGHT);

        handler.render(g);

        if (gameState == STATE.GAME) {

            hud.render(g);
        }
        g.dispose();
        bs.show();
    }

    /*
     *You will not use this enum in our project, however its very common for game Menues .
     */
    private enum STATE {
        MENU,
        GAME,
        HELP,
        OPTIONS
    }


}
