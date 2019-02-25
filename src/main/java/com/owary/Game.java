package com.owary;

import com.owary.action.KeyInput;
import com.owary.adjustments.Strategy;
import com.owary.extra.HUD;
import com.owary.handler.Handler;
import com.owary.handler.HandlerImpl;
import com.owary.model.player.Player;
import com.owary.model.types.ID;
import com.owary.utils.Utils;
import com.owary.adjustments.Level;

import java.awt.*;
import java.awt.image.BufferStrategy;
import java.util.Random;
import java.util.logging.Logger;

/**
 * @author Grama
 */
public class Game extends Canvas implements Runnable {

    public static int WIDTH, HEIGHT;  // Your game Canvas dimensions.
    private Thread thread;
    private final Handler handler;
    private final HUD hud;
    private final Random random;
    private Level level;
    private final STATE gameState = STATE.GAME;
    private boolean running = false;

    private final Player playerOne;

    public static void main(String[] args) {
        new Game();
    }

    private Game() {
        final Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();

        WIDTH = screenSize.width;
        HEIGHT = screenSize.height;

        level = Level.ONE;
        handler = new HandlerImpl();
        playerOne = new Player(400, 200, handler, Utils.getArrowControl());
        Player playerTwo = new Player(100, 200, handler, Utils.getArrowControl());

        random = new Random();
        hud = new HUD(playerOne);

        this.addKeyListener(new KeyInput(this, playerOne, playerTwo));

        Window.start(WIDTH, HEIGHT, "The Game", this);

        if (gameState == STATE.GAME) {
            handler.addObject(playerOne);
//            handler.addObject(playerTwo);
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
            Logger.getLogger(Game.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
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
        level = getCurrentLevel(playerOne);

        for (ID key : ID.values()) {
            Strategy.generateGameObject(key, level, handler);
        }

        handler.tick();
        if (gameState == STATE.GAME) {
            hud.tick();
        }
    }

    private Level getCurrentLevel(Player playerOne) {
        int score = playerOne.getScore();
        Level currentLevel = Level.ONE;
        if (score >= 0 && score <= 100){
            currentLevel = Level.ONE;
        }else if(score > 100 && score <= 200){
            currentLevel = Level.TWO;
        }else if(score > 200){
            currentLevel = Level.THREE;
        }
        return currentLevel;
    }

    private void render() {

        BufferStrategy bs = this.getBufferStrategy();
        if (bs == null) {
            this.createBufferStrategy(3);
            return;
        }
        Graphics g = bs.getDrawGraphics();
        g.setColor(Color.BLACK);
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
