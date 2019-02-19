package com.owary;

import com.owary.action.KeyInput;
import com.owary.extra.HUD;
import com.owary.handler.Handler;
import com.owary.handler.HandlerImpl;
import com.owary.model.Coin;
import com.owary.model.Enemy;
import com.owary.model.ID;
import com.owary.model.Player;

import java.awt.*;
import java.awt.image.BufferStrategy;
import java.util.Random;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * @author Grama
 */
public class Game extends Canvas implements Runnable {

    public static final int WIDTH = 1200, HEIGHT = WIDTH / 2;  // Your game Canvas dimensions.
    private Thread thread;
    private Handler handler;
    private HUD hud;
    private STATE gameState = STATE.GAME;
    private boolean running = false;

    public Game() {
        Random rand = new Random();
        Player player = new Player(400, 200, ID.Player, handler);

        handler = new HandlerImpl();
        hud = new HUD(player);

        this.addKeyListener(new KeyInput(this, player));

        Window.start(WIDTH, HEIGHT, "GD by Sayid Akhundov - Our First game ;)", this);

        if (gameState == STATE.GAME) {
            handler.addObject(player);
            handler.addObject(new Enemy(rand.nextInt(WIDTH - 20), rand.nextInt(HEIGHT - 40), ID.Enemy, handler, Color.blue));
            handler.addObject(new Enemy(rand.nextInt(WIDTH - 20), rand.nextInt(HEIGHT - 40), ID.Enemy, handler, Color.YELLOW));
            handler.addObject(new Coin(500, 250, ID.Coin));
        }

    }

    public static void main(String[] args) {
        new Game();
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

    public synchronized void stop() {
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
        handler.tick();
        if (gameState == STATE.GAME) {
            hud.tick();
        }
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
