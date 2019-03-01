package com.owary;

import com.owary.action.KeyInput;
import com.owary.adjustments.Strategy;
import com.owary.extra.HUD;
import com.owary.extra.StartMenu;
import com.owary.handler.HUDHandler;
import com.owary.handler.Handler;
import com.owary.handler.GameObjectHandler;
import com.owary.model.GameObject;
import com.owary.model.player.Player;
import com.owary.model.types.ID;
import com.owary.model.types.State;
import com.owary.utils.Utils;
import com.owary.adjustments.Level;

import java.awt.*;
import java.awt.image.BufferStrategy;
import java.util.logging.Logger;

import static com.owary.model.types.State.GAME;
import static com.owary.model.types.State.MENU;

/**
 * @author Grama
 */
public class Game extends Canvas implements Runnable {

    public static int WIDTH, HEIGHT;  // Your game Canvas dimensions.
    private Thread thread;
    private static Handler<GameObject> gameObjectHandler;
    private static Handler<HUD> hudHandler;

    private static HUD hudPlayerOne;
    private static HUD hudPlayerTwo;

    private static StartMenu menu;
    private Level level;
    private static State gameState = MENU;
    private boolean running = false;

    public volatile static boolean paused = false;

    private static Player playerOne;
    private static Player playerTwo;

    public static void main(String[] args) {
        new Game();
    }

    private Game() {
        final Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();

        WIDTH = screenSize.width;
        HEIGHT = screenSize.height;

        HUD.screenWidth = WIDTH;
        HUD.screenHeight = HEIGHT;

        level = Level.ONE;
        gameObjectHandler = new GameObjectHandler();
        hudHandler = new HUDHandler();

        playerOne = new Player(400, 200, gameObjectHandler, Utils.getArrowControl());

        playerTwo = new Player(800, 200, gameObjectHandler, Utils.getWASDControl());
        playerTwo.setCharacterImage("assets/images/players/the_blues.png");

        hudPlayerOne = new HUD(playerOne);

        menu = new StartMenu(screenSize);
        this.addKeyListener(new KeyInput(this, playerOne, playerTwo));

        Window.start(WIDTH, HEIGHT, "The Game", this);

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
            if (paused) {
                lastTime = System.nanoTime();
                render();
                continue;
            }
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
        if (gameState == GAME) {
            hudHandler.tick();
            level = getCurrentLevel(playerOne);
            for (ID key : ID.values()) {
                Strategy.generateGameObject(key, level, gameObjectHandler);
            }
            gameObjectHandler.tick();
        }
    }

    private Level getCurrentLevel(Player playerOne) {
        int score = playerOne.getScore();
        Level currentLevel = Level.ONE;
        if (score >= 0 && score <= 100){
            currentLevel = Level.ONE;
        }else if(score > 100 && score <= 200){
            currentLevel = Level.TWO;
        }else if(score > 200 && score <= 300){
            currentLevel = Level.THREE;
        }else if(score > 300){
            currentLevel = Level.FOUR;
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


        if (gameState == GAME) {
            g.setColor(Color.PINK);
            g.fillRect(0, 0, WIDTH, HEIGHT);
            hudHandler.render(g);
            gameObjectHandler.render(g);
        }else if (gameState == MENU) {
            menu.render(g);
        }
        g.dispose();
        bs.show();
    }

    public static void setGameState(State state) {
        gameState = state;
    }

    public static State getGameState() {
        return gameState;
    }

    public static void addPlayers(){
        if (gameState == GAME) {
            gameObjectHandler.addObject(playerOne);
            hudHandler.addObject(hudPlayerOne);
            if (!StartMenu.isSingleMode()){
                hudPlayerTwo = new HUD(playerTwo, false);
                gameObjectHandler.addObject(playerTwo);
                hudHandler.addObject(hudPlayerTwo);
            }
        }
    }
}
