package com.owary.action;

import com.owary.Game;
import com.owary.model.ID;
import com.owary.model.Player;

import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

/**
 * Simplest Keyboard representation which takes keyboard actions and sending integer type information
 * to the referenced Player Objects public keyPressed array.
 *
 * @author Sayid Akhundov
 */
public class KeyInput extends KeyAdapter {
    private Game game;

    private Player player;

    public KeyInput(Game game, Player player) {
        this.player = player;
        this.game = game;
    }


    @Override
    public void keyPressed(KeyEvent e) {
        int key = e.getKeyCode();
        if (key == KeyEvent.VK_ESCAPE) System.exit(0);


        if (player.getId() == ID.Player) {
            if (key == KeyEvent.VK_W) {
                player.getKeyPressed()[3] = true;
            }
            if (key == KeyEvent.VK_A) {
                player.getKeyPressed()[1] = true;
            }
            if (key == KeyEvent.VK_S) {
                player.getKeyPressed()[4] = true;
            }
            if (key == KeyEvent.VK_D) {
                player.getKeyPressed()[2] = true;
            }

            if (key == KeyEvent.VK_UP) {
                player.getKeyPressed()[3] = true;
            }
            if (key == KeyEvent.VK_LEFT) {
                player.getKeyPressed()[1] = true;
            }
            if (key == KeyEvent.VK_DOWN) {
                player.getKeyPressed()[4] = true;
            }
            if (key == KeyEvent.VK_RIGHT) {
                player.getKeyPressed()[2] = true;
            }

            if (key == KeyEvent.VK_SPACE) {

            }

        }
    }

    @Override
    public void keyReleased(KeyEvent e) {
        int key = e.getKeyCode();

        if (player.getId() == ID.Player) {
            if (key == KeyEvent.VK_W) player.getKeyPressed()[3] = false;
            if (key == KeyEvent.VK_A) player.getKeyPressed()[1] = false;
            if (key == KeyEvent.VK_S) player.getKeyPressed()[4] = false;
            if (key == KeyEvent.VK_D) player.getKeyPressed()[2] = false;
            if (key == KeyEvent.VK_UP) player.getKeyPressed()[3] = false;
            if (key == KeyEvent.VK_LEFT) player.getKeyPressed()[1] = false;
            if (key == KeyEvent.VK_DOWN) player.getKeyPressed()[4] = false;
            if (key == KeyEvent.VK_RIGHT) player.getKeyPressed()[2] = false;
        }

    }

}
