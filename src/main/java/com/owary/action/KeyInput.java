package com.owary.action;

import com.owary.Game;
import com.owary.model.ID;
import com.owary.model.Player;

import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.util.Arrays;

/**
 * Simplest Keyboard representation which takes keyboard actions and sending integer type information
 * to the referenced Player Objects public keyPressed array.
 *
 * @author Sayid Akhundov
 */
public class KeyInput extends KeyAdapter {
    private Game game;

    private Player[] players;

    public KeyInput(Game game, Player...players) {
        this.players = players;
        this.game = game;
    }


    @Override
    public void keyPressed(KeyEvent e) {
        int key = e.getKeyCode();
        if (key == KeyEvent.VK_ESCAPE) System.exit(0);

        for (Player player : players) {
            PlayerControlMapper controlMapper = player.getControlMapper();
            int control = controlMapper.getControl(key);
            if (control != -1){
                player.keyPressed(control);
            }
        }
    }

    @Override
    public void keyReleased(KeyEvent e) {
        int key = e.getKeyCode();

        for (Player player : players) {
            PlayerControlMapper controlMapper = player.getControlMapper();
            int control = controlMapper.getControl(key);
            if (control != -1){
                player.keyReleased(control);
            }
        }

    }

}
