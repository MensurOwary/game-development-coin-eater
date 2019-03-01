package com.owary.action;

import com.owary.Game;
import com.owary.extra.StartMenu;
import com.owary.model.player.Player;
import com.owary.model.types.State;

import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class KeyInput extends KeyAdapter {
    private Game game;

    private final List<Player> players = new ArrayList<>();

    public KeyInput(){}

    public KeyInput(Game game, Player...players) {
        this.game = game;
        this.players.addAll(Arrays.asList(players));
    }

    public void addPlayer(Player player){
        this.players.add(player);
    }


    @Override
    public void keyPressed(KeyEvent e) {
        int key = e.getKeyCode();

        if (Game.getGameState() == State.MENU){
            if (key == KeyEvent.VK_UP || key == KeyEvent.VK_DOWN){
                StartMenu.switchMode();
            }
        }


        if (key == KeyEvent.VK_ESCAPE){
            // if in options -> return
            if (Game.getGameState() == State.MENU) {
                Game.paused = !Game.paused;
                Game.setGameState(State.GAME);
            }else {
                System.exit(0);
            }
        }
        if (key == KeyEvent.VK_ENTER) {
            // Options
            if (Game.getGameState() == State.GAME) {
                System.out.println("");
                Game.setGameState(State.MENU);
                Game.paused = !Game.paused;
            }else if(Game.getGameState() == State.MENU){
                Game.setGameState(State.GAME);
                Game.addPlayers();
            }
        }
        if (key == KeyEvent.VK_SPACE){
            // Go to main menu
        }

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
