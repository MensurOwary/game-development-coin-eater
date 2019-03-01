package com.owary.action;

import com.owary.Game;
import com.owary.extra.PauseMenu;
import com.owary.extra.SettingsMenu;
import com.owary.extra.StartMenu;
import com.owary.model.player.Player;
import com.owary.adjustments.State;

import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static com.owary.adjustments.State.*;

public class KeyInput extends KeyAdapter {

    private State previousState = NO_OP;
    private final List<Player> players = new ArrayList<>();

    public KeyInput(){}

    public KeyInput(Player...players) {
        this.players.addAll(Arrays.asList(players));
    }

    public void addPlayer(Player player){
        this.players.add(player);
    }

    @Override
    public void keyPressed(KeyEvent e) {
        int key = e.getKeyCode();

        if (key == KeyEvent.VK_BACK_SPACE){
            System.exit(0);
        }

        switch (Game.getGameState()){
            case MENU:
                executeStartMenuBehavior(key);
                break;
            case GAME:
                executeGameBehavior(key);
                break;
            case PAUSED:
                executePauseMenuBehavior(key);
                break;
            case SETTINGS:
                executeSettingsBehavior(key);
                break;
            default:
                break;
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

    /**
     * Executes menu behavior only for up and down keys
     * @param key
     */
    private void executeStartMenuBehavior(int key){
        switch (key){
            case KeyEvent.VK_UP:
                StartMenu.switchModeVertical(true);
                break;
            case KeyEvent.VK_DOWN:
                StartMenu.switchModeVertical(false);
                break;
            case KeyEvent.VK_ESCAPE:
                System.exit(0);
                break;
            case KeyEvent.VK_ENTER:
                State currentMode = StartMenu.getCurrentMode();
                // for both player modes the Game Mode should be GAME
                if (currentMode == SINGLE_PLAYER || currentMode == TWO_PLAYERS) {
                    Game.setGameState(GAME);
                    Game.addPlayers();
                }else if (currentMode == SETTINGS) {
                    Game.setGameState(SETTINGS);
                    previousState = MENU;
                }else if (currentMode == EXIT) {
                    System.exit(0);
                }
                break;
            default:
                break;
        }
    }

    private void executePauseMenuBehavior(int key){
        switch (key){
            case KeyEvent.VK_UP:
                PauseMenu.switchModeVertical(true);
                break;
            case KeyEvent.VK_DOWN:
                PauseMenu.switchModeVertical(false);
                break;
            case KeyEvent.VK_ESCAPE:
                Game.paused = false;
                Game.setGameState(GAME);
                break;
            case KeyEvent.VK_ENTER:
                State currentMode = PauseMenu.getCurrentMode();
                if (currentMode == NO_OP) {
                    return;
                }
                if (currentMode == EXIT) {
                    System.exit(0);
                }
                Game.setGameState(currentMode);
                if (currentMode == SETTINGS) {
                    previousState = PAUSED;
                }
                if (currentMode == State.GAME){
                    Game.paused = false;
                }
        }
    }

    private void executeGameBehavior(int key){
        switch (key) {
            case KeyEvent.VK_ENTER:
                Game.setGameState(State.MENU);
                Game.paused = !Game.paused;
                break;
            case KeyEvent.VK_ESCAPE:
                Game.paused = true;
                Game.setGameState(State.PAUSED);
                break;
            default:
                // for controlling the players
                for (Player player : players) {
                    PlayerControlMapper controlMapper = player.getControlMapper();
                    int control = controlMapper.getControl(key);
                    if (control != -1){
                        player.keyPressed(control);
                    }
                }
                break;
        }
    }

    private void executeSettingsBehavior(int key){
        switch (key){
            case KeyEvent.VK_UP:
                break;
            case KeyEvent.VK_DOWN:
                break;
            case KeyEvent.VK_ESCAPE:
                Game.setGameState(previousState);
                break;
            case KeyEvent.VK_ENTER:
//                State currentMode = SettingsMenu.getCurrentMode();
                break;
            default:
                break;
        }
    }

}
