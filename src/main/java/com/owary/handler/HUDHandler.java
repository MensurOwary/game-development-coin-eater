package com.owary.handler;

import com.owary.extra.HUD;
import com.owary.model.player.Player;

import java.awt.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import static java.util.stream.Collectors.toList;

public class HUDHandler implements Handler<HUD> {

    private List<HUD> hudList;

    public HUDHandler() {
        this.hudList = new ArrayList<>();
    }

    @Override
    public void tick() {

    }

    @Override
    public void render(Graphics g) {
        hudList
                .stream()
                .filter(Objects::nonNull)
                .collect(toList())
                .forEach(e->e.render(g));
    }

    @Override
    public void addObject(HUD object) {
        hudList.add(object);
    }

    @Override
    public void removeObject(HUD object) {

    }

    @Override
    public List<HUD> getObjects() {
        return null;
    }

    @Override
    public void setPlayer(Player player) {

    }
}
