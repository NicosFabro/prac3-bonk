package com.ncs.plataformes.characters;

import com.ncs.plataformes.GameEngine;

public class Box extends Character {

    public Box(GameEngine gameEngine, int x, int y) {
        super(gameEngine, x, y);
    }

    private static final int[][] ANIMATIONS = new int[][]{
            new int[] { 38 }
    };

    @Override
    void updatePhysics(int delta) {

    }

    @Override
    void updateCollisionRect() {
        collisionRect.set(x, y, x + 16, y + 16);
    }

    @Override
    int[][] getAnimations() {
        return ANIMATIONS;
    }
}
