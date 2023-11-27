package com.mygdx.game.interfaces;

import com.mygdx.game.GameWorld;

public interface PowerUpStrategy
{
    void apply(GameWorld game, boolean isBuff);
}