package com.mygdx.game.interfaces;

import com.mygdx.game.GameWorld;

public interface TimedPowerUpStrategy extends PowerUpStrategy
{
    void deactivate(GameWorld game, boolean isBuff);
}
