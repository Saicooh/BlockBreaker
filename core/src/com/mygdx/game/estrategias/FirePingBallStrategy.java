package com.mygdx.game.estrategias;

import com.mygdx.game.GameWorld;
import com.mygdx.game.interfaces.PowerUpStrategy;

public class FirePingBallStrategy implements PowerUpStrategy
{
    @Override
    public void apply(GameWorld game, boolean isBuff) { game.addBall(game.createBall(false, true)); }
}
