package com.mygdx.game.estrategias;

import com.mygdx.game.GameWorld;
import com.mygdx.game.interfaces.TimedPowerUpStrategy;

public class DoubleScoreStrategy implements TimedPowerUpStrategy
{
    @Override
    public void apply(GameWorld game, boolean isBuff)
    {
        int multiplicadorActual = game.getMultiplicadorPuntaje();

        if (isBuff) game.setMultiplicadorPuntaje(2 * multiplicadorActual);
        else game.setMultiplicadorPuntaje(multiplicadorActual / 2);
    }

    @Override
    public void deactivate(GameWorld game, boolean isBuff)
    {
        if (isBuff) game.setMultiplicadorPuntaje(game.getMultiplicadorPuntaje() / 2);
        else game.setMultiplicadorPuntaje(game.getMultiplicadorPuntaje() * 2);
    }
}
