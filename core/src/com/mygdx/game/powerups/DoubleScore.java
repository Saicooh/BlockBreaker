/*package com.mygdx.game.powerups;

import com.badlogic.gdx.graphics.Color;
import com.mygdx.game.GameWorld;
import com.mygdx.game.TimedPowerUp;

public class DoubleScore extends TimedPowerUp
{
    public DoubleScore(GameWorld game, int x, int y, int width, int height, Color color, boolean isBuff)
    {
        super(game, x, y, width, height, color, 10000, isBuff);
    }

    @Override
    public void deactivate()
    {
        if (isBuff) game.setMultiplicadorPuntaje(game.getMultiplicadorPuntaje() / 2);
        else game.setMultiplicadorPuntaje(game.getMultiplicadorPuntaje() * 2);
    }

    @Override
    public void apply()
    {
        startTimer();
        int multiplicadorActual = game.getMultiplicadorPuntaje();

        if (isBuff) game.setMultiplicadorPuntaje(2 * multiplicadorActual);
        else game.setMultiplicadorPuntaje(multiplicadorActual / 2);
    }
}
*/