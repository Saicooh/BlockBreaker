package com.mygdx.game;

import com.badlogic.gdx.graphics.Color;

public class DoubleScore extends TimedPowerUp
{

    public DoubleScore(GameWorld game, int x, int y, int width, int height, Color color)
    {
        super(game, x, y, width, height, color, 10000, true);
    }

    @Override
    public void deactivate()
    {
        game.setMultiplicadorPuntaje(game.getMultiplicadorPuntaje() / 2);
        System.out.println("--------PODER DESACTIVADO-------------");
    }

    @Override
    public void apply()
    {
        startTimer();

        System.out.println("--------------PODER ACTIVADO------------");

        int multiplicadorActual = game.getMultiplicadorPuntaje();
        game.setMultiplicadorPuntaje(2 * multiplicadorActual);
    }
}
