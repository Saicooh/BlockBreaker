package com.mygdx.game;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;

public class DoubleScore extends PowerUp implements Deactivable
{
    private long startTime;
    private final long duration = 10000; // 10 segundos en milisegundos

    public DoubleScore(GameWorld game, int x, int y, int width, int height, Color color)
    {
        super(game, x, y, width, height, color, true);
    }

    @Override
    public void apply()
    {
        activate();

        int multiplicadorActual = game.getMultiplicadorPuntaje();
        game.setMultiplicadorPuntaje(2 * multiplicadorActual);

        update();
        // cambiar dfddf
    }

    @Override
    public void activate()
    {
        startTime = System.currentTimeMillis(); // Registra el tiempo de inicio
    }

    public void deactivate()
    {
        game.setMultiplicadorPuntaje(game.getMultiplicadorPuntaje() / 2);
    }

    @Override
    public void update()
    {
        System.out.println("update");
        System.out.println(System.currentTimeMillis() - startTime);
        if (System.currentTimeMillis() - startTime > duration) deactivate();
    }




}
