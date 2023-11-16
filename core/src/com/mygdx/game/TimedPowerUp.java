package com.mygdx.game;

import com.badlogic.gdx.graphics.Color;

import java.util.Timer;
import java.util.TimerTask;

public abstract class TimedPowerUp extends PowerUp
{
    protected Timer timer;

    protected long duration;

    public TimedPowerUp(GameWorld game, int x, int y, int width, int height, Color color, long duration, boolean isBuff)
    {
        super(game, x, y, width, height, color, isBuff);
        this.duration = duration;
    }

    protected void startTimer()
    {
        cancelTimer(); // Cancelar cualquier temporizador existente

        timer = new Timer();

        TimerTask task = new TimerTask()
        {
            @Override
            public void run() { deactivate(); }
        };

        timer.schedule(task, duration);
    }

    public void cancelTimer()
    {
        if (timer != null) timer.cancel();
    }

    public abstract void deactivate();
}

