package com.mygdx.game;

import com.badlogic.gdx.graphics.Color;
import com.mygdx.game.interfaces.PowerUpStrategy;
import com.mygdx.game.interfaces.TimedPowerUpStrategy;

import java.util.Timer;
import java.util.TimerTask;

public class TimedPowerUp extends PowerUp
{
    protected Timer timer;

    protected long duration;

    protected TimedPowerUpStrategy timedStrategy;

    public TimedPowerUp(GameWorld game, int x, int y, int width, int height, Color color, long duration, boolean isBuff, TimedPowerUpStrategy strategy)
    {
        super(game, x, y, width, height, color, isBuff, strategy);
        this.duration = duration;
        this.timedStrategy = strategy;
    }

    @Override
    public void apply()
    {
        startTimer();
        timedStrategy.apply(game, isBuff);
    }

    protected void startTimer()
    {
        //cancelTimer(); // Cancelar cualquier temporizador existente

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

    public void deactivate() { timedStrategy.deactivate(game, isBuff); }
}

