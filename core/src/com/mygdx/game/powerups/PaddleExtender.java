/*package com.mygdx.game.powerups;

import com.badlogic.gdx.graphics.Color;
import com.mygdx.game.GameWorld;
import com.mygdx.game.TimedPowerUp;

public class PaddleExtender extends TimedPowerUp
{
    public PaddleExtender(GameWorld game, int x, int y, int width, int height, Color color)
    {
        super(game, x, y, width, height, color, 10000, true);
    }

    @Override
    public void apply()
    {
        startTimer();
        game.setPaddleWidth(game.getPaddleWidth() + 50);
    }

    @Override
    public void deactivate() { game.setPaddleWidth(game.getPaddleWidth() - 50); }
}

// pelota explota

// paddle pequeño

// frenar paddle

// invertir controles paddle/*/