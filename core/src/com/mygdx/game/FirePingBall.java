package com.mygdx.game;

import com.badlogic.gdx.graphics.Color;

public class FirePingBall extends PowerUp
{
    public FirePingBall(GameWorld game, int x, int y, int width, int height, Color color)
    {
        super(game, x, y, width, height, color, true);
    }

    @Override
    public void apply()
    {
        game.addBall(game.createBall(false, true));
    }
}
