package com.mygdx.game;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;

public class ExtraLife extends PowerUp
{
    public ExtraLife(GameWorld game, int x, int y, int width, int height, Color color)
    {
        super(game, x, y, width, height, color);
    }

    @Override
    public void apply() { game.setVidas(game.getVidas() + 1); }

    @Override
    public void draw(ShapeRenderer shape)
    {
        shape.setColor(color);
        shape.circle(x, y, width);
    }
}
