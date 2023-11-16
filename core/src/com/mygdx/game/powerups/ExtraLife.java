package com.mygdx.game.powerups;

import com.badlogic.gdx.graphics.Color;
import com.mygdx.game.GameWorld;
import com.mygdx.game.PowerUp;

public class ExtraLife extends PowerUp
{
    public ExtraLife(GameWorld game, int x, int y, int width, int height, Color color)
    {
        super(game, x, y, width, height, color, true);
    }

    @Override
    public void apply() { game.setVidas(game.getVidas() + 1); }

    // pelota fuego
    // paddle grande
    // pelotas multiples
    // puntaje x2
}
