package com.mygdx.game.estrategias;

import com.mygdx.game.GameWorld;
import com.mygdx.game.interfaces.PowerUpStrategy;

public class ExtraLifeStrategy implements PowerUpStrategy
{
    @Override
    public void apply(GameWorld game, boolean isBuff)
    {
        if (isBuff) game.setVidas(game.getVidas() + 1);
        else game.setVidas(game.getVidas() - 1);
    }
}
