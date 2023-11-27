package com.mygdx.game.estrategias;

import com.mygdx.game.GameWorld;
import com.mygdx.game.interfaces.TimedPowerUpStrategy;

public class PaddleExtenderStrategy implements TimedPowerUpStrategy
{
    @Override
    public void apply(GameWorld game, boolean isBuff)
    {
        game.setPaddleWidth(game.getPaddleWidth() + 50);
    }

    @Override
    public void deactivate(GameWorld game, boolean isBuff) { game.setPaddleWidth(game.getPaddleWidth() - 50); }
}
