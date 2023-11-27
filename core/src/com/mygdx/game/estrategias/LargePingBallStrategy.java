package com.mygdx.game.estrategias;

import com.mygdx.game.GameWorld;
import com.mygdx.game.PingBall;
import com.mygdx.game.interfaces.PowerUpStrategy;

public class LargePingBallStrategy implements PowerUpStrategy
{
    @Override
    public void apply(GameWorld game, boolean isBuff)
    {
        PingBall ball = game.createBall(false, false);

        ball.setSize(ball.getWidth() + 20);

        game.addBall(ball);
    }
}
