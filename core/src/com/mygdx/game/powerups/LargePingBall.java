/*ackage com.mygdx.game.powerups;

import com.badlogic.gdx.graphics.Color;
import com.mygdx.game.GameWorld;
import com.mygdx.game.PingBall;
import com.mygdx.game.PowerUp;

public class LargePingBall extends PowerUp
{
    public LargePingBall(GameWorld game, int x, int y, int width, int height, Color color)
    {
        super(game, x, y, width, height, color, true);
    }

    @Override
    public void apply()
    {
        PingBall ball = game.createBall(false, false);

        ball.setSize(getWidth() + 20);

        game.addBall(ball);
    }
}
/*/