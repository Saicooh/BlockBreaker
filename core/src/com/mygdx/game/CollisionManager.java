package com.mygdx.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.math.Rectangle;

public class CollisionManager
{

    private final Rectangle rect1 = new Rectangle();
    private final Rectangle rect2 = new Rectangle();

    public boolean checkCollision(GameObject obj1, GameObject obj2)
    {
        setRectangleFromObject(rect1, obj1);
        setRectangleFromObject(rect2, obj2);
        return rect1.overlaps(rect2);
    }

    public void handleBallBlockCollision(PingBall ball, Block block)
    {
        if (checkCollision(ball, block))
        {
            ball.reverseYDirection();
            block.setDestroyed(true);
        }
    }

    public boolean handleBallPaddleCollision(PingBall ball, Paddle paddle)
    {
        if (checkCollision(ball, paddle))
        {
            ball.reverseYDirection();
            return true;
        }
        return false;
    }

    private void setRectangleFromObject(Rectangle rect, GameObject obj)
    {
        if (obj instanceof PingBall)
            rect.set(obj.getX() - (float) obj.getWidth() / 2, obj.getY() - (float) obj.getHeight() / 2, obj.getWidth(), obj.getHeight());

        else rect.set(obj.getX(), obj.getY(), obj.getWidth(), obj.getHeight());
    }

    public void handleBallScreenCollision(PingBall ball)
    {
        if (ball.getX() - ball.getWidth() / 2 < 0 || ball.getX() + ball.getWidth() / 2 > Gdx.graphics.getWidth())
            ball.reverseXDirection();

        if (ball.getY() + ball.getWidth() / 2 > Gdx.graphics.getHeight())
            ball.reverseYDirection();
    }
}

