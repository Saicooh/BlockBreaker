package com.mygdx.game.managers;

import com.badlogic.gdx.math.Rectangle;
import com.mygdx.game.GameObject;
import com.mygdx.game.PingBall;


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

    private void setRectangleFromObject(Rectangle rect, GameObject obj)
    {
        if (obj instanceof PingBall)
            rect.set(obj.getX() - (float) obj.getWidth() / 2, obj.getY() - (float) obj.getHeight() / 2, obj.getWidth(), obj.getHeight());

        else rect.set(obj.getX(), obj.getY(), obj.getWidth(), obj.getHeight());
    }
}

