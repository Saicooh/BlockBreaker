package com.mygdx.game;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import java.util.Random;

public class Block extends GameObject
{
    private boolean destroyed = false;

    public Block(int x, int y, int width, int height)
    {
        super(x, y, width, height, randomColor());  // Pasamos un color aleatorio al constructor de GameObject
    }

    private static Color randomColor()
    {
        Random rand = new Random();
        float r = rand.nextFloat();
        float g = rand.nextFloat();
        float b = rand.nextFloat();
        return new Color(r, g, b, 1);
    }

    public void draw(ShapeRenderer shape)
    {
        if (!isDestroyed())
        {
            shape.setColor(color);  // Usamos el color almacenado en lugar de un color fijo
            shape.rect(getX(), getY(), getWidth(), getHeight());
        }
    }

    public boolean isDestroyed() { return destroyed; }

    public void setDestroyed(boolean destroyed) { this.destroyed = destroyed; }
}
