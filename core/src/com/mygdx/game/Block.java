package com.mygdx.game;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;

import java.util.Random;

public class Block extends GameObject
{
    private final boolean destroyed;

    // Constructor privado para usar solo con el Builder
    private Block(Builder builder)
    {
        super(builder.x, builder.y, builder.width, builder.height, builder.color);
        this.destroyed = builder.destroyed;
    }

    public static class Builder
    {
        private final int x, y, width, height;
        private final Color color;
        private final boolean destroyed;

        public Builder(int x, int y, int width, int height)
        {
            this.x = x;
            this.y = y;
            this.width = width;
            this.height = height;
            this.color = randomColor(); // Valor por defecto
            this.destroyed = false;     // Valor por defecto
        }

        public Block build() { return new Block(this); }

        private static Color randomColor()
        {
            Random rand = new Random();
            float r = rand.nextFloat();
            float g = rand.nextFloat();
            float b = rand.nextFloat();
            return new Color(r, g, b, 1);
        }
    }

    @Override
    public void draw(ShapeRenderer shape)
    {
        if (!isDestroyed())
        {
            shape.setColor(color);
            shape.rect(x, y, width, height);
        }
    }

    public boolean isDestroyed() { return destroyed; }
}
