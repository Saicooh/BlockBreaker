package com.mygdx.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.mygdx.game.interfaces.Movable;

public class Paddle extends GameObject implements Movable
{
    private static final int VELOCIDAD_PADDLE = 12;
    public Paddle(int x, int y, int width, int height) { super(x, y, width, height, Color.BLUE); }

    public void draw(ShapeRenderer shape)
    {
        shape.setColor(color);
        shape.rect(x, y, width, height);
    }

    public void move(int dx, int dy)
    {
        int newX = x + dx;

        if (newX >= 0 && newX + width <= Gdx.graphics.getWidth()) x = newX;

        y += dy;
    }

    public void handleInput()
    {
        if (Gdx.input.isKeyPressed(Input.Keys.LEFT)) move(-VELOCIDAD_PADDLE, 0);
        if (Gdx.input.isKeyPressed(Input.Keys.RIGHT)) move(VELOCIDAD_PADDLE, 0);
    }
}
