package com.mygdx.game;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.mygdx.game.interfaces.Movable;
import com.mygdx.game.interfaces.PowerUpStrategy;

public class PowerUp extends GameObject implements Movable
{
    protected int speedY; // Velocidad vertical del modificador (caída).
    protected GameWorld game;
    protected boolean isBuff;
    protected Sprite sprite;

    protected PowerUpStrategy strategy;

    public PowerUp(GameWorld game, int x, int y, int width, int height, Color color, boolean isBuff, PowerUpStrategy strategy, Sprite sprite)
    {
        super(x, y, width, height, color); // Llama al constructor de GameObject.
        this.game = game;
        this.speedY = 3; // Velocidad de caída.

        this.isBuff = isBuff;
        this.strategy = strategy;
        this.sprite = sprite;
    }

    @Override
    public void move(int deltaX, int deltaY)
    {
        this.x += deltaX;
        this.y += deltaY;
    }

    @Override
    public void draw(ShapeRenderer shape)
    {
        shape.setColor(color);
        shape.rect(x, y, width, height);
    }

    @Override
    public void drawSprite(SpriteBatch batch)
    {
        float xNew = x + x * 0.09f;

        sprite.setPosition(xNew, y);
        sprite.draw(batch);
    }

    public void mover() { move(0, -speedY); } // Mover hacia abajo a la velocidad speedY.

    public void apply() { strategy.apply(game, isBuff); }
}
