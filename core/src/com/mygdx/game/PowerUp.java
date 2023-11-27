package com.mygdx.game;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.mygdx.game.interfaces.Movable;
import com.mygdx.game.interfaces.PowerUpStrategy;

public class PowerUp extends GameObject implements Movable
{
    protected int speedY; // Velocidad vertical del modificador (caída).
    protected GameWorld game;
    protected boolean isBuff;

    protected PowerUpStrategy strategy;

    public PowerUp(GameWorld game, int x, int y, int width, int height, Color color, boolean isBuff, PowerUpStrategy strategy)
    {
        super(x, y, width, height, color); // Llama al constructor de GameObject.
        this.game = game;
        this.speedY = 3; // Velocidad de caída.

        this.isBuff = isBuff;
        this.strategy = strategy;
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
        if (!isBuff) color = Color.RED;

        shape.setColor(color);
        shape.circle(x, y, width);
    }

    public void mover() { move(0, -speedY); } // Mover hacia abajo a la velocidad speedY.

    public void apply() { strategy.apply(game, isBuff); }
}
