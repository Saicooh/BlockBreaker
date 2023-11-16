package com.mygdx.game;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;

public abstract class PowerUp extends GameObject implements Movable
{
    protected int speedY; // Velocidad vertical del modificador (caída).
    protected GameWorld game;
    protected boolean isBuff;

    public PowerUp(GameWorld game, int x, int y, int width, int height, Color color, boolean isBuff)
    {
        super(x, y, width, height, color); // Llama al constructor de GameObject.
        this.game = game;
        this.speedY = 3; // Velocidad de caída.

        this.isBuff = isBuff;
    }

    @Override
    public void move(int deltaX, int deltaY)
    {
        this.x += deltaX;
        this.y += deltaY;

        setX(this.x);
        setY(this.y);
    }

    @Override
    public void draw(ShapeRenderer shape)
    {
        shape.setColor(color);
        shape.circle(x, y, width);
    }

    public void mover() { move(0, -speedY); } // Mover hacia abajo a la velocidad speedY.

    // Método abstracto para aplicar el efecto del modificador.
    public abstract void apply();
}
