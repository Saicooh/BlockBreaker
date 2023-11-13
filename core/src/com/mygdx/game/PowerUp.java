package com.mygdx.game;

import com.badlogic.gdx.graphics.Color;

public abstract class PowerUp extends GameObject implements Movable
{
    protected int speedY; // Velocidad vertical del modificador (caída).
    protected GameWorld game;
    protected boolean isBuff;

    public PowerUp(GameWorld game, int x, int y, int width, int height, Color color)
    {
        super(x, y, width, height, color); // Llama al constructor de GameObject.
        this.game = game;
        this.speedY = 5; // Velocidad de caída.
        this.isBuff = true;
    }

    @Override
    public void move(int deltaX, int deltaY)
    {
        // Actualizar la posición del modificador.
        this.x += deltaX;
        this.y += deltaY;
        // Asumiendo que deseas mantener actualizados los campos x e y de GameObject.
        setX(this.x);
        setY(this.y);
    }

    public void mover()
    {
        move(0, -speedY); // Mover hacia abajo a la velocidad speedY.
    }

    // Método abstracto para aplicar el efecto del modificador.
    public abstract void apply();
}
