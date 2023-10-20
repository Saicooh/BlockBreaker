package com.mygdx.game;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;

import java.util.Random;

import com.badlogic.gdx.graphics.Color;

public class Block extends GameObject
{
    private boolean destroyed = false;
    
    public Block(int x, int y, int width, int height)
    {
        super(x, y, width, height, Color.RED);
    }
    public void draw(ShapeRenderer shape)
    {
    	if (!destroyed)
    	{
    		shape.setColor(color);
    		shape.rect(x, y, width, height);
    	}
    }

    public boolean isDestroyed() { return destroyed; }

    public void setDestroyed(boolean destroyed) { this.destroyed = destroyed; }
}
