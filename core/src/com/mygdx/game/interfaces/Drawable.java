package com.mygdx.game.interfaces;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;

public interface Drawable
{
    void draw(ShapeRenderer shape);
    void drawSprite(SpriteBatch batch);
}
