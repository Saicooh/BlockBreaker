package com.mygdx.game;

import com.badlogic.gdx.graphics.Color;

import java.util.Iterator;
import java.util.List;
import java.util.ArrayList;

public class PowerUpManager
{
    private final List<PowerUp> powerUpsCayendo;
    private final GameWorld gameWorld;
    private final CollisionManager collisionManager;

    public PowerUpManager(GameWorld gameWorld, CollisionManager collisionManager)
    {
        powerUpsCayendo = new ArrayList<>();
        this.gameWorld = gameWorld;
        this.collisionManager = collisionManager;
    }

    public void spawnPowerUp(int x, int y)
    {
        PowerUp newPowerUp = new ExtraLife(this.gameWorld, x, y, 20, 20, Color.GREEN);
        powerUpsCayendo.add(newPowerUp);
    }

    public void update()
    {
        Iterator<PowerUp> iterator = powerUpsCayendo.iterator();

        while (iterator.hasNext())
        {
            PowerUp powerUp = iterator.next();
            powerUp.mover();
            if (collisionManager.checkCollision(gameWorld.getPad(), powerUp))
            {
                powerUp.apply();
                iterator.remove();
            }
            else if (powerUp.getY() + powerUp.getHeight() < 0) iterator.remove(); // Elimina si sale de la pantalla
        }
    }

    public List<PowerUp> getPowerUpsCayendo() { return powerUpsCayendo; }
}