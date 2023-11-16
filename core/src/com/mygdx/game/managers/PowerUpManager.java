package com.mygdx.game.managers;

import com.badlogic.gdx.graphics.Color;
import com.mygdx.game.*;
import com.mygdx.game.powerups.*;

import java.util.Iterator;
import java.util.List;
import java.util.ArrayList;
import java.util.Random;

public class PowerUpManager
{
    private final List<PowerUp> powerUpsCayendo;
    private final List<PowerUp> powerUpsActivos;
    private final GameWorld gameWorld;
    private final CollisionManager collisionManager;
    private final SoundManager soundManager;

    public PowerUpManager(GameWorld gameWorld, CollisionManager collisionManager, SoundManager soundManager)
    {
        powerUpsCayendo = new ArrayList<>();
        powerUpsActivos = new ArrayList<>();
        this.gameWorld = gameWorld;
        this.collisionManager = collisionManager;
        this.soundManager = soundManager;
    }

    public void spawnPowerUp(int x, int y)
    {
        if (Math.random() < 0.000001) return;

        Random random = new Random();

        int rand = random.nextInt(5) + 1;

        PowerUp newPowerUp = null;

        switch (rand)
        {
            case 1: newPowerUp = new ExtraLife(this.gameWorld, x, y, 20, 20, Color.GREEN); break;
            case 2: newPowerUp = new DoubleScore(this.gameWorld, x, y, 20, 20, Color.YELLOW); break;
            case 3: newPowerUp = new PaddleExtender(this.gameWorld, x, y, 20, 20, Color.BLUE); break;
            case 4: newPowerUp = new DoublePingBall(this.gameWorld, x, y, 20, 20, Color.WHITE); break;
            case 5: newPowerUp = new FirePingBall(this.gameWorld, x, y, 20, 20, Color.ORANGE); break;
        }

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
                powerUpsActivos.add(powerUp);
                soundManager.play("powerup", 0.3f);
                powerUp.apply();
                iterator.remove();
            }
            else if (powerUp.getY() + powerUp.getHeight() < 0) iterator.remove(); // Elimina si sale de la pantalla
        }
    }

    public void limpiarPoderesCayendo() { powerUpsCayendo.clear(); } // Limpia la lista de power-ups cayendo

    public void limpiarPoderesActivos() { powerUpsActivos.clear(); }// Limpia la lista de power-ups activos

    public void cancelAllTimers()
    {
        for (PowerUp powerUp : powerUpsActivos)
            if (powerUp instanceof TimedPowerUp) ((TimedPowerUp) powerUp).cancelTimer();
    }

    public List<PowerUp> getPowerUpsCayendo() { return powerUpsCayendo; }
}