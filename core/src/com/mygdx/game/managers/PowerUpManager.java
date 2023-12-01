package com.mygdx.game.managers;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.mygdx.game.*;
import com.mygdx.game.estrategias.*;

import java.util.Iterator;
import java.util.List;
import java.util.ArrayList;
import java.util.Random;

public class PowerUpManager
{
    private final List<PowerUp> powerUpsCayendo;
    private final List<PowerUp> powerUpsActivos;
    private final GameWorld gameWorld;

    public PowerUpManager(GameWorld gameWorld)
    {
        powerUpsCayendo = new ArrayList<>();
        powerUpsActivos = new ArrayList<>();
        this.gameWorld = gameWorld;
    }

    public void spawnPowerUp(int x, int y)
    {
        if (Math.random() < 0.15) return;

        Random random = new Random();
        boolean isBuff;

        int rand = random.nextInt(6) + 1;
        int rand2 = random.nextInt(100) + 1;

        isBuff = rand2 >= 10;

        PowerUp newPowerUp = null;
        Sprite miSprite = null;

        switch(rand)
        {
            case 1: if (isBuff) miSprite = new Sprite(AssetManager.getInstance().getExtraLifeTexture()); else miSprite = new Sprite(AssetManager.getInstance().getExtraLifeNerfTexture()); break;
            case 2: if (isBuff) miSprite = new Sprite(AssetManager.getInstance().getDoubleScoreTexture()); else miSprite = new Sprite(AssetManager.getInstance().getDoubleScoreNerfTexture()); break;
            case 3: if (isBuff) miSprite = new Sprite(AssetManager.getInstance().getPaddleExtenderTexture()); else miSprite = new Sprite(AssetManager.getInstance().getPaddleExtenderNerfTexture()); break;
            case 4: miSprite = new Sprite(AssetManager.getInstance().getExtraBallTexture()); break;
            case 5: miSprite = new Sprite(AssetManager.getInstance().getFireBallTexture()); break;
            case 6: miSprite = new Sprite(AssetManager.getInstance().getLargePingBallTexture()); break;
        }

        switch (rand)
        {
            case 1: newPowerUp = new PowerUp(this.gameWorld, x, y, 20, 20, Color.GREEN, isBuff, new ExtraLifeStrategy(), miSprite); break;
            case 2: newPowerUp = new TimedPowerUp(this.gameWorld, x, y, 20, 20, Color.YELLOW, 10000, isBuff, new DoubleScoreStrategy(), miSprite); break;
            case 3: newPowerUp = new TimedPowerUp(this.gameWorld, x, y, 20, 20, Color.BLUE, 10000, isBuff, new PaddleExtenderStrategy(), miSprite); break;
            case 4: newPowerUp = new PowerUp(this.gameWorld, x, y, 20, 20, Color.WHITE, isBuff, new ExtraBallStrategy(), miSprite); break;
            case 5: newPowerUp = new PowerUp(this.gameWorld, x, y, 20, 20, Color.ORANGE, isBuff, new FirePingBallStrategy(), miSprite); break;
            case 6: newPowerUp = new PowerUp(this.gameWorld, x, y, 20, 20, Color.DARK_GRAY, isBuff, new LargePingBallStrategy(), miSprite); break;
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

            if (CollisionManager.getInstance().checkCollision(powerUp, gameWorld.getPad()))
            {
                powerUpsActivos.add(powerUp);
                SoundManager.getInstance().play("powerup", 0.3f);
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