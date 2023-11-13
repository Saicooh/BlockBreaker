package com.mygdx.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class GameWorld
{
    private PingBall ball;
    private final Paddle pad;

    private int vidas, puntaje, nivel;

    private final SoundManager soundManager;

    private final CollisionManager collisionManager;

    private final BlockManager blockManager;

    private final PowerUpManager powerUpManager;

    private static final int VIDAS_INICIALES = 3;
    private static final int ANCHO_PADDLE = 140;
    private static final int ALTO_PADDLE = 15;

    private static final int TAMANO_BOLA = 12;

    private static final int ANCHO_BLOQUE = 70;
    private static final int ALTO_BLOQUE = 26;

    public GameWorld()
    {
        nivel = 1;
        blockManager = new BlockManager();
        blockManager.createBlocks(2 + nivel, ANCHO_BLOQUE, ALTO_BLOQUE);
        ball = new PingBall(Gdx.graphics.getWidth() / 2 - 10, 41, TAMANO_BOLA, 7, 7, true);
        pad = new Paddle(Gdx.graphics.getWidth() / 2 - 50, 40, ANCHO_PADDLE, ALTO_PADDLE);
        vidas = VIDAS_INICIALES;
        puntaje = 0;

        soundManager = new SoundManager();
        collisionManager = new CollisionManager();
        powerUpManager = new PowerUpManager(this, collisionManager);
    }

    public void handleGameOver()
    {
        if (vidas > 0) return;

        soundManager.play("gameover", 0.3f);
        vidas = VIDAS_INICIALES;
        nivel = 1;
        puntaje = 0;
        blockManager.createBlocks(2 + nivel, ANCHO_BLOQUE, ALTO_BLOQUE);
    }

    public void handleStart()
    {
        if (ball.estaQuieto())
        {
            ball.setXY(pad.getX() + pad.getWidth() / 2 - 5, pad.getY() + pad.getHeight() + 11);
            if (Gdx.input.isKeyPressed(Input.Keys.SPACE)) ball.setEstaQuieto(false);
        }
        else ball.move(ball.getXSpeed(), ball.getYSpeed());
    }

    public void handleOutOfBounds(Paddle pad)
    {
        if (ball.getY() < 0)
        {
            vidas--;
            ball = new PingBall(pad.getX() + pad.getWidth() / 2 - 5, pad.getY() + pad.getHeight() + 11, TAMANO_BOLA, 7, 7, true);
        }
    }

    public void handleLevelFinished()
    {
        if (blockManager.getBlocks().isEmpty())
        {
            nivel++;
            blockManager.createBlocks(2 + nivel, ANCHO_BLOQUE, ALTO_BLOQUE);
            ball = new PingBall(pad.getX() + pad.getWidth() / 2 - 5, pad.getY() + pad.getHeight() + 11, 10, 7, 7, true);
            soundManager.play("finish", 0.3f);
        }
    }

    private void verifyBlocksCollision()
    {
        Iterator<Block> iterator = blockManager.getBlocks().iterator();

        while (iterator.hasNext())
        {
            Block block = iterator.next();

            if (collisionManager.checkCollision(ball, block))
            {
                if (Math.random() < 0.9) generatePowerUp(block.getX(), block.getY());
                ball.reverseYDirection();
                block.setDestroyed(true);
                iterator.remove(); // Eliminar el bloque usando el iterador

                soundManager.play("collision", 0.3f);
                puntaje++;
            }
        }
    }

    private void generatePowerUp(int x, int y)
    {
        powerUpManager.spawnPowerUp(x, y);
    }

    private void handleBallPaddleCollision()
    {
        if (collisionManager.checkCollision(ball, pad))
        {
            ball.reverseYDirection();
            soundManager.play("paddleHit2", 0.3f);
        }
    }

    public void update()
    {
        // Manejar movimiento de paddle
        pad.handleInput();

        // Monitorear inicio del juego
        handleStart();

        // Verificar si se fue la bola x abajo
        handleOutOfBounds(pad);

        handleBallPaddleCollision();

        // Verificar game over
        handleGameOver();

        // Verificar si el nivel se terminó
        handleLevelFinished();

        powerUpManager.update();

        verifyBlocksCollision();

    }

    public Paddle getPad() { return pad; }

    public PingBall getBall() { return ball; }

    public ArrayList<Block> getBlocks() { return (ArrayList<Block>) blockManager.getBlocks(); }

    public int getPuntaje() { return puntaje; }

    public int getVidas() { return vidas; }

    public PowerUpManager getPowerUpManager()
    {
        return powerUpManager;
    }

    public void setVidas(int vidas) { this.vidas = vidas; }

    public void dispose() { soundManager.dispose(); }

    // Getters para elementos del juego (paddle, ball, blocks, etc.)
}

