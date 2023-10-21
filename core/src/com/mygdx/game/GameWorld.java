package com.mygdx.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;

import java.util.ArrayList;

public class GameWorld
{
    private PingBall ball;
    private final Paddle pad;
    private final ArrayList<Block> blocks = new ArrayList<>();
    private int vidas, puntaje, nivel;

    private final SoundManager soundManager;

    private final CollisionManager collisionManager;

    private static final int VIDAS_INICIALES = 3;
    private static final int ANCHO_PADDLE = 100;
    private static final int ALTO_PADDLE = 10;

    private static final int TAMANO_BOLA = 10;

    public GameWorld()
    {
        nivel = 1;
        crearBloques(2 + nivel);
        ball = new PingBall(Gdx.graphics.getWidth() / 2 - 10, 41, TAMANO_BOLA, 5, 7, true);
        pad = new Paddle(Gdx.graphics.getWidth() / 2 - 50, 40, ANCHO_PADDLE, ALTO_PADDLE);
        vidas = VIDAS_INICIALES;
        puntaje = 0;

        soundManager = new SoundManager();
        collisionManager = new CollisionManager();
    }

    public void handleGameOver()
    {
        if (vidas > 0) return;

        soundManager.play("gameover", 0.3f);
        vidas = VIDAS_INICIALES;
        nivel = 1;
        puntaje = 0;
        crearBloques(2 + nivel);
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
            ball = new PingBall(pad.getX() + pad.getWidth() / 2 - 5, pad.getY() + pad.getHeight() + 11, TAMANO_BOLA, 5, 7, true);
        }
    }

    public void handleLevelFinished()
    {
        if (blocks.isEmpty())
        {
            nivel++;
            crearBloques(2 + nivel);
            ball = new PingBall(pad.getX() + pad.getWidth() / 2 - 5, pad.getY() + pad.getHeight() + 11, 10, 5, 7, true);
            soundManager.play("finish", 0.3f);
        }
    }

    void handleBlockCollision()
    {
        ArrayList<Block> blocksToRemove = new ArrayList<>();

        for (Block block : blocks)
        {
            if (collisionManager.checkCollision(ball, block))
            {
                ball.reverseYDirection();
                block.setDestroyed(true);
            }

            if (block.isDestroyed())
            {
                soundManager.play("collision", 1.0f);
                puntaje++;
                blocksToRemove.add(block);
            }
        }

        blocks.removeAll(blocksToRemove);
    }

    public void update()
    {
        // Manejar movimiento de paddle
        pad.handleInput();

        // Monitorear inicio del juego
        handleStart();

        // Verificar si se fue la bola x abajo
        handleOutOfBounds(pad);

        // Verificar game over
        handleGameOver();

        // Verificar si el nivel se terminó
        handleLevelFinished();

        // Verificar colisiones
        handleBlockCollision();

        if (collisionManager.handleBallPaddleCollision(ball, pad)) soundManager.play("paddleHit2", 0.3f);
    }

    public void crearBloques(int filas)
    {
        blocks.clear();

        int blockWidth = 70;
        int blockHeight = 26;
        int y = Gdx.graphics.getHeight();

        for (int cont = 0; cont < filas; cont++)
        {
            y -= blockHeight + 10;
            for (int x = 5; x < Gdx.graphics.getWidth(); x += blockWidth + 10)
                blocks.add(new Block(x, y, blockWidth, blockHeight));
        }
    }

    public Paddle getPad() { return pad; }

    public PingBall getBall() { return ball; }

    public ArrayList<Block> getBlocks() { return blocks; }

    public int getPuntaje() { return puntaje; }

    public int getVidas() { return vidas; }

    public void dispose() { soundManager.dispose(); }

    // Getters para elementos del juego (paddle, ball, blocks, etc.)
}

