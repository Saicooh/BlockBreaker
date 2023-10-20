package com.mygdx.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;

import java.util.ArrayList;

public class GameWorld {
    private PingBall ball;
    private Paddle pad;
    private ArrayList<Block> blocks = new ArrayList<>();
    private int vidas;
    private int puntaje;
    private int nivel;

    private final SoundManager soundManager;

    public GameWorld()
    {
        nivel = 1;
        crearBloques(2 + nivel);
        ball = new PingBall(Gdx.graphics.getWidth() / 2 - 10, 41, 10, 5, 7, true);
        pad = new Paddle(Gdx.graphics.getWidth() / 2 - 50, 40, 100, 10);
        vidas = 3;
        puntaje = 0;

        soundManager = new SoundManager();
    }

    public void update(float delta)
    {
        // Handle user input to move the paddle
        pad.handleInput();

        // Monitorear inicio del juego
        if (ball.estaQuieto())
        {
            ball.setXY(pad.getX() + pad.getWidth() / 2 - 5, pad.getY() + pad.getHeight() + 11);
            if (Gdx.input.isKeyPressed(Input.Keys.SPACE)) ball.setEstaQuieto(false);
        }
        else ball.update();

        // Verificar si se fue la bola x abajo
        if (ball.getY() < 0)
        {
            vidas--;
            ball = new PingBall(pad.getX() + pad.getWidth() / 2 - 5, pad.getY() + pad.getHeight() + 11, 10, 5, 7, true);
        }

        // Verificar game over
        if (vidas <= 0)
        {
            soundManager.play("gameover");
            vidas = 3;
            nivel = 1;
            puntaje = 0;
            crearBloques(2 + nivel);
        }

        // Verificar si el nivel se terminó
        if (blocks.isEmpty())
        {
            nivel++;
            crearBloques(2 + nivel);
            ball = new PingBall(pad.getX() + pad.getWidth() / 2 - 5, pad.getY() + pad.getHeight() + 11, 10, 5, 7, true);
        }

        // Verificar colisiones
        ArrayList<Block> blocksToRemove = new ArrayList<>();
        for (Block b : blocks)
        {
            ball.checkCollision(b);
            if (b.destroyed)
            {
                soundManager.play("collision");
                puntaje++;
                blocksToRemove.add(b);
            }
        }

        blocks.removeAll(blocksToRemove);

        ball.checkCollision(pad);
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

    public void dispose()
    {
        soundManager.dispose();
    }

    // Getters para elementos del juego (paddle, ball, blocks, etc.)
}

