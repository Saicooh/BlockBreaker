package com.mygdx.game;

import com.badlogic.gdx.Gdx;

import java.util.Iterator;

public class GameWorld
{
    private final Paddle pad;

    private int vidas = VIDAS_INICIALES, puntaje = 0, nivel = 1, multiplicadorPuntaje = 1;

    private final SoundManager soundManager;
    private final CollisionManager collisionManager;
    private final BlockManager blockManager;
    private final PowerUpManager powerUpManager;
    private final PingBallManager pingBallManager;

    private static final int VIDAS_INICIALES = 3;
    private static final int ANCHO_PADDLE = 140;
    private static final int ALTO_PADDLE = 15;
    private static final int TAMANO_BOLA = 12;
    private static final int ANCHO_BLOQUE = 70;
    private static final int ALTO_BLOQUE = 26;

    public GameWorld()
    {
        blockManager = new BlockManager();
        blockManager.createBlocks(2 + nivel, ANCHO_BLOQUE, ALTO_BLOQUE);

        pad = new Paddle(Gdx.graphics.getWidth() / 2 - 50, 40, ANCHO_PADDLE, ALTO_PADDLE);

        soundManager = new SoundManager();
        collisionManager = new CollisionManager();
        powerUpManager = new PowerUpManager(this, collisionManager, soundManager);
        pingBallManager = new PingBallManager(this, collisionManager, soundManager);

        pingBallManager.addBall(createBall(true, false));
    }

    private void resetGame()
    {
        soundManager.play("gameover", 0.3f);
        resetGameStatus();
        blockManager.createBlocks(2 + nivel, ANCHO_BLOQUE, ALTO_BLOQUE);
    }

    private void resetGameStatus()
    {
        cancelAllTimers();

        vidas = VIDAS_INICIALES;
        nivel = 1;
        puntaje = 0;
        multiplicadorPuntaje = 1;

        pad.setWidth(ANCHO_PADDLE);

        powerUpManager.limpiarPoderesCayendo();
        powerUpManager.limpiarPoderesActivos();
        pingBallManager.cleanBallList();

        pingBallManager.addBall(createBall(true, false));
    }

    private void increaseLevel()
    {
        nivel++;
        resetGameStatus();
        soundManager.play("finish", 0.3f);
    }

    public void handleGameOver() { if (vidas == 0) resetGame(); }

    public void handleOutOfBounds()
    {
        if (pingBallManager.verifyAllBallsOutOfBounds())
        {
            vidas--;
            pingBallManager.addBall(createBall(true, false));
            powerUpManager.limpiarPoderesCayendo();
        }
    }

    public void handleLevelFinished()
    {
        if (blockManager.verificarListaVacia())
        {
            increaseLevel();
            blockManager.createBlocks(2 + nivel, ANCHO_BLOQUE, ALTO_BLOQUE);
        }
    }

    private void verifyBlocksCollision()
    {
        Iterator<Block> iterator = blockManager.getBlocks().iterator();

        while (iterator.hasNext())
        {
            Iterator<PingBall> pingBallIterator = pingBallManager.getBallList().iterator();

            Block block = iterator.next();

            while (pingBallIterator.hasNext())
            {
                PingBall ball = pingBallIterator.next();

                if (collisionManager.checkCollision(ball, block))
                {
                    generatePowerUp(block.getX(), block.getY());

                    if (!ball.isFire()) ball.reverseYDirection();

                    block.setDestroyed(true);
                    iterator.remove();

                    soundManager.play("collision", 0.3f);

                    puntaje += Math.max(multiplicadorPuntaje, 1);
                }
            }
        }
    }

    public PingBall createBall(boolean iniciaQuieto, boolean fire)
    {
        return new PingBall(pad.getX() + pad.getWidth() / 2 - 5, pad.getY() + pad.getHeight() + 11, TAMANO_BOLA, 7, 7, iniciaQuieto, fire);
    }

    public void addBall(PingBall ball) { pingBallManager.addBall(ball); }

    private void generatePowerUp(int x, int y) { powerUpManager.spawnPowerUp(x, y); }


    public void update()
    {
        // Manejar movimiento de paddle
        pad.handleInput();

        // Monitorear inicio del juego
        pingBallManager.handleStart();

        // Verificar si se fue la bola x abajo
        handleOutOfBounds();

        pingBallManager.handleBallPaddleCollision();

        pingBallManager.handleBallScreen();

        // Verificar game over
        handleGameOver();

        // Verificar si el nivel se terminó
        handleLevelFinished();

        powerUpManager.update();

        verifyBlocksCollision();
    }

    public Paddle getPad() { return pad; }

    public int getPuntaje() { return puntaje; }
    public int getVidas() { return vidas; }
    public int getMultiplicadorPuntaje() { return multiplicadorPuntaje; }
    public int getPaddleWidth() { return pad.getWidth(); }

    public PowerUpManager getPowerUpManager() { return powerUpManager; }
    public BlockManager getBlockManager() { return blockManager; }

    public void cancelAllTimers() { powerUpManager.cancelAllTimers(); }

    public String getNivel() { return String.valueOf(nivel);}

    public void setPaddleWidth(int width) { pad.setWidth(width); }
    public void setVidas(int vidas) { this.vidas = vidas; }

    public void setMultiplicadorPuntaje(int multiplicadorPuntaje)
    {
        if (multiplicadorPuntaje < 1) return;

        this.multiplicadorPuntaje = multiplicadorPuntaje;
    }

    public void dispose() { soundManager.dispose(); }

    public PingBallManager getPingBallManager() { return pingBallManager; }
}

