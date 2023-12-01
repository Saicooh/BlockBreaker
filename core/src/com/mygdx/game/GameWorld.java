package com.mygdx.game;

import com.badlogic.gdx.Gdx;
import com.mygdx.game.managers.*;

import java.util.Iterator;

public class GameWorld
{
    private final Paddle pad;

    private int vidas = VIDAS_INICIALES, nivel = 1, multiplicadorPuntaje = 1;
    private long puntaje = 0;

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

        powerUpManager = new PowerUpManager(this);
        pingBallManager = new PingBallManager(this);

        pingBallManager.addBall(createBall(true, false));
        SoundManager.getInstance().play("background", 0.05f);
        AssetManager.getInstance().loadAssets();
    }

    private void resetGame()
    {
        SoundManager.getInstance().play("gameover", 0.3f);

        resetGameStatus();
        vidas = VIDAS_INICIALES;
        puntaje = 0;
        nivel = 1;

        blockManager.createBlocks(2 + nivel, ANCHO_BLOQUE, ALTO_BLOQUE);
    }

    private void resetGameStatus()
    {
        cancelAllTimers();

        multiplicadorPuntaje = 1;

        pad.setWidth(ANCHO_PADDLE);

        powerUpManager.limpiarPoderesCayendo();
        powerUpManager.limpiarPoderesActivos();
        pingBallManager.cleanBallList();

        pingBallManager.addBall(createBall(true, false));
    }

    private void increaseLevel()
    {
        resetGameStatus();
        nivel++;
        SoundManager.getInstance().play("finish", 0.3f);
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
            int newRows = 2 + nivel;
            blockManager.createBlocks(newRows + 1, ANCHO_BLOQUE, ALTO_BLOQUE);
        }
    }

    private void verifyBlocksCollision()
    {
        Iterator<Block> blockIterator = blockManager.getBlocks().iterator();

        while (blockIterator.hasNext())
        {
            Block block = blockIterator.next();
            if (checkAndHandleBallBlockCollision(block)) blockIterator.remove(); // Eliminar el bloque después de la colisión
        }
    }

    private boolean checkAndHandleBallBlockCollision(Block block)
    {
        for (PingBall ball : pingBallManager.getBallList())
        {
            if (CollisionManager.getInstance().checkCollision(ball, block))
            {
                handleBallBlockCollision(ball, block);
                return true; // Indica que hubo una colisión y el bloque debe eliminarse
            }
        }
        return false; // No hubo colisión con este bloque
    }

    private void handleBallBlockCollision(PingBall ball, Block block)
    {
        generatePowerUp(block.getX(), block.getY());

        if (!ball.isFire()) ball.reverseYDirection();

        SoundManager.getInstance().play("collision", 0.3f);

        puntaje += Math.max(multiplicadorPuntaje, 1);
    }

    public PingBall createBall(boolean iniciaQuieto, boolean fire)
    {
        return new PingBall(pad.getX() + pad.getWidth() / 2 - 5, pad.getY() + pad.getHeight() + 11, TAMANO_BOLA, 7, 7, iniciaQuieto, fire);
    }

    public void addBall(PingBall ball) { pingBallManager.addBall(ball); }

    private void generatePowerUp(int x, int y) { powerUpManager.spawnPowerUp(x, y); }

    public void update()
    {
        // Maneja movimiento de paddle
        pad.handleInput();

        // Monitorea inicio del juego
        pingBallManager.handleStart();

        // Verifica si todas las pingballs están fuera de los límites
        handleOutOfBounds();

        // Verifica colisión de cada pingball con el paddle
        pingBallManager.handleBallPaddleCollision();

        // Verifica colisión de cada pingball con los límites de la pantalla
        pingBallManager.handleBallScreen();

        // Verifica game over
        handleGameOver();

        // Verificar si el nivel se terminó
        handleLevelFinished();

        // Actualiza el estado de los powerups
        powerUpManager.update();

        // Verifica colisión de cada pingball con los bloques
        verifyBlocksCollision();
    }

    public Paddle getPad() { return pad; }

    public long getPuntaje() { return puntaje; }
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

    public PingBallManager getPingBallManager() { return pingBallManager; }

    public void dispose() {
    }
}