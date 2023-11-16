package com.mygdx.game.managers;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.mygdx.game.GameWorld;
import com.mygdx.game.PingBall;

import java.util.ArrayList;
import java.util.List;
import java.util.Iterator;

public class PingBallManager
{
    private final List<PingBall> balls;
    private final GameWorld game;
    private final CollisionManager collisionManager;
    private final SoundManager soundManager;

    public PingBallManager(GameWorld game, CollisionManager collisionManager, SoundManager soundManager)
    {
        this.game = game;
        this.collisionManager = collisionManager;
        this.soundManager = soundManager;
        balls = new ArrayList<>();
    }

    public void addBall(PingBall ball)
    {
        balls.add(ball);
    }

    public void cleanBallList()
    {
        balls.clear();
    }

    public void handleBallScreen()
    {
        for (PingBall ball : balls)
        {
            if (ball.getX() - ball.getWidth() / 2 < 0 || ball.getX() + ball.getWidth() / 2 > Gdx.graphics.getWidth()) ball.reverseXDirection();

            if (ball.getY() + ball.getWidth() / 2 > Gdx.graphics.getHeight()) ball.reverseYDirection();
        }
    }

    public boolean verifyAllBallsOutOfBounds()
    {
        boolean allBallsOutOfBounds = true;
        Iterator<PingBall> iterator = balls.iterator();

        while (iterator.hasNext())
        {
            PingBall ball = iterator.next();

            if (ball.getY() >= 0) allBallsOutOfBounds = false;
            else iterator.remove();
        }

        return allBallsOutOfBounds; // Retorna true si todas las pelotas salieron de los límites
    }

    public void handleStart()
    {
        if (balls.size() == 1)
        {
            PingBall ball = balls.get(0);

            if (ball.estaQuieto())
            {
                ball.setXY(game.getPad().getX() + game.getPad().getWidth() / 2 - 5, game.getPad().getY() + game.getPad().getHeight() + 11);
                if (Gdx.input.isKeyPressed(Input.Keys.SPACE)) ball.setEstaQuieto(false);
            }
            else ball.move(ball.getXSpeed(), ball.getYSpeed());
        }

        else for (PingBall ball : balls) ball.move(ball.getXSpeed(), ball.getYSpeed());
    }

    public void handleBallPaddleCollision()
    {
        for (PingBall ball : balls)
        {
            if (collisionManager.checkCollision(ball, game.getPad()))
            {
                ball.reverseYDirection();
                soundManager.play("paddleHit2", 0.3f);
            }
        }
    }

    public List<PingBall> getBallList() { return balls; }
}
