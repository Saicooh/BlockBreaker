package com.mygdx.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.mygdx.game.managers.AssetManager;

public class GameRenderer
{
    private final GameWorld world;
    private final ShapeRenderer shapeRenderer;
    private final BitmapFont font;
    private final SpriteBatch batch;
    private final OrthographicCamera camera;

    private static final int CAMERA_WIDTH = 2075;
    private static final int CAMERA_HEIGHT = 1080;

    public GameRenderer(GameWorld world)
    {
        this.world = world;
        this.batch = new SpriteBatch();
        this.shapeRenderer = new ShapeRenderer();
        this.camera = new OrthographicCamera();
        this.camera.setToOrtho(false, CAMERA_WIDTH, CAMERA_HEIGHT);
        this.font = new BitmapFont();
        this.font.getData().setScale(3, 2);
    }

    public void render()
    {
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        Sprite miSprite = new Sprite(AssetManager.getInstance().getBackgroundTexture());

        shapeRenderer.begin(ShapeRenderer.ShapeType.Filled);
        batch.begin();

        // Dibuja el Paddle
        world.getPad().draw(shapeRenderer);

        miSprite.setSize(2075, 1080);
        miSprite.setAlpha(0.5f);
        miSprite.draw(batch);

        // Dibuja la lista de pelotas
        for (PingBall ball : world.getPingBallManager().getBallList())
            ball.draw(shapeRenderer);

        // Dibuja los powerups
        for (PowerUp pu : world.getPowerUpManager().getPowerUpsCayendo())
            pu.drawSprite(batch);

        // Dibuja los bloques
        for (Block b : world.getBlockManager().getBlocks())
            b.draw(shapeRenderer);

        batch.end();
        shapeRenderer.end();
        dibujaTextos();
    }

    private void dibujaTextos()
    {
        //actualizar matrices de la cámara
        camera.update();
        //actualizar
        batch.setProjectionMatrix(camera.combined);
        batch.begin();
        //dibujar textos
        font.draw(batch, "Puntos: " + world.getPuntaje(), 10, 55);
        font.draw(batch, "Multiplicador: " + world.getMultiplicadorPuntaje() + "x", 10, 25);
        font.draw(batch, "Nivel: " + world.getNivel(), Gdx.graphics.getWidth(), 55);
        font.draw(batch, "Vidas: " + world.getVidas(), Gdx.graphics.getWidth() - 20, 25);
        batch.end();
    }

    public void dispose()
    {
        shapeRenderer.dispose();
        batch.dispose();
        font.dispose();
    }
}
