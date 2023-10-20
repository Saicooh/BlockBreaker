package com.mygdx.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;

public class GameRenderer
{
    private GameWorld world;
    private ShapeRenderer shapeRenderer;
    private BitmapFont font;
    private SpriteBatch batch;
    private OrthographicCamera camera;

    public GameRenderer(GameWorld world)
    {
        this.world = world;
        this.shapeRenderer = new ShapeRenderer();
        this.camera = new OrthographicCamera();
        this.camera.setToOrtho(false, 800, 480);
        this.batch = new SpriteBatch();
        this.font = new BitmapFont();
        this.font.getData().setScale(3, 2);
    }

    public void render(float delta)
    {
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        shapeRenderer.begin(ShapeRenderer.ShapeType.Filled);

        // Dibuja el Paddle
        world.getPad().draw(shapeRenderer);

        // Dibuja la bola
        world.getBall().draw(shapeRenderer);

        // Dibuja los bloques
        for (Block b : world.getBlocks())
            b.draw(shapeRenderer);

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
        font.draw(batch, "Puntos: " + world.getPuntaje(), 10, 25);  // Asumiendo que hay un getter para puntaje en GameWorld
        font.draw(batch, "Vidas : " + world.getVidas(), Gdx.graphics.getWidth() - 20, 25); // Asumiendo que hay un getter para vidas en GameWorld
        batch.end();
    }

    public void dispose()
    {
        shapeRenderer.dispose();
        batch.dispose();
        font.dispose();
    }
}
