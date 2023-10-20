package com.mygdx.game;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;

public class BlockBreakerGame extends ApplicationAdapter
{
	private GameWorld world;
	private GameRenderer renderer;

	@Override
	public void create()
	{
		world = new GameWorld();
		renderer = new GameRenderer(world);
	}

	@Override
	public void render()
	{
		world.update(Gdx.graphics.getDeltaTime());
		renderer.render(Gdx.graphics.getDeltaTime());
	}

	@Override
	public void dispose()
	{
		world.dispose();
		renderer.dispose();
	}
}
