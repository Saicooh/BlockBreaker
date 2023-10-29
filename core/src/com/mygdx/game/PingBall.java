package com.mygdx.game;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;

public class PingBall extends GameObject implements Movable
{
	private int xSpeed;
	private int ySpeed;
	private boolean estaQuieto;

	private final CollisionManager collisionManager;

	public PingBall(int x, int y, int size, int xSpeed, int ySpeed, boolean iniciaQuieto)
	{
		super(x, y, size, size, Color.WHITE);
		this.xSpeed = xSpeed;
		this.ySpeed = ySpeed;
		estaQuieto = iniciaQuieto;
		collisionManager = new CollisionManager();
	}

	public boolean estaQuieto() { return estaQuieto; }

	public void setEstaQuieto(boolean bb) { estaQuieto = bb; }

	@Override
	public void draw(ShapeRenderer shape)
	{
		shape.setColor(color);
		shape.circle(x, y, (float) width); // width / 2 es el radio
	}

	@Override
	public void move(int xSpeed, int ySpeed)
	{
		if (estaQuieto) return;

		x += xSpeed;
		y += ySpeed;

		collisionManager.handleBallScreenCollision(this);
	}

	public void reverseYDirection() { ySpeed = -ySpeed; }

	public void reverseXDirection() { xSpeed = -xSpeed; }

	public int getXSpeed() { return xSpeed; }

	public int getYSpeed() { return ySpeed; }

	public void setXY(int x, int y)
	{
		this.x = x;
		this.y = y;
	}

	public void setXSpeed(double xSpeed) { this.xSpeed = (int) xSpeed; }
	public void setYSpeed(double ySpeed) { this.ySpeed = (int) ySpeed; }

	public double getSpeed() { return Math.sqrt(xSpeed * xSpeed + ySpeed * ySpeed); }


}


