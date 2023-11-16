package com.mygdx.game;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;

public class PingBall extends GameObject implements Movable
{
	private int xSpeed;
	private int ySpeed;

	private boolean estaQuieto;
	private final boolean fire;

	public PingBall(int x, int y, int size, int xSpeed, int ySpeed, boolean iniciaQuieto, boolean fire)
	{
		super(x, y, size, size, Color.WHITE);

		this.xSpeed = xSpeed;
		this.ySpeed = ySpeed;

		this.fire = fire;
		estaQuieto = iniciaQuieto;
	}

	public boolean estaQuieto() { return estaQuieto; }

	public void setEstaQuieto(boolean bb) { estaQuieto = bb; }

	@Override
	public void draw(ShapeRenderer shape)
	{
		if (fire) shape.setColor(Color.RED);
		else shape.setColor(color);

		shape.circle(x, y, (float) width); // width / 2 es el radio
	}

	@Override
	public void move(int xSpeed, int ySpeed)
	{
		if (estaQuieto) return;

		x += xSpeed;
		y += ySpeed;
	}

	public void reverseYDirection() { ySpeed = -ySpeed; }

	public void reverseXDirection() { xSpeed = -xSpeed; }

	public boolean isFire() { return fire; }

	public int getXSpeed() { return xSpeed; }

	public int getYSpeed() { return ySpeed; }

	public void setXY(int x, int y)
	{
		this.x = x;
		this.y = y;
	}

	/*

	public void setXSpeed(double xSpeed) { this.xSpeed = (int) xSpeed; }
	public void setYSpeed(double ySpeed) { this.ySpeed = (int) ySpeed; }

	public double getSpeed() { return Math.sqrt(xSpeed * xSpeed + ySpeed * ySpeed); }

	pora futura implementacion de velocidad variable con trigonometría y ángulos de choque

	*/
}


