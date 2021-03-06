package com.mbokinala.gameframework.gameobjects;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Rectangle;

public class Box extends GameObject{

	public Box(int x, int y, ID id) {
		super(x, y, id);
	}

	@Override
	public void update() {
		x += velX;
		y += velY;
	}

	@Override
	public void render(Graphics g) {
		g.setColor(Color.blue);
		g.fillRect(x, y, 32, 32);
	}

	@Override
	public Rectangle getBounds() {
		return null;
	}

}
