package com.mbokinala.gameframework;

import java.awt.Canvas;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.image.BufferStrategy;

import com.mbokinala.gameframework.gameobjects.Box;
import com.mbokinala.gameframework.gameobjects.ID;

public class Game extends Canvas implements Runnable {
	
	private boolean isRunning = false;
	private Thread thread;
	private Handler handler;
	
	public Game() {
		new Window(1000, 563, "Basic Game", this);
		start();
		
		handler = new Handler();
		handler.addGameObject(new Box(30, 30, ID.Block));
	}
	
	private void start() {
		isRunning = true;
		thread = new Thread(this);
		thread.start();
	}
	
	private void stop() {
		isRunning = false;
		try {
			thread.join();
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}
	
	@Override
	public void run() {
		this.requestFocus();
		long lastTime = System.nanoTime();
		double amountOfTicks = 60.0;
		double ns = 1000000000 / amountOfTicks;
		double delta = 0;
		long timer = System.currentTimeMillis();
		int frames = 0;
		while(isRunning) {
			long now = System.nanoTime();
			delta += (now - lastTime) / ns;
			lastTime = now;
			while(delta >= 1) {
				update();
				delta--;
			}
			
			render();
			frames++;
			
			if (System.currentTimeMillis() - timer > 1000) {
				timer += 1000;
				frames = 0;
			}
			
		}
		
		stop();
	}
	
	public void update() {
		handler.update();
	}
	
	public void render() {
		BufferStrategy bufferStrategy = this.getBufferStrategy();
		if (bufferStrategy == null) {
			this.createBufferStrategy(3);
			return;
		}
		
		Graphics g = bufferStrategy.getDrawGraphics();
		
		g.setColor(Color.red);
		g.fillRect(0, 0, 1000, 563);
		
		handler.render(g);
		
		g.dispose();
		bufferStrategy.show();
	}
	
	public static void main(String args[]) {
		new Game();
	}
}
