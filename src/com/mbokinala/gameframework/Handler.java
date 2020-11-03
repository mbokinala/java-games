package com.mbokinala.gameframework;

import java.awt.Graphics;
import java.util.LinkedList;

import com.mbokinala.gameframework.gameobjects.GameObject;

public class Handler {
	LinkedList<GameObject> gameObjects = new LinkedList<GameObject>();
	
	public void update() {
		for (int i = 0; i < gameObjects.size(); i++) {
			GameObject obj = gameObjects.get(i);
			
			obj.update();
		}
	}
	
	public void render(Graphics g) {
		for (int i = 0; i < gameObjects.size(); i++) {
			GameObject obj = gameObjects.get(i);
			
			obj.render(g);
		}
	}
	
	public void addGameObject(GameObject object) {
		gameObjects.add(object);
	}
	
	public void removeGameObject(GameObject object) {
		gameObjects.remove(object);
	}
}
