package de.justcodingthings.lwjgltut.game;

import java.util.ArrayList;

import de.justcodingthings.lwjgltut.game.objects.GameObject;
import de.justcodingthings.lwjgltut.game.objects.MovingObject;
import de.justcodingthings.lwjgltut.game.objects.Player;

public class Game {
	
	private int player = 0;
	
	private ArrayList<GameObject> gameObjects = new ArrayList<GameObject>();
	private ArrayList<MovingObject> movingObjects = new ArrayList<MovingObject>();
	
	public Game(){
		movingObjects.add(player, new Player(0, 0, 32, 32));		
	}
	
	public void getInput(){
		((Player) movingObjects.get(player)).getInput();
	}
	
	public void update(){
		for (MovingObject obj : movingObjects){
			obj.update();
		}
	}
	
	public void render(){
		for (GameObject obj : gameObjects){
			obj.render();
		}
		for (MovingObject obj : movingObjects){
			obj.render();
		}
	}
	
}
