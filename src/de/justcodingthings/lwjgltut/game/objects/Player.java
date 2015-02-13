package de.justcodingthings.lwjgltut.game.objects;

import de.justcodingthings.lwjgltut.engine.Main;
import static org.lwjgl.glfw.GLFW.*;


public class Player extends MovingObject{


	private float SPEED = 2f;
	
	public Player(float x, float y, float width, float height) {
		super(x, y, width, height);
	}

	public void getInput(){
		if (Main.isKeyDown(GLFW_KEY_W))
			setY(getY() + SPEED);
		if (Main.isKeyDown(GLFW_KEY_S))
			setY(getY() - SPEED);
		if (Main.isKeyDown(GLFW_KEY_A))
			setX(getX() - SPEED);
		if (Main.isKeyDown(GLFW_KEY_D))
			setX(getX() + SPEED);
	}
	
}
