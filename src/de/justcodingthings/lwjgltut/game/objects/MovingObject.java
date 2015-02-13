package de.justcodingthings.lwjgltut.game.objects;

public class MovingObject extends GameObject {

	protected float velX, velY;
	
	public MovingObject(float x, float y, float width, float height) {
		super(x, y, width, height);
	}
	
	public void update() {
		x += velX;
		y += velY;
		
	}

	public void setVelX(float velX) {
		this.velX = velX;
	}

	public void setVelY(float velY) {
		this.velY = velY;
	}

}
