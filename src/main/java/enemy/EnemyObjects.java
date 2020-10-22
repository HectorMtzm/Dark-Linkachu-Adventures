package enemy;


import utility.GameFrame;

import java.awt.Graphics;
import java.awt.Rectangle;

public abstract class EnemyObjects {

	public boolean isRight;
	boolean isAlive = true;
	int width, height;
	float velX = 0, velY = 0;
	float x, y;
	public int hit;

	public EnemyObjects() {
		GameFrame.allEnemies.add(this);
	}
	
	public abstract void draw(Graphics g);

	public abstract void move();
	
	public void die(boolean clearMap) {
		if(!clearMap)
			GameFrame.sound.playSound(GameFrame.sound.monsterDie);
		isAlive = false;
	}
	
	public Rectangle getBounds() {
		// System.out.println(getX() + " " + getY());
		return new Rectangle((int) x + 5, (int) y, width - 10, height);
	}

	public Rectangle getBoundsTOP() {
		return new Rectangle((int) x + (width / 2 - 5) - ((width / 2) / 2), (int) y, width / 2 + 10, height / 2);
	}

	public Rectangle getBoundsBottom() {
		return new Rectangle((int) x + (width / 2 - 5) - ((width / 2) / 2), (int) y + (height / 2) + 1, width / 2 + 10,	height / 2);
	}

	public Rectangle getBoundsLeft() {
		return new Rectangle((int) x + 5, (int) y + 5, 5, height - 10);															//four side boundaries
	}

	public Rectangle getBoundsRight() {
		return new Rectangle((int) x + width - 10, (int) y + 5, 5, height - 10);
	}

	public void setVelX(float x) {
		velX = x;
	}

	public void setVelY(float y) {
		velY = y;
	}

	public float getX() {
		return x;
	}

	public float getY() {
		return y;
	}

	public void setX(float x) {
		this.x = x;
	}

	public void setY(float y) {
		this.y = y;
	}

	public boolean isRight() {
		return isRight;
	}

	public void setRight(boolean right) {
		isRight = right;
	}

	public boolean isAlive() {
		return isAlive;
	}

	public void setAlive(boolean alive) {
		isAlive = alive;
	}
}
