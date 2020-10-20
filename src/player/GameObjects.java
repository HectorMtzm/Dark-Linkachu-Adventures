package player;

import consumable.ConsumableObject;
import enemy.EnemyObjects;
import map_object.Flag;
import map_object.MapObjects;
import projectile.ProjectileObjects;
import utility.AePlayWave;
import utility.GameFrame;

import java.awt.Graphics;
import java.awt.Rectangle;

public abstract class GameObjects {
				//sound effects
	AePlayWave ju = new AePlayWave("jump.wav");
	AePlayWave fr = new AePlayWave("fire.wav");


	public boolean isRight;
	boolean isAlive = true;
	float Gravity = 0.5f;
	protected int width, height;
	float velX = 0, velY = 0;
	protected float x, y;
	public int hit;

	public GameObjects() {
		GameFrame.AllGameObjects.add(this);
	}

	public abstract void draw(Graphics g);

	public abstract void move();

	public void Die() {
		isAlive = false;
	}

	// Return player to level 1
	public void playerDie(){
		clearLvl();
		Flag.setLevel(1);
		Player.CREATELEVEL1 = true;
		GameFrame.getPlayer().arrows=0;
	}
	
	public static void clearLvl(){
		GameFrame.getPlayer().setNumOfGems(0);
		GameFrame.getPlayer().setX(100);
		GameFrame.setNumOfHearts(5);
		for (ConsumableObject consumable : GameFrame.allConsumables)
			consumable.Die();
		for (EnemyObjects enemy : GameFrame.getAllEnemies())
			enemy.Die();
		for (ProjectileObjects projectile : GameFrame.allProjectiles)
			projectile.Die();
		for (MapObjects mapObject : GameFrame.allMapObjects)
			mapObject.Die();
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

	public boolean isAlive() {
		return isAlive;
	}
}