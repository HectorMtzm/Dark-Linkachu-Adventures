package enemy;


import utility.GameFrame;
import utility.Sounds;

import java.awt.Graphics;
import java.awt.Rectangle;

public abstract class EnemyObjects {

	public boolean isRight;
	boolean isAlive = true;
	int width, height;
	float velX = 0, velY = 0;
	float x, y;

	public EnemyObjects() {
		GameFrame.allEnemies.add(this);
	}
	
	public abstract void draw(Graphics g);

	public abstract void move();
	
	public void die(boolean clearMap) {
		if(!clearMap)
			GameFrame.sound.playSound(Sounds.monsterDie);
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

	public boolean isAlive() {
		return isAlive;
	}

	public int getWidth() {
		return width;
	}
}
