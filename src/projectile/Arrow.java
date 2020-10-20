package projectile;

import enemy.EnemyObjects;
import map_object.Blocks;
import map_object.MapObjects;
import utility.GameFrame;

import java.awt.Graphics;

import javax.swing.ImageIcon;

public class Arrow extends ProjectileObjects {
	java.awt.Image arrowR = new ImageIcon("ArrowR.png").getImage();
	java.awt.Image arrowL = new ImageIcon("ArrowL.png").getImage();

	int timeToLive = 60;

	public Arrow(int i, float x, float y) {
		width = 50;
		height = 13;
		velX = i * 6;
		this.x = x + 20;
		this.y = y + 40;
		arrow.start();
	}

	public void draw(Graphics g) {
		if (velX > 1)
			g.drawImage(arrowR, (int) x, (int) y, null);
		else
			g.drawImage(arrowL, (int) x, (int) y, null);

	}


	// CHANGE. NOT EFFICIENT
	public void move() {
		timeToLive--;
		x += velX;
		for (MapObjects mapObject : GameFrame.getAllMapObjects()) {
			if (mapObject instanceof Blocks) {
				if (getBounds().intersects(mapObject.getBounds()))
					Die();
			}
		}
		
		for(EnemyObjects enemy : GameFrame.getAllEnemies()) {
			if (getBounds().intersects(enemy.getBounds())) {
				Die();
				enemy.Die();
				enemy.monsterDie.start();
			}
		}
		if (timeToLive <= 0)
			Die();
	}
}