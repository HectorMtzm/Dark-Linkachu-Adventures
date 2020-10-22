package projectile;

import enemy.EnemyObjects;
import map_object.Blocks;
import map_object.MapObjects;
import utility.GameFrame;

import java.awt.*;

import javax.swing.ImageIcon;

public class Arrow extends ProjectileObjects {
	Image arrowR = new ImageIcon("src/main/resources/images/ArrowR.png").getImage();

	int timeToLive = 60;

	public Arrow(int i, float x, float y) {
		width = 50;
		height = 13;
		velX = i * 6;
		this.x = x + 20;
		this.y = y + 40;
		GameFrame.sound.playSound(GameFrame.sound.arrow);
	}

	public void draw(Graphics g) {
		if (velX > 1)
			g.drawImage(arrowR, (int) x, (int) y, null);
		else
			g.drawImage(arrowR, (int) x + width, (int) y,-width, height, null);

	}

	// CHANGE. NOT EFFICIENT
	public void move() {
		timeToLive--;
		x += velX;
		for (MapObjects mapObject : GameFrame.getAllMapObjects()) {
			if (mapObject instanceof Blocks) {
				if (getBounds().intersects(mapObject.getBounds()))
					die();
			}
		}
		
		for(EnemyObjects enemy : GameFrame.getAllEnemies()) {
			if (getBounds().intersects(enemy.getBounds())) {
				die();
				enemy.die(false);
			}
		}
		if (timeToLive <= 0)
			die();
	}
}