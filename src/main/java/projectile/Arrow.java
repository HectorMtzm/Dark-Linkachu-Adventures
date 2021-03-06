package projectile;

import enemy.Boss;
import enemy.EnemyObjects;
import map_object.Block;
import map_object.MapObjects;
import utility.GameFrame;
import utility.Sounds;

import java.awt.*;

import javax.swing.ImageIcon;

public class Arrow extends ProjectileObjects {
	final Image arrowR = new ImageIcon("src/main/resources/images/Arrow.png").getImage();

	public Arrow(int i, float x, float y) {
		timeToLive = 60;
		width = 50;
		height = 13;
		velX = i * 6;
		this.x = x + 20;
		this.y = y + 40;
		GameFrame.sound.playSound(Sounds.arrow);
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
		for (MapObjects mapObject : GameFrame.allMapObjects) {
			if (mapObject instanceof Block) {
				if (getBounds().intersects(mapObject.getBounds()))
					die();
			}
		}
		
		for(EnemyObjects enemy : GameFrame.allEnemies) {
			if(!(enemy instanceof Boss)){
				if (getBounds().intersects(enemy.getBounds())) {
					die();
					enemy.die(false);
				}
			}
		}
		if (timeToLive <= 0)
			die();
	}
}