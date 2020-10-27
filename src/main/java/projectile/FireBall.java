package projectile;

import map_object.MapObjects;
import utility.GameFrame;
import utility.Sounds;

import java.awt.*;
import javax.swing.ImageIcon;

public class FireBall extends ProjectileObjects {
	final Image fireball = new ImageIcon("src/main/resources/images/fireball.png").getImage();

	public FireBall(int v, int h, float x, float y) {
		timeToLive = 100;
		width = 20;
		height = 20;
		velX = v * 4;
		velY = h * 4;
		this.x = x;
		this.y = y;
		if (Math.abs(GameFrame.getPlayer().getX() - x) < 1000)
			GameFrame.sound.playSound(Sounds.fireball);
	}

	public void draw(Graphics g) {
		if (velX > 1)
			g.drawImage(fireball, (int) x, (int) y, null);
		else
			g.drawImage(fireball, (int) x + width, (int) y,-width, height, null);
	}

	public void move() {
		timeToLive--;
		y += velY;
		x += velX;
		if (timeToLive <= 0)
			die();
		else {
			for (MapObjects mapObject : GameFrame.allMapObjects){
				if (getBounds().intersects(mapObject.getBounds())){
					die();
				}
			}
		}
	}

}