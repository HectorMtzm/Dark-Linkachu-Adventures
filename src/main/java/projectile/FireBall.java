package projectile;

import utility.GameFrame;
import utility.Sounds;

import java.awt.*;
import javax.swing.ImageIcon;

public class FireBall extends ProjectileObjects {
	final Image fireball = new ImageIcon("src/main/resources/images/fireball.png").getImage();
	int timeToLive = 100;

	public FireBall(int v, int h, float x, float y) {
		width = 20;
		height = 20;
		velX = v * 4;
		velY = h * 4;
		this.x = x;
		this.y = y;
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
	}

}