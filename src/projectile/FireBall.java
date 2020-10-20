package projectile;

import java.awt.Graphics;

import javax.swing.ImageIcon;

public class FireBall extends ProjectileObjects {
	java.awt.Image fireball = new ImageIcon("fireball.png").getImage();
	int timeToLive = 100;

	public FireBall(int v, int h, float x, float y) {
		width = 20;
		height = 20;
		velX = v * 4;
		velY = h * 4;
		this.x = x + 20;
		this.y = y + 35;
		fireBall.start();
	}

	public void draw(Graphics g) {
		g.drawImage(fireball, (int) x, (int) y, null);
	}

	public void move() {
		timeToLive--;
		y += velY;
		x += velX;
		if (timeToLive <= 0)
			Die();
	}

}