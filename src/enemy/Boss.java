package enemy;

import projectile.Arrow;
import projectile.ProjectileObjects;
import projectile.FireBall;
import utility.GameFrame;

import java.awt.Graphics;

import javax.swing.ImageIcon;

public class Boss extends EnemyObjects {
	static java.awt.Image bossR = new ImageIcon("bossPR.png").getImage();
	static java.awt.Image bossL = new ImageIcon("bossPL.png").getImage();
	int Rnum;

	public Boss(float x, float y) {
		width = 250;
		height = 325;
		this.x = x;
		this.y = y;
		EnterBossLevel.start();
	}

	@Override
	public void draw(Graphics g) {
		
		if (GameFrame.getPlayer().getX() > x) {
			g.drawImage(bossR, (int) x, (int) y, width, height, null);
			isRight = true;
		} else {
			g.drawImage(bossL, (int) x, (int) y, width, height, null);
			isRight = false;
		}
		if (GameFrame.getNumOfHearts() > 0) {
			for (int i = 0, heartDistance = 30; i < GameFrame.getNumOfHearts(); i++, heartDistance += 30)
				g.fillRect((int) x + heartDistance + 10, (int) y - 30, 30, 8);
		}
	}

	@Override
	public void move() {
		Rnum = (int) (1 + Math.random() * 300);
		if (Rnum == 26)
			new FireBall(isRight ? 1 : -1, 0, x + (width / 2), y - 30);
		if (Rnum == 226)
			new FireBall(isRight ? 1 : -1, 0, x + (width / 2), y + 90);								//shoots randomly at different times
		if (Rnum == 126)
			new FireBall(isRight ? 1 : -1, 0, x + (width / 2), y + 210);
		if (GameFrame.getPlayer().getX() > x) {
			if (Rnum == 175)
				new FireBall(0, -1, x + (width / 2), y);
		}

		for (ProjectileObjects projectile : GameFrame.getAllProjectiles()) {
			if (projectile instanceof Arrow) {
				if (getBoundsTOP().intersects(projectile.getBounds()) || getBoundsBottom().intersects(projectile.getBounds())) {
					if (GameFrame.getNumOfHearts() > 1) {
						GameFrame.setNumOfHearts(GameFrame.getNumOfHearts() - 1);
						hurtMonster.start();
					} 
					else{
						Die();
						bossDie.start();
						GameFrame.setNumOfHearts(GameFrame.getNumOfHearts() - 1);;
					}
					projectile.Die();
				}

			}
		}
	}
}