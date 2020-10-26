package enemy;

import projectile.Arrow;
import projectile.FireBall;
import projectile.ProjectileObjects;
import utility.GameFrame;
import utility.Sounds;

import java.awt.*;

import javax.swing.ImageIcon;

public class Boss extends EnemyObjects {
	static final Image bossR = new ImageIcon("src/main/resources/images/bossP.png").getImage();
	int Rnum;

	public Boss(float x, float y) {
		width = 250;
		height = 325;
		this.x = x;
		this.y = y;
		GameFrame.sound.playSound(Sounds.bossLevel);
	}

	@Override
	public void draw(Graphics g) {
		if (GameFrame.getPlayer().getX() > x) {
			g.drawImage(bossR, (int) x, (int) y, width, height, null);
			isRight = true;
		} else {
			g.drawImage(bossR, (int) x + width, (int) y,-width, height, null);
			isRight = false;
		}
		if (GameFrame.getNumOfHearts() > 0) {
			g.setColor(Color.red);
			for (int i = 0, heartDistance = 30; i < GameFrame.getNumOfHearts(); i++, heartDistance += 30)
				g.fillRect((int) x + heartDistance + 10, (int) y - 30, 30, 8);
		}
	}

	@Override
	public void move() {
		Rnum = (int) (1 + Math.random() * 300);
		if (Rnum == 26)
			new FireBall(isRight ? 1 : -1, 0, x + (width / 2), y - 30);
		else if (Rnum == 226)
			new FireBall(isRight ? 1 : -1, 0, x + (width / 2), y + 90);
		else if (Rnum == 126)
			new FireBall(isRight ? 1 : -1, 0, x + (width / 2), y + 210);
		if (GameFrame.getPlayer().getX() > x) {
			if (Rnum == 175)
				new FireBall(0, -1, x + (width / 2), y);
		}

		for (ProjectileObjects projectile : GameFrame.allProjectiles) {
			if (projectile instanceof Arrow) {
				if (getBoundsTOP().intersects(projectile.getBounds()) || getBoundsBottom().intersects(projectile.getBounds())) {
					if (GameFrame.getNumOfHearts() > 1) {
						GameFrame.setNumOfHearts(GameFrame.getNumOfHearts() - 1);
						GameFrame.sound.playSound(Sounds.monsterHurt);
					}
					else{
						die();
						GameFrame.setNumOfHearts(GameFrame.getNumOfHearts() - 1);
					}
					projectile.die();
				}
			}
		}
	}

	public void die(){
		isAlive = false;
		GameFrame.sound.playSound(Sounds.bossDie);
	}
}