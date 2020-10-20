package enemy;

import projectile.FireBall;
import utility.GameFrame;

import java.awt.Graphics;

import javax.swing.ImageIcon;

public class MonsterFire extends EnemyObjects {											//static monster.
	java.awt.Image monsterFR = new ImageIcon("monsterFR.png").getImage();
	java.awt.Image monsterFL = new ImageIcon("monsterFL.png").getImage();

	int Rnum;

	public MonsterFire(float x, float y) {
		width = 95;
		height = 95;
		this.x = x;
		this.y = y;
	}

	@Override
	public void draw(Graphics g) {
		if (GameFrame.getPlayer().getX() > this.x) {
			g.drawImage(monsterFR, (int) x, (int) y, null);
			isRight = true;												
		} else {
			g.drawImage(monsterFL, (int) x, (int) y, null);
			isRight = false;
		}
	}

	@Override
	public void move() {
		Rnum = (int) (1 + Math.random() * 600);
		if (Rnum == 26) {
			new FireBall(isRight ? 1 : -1, 0, x, y);
		}
	}
}