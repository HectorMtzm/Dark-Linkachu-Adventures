package enemy;


import enemy.EnemyObjects;
import projectile.FireBall;
import utility.GameFrame;

import java.awt.*;

import javax.swing.ImageIcon;

public class MonsterFire extends EnemyObjects {											//static monster.
	Image monsterFR = new ImageIcon("src/main/resources/images/monsterFR.png").getImage();

	int Rnum;

	public MonsterFire(float x, float y) {
		width = 95;
		height = 95;
		this.x = x;
		this.y = y;
	}

	@Override
	public void draw(Graphics g) {
		if (GameFrame.getPlayer().getX() > x) {
			g.drawImage(monsterFR, (int) x, (int) y, null);
			isRight = true;												
		} else {
			g.drawImage(monsterFR, (int) x + width, (int) y,-width, height, null);
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