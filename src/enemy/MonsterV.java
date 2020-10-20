package enemy;

import map_object.Blocks;
import map_object.MapObjects;
import projectile.FireBall;
import utility.GameFrame;

import java.awt.Graphics;

import javax.swing.ImageIcon;

public class MonsterV extends EnemyObjects {												//monster that moves up and down
	static java.awt.Image monsterV = new ImageIcon("monsterB.png").getImage();
	int Rnum;

	public MonsterV(float x, float y, int velY) {
		width = 90;
		height = 65;
		this.x = x;
		this.y = y;
		this.velY = -1;
	}

	@Override
	public void draw(Graphics g) {
		g.drawImage(monsterV, (int) x, (int) y, null);
	}

	@Override
	public void move() {
		Rnum = (int) (1 + Math.random() * 400);
		if (Rnum == 26)
			new FireBall(GameFrame.getPlayer().getX() > x ? 1 : -1, 0, x, y);
		y += velY;

		for (MapObjects mapObject : GameFrame.allMapObjects) {
			if (mapObject instanceof Blocks) {
				if (this.getBounds().intersects(mapObject.getBounds())) {
					this.velY *= -1;
				}
			}
		}
	}
}
