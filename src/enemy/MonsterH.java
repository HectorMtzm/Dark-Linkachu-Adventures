package enemy;

import map_object.Blocks;
import map_object.MapObjects;
import map_object.SpecialBlocks;
import projectile.FireBall;
import utility.GameFrame;

import java.awt.Graphics;

import javax.swing.ImageIcon;

public class MonsterH extends EnemyObjects {												//monster that moves from left to right
	static java.awt.Image monsterH = new ImageIcon("monsterB.png").getImage();
	int Rnum;

	public MonsterH(float x, float y, int velX) {
		width = 90;
		height = 65;
		this.x = x;
		this.y = y;
		this.velX = velX;
	}

	@Override
	public void draw(Graphics g) {
		g.drawImage(monsterH, (int) x, (int) y, null);
	}
	
	@Override
	public void move() {
		Rnum = (int) (1 + Math.random() * 400);				//shoots randomly
		if (Rnum == 26)
			new FireBall(0, GameFrame.getPlayer().getY() > y ? 1 : -1, x, y);
		x += velX;

		for (MapObjects mapObject : GameFrame.allMapObjects) {
			if (mapObject instanceof Blocks || mapObject instanceof SpecialBlocks) {
				if (this.getBounds().intersects(mapObject.getBounds())) {
					this.velX *= -1;
				}
			}
		}
	}
}