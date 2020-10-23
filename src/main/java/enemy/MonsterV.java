package enemy;


import map_object.Blocks;
import map_object.MapObjects;
import projectile.FireBall;
import utility.GameFrame;

import java.awt.*;

import javax.swing.ImageIcon;

// Monster that moves vertically

public class MonsterV extends EnemyObjects {
	static final Image monsterV = new ImageIcon("src/main/resources/images/monsterB.png").getImage();
	int Rnum;

	public MonsterV(float x, float y, int velY) {
		width = 90;
		height = 65;
		this.x = x;
		this.y = y;
		this.velY = velY;
	}

	@Override
	public void draw(Graphics g) {
		g.drawImage(monsterV, (int) x, (int) y, null);
	}

	@Override
	public void move() {
		Rnum = (int) (1 + Math.random() * 400);
		if (Rnum == 26)
			new FireBall(GameFrame.getPlayer().getX() > x ? 1 : -1, 0, x + (width/2), y  + (height/2));
		y += velY;

		for (MapObjects mapObject : GameFrame.allMapObjects) {
			if (mapObject instanceof Blocks) {
				if (getBounds().intersects(mapObject.getBounds())) {
					velY *= -1;
				}
			}
		}
	}
}
