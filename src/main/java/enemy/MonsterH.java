package enemy;

import map_object.Block;
import map_object.MapObjects;
import map_object.SpecialBlocks;
import projectile.FireBall;
import utility.GameFrame;

import java.awt.*;

import javax.swing.ImageIcon;

// Monster that moves horizontally

public class MonsterH extends EnemyObjects {
	static final Image monsterH = new ImageIcon("src/main/resources/images/monsterB.png").getImage();

	public MonsterH(float x, float y, int velX) {
		width = 90;
		height = 65;
		this.x = x;
		this.y = y + 1;
		this.velX = velX;
	}

	@Override
	public void draw(Graphics g) {
		g.drawImage(monsterH, (int) x, (int) y, null);
	}
	
	@Override
	public void move() {
		randomNum = (int) (1 + Math.random() * 400);
		if (randomNum == 26)
			new FireBall(0, GameFrame.getPlayer().getY() > y ? 1 : -1,x + (width/2),y  + (height/2));
		x += velX;

		for (MapObjects mapObject : GameFrame.allMapObjects) {
			if (mapObject instanceof Block || mapObject instanceof SpecialBlocks) {
				if (getBounds().intersects(mapObject.getBounds())) {
					velX *= -1;
				}
			}
		}
	}
}