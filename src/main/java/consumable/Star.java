package consumable;

import map_object.Block;
import map_object.MapObjects;
import map_object.SpecialBlocks;
import utility.GameFrame;

import java.awt.*;

import javax.swing.ImageIcon;

public class Star extends ConsumableObject {
	public static final Image star = new ImageIcon("src/main/resources/images/star.png").getImage();
	float gravity = .3f;
	boolean jump = false, falling = true;

	public Star(float x, float y) {
		this.x = x + 20;
		this.y = y;
		velX = -1;
		velY = -1;
	}

	public void draw(Graphics g) {
		g.drawImage(star, (int) x-5, (int) y, 60, 60, null);
	}

	public void move() {
		x += velX;
		y += velY;
		if (jump) {
			gravity -= 0.1;
			velY = -gravity;

			if (gravity <= 0.0) {
				jump = false;
				falling = true;
			}
		}
		if (falling) {
			gravity += 0.1;
			velY = gravity;
		}
		for (MapObjects mapObject : GameFrame.allMapObjects) {
			if (mapObject instanceof Block || mapObject instanceof SpecialBlocks) {
				if (getBoundsBottom().intersects(mapObject.getBounds())) {
					y = y - 1;
					velY = 0;
					gravity = 6;
					falling = false;
					jump = true;
				}
				if (getBoundsTOP().intersects(mapObject.getBounds())) {
					velY = 0;
					gravity = 0;
					y = y + mapObject.getHeight() - mapObject.getHeight() + 1;
					falling = true;
					jump = false;
				}
				if (getBoundsRight().intersects(mapObject.getBounds())) {
					velX *= -1;
				}
				if (getBoundsLeft().intersects(mapObject.getBounds())) {
					velX *= -1;
				}
			}
			if (getBounds().intersects(GameFrame.getPlayer().getBounds())) {
				die();
				GameFrame.getPlayer().setSuperS(true);
				GameFrame.getPlayer().setTakeStar(System.currentTimeMillis());

			}

		}
	}
}