package consumable;

import map_object.MapObjects;
import map_object.SpecialBlocks;
import player.Player;
import map_object.Blocks;
import utility.GameFrame;

import java.awt.Graphics;

import javax.swing.ImageIcon;

public class Star extends ConsumableObject {
	public static java.awt.Image star = new ImageIcon("star.png").getImage();
	float Gravity = .5f;
	boolean JUMP = false, FALLING = true;

	public Star(float x, float y) {
		this.x = x;
		this.y = y;
		width = 90;
		height = 90;
		velX = -1;
		velY = -1;
	}

	public void draw(Graphics g) {
		g.drawImage(star, (int) x, (int) y, null);
	}

	public void move() {
		x += velX;
		y += velY;
		if (JUMP) {
			Gravity -= 0.1;
			setVelY(-Gravity);

			if (Gravity <= 0.0) {
				JUMP = false;
				FALLING = true;
			}
		}
		if (FALLING) {
			Gravity += 0.1;
			setVelY(Gravity);
		}
		for (MapObjects mapObject : GameFrame.allMapObjects) {
			if (mapObject instanceof Blocks || mapObject instanceof SpecialBlocks) {
				if (this.getBoundsBottom().intersects(mapObject.getBounds())) {
					this.y = this.y - 1;
					this.velY = 0;
					Gravity = 6;
					FALLING = false;
					JUMP = true;
				}
				if (this.getBoundsTOP().intersects(mapObject.getBounds())) {
					this.velY = 0;
					Gravity = 0;
					this.y = this.y + mapObject.getHeight() - mapObject.getHeight() + 1;
					this.FALLING = true;
					this.JUMP = false;
				}
				if (this.getBoundsRight().intersects(mapObject.getBounds())) {
					this.velX *= -1;
				}
				if (this.getBoundsLeft().intersects(mapObject.getBounds())) {
					this.velX *= -1;
				}
			}
			if (this.getBounds().intersects(GameFrame.getPlayer().getBounds())) {
				this.Die();
				GameFrame.getPlayer().setSuperS(true);
				Player.setTakeStar(System.currentTimeMillis());

			}

		}
	}
}