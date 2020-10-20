package consumable;

import utility.GameFrame;

import java.awt.Graphics;

import javax.swing.ImageIcon;

public class Shield extends ConsumableObject {
	static java.awt.Image shield = new ImageIcon("shield.png").getImage();

	public Shield(float x, float y, int width, int height) {
		this.x = x;
		this.y = y;
		this.width = width;
		this.height = height;
	}

	public void draw(Graphics g) {
		g.drawImage(shield, (int) x, (int) y, null);
	}

	public void move() {
		if (this.getBounds().intersects(GameFrame.getPlayer().getBounds())) {
			this.Die();
			sh.start();
			GameFrame.getPlayer().setGuard(true);
		}
	}
}
