package consumable;

import utility.GameFrame;

import java.awt.*;

import javax.swing.ImageIcon;

public class GBow extends ConsumableObject {
	static final Image bow = new ImageIcon("src/main/resources/images/bow.png").getImage();

	public GBow(float x, float y) {
		this.x = x + 20;
		this.y = y;
	}

	public void draw(Graphics g) {
		g.drawImage(bow, (int) x-5, (int) y, 60, 60, null);
	}

	public void move() {
		if (getBounds().intersects(GameFrame.getPlayer().getBounds())) {
			die();
			GameFrame.getPlayer().setArrows(GameFrame.getPlayer().getArrows() + 3);
		}
	}
}