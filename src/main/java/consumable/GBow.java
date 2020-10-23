package consumable;

import consumable.ConsumableObject;
import utility.GameFrame;

import java.awt.*;
import java.awt.geom.AffineTransform;

import javax.swing.ImageIcon;

public class GBow extends ConsumableObject {
	static final Image gbow = new ImageIcon("src/main/resources/images/gbow.png").getImage();

	public GBow(float x, float y, int width, int height) {
		this.x = x;
		this.y = y;
		this.width = width;
		this.height = height;
	}

	public void draw(Graphics g) {
		AffineTransform at = new AffineTransform();
		at.setTransform(GameFrame.getIdentity());
		at.translate(x, y);
		Graphics2D gb = (Graphics2D) g;
		gb.drawImage(gbow, at, null);
	}

	public void move() {
		if (getBounds().intersects(GameFrame.getPlayer().getBounds())) {
			die();
			GameFrame.getPlayer().setArrows(GameFrame.getPlayer().getArrows() + 3);
		}
	}
}