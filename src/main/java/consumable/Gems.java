package consumable;

import java.awt.*;

import javax.swing.ImageIcon;

public class Gems extends ConsumableObject {
	static final Image gems = new ImageIcon("src/main/resources/images/gemY.png").getImage();

	public Gems(float x, float y) {
		this.x = x;
		this.y = y;
		width = 69;
		height = 55;
	}

	public void draw(Graphics g) {
		g.drawImage(gems, (int) x, (int) y, null);
	}

	public void move() {

	}

}