package consumable;

import player.GameObjects;

import java.awt.Graphics;

import javax.swing.ImageIcon;

public class Gems extends ConsumableObject {
	static java.awt.Image gems = new ImageIcon("gemY.png").getImage();

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