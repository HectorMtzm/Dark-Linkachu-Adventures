package map_object;

import java.awt.Graphics;

import javax.swing.ImageIcon;

public class Floor extends MapObjects {
	java.awt.Image floor = new ImageIcon("Bfloor.png").getImage();

	public Floor(float x, float y) {
		this.x = x;
		this.y = y;
		this.width = 4164;
		this.height = 119;
	}

	public void draw(Graphics g) {
		g.drawImage(floor, (int) x, (int) y, null);
	}

	public void move() {
	}
}