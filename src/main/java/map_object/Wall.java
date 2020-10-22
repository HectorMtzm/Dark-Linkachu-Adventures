package map_object;

import java.awt.*;

import javax.swing.ImageIcon;

class Wall extends MapObjects {
	static Image wall = new ImageIcon("src/main/resources/images/wall.png").getImage();

	public Wall(float x, float y, int width, int height) {
		this.x = x;
		this.y = y;
		this.width = width;
		this.height = height;
	}

	public void draw(Graphics g) {
		g.drawImage(wall, (int) x, (int) y, null);
	}

	public void move() {
	}

}

