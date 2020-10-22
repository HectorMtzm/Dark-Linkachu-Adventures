package map_object;

import java.awt.*;

import javax.swing.ImageIcon;

public class SpecialBlocks extends MapObjects {
	Image specialBlocks = new ImageIcon("src/main/resources/images/specialBlock.png").getImage();
	int object;

	public SpecialBlocks(float x, float y, int object) { //Create power up when the main.java.player hit it from below
		this.x = x;
		this.y = y;
		width = 90;
		height = 90;
		this.object = object;
		hit = 1;
	}

	public void draw(Graphics g) {
		if (hit == 1)
			g.drawImage(specialBlocks, (int) x, (int) y, null);
		else
			g.drawImage(Blocks.block, (int) x, (int) y, null);
	}

	public void move() {
	}

	public int getObject() {
		return object;
	}

	public void setObject(int object) {
		this.object = object;
	}
}