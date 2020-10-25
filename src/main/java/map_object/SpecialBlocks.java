package map_object;

import java.awt.*;

import javax.swing.ImageIcon;

//Creates a random power up when the player hits it from below

public class SpecialBlocks extends MapObjects {
	final Image specialBlocks = new ImageIcon("src/main/resources/images/specialBlock.png").getImage();
	final int object;

	public SpecialBlocks(float x, float y, int object) {
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
			g.drawImage(Block.block, (int) x, (int) y, null);
	}

	public void move() {
	}

	public int getObject() {
		return object;
	}
}
