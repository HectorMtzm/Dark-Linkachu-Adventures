package map_object;

import java.awt.*;

import javax.swing.ImageIcon;

public class Block extends MapObjects {

	static final Image block = new ImageIcon("src/main/resources/images/block.png").getImage();

	public Block(float x, float y) {
		this.x = x;
		this.y = y;
		this.width = 90;
		this.height = 90;
	}

	public void draw(Graphics g) {
		g.drawImage(block, (int) x, (int) y, null);
	}

	public void move() {
	}

}