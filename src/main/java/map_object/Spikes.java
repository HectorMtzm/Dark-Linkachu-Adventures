package map_object;

import java.awt.*;

import javax.swing.ImageIcon;

public class Spikes extends MapObjects {
	final Image spikes = new ImageIcon("src/main/resources/images/spikes.png").getImage();

	public Spikes(float x, float y) {
		this.x = x;
		this.y = y;
		width = 78;
		height = 26;
	}

	public void draw(Graphics g) {
		g.drawImage(spikes, (int) x, (int) y, null);
	}

	public void move() {

	}
}