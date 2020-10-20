package player;

import utility.GameFrame;

import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.geom.AffineTransform;

import javax.swing.ImageIcon;

public class GBow extends GameObjects {
	static java.awt.Image gbow;

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
		gbow = new ImageIcon("gbow.png").getImage();
	}

	public void move() {
		if (this.getBounds().intersects(GameFrame.getPlayer().getBounds())) {
			this.Die();
			GameFrame.getPlayer().arrows += 3;
		}
	}
}