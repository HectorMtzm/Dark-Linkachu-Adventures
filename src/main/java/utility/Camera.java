package utility;

import player.GameObjects;
import player.Player;

import java.awt.Graphics;
import java.awt.Graphics2D;

public class Camera extends GameObjects {

	public Camera(float x, float y) {
		this.x = x;
		this.y = y;
		width = 960;
		height = 720;
	}

	public void draw(Graphics g) {
		Graphics2D g2d = (Graphics2D) g;
		g2d.translate(x, y);
		move(GameFrame.getPlayer());
		g2d.translate(-x, -y);

	}

	public void move() {

	}

	public void move(Player player) {
		if (GameFrame.getPlayer().getX() < 960 / 2 - 60)
			x = 0;
		else if (GameFrame.getPlayer().getX() > GameFrame.getLevelWidth())
			x = (GameFrame.getLevelWidth() - (960 / 2 - 60));
		else
			x = (GameFrame.getPlayer().getX() - 420);

		if (GameFrame.getPlayer().getY() < 500)
			y = 0;
		else
			y = (GameFrame.getPlayer().getY() - 500);
	}



}