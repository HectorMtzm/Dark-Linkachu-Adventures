package map_object;


import utility.GameFrame;
import utility.LevelCreator;

import java.awt.*;

import javax.swing.ImageIcon;

public class Flag extends MapObjects {

	static final Image flag = new ImageIcon("src/main/resources/images/flag.png").getImage();
	static int level = 1;

	public Flag(float x, float y) {
		this.x = x;
		this.y = y;
		width = 108;
		height = 200;
	}

	public void draw(Graphics g) {
		g.drawImage(flag, (int) x, (int) y, null);
	}

	public void move() {
		if (getBounds().intersects(GameFrame.getPlayer().getBounds()) && GameFrame.getPlayer().getNumOfGems() == 5) {
			GameFrame.clearLvl();
			level++;
			if (level == 2)
				new LevelCreator("level2");
			else if (level == 3)
				new LevelCreator("level3");
			else
				new LevelCreator("level4");
		}
	}

	public static int getLevel() {
		return level;
	}

	public static void setLevel(int level) {
		Flag.level = level;
	}
}