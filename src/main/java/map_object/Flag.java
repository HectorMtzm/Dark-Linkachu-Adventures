package map_object;


import player.GameObjects;
import utility.GameFrame;
import utility.LevelCreator;

import java.awt.*;

import javax.swing.ImageIcon;

public class Flag extends MapObjects {
	boolean createLevel2, createLevel3, createBossLevel;

	static final Image flag = new ImageIcon("src/main/resources/images/flagg.png").getImage();
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
			GameObjects.clearLvl();
			level++;
			if (level == 2)
				createLevel2 = true;
			else if (level == 3)
				createLevel3 = true;
			else
				createBossLevel = true;
		}
		if (createLevel2) {
			GameFrame.getPlayer().setArrows(0);
			new LevelCreator("level2");
			createLevel2 = false;
		}
		if (createLevel3) {
			GameFrame.getPlayer().setArrows(0);
			new LevelCreator("level3");
			createLevel3 =false;
		}
		if (createBossLevel) {
			GameFrame.getPlayer().setArrows(0);
			new LevelCreator("level4");
			createBossLevel = false;
		}
	}

	public static int getLevel() {
		return level;
	}

	public static void setLevel(int level) {
		Flag.level = level;
	}
}