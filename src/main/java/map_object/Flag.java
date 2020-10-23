package map_object;


import player.GameObjects;
import utility.GameFrame;
import utility.LevelCreator;

import java.awt.*;

import javax.swing.ImageIcon;

public class Flag extends MapObjects {									//objective 2
	boolean CREATELEVEL2, CREATELEVEL3, CREATEBOSSLEVEL;

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
				CREATELEVEL2 = true;
			else if (level == 3)
				CREATELEVEL3 = true;
			else
				CREATEBOSSLEVEL = true;
		}
		if (CREATELEVEL2) {
			GameFrame.getPlayer().setArrows(0);
			new LevelCreator("level2");
			CREATELEVEL2 = false;
		}
		if (CREATELEVEL3) {
			GameFrame.getPlayer().setArrows(0);
			new LevelCreator("level3");
			CREATELEVEL3=false;
		}
		if (CREATEBOSSLEVEL) {
			GameFrame.getPlayer().setArrows(0);
			new LevelCreator("level4");
			CREATEBOSSLEVEL = false;
		}
	}

	public static int getLevel() {
		return level;
	}

	public static void setLevel(int level) {
		Flag.level = level;
	}
}