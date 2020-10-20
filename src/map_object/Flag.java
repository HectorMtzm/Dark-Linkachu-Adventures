package map_object;

import consumable.Gems;
import enemy.Boss;
import player.GBow;
import enemy.MonsterFire;
import enemy.MonsterH;
import enemy.MonsterV;
import player.GameObjects;
import utility.GameFrame;

import java.awt.Graphics;

import javax.swing.ImageIcon;

public class Flag extends MapObjects {									//objective 2
	boolean CREATELEVEL2, CREATELEVEL3, CREATEBOSSLEVEL;
	long gameOver;

	static java.awt.Image flag = new ImageIcon("flagg.png").getImage();
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
		if (this.getBounds().intersects(GameFrame.getPlayer().getBounds()) && GameFrame.getPlayer().getNumOfGems() == 5) {
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
			new Floor(-10, 650);
			new GBow(1000, 180, 90, 90);
			new Blocks(200, 470);
			new Blocks(400, 270);
			new Blocks(1000, 270);
			new Blocks(800, 370);
			new Blocks(1200, 465);
			new Spikes(800, 627);
			new Spikes(900, 627);
			new Spikes(1100, 627);
			new Gems(1000, 590);
			new Gems(1200, 350);
			new MonsterFire(1200, 550);
			new SpecialBlocks(1700, 465,1);
			new Gems(1700, 350);					
			new Blocks(2000, 305);
			new MonsterH(500, 305, -1);
			new Gems(2000, 150);
			new MonsterFire(1700, 550);
			new Blocks(2300, 465);
			new Gems(2300, 350);
			new MonsterV(2000, 550, -1);
			new Blocks(2800, 465);
			new SpecialBlocks(1450, 400,(int) (1 + Math.random() * 2));
			new MonsterFire(3800, 550);
			new MonsterFire(3400, 550);
			new MonsterFire(3000, 550);
			new Spikes(500, 627);
			new Flag(3900, 450);
			CREATELEVEL2 = false;
		}
		if (CREATELEVEL3) {
			GameFrame.getPlayer().setArrows(0);
			new Floor(-10, 650);
			new SpecialBlocks(600, 320, (int) (1 + Math.random() * 2));
			new Blocks(840, 550);
			new Gems(995, 350);
			new Blocks(995, 440); // left side
			new Blocks(1095, 310);
			new MonsterV(1160, 450, 1);
			new Wall(1280,280,50,480);
			new Gems(1700,150);
			
			new SpecialBlocks(1200, 100, 3);
			new Blocks(1500, 350);		//Middle part
			new Gems(1200, 20);
			new MonsterFire(1350, 560);
			
			new Blocks(1900, 500);
			new Blocks(2050, 310);
			new Gems(2150, 110);
			new Blocks(2250, 310);
			new SpecialBlocks(2600, 450, (int) (1 + Math.random() * 2));	//Right side
			new MonsterH(2200, 450, 1);
			new Spikes(3500, 627);
			new Gems(3500, 320);
			new MonsterFire(3000, 560);
			new Flag(3900, 460);
			CREATELEVEL3=false;
		}
		if (CREATEBOSSLEVEL) {
			GameFrame.getPlayer().setArrows(0);
			new Floor(-10, 650);
			new Blocks(1900, 505);
			new Blocks(1990, 355); // left side
			new Blocks(2080, 195);

			new MonsterV(1100,300,-1);
			new Blocks(1100,0);
			new SpecialBlocks(1500, 400, 1);
			new Boss(2087, 325);
			new SpecialBlocks(2920, 400, 1);
			new Blocks(3300,0);
			new MonsterV(3300, 300, -1);

			new Blocks(2420, 505);
			new Blocks(2330, 355); // right side
			new Blocks(2240, 195);
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