package consumable;

import utility.GameFrame;
import utility.Sounds;

import java.awt.*;

import javax.swing.ImageIcon;

public class Shield extends ConsumableObject {
	static Image shield = new ImageIcon("src/main/resources/images/shield.png").getImage();

	public Shield(float x, float y, int width, int height) {
		this.x = x;
		this.y = y;
		this.width = width;
		this.height = height;
	}

	public void draw(Graphics g) {
		g.drawImage(shield, (int) x, (int) y, null);
	}

	public void move() {
		if (getBounds().intersects(GameFrame.getPlayer().getBounds())) {
			die();
			GameFrame.getPlayer().setGuard(true);
		}
	}

	public void die(){
		isAlive = false;
		GameFrame.sound.playSound(Sounds.shield);
	}
}
