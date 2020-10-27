package consumable;

import utility.GameFrame;
import utility.Sounds;

import java.awt.*;

import javax.swing.ImageIcon;

public class Shield extends ConsumableObject {
	static final Image shield = new ImageIcon("src/main/resources/images/shield.png").getImage();

	public Shield(float x, float y) {
		this.x = x + 20;
		this.y = y;
	}

	public void draw(Graphics g) {
		g.drawImage(shield, (int) x-5, (int) y, 60, 60, null);
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
