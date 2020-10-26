package consumable;

import utility.GameFrame;
import utility.Sounds;

import java.awt.*;

import javax.swing.ImageIcon;

public class Shield extends ConsumableObject {
	static final Image shield = new ImageIcon("src/main/resources/images/shield.png").getImage();

	public Shield(float x, float y) {
		this.x = x;
		this.y = y;
		this.width = 90;
		this.height = 90;
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
