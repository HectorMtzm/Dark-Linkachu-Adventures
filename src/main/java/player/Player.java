package player;

import consumable.*;
import enemy.*;
import map_object.*;
import projectile.Arrow;
import projectile.FireBall;
import projectile.ProjectileObjects;
import utility.GameFrame;
import utility.LevelCreator;
import utility.Sounds;

import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import javax.swing.ImageIcon;

public class Player extends GameObjects implements KeyListener {
	static boolean CREATELEVEL1 = false;
	Image playerR = new ImageIcon("src/main/resources/images/playerR.png").getImage();

	boolean guard, superS, createGbow, createShield, createStar;				// Create power ups.
	boolean shoot, right, left, jump = false, falling = true;												//Movement and actions.
	int numOfGems;
	int arrows = 0;
	long lastFireTime;
	static long takeStar;

	public Player() {
		x = 20;
		y = 300;
		width = 89;
		height = 89;
		velY = 0;
		velX = 0;
		isRight = true;
	}

	public void draw(Graphics g) {
		g.setColor(Color.WHITE);
		g.setFont(new Font("Comic Sans MS", Font.PLAIN, 20));
		if (isRight)
			g.drawImage(playerR, (int) x, (int) y, null);
		else
			g.drawImage(playerR, (int) x + width, (int) y,-width, height, null);
		if (guard) {
			g.drawImage(GameFrame.getShield(), (int) (GameFrame.cam.x + 220), 40, 70, 70, null);
		}
		if(superS){
			g.drawImage(Star.star, (int) x+35, (int)y-20, 30, 30, null);
		}
		
		g.drawImage(GameFrame.gbow, (int) GameFrame.cam.x + 20, 40, 70, 70, null);
		g.drawString("" + arrows, (int) (GameFrame.cam.x + 60), 50);

		g.drawImage(GameFrame.gems, (int) (GameFrame.cam.x + 120), 40, 69, 55, null);
		g.drawString("" + numOfGems, (int) (GameFrame.cam.x + 185), 55);
		if(GameFrame.getNumOfHearts() == 0){
			g.drawString("CONGRATULATIONS! You're alright buddy. Stay in school!", (int) (x-220), (int)y-70);  //end of the game
			g.drawString("Thanks for saving me Linkachu!! My handsome Prince ^_^ ", (int) 3500, 300);
			g.drawImage(GameFrame.princess, 3700, 408, null);
	
		}
		if(Flag.getLevel() == 1){
			g.drawString("Space bar to jump", (int) 100, 200);
			g.drawString("Long pressing the space bar will make you fall faster and jump higher", (int) 100, 250);
			g.drawString("'X' to shoot", (int) 100, 300);
			g.drawString("get the 5 gems and reach the flag to go to the next level", (int) 100, 400);
			
		}
		
		g.setColor(Color.RED);
		
	}

	public void move() {
		if (x < 0)
			x = 0;
		if (x > 3950)
			x = 3950;

		for (MapObjects mapObject : GameFrame.allMapObjects){
			if (mapObject instanceof Spikes) {
				if (superS) continue;
				else {
					if (getBounds().intersects(mapObject.getBounds())) {
						GameFrame.sound.playSound(Sounds.youDie);
						playerDie();
					}
				}
			}
			else if (mapObject instanceof Blocks || mapObject instanceof Floor) {
				collisionTopSidesBottom(mapObject);
				if (getBoundsTOP().intersects(mapObject.getBounds())) {
					velY = 0;
					gravity = 0;
					y = y + mapObject.getHeight() - mapObject.getHeight() + 1;
					falling = true;
					jump = false;
				}
			}
			else if (mapObject instanceof SpecialBlocks) {
				if (getBoundsTOP().intersects(mapObject.getBounds())) {
					y = y + 1;
					velY = 0;
					gravity = 0;
					falling = true;
					jump = false;
					if (mapObject.hit == 1) {
						mapObject.hit--;
						if (((SpecialBlocks) mapObject).getObject() == 1)
							createGbow = true;
						else if (((SpecialBlocks) mapObject).getObject() == 2)			// some Special blocks will create random power ups
							createShield = true;
						else if (((SpecialBlocks) mapObject).getObject() == 3)
							createStar =true;
					}
				}
				collisionTopSidesBottom(mapObject);
			}

		}

		for (EnemyObjects enemy : GameFrame.allEnemies){
			if (enemy instanceof MonsterFire) {
				if (getBounds().intersects(enemy.getBounds())) {
					if (guard) {
						GameFrame.sound.playSound(GameFrame.sound.shield);
						enemy.die(false);
						guard = false;
					}
					else if (superS) {
						enemy.die(false);
					}
					else {
						playerDie();
//						die.start();
					}
				}
			}

			else if (enemy instanceof MonsterH || enemy instanceof MonsterV) {
				if (getBoundsBottom().intersects(enemy.getBounds())) {
					enemy.die(false);
					falling = false;
					jump = true;
					gravity = 4;

				}

				else if (getBounds().intersects(enemy.getBounds())) {
					if (guard) {
						GameFrame.sound.playSound(GameFrame.sound.shield);
						enemy.die(false);
						guard = false;
					} else if (superS) {
						enemy.die(false);
					} else {
						playerDie();
					}
				}

			}

			else if (enemy instanceof Boss) {
				if (superS) continue;
				else if (getBounds().intersects(enemy.getBounds())) {
					guard = false;
					playerDie();
				}
			}
		}

		for (ConsumableObject consumable : GameFrame.allConsumables) {
			if (consumable instanceof Gems) {
				if (getBounds().intersects(consumable.getBounds())) {
					GameFrame.sound.playSound(GameFrame.sound.gem);
					numOfGems++;
					consumable.die();
				}
			}
		}

		for (ProjectileObjects projectile : GameFrame.allProjectiles){
			if (projectile instanceof FireBall) {
				if (getBounds().intersects(projectile.getBounds())) {
					if (guard) {
						projectile.die();
						guard = false;
					} else if (superS) {
						projectile.die();
					} else {
						playerDie();
					}
				}
			}
		}

		x += velX;
		y += velY;
		if (right) {
			velX = 2;

		}
		if (left) {
			velX = -2;

		}

		float timeElapsedA = (float) (System.currentTimeMillis() - lastFireTime);
		if (shoot && timeElapsedA > 500 && arrows > 0) {
			lastFireTime = System.currentTimeMillis();									//only can shoot every .5 seconds
			arrows--;
			new Arrow(isRight ? 1 : -1, x, y);

		}
		if (jump) {
			gravity -= 0.1;
			setVelY(-gravity);

			if (gravity <= 0.0) {
				jump = false;
				falling = true;
			}
		}
		if (falling) {
			gravity += 0.1;
			setVelY(gravity);
		}
		float timeElapsedS = (float) (System.currentTimeMillis() - takeStar);

		if (superS && timeElapsedS > 5000) {												//the star last 5 seconds
			superS = false;
		}

		if (createGbow) {
			new GBow(x, y - 180, 90, 90);
			createGbow = false;
		}
		if (createShield) {
			new Shield(x, y - 180, 80, 80);				// the only way we found to create the objects while playing
			createShield = false;
		}
		if (createStar) {
			new Star(x, y - 180);
			createStar = false;
		}
		if(CREATELEVEL1){
			new LevelCreator("level1");
			CREATELEVEL1=false;
		}
	}

	private void collisionTopSidesBottom(MapObjects mapObject) {
		if (getBoundsBottom().intersects(mapObject.getBounds())) {
			y = y - 1;
			this.velY = 0;
			falling = false;
		} else {
			if (!falling && !jump) {
				gravity = 0.0f;
				falling = true;
			}
		}
		if (getBoundsRight().intersects(mapObject.getBounds())) {
			velX = 0;
			x = x - mapObject.getWidth() + mapObject.getWidth() - 1;
		}
		if (getBoundsLeft().intersects(mapObject.getBounds())) {
			velX = 0;
			x = x - mapObject.getWidth() + mapObject.getWidth() + 1;
		}
	}

	public void keyPressed(KeyEvent e) {
		if (e.getKeyCode() == KeyEvent.VK_SPACE && jump == false) {
			jump = true;
			falling = true;
			gravity = 6.8f;
		}
		if (e.getKeyCode() == KeyEvent.VK_RIGHT) {
			right = true;
			isRight = true;
		}
		if (e.getKeyCode() == KeyEvent.VK_LEFT) {
			left = true;
			isRight = false;
		}
		if (e.getKeyCode() == KeyEvent.VK_X) {
			shoot = true;

		}
	}

	public void keyReleased(KeyEvent e) {
		if (e.getKeyCode() == KeyEvent.VK_X) {
			shoot = false;
		}

		if (e.getKeyCode() == KeyEvent.VK_RIGHT) {
			right = false;
			setVelX(0);
		}
		if (e.getKeyCode() == KeyEvent.VK_LEFT) {
			left = false;
			setVelX(0);
		}
	}

	public void keyTyped(KeyEvent e) {
	}

	public int getNumOfGems() {
		return numOfGems;
	}

	public void setNumOfGems(int numOfGems) {
		this.numOfGems = numOfGems;
	}

	public void setSuperS(boolean superS) {
		this.superS = superS;
	}

	public static void setTakeStar(long takeStar) {
		Player.takeStar = takeStar;
	}

	public void setGuard(boolean guard) {
		this.guard = guard;
	}

	public void setArrows(int arrows) {
		this.arrows = arrows;
	}

	public int getArrows() {
		return arrows;
	}
}
