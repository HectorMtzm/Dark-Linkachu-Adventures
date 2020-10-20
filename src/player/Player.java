package player;

import consumable.ConsumableObject;
import consumable.Gems;
import consumable.Shield;
import consumable.Star;
import enemy.*;
import map_object.*;
import projectile.Arrow;
import projectile.FireBall;
import projectile.ProjectileObjects;
import utility.AePlayWave;
import utility.GameFrame;
import utility.LevelCreator;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import javax.swing.ImageIcon;

public class Player extends GameObjects implements KeyListener {
	static boolean CREATELEVEL1 = false;
	java.awt.Image playerR = new ImageIcon("playerR.png").getImage();
	java.awt.Image playerL = new ImageIcon("playerL.png").getImage();

	AePlayWave die = new AePlayWave("youDie.wav");
	AePlayWave shield = new AePlayWave("shieldTake.wav");
	AePlayWave gem = new AePlayWave("gem.wav");

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
			g.drawImage(playerL, (int) x, (int) y, null);
		if (guard) {
			g.drawImage(GameFrame.getShield(), (int) (GameFrame.cam.x + 220), 40, 70, 70, null);
		}
		if(superS){
			g.drawImage(Star.star, (int) x+35, (int)y-20, 30, 30, null);
		}
		
		g.drawImage(GameFrame.gbow, (int) GameFrame.cam.x + 20, 40, 70, 70, null);
		g.drawString("" + this.arrows, (int) (GameFrame.cam.x + 60), 50);

		g.drawImage(GameFrame.gems, (int) (GameFrame.cam.x + 120), 40, 69, 55, null);
		g.drawString("" + this.numOfGems, (int) (GameFrame.cam.x + 185), 55);
		if(GameFrame.getNumOfHearts() == 0){
			g.drawString("CONGRATULATIONS! You're alright buddy. Stay in school!", (int) (this.x-220), (int)this.y-70);  //end of the game
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
					if (this.getBounds().intersects(mapObject.getBounds())) {
						die.start();
						playerDie();
					}
				}
			}
			else if (mapObject instanceof Blocks || mapObject instanceof Floor) {
				collisionTopSidesBottom(mapObject);
				if (this.getBoundsTOP().intersects(mapObject.getBounds())) {
					this.velY = 0;
					Gravity = 0;
					this.y = this.y + mapObject.getHeight() - mapObject.getHeight() + 1;
					this.falling = true;
					this.jump = false;
				}
			}
			else if (mapObject instanceof SpecialBlocks) {
				if (this.getBoundsTOP().intersects(mapObject.getBounds())) {
					this.y = this.y + 1;
					this.velY = 0;
					this.Gravity = 0;
					this.falling = true;
					this.jump = false;
					if (mapObject.hit == 1) {
						System.out.println(((SpecialBlocks) mapObject).getObject());
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
				if (this.getBounds().intersects(enemy.getBounds())) {
					if (guard) {
						shield.start();
						enemy.Die();
						guard = false;
						enemy.monsterDie.start();
					}
					else if (superS) {
						enemy.Die();
					}
					else {
						playerDie();
						die.start();
					}
				}
			}

			else if (enemy instanceof MonsterH || enemy instanceof MonsterV) {
				if (this.getBoundsBottom().intersects(enemy.getBounds())) {
					enemy.Die();
					enemy.monsterDie.start();
					this.falling = false;
					this.jump = true;
					Gravity = 4;

				}

				else if (this.getBoundsLeft().intersects(enemy.getBounds())) {
					if (guard) {
						shield.start();
						enemy.Die();
						guard = false;
						enemy.monsterDie.start();
					} else if (superS) {
						enemy.Die();
						enemy.monsterDie.start();
					} else {
						playerDie();
						die.start();
					}
				}

				else if (this.getBoundsRight().intersects(enemy.getBounds())) {
					if (guard) {
						shield.start();
						enemy.Die();
						guard = false;
						enemy.monsterDie.start();
					} else if (superS) {
						enemy.Die();
						enemy.monsterDie.start();
					} else {
						playerDie();
						die.start();
					}
				}

				else if (this.getBoundsTOP().intersects(enemy.getBounds())) {
					if (guard) {
						shield.start();
						enemy.Die();
						guard = false;
						enemy.monsterDie.start();
					} else if (superS) {
						enemy.Die();
						enemy.monsterDie.start();
					} else {
						playerDie();
						die.start();
					}
				}
				if (enemy instanceof Boss) {
					if (superS) continue;
					else if (this.getBounds().intersects(enemy.getBounds())) {
						guard = false;
						playerDie();
//						die.start();
					}
				}
			}
		}

		for (ConsumableObject consumable : GameFrame.allConsumables) {
			if (consumable instanceof Gems) {
				if (this.getBounds().intersects(consumable.getBounds())) {
					gem.start();
					this.numOfGems++;
					consumable.Die();
				}
			}
		}

		for (ProjectileObjects projectile : GameFrame.allProjectiles){
			if (projectile instanceof FireBall) {
				if (this.getBounds().intersects(projectile.getBounds())) {
					if (guard) {
						shield.start();
						projectile.Die();
						guard = false;
					} else if (superS) {
						projectile.Die();
					} else {
						playerDie();
						die.start();
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
			Gravity -= 0.1;
			setVelY(-Gravity);

			if (Gravity <= 0.0) {
				jump = false;
				falling = true;
			}
		}
		if (falling) {
			Gravity += 0.1;
			setVelY(Gravity);
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
		if (this.getBoundsBottom().intersects(mapObject.getBounds())) {
			this.y = this.y - 1;
			this.velY = 0;
			falling = false;
		} else {
			if (!falling && !jump) {
				Gravity = 0.0f;
				falling = true;
			}
		}
		if (this.getBoundsRight().intersects(mapObject.getBounds())) {
			this.velX = 0;
			this.x = this.x - mapObject.getWidth() + mapObject.getWidth() - 1;
		}
		if (this.getBoundsLeft().intersects(mapObject.getBounds())) {
			this.velX = 0;
			this.x = this.x - mapObject.getWidth() + mapObject.getWidth() + 1;
		}
	}

	public void keyPressed(KeyEvent e) {
		if (e.getKeyCode() == KeyEvent.VK_SPACE && jump == false) {
			jump = true;
			falling = true;
			Gravity = 6.8f;
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
}
