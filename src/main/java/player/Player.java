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

public class Player implements KeyListener {
	final Image playerR = new ImageIcon("src/main/resources/images/player.png").getImage();

	private boolean isRight;
	private float gravity = 0.1f;
	private final int width;
	private final int height;
	private float velX, velY;
	private float x, y;

	private boolean jumping = false;
	private boolean moveRight, moveLeft;
	private boolean guard, superS;
	private boolean shoot;
	private int arrows = 0;
	private int numOfGems;
	private long lastFireTime;
	private long takeStar;

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
		if (isRight)
			g.drawImage(playerR, (int) x, (int) y, null);
		else
			g.drawImage(playerR, (int) x + width, (int) y,-width, height, null);
		if (superS){
			g.drawImage(Star.star, (int) x+35, (int)y-20, 30, 30, null);
		}
	}

	public void move() {
		gravity = 0.1f;
		velY += gravity;

		if (x < 0)
			x = 0;
		else if (x > GameFrame.getLevelWidth() - 90)
			x = GameFrame.getLevelWidth() - 90;

		if (y > GameFrame.getLevelHeight())
			playerDie();

		for (MapObjects mapObject : GameFrame.allMapObjects){
			if (mapObject instanceof Spikes) {
				if (superS) continue;
				else {
					if (getBounds().intersects(mapObject.getBounds())) {
						GameFrame.sound.playSound(Sounds.youDie);
						playerDie();
						break;
					}
				}
			}
			else if (mapObject instanceof Block) {
				blocksTopSidesCollisionDetection(mapObject);
				if (getBoundsTop().intersects(mapObject.getBounds())) {
					velY = 0;
					y = y + mapObject.getHeight() - mapObject.getHeight() + 1;
				}
			}
			else if (mapObject instanceof SpecialBlocks) {
				if (getBoundsTop().intersects(mapObject.getBounds())) {
					y = y + 1;
					velY = 0;
					if (mapObject.hit == 1) {
						mapObject.hit--;
						if (((SpecialBlocks) mapObject).getObject() == 1)
							new GBow(mapObject.getX(), mapObject.getY() - 90);
						else if (((SpecialBlocks) mapObject).getObject() == 2)
							new Shield(mapObject.getX(), mapObject.getY() - 90);
						else {
							new Star(mapObject.getX(), mapObject.getY() - 90);
						}
					}
				}
				blocksTopSidesCollisionDetection(mapObject);
			}
		}

		for (EnemyObjects enemy : GameFrame.allEnemies){
			if (enemy instanceof MonsterFire) {
				if (monstersSideCollisionDetection(enemy)) break;
			}

			else if (enemy instanceof MonsterH || enemy instanceof MonsterV) {
				if (getBoundsBottom().intersects(enemy.getBounds())) {
					enemy.die(false);
					velY = -5f;
				}

				else if (monstersSideCollisionDetection(enemy)) break;

			}

			else if (enemy instanceof Boss) {
				if (superS) continue;
				else if (getBounds().intersects(enemy.getBounds())) {
					guard = false;
					playerDie();
					break;
				}
			}
		}

		for (ConsumableObject consumable : GameFrame.allConsumables) {
			if (consumable instanceof Gems) {
				if (getBounds().intersects(consumable.getBounds())) {
					GameFrame.sound.playSound(Sounds.gem);
					numOfGems++;
					consumable.die();
				}
			}
		}

		for (ProjectileObjects projectile : GameFrame.allProjectiles){
			if (projectile instanceof FireBall) {
				if (getBounds().intersects(projectile.getBounds())) {
					if (superS) {
						projectile.die();
					}
					else if (guard) {
						projectile.die();
						guard = false;
						GameFrame.sound.playSound(Sounds.shield);
					}
					else {
						playerDie();
						break;
					}
				}
			}
		}

		x += velX;
		y += velY;
		if (moveRight)
			velX = 2;
		if (moveLeft)
			velX = -2;

		long timeElapsedA = System.currentTimeMillis() - lastFireTime;
		if (shoot && timeElapsedA > 500 && arrows > 0) {
			lastFireTime = System.currentTimeMillis();
			arrows--;
			new Arrow(isRight ? 1 : -1, x, y);
		}

		long timeElapsedS = System.currentTimeMillis() - takeStar;

		if (superS && timeElapsedS > 5000) {
			superS = false;
		}

	}

	private boolean monstersSideCollisionDetection(EnemyObjects enemy) {
		if (getBounds().intersects(enemy.getBounds())) {
			if (superS) {
				enemy.die(false);
			}
			else if (guard) {
				GameFrame.sound.playSound(Sounds.shield);
				enemy.die(false);
				guard = false;
			}
			else {
				playerDie();
				return true;
			}
		}
		return false;
	}

	private void blocksTopSidesCollisionDetection(MapObjects mapObject) {
		if (getBoundsBottom().intersects(mapObject.getBounds())) {
			y = mapObject.getY() - 88;
			jumping = false;
			gravity = 0;
			velY = 0;
		}

		if (getBoundsRight().intersects(mapObject.getBounds())) {
			velX = 0;

			if (moveLeft)
				velX -= 1;
		}
		else if (getBoundsLeft().intersects(mapObject.getBounds())) {
			velX = 0;

			if (moveRight)
				velX += 1;
		}
	}

	// Returns player to level 1
	public void playerDie(){
		GameFrame.sound.playSound(Sounds.youDie);
		Flag.setLevel(1);
		GameFrame.clearLvl();
		new LevelCreator("level1");
		guard = false;
	}

	public Rectangle getBounds() {
		return new Rectangle((int) x + 5, (int) y, width - 10, height);
	}

	public Rectangle getBoundsTop() {
		return new Rectangle((int) x + (width / 2 - 5) - ((width / 2) / 2), (int) y, width / 2 + 10, height / 2);
	}

	public Rectangle getBoundsBottom() {
		return new Rectangle((int) x + (width / 2 - 5) - ((width / 2) / 2), (int) y + (height / 2) + 1, width / 2 + 10,	height / 2);
	}

	public Rectangle getBoundsLeft() {
		return new Rectangle((int) x + 5, (int) y + 5, 5, height - 10);
	}

	public Rectangle getBoundsRight() {
		return new Rectangle((int) x + width - 10, (int) y + 5, 5, height - 10);
	}

	public void keyPressed(KeyEvent e) {
		if (e.getKeyCode() == KeyEvent.VK_SPACE && !jumping) {
			y -= 1;
			velY = -6.8f;
			jumping = true;
		}
		if (e.getKeyCode() == KeyEvent.VK_RIGHT) {
			moveRight = true;
			isRight = true;
		}
		if (e.getKeyCode() == KeyEvent.VK_LEFT) {
			moveLeft = true;
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
			moveRight = false;
			velX = 0;
		}
		if (e.getKeyCode() == KeyEvent.VK_LEFT) {
			moveLeft = false;
			velX = 0;
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

	public void setTakeStar(long takeStar) {
		this.takeStar = takeStar;
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

	public boolean isGuard() {
		return guard;
	}

	public float getX() {
		return x;
	}

	public float getY() {
		return y;
	}

	public void setX(float x) {
		this.x = x;
	}

	public void setY(float y) {
		this.y = y;
	}
}