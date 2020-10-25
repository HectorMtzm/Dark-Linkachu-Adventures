package utility;

import consumable.ConsumableObject;
import enemy.Boss;
import enemy.EnemyObjects;
import map_object.Flag;
import map_object.MapObjects;
import map_object.UntouchableObjects;
import player.GameObjects;
import player.Player;
import projectile.ProjectileObjects;

import java.awt.*;
import java.awt.geom.AffineTransform;
import java.util.ArrayList;

import javax.swing.ImageIcon;
import javax.swing.JFrame;

@SuppressWarnings("serial")
public class GameFrame extends JFrame {

	private Image raster;
	private Graphics g;
	static final boolean GameRunning = true;

	public static Sounds sound;
	public static Camera cam;

	static int numOfHearts = 5;
	static final AffineTransform identity = new AffineTransform();
	private static Player player;
	private static int levelHeight;
	private static int levelWidth;

	private final Image Background = new ImageIcon("src/main/resources/images/background.png").getImage();
	private static final Image gbow = new ImageIcon("src/main/resources/images/gbow.png").getImage();
	private static final Image shield = new ImageIcon("src/main/resources/images/shield.png").getImage();
	private static final Image gems = new ImageIcon("src/main/resources/images/gemY.png").getImage();
	public static Image princess = new ImageIcon("src/main/resources/images/princess.png").getImage();

	public static final ArrayList<MapObjects> allMapObjects = new ArrayList<>();
	public static final ArrayList<EnemyObjects> allEnemies = new ArrayList<>();
	public static final ArrayList<ConsumableObject> allConsumables = new ArrayList<>();
	public static final ArrayList<ProjectileObjects> allProjectiles = new ArrayList<>();
	public static final ArrayList<GameObjects> AllGameObjects = new ArrayList<>();
	public static final ArrayList<UntouchableObjects> allUntouchableObjects = new ArrayList<>();




	public void setup() {
		raster = createImage(getWidth(), getHeight());
		g = raster.getGraphics();

		new LevelCreator("level1");

		player = new Player();
		cam = new Camera(0, 0);
		addKeyListener(player);

		sound = new Sounds();
	}

	public void run() {
		while (GameRunning) {
			EnemyObjects enemy;
			ProjectileObjects projectile;
			GameObjects gameObject;
			ConsumableObject consumable;
			MapObjects mapObject;
			UntouchableObjects uObject;

			// move any game objects
			for (int i = 0; i < allEnemies.size(); i++) {
				enemy = allEnemies.get(i);
				enemy.move();
				if (!enemy.isAlive()) {
					allEnemies.remove(enemy);
					i--;
				}
			}

			for (int i = 0; i < allProjectiles.size(); i++) {
				projectile = allProjectiles.get(i);
				projectile.move();
				if (!projectile.isAlive()) {
					allProjectiles.remove(projectile);
					i--;
				}
			}

			for (int i = 0; i < AllGameObjects.size(); i++) {
				gameObject = AllGameObjects.get(i);
				gameObject.move();
				if (!gameObject.isAlive()) {
					AllGameObjects.remove(gameObject);
					i--;
				}
			}

			for (int i = 0; i < allConsumables.size(); i++) {
				consumable = allConsumables.get(i);
				consumable.move();
				if (!consumable.isAlive()) {
					allConsumables.remove(consumable);
					i--;
				}
			}

			for (int i = 0; i < allMapObjects.size(); i++) {
				mapObject = allMapObjects.get(i);
				mapObject.move();
				if (!mapObject.isAlive()) {
					allMapObjects.remove(mapObject);
					i--;
				}
			}
			for (int i = 0; i < allUntouchableObjects.size(); i++) {
				uObject = allUntouchableObjects.get(i);
				if (!uObject.isAlive()) {
					allUntouchableObjects.remove(uObject);
					i--;
				}
			}

			// draw background
			g.drawImage(Background, -100, -100, 4200, 1800, null);

			// draw any game objects
			for (GameObjects gameObject2 : AllGameObjects)
				gameObject2.draw(g);
			for(EnemyObjects enemy2 : allEnemies)
				enemy2.draw(g);
			for(ConsumableObject consumable2 : allConsumables)
				consumable2.draw(g);
			for(ProjectileObjects projectile2 : allProjectiles)
				projectile2.draw(g);
			for(MapObjects mapObject2 : allMapObjects)
				mapObject2.draw(g);
			for(UntouchableObjects uObject2 : allUntouchableObjects)
				uObject2.draw(g);


			//Draw UI
			g.setColor(Color.WHITE);
			g.setFont(new Font("Comic Sans MS", Font.PLAIN, 16));
			g.drawImage(gbow, (int)cam.getX() + 20, (int)cam.getY() + 40, 70, 70, null);
			g.drawString("" + player.getArrows(), (int)cam.getX() + 60, (int)cam.getY() + 50);
			g.drawImage(gems, (int)cam.getX() + 120, (int)cam.getY() + 40, 69, 55, null);
			g.drawString("" + player.getNumOfGems(), (int) cam.getX() + 185, (int)cam.getY() + 55);
			if (player.isGuard()) {
				g.drawImage(shield, (int)cam.getX() + 220, (int)cam.getY() + 40, 70, 70, null);
			}
			if(getNumOfHearts() == 0){
				g.drawString("CONGRATULATIONS! You're alright buddy. Stay in school!", (int) (player.getX() - 220), (int)player.getY() - 70);  //end of the game
				g.drawString("Thanks for saving me Linkachu!! My handsome hero <3", (int) 3500, 300);
				g.drawImage(princess, 3700, 408, null);
			}
			if(Flag.getLevel() == 1){
				g.drawString("Space bar to jump",100, 150);
				g.drawString("Long pressing the space bar will make you fall faster and jump higher",100, 200);
				g.drawString("'X' to shoot",100, 250);
				g.drawString("get the 5 gems and reach the flag to go to the next level", 100, 300);

			}

			// Draw final Image
			Graphics frameG = getGraphics();
			frameG.drawImage(raster, 0, 0, 960, 720, null);

			try {
				Thread.sleep(2);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}

	// Getters and setters

	public static int getNumOfHearts() {
		return numOfHearts;
	}

	public static void setNumOfHearts(int numOfHearts) {
		GameFrame.numOfHearts = numOfHearts;
	}

	public static void setPrincess(Image princess) {
		GameFrame.princess = princess;
	}

	public static Player getPlayer() {
		return player;
	}


	public static AffineTransform getIdentity() {
		return identity;
	}

	public static int getLevelHeight() {
		return levelHeight;
	}

	public static void setLevelHeight(int levelHeight) {
		GameFrame.levelHeight = levelHeight;
	}

	public static int getLevelWidth() {
		return levelWidth;
	}

	public static void setLevelWidth(int levelWidth) {
		GameFrame.levelWidth = levelWidth;
	}
}