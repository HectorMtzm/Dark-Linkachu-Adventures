package utility;

import consumable.ConsumableObject;
import enemy.Boss;
import enemy.EnemyObjects;
import map_object.MapObjects;
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

	Image raster;
	Graphics rasterGraphics;
	Image Background;
	public static Sounds sound;
	static boolean GameRunning = true;

	static int numOfHearts = 5;
	static AffineTransform identity = new AffineTransform();
	public static Camera cam;
	static Player player;
	static Boss boss;
	private static int levelHeight;
	private static int levelWidth;
	
	public static Image gbow = new ImageIcon("src/main/resources/images/gbow.png").getImage();
	public static Image shield = new ImageIcon("src/main/resources/images/shield.png").getImage();		//top left images
	public static Image gems = new ImageIcon("src/main/resources/images/gemY.png").getImage();
	
	public static Image princess = new ImageIcon("src/main/resources/images/princess.png").getImage();

	public static ArrayList<MapObjects> allMapObjects = new ArrayList<>();
	public static ArrayList<EnemyObjects> allEnemies = new ArrayList<>();
	public static ArrayList<ConsumableObject> allConsumables = new ArrayList<>();
	public static ArrayList<ProjectileObjects> allProjectiles = new ArrayList<>();
	public static ArrayList<GameObjects> AllGameObjects = new ArrayList<>();

	private void DrawBackground(Graphics g) {
		Background = new ImageIcon("src/main/resources/images/background.png").getImage();
	}

	public void setup() {
		raster = createImage(getWidth(), getHeight());
		rasterGraphics = raster.getGraphics();

		Background = createImage(4164, getHeight());
		DrawBackground(Background.getGraphics());

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

			// draw background
			rasterGraphics.drawImage(Background, -100, -100, 4200, 1800, null);

			// draw any game objects
			for (GameObjects gameObject2 : AllGameObjects)
				gameObject2.draw(rasterGraphics);
			for(EnemyObjects enemy2 : allEnemies)
				enemy2.draw(rasterGraphics);
			for(ConsumableObject consumable2 : allConsumables)
				consumable2.draw(rasterGraphics);
			for(ProjectileObjects projectile2 : allProjectiles)
				projectile2.draw(rasterGraphics);
			for(MapObjects mapObject2 : allMapObjects){
				mapObject2.draw(rasterGraphics);
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

	public static Image getGbow() {
		return gbow;
	}

	public static void setGbow(Image gbow) {
		GameFrame.gbow = gbow;
	}

	public static Image getShield() {
		return shield;
	}

	public static void setShield(Image shield) {
		GameFrame.shield = shield;
	}

	public static Image getGems() {
		return gems;
	}

	public static void setGems(Image gems) {
		GameFrame.gems = gems;
	}

	public static Image getPrincess() {
		return princess;
	}

	public static void setPrincess(Image princess) {
		GameFrame.princess = princess;
	}

	public static Camera getCam() {
		return cam;
	}

	public static Player getPlayer() {
		return player;
	}

	public static Boss getBoss() {
		return boss;
	}

	public static ArrayList<MapObjects> getAllMapObjects() {
		return allMapObjects;
	}

	public static ArrayList<EnemyObjects> getAllEnemies() {
		return allEnemies;
	}

	public static ArrayList<ConsumableObject> getAllConsumables() {
		return allConsumables;
	}

	public static ArrayList<ProjectileObjects> getAllProjectiles() {
		return allProjectiles;
	}

	public static void setAllMapObjects(ArrayList<MapObjects> allMapObjects) {
		GameFrame.allMapObjects = allMapObjects;
	}

	public static void setAllEnemies(ArrayList<EnemyObjects> allEnemies) {
		GameFrame.allEnemies = allEnemies;
	}

	public static void setAllConsumables(ArrayList<ConsumableObject> allConsumables) {
		GameFrame.allConsumables = allConsumables;
	}

	public static void setAllProjectiles(ArrayList<ProjectileObjects> allProjectiles) {
		GameFrame.allProjectiles = allProjectiles;
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