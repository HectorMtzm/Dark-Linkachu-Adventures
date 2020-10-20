package utility;

import player.*;
import consumable.ConsumableObject;
import enemy.Boss;
import enemy.EnemyObjects;
import map_object.*;
import projectile.ProjectileObjects;

import java.awt.Graphics;
import java.awt.Image;
import java.awt.geom.AffineTransform;
import java.util.ArrayList;

import javax.swing.ImageIcon;
import javax.swing.JFrame;

@SuppressWarnings("serial")
public class GameFrame extends JFrame {

	Image raster;
	Graphics rasterGraphics;
	Image Background;
	static boolean GameRunning = true;

	static int numOfHearts = 5;
	static AffineTransform identity = new AffineTransform();
	public static Camera cam;
	static Player player;
	static Boss boss;
	
	public static Image gbow = new ImageIcon("gbow.png").getImage();
	public static Image shield = new ImageIcon("shield.png").getImage();		//top left images
	public static Image gems = new ImageIcon("gemY.png").getImage();
	
	public static Image princess = new ImageIcon("princess.png").getImage();

	public static ArrayList<MapObjects> allMapObjects = new ArrayList<>();
	public static ArrayList<EnemyObjects> allEnemies = new ArrayList<>();
	public static ArrayList<ConsumableObject> allConsumables = new ArrayList<>();
	public static ArrayList<ProjectileObjects> allProjectiles = new ArrayList<>();
	public static ArrayList<GameObjects> AllGameObjects = new ArrayList<>();

	private void DrawBackground(Graphics g) {
		Background = new ImageIcon("background.png").getImage();
	}

	public void level1() {
		new LevelCreator("level1");
	}

	public void setup() {
		raster = createImage(getWidth(), getHeight());
		rasterGraphics = raster.getGraphics();

		Background = createImage(4164, getHeight());
		DrawBackground(Background.getGraphics());

		player = new Player();
		addKeyListener(player);
		cam = new Camera(0, 0);

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
			for(MapObjects mapobject2 : allMapObjects){
				mapobject2.draw(rasterGraphics);
			}

			// Draw final Image
			Graphics frameG = getGraphics();
			frameG.drawImage(raster, 0, 0, 960, 720, null);

			try {
				Thread.sleep(2);
			} catch (Exception e) {
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
}