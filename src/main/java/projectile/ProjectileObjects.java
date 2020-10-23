package projectile;

import utility.GameFrame;

import java.awt.*;

public abstract class ProjectileObjects {

    boolean isAlive = true;
    int width, height;
    float velX = 0, velY = 0;
    float x, y;

    public ProjectileObjects() {
        GameFrame.allProjectiles.add(this);
    }

    public abstract void draw(Graphics g);

    public abstract void move();

    public void die() {
        isAlive = false;
    }

    public Rectangle getBounds() {
        return new Rectangle((int) x + 5, (int) y, width - 10, height);
    }

    public boolean isAlive() {
        return isAlive;
    }
}
