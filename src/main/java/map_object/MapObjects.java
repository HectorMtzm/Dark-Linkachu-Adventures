package map_object;

import utility.GameFrame;

import java.awt.Graphics;
import java.awt.Rectangle;

public abstract class MapObjects {
    int width, height;
    boolean isAlive = true;
    float x, y;
    public int hit;

    public MapObjects() {
        GameFrame.allMapObjects.add(this);
    }

    public abstract void draw(Graphics g);

    public abstract void move();

    public void die() {
        isAlive = false;
    }

    public Rectangle getBounds() {
        return new Rectangle((int) x + 5, (int) y, width - 10, height);
    }


    public int getHeight() {
        return height;
    }

    public boolean isAlive() {
        return isAlive;
    }

    public float getY() {
        return y;
    }

    public float getX() {
        return x;
    }
}

