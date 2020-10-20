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

    public void Die() {
        isAlive = false;
    }

    public Rectangle getBounds() {
        // System.out.println(getX() + " " + getY());
        return new Rectangle((int) x + 5, (int) y, width - 10, height);
    }

    public Rectangle getBoundsTOP() {
        return new Rectangle((int) x + (width / 2 - 5) - ((width / 2) / 2), (int) y, width / 2 + 10, height / 2);
    }

    public Rectangle getBoundsBottom() {
        return new Rectangle((int) x + (width / 2 - 5) - ((width / 2) / 2), (int) y + (height / 2) + 1, width / 2 + 10, height / 2);
    }

    public Rectangle getBoundsLeft() {
        return new Rectangle((int) x + 5, (int) y + 5, 5, height - 10);                                                            //four side boundaries
    }

    public Rectangle getBoundsRight() {
        return new Rectangle((int) x + width - 10, (int) y + 5, 5, height - 10);
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

    public int getWidth() {
        return width;
    }

    public int getHeight() {
        return height;
    }

    public boolean isAlive() {
        return isAlive;
    }
}

