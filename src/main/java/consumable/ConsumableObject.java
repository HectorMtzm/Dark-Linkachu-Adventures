package consumable;

import utility.GameFrame;

import java.awt.*;

public abstract class ConsumableObject {

    boolean isAlive = true;
    final int width = 50;
    final int height = 50;
    float velX = 0, velY = 0;
    float x, y;

    public ConsumableObject() {
        GameFrame.allConsumables.add(this);
    }

    public abstract void draw(Graphics g);

    public abstract void move();

    public void die() {
        isAlive = false;
    }

    public Rectangle getBounds() {
        return new Rectangle((int) x, (int) y, width, height);
    }

    public Rectangle getBoundsTOP() {
        return new Rectangle((int) x + (width / 2 - 5) - ((width / 2) / 2), (int) y, width / 2 + 10, height / 2);
    }

    public Rectangle getBoundsBottom() {
        return new Rectangle((int) x + (width / 2 - 5) - ((width / 2) / 2), (int) y + (height / 2) + 1, width / 2 + 10,height / 2);
    }

    public Rectangle getBoundsLeft() {
        return new Rectangle((int) x + 5, (int) y + 5, 5, height - 10);
    }

    public Rectangle getBoundsRight() {
        return new Rectangle((int) x + width - 10, (int) y + 5, 5, height - 10);
    }

    public boolean isAlive() {
        return isAlive;
    }
}
