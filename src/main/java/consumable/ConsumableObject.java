package consumable;

import utility.GameFrame;

import java.awt.*;

public abstract class ConsumableObject {

    boolean isAlive = true;
    int width = 90, height = 90;
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
        return new Rectangle((int) x + 5, (int) y, width - 10, height);
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

    public void setVelY(float y) {
        velY = y;
    }

    public boolean isAlive() {
        return isAlive;
    }
}
