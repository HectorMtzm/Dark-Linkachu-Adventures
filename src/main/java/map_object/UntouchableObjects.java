package map_object;

import utility.GameFrame;

import java.awt.*;

public abstract class UntouchableObjects {
//    int width, height;
    boolean isAlive = true;
    float x, y;

    public UntouchableObjects() {
        GameFrame.allUntouchableObjects.add(this);
    }

    public abstract void draw(Graphics g);

    public void die() {
        isAlive = false;
    }

    public boolean isAlive() {
        return isAlive;
    }
}
