package map_object;

import javax.swing.*;
import java.awt.*;

public class BlockU extends UntouchableObjects {
    static final Image block = new ImageIcon("src/main/resources/images/block.png").getImage();

    public BlockU(float x, float y) {
        this.x = x;
        this.y = y;
    }

    public void draw(Graphics g) {
        g.drawImage(block, (int) x, (int) y, null);
    }

}
