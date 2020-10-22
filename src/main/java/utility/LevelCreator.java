package utility;

import enemy.MonsterFire;
import enemy.MonsterH;
import enemy.MonsterV;
import map_object.Blocks;
import map_object.SpecialBlocks;
import map_object.Spikes;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.net.URL;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Scanner;

public class LevelCreator {

    public LevelCreator(String level) {
        try {

            File file = new File("src/main/resources/levels/" + level);

            Scanner scanner = new Scanner(file);
            int lineNum = 1;
            int x,y;
            while (lineNum < 8) {
                char[] data = scanner.nextLine().toCharArray();
                if (data.length == 0){
                    lineNum++;
                    continue;
                }
                for(int i = 0; i < data.length; i++){
                    x = i * 90;
                    y = lineNum * 90;
                    switch (data[i]){
                        case 'B':
                            new Blocks(x, y);
                            break;
                        case 'H':
                            new MonsterH(x, y, -1);
                            break;
                        case 'h':
                            new MonsterH(x, y, 1);
                            break;
                        case 'S':
                            new SpecialBlocks(x,y, (int) 1);
//                            new SpecialBlocks(x,y, (int) (1 + Math.random() * 2));
                            break;
                        case 'F':
                            new MonsterFire(x,y);
                            break;
                        case 'T':
                            new Spikes(x,y + 64);   //90 - height = 64
                            break;
                        case 'V':
                            new MonsterV(x,y,-1);
                            break;
                        case 'v':
                            new MonsterV(x,y,1);
                            break;
                    }
                }
                lineNum++;
            }
            scanner.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }



}
