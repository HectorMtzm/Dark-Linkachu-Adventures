package utility;

import consumable.Gems;
import enemy.Boss;
import enemy.MonsterFire;
import enemy.MonsterH;
import enemy.MonsterV;
import map_object.Blocks;
import map_object.Flag;
import map_object.SpecialBlocks;
import map_object.Spikes;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class LevelCreator {

    public LevelCreator(String level) {
        try {
            File file = new File("src/main/resources/levels/" + level);
            Scanner scanner = new Scanner(file);

            int lineNum = 1;
            int x, y;
            int levelW = 0;

            while (scanner.hasNextLine()) {
                char[] data = scanner.nextLine().toCharArray();

                if (data.length > levelW)
                    levelW = data.length;

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
                            new SpecialBlocks(x, y, (int) (Math.random() * (4 - 1)) + 1);
                            break;
                        case 's':
                            new SpecialBlocks(x, y, (int) (Math.random() * (3 - 1)) + 1);
                            break;
                        case 'F':
                            new MonsterFire(x, y);
                            break;
                        case 'T':
                            new Spikes(x,y + 70);
                            break;
                        case 'V':
                            new MonsterV(x, y,-1);
                            break;
                        case 'v':
                            new MonsterV(x, y,1);
                            break;
                        case 'M':
                            new Flag(x, y-100);
                            break;
                        case 'G':
                            new Gems(x,y);
                            break;
                        case 'A':
                            new SpecialBlocks(x, y, 1);
                            break;
                        case 'X':
                            new Boss(x - 78,y-232);
                            break;
                    }
                }
                lineNum++;
            }
            scanner.close();

            GameFrame.setLevelHeight(lineNum * 90);
            GameFrame.setLevelWidth(levelW * 90);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }



}
