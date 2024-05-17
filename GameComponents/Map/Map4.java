package GameComponents.Map;

import java.awt.Graphics2D;

import GameComponents.GamePanel;
import GameComponents.Objects.ObjectManager;
import Utilities.Image;
import Utilities.Tool;

public class Map4 {

    private GamePanel gamePanel;
    private ObjectManager objectManager;

    private int[][] map = {
            {0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0},
            {0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0},
            {0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0},
            {0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0},
            {0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0},
            {0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0},
            {0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0},
            {0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0},
            {0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0},
            {0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0},
            {0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0},
            {0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0},
            {0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0},
            {0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0},
            {0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0},
    };

    private Image[] mapImages = new Image[10];

    public ObjectManager getObjectManager() {
        return objectManager;
    }

    public Map4(GamePanel gamePanel) {
        this.gamePanel = gamePanel;
        objectManager = new ObjectManager();

        mapImages[0] = new Image(Tool.getBufferedImage("../Assets/Images/Maps/land.png", 64, 64, 1));

        initObject();
    }

    public void initObject() {
//

        objectManager.createObject("Tree", 64*3-10, 64*2);
        objectManager.createObject("Tree", 64*5-22, 64*11);
        objectManager.createObject("Tree", 64*10-45, 64*3);
        objectManager.createObject("Tree", 64*12+7, 64*5);
        objectManager.createObject("Tree", 64*15-35, 64*11);
        objectManager.createObject("Tree", 64*18-65, 64*2);
        objectManager.createObject("Tree", 64*3-22, 64*3);
        objectManager.createObject("Tree", 64*2-45, 64*10);
        objectManager.createObject("Tree", 64*6+7, 64*11);
        objectManager.createObject("Tree", 64*19-65, 64*4);
        objectManager.createObject("Tree", 64*18-45, 64*10);
        objectManager.createObject("Tree", 64*10+7, 64*11);
        objectManager.createObject("Tree", 64*11+7, 64*1);
        objectManager.createObject("Tree", 64*8+7, 64*2);
        objectManager.createObject("Tree", 64*9+7, 64*3);
        objectManager.createObject("Tree", 64*4+7, 64*5);
        objectManager.createObject("Tree", 64*9+7, 64*10);
        objectManager.createObject("Tree", 64*20+7, 64*3);
        objectManager.createObject("Tree", 64*21+7, 64*2);


    }

    public void update() {
        objectManager.update();
    }

    public void render(Graphics2D graphics2d) {
        for(int i = 0; i < gamePanel.SCREEN_ROW; ++i) {
            for(int j = 0; j < gamePanel.SCREEN_COLUMN; ++j) {
                graphics2d.drawImage(mapImages[map[i][j]].getImage(), j * gamePanel.PIXEL, i * gamePanel.PIXEL, mapImages[map[i][j]].getWidth(), mapImages[map[i][j]].getHeight(), null);
            }
        }
        objectManager.render(graphics2d);
    }

}
