package GameComponents.Map;

import java.awt.Graphics2D;

import GameComponents.GamePanel;
import GameComponents.Objects.ObjectManager;
import Utilities.Image;
import Utilities.Tool;

public class Map {

    private GamePanel gamePanel;
    private ObjectManager objectManager;

    private int[][] map = {
            { 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0 },
            { 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0 },
            { 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0 },
            { 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0 },
            { 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0 },
            { 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0 },
            { 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0 },
            { 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0 },
            { 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0 },
            { 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0 },
            { 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0 },
            { 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0 },
    };

    private Image[] mapImages = new Image[10];

    public ObjectManager getObjectManager() {
        return objectManager;
    }

    public Map(GamePanel gamePanel) {
        this.gamePanel = gamePanel;
        objectManager = new ObjectManager();

        mapImages[0] = new Image(Tool.getBufferedImage("../Assets/Images/Maps/land.png", 64, 64, 1));

        initObject();
    }

    public void initObject() {

        objectManager.createObject("Tree", 100, 600);
        objectManager.createObject("Tree", 200, 650);

        objectManager.createObject("Tree", 500, 250);
        objectManager.createObject("Tree", 850, 500);
        objectManager.createObject("Tree", 1100, 150);
        objectManager.createObject("Tree", 680, 350);
        objectManager.createObject("Tree", 400, 350);

        objectManager.createObject("Tree", 680, 600);
        objectManager.createObject("Tree", 450, 550);

        objectManager.createObject("Tree", 750, 450);
        objectManager.createObject("Tree", 600, 150);
        objectManager.createObject("Tree", 900, 250);
        objectManager.createObject("Tree", 200, 150);
        objectManager.createObject("Tree", 1150, 650);
        objectManager.createObject("Tree", 150, 50);
        objectManager.createObject("Tree", 1250, 600);

   

        for (int i = 0; i < 2; ++i) {
            objectManager.createObject("VWall", 785, i * 64);
        }
        for (int i = 10; i < 12; ++i) {
            objectManager.createObject("VWall", 595, i * 64+10);
        }
        for (int i = 4; i < 7; ++i) {
            objectManager.createObject("HWall", 64 * i, 2 * 64);
        }
        for (int i = 15; i < 18; ++i) {
            objectManager.createObject("HWall", 64 * i, 9 * 64);
        }

    }

    public void update() {
        objectManager.update();
    }

    public void render(Graphics2D graphics2d) {
        for (int i = 0; i < gamePanel.SCREEN_ROW; ++i) {
            for (int j = 0; j < gamePanel.SCREEN_COLUMN; ++j) {
                graphics2d.drawImage(mapImages[map[i][j]].getImage(), j * gamePanel.PIXEL, i * gamePanel.PIXEL,
                        mapImages[map[i][j]].getWidth(), mapImages[map[i][j]].getHeight(), null);
            }
        }

        objectManager.render(graphics2d);
    }

}
