package GameComponents.Map;

import java.awt.Graphics2D;

import GameComponents.GamePanel;
import GameComponents.Objects.ObjectManager;
import Utilities.Image;
import Utilities.Tool;

public class Map2 {

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

    public Map2(GamePanel gamePanel) {
        this.gamePanel = gamePanel;
        objectManager = new ObjectManager();

        mapImages[0] = new Image(Tool.getBufferedImage("../Assets/Images/Maps/sand.png", 64, 64, 1));

        initObject();
    }

    public void initObject() {

        objectManager.createObject("Tree", 100, 150);
        objectManager.createObject("Tree", 500, 250);
        objectManager.createObject("Tree", 850, 500);
        objectManager.createObject("Tree", 1100, 550);
        objectManager.createObject("Tree", 450, 600);
        objectManager.createObject("Tree", 900, 150);
        objectManager.createObject("Tree", 900, 150);

        objectManager.createObject("Tree", 800, 300);

        objectManager.createObject("Tree", 700, 400);

        objectManager.createObject("Tree", 1000, 600);
        objectManager.createObject("Tree", 950, 200);

        objectManager.createObject("Tree", 350, 300);

        for (int i = 0; i < 1; ++i) {
            objectManager.createObject("VWall", 64 * 18, i * 64);
        }
        for (int i = 11; i < 12; ++i) {
            objectManager.createObject("VWall", 64 * 3, i * 64);
        }
        for (int i = 2; i < 4; ++i) {
            objectManager.createObject("VWall", 64 * 6, i * 64);
        }
        for (int i = 8; i < 10; ++i) {
            objectManager.createObject("VWall", 64 * 15, i * 64);
        }
        objectManager.createObject("HWall", 900, 300);
        objectManager.createObject("HWall", 600, 600);
        objectManager.createObject("HWall", 120, 550);
        objectManager.createObject("HWall", 1230, 190);

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
