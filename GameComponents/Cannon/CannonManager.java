package GameComponents.Cannon;

import java.awt.Graphics2D;

import GameComponents.GamePanel;

public class CannonManager {

    public final int MAX_PLAYER = 5;
    private GamePanel gamePanel;

    private Cannon[] Cannons = new Cannon[MAX_PLAYER];

    private int numCannon = 2;

    public void createCannon1() {
        Cannons[0].create(64 * 9, 64 * 9, true);
        Cannons[1].create(64 * 12, 64 * 2, false);

    }

    public void createCannon2() {
        Cannons[0].create(64 * 3, 64 * 8, true);
        Cannons[1].create(64 * 18, 64 * 3, false);
        Cannons[0].isMove = true;
        Cannons[1].isMove = true;
    }

    public void createCannon3() {
        Cannons[0].create(64 * 6, (1408 / 2 - 60) / 2, true);
        Cannons[1].create(64 * 15, (1408 / 2 - 60) / 2, false);
        Cannons[0].isMove = true;
        Cannons[1].isMove = true;
    }

    public void createCannon4() {
        Cannons[0].create(64 * 6, (1408 / 2 - 60) / 2, true);
        Cannons[1].create(64 * 15, (1408 / 2 - 60) / 2, false);
        Cannons[0].isMove = true;
        Cannons[1].isMove = true;
    }

    public void init() {
        for (int i = 0; i < MAX_PLAYER; ++i) {
            Cannons[i] = new Cannon(gamePanel, i);
        }
    }

    public CannonManager(GamePanel gamePanel) {
        this.gamePanel = gamePanel;
        init();
    }

    public void update() {
        for (int i = 0; i < numCannon; ++i) {
            Cannons[i].update();
        }
    }

    public void render(Graphics2D graphics2d) {
        for (int i = 0; i < numCannon; ++i) {
            Cannons[i].render(graphics2d);
        }
    }
}
