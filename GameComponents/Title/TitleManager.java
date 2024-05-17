package GameComponents.Title;

import java.awt.Graphics2D;

import GameComponents.GamePanel;

public class TitleManager {

    public final int MAX_TITLE = 10;
    private GamePanel gamePanel;

    private Title[] Titles = new Title[MAX_TITLE];

    public int numTitle = 2;

    public Title[] getTitles(){
        return Titles;
    }

    public void createTitle1() {
        Titles[0].create(64 * 5, 64*6, 64, 64 * 2);
        Titles[1].create(64 * 16, 64 * 4, 64, 64 * 2);
        
    }
    
    public void createTitle2() {
        Titles[0].create(64 * 11, 64*8, 64, 64 * 2);
        Titles[1].create(64 * 10, 64 * 2, 64, 64 * 2);

    }
    public void createTitle3() {
        Titles[0].create(64 * 12, 64*8, 64, 64 * 2);
        Titles[1].create(64 * 10, 64 * 2, 64, 64 * 2);

    }
    public void createTitle4() {
        Titles[0].create(64 * 12, 64*8, 64, 64 * 2);
        Titles[1].create(64 * 10, 64 * 2, 64, 64 * 2);

    }

    public void init() {
        for (int i = 0; i < MAX_TITLE; ++i) {
            Titles[i] = new Title(gamePanel, i);
        }
    }

    public TitleManager(GamePanel gamePanel) {
        this.gamePanel = gamePanel;
        init();
    }

    public void update() {
        for (int i = 0; i < numTitle; ++i) {
            Titles[i].update();
        }
    }

    public void render(Graphics2D graphics2d) {
        for (int i = 0; i < numTitle; ++i) {
            Titles[i].render(graphics2d);
        }
    }
}

