package GameComponents;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics2D;
import java.awt.Rectangle;

import Utilities.Image;
import Utilities.Tool;

public class Menu {

    private GamePanel gamePanel;
    private int mouseX, mouseY;
    private boolean mousePressed = false;
    private int winner;
    boolean UseSound = false, pre_UseSound;

    public void setWinner(int team) {
        this.winner = team;
    }

    private Image[] images = {
            new Image(Tool.getBufferedImage("../Assets/Images/Menu/play_no.png", 400, 150, 1)),
            new Image(Tool.getBufferedImage("../Assets/Images/Menu/exit_no.png", 400, 150, 1)),
            new Image(Tool.getBufferedImage("../Assets/Images/Menu/play_yes.png", 400, 150, 1)),
            new Image(Tool.getBufferedImage("../Assets/Images/Menu/exit_yes.png", 400, 150, 1)),
            new Image(Tool.getBufferedImage("../Assets/Images/Menu/BG1.jpg", 400, 150, 1))
    };

    Rectangle playBounds = new Rectangle(1408 / 2 - 200, 400, 400, 150);
    Rectangle exitBounds = new Rectangle(1408 / 2 - 200, 600, 400, 150);

    public int gameState = -1;
    private int choice = 0;
    private static String options[] = {
            "1. Game Play.", // option[0]
            "2. Exit Game." // option[1]
    };

    public Menu(GamePanel gamePanel) {
        this.gamePanel = gamePanel;
        gamePanel.playMusic(3);


    }

    public void update() {

        mouseX = gamePanel.getMouseX();
        mouseY = gamePanel.getMouseY();
        // System.out.println(mouseX + " " + mouseY);
        pre_UseSound = UseSound;

        if (pre_UseSound != UseSound) gamePanel.playSE(2);

        if (playBounds.contains(mouseX, mouseY) && gameState == -1) {
            choice = 0;
            UseSound = true;
        }

        else if (exitBounds.contains(mouseX, mouseY) && gameState == -1) {
            choice = 1;
            UseSound = true;
        } else{
            choice = -1;
            UseSound = false;
        } 

        if (pre_UseSound != UseSound) gamePanel.playSE(2);


        if (gamePanel.isMousePressed() && gameState == -1) {
            gameState = choice;
            if (gameState == 0) {
                gamePanel.reset();
            }
            UseSound = true;
        }
    }

    public void render(Graphics2D graphics2d) {

        graphics2d.drawImage(images[4].getImage(), 0, 0, gamePanel.SCREEN_WIDTH, gamePanel.SCREEN_HEIGHT, null);

        Font font1 = new Font("Lato", Font.BOLD, 100);
        graphics2d.setFont(font1);
        graphics2d.setColor(Color.RED);

        if ((gameState == -1)) {
            graphics2d.drawString("GAME TANK", gamePanel.SCREEN_WIDTH / 2 - 300, 200);
        } else {
            if (winner == 1) {
                graphics2d.drawString("TEAM 1 WIN", gamePanel.SCREEN_WIDTH / 2 - 300, 200);
            } else {
                graphics2d.drawString("TEAM 2 WIN", gamePanel.SCREEN_WIDTH / 2 - 300, 200);
            }
        }

        for (int i = 0; i < options.length; i++) {
            if (i == choice) {
                graphics2d.drawImage(images[i + 2].getImage(), gamePanel.SCREEN_WIDTH / 2 - 200, 400 + i * 150,
                        images[i + 2].getWidth(), images[i + 2].getHeight(), null);
            } else {
                graphics2d.drawImage(images[i].getImage(), gamePanel.SCREEN_WIDTH / 2 - 200, 400 + i * 150,
                        images[i].getWidth(), images[i].getHeight(), null);
            }

        }
    }

}
