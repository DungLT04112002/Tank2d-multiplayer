package GameComponents;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Toolkit;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;

import javax.swing.JPanel;

import GameComponents.Base.BaseManager;
import GameComponents.Buff.BuffManager;
import GameComponents.Bullets.BulletManager;
import GameComponents.Cannon.CannonManager;
import GameComponents.Flag.FlagManager;
import GameComponents.Gun.GunManager;
import GameComponents.Map.Map;
import GameComponents.Map.Map2;
import GameComponents.Map.Map3;
import GameComponents.Map.Map4;
import GameComponents.Players.PlayerManager;
import GameComponents.Title.TitleManager;
import Inputs.KeyHandler;
import Utilities.Tool;

public class GamePanel extends JPanel implements Runnable, MouseListener, MouseMotionListener{

    public final int SCREEN_WIDTH = 1408;
    public final int SCREEN_HEIGHT = 64 * 12;
    public final int PIXEL = 64;
    public final int SCREEN_ROW = SCREEN_HEIGHT / PIXEL;
    public final int SCREEN_COLUMN = SCREEN_WIDTH / PIXEL;

    private int mouseX;
    private int mouseY;
    private boolean mousePressed;

    public final int GAME_FPS = 120;
    public final int GAME_UPS = 100;

    private int displayFPS = GAME_FPS, displayUPS = GAME_UPS;

    private Thread gameThread;
    private KeyHandler keyHandler = new KeyHandler(this);
    private Map map;
    private Map2 map2;
    private Map3 map3 = new Map3(this);
    private Map4 map4 = new Map4(this);
    private PlayerManager playerManager = new PlayerManager(this);
    private BulletManager bulletManager = new BulletManager(this);
    private CollisionChecker collisionChecker = new CollisionChecker(this);
    private BuffManager buffManager = new BuffManager(this);
    private TitleManager titleManager = new TitleManager(this);
    private BaseManager baseManager = new BaseManager(this);
    private FlagManager flagManager = new FlagManager(this);
    private GunManager gunManager = new GunManager(this);
    private CannonManager cannonManager = new CannonManager(this);
    public int chooseMap = Tool.Random(1, 4);

    public void reset() {
        baseManager.reset();
        playerManager.reset();
    }

    public void setWinner(int team) {
        menu.gameState = 2;
        menu.setWinner(team);
    }

    public CannonManager getCannonManager() {
        return cannonManager;
    }

    public BuffManager getBuffManager() {
        return buffManager;
    }

    Sound sound = new Sound();

    /////////////////////////////
    private Menu menu = new Menu(this);
    /////////////////////////////

    public FlagManager getFlagManager() {
        return flagManager;
    }

    public BaseManager getBaseManager() {
        return baseManager;
    }

    public TitleManager getTitleManager() {
        return titleManager;
    }

    public PlayerManager getPlayerManager() {
        return playerManager;
    }

    public BulletManager getBulletManager() {
        return bulletManager;
    }

    public CollisionChecker getCollisionChecker() {
        return collisionChecker;
    }

    public Map getMap() {
        return map;
    }

    public Map2 getMap2() {
        return map2;
    }
    public Map3 getMap3() {
        return map3;
    }
    public Map4 getMap4() {
        return map4;
    }
    public KeyHandler getKeyHandler() {
        return keyHandler;
    }

    public GamePanel() {
        this.setPreferredSize(new Dimension(SCREEN_WIDTH, SCREEN_HEIGHT));
        this.setBackground(Color.black);
        this.setDoubleBuffered(true);
        this.addKeyListener(keyHandler);
        this.setFocusable(true); // gamePanel can be focused to receive key input

        this.addMouseListener(this);
        this.addMouseMotionListener(this);

        menu.gameState = -1;// trang thai ban dau
    }

    public void createBase() {
        baseManager.getBase(1).active(50, 350);
        baseManager.getBase(2).active(1300, 350);
    }

    public void createTeam() {
        playerManager.createPlayer(1);

        playerManager.createPlayer(1);

        playerManager.createPlayer(1);

        playerManager.createPlayer(2);

        playerManager.createPlayer(2);

        playerManager.createPlayer(2);
    }

    public void start() {

        gameThread = new Thread(this);
        gameThread.start(); // Automatic call run method

        createBase();
        createTeam();

        buffManager.createRandom(5);
        if (chooseMap == 1) {
            map = new Map(this);
            titleManager.createTitle1();
            cannonManager.createCannon1();
        } else if (chooseMap == 2) {
            map2 = new Map2(this);
            titleManager.createTitle2();
            cannonManager.createCannon2();
        }else if (chooseMap == 3) {
            titleManager.createTitle3();
            cannonManager.createCannon3();
        }else if (chooseMap == 4) {
            titleManager.createTitle4();
            cannonManager.createCannon4();
        }
    }

    @Override
    public void run() {

        double timePerFrame = (double) (1000000000.0 / GAME_FPS);// đổi sang ns
        double timePerUpdate = (double) (1000000000.0 / GAME_UPS);

        long currentTime;

        long lastCheck = System.currentTimeMillis();// ms
        long previousTime = System.nanoTime();// ns

        double deltaF = 0;
        double deltaU = 0;

        int updates = 0;
        int frames = 0;

        while (gameThread != null) {
            currentTime = System.nanoTime();
            deltaF += (currentTime - previousTime) / timePerFrame;
            deltaU += (currentTime - previousTime) / timePerUpdate;
            previousTime = currentTime;
            if (deltaU >= 1) {
                // 1. UPDATE: Update game variant such as character position, heal,...
                update();
                deltaU = deltaU - 1;
                updates = updates + 1;
            }
            if (deltaF >= 1) {
                // 2. DRAW: render the screen with the updated game variant
                repaint();
                deltaF = deltaF - 1;
                frames = frames + 1;

            }

            if (System.currentTimeMillis() - lastCheck >= 1000) {
                lastCheck = System.currentTimeMillis();
                // System.out.println("FPS: " + frames + " | UPS: " + updates);
                displayFPS = frames;
                displayUPS = updates;
                frames = 0;
                updates = 0;
            }
        }
    }

    public void update() {
        //////////////////////////////////////////// cập nhật lại update()

        // System.out.println("gameState " + menu.gameState);

        if (menu.gameState == -1 || menu.gameState == 2) {
            menu.update();
        } else if (menu.gameState == 0) {
            if (chooseMap == 1) {
                map.update();
            } else if (chooseMap == 2) {
                map2.update();
            }else if (chooseMap == 3) {
                map3.update();
            }else if (chooseMap == 4) {
                map4.update();
            }
            baseManager.update();
            flagManager.update();
            playerManager.update();
            bulletManager.update();
            collisionChecker.update();
            buffManager.update();
            titleManager.update();
            gunManager.update();
            cannonManager.update();
            
        } else if (menu.gameState == 1) {
            System.exit(0);
        }

    }

    /////////////////////////////////// cập nhật lại paintComponent()
    public void paintComponent(Graphics graphics) {

        Toolkit.getDefaultToolkit().sync(); // os is Linux

        super.paintComponent(graphics);

        Graphics2D graphics2d = (Graphics2D) graphics;

        if (menu.gameState == -1 || menu.gameState == 2) {
            menu.render(graphics2d);
        }

        if (menu.gameState == 0) {
            if (chooseMap == 1) {
                map.render(graphics2d);
            } else if (chooseMap == 2) {
                map2.render(graphics2d);
            }else if (chooseMap == 3) {
                map3.render(graphics2d);
            }else if (chooseMap == 4) {
                map4.render(graphics2d);
            }
            baseManager.render(graphics2d);
            flagManager.render(graphics2d);
            playerManager.render(graphics2d);
            bulletManager.render(graphics2d);
            titleManager.render(graphics2d);
            buffManager.render(graphics2d);
            gunManager.render(graphics2d);
            cannonManager.render(graphics2d);
        }

    }

    public int getFPS() {
        return displayFPS;
    }

    public int getUPS() {
        return displayUPS;
    }

    // các trường hợp tạo âm thanh
    /////////////////////////////
    public void playMusic(int i) {
        sound.setFile(i);
        sound.play();
        sound.loop();
    }

    public void stopMusic() {
        sound.stop();
    }

    public void playSE(int i) {
        sound.setFile(i);
        sound.play();
    }

    public int getMouseX() {
        return mouseX;
    }

    public int getMouseY() {
        return mouseY;
    }

    public boolean isMousePressed() {
        return mousePressed;
    }

    @Override
    public void mouseClicked(MouseEvent e) {
    }

    @Override
    public void mousePressed(MouseEvent e) {
        //System.out.println("true");
        mousePressed = true;
    }

    @Override
    public void mouseReleased(MouseEvent e) {
        //System.out.println("false");
        mousePressed = false;
    }

    @Override
    public void mouseEntered(MouseEvent e) {

    }

    @Override
    public void mouseExited(MouseEvent e) {

    }

    @Override
    public void mouseMoved(MouseEvent e) {
        mouseX = e.getX();
        mouseY = e.getY();
    }

    @Override
    public void mouseDragged(MouseEvent e) {
        mouseX = e.getX();
        mouseY = e.getY();
    }


    /////////////////////////////
}
