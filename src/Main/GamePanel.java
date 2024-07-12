package Main;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.GraphicsDevice;
import java.awt.GraphicsEnvironment;
import java.awt.image.BufferedImage;

import javax.swing.JPanel;

import entity.Player;
import entity.Player2;
import object.SuperObject;
import tile.TileManager;


public class GamePanel extends JPanel implements Runnable {

    // Screen settings

    final int originalTileSize = 16; 
    final int scale = 3;

    public final int tileSize = originalTileSize * scale; 
    public final int maxScreenCol = 24; 
    public final int maxScreenRow = 18; 
    public final int screenWidth = tileSize * maxScreenCol; 
    public final int screenHeight = tileSize * maxScreenRow; 
    int i = 0;
    // for full screen

    int screenWidth2 = screenWidth;
    int screenHeight2 = screenHeight;
    BufferedImage tempScreen;
    Graphics2D g2;

    // FPS

    int FPS = 60;

    TileManager tileM = new TileManager(this);
    KeyHandler keyH = new KeyHandler(this);

    public CollisionChecker cChecker = new CollisionChecker(this);

    public AssetSetter aSetter = new AssetSetter(this);
    Thread gameThread; // it keeps your program running until you stop it, like doing smtg repeatedly

    public UI ui = new UI(this);

    public EventHandler eHandler = new EventHandler(this);    
    public Player player;
    public Player2 player2;

    public SuperObject obj[] = new SuperObject[10];

    // game state
    public final int titleState = 0;
    public int gameState;
    public final int playState = 1;
    public final int pauseState = 2;

    public GamePanel() {
        this.setPreferredSize(new Dimension(screenWidth, screenHeight));
        this.setBackground(Color.black);
        this.setDoubleBuffered(true);
        this.addKeyListener(keyH); // Using keyH directly
        this.setFocusable(true);
        
    }

    public void setupGame() {
        
    		aSetter.setObject();
    	
        // playMusic(0);
        // start the music
        gameState = titleState;
        if (gameState == titleState) {
            Sound.RunMusic("res/sound/rockyou.wav");
        }

        tempScreen = new BufferedImage(screenWidth, screenHeight, BufferedImage.TYPE_INT_ARGB);
        g2 = (Graphics2D) tempScreen.getGraphics();

        setFullScreen();
        
    }

    public void setFullScreen() {

        // get local screen device

        GraphicsEnvironment ge = GraphicsEnvironment.getLocalGraphicsEnvironment();
        GraphicsDevice gd = ge.getDefaultScreenDevice();
        gd.setFullScreenWindow(Main.window);
        // get full screen width and height

        screenWidth2 = Main.window.getWidth();
        screenHeight2 = Main.window.getHeight();

    }

    public void startGameThread() {

        gameThread = new Thread(this); // passing game panel class to this constructor
        gameThread.start();
        // Sound.RunMusic("res/sound/musicW.wav");

    }

    @Override
    public void run() {
        double drawInterval = 1000000000 / FPS;
        double delta = 0;
        long lastTime = System.nanoTime();
        long currentTime;
        long timer = 0;
        while (gameThread != null) {

            currentTime = System.nanoTime();

            delta += (currentTime - lastTime) / drawInterval;
            timer += (currentTime - lastTime);
            lastTime = currentTime;

            if (delta > 1) {
            	if(gameState == playState) {
            		while(i != 1){
            			player = new Player(this, keyH, ui.getCharNum());
                        player2 = new Player2(this, keyH, ui.getCharNum2());
                        i = 1;
            		}
            		
            		update(); // 1 update information such as character positions
            	}
            	
                // repaint();//2 draw the screen with the updated information
                drawToTempScreen();
                drawToScreen();
                delta--;
            }

            if (timer >= 1000000000) {
                timer = 0;
            }

        }

    }
    public void update() {
      
    		
            player.update();
            player2.update();
            
        
        
    }


    public void drawToTempScreen() {
        // tile
        long drawStart = 0;
        if (keyH.checkDrawTime == true) {
            drawStart = System.nanoTime();
        }

        // title screen
        if (gameState == titleState) {
            ui.draw(g2);
        } else {
            // Draw tiles
            tileM.draw(g2);
            
            // Draw objects
            for (int i = 0; i < obj.length; i++) {
                if (obj[i] != null) {
                    obj[i].draw(g2, this);
                }
            }
            
            // Draw players
            player.draw(g2);
            player2.draw(g2);            // player
            
            

            // ui

            ui.draw(g2);
        }

        if (keyH.checkDrawTime == true) {
            long drawEnd = System.nanoTime();
            long passed = drawEnd - drawStart;
            g2.setColor(Color.white);
            g2.drawString("Draw Time: " + passed, 10, 400);
            System.out.println("Draw Time: " + passed);
        }

    }

    public void drawToScreen() {
        Graphics g = getGraphics();
        g.drawImage(tempScreen, 0, 0, screenWidth2, screenHeight2, null);

        g.dispose();

    }


}
