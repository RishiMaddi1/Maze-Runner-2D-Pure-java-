package Main;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics2D;
import java.awt.FontFormatException;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.io.InputStream;

import javax.imageio.ImageIO;

import object.OBJ_key;
import entity.Player;
import entity.Player2;
import java.text.DecimalFormat;


// Abstract class for game objects
abstract class GameObject {
    BufferedImage image;
    int x, y;
    
    public GameObject(int x, int y) {
        this.x = x;
        this.y = y;
    }
    
    abstract void draw(Graphics2D g2);
}

// Interface for UI elements
interface UIElement {
    void draw(Graphics2D g2);
    void showMessage(String text);
    void import_image(Graphics2D g2, String warrior, int x, int y, int width, int height);
}

public class UI implements UIElement {
    GamePanel gp;
    Graphics2D g2;
    Font customFont;
    DecimalFormat dFormat;
    BufferedImage coinImage, heartimage, health_full, health_empty, heart_empty, mana_Full, mana_Empty;
    public boolean messageOn = false;
    public String message = "";
    int messageCounter = 0;
    int messageCounter2 = 0;
    public boolean gameFinished = false;
    double playTime = 0;
    double gameendtime = 0;
    public int commandNum = 0;
    public int titleScreenState = 0; // the first screen
    public static String character;
    public int charNum;
    public int charNum2;
    public String attack;

    public UI(GamePanel gp) {
        dFormat = new DecimalFormat("#0.0");
        this.gp = gp;
        // Load custom font, process marchipoku
        loadCustomFont("/font/ThaleahFat.ttf");
        OBJ_key key = new OBJ_key(gp);
        coinImage = key.image;
    }

    public void setCharNum(int charNum) {
        this.charNum = charNum;
    }

    public int getCharNum() {
        return charNum;
    }

    public void setCharNum2(int charNum2) {
        this.charNum2 = charNum2;
    }

    public int getCharNum2() {
        return charNum2;
    }

    public void showMessage(String text) {
        message = text;
        messageOn = true;
    }

    public void draw(Graphics2D g2) {
        this.g2 = g2;
        g2.setFont(customFont);
        if (gp.gameState == gp.titleState) {
            drawTitleScreen();
        }
        if (gp.gameState == gp.playState) {
            drawPlayScreen();
        }
        if (gp.gameState == gp.pauseState) {
            //pause state ui
            drawPauseScreen();
        }
    }

    public void import_image(Graphics2D g2, String warrior, int x, int y, int width, int height) {
        String imagePath = warrior;
        try (InputStream is = getClass().getResourceAsStream(imagePath)) {
            BufferedImage image = ImageIO.read(is);
            g2.drawImage(image, x, y, width, height, null);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void drawTitleScreen() {
        if (titleScreenState == 0) {
            g2.setColor(new Color(0, 0, 0));
            g2.fillRect(0, 0, gp.screenWidth, gp.screenHeight);
            Font originalFont = g2.getFont();
            Font newFont = originalFont.deriveFont(Font.PLAIN, 100);
            g2.setFont(newFont);
            g2.setColor(Color.white);
            String text = "Maze Mayhem";
            int k = getXforCenteredtext(text) + 10;
            g2.drawString(text, k, gp.tileSize * 3 * 2);
            //shadow
            g2.setColor(Color.gray);
            g2.drawString(text, k + 3, (gp.tileSize * 3 * 2) + 3);
            //main color
            g2.setColor(Color.white);
            g2.drawString(text, k, gp.tileSize * 3 * 2);
            g2.setFont(originalFont);
            int m = gp.tileSize * 3 * 2;
            //menu
            Font menuFont = g2.getFont().deriveFont(Font.PLAIN, 50);
            g2.setFont(menuFont);
            text = "Race";
            k = getXforCenteredtext(text) + 10;
            m += gp.tileSize * 3 * 2;
            g2.drawString(text, k, m);
            //cursor
            if (commandNum == 0) {
                g2.drawString(">", k - (gp.tileSize * 3) + 20, m);
            }
            text = "Exit Maze Mayhem";
            k = getXforCenteredtext(text) + 10;
            m += gp.tileSize * 2;
            g2.drawString(text, k, m);
            if (commandNum == 1) {
                g2.drawString(">", k - (gp.tileSize * 3) + 20, m);
            }
        } else if (titleScreenState == 1) {
            //class selection screen
            g2.setColor(new Color(0, 0, 0));
            g2.fillRect(0, 0, gp.screenWidth, gp.screenHeight);
            //p1
            g2.setColor(Color.white);
            g2.setFont(g2.getFont().deriveFont(20f));
            Font menuFont = g2.getFont().deriveFont(Font.PLAIN, 35);
            g2.setFont(menuFont);
            String text = "Select your Warrior Player 1!";
            g2.drawString(text, 50, 65);
            g2.setFont(g2.getFont().deriveFont(20f));
            import_image(g2, "/player/blue_girl_down1.png", 18 + 70, 330, gp.tileSize, gp.tileSize);
            if (charNum == 0) {
                g2.drawString("===", 26 + 70, 395);
            }
            import_image(g2, "/player/blue_wizard_down1.png", 101 + 70, 330, gp.tileSize, gp.tileSize);
            if (charNum == 1) {
                g2.drawString("===", 111 + 70, 395);
            }
            import_image(g2, "/player/caped_baldy_down1.png", 177 + 70, 330, gp.tileSize, gp.tileSize);
            if (charNum == 2) {
                g2.drawString("===", 186 + 70, 395);
            }
            import_image(g2, "/player/muscle_girl_down1.png", 253 + 70, 330, gp.tileSize, gp.tileSize);
            if (charNum == 3) {
                g2.drawString("===", 262 + 70, 395);
            }
            import_image(g2, "/player/ninja_guy_down1.png", 329 + 70, 330, gp.tileSize, gp.tileSize);
            if (charNum == 4) {
                g2.drawString("===", 338 + 70, 395);
            }
            import_image(g2, "/player/pirate_head_down1.png", 18 + 70, 412, gp.tileSize, gp.tileSize);
            if (charNum == 5) {
                g2.drawString("===", 26 + 70, 470);
            }
            import_image(g2, "/player/royal_boy_down1.png", 101 + 70, 412, gp.tileSize, gp.tileSize);
            if (charNum == 6) {
                g2.drawString("===", 111 + 70, 470);
            }
            import_image(g2, "/player/royal_grey_down1.png", 177 + 70, 412, gp.tileSize, gp.tileSize);
            if (charNum == 7) {
                g2.drawString("===", 186 + 70, 470);
            }
            import_image(g2, "/player/royal_red_down1.png", 253 + 70, 412, gp.tileSize, gp.tileSize);
            if (charNum == 8) {
                g2.drawString("===", 262 + 70, 470);
            }
            import_image(g2, "/player/sand_trader_down1.png", 329 + 70, 412, gp.tileSize, gp.tileSize);
            if (charNum == 9) {
                g2.drawString("===", 338 + 70, 470);
            }
            //p2
            g2.setColor(Color.white);
            g2.setFont(g2.getFont().deriveFont(20f));
            Font menuFontt = g2.getFont().deriveFont(Font.PLAIN, 35);
            g2.setFont(menuFontt);
            text = "Select your Warrior Player 2!";
            g2.drawString(text, 600, 65);
            g2.setFont(g2.getFont().deriveFont(20f));
            import_image(g2, "/player/blue_girl_down1.png", 400 + 240, 330, gp.tileSize, gp.tileSize);
            if (charNum2 == 0) {
                g2.drawString("===", 408 + 240, 395);
            }
            import_image(g2, "/player/blue_wizard_down1.png", 483 + 240, 330, gp.tileSize, gp.tileSize);
            if (charNum2 == 1) {
                g2.drawString("===", 492 + 240, 395);
            }
            import_image(g2, "/player/caped_baldy_down1.png", 559 + 240, 330, gp.tileSize, gp.tileSize);
            if (charNum2 == 2) {
                g2.drawString("===", 568 + 240, 395);
            }
            import_image(g2, "/player/muscle_girl_down1.png", 635 + 240, 330, gp.tileSize, gp.tileSize);
            if (charNum2 == 3) {
                g2.drawString("===", 644 + 240, 395);
            }
            import_image(g2, "/player/ninja_guy_down1.png", 711 + 240, 330, gp.tileSize, gp.tileSize);
            if (charNum2 == 4) {
                g2.drawString("===", 721 + 240, 395);
            }
            import_image(g2, "/player/pirate_head_down1.png", 400 + 240, 412, gp.tileSize, gp.tileSize);
            if (charNum2 == 5) {
                g2.drawString("===", 408 + 240, 470);
            }
            import_image(g2, "/player/royal_boy_down1.png", 483 + 240, 412, gp.tileSize, gp.tileSize);
            if (charNum2 == 6) {
                g2.drawString("===", 492 + 240, 470);
            }
            import_image(g2, "/player/royal_grey_down1.png", 559 + 240, 412, gp.tileSize, gp.tileSize);
            if (charNum2 == 7) {
                g2.drawString("===", 568 + 240, 470);
            }
            import_image(g2, "/player/royal_red_down1.png", 635 + 240, 412, gp.tileSize, gp.tileSize);
            if (charNum2 == 8) {
                g2.drawString("===", 644 + 240, 470);
            }
            import_image(g2, "/player/sand_trader_down1.png", 711 + 240, 412, gp.tileSize, gp.tileSize);
            if (charNum2 == 9) {
                g2.drawString("===", 721 + 240, 470);
            }
        } else if (titleScreenState == 2) {
            g2.setColor(new Color(0, 0, 0));
            g2.fillRect(0, 0, gp.screenWidth, gp.screenHeight);
            //text
            g2.setColor(Color.white);
            String text = "Pick Your Planet";
            int k = getXforCenteredtext(text) + 10;
            g2.drawString(text, k, gp.tileSize * 3);
            import_image(g2, "/maps/map3.png", 18, 280, gp.tileSize * 4, gp.tileSize * 3);
        }
    }

    public void drawPlayScreen() {
        //play state ui
        if (Player.score == 1 || Player2.score2 == 1) {
            gameFinished = true;
        } else {
            playTime += (double) 1 / 60;
        }
        if (gameFinished) {
            // here logic for who wins
            Font menuFontt = g2.getFont().deriveFont(Font.PLAIN, 80);
            g2.setFont(menuFontt);
            if (Player.score == 1) {
                g2.setColor(Color.black);
                int k = getXforCenteredtext("Player1 wins with a time of:" + dFormat.format(playTime) + " !!") + 25;
                g2.drawString("Player1 wins with a time of:" + dFormat.format(playTime) + " !!", k, 230);
            } else if (Player2.score2 == 1) {
                g2.setColor(Color.black);
                int k = getXforCenteredtext("Player2 wins with a time of:" + dFormat.format(playTime) + " !!") + 25;
                g2.drawString("Player2 wins with a time of:" + dFormat.format(playTime) + " !!", k, 230);
            }
            g2.setColor(Color.black);
            Font menuFonttt = g2.getFont().deriveFont(Font.PLAIN, 40);
            g2.setFont(menuFonttt);
            int k = getXforCenteredtext("You will be sent to the Menu in 5 seconds!") + 25;
            g2.drawString("You will be sent to the Menu in 5 seconds!", k, 310);
            gameendtime += (double) 1 / 60;
            if (gameendtime > 5) {
            	Sound.RunMusic("res/sound/rockyou.wav");
                gp.ui.titleScreenState = 0;
                gp.gameState = gp.titleState;
            }
        } else {
        	g2.setColor(Color.black);
            Font menuFontt = g2.getFont().deriveFont(Font.PLAIN, 60);
            g2.setFont(menuFontt);
            g2.drawString("" + dFormat.format(playTime), 520, 40);
        }
    }

    public void drawPauseScreen() {
        String Text = "Paused";
        int x = getXforCenteredtext(Text);
        int y = gp.screenHeight / 2 + 10;
        g2.drawString(Text, x, y);
    }

    public int getXforCenteredtext(String Text) {
        int length = (int) g2.getFontMetrics().getStringBounds(Text, g2).getWidth() + 25;
        int x = gp.screenWidth / 2 - length / 2;
        return x;
    }

    private void loadCustomFont(String fontPath) {
        try {
            InputStream is = getClass().getResourceAsStream(fontPath);
            customFont = Font.createFont(Font.TRUETYPE_FONT, is);
            customFont = customFont.deriveFont(35f);
        } catch (IOException | FontFormatException e) {
            e.printStackTrace();
        }
    }
}
