package object;

import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;

import Main.GamePanel;
import Main.UtilityTool;

public class SuperObject {
	
	public BufferedImage image,up1,up2,down1,down2,left1,left2,right1,right2;
	public String name;
	public boolean collision = false;
	public boolean alive;
	public int X, Y, speed, Life, attack, usecost,max_life;
	public Rectangle solidArea = new Rectangle(0,0,48,48);
	public int solidAReaDefaultX = 0;
	public int solidAReaDefaultY = 0;
	UtilityTool uTool = new UtilityTool();
	
	
	public void draw(Graphics2D g2, GamePanel gp) {
		
		g2.drawImage(image, X, Y, gp.tileSize, gp.tileSize, null);
	}
}
