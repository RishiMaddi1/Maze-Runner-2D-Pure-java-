package entity;

import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;
import java.io.IOException;

import javax.imageio.ImageIO;

import Main.GamePanel;
import Main.KeyHandler;
import Main.Sound;
import object.OBJ_key;

public class Player2 extends Entity {
	
	GamePanel gp;
	KeyHandler keyH;
	public static int score2 = 0;
	public int coinpicked2 = 0;
	public static boolean coinpickedstate2 = false;
	
	private String name;
	public int charnum2;
	public static int maxlife2;
	public static int life2;
	public static int heart2;
	public static int max_mana2;
	public static int mana2;
	
	public int getScore2() {
		return score2;
	}
	
	public Player2(GamePanel gp, KeyHandler keyH, int charNum2) {
		
		
	    this.gp = gp;
	    this.keyH = keyH;
	    this.charnum2 = charNum2;
	    
	    //solidArea = new Rectangle(4,8,16,16);
	    solidArea = new Rectangle(0,0,25,25);
	    

	    solidAreaDefaultX = solidArea.x;
	    solidAreaDefaultY = solidArea.y;
	    initializeCharNum();  
	    setDefaultValues();
	    getPlayerImage();
	    
	}
	private void initializeCharNum() {
        charnum2 = gp.ui.charNum2; // Accessing charNum after ui is initialized
    }
	

	public void setDefaultValues() {
		
		x = 21*gp.tileSize + 10;
		y = 15*gp.tileSize + 10;
		speed = 4;
		direction = "down";
		maxlife2 = 8;
		life2 =8;
		heart2 = 1;
		max_mana2 = 10;
		mana2 = 10;
		
	}
	
public void getPlayerImage() {
	
	

	System.out.println(charnum2);
	switch(charnum2) {
	
	case 0:
		name = "blue_girl";
		break;
	case 1:
		name = "blue_wizard";
		break;
	case 2:
		name = "caped_baldy";
		break;
	case 3:
		name = "muscle_girl";
		break;
	case 4:
		name = "ninja_guy";
		break;
	case 5:
		name = "pirate_head";
		break;
	case 6:
		name = "royal_boy";
		break;
	case 7:
		name = "royal_grey";
		break;
	case 8:
		name = "royal_red";
		break;
	case 9:
		name = "sand_trader";
		break;

	
	}


		try {
			up1 = ImageIO.read(getClass().getResource("/player/"+name+"_up1.png"));
			up2 = ImageIO.read(getClass().getResource("/player/"+name+"_up2.png"));
		    down1 = ImageIO.read(getClass().getResource("/player/"+name+"_down1.png"));
		    down2 = ImageIO.read(getClass().getResource("/player/"+name+"_down2.png"));
		    left1 = ImageIO.read(getClass().getResource("/player/"+name+"_left1.png"));
		    left2 = ImageIO.read(getClass().getResource("/player/"+name+"_left2.png"));
		    right1 = ImageIO.read(getClass().getResource("/player/"+name+"_right1.png"));
		    right2 = ImageIO.read(getClass().getResource("/player/"+name+"_right2.png"));

			
			
		} catch (IOException e) {
			
			e.printStackTrace();
		}

	    	    
	}
	
	public void update() {
		if (coinpickedstate2 == true) {
			coinpicked2++;
			
		}
		if (coinpicked2 > 300) {
			gp.obj[0] = null;
			gp.obj[0] = new OBJ_key(gp);
			gp.obj[0].X = 7 * gp.tileSize;
			gp.obj[0].Y = 5 * gp.tileSize;
			gp.obj[1] = null;
			gp.obj[1] = new OBJ_key(gp);
			gp.obj[1].X = 7 * gp.tileSize;
			gp.obj[1].Y = 6 * gp.tileSize;
			coinpicked2 = 0;
			coinpickedstate2 = false;
		}
		
		
		if (keyH.upPressed1 == true || keyH.downPressed1 == true || keyH.leftPressed1 == true || keyH.rightPressed1 == true) {
			
			if(keyH.upPressed1 == true ) {
				direction = "up";
				
			}
			else if (keyH.downPressed1 == true) {
				direction = "down";
				
			}
			else if (keyH.leftPressed1 == true) {
				direction = "left";
				
			}
			else if (keyH.rightPressed1 == true) {
				direction = "right";
				
			}
			
		
		

		collisionOn = false;
		gp.cChecker.checkTile(this);
		int objIndex = gp.cChecker.checkObject(this, true);
		pickUpObject(objIndex);
		
		if(collisionOn == false) {
		switch(direction) {
		case "up":
			y -= speed;
			break;
		case "down":
			y += speed;
			break;
		case "left":
			x -= speed;
			break;
		case "right":
			x += speed;	
			break;

		}
		
	}
		spriteCounter ++;
		if(spriteCounter > 10) {
			if(spriteNum == 1) {
				spriteNum = 2;
			}
			else if(spriteNum == 2) {
				spriteNum = 1;
			}
			spriteCounter = 0;
		}
		}
		
	}
public void pickUpObject (int i) {
		
		if(i != 999) {
			String objectName = gp.obj[i].name;
			
			switch(objectName) {
			case "Coin":
				score2++;
				System.out.println("Player2 Score:" + score2);
				coinpickedstate2 = true;
				gp.obj[i] = null;
				break;
			}
			
			Sound.RunSound("res/sound/coin_picked.wav");
		}
		
	}
	
public void draw(Graphics2D g2) {
	
	BufferedImage image = null;
	
	switch(direction) {
	case "up":
		if(spriteNum == 1) {
			image = up1;
		}
		if(spriteNum == 2) {
			image = up2;
		}
		break;
	case "down":
		if(spriteNum == 1) {
			image = down1;
		}
		if(spriteNum == 2) {
			image = down2;
		}
		break;
	case "left":
		if(spriteNum == 1) {
			image = left1;
		}
		if(spriteNum == 2) {
			image = left2;
		}
		break;
	case "right":
		if(spriteNum == 1) {
			image = right1;
		}
		if(spriteNum == 2) {
			image = right2;
		}
		break;	
	}
	g2.drawImage(image, x, y, 38, 38 ,null);
}
}









