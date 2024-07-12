package entity;

import java.awt.Graphics2D;

import java.awt.Rectangle;
import java.awt.image.BufferedImage;
import java.io.IOException;
import javax.imageio.ImageIO;

import Main.GamePanel;
import Main.KeyHandler;
import Main.Sound;


public class Player extends Entity {
	
	GamePanel gp;
	KeyHandler keyH;
	public static int score = 0;
	public int coinpicked = 0;
	public static boolean coinpickedstate = false;

	private String name;
	public int charnum;
	public static int heart;
	public static int maxlife;
	public static int life;
	public static int max_mana;
	public static int mana;
	
	
	public int getScore() {
		return score;
	}

	
	public Player(GamePanel gp, KeyHandler keyH, int charNum) {
	    this.gp = gp;
	    this.keyH = keyH;
	    this.charnum = charNum;
	    
	    //solidArea = new Rectangle(4,8,16,16);
	    solidArea = new Rectangle(0,0,25,25);
	    

	    solidAreaDefaultX = solidArea.x;
	    solidAreaDefaultY = solidArea.y;
	    
	    initializeCharNum();
	    setDefaultValues();
	    getPlayerImage();
	    
	    
	}
	private void initializeCharNum() {
        charnum = gp.ui.charNum; // Accessing charNum after ui is initialized
    }

	
	public void setDefaultValues() {
		
		x = 21*gp.tileSize + 10;
		y = 15*gp.tileSize + 10;
		speed = 4;
		direction = "down";
		maxlife = 8;
		life = 8;
		heart = 1;
		max_mana = 10;
		mana = 10;
		
	}
	
	public void getPlayerImage() {
		System.out.println(charnum);
		switch(charnum) {
		
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
		       //explain this in detail the exact thing that is achieved by using the exception handling here rather than just normally doing it
	}

	public void update() {
	
		
//		if (coinpickedstate == true) {
//			coinpicked++;
//			
//		}
//		if (coinpicked > 300) {
//			gp.obj[0] = null;
//			gp.obj[0] = new OBJ_key(gp);
//			gp.obj[0].X = 7 * gp.tileSize;
//			gp.obj[0].Y = 5 * gp.tileSize;
//			gp.obj[1] = null;
//			gp.obj[1] = new OBJ_key(gp);
//			gp.obj[1].X = 7 * gp.tileSize;
//			gp.obj[1].Y = 6 * gp.tileSize;
//			coinpicked = 0;
//			coinpickedstate = false;
//		}
		
		
		if (keyH.upPressed == true || keyH.downPressed == true || keyH.leftPressed == true || keyH.rightPressed == true) {
			
			if(keyH.upPressed == true ) {
				direction = "up";
				
			}
			else if (keyH.downPressed == true) {
				direction = "down";
				
			}
			else if (keyH.leftPressed == true) {
				direction = "left";
				
			}
			else if (keyH.rightPressed == true) {
				direction = "right";
				
			}
			
		
		gp.eHandler.checkEvent();

		collisionOn = false;
		gp.cChecker.checkTile(this);
		
		// checking object collision
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
				
				score++;
				System.out.println("Player1 Score:" + score);
				coinpickedstate = true;
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
		g2.drawImage(image, x, y,38, 38 ,null);
	}
}









