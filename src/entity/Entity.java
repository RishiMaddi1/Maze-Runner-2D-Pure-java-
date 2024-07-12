package entity;

import java.awt.Rectangle;
import java.awt.image.BufferedImage;

public class Entity {
	public int x, y;
	
	public boolean alive = false;
	public boolean dying = true;
	
	// character attributes
	public int speed;
	public int maxLife, Life;
	public String name;
	public int maxMana;
	public int mana;
	public int attack;
	
	public Projectile projectile;
	
	public int useCost;
	
	public BufferedImage up1, up2,up3,up4,up5,up6,up7,up8,up9,up10, down1, down2, down3, down4,down5, down6,down7, down8,down9, down10 ,mc,pup1, pup2, pdown1, pdown2, pright1, pright2, pleft1, pleft2,p2up1, p2up2, p2down1, p2down2, p2right1, p2right2, p2left1, p2left2;
	public BufferedImage right1, right2, right3, right4, right5, right6, right7, right8,right9, right10 , left1, left2,left3, left4,left5, left6,left7, left8,left9, left10;
	public String direction, ntg, drawAttack;
	
	public int spriteCounter = 0;
	public int spriteNum = 1;
	 
	public Rectangle solidArea;
	public int solidAreaDefaultX, solidAreaDefaultY;
	
	public boolean collisionOn = false;
	
}
