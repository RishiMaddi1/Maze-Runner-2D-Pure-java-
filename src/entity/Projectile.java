package entity;

import Main.GamePanel;

public class Projectile extends Entity {
	
	Entity user;
	
	public Projectile(GamePanel gp) {
		super();
	}
	
	public void set(int x, int y, String direction, boolean alive, Entity user) {
		this.x = x;
		this.y = y;
		this.direction = direction;
		this.alive = alive;
		this.user = user;
		this.Life = this.maxLife;
	}
	
	public void update() {
		switch(direction) {
		case "up":
			y -= speed;
			break;
		case "down":
			y += speed;
			break;
		case "right":
			x += speed;
			break;
		case "left":
			x -= speed;
			break;
		}
		
		Life--;
		if(Life <= 0) {
			alive = false;
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
