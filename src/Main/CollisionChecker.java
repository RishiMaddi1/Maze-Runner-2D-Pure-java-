package Main;

import entity.Entity;

public class CollisionChecker {

    GamePanel gamep;

    public CollisionChecker(GamePanel gamep) {
        this.gamep = gamep;
    }

    
    public void checkTile(Entity entity) {
        int entityLeftX = entity.x + entity.solidArea.x;
        int entityRightX = entity.x + entity.solidArea.x + entity.solidArea.width;
        int entityTopY = entity.y + entity.solidArea.y;
        int entityBottomY = entity.y + entity.solidArea.y + entity.solidArea.height;

        int entityLeftCol = entityLeftX / gamep.tileSize;
        int entityRightCol = entityRightX / gamep.tileSize;
        int entityTopRow = entityTopY / gamep.tileSize;
        int entityBotRow = entityBottomY / gamep.tileSize;

        int tileNum1, tileNum2;

        switch (entity.direction) {
            case "up":
                entityTopRow = (entityTopY - entity.speed) / gamep.tileSize;
                tileNum1 = gamep.tileM.mapTileNum[entityLeftCol][entityTopRow];
                tileNum2 = gamep.tileM.mapTileNum[entityRightCol][entityTopRow];

                if (gamep.tileM.tile[tileNum1].collision == true || gamep.tileM.tile[tileNum2].collision == true) {
                    entity.collisionOn = true;
                }
                
                break;
            case "down":
            	entityBotRow = (entityBottomY + entity.speed) / gamep.tileSize;
                tileNum1 = gamep.tileM.mapTileNum[entityLeftCol][entityBotRow];
                tileNum2 = gamep.tileM.mapTileNum[entityRightCol][entityBotRow];

                if (gamep.tileM.tile[tileNum1].collision == true || gamep.tileM.tile[tileNum2].collision == true) {
                    entity.collisionOn = true;
                }

                break;
            case "left":
            	entityLeftCol = (entityLeftX - entity.speed) / gamep.tileSize;
                tileNum1 = gamep.tileM.mapTileNum[entityLeftCol][entityTopRow];
                tileNum2 = gamep.tileM.mapTileNum[entityLeftCol][entityBotRow];

                if (gamep.tileM.tile[tileNum1].collision == true || gamep.tileM.tile[tileNum2].collision == true) {
                    entity.collisionOn = true;
                }

                break;
            case "right":
            	entityRightCol = (entityRightX + entity.speed) / gamep.tileSize;
                tileNum1 = gamep.tileM.mapTileNum[entityRightCol][entityTopRow];
                tileNum2 = gamep.tileM.mapTileNum[entityRightCol][entityBotRow];

                if (gamep.tileM.tile[tileNum1].collision == true || gamep.tileM.tile[tileNum2].collision == true) {
                    entity.collisionOn = true;
                }

                break;
        }
    }

	public int checkObject(Entity entity, boolean player) {
    	int index = 999;
    	
    	for (int i = 0; i < gamep.obj.length; i++) {
    		if(gamep.obj[i] != null) {
    			//get entities solid area position
    			entity.solidArea.x = entity.x + entity.solidArea.x;
    			entity.solidArea.y = entity.y + entity.solidArea.y;
    			
    			//get the entities solid area position
    			// Check for null before accessing gamep.obj[i].solidArea
    			if (gamep.obj[i] != null) {
    			    gamep.obj[i].solidArea.x = gamep.obj[i].X + gamep.obj[i].solidArea.x;
    			    gamep.obj[i].solidArea.y = gamep.obj[i].Y + gamep.obj[i].solidArea.y; 
    			}

    			
    			
    			switch(entity.direction) {
    			case "up":
    				entity.solidArea.y -= entity.speed;
    				if(entity.solidArea.intersects(gamep.obj[i].solidArea)) {
    					if(gamep.obj[i].collision == true) {
    						entity.collisionOn = true;
    					}
    					if (player == true) {
    						index = i;
    					}
    					
    					
    				}
    				break;
    			case "down":
    				entity.solidArea.y += entity.speed;
    				if(entity.solidArea.intersects(gamep.obj[i].solidArea)) {
    					if(gamep.obj[i].collision == true) {
    						entity.collisionOn = true;
    					}
    					if (player == true) {
    						index = i;
    					}
    					
    					
    				}
    				break;
    			case "left":
    				entity.solidArea.x -= entity.speed;
    				if(entity.solidArea.intersects(gamep.obj[i].solidArea)) {
    					if(gamep.obj[i].collision == true) {
    						entity.collisionOn = true;
    					}
    					if (player == true) {
    						index = i;
    					}
    					
    					
    				}
    				break;
    			case "right":
    				entity.solidArea.x += entity.speed;
    				if(entity.solidArea.intersects(gamep.obj[i].solidArea)) {
    					if(gamep.obj[i].collision == true) {
    						entity.collisionOn = true;
    					}
    					if (player == true) {
    						index = i;
    					}
    					
    					
    				}
    				break;
    			}
    		}
    		entity.solidArea.x = entity.solidAreaDefaultX;
    		entity.solidArea.y = entity.solidAreaDefaultY;
    		
    		if (gamep.obj[i] != null) {
    		    gamep.obj[i].solidArea.x = gamep.obj[i].solidAReaDefaultX; 
    		    gamep.obj[i].solidArea.y = gamep.obj[i].solidAReaDefaultY; 
    		}


    		
    	}
    	
    	return index;
    }
	//npc or monster collsion
//	public int checkEntity(Entity entity, Entity[] target) {
//    	int index = 999;
//    	
//    	for (int i = 0; i < gamep.target.length; i++) {
//    		if(gamep.obj[i] != null) {
//    			//get entities solid area position
//    			entity.solidArea.x = entity.x + entity.solidArea.x;
//    			entity.solidArea.y = entity.y + entity.solidArea.y;
//    			
//    			//get the entities solid area position
//    			// Check for null before accessing gamep.obj[i].solidArea
//    			if (gamep.obj[i] != null) {
//    			    gamep.obj[i].solidArea.x = gamep.obj[i].X + gamep.obj[i].solidArea.x;
//    			    gamep.obj[i].solidArea.y = gamep.obj[i].Y + gamep.obj[i].solidArea.y; 
//    			}
//
//    			
//    			
//    			switch(entity.direction) {
//    			case "up":
//    				entity.solidArea.y -= entity.speed;
//    				if(entity.solidArea.intersects(gamep.obj[i].solidArea)) {
//    					if(gamep.obj[i].collision == true) {
//    						entity.collisionOn = true;
//    					}
//    					if (player == true) {
//    						index = i;
//    					}
//    					
//    					
//    				}
//    				break;
//    			case "down":
//    				entity.solidArea.y += entity.speed;
//    				if(entity.solidArea.intersects(gamep.obj[i].solidArea)) {
//    					if(gamep.obj[i].collision == true) {
//    						entity.collisionOn = true;
//    					}
//    					if (player == true) {
//    						index = i;
//    					}
//    					
//    					
//    				}
//    				break;
//    			case "left":
//    				entity.solidArea.x -= entity.speed;
//    				if(entity.solidArea.intersects(gamep.obj[i].solidArea)) {
//    					if(gamep.obj[i].collision == true) {
//    						entity.collisionOn = true;
//    					}
//    					if (player == true) {
//    						index = i;
//    					}
//    					
//    					
//    				}
//    				break;
//    			case "right":
//    				entity.solidArea.x += entity.speed;
//    				if(entity.solidArea.intersects(gamep.obj[i].solidArea)) {
//    					if(gamep.obj[i].collision == true) {
//    						entity.collisionOn = true;
//    					}
//    					if (player == true) {
//    						index = i;
//    					}
//    					
//    					
//    				}
//    				break;
//    			}
//    		}
//    		entity.solidArea.x = entity.solidAreaDefaultX;
//    		entity.solidArea.y = entity.solidAreaDefaultY;
//    		
//    		if (gamep.obj[i] != null) {
//    		    gamep.obj[i].solidArea.x = gamep.obj[i].solidAReaDefaultX; 
//    		    gamep.obj[i].solidArea.y = gamep.obj[i].solidAReaDefaultY; 
//    		}
//
//
//    		
//    	}
//    	
//    	return index;
//	}
}
	