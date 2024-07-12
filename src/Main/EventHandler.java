package Main;

import java.awt.Rectangle;

public class EventHandler {
	GamePanel gp;
	Rectangle eventRect;
	int eventRectDefaultX, eventRectDefaultY;
	
	
	public EventHandler(GamePanel gp) {
		this.gp = gp;
		
		eventRect = new Rectangle();
		eventRect.x = 0;
		eventRect.y = 0;
		eventRect.width = 48;
		eventRect.height = 48;
		eventRectDefaultX = eventRect.x;
		eventRectDefaultY = eventRect.y;	
	}
	
		
	}
	
	public boolean hit(int eventcol, int eventRow, String reqDirection) {
		boolean hit = false;
		
		gp.player.solidArea.x = gp.player.x + gp.player.solidArea.x;
		gp.player.solidArea.y = gp.player.y + gp.player.solidArea.y;
		eventRect.x = eventcol*gp.tileSize + eventRect.x;
		eventRect.y = eventcol*gp.tileSize + eventRect.y;
		
		
		if(gp.player.solidArea.intersects(eventRect)) {
			if(gp.player.direction.equals(reqDirection) || reqDirection.contentEquals("any")) {
				hit = true;
			}
			gp.player.solidArea.x = gp.player.solidAreaDefaultX;
			gp.player.solidArea.y = gp.player.solidAreaDefaultY;
			eventRect.x = eventRectDefaultX;
			eventRect.y = eventRectDefaultY;		
			
		
		
	}
		return hit;
	
	
    }
}
