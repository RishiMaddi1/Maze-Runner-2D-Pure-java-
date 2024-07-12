package object;

import java.io.IOException;

import javax.imageio.ImageIO;

import Main.GamePanel;

public class OBJ_key extends SuperObject{
	
	GamePanel gp;
	
	public OBJ_key(GamePanel gp) {
		name = "Coin";
		try {
			image = ImageIO.read(getClass().getResourceAsStream("/objects/coin_1.png"));
			uTool.scaleImage(image,  gp.tileSize,  gp.tileSize);
		}catch(IOException e) {
			e.printStackTrace();
		}
		collision = true;
	}
	
	
	
}
