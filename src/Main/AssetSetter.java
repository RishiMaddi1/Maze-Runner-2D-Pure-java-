package Main;

import object.OBJ_key;


public class AssetSetter {

	GamePanel gp;
	
	public AssetSetter(GamePanel gp) {
		this.gp = gp;
	}
	
	public void setObject () {
		gp.obj[0] = new OBJ_key(gp);
		gp.obj[0].X = gp.tileSize + 1;
		gp.obj[0].Y = gp.tileSize + 1;
		
//		gp.obj[1] = new OBJ_key(gp);
//		gp.obj[1].X = 7 * gp.tileSize;
//		gp.obj[1].Y = 6 * gp.tileSize;
	}

}
