package tile;

import java.awt.Graphics2D;
import java.io.BufferedReader;
import java.io.IOException;

import javax.imageio.ImageIO;

import Main.GamePanel;
import Main.UtilityTool;

import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.Random;


public class TileManager {
	GamePanel gp;
	public Tile[] tile;
	public int mapTileNum[][];
	
	public TileManager(GamePanel gp) {
		this.gp = gp;
		
		tile = new Tile[10];
		mapTileNum = new int[gp.maxScreenCol][gp.maxScreenRow];
		getTileImage();
		Random random = new Random();
        int randomMazeNumber = random.nextInt(95) + 5; 
        System.out.println(randomMazeNumber);
        String filePath = String.format("/maps/maze%d.txt", randomMazeNumber);
        loadMap(filePath);
		
	}
	public void getTileImage() {
			Random random = new Random();
			int randnumber = random.nextInt(4) + 1;
			
			switch(randnumber) {
			case 1:
				setup(0, "arabictile1", false, false);
				setup(1, "arabicwall1", true, false);
				break;
			case 2:
				setup(0, "lilytile", false, false);
				setup(1, "lilytree", true, false);
				break;
			case 3:
				setup(0, "snowtile", false, false);
				setup(1, "snowtree", true, false);
				break;
			case 4:
				setup(0, "sandtile", false, false);
				setup(1, "sandtree", true, false);
				break;	
			}
		
	}
	
	public void setup(int index, String imageName, boolean collision,boolean isLava) {
		UtilityTool utool = new UtilityTool();
		
		try {
			tile[index] = new Tile();
			tile[index].image = ImageIO.read(getClass().getResourceAsStream("/tiles/"+ imageName +".png"));
			tile[index].image = utool.scaleImage(tile[index].image, gp.tileSize, gp.tileSize);
			tile[index].collision = collision;
			
		}catch(IOException e) {
			e.printStackTrace();
		}
	}
	
	public void loadMap(String filePath) {
	    try {
	        InputStream is = getClass().getResourceAsStream(filePath);
	        BufferedReader br = new BufferedReader(new InputStreamReader(is));
	        
	        int col = 0;
	        int row = 0;
	        boolean firstLineSkipped = false; 
	        
	        String line;
	        while ((line = br.readLine()) != null && col < gp.maxScreenCol && row < gp.maxScreenRow) {
	            // Skip the first line
	            if (!firstLineSkipped) {
	                firstLineSkipped = true;
	                continue;
	            }
	            
	            // Start reading from the second column
	            col = 0;
	            String numbers[] = line.split(" ");
	            
	            for (int i = 1; i < numbers.length && col < gp.maxScreenCol; i++) {
	                int num = Integer.parseInt(numbers[i]);
	                mapTileNum[col][row] = num;
	                col++;
	            }
	            row++;
	        }
	        br.close();
	    } catch(Exception e) {
	        e.printStackTrace(); // Handle the exception properly instead of just ignoring it
	    }
	}

	public void draw(Graphics2D g2) {
		
		int col = 0;
		int row = 0;
		int x = 0;
		int y = 0;
		
		
		while(col < gp.maxScreenCol && row < gp.maxScreenRow) {
			
			int tileNum = mapTileNum[col][row];
			
			g2.drawImage(tile[tileNum].image, x, y, null);
			col++;
			x += gp.tileSize;
			
			if(col == gp.maxScreenCol) {
				col = 0;
				x = 0;
				row ++;
				y += gp.tileSize;
				
			}
			
			
		}
	}

}
