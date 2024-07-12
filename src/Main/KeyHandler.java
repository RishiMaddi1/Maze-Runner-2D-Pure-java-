package Main;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import entity.Entity;


public class KeyHandler extends Entity implements KeyListener{
	public int charNum2;
	public int charNum;
	public int CharNum;
	public int CharNum2;
	public int p1c,p2c;
	GamePanel gp;
	public boolean upPressed, downPressed, leftPressed, rightPressed,upPressed1, downPressed1, leftPressed1, rightPressed1,iceshotPressed;
	//public BufferedImage up1, up2, down1, down2, right1, right2, left1, left2,pup1, pup2, pdown1, pdown2, pright1, pright2, pleft1, pleft2;
	public boolean c1 = false;
	public boolean c2 = false;

	boolean checkDrawTime = false;
	public static String name;
    public static String name2;
	
	public KeyHandler(GamePanel gp) {
		this.gp = gp;
	}
	
	@Override
	public void keyTyped(KeyEvent e) {
		
		
	}
	
	@Override
	public void keyPressed(KeyEvent e) {
		int code = e.getKeyCode();
		//title state
		
		if(gp.ui.titleScreenState == 0) {
			if(gp.gameState == gp.titleState) {
				if (code == KeyEvent.VK_W) {
					Sound.RunSound("res/sound/select.wav");
					gp.ui.commandNum--;
					if(gp.ui.commandNum < 0) {
						gp.ui.commandNum = 0;
					}
				}
				if (code == KeyEvent.VK_S) {
					Sound.RunSound("res/sound/select.wav");
					gp.ui.commandNum++;
					if(gp.ui.commandNum > 0) {
						gp.ui.commandNum = 1;
					}
				}
				if(code == KeyEvent.VK_ENTER) {
					if(gp.ui.commandNum == 0) {
						gp.ui.titleScreenState = 1;
						
						
					}
					if(gp.ui.commandNum == 1) {
						System.exit(0);
					}
				}
			}
		}
		

		if(gp.ui.titleScreenState == 1) {
			if(gp.gameState == gp.titleState) {

				if (code == KeyEvent.VK_A) {
					Sound.RunSound("res/sound/select.wav");
					gp.ui.charNum--;
					
					
					if(gp.ui.charNum < 0) {
						gp.ui.charNum = 9;
						
					}
					
					

					
				}
				if (code == KeyEvent.VK_D) {
					Sound.RunSound("res/sound/select.wav");
					gp.ui.charNum++;
					
					if(gp.ui.charNum > 9) {
						gp.ui.charNum = 0;
						
					}

					
				}
				if (code == KeyEvent.VK_LEFT) {
					Sound.RunSound("res/sound/select.wav");
					gp.ui.charNum2--;
					
					
					if(gp.ui.charNum2 < 0) {
						gp.ui.charNum2 = 0;
						
					}

					

				}
				if (code == KeyEvent.VK_RIGHT) {
					Sound.RunSound("res/sound/select.wav");
					gp.ui.charNum2++;
					
					if(gp.ui.charNum2 > 9) {
						gp.ui.charNum2 = 0;
						
					}

					
				}

				
				else if (code == KeyEvent.VK_SPACE) {
					
					gp.gameState = gp.playState;
					//gp.ui.titleScreenState = 2;
					Sound.RunMusic("res/sound/pink.wav");
					
					//after pressing this and the values go into their classes the player should not attempt to be loaded

					
				}
				else if (code == KeyEvent.VK_ESCAPE) {
					gp.ui.titleScreenState = 0;
					
				}
			}
		}
		if(gp.ui.titleScreenState == 2) {
			if(gp.gameState == gp.titleState) {
				if (code == KeyEvent.VK_ENTER) {
					
					gp.gameState = gp.playState;
					Sound.RunMusic("res/sound/musicW.wav");
					
					
				}
			}
		}
		
		
		
		
		
		//wasd for player1
		if (code == KeyEvent.VK_W) {
			upPressed = true;
		}
		if (code == KeyEvent.VK_S) {
			downPressed = true;
		}
        if (code == KeyEvent.VK_A) {
			leftPressed = true;
		}
        if (code == KeyEvent.VK_D) {
        	rightPressed = true;
        }
        // arrow keys for player 2
        if (code == KeyEvent.VK_UP) {
            upPressed1 = true;
        }
        if (code == KeyEvent.VK_DOWN) {
            downPressed1 = true;
        }
        if (code == KeyEvent.VK_LEFT) {
            leftPressed1 = true;
        }
        if (code == KeyEvent.VK_RIGHT) {
            rightPressed1 = true;
        }
        //attacks
//        if (code == KeyEvent.VK_F) {
//            iceshotPressed = true;
//        }
        
        if (code == KeyEvent.VK_ESCAPE) {
            if(gp.gameState == gp.playState) {
            	gp.gameState = gp.pauseState;
            }else if (gp.gameState == gp.pauseState){
            	gp.gameState = gp.playState;
            }
        }
        
        //debug
        if (code == KeyEvent.VK_T) {
            if(checkDrawTime == false) {
            	checkDrawTime = true;
            }
            else if(checkDrawTime == true) {
            	checkDrawTime = false;
            }
        }
        

		
		
	}

	@Override
	public void keyReleased(KeyEvent e) {
		
		int code = e.getKeyCode();
		
		if (code == KeyEvent.VK_W) {
			upPressed = false;
		}
		if (code == KeyEvent.VK_S) {
			downPressed = false;
		}
        if (code == KeyEvent.VK_A) {
			leftPressed = false;
		}
        if (code == KeyEvent.VK_D) {
        	rightPressed = false;
        }
        
        
        if (code == KeyEvent.VK_UP) {
            upPressed1 = false;
        }
        if (code == KeyEvent.VK_DOWN) {
            downPressed1 = false;
        }
        if (code == KeyEvent.VK_LEFT) {
            leftPressed1 = false;
        }
        if (code == KeyEvent.VK_RIGHT) {
            rightPressed1 = false;
        }
//        if (code == KeyEvent.VK_F) {
//            iceshotPressed = false;
//        }
		
	}


	
}







