import java.applet.Applet;
import java.awt.*;
import java.awt.event.*;

public class Spellbound extends Applet implements Runnable, KeyListener, MouseListener
{	
	// Background
	// Image forest = Toolkit.getDefaultToolkit().getImage("forest_completed.png");
	ImageLayer forest_11 = new ImageLayer("forest_11.png", 0, -270, 10);
	ImageLayer forest_10 = new ImageLayer("forest_10.png", 0, -270, 10);
	ImageLayer forest_9 = new ImageLayer("forest_9.png", 0, -270, 8);
	ImageLayer forest_8 = new ImageLayer("forest_8.png", 0, -270, 8);
	ImageLayer forest_7 = new ImageLayer("forest_7.png", 0, -270, 6);
	ImageLayer forest_6 = new ImageLayer("forest_6.png", 0, -270, 6);
	ImageLayer forest_5 = new ImageLayer("forest_5.png", 0, -270, 4);
	ImageLayer forest_4 = new ImageLayer("forest_4.png", 0, -270, 4);
	ImageLayer forest_3 = new ImageLayer("forest_3.png", 0, -270, 2);
	ImageLayer forest_2 = new ImageLayer("forest_2.png", 0, -270, 2);
	ImageLayer forest_1 = new ImageLayer("forest_1.png", 0, -270, 1);
	ImageLayer forest_0 = new ImageLayer("forest_0.png", 0, -270, 1);

	
	
	
	// AI Enemy
	String[] venustrap_pose = {"LTidle", "RTidle", "LTwalk", "RTwalk", "LTattack", "RTattack"};
	int [] venustrap_count = {4, 4, 6, 6, 6, 6};
	int [] venustrap_duration = {10, 10, 8, 8, 6, 6};
	
	Hitbox venustrap_hitbox = new Hitbox(500, 520, 128, 128);
	AI_control venustrap = new AI_control("venustrap", venustrap_pose, 900, 520, 128, 128, venustrap_count, venustrap_duration);
	
	// AI Enemy
	String[] scorpion_pose = {"LTidle", "RTidle", "LTwalk", "RTwalk", "LTattack", "RTattack"};
	int [] scorpion_count = {4, 4, 4, 4, 4, 4, 4};
	int [] scorpion_duration = {10, 10, 10, 10, 6, 6, 15};
		
	Hitbox scorpion_hitbox = new Hitbox(300, 716, 48, 48);
	AI_control scorpion = new AI_control("scorpion", scorpion_pose, 700, 602, 48, 48, scorpion_count, scorpion_duration);
		
	// PLAYER
	String[] player_pose = {"LTidle", "RTidle", "LTwalk", "RTwalk", "LTrun", "RTrun", "LTdeath", "RTdeath", "LTattack", "RTattack"};
	int [] player_count = {8, 8, 7, 7, 8, 8, 4, 4, 7, 7};
	int [] player_duration = {7, 7, 6, 6, 5, 5, 10, 10, 8, 8};
			
	
	Hitbox player_hitbox = new Hitbox(50, 590, 50, 120);
	Sprite player = new Sprite("wm", player_pose, 0, 395, 256, 256, player_count, player_duration);
	
	// GUI
	UI health = new UI(20, 50,256,40);
	
	UI menu = new UI(0,0, 1800, 720);
	UI death = new UI(0,0, 1800, 720);
	
	Rect start_button = new Rect(677, 495, 480, 60);
	//Rect restart_button = new Rect(587, 481, 660, 60);
	
	Rect quit_button = new Rect(720, 654, 390, 60);
	
	// Boundaries
	Rect top_wall = new Rect(0, -50, 1800, 50);
	Rect left_wall = new Rect(-50, 0, 50, 800);
	Rect floor = new Rect(0, 650, 1800, 85);
	

	//  Menu Controls
	boolean gameMenu = true;
	
	// PLayer Controls
	boolean UP_Pressed = false;
	boolean LT_Pressed = false;
	boolean RT_Pressed = false;
	boolean shift_Pressed = false;
	boolean attack_Pressed = false;
	
	// Testing Controls
	boolean testing_Tool = false;
	
	//Paints Image to offScreenImg to reduce flicker
	Image offScreenImg;
	Graphics offScreenPen;
	
	public void run()
	{
		
		// Game Loop
		while(true)
		{
			//if(UP_Pressed)
			//{
			//player.moveUP(2);
			//}

			
			if (gameMenu == false) 
			{
				// Walking
				if(LT_Pressed && health.playerHealth > 0)
				{
					player.walkLT(2);
					//Camera.moveLT(2);
				}
				if(RT_Pressed && health.playerHealth > 0)
				{
					player.walkRT(2);
					//Camera.moveRT(2);
				}
				
				// Running
				if(LT_Pressed && shift_Pressed && health.playerHealth > 0)
				{
					player.runLT(4);
					//Camera.moveLT(4);
				}
				if(RT_Pressed && shift_Pressed && health.playerHealth > 0)
				{
					player.runRT(4);
					//Camera.moveRT(4);
				}
			
				// AI_Control 
				venustrap_hitbox.track(venustrap);
				venustrap.chase(player_hitbox, venustrap_hitbox, 2);
				
				scorpion_hitbox.track(scorpion);
				scorpion.chase(player_hitbox, scorpion_hitbox,  3);
				
				//if(scorpion.x == 400) scorpion.x = 0;
				
				// PLAYER
				player_hitbox.player_track(player);
			}
				
			repaint();
			
			try
			{			
				Thread.sleep(16);
			}
			catch(Exception x) {};
		}
	}
	
    @Override
    public void stop() {
        // Suspend execution
        System.out.println("Applet game stopped");
        System.exit(0);
    }

	public void paint(Graphics pen)
	{
		pen.setColor(Color.WHITE);
		
		 // Menu screen
		if(health.playerHealth > 0 && gameMenu == true) 
		{
			
		    menu.draw_menu(pen);
		    start_button.draw(pen);
		    quit_button.draw(pen);
		   
		}
		
		// Death Screen
		if(health.playerHealth == 0) {
			 death.draw_gameover(pen);
			 quit_button.set(727, 639, 390, 60);
			 quit_button.draw(pen);
			// restart_button.draw(pen);
		}
		
		
			
		// Game start
		if (health.playerHealth > 0 && gameMenu == false) 
		{
		    // Sets background ImageLayers
		    forest_11.draw(pen);  
		    forest_10.draw(pen);
		    forest_9.draw(pen); 
		    forest_8.draw(pen); 
		    forest_7.draw(pen); 
		    forest_6.draw(pen); 
		    forest_5.draw(pen);		
		    forest_4.draw(pen);
		    forest_3.draw(pen);	
		    forest_2.draw(pen);	
		    forest_1.draw(pen); 
		    forest_0.draw(pen);  
		    
		   
		    // PLAYER
		    player.draw(pen);
		    
		    //Health_UI
		    health.health_UI_draw(pen, player_hitbox, venustrap_hitbox);
		    health.health_UI_draw(pen, player_hitbox, scorpion_hitbox);
		    
		    
		    //	AI
		    venustrap.ai_draw(pen, venustrap_hitbox, player_hitbox);
		    scorpion.ai_draw(pen, scorpion_hitbox, player_hitbox);
		    
		    //Testing Tool
		    if(testing_Tool == true) 
		    {
			    // Sets the color to green for the player_hitbox
			    pen.setColor(Color.GREEN);
			    player_hitbox.draw(pen);
		
			    // Sets the colors for other elements to Default
			    pen.setColor(Color.BLACK);
			    floor.draw(pen);
			    top_wall.draw(pen);
			    left_wall.draw(pen);
		
			    // Sets the colors for AI_enemies_hitbox
			    pen.setColor(Color.RED);
			    venustrap_hitbox.draw(pen);
			    scorpion_hitbox.draw(pen);
		
			    // Sets the color to red if the player_hitbox overlaps with the AI_enemies_hitbox
			    if (venustrap_hitbox.overlaps(player_hitbox)) 
			    {
			    	 pen.setColor(Color.RED);
				     player_hitbox.draw(pen);
			    }
			    
			    if (scorpion_hitbox.overlaps(player_hitbox)) 
			    {
			    	 pen.setColor(Color.RED);
				     player_hitbox.draw(pen);
			    }
		    }
		    
		    // Reset the color to default
		    pen.setColor(Color.BLACK);
		    
		}
	}

	
	// Updates the image on the screen
	public void update(Graphics pen)
	{
		offScreenPen.clearRect(0, 0, 1920, 1000);
		
		paint(offScreenPen);
		
		pen.drawImage(offScreenImg, 0, 0, null);
	}
	
	
	public void init()
	{
		offScreenImg = createImage(1920, 1000);
		offScreenPen = offScreenImg.getGraphics();
		
		addKeyListener(this);
		requestFocus();
		
		addMouseListener(this);

		Thread t = new Thread(this);

		t.start();
	}
	
	public void keyPressed(KeyEvent e)
	{		
		int code = e.getKeyCode();
		
		if (code == e.VK_W)   UP_Pressed = true;   
		if (code == e.VK_A)   LT_Pressed = true;  
		if (code == e.VK_D)   RT_Pressed = true; 
		
		if (code == e.VK_J) {	  
			attack_Pressed = true;
			System.out.println("Attack");
		}
		
		if (code == e.VK_SHIFT)  shift_Pressed = true;  
		
		// Menu Controls / Pause game
		if (code == e.VK_M) gameMenu = true;
		
		
		//Testing tool shows hitboxes and terrain boundaries
		if (code == e.VK_T ) 
		{
			if (testing_Tool == true) {
				testing_Tool = false; 
			}else {
				testing_Tool = true; 
			}
			
		}
	}
	
	public void keyReleased(KeyEvent e)
	{
		int code = e.getKeyCode();

		if (code == e.VK_W)   UP_Pressed = false;  
		if (code == e.VK_A)   LT_Pressed = false;  
		if (code == e.VK_D)   RT_Pressed = false;
		if (code == e.VK_J)	  attack_Pressed = false;
		if (code == e.VK_SHIFT)  shift_Pressed = false;  
	}

	public void mousePressed(MouseEvent e) {
		int mX = e.getX();
		int mY = e.getY();
		
		
		//System.out.println("(" + mX + " , " + mY + ")");
		
		// Menu Screen
		if(gameMenu == true) {
			if (start_button.contains(mX, mY)) {
				//Starts the game
				gameMenu = false;
			}
			
			if(quit_button.contains(mX, mY)) {
				System.out.println("(" + mX + " , " + mY + ")");
			    stop();
			}
		}
		
		// Death Menu Screen 
		if(health.playerHealth == 0 && quit_button.contains(mX, mY)) {
		    stop();
		}
		
	}
	public void mouseReleased(MouseEvent e) {}
	
	// No used for now
	public void mouseClicked(MouseEvent e) {}
	public void mouseExit(MouseEvent e) {}
	public void mouseEntered(MouseEvent e) {}
	public void keyTyped(KeyEvent e) {}



	@Override
	public void mouseExited(MouseEvent arg0) {
		// TODO Auto-generated method stub
		
	}
	
}