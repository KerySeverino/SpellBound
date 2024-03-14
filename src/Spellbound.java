import java.applet.Applet;
import java.awt.*;
import java.awt.event.*;

public class Spellbound extends Applet implements Runnable, KeyListener
{	
	//Background
	//Image forest = Toolkit.getDefaultToolkit().getImage("forest_completed.png");
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

	
	
	
	//AI Enemy
	String[] venustrap_pose = {"LTidle", "RTidle", "LTwalk", "RTwalk", "LTattack", "RTattack"};
	int [] venustrap_count = {4, 4, 6, 6, 6, 6};
	int [] venustrap_duration = {10, 10, 10, 10, 6, 6};
	
	Hitbox venustrap_hitbox = new Hitbox(500, 520, 128, 128);
	AI_control venustrap = new AI_control("venustrap", venustrap_pose, 500, 520, 128, 128, venustrap_count, venustrap_duration);
	
	//AI Enemy
	String[] scorpion_pose = {"LTidle", "RTidle", "LTwalk", "RTwalk", "LTattack", "RTattack"};
	int [] scorpion_count = {4, 4, 4, 4, 4, 4, 4};
	int [] scorpion_duration = {10, 10, 10, 10, 6, 6, 15};
		
	Hitbox scorpion_hitbox = new Hitbox(300, 716, 48, 48);
	AI_control scorpion = new AI_control("scorpion", scorpion_pose, 300, 602, 48, 48, scorpion_count, scorpion_duration);
		
	//PLAYER
	String[] player_pose = {"LTidle", "RTidle", "LTwalk", "RTwalk", "LTrun", "RTrun", "LTdeath", "RTdeath"};
	int [] player_count = {8, 8, 7, 7, 8, 8, 4, 4};
	int [] player_duration = {10, 10, 10, 10, 10, 10, 10, 10};
			
	
	Hitbox player_hitbox = new Hitbox(50, 590, 50, 120);
	Sprite player = new Sprite("wm", player_pose, 50, 395, 256, 256, player_count, player_duration);
	Health_UI health = new Health_UI(20, 50,256,40);
	
	//Boundaries
	Rect top_wall = new Rect(0, -50, 1800, 50);
	Rect left_wall = new Rect(-50, 0, 50, 800);
	Rect floor = new Rect(0, 650, 1800, 85);

	//Controls
	boolean UP_Pressed = false;
	boolean LT_Pressed = false;
	boolean RT_Pressed = false;
	boolean shift_Pressed = false;
	boolean testing_Tool = false;
	
	//Paints Image to offScreenImg to reduce flicker
	Image offScreenImg;
	Graphics offScreenPen;
	
	public void run()
	{
		// Game Loop
		while(true)
		{
//			if(UP_Pressed)
//			{
//				player.moveUP(2);
//			}

			//Walking
			if(LT_Pressed && health.playerHealth > 0)
			{
				player.walkLT(2);
				forest_11.moveLT(10);
				forest_10.moveLT(10);
				forest_9.moveLT(10);
				forest_8.moveLT(10);
				forest_7.moveLT(10);
				forest_6.moveLT(10);
				forest_5.moveLT(10);
				forest_4.moveLT(10);
				forest_3.moveLT(10);
				forest_2.moveLT(10);
				forest_1.moveLT(10);
				forest_0.moveLT(10);
			}
			if(RT_Pressed && health.playerHealth > 0)
			{
				player.walkRT(2);
				forest_11.moveRT(10);
				forest_10.moveRT(10);
				forest_9.moveRT(10);
				forest_8.moveRT(10);
				forest_7.moveRT(10);
				forest_6.moveRT(10);
				forest_5.moveRT(10);
				forest_4.moveRT(10);
				forest_3.moveRT(10);
				forest_2.moveRT(10);
				forest_1.moveRT(10);
				forest_0.moveRT(10);
			}
			
			//Running
			if(LT_Pressed && shift_Pressed && health.playerHealth > 0)
			{
				player.runLT(4);
			}
			if(RT_Pressed && shift_Pressed && health.playerHealth > 0)
			{
				player.runRT(4);
			}
		
			//AI_Control
			venustrap_hitbox.track(venustrap);
			//venustrap.chase(player_hitbox, 2);
			
			scorpion_hitbox.track(scorpion);
			//scorpion.evade(player_hitbox, 1);
			
			if(scorpion.x == 0 || scorpion.x == 1800) scorpion.x = 800;
			
			//PLAYER
			player_hitbox.player_track(player);
			
			repaint();
			
			try
			{			
				Thread.sleep(16);
			}
			catch(Exception x) {};
		}
	}
	
	
	
	//Updates the image on the screen
	public void update(Graphics pen)
	{
		offScreenPen.clearRect(0, 0, 1800, 1000);
		
		paint(offScreenPen);
		
		pen.drawImage(offScreenImg, 0, 0, null);
	}

	public void paint(Graphics pen)
	{
	    // Sets background image
	    
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
		   
	   
	    //PLAYER
	    player.draw(pen);
	    health.draw(pen, player_hitbox, venustrap_hitbox);
	    
	    //	AI
	    //venustrap.ai_draw(pen, venustrap_hitbox, player_hitbox);
	    //scorpion.ai_draw(pen, scorpion_hitbox, player_hitbox);
	    
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
		    if (venustrap.overlaps(player)) 
		    {
		    	 pen.setColor(Color.RED);
			     player_hitbox.draw(pen);
		    }
		    
		    if (scorpion.overlaps(player)) 
		    {
		    	 pen.setColor(Color.RED);
			     player_hitbox.draw(pen);
		    }
	    }
	    
	    // Reset the color to default
	    pen.setColor(Color.BLACK);
	}

	
	
	public void init()
	{
		offScreenImg = createImage(1800, 1000);
		offScreenPen = offScreenImg.getGraphics();
		
		addKeyListener(this);
		requestFocus();

		Thread t = new Thread(this);

		t.start();
	}
	
	public void keyPressed(KeyEvent e)
	{		
		int code = e.getKeyCode();
		
		if (code == e.VK_W)   UP_Pressed = true;   
		if (code == e.VK_A)   LT_Pressed = true;  
		if (code == e.VK_D)   RT_Pressed = true; 
		if (code == e.VK_SHIFT)  shift_Pressed = true;  
		
		
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
		if (code == e.VK_SHIFT)  shift_Pressed = false;  
	}

	public void keyTyped(KeyEvent e) {}
	
}