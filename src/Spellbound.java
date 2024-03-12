import java.applet.Applet;
import java.awt.*;
import java.awt.event.*;

public class Spellbound extends Applet implements Runnable, KeyListener
{	
	//Background
	Image forest = Toolkit.getDefaultToolkit().getImage("forest_completed.png");
	
	//AI Enemy
	String[] venustrap_pose = {"LTidle", "RTidle", "LTwalk", "RTwalk", "LTattack", "RTattack"};
	int [] venustrap_count = {4, 4, 6, 6, 6, 6};
	int [] venustrap_duration = {10, 10, 10, 10, 6, 6};
	
	Hitbox venustrap_hitbox = new Hitbox(500, 716, 128, 128);
	AI_control venustrap = new AI_control("venustrap", venustrap_pose, 500, 716, 128, 128, venustrap_count, venustrap_duration);
	
	//AI Enemy
	String[] scorpion_pose = {"LTidle", "RTidle", "LTwalk", "RTwalk", "LTattack", "RTattack"};
	int [] scorpion_count = {4, 4, 4, 4, 4, 4, 4};
	int [] scorpion_duration = {10, 10, 10, 10, 6, 6, 15};
		
	Hitbox scorpion_hitbox = new Hitbox(300, 716, 48, 48);
	AI_control scorpion = new AI_control("scorpion", scorpion_pose, 300, 798, 48, 48, scorpion_count, scorpion_duration);
		
	//PLAYER
	String[] player_pose = {"LTidle", "RTidle", "LTwalk", "RTwalk", "LTrun", "RTrun", "RTdeath"};
		
	Hitbox player_hitbox = new Hitbox(50, 744, 50, 100);
	Sprite player = new Sprite("wm", player_pose, 50, 744, 100, 100, 7, 8);
	Health_UI health = new Health_UI(20, 50,256,40);
	
	//Boundaries
	Rect top_wall = new Rect(0, -50, 1500, 50);
	Rect left_wall = new Rect(-50, 0, 50, 800);
	Rect floor = new Rect(0, 845, 1500, 85);

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
				System.out.println(health.playerHealth);
				
			}
			if(RT_Pressed && health.playerHealth > 0)
			{
				player.walkRT(2);
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
			venustrap.chase(player_hitbox, 2);
			
			scorpion_hitbox.track(scorpion);
			scorpion.evade(player_hitbox, 1);
			
			if(scorpion.x == 0 || scorpion.x == 1920) scorpion.x = 800;
			
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
		offScreenPen.clearRect(0, 0, 1920, 1080);
		
		paint(offScreenPen);
		
		pen.drawImage(offScreenImg, 0, 0, null);
	}

	public void paint(Graphics pen)
	{
	    // Sets background image
	    pen.drawImage(forest, 0, -280, 1920, 1200, null);
	   
	    //PLAYER
	    player.draw(pen);
	    health.draw(pen, player_hitbox, venustrap_hitbox);
	    
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
		offScreenImg = createImage(1920, 1080);
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