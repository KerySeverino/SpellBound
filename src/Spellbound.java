import java.applet.Applet;
import java.awt.*;
import java.awt.event.*;

public class Spellbound extends Applet implements Runnable, KeyListener
{
	
	//Background
	Image forest = Toolkit.getDefaultToolkit().getImage("forest_completed.png");
	
	//PLAYER
	String[] player_pose = {"LTidle", "RTidle", "LTwalk", "RTwalk", "LTrun", "RTrun"};
	
	Hitbox player_hitbox = new Hitbox(50, 744, 100, 100);
	Sprite player = new Sprite("wm", player_pose, 50, 744, 100, 100, 7, 8);
	
	//AI Enemy
	String[] venustrap_pose = {"LTidle", "RTidle", "LTwalk", "RTwalk", "LTattack", "RTattack"};
	int [] venustrap_count = {4, 4, 6, 6, 6, 6};
	int [] venustrap_duration = {10, 10, 10, 10, 6, 6};
	
	Hitbox venustrap_hitbox = new Hitbox(500, 716, 128, 128);
	AI_control venustrap = new AI_control("venustrap", venustrap_pose, 500, 716, 128, 128, venustrap_count, venustrap_duration);
	
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
			if(LT_Pressed)
			{
				player.walkLT(2);
			}
			if(RT_Pressed)
			{
				player.walkRT(2);
			}
			
			//Running
			if(LT_Pressed && shift_Pressed)
			{
				player.runLT(4);
			}
			if(RT_Pressed && shift_Pressed)
			{
				player.runRT(4);
			}
//			
//			
//			//Prevents the player from going left to the screen
//			if( player.overlaps(left_wall))
//			{
//				if( player.cameFromLeftOf(left_wall))
//				{
//					 player.pushbackLeftFrom(left_wall);
//				}
//				
//				if( player.cameFromRightOf(left_wall))
//				{
//					 player.pushbackRightFrom(left_wall);
//				}
//			}
//			
//			//Prevents the player from going down the screen
//			if( player.overlaps(floor))
//			{
//				if( player.cameFromAbove(floor))
//				{
//					 player.pushbackUpFrom(floor);
//				}
//				
//				if( player.cameFromBelow(floor))
//				{
//					 player.pushbackDownFrom(floor);
//				}
//
//				if( player.cameFromLeftOf(floor))
//				{
//					 player.pushbackLeftFrom(floor);
//				}
//				
//				if( player.cameFromRightOf(floor))
//				{
//					 player.pushbackRightFrom(floor);
//				}
//			}
				
			//AI
			venustrap_hitbox.track(venustrap);
			venustrap.chase(player, 3);
			player_hitbox.track(player);
			
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
	   
	    player.draw(pen);
	    venustrap.ai_draw(pen, venustrap_hitbox, player_hitbox);
	    
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
	
		    // Sets the color to red if the player_hitbox overlaps with the AI_enemies_hitbox
		    if (venustrap.overlaps(player)) 
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