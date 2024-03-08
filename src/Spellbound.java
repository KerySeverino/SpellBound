import java.applet.Applet;
import java.awt.*;
import java.awt.event.*;

public class Spellbound extends Applet implements Runnable, KeyListener
{
	
	//Background
	Image forest = Toolkit.getDefaultToolkit().getImage("forest_completed.png");
	
	//PLAYER
	String[] pose = {"Lwalk", "Rwalk"};
	
	Rect player_hitbox = new Rect(100, 774, 30, 70);
	//Animation animation = new Animation("wm_Rwalk", 7, 10);
	Sprite player = new Sprite("wm", pose, 50, 697, 7, 10);
	
	//AI Enemy
	AI_control venustrap_hitbox = new AI_control(500, 804, 40, 40);
	
	//Boundaries
	Rect top_wall = new Rect(0, -50, 1500, 50);
	Rect left_wall = new Rect(-50, 0, 50, 800);
	Rect floor = new Rect(0, 845, 1500, 85);

	//Controls
	boolean UP_Pressed = false;
	boolean DN_Pressed = false;
	boolean LT_Pressed = false;
	boolean RT_Pressed = false;
	
	//Paints Image to offScreenImg to reduce flicker
	Image offScreenImg;
	Graphics offScreenPen;
	
	
	public void run()
	{
		// Game Loop
		while(true)
		{
			//if(UP_Pressed) player_hitbox.moveUP(10);
			//if(DN_Pressed) player_hitbox.moveDN(10);
			if(LT_Pressed)
			{
				player_hitbox.moveLT(3);
				player.moveLT(3);
			}
			if(RT_Pressed)
			{
				player_hitbox.moveRT(3);
				player.moveRT(3);
			}
			
			//Prevents the player from going down the screen
			if( player_hitbox.overlaps(top_wall))
			{
				if(player_hitbox.cameFromAbove(top_wall))
				{
					 player_hitbox.pushbackUpFrom(top_wall);
					 player.pushbackUpFrom(top_wall);
				}
				
				if( player_hitbox.cameFromBelow(top_wall))
				{
					 player_hitbox.pushbackDownFrom(top_wall);
					 player.pushbackDownFrom(top_wall);
				}

				if( player_hitbox.cameFromLeftOf(top_wall))
				{
					 player_hitbox.pushbackLeftFrom(top_wall);
					 player.pushbackLeftFrom(top_wall);
				}
				
				if( player_hitbox.cameFromRightOf(top_wall))
				{
					 player_hitbox.pushbackRightFrom(top_wall);
					 player.pushbackRightFrom(top_wall);
				}
			}
			
			
			//Prevents the player from going down the screen
			if( player_hitbox.overlaps(left_wall))
			{
				if( player_hitbox.cameFromAbove(left_wall))
				{
					 player_hitbox.pushbackUpFrom(left_wall);
					 player.pushbackUpFrom(left_wall);
				}
				
				if( player_hitbox.cameFromBelow(left_wall))
				{
					 player_hitbox.pushbackDownFrom(left_wall);
					 player.pushbackDownFrom(left_wall);
				}

				if( player_hitbox.cameFromLeftOf(left_wall))
				{
					 player_hitbox.pushbackLeftFrom(left_wall);
					 player.pushbackLeftFrom(left_wall);
				}
				
				if( player_hitbox.cameFromRightOf(left_wall))
				{
					 player_hitbox.pushbackRightFrom(left_wall);
					 player.pushbackRightFrom(left_wall);
				}
			}
			
			
			//Prevents the player from going down the screen
			if( player_hitbox.overlaps(floor))
			{
				if( player_hitbox.cameFromAbove(floor))
				{
					 player_hitbox.pushbackUpFrom(floor);
				}
				
				if( player_hitbox.cameFromBelow(floor))
				{
					 player_hitbox.pushbackDownFrom(floor);
				}

				if( player_hitbox.cameFromLeftOf(floor))
				{
					 player_hitbox.pushbackLeftFrom(floor);
				}
				
				if( player_hitbox.cameFromRightOf(floor))
				{
					 player_hitbox.pushbackRightFrom(floor);
				}
			}
				
			//AI
			//venustrap_hitbox.chase(player_hitbox, 3);
			
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
	    pen.drawImage(forest, 0, -280, 1500, 1200, null);

	    // Testing player animation
	    //pen.drawImage(animation.nextImage(), 55, 717, null);
	    player.draw(pen);

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
	    if (player_hitbox.overlaps(venustrap_hitbox)) 
	    {
	        pen.setColor(Color.RED);
	        player_hitbox.draw(pen);
	        
	        // Takes damage
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
		
		//if (code == e.VK_W)   UP_Pressed = true;  
		//if (code == e.VK_S)   DN_Pressed = true;  
		if (code == e.VK_A)   LT_Pressed = true;  
		if (code == e.VK_D)   RT_Pressed = true;  
	}
	
	public void keyReleased(KeyEvent e)
	{
		int code = e.getKeyCode();

		//if (code == e.VK_W)   UP_Pressed = false;  
		//if (code == e.VK_S)   DN_Pressed = false; 
		if (code == e.VK_A)   LT_Pressed = false;  
		if (code == e.VK_D)   RT_Pressed = false;  
	}

	public void keyTyped(KeyEvent e) {}
	
}