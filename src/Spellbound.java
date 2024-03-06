import java.applet.Applet;
import java.awt.*;
import java.awt.event.*;

public class Spellbound extends Applet implements Runnable, KeyListener
{

	Rect player_hitbox = new Rect(50, 789, 40, 80);
	AI_control wilddog_hitbox = new AI_control(500, 829, 40, 40);
	
	Rect floor = new Rect(0, 870, 1500, 50);

	
	boolean UP_Pressed = false;
	boolean DN_Pressed = false;
	boolean LT_Pressed = false;
	boolean RT_Pressed = false;
	

	public void init()
	{
		addKeyListener(this);
		requestFocus();

		
		Thread t = new Thread(this);

		t.start();
	}
	
	public void run()
	{
		// Game Loop
		while(true)
		{
			if(UP_Pressed) player_hitbox.moveUP(10);
			if(DN_Pressed) player_hitbox.moveDN(10);
			if(LT_Pressed) player_hitbox.moveLT(10);
			if(RT_Pressed) player_hitbox.moveRT(10);
			
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
			wilddog_hitbox.chase(player_hitbox, 3);
			
			repaint();
			
			try
			{			
				Thread.sleep(16);
			}
			catch(Exception x) {};
		}
	}
	
	
	public void keyPressed(KeyEvent e)
	{		
		int code = e.getKeyCode();
		
		if (code == e.VK_W)   UP_Pressed = true;  
		if (code == e.VK_S)   DN_Pressed = true;  
		if (code == e.VK_A)   LT_Pressed = true;  
		if (code == e.VK_D)   RT_Pressed = true;  
	}
	
	public void keyReleased(KeyEvent e)
	{
		int code = e.getKeyCode();

		if (code == e.VK_W)   UP_Pressed = false;  
		if (code == e.VK_S)   DN_Pressed = false; 
		if (code == e.VK_A)   LT_Pressed = false;  
		if (code == e.VK_D)   RT_Pressed = false;  
	}
	
	
	public void keyTyped(KeyEvent e) {}

	public void paint(Graphics pen)
	{

		 // Sets the color to green for the player_hitbox
	    pen.setColor(Color.GREEN);
	    player_hitbox.draw(pen);
	    
	    // Sets the colors for other elements to Default
	    pen.setColor(Color.BLACK);
	    floor.draw(pen);
	    
	    // Sets the colors for AI_enemies_hitbox
	    pen.setColor(Color.RED);
	    wilddog_hitbox.draw(pen);

	    // Sets the color to red if the player_hitbox overlaps with the floor
	    if (player_hitbox.overlaps(wilddog_hitbox)) {
	        pen.setColor(Color.RED);
	        player_hitbox.draw(pen);
	    }

	   
	    
	}
	
}