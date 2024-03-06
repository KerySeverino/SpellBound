import java.applet.Applet;
import java.awt.*;
import java.awt.event.*;

public class Spellbound extends Applet implements Runnable, KeyListener
{

	Rect r1 = new Rect(710, 200, 300, 500);
	Rect r2 = new Rect(200, 200, 500, 300);
	
	
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
			if(UP_Pressed) r1.moveBy( 0, -1);
			if(DN_Pressed) r1.moveBy( 0, +1);
			if(LT_Pressed) r1.moveBy(-1,  0);
			if(RT_Pressed) r1.moveBy(+1,  0);
			
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
		
		if (code == e.VK_A )  LT_Pressed = true;  
		if (code == e.VK_D)   RT_Pressed = true;  
	}
	
	public void keyReleased(KeyEvent e)
	{
		int code = e.getKeyCode();

		if (code == e.VK_A )   LT_Pressed = false;  
		if (code == e.VK_D)    RT_Pressed = false;  
	}
	
	
	public void keyTyped(KeyEvent e) {}

	public void paint(Graphics pen)
	{

		pen.setColor(Color.BLACK);
		
		if(r1.overlaps(r2))  pen.setColor(Color.RED);
		
		r1.draw(pen);
		r2.draw(pen);
	}
	
}