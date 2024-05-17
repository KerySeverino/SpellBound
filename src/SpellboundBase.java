import java.applet.Applet;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;

public abstract class SpellboundBase extends  Applet implements Runnable, KeyListener, MouseListener, MouseMotionListener{

	
	public int timer = 0;
	int werewolf_Speed;
	
	// GUI
	UI health = new UI(20, 50,256,40);
	UI menu = new UI(0,0, 1800, 720);
	UI death = new UI(0,0, 1800, 720);
	UI win = new UI(0,0, 1800, 720);
	
	
	Rect start_button = new Rect(677, 495, 480, 60);
	//Rect restart_button = new Rect(587, 481, 660, 60);
	Rect quit_button = new Rect(720, 654, 390, 60);
	
	// Boundaries
	Rect top_wall = new Rect(0, -50, 1800, 50);
	Rect left_wall = new Rect(500, 0, 50, 800);
	Rect floor = new Rect(0, 635, 18000, 85);
	
	//  Menu Controls
	boolean gameMenu = true;
	boolean gameWon = false;
	
	// PLayer Controls
	boolean UP_Pressed = false;
	boolean LT_Pressed = false;
	boolean RT_Pressed = false;
	boolean shift_Pressed = false;
	
	// Testing Controls
	boolean testing_Tool = false;
	
	
	//Paints Image to offScreenImg to reduce flicker
		Image offScreenImg;
		Graphics offScreenPen;
		
		
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
					//System.out.println("(" + mX + " , " + mY + ")");
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
