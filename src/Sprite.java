import java.awt.Graphics;

public class Sprite extends Rect {

		Animation[] animation;
		
		int action = 0;
		
		
		public Sprite(String name, String[] pose, int x, int y, int count, int duration) 
		{
			super(x, y,148, 148);
			
			animation = new Animation[pose.length];
			
			for(int i = 0; i < animation.length; i++)
			{
				animation[i] = new Animation(name + "_" + pose[i], count, duration);
				//Debugging check
				//System.out.println(name + "_" + pose[i]);
			}
		}
		
		
		public void moveLT(int dx)
		{
			old_x = x;
			
			action = 0;
			
			x -= dx;	
		}
		

		public void moveRT(int dx)
		{
			old_x = x;
			
			action = 1;
			
			x += dx;
			
			//Debuggingcheck
			//System.out.println("Right");
		}
		
	
		public void moveUP(int dy)
		{
			old_y = y;
			
			action = 2;
			
			y -= dy;
		}
		
		public void draw(Graphics pen) 
		{
			pen.drawImage(animation[action].nextImage(), x, y, w, h, null);
		}
		
		
//		public void moveDN(int dy)
//		{
//			old_y = y;
//			
//			y += dy;
//		}
}
