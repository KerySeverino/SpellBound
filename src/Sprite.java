import java.awt.Graphics;

public class Sprite extends Rect {

		Animation[] animation;
		
		int action = 0;
		boolean moving = false;
		boolean lookingLeft = true;
		
		
		
		public Sprite(String name, String[] pose, int x, int y, int count, int duration) 
		{
			super(x, y, 190, 190);
			
			animation = new Animation[pose.length];
			
			for(int i = 0; i < animation.length; i++)
			{
				animation[i] = new Animation(name + "_" + pose[i], count, duration);
				//Debugging check
				System.out.println(name + "_" + pose[i]);
			}
		}
		
		
		public void walkLT(int dx)
		{
			old_x = x;
			action = 2;
			
			moving = true;
			lookingLeft = true;
			
			x -= dx;	
			
		}
		

		public void walkRT(int dx)
		{
			old_x = x;
			
			action = 3;
			moving = true;
			lookingLeft = false;
			
			x += dx;
			
			//Debugging check
			//System.out.println("Right");
		}
		
		
		public void runLT(int dx)
		{
			old_x = x;
			action = 4;
			
			moving = true;
			lookingLeft = true;
			
			x -= dx;	
			
		}
		

		public void runRT(int dx)
		{
			old_x = x;
			
			action = 5;
			moving = true;
			lookingLeft = false;
			
			x += dx;
			
			//Debugging check
			//dSystem.out.println("Run");
		}
		
	
//		public void moveUP(int dy)
//		{
//			old_y = y;
//			
//			action = 4;
//			moving = true;
//			
//			y -= dy;
//		}
//		
		//Push the character out of terrain
		public void pushedOutOf(Rect r)
		{
			if(cameFromLeftOf(r))   pushbackLeftFrom(r);		
			if(cameFromRightOf(r))	pushbackRightFrom(r);
		}
		
		public boolean cameFromLeftOf(Rect r)
		{
			return old_x + w < r.x;
		}
		
		public boolean cameFromRightOf(Rect r)
		{
			return r.x + r.w < old_x;
		}
		
		public void pushbackLeftFrom(Rect r)
		{
			x = r.x - 13;
		}
		
		public void pushbackRightFrom(Rect r)
		{
			x = r.x - 13;
		}
		
		public void draw(Graphics pen) 
		{
			if(!moving && lookingLeft) {
				pen.drawImage(animation[0].nextImage(), x, y, w, h, null);
			}else if(!moving && !lookingLeft){
				pen.drawImage(animation[1].nextImage(), x, y, w, h, null);
			}else {
				pen.drawImage(animation[action].nextImage(), x, y, w, h, null);
				moving = false;
			}
			
		}


		public void chase(Sprite r, int dx) {
			if(isLeftOf(r))   moveRT(dx); 
			if(isRightOf(r))  moveLT(dx); 
		}
		
		public boolean isLeftOf(Rect r)
		{
			return x + w < r.x;
		}
		
		public boolean isRightOf(Rect r)
		{
			return r.x + r.w < x;
		}
		
}
