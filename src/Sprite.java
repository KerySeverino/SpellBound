import java.awt.Graphics;

public class Sprite extends Rect {

		Animation[] animation;
		
		int player_action = 0;
		boolean player_moving = false;
		boolean player_lookingLeft = true;
		
		
		int ai_action = 0;
		boolean chasing = false;
		public Sprite(String name, String[] pose, int x, int y, int w, int h, int[] count, int[] duration) 
		{
			super(x, y, w, h);
			
			animation = new Animation[pose.length];
			
			for(int i = 0; i < animation.length; i++)
			{
				animation[i] = new Animation(name + "_" + pose[i], count[i], duration[i]);
				//Debugging check
				//System.out.println(name + "_" + pose[i]);
			}
		}
		
		
		public void walkLT(int dx)
		{
			old_x = x;
			player_action = 2;
			
			player_moving = true;
			player_lookingLeft = true;
			
			x -= dx;	
		}
		

		public void walkRT(int dx)
		{
			old_x = x;
			
			player_action = 3;
			player_moving = true;
			player_lookingLeft = false;
			
			x += dx;
			
			//Debugging check
			//System.out.println("Right");
		}
		
		public void runLT(int dx)
		{
			old_x = x;
			player_action = 4;
			
			player_moving = true;
			player_lookingLeft = true;
			
			x -= dx;	
		}
		

		public void runRT(int dx)
		{
			old_x = x;
			
			player_action = 5;
			player_moving = true;
			player_lookingLeft = false;
			
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
			if(!player_moving && player_lookingLeft) {
				pen.drawImage(animation[0].nextImage(), x - Camera.x, y - Camera.y, w, h, null);
			}else if(!player_moving && !player_lookingLeft){
				pen.drawImage(animation[1].nextImage(), x - Camera.x, y - Camera.y, w, h, null);
			}else{
				pen.drawImage(animation[player_action].nextImage(), x - Camera.x, y - Camera.y, w, h, null);
				player_moving = false;
			}
			
		}
		

		public void chase(Sprite r, int dx) {
			if(isLeftOf(r)) {
				chasing = true;
				ai_action = 1;
				moveRT(dx); 
				
			}
			if(isRightOf(r)) {
				chasing = true;
				ai_action = 0;
				moveLT(dx); 
			}
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
