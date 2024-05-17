import java.awt.Graphics;

public class Sprite extends Rect {

		Animation[] animation;
		
		int player_action = 0;
		boolean player_moving = false;
		boolean player_lookingLeft = true;
		boolean attack_Pressed;
		
		
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
				System.out.println(name + "_" + pose[i]);
			}
		}
		
		
		public void runLT(int dx)
		{
			super.moveLT(dx);
			
			player_action = 2;
			player_moving = true;
			player_lookingLeft = true;
			
			//x -= dx;	
		}
		
		public void runRT(int dx)
		{
			super.moveRT(dx);
			
			player_action = 3;
			player_moving = true;
			player_lookingLeft = false;
			
			//x += dx;
			
			//Debugging check
			//System.out.println("Right");
		}
		
		public void jump(int dy)
		{
			super.moveUP(dy);
			if(player_lookingLeft) {
				player_action = 4;
			}else {
				player_action = 5;
			}
			player_moving = true;
			
		}
//		
//		public void move() {
//			super.move();
//		}
		
//		
		public boolean getAttack(boolean attack) {
			attack_Pressed = attack;
			return attack_Pressed;
		}
		
		public void pushbackLeftFrom(Rect r)
		{
			super.pushbackLeftFrom(r);
			x = r.x - 15;
		}
		
		public void pushbackRightFrom(Rect r)
		{
			super.pushbackRightFrom(r);
			x = r.x + 15;
		}
		
		public void draw(Graphics pen) 
		{
		
			
			if(!player_moving && player_lookingLeft){
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
