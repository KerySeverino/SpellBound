import java.awt.Graphics;

public class AI_control extends Rect{
	
	Animation [] animation;
	boolean moving = false;
	int direction = 1; // 0 = left, 1 = right
	int distanceFromPlayer;
	
	public AI_control(String name, String[] pose, int x, int y, int w, int h, int[] count, int[] duration) 
	{
		super(x, y, w, h);
		
		
		animation = new Animation[pose.length];
		
		for(int i = 0; i < animation.length; i++)
		{
			animation[i] = new Animation(name + "_" + pose[i], count[i], duration[i]);
			//Debugging:
			//System.out.println(name + "_" + pose[i]);
		}
	}
	
	public void ai_draw(Graphics pen, Rect ai, Rect player) 
	{
		
		// Walk Left
		if (direction == 0 && !ai.overlaps(player)){
			pen.drawImage(animation[2].nextImage(), x, y, w, h, null);	
		// Walk Right
		}else if (direction == 1 && !ai.overlaps(player)){
			pen.drawImage(animation[3].nextImage(), x, y, w, h, null);	
		// Attack Left
		}else if (direction == 0 && ai.overlaps(player)){
			pen.drawImage(animation[4].nextImage(), x, y, w, h, null);	
		// Attack Right
		}else if (direction == 1 && ai.overlaps(player)){
			pen.drawImage(animation[5].nextImage(), x, y, w, h, null);	
		}
		
	}
	
	public void chase(Rect player, Rect ai, int dx)
	{
		moving = true;
		distanceFromPlayer =  Math.abs(player.x - ai.x);
		
		if(distanceFromPlayer <= 800) 
		{
			if(isLeftOf(player)) {
				direction = 1;
				moveRT(dx); 
			}
			
			if(isRightOf(player)) {
				direction = 0;
				moveLT(dx); 
			}

		}	
		//if(isAbove(r))    moveDN(dx); 
		//if(isBelow(r))    moveUP(dx); 
	}
	
	public void evade(Rect r, int dx)
	{
		moving = true;
		if(isLeftOf(r)) {
			direction = 0;
			moveLT(dx); 
		}
		if(isRightOf(r)) {
			direction = 1;
			moveRT(dx); 
		}
		
		//if(isAbove(r))    moveUP(dx); 
		//if(isBelow(r))    moveDN(dx); 
	}
	
	
	public void moveLT(int dx)
	{
		x -= dx;		
	}
	
	public void moveRT(int dx)
	{
		x += dx;		
	}
	
	public boolean isLeftOf(Rect r)
	{
		return x + w < r.x ;
	}
	
	public boolean isRightOf(Rect r)
	{
		return r.x + r.w < x;
	}
	
	public boolean isAbove(Rect r)
	{
		return y + h < r.y;
	}
	
	public boolean isBelow(Rect r)
	{
		return r.y + r.h < y;
	}

}
