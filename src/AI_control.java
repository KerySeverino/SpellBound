
public class AI_control extends Rect{
	public AI_control(int x, int y, int w, int h) {
		super(x, y, w, h);
	}
	
	public void chase(Rect r, int dx)
	{
		if(isLeftOf(r))   moveRT(dx); 
		if(isRightOf(r))  moveLT(dx); 
		//if(isAbove(r))    moveDN(dx); 
		//if(isBelow(r))    moveUP(dx); 
	}
	
	public void evade(Rect r, int dx)
	{
		if(isLeftOf(r))   moveLT(dx); 
		if(isRightOf(r))  moveRT(dx); 
		//if(isAbove(r))    moveUP(dx); 
		//if(isBelow(r))    moveDN(dx); 
	}
	
	public boolean isLeftOf(Rect r)
	{
		return x + w < r.x;
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
