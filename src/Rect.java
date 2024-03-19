import java.awt.Graphics;

public class Rect
{
	int x;
	int y;
	
	int w;
	int h;
	
	int old_x;
	int old_y;
	
	public Rect(int x, int y, int w, int h)
	{
		this.x = x - Camera.x;
		this.y = y - Camera.y;
		
		this.w = w;
		this.h = h;
		
		old_x = x - Camera.x;
		old_y = y - Camera.y;
	}
	
	public void moveLT(int dx)
	{
		old_x = x;
		
		x -= dx;		
	}
	
	public void moveRT(int dx)
	{
		old_x = x;
		
		x += dx;		
	}
	
//	public void moveUP(int dy)
//	{
//		old_y = y;
//		
//		y -= dy;
//	}
	
	public void moveDN(int dy)
	{
		old_y = y;
		
		y += dy;
	}
	
	public void resizeBy(int dw, int dh)
	{
		w += dw;
		
		h += dh;
	}
	
	public boolean overlaps(Rect r)
	{
		return (x + w >= r.x  	  ) &&				
			   (x     <= r.x + r.w) &&
			   (y + h >= r.y      ) &&			   
			   (y     <= r.y + r.h);
	}
	
	// Push the character out of terrain
	public void pushedOutOf(Rect r)
	{
		if(cameFromAbove(r)) 	pushbackUpFrom(r);
		if(cameFromBelow(r))    pushbackDownFrom(r);
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
	
	public boolean cameFromAbove(Rect r)
	{
		return old_y + h < r.y;
	}
	
	public boolean cameFromBelow(Rect r)
	{
		return r.y + r.h < old_y;
	}
	
	public void pushbackLeftFrom(Rect r)
	{
		x = r.x - w - 1;
	}
	
	public void pushbackRightFrom(Rect r)
	{
		x = r.x + r.w + 1;
	}
	
	public void pushbackUpFrom(Rect r)
	{
		y = r.y - h - 1;
	}
	
	public void pushbackDownFrom(Rect r)
	{
		y = r.y + r.h + 1;
	}
	
	
	public void draw(Graphics pen)
	{
		pen.drawRect(x, y, w, h);
	}

}