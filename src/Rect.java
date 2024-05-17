import java.awt.Graphics;

public class Rect
{
	int x;
	int y;
	
	int w;
	int h;
	
	double vx = 0;
	double vy = 0;
	
	double ay = G;
	
	static double G = .7;
	static double F = .6;
	
	boolean held = false;
	
	public void physicsOff()
	{
		vx = 0;
		vy = 0;
		
		ay = 0;
	}
	
	public Rect(int x, int y, int w, int h)
	{
		this.x = x;
		this.y = y;
		
		this.w = w;
		this.h = h;

	}
	

	public void set(int x, int y, int w, int h) {
		this.x = x;
		this.y = y;
		this.w = w;
		this.h = h;
	}
	
	public void setVelocity(double vx, double vy)
	{
		this.vx = vx;
		this.vy = vy;
	}
	
	public void grabbed()
	{
		held = true;
	}
	
	public void dropped()
	{
		held = false;
	}
	
	public void moveLT(int vx)
	{
		this.vx = -vx;		
	}
	
	public void moveRT(int vx)
	{
		
		this.vx = +vx;		
	}
	
	public void moveUP(int vy)
	{
		this.vy = -vy;
	}
	
	public void moveDN(int vy)
	{
		this.vy = +vy;
	}
	
	public void jump(int h)
	{
		vy = -h;		
	}
	
	public void move()
	{
		x += vx;
		y += vy + G/2;
		
		vy += G;
	}
	
	public void moveBy(int dx, int dy)
	{
		x += dx;
		y += dy;
	}
	
	
	public void resizeBy(int dw, int dh)
	{
		w += dw;
		
		h += dh;
	}
	
	public void chase(Rect r, int dx)
	{
		if(isLeftOf(r))   moveRT(dx); 
		if(isRightOf(r))  moveLT(dx); 
		if(isAbove(r))    moveDN(dx); 
		if(isBelow(r))    moveUP(dx);
		
		move();
	}
	
	public void evade(Rect r, int dx)
	{
		if(isLeftOf(r))   moveLT(dx); 
		if(isRightOf(r))  moveRT(dx); 
		if(isAbove(r))    moveUP(dx); 
		if(isBelow(r))    moveDN(dx); 
		
		move();
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
	
	
	
	public boolean contains(int mx, int my)
	{
		return (mx >= x    )  &&
			   (mx <= x + w)  &&
			   (my >= y    )  &&
			   (my <= y + h);
	}
	
	
	public boolean overlaps(Rect r)
	{
		return (x + w >= r.x      ) &&				
			   (x     <= r.x + r.w) &&
			   (y + h >= r.y      ) &&			   
			   (y     <= r.y + r.h);
	}
	
	public void pushedOutOf(Rect r)
	{
		if(cameFromAbove(r)) 	pushbackUpFrom(r);
		if(cameFromBelow(r))    pushbackDownFrom(r);
		if(cameFromLeftOf(r))   pushbackLeftFrom(r);		
		if(cameFromRightOf(r))	pushbackRightFrom(r);
		
		vx *= F;
		
		if(Math.abs(vx) <= 1)  vx = 0;
	}
	
	
	public void bounceOff(Rect r)
	{
		if(cameFromAbove(r)  || cameFromBelow(r))    vy = -vy;
		if(cameFromLeftOf(r) || cameFromRightOf(r))  vx = -vx;
	}
	
	public boolean cameFromLeftOf(Rect r)
	{
		return x - vx + w < r.x;
	}
	
	public boolean cameFromRightOf(Rect r)
	{
		return r.x + r.w < x - vx;
	}
	
	public boolean cameFromAbove(Rect r)
	{
		return y - vy + h < r.y;
	}
	
	public boolean cameFromBelow(Rect r)
	{
		return r.y + r.h < y - vy;
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
		
		vy = 0;
	}
	
	public void pushbackDownFrom(Rect r)
	{
		y = r.y + r.h + 1;
	}
	
	
	public void draw(Graphics pen)
	{
		pen.drawRect(x - Camera.x, y - Camera.y, w, h);
	}
	
	
	public String toString()
	{
		return "new Rect(" + x + ", " + y + ", " + w + ", " + h + "),";
	}

}