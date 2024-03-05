import java.awt.Graphics;

public class Rect
{
	int x;
	int y;
	
	int w;
	int h;
	
	public Rect(int x, int y, int w, int h)
	{
		this.x = x;
		this.y = y;
		
		this.w = w;
		this.h = h;
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
	
	public boolean overlaps(Rect r)
	{
		return (x + w >= r.x      ) &&				
			   (x     <= r.x + r.w) &&
			   (y + h >= r.y      ) &&			   
			   (y     <= r.y + r.h);
	}
	
	
	public void draw(Graphics pen)
	{
		pen.drawRect(x, y, w, h);
	}

}