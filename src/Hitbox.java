
public class Hitbox extends Rect{
	public Hitbox(int x, int y, int w, int h) {
		super(x, y, w, h);
	}
	
	public void track(Rect r) 
	
	{
		x = r.x + 65;
		y = r.y + 100;
	}
	
}
