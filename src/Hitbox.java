

public class Hitbox extends Rect{
	public Hitbox(int x, int y, int w, int h) {
		super(x - Camera.x, y - Camera.y, w, h);
	}
	
	public void track(Rect r) 
	
	{
		x = r.x - Camera.x;
		y = r.y - Camera.y;
	}
	
	public void player_track(Rect r) 
	
	{
		x = r.x + 102 - Camera.x;
		y = r.y + 132 - Camera.y;
	}

	
}
