
public class Camera {

	static int x;
	static int y;
	
	public static void moveLT(int dx)
	{
		x -= dx;
	}
	
	public static void moveRT(int dx)
	{
		x += dx;
	}
	
	public static void moveUp(int dy)
	{
		y -= dy;
	}
	
	public static void moveDown(int dy)
	{
		y += dy;
	}

}
