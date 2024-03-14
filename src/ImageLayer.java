import java.awt.Graphics;
import java.awt.Image;
import java.awt.Toolkit;

public class ImageLayer 
{

	public Image image;
	
	int x;
	int y;
	int z;
	
	public ImageLayer(String filename, int x, int y, int z) 
	{
		image = Toolkit.getDefaultToolkit().getImage(filename);
		this.x = x;
		this.y = y;
		this.z = z;
	}
	
	public void moveLT(int dx)
	{
		x += dx / z;
	}
	
	public void moveRT(int dy) {
		x -= dy / z;
	}
	
	public void draw(Graphics pen)
	{
		for(int i = 0; i < 13; i++) {
			pen.drawImage(image, x + i * 1800, y, 1800, 1000, null);	
		}
	}
}
