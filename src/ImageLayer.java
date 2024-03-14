import java.awt.Graphics;
import java.awt.Image;
import java.awt.Toolkit;

public class ImageLayer 
{

	public Image image;
	
	public ImageLayer(String filename) 
	{
		image = Toolkit.getDefaultToolkit().getImage(filename);
	}
	
	public void draw(Graphics pen)
	{
		pen.drawImage(image, 0, 0, null);
	}
}
