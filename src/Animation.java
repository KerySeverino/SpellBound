import java.awt.Image;
import java.awt.Toolkit;

public class Animation {
	private Image[] image;
	private int next;
	private int duration = 10;
	private int delay = duration;
	
	public Animation(String name, int count)
	{
		image = new Image[count];
		
		for(int i = 0; i < count; i++)
		{
			image[i] = Toolkit.getDefaultToolkit().getImage(name + "_" + i + ".png");
		}
	}
	
	public Image nextImage()
	{
		if(delay == 0)
		{
			next++;
			
			if(next == image.length) next = 1;
			delay = duration;
			
		}
		delay--;
		
		return image[next];
	}
}
