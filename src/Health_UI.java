import java.awt.Graphics;
import java.awt.Image;
import java.awt.Toolkit;

public class Health_UI{
	Image health;
	int decreasedHealth = 1;
	int timeElapse = 0;
	
	
	public Health_UI (Hitbox player)
	{
		health = Toolkit.getDefaultToolkit().getImage("health_UI_0.png");
	}
	
	public void draw (Graphics pen, Hitbox player, Hitbox enemy) 
	{
		pen.drawImage(health, 20, 50,256,40, null);
		if(enemy.overlaps(player) && timeElapse == 100 && decreasedHealth <= 6) {
			health = Toolkit.getDefaultToolkit().getImage("health_UI_" + decreasedHealth + ".png");
			decreasedHealth += 1;
			timeElapse = 0;
			// Debugger
			//System.out.println("Hit");
		}
		
		if(timeElapse < 100) {
			timeElapse += 1;
			//Debugger: How many frames have past
			System.out.println(timeElapse);
		}
	}
	
}