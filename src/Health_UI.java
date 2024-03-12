import java.awt.Graphics;
import java.awt.Image;
import java.awt.Toolkit;

public class Health_UI extends Rect{
	public int playerHealth = 6;
	Image health;
	Image gameOver;
	int decreasedHealthImg = 1;
	int timeElapse = 0;
	
	
	public Health_UI (int x, int y, int w, int h)
	{
		super(x, y, w, h);
		health = Toolkit.getDefaultToolkit().getImage("health_UI_0.png");
		gameOver = Toolkit.getDefaultToolkit().getImage("GameOver.png");
	}
	
	public void draw (Graphics pen, Hitbox player, Hitbox enemy) 
	{
		pen.drawImage(health, x, y, w, h, null);
		if(enemy.overlaps(player) && timeElapse == 100 && decreasedHealthImg <= 6) {
			health = Toolkit.getDefaultToolkit().getImage("health_UI_" + decreasedHealthImg + ".png");
			playerHealth -= 1;
			decreasedHealthImg += 1;
			timeElapse = 0;
		}
		
		if(playerHealth == 0) {
			pen.drawImage(gameOver, 710, 390, 500, 300, null);
		}
		
		if(timeElapse < 100) {
			timeElapse += 1;
			//Debugger: How many frames have past
			//System.out.println(timeElapse);
		}
	}
	
}