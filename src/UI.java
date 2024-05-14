import java.awt.Graphics;
import java.awt.Image;
import java.awt.Toolkit;

public class UI extends Rect{
	public int playerHealth = 6;
	Image health;
	Image gameOver;
	Image menu_screen;
	int decreasedHealthImg = 1;
	int timeElapse = 0;
	
	
	
	public UI (int x, int y, int w, int h)
	{
		super(x, y, w, h);
		health = Toolkit.getDefaultToolkit().getImage("health_UI_0.png");
		gameOver = Toolkit.getDefaultToolkit().getImage("GameOver.png");
		menu_screen = Toolkit.getDefaultToolkit().getImage("Menu_Screen.png");
	}
	
	
	public void health_UI_draw (Graphics pen, Hitbox player, Hitbox enemy) 
	{
		pen.drawImage(health, x, y, w, h, null);
		if(enemy.overlaps(player) && timeElapse == 45 && decreasedHealthImg <= 6) {
			health = Toolkit.getDefaultToolkit().getImage("health_UI_" + decreasedHealthImg + ".png");
			playerHealth -= 1;
			decreasedHealthImg += 1;
			timeElapse = 0;
		}
		
		if(playerHealth == 0) {
			pen.drawImage(gameOver, 500, 200, 500, 300, null);
		}
		
		if(timeElapse < 45) {
			timeElapse += 1;
			//Debugger: How many frames have past
			//System.out.println(timeElapse);
		}
	}
	
	public void draw_menu(Graphics pen) {
		pen.drawImage(menu_screen, x, y, w, h, null);
	}
	
}