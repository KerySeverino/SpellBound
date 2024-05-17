import java.awt.*;
import java.awt.event.*;

public class Spellbound extends SpellboundBase
{	
	// Background
	// Image forest = Toolkit.getDefaultToolkit().getImage("forest_completed.png");
	ImageLayer forest_11 = new ImageLayer("forest_11.png", 0, -270, 10);
	ImageLayer forest_10 = new ImageLayer("forest_10.png", 0, -270, 10);
	ImageLayer forest_9 = new ImageLayer("forest_9.png", 0, -270, 8);
	ImageLayer forest_8 = new ImageLayer("forest_8.png", 0, -270, 8);
	ImageLayer forest_7 = new ImageLayer("forest_7.png", 0, -270, 6);
	ImageLayer forest_6 = new ImageLayer("forest_6.png", 0, -270, 6);
	ImageLayer forest_5 = new ImageLayer("forest_5.png", 0, -270, 4);
	ImageLayer forest_4 = new ImageLayer("forest_4.png", 0, -270, 4);
	ImageLayer forest_3 = new ImageLayer("forest_3.png", 0, -270, 2);
	ImageLayer forest_2 = new ImageLayer("forest_2.png", 0, -270, 2);
	ImageLayer forest_1 = new ImageLayer("forest_1.png", 0, -270, 1);
	ImageLayer forest_0 = new ImageLayer("forest_0.png", 0, -270, 1);

//	
//	// AI Enemy
//	String[] venustrap_pose = {"LTidle", "RTidle", "LTwalk", "RTwalk", "LTattack", "RTattack"};
//	int [] venustrap_count = {4, 4, 6, 6, 6, 6};
//	int [] venustrap_duration = {10, 10, 8, 8, 6, 6};
//	
//	Hitbox venustrap_hitbox = new Hitbox(500, 520, 128, 128);
//	AI_control venustrap = new AI_control("venustrap", venustrap_pose, 900, 520, 128, 128, venustrap_count, venustrap_duration);
//	
//	
//	// AI Enemy
//	String[] scorpion_pose = {"LTidle", "RTidle", "LTwalk", "RTwalk", "LTattack", "RTattack"};
//	int [] scorpion_count = {4, 4, 4, 4, 4, 4, 4};
//	int [] scorpion_duration = {10, 10, 10, 10, 6, 6, 15};
//		
//	Hitbox scorpion_hitbox = new Hitbox(300, 716, 48, 48);
//	AI_control scorpion = new AI_control("scorpion", scorpion_pose, 700, 619, 48, 48, scorpion_count, scorpion_duration);
//		
	// AI Enemy
	String[] werewolf_pose = {"idleLT", "idleRT", "walkLT", "walkRT", "runLT", "runRT", "attackLT", "attackRT"};
	int [] werewolf_count = {8, 8, 11, 11,9, 9, 6, 6};
	int [] werewolf_duration = {10, 10, 10, 10, 5, 5, 6, 6};
		
	Hitbox werewolf_hitbox = new Hitbox(300, 716, 210, 100);
	AI_control werewolf = new AI_control("wolf", werewolf_pose, 700, 450, 200, 200, werewolf_count, werewolf_duration);
		

	
	// PLAYER
	String[] redhood_pose = {"idleLT", "idleRT", "runLT", "runRT", "jumpLT", "jumpRT"};
	int [] redhood_frames_count = {18, 18, 24, 24, 19, 19};
	int [] redhood_frames_duration = {5,5, 2, 2, 3, 3};
	Hitbox redhood_hitbox = new Hitbox(50, 590, 100, 89);
	
	//Hitbox player_attack_hitbox = new Hitbox(50, 590, 170, 120);
	Sprite player = new Sprite("redhood", redhood_pose, 400, 460, 195, 256, redhood_frames_count, redhood_frames_duration);
	
	
	public void run()
	{
		
		// Game Loop
		while(true)
		{
		
			if (gameMenu == false && gameWon == false) 
			{
				if(health.playerHealth > 0) {
					timer += 1;
					if(timer >= 1000) {
						gameWon = true;
					}
				}
				
				// Walking
				if(LT_Pressed && !RT_Pressed && health.playerHealth > 0) {
					if(!shift_Pressed) {
						player.runLT(2);
						Camera.moveLT(2);
					}else {
						player.runLT(4);
						Camera.moveLT(4);
					}
				
				}
			
				if(RT_Pressed && !LT_Pressed && health.playerHealth > 0) {
					
					if(!shift_Pressed) {
						player.runRT(2);
						Camera.moveRT(2);
					}else {
						player.runRT(4);
						Camera.moveRT(4);
					}
					
				}
				
				if(UP_Pressed && health.playerHealth > 0) {
					if (player.overlaps(floor)){
						player.jump(15);
					}
				}
					
				
				player.move();
				
				if(redhood_hitbox.overlaps(floor))
				{
					//soldier.vx = 0;
					player.vy = 0;

					player.pushedOutOf(floor);
					
				}
				

//				if(redhood_hitbox.overlaps(left_wall))
//				{
//					//soldier.vx = 0;
//					player.vx = 0;
//
//					player.pushedOutOf(left_wall);
//					
//				}

				// AI_Control 
				//venustrap_hitbox.track(venustrap);
				//venustrap.chase(redhood_hitbox, venustrap_hitbox, 4);
				
				//scorpion_hitbox.track(scorpion);
				//scorpion.chase(redhood_hitbox, scorpion_hitbox,  5);
				
				
				werewolf_hitbox.wolf_track(werewolf);
				
				if(werewolf.distanceFromPlayer <= 400) {
					werewolf_Speed = 3;
				}else if(werewolf.distanceFromPlayer > 400){
					werewolf_Speed = 4;
				}
				
				werewolf.chase(redhood_hitbox, werewolf_hitbox,  werewolf_Speed);
				
				
				// PLAYER
				redhood_hitbox.player_track(player);
				
				System.out.println(timer);
				System.out.println(gameWon);
				
			}
				
			repaint();
			
			try
			{			
				Thread.sleep(16);
			}
			catch(Exception x) {};
		}
	}
	
    @Override
    public void stop() {
        // Suspend execution
        System.out.println("Applet game stopped");
        System.exit(0);
    }

	public void paint(Graphics pen)
	{
		pen.setColor(Color.WHITE);
		
		
		
		
		 // Menu screen
		if(health.playerHealth > 0 && gameMenu == true && gameWon == false) 
		{
		    menu.draw_menu(pen);
		    start_button.draw(pen);
		    quit_button.draw(pen);
		   
		} else if(health.playerHealth <= 0 && gameWon == false) {
			 death.draw_gameover(pen);
			 quit_button.set(727, 639, 390, 60);
			 quit_button.draw(pen);
			// restart_button.draw(pen);
		}else if(gameWon) {
			win.draw_win(pen);
		}
		
		
			
		// Game start
		if (health.playerHealth > 0 && gameMenu == false && gameWon == false) 
		{
		    // Sets background ImageLayers
		    forest_11.draw(pen);  
		    forest_10.draw(pen);
		    forest_9.draw(pen); 
		    forest_8.draw(pen); 
		    forest_7.draw(pen); 
		    forest_6.draw(pen); 
		    forest_5.draw(pen);		
		    forest_4.draw(pen);
		    forest_3.draw(pen);	
		    forest_2.draw(pen);	
		    forest_1.draw(pen); 
		    forest_0.draw(pen);  
		    
		   
		    // PLAYER
		    player.draw(pen);
		    
		    //Health_UI
		   // health.health_UI_draw(pen, redhood_hitbox, venustrap_hitbox);
		   // health.health_UI_draw(pen, redhood_hitbox, scorpion_hitbox);
		    health.health_UI_draw(pen, redhood_hitbox, werewolf_hitbox);
		    
		    
		    
		    //	AI
		   //venustrap.ai_draw(pen, venustrap_hitbox, redhood_hitbox);
		  // scorpion.ai_draw(pen, scorpion_hitbox, redhood_hitbox);
		   werewolf.ai_draw(pen, werewolf_hitbox, redhood_hitbox);
		    
		    //Testing Tool
		    if(testing_Tool == true) 
		    {
			    // Sets the color to green for the player_hitbox
			    pen.setColor(Color.GREEN);
			    
			    redhood_hitbox.draw(pen);
			    
			   // player_attack_hitbox.draw(pen);
		
			    // Sets the colors for other elements to Default
			    pen.setColor(Color.RED);
			    floor.draw(pen);
			    top_wall.draw(pen);
			    left_wall.draw(pen);
		
			    // Sets the colors for AI_enemies_hitbox
			    pen.setColor(Color.RED);
			   // venustrap_hitbox.draw(pen);
			  //  scorpion_hitbox.draw(pen);
			    werewolf_hitbox.draw(pen);
			    
			    // Sets Attack hitbox when colliding with enemies
			   
//			    if(player_attack_hitbox.overlaps(scorpion)) {
//			    	 pen.setColor(Color.BLUE);
//			    	 player_attack_hitbox.draw(pen);
//			    	 scorpion.health -= 50;
//			    	 if(scorpion.health <= 0) {
//			    		 pen.setColor(Color.GREEN);
//					     scorpion_hitbox.draw(pen);
//			    	 }
//			    }
//			    
			    if (werewolf_hitbox.overlaps(redhood_hitbox)){
				    	 pen.setColor(Color.RED);
				    	 redhood_hitbox.draw(pen);
					     
				}
		
			    // Sets the color to red if the player_hitbox overlaps with the AI_enemies_hitbox
//			    if (venustrap_hitbox.overlaps(redhood_hitbox)) 
//			    {
//			    	 pen.setColor(Color.RED);
//			    	 redhood_hitbox.draw(pen);
//				     
//			    }
//			    
//			    if (scorpion_hitbox.overlaps(redhood_hitbox)) 
//			    {
//			    	 pen.setColor(Color.RED);
//			    	 redhood_hitbox.draw(pen);
//			    }
		    }
		    
		    // Reset the color to default
		    pen.setColor(Color.BLACK);
		}
	}

	@Override
	public void mouseDragged(MouseEvent arg0) {}

	@Override
	public void mouseMoved(MouseEvent arg0) {}

	
}