package game.control;

import org.newdawn.slick.geom.Rectangle;

import game.objects.GameObject;

public final class GameParams {
	public static final String	gameName 	= "Space Rush!";
	
	public static final int		menu		= 0;
	public static final int 	play		= 1;	
	public static final int 	gameOver	= 2;

	public static final int 	screenX		= 1280;
	public static final int 	screenY		= 720;
	
	public static final int		shootRate	= 500;
	public static final int		shootSpeed	= 2; //Less is faster
	
	public static final int		bossTime	= 3000;

	public static final int		spawnRate	= 400;

	public static final int		moveSpeed	= GameParams.screenY/500;
	
	public static float mapScreenY(int value){
		if((value>=0)&&(value<=100))
			return screenY/100 * value;
		else 
			return -1;
	}
	
	public static float mapScreenX(float value){
		if((value>=0)&&(value<=100))
			return screenX/100 * value;
		
		else 
			return -1;
	}
	
	public static boolean trigger(GameObject shoot, GameObject astro){
		
		Rectangle box1	= shoot.getBox();
		Rectangle box2	= astro.getBox();
		
		if(box1.intersects(box2)){
			return true;
		}else{
			return false;
		}
	}
}
