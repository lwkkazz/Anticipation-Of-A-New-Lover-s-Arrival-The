package maingame;

import org.newdawn.slick.geom.Rectangle;

public final class GameParams {
	public static final String	gameName 	= "Vector Combat ";
	
	public static final int		menu		= 0;
	public static final int 	play		= 1;
	
	public static final int 	screenX		= 1280;
	public static final int 	screenY		= 720;
	
	public static final int		shootRate	= 200;
	
	public static final int		moveSpeed	= GameParams.screenY/500;
	
	public static float mapScreenY(int value){
		if((value>=0)&&(value<=100))
			return screenY/100 * value;
		else 
			return -1;
	}
	
	public static float mapScreenX(int value){
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
