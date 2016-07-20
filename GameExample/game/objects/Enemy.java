package game.objects;

import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.geom.Rectangle;
import org.newdawn.slick.state.StateBasedGame;

import game.control.GameParams;

public class Enemy extends GameObject{


	private int x, y, w, h;
	
	private Rectangle boundBox;

	private boolean isValid, enter;

	private int cont=0;
	
	public Enemy(){
		isValid = true;
		
		enter = true;
		
		y = -200;
		x = (int)GameParams.mapScreenX(50);
		w = GameParams.screenX/70;
		h = w*2;
		
		
		boundBox = new Rectangle(x,y,w,h);
	}
	
	public void render(GameContainer gameContainer, StateBasedGame sbGame, Graphics graph){
		graph.drawOval(x, y, w, h);
	}
	
	public void update(GameContainer gameContainer, StateBasedGame sbGame, int delta){	
		if(enter){
			
			if(cont==0){
				y ++;
			}
			
			cont ++;
			
			if(cont==20){
				cont=0;
			}
		
			if(y >= GameParams.mapScreenY(15))
				enter=false;
		}else{
			boundBox = new Rectangle(x,y,w,h);
		}
		
	}
	
	public Rectangle getBox(){
		return boundBox;
	}
	
	public void setIsValid(boolean value){
		isValid = value;
	}		
	public boolean isValid(){
		return false;
	}
}
