package maingame;

import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.geom.Rectangle;
import org.newdawn.slick.state.StateBasedGame;

public class Shoot extends GameObject {
	
	private float x, y, h, w;
	
	private Rectangle boundBox;
	
	private boolean isValid;
	
	public Shoot(float x, float y){
		w = GameParams.screenY/500;
		h = w*2;
		
		this.x = x + w/2;
		this.y = y + h;
		
		boundBox = new Rectangle(x,y,h,w);
		
		isValid = true;
	}
	
	public void render(GameContainer gameContainer, StateBasedGame sbGame, Graphics graph){
		graph.drawRect(x, y, w, h);
	}
	
	public void update(GameContainer gameContainer, StateBasedGame sbGame, int delta){
		if(y>0)
			y = y-2;
		boundBox = new Rectangle(x,y,h,w);
	}
	
	public Rectangle getBox(){
		return boundBox;
	}

	public void setIsValid(boolean value){
		isValid = value;
	}
	
	public boolean isValid(){
		return isValid;
	}
	
}
