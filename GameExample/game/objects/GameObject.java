package game.objects;

import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.geom.Rectangle;
import org.newdawn.slick.state.StateBasedGame;

public abstract class GameObject {

	
	public GameObject(){
		
	}
	
	public void render(GameContainer gameContainer, StateBasedGame sbGame, Graphics graph){
	}
	
	public void update(GameContainer gameContainer, StateBasedGame sbGame, int delta){
	}
	
	public Rectangle getBox(){
		return null;
	}
	
	public void setIsValid(boolean value){
	}		
	public boolean isValid(){
		return false;
	}
}
