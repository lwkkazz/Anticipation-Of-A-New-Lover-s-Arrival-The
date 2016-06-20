package maingame;

import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.state.StateBasedGame;

public abstract class GameObject {

	
	public GameObject(){
		
	}
	
	public void render(GameContainer gameContainer, StateBasedGame sbGame, Graphics graph){
	}
	
	public void update(GameContainer gameContainer, StateBasedGame sbGame, int delta){
	}
		
}
