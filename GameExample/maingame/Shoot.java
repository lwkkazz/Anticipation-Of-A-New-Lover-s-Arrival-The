package maingame;

import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.state.StateBasedGame;

public class Shoot {
	
	public float x, y, h, w;
	
	public Shoot(float x, float y){
		w = GameParams.screenY/1200;
		h = w*2;
		
		this.x = x + w/2;
		this.y = y + h;
		
	}
	
	public void render(GameContainer gameContainer, StateBasedGame sbGame, Graphics graph){
		graph.drawRect(x, y, w, h);
	}
	
	public void update(GameContainer gameContainer, StateBasedGame sbGame, int delta){
		if(y>0)
			y--;
	}
}
