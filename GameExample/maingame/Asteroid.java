package maingame;

import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.state.StateBasedGame;

public class Asteroid extends GameObject {

	int x,y,h,w;
	
	public Asteroid(){
		
		double rand = Math.random();
		y = 0;
		x = (int) GameParams.mapScreenX((int) rand*100);
		System.out.println("Random: "+rand+"|Mapped: "+x);
		h = GameParams.screenY/100;
		w = h;
	}
	

	public void render(GameContainer gameContainer, StateBasedGame sbGame, Graphics graph){
		graph.drawOval(x, y, w, h);
	}
	
	public void update(GameContainer gameContainer, StateBasedGame sbGame, int delta){
		if(y<GameParams.screenY)
			y ++;
	}
	
}
