package maingame;

import java.util.Random;

import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.state.StateBasedGame;

public class Asteroid extends GameObject {

	int x,y,h,w;
	
	Random rng;
	
	public Asteroid(){
		rng = new Random();
		
		int rand = rng.nextInt(100);
		y = 0;
		x = (int) GameParams.mapScreenX(rand);
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
