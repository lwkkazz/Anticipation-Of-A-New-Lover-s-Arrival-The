package game;

import java.util.Random;

import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.geom.Rectangle;
import org.newdawn.slick.state.StateBasedGame;

public class Asteroid extends GameObject {

	private int x,y,h,w;
	
	private Random rng;
	
	private boolean isValid;
	
	private Rectangle boundBox;
	
	public Asteroid(){
		rng = new Random();
		int rand = rng.nextInt(100);
		y = 0;
		x = (int) GameParams.mapScreenX(rand);
		h = GameParams.screenY/10;
		w = h;
		boundBox = new Rectangle(x,y,w,h);
		isValid = true;
	}	
	
	public Asteroid(double x){
		y=0;
		this.x =(int) GameParams.mapScreenX((float)x);
		h = GameParams.screenY/10;
		w = h;
		boundBox = new Rectangle(this.x, y, h, w);
		isValid = true;
	}

	public void render(GameContainer gameContainer, StateBasedGame sbGame, Graphics graph){
		graph.drawOval(x, y, w, h);
	}
	
	public void update(GameContainer gameContainer, StateBasedGame sbGame, int delta){
		if(y<GameParams.screenY)
			y ++;
		boundBox = new Rectangle(x,y,w,h);
		//System.out.println("Astro created at: "+x);
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
