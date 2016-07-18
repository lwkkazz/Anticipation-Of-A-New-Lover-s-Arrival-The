package game.objects;

import org.lwjgl.input.Keyboard;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.geom.Rectangle;
import org.newdawn.slick.state.StateBasedGame;

import game.control.GameParams;

public class Player extends GameObject {

	private int x, y, w, h, delta;
	
	private Rectangle boundBox;
	
	public Player(int x, int y){
		
		this.x = x;
		this.y = y;
		
		w = GameParams.screenX/100;
		h = w*2;
		
		boundBox = new Rectangle(x,y,w,h);
	}
	

	public void render(GameContainer gameContainer, StateBasedGame sbGame, Graphics graph){
		graph.drawOval(x, y, w, h);
	}
	
	public void update(GameContainer gameContainer, StateBasedGame sbGame, int delta){
		boundBox = new Rectangle(x,y,w,h);
		this.delta = delta;
	}

	
	public void move(int dir){
		switch(dir){
			case Keyboard.KEY_UP:
				if(y>GameParams.screenY/2)
					y -= GameParams.moveSpeed*delta;
				break;
				
			case Keyboard.KEY_DOWN:
				if(y<GameParams.screenY-h)
					y += GameParams.moveSpeed*delta;
				break;
				
			case Keyboard.KEY_LEFT:
				if(x>0)
					x -= GameParams.moveSpeed*delta;
				break;
				
			case Keyboard.KEY_RIGHT:
				if(x<GameParams.screenX-w)
					x += GameParams.moveSpeed*delta;
				break;
	
		}
	}
		
	public int getX() {
		return x;
	}
	public void setX(int x) {
		this.x = x;
	}

	public int getY() {
		return y;
	}
	public void setY(int y) {
		this.y = y;
	}
	
	public Rectangle getBox(){
		return boundBox;
	}	
}
