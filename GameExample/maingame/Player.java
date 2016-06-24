package maingame;

import org.lwjgl.input.Keyboard;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.state.StateBasedGame;

public class Player extends GameObject {

	private int x, y, w, h;
	
	public Player(int x, int y){
		
		this.x = x;
		this.y = y;
		
		w = GameParams.screenX/100;
		h = w*2;
	}
	

	public void render(GameContainer gameContainer, StateBasedGame sbGame, Graphics graph){
		graph.drawOval(x, y, w, h);
	}
	
	public void update(GameContainer gameContainer, StateBasedGame sbGame, int delta){
		
	}

	
	public void move(int dir){
		switch(dir){
		
			case Keyboard.KEY_UP:
				if(y>0)
					y -= GameParams.moveSpeed;
				break;
				
			case Keyboard.KEY_DOWN:
				if(y<GameParams.screenY)
					y += GameParams.moveSpeed;
				break;
				
			case Keyboard.KEY_LEFT:
				if(x>0)
					x -= GameParams.moveSpeed;
				break;
				
			case Keyboard.KEY_RIGHT:
				if(x<GameParams.screenX)
					x += GameParams.moveSpeed;
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
	
	
}
