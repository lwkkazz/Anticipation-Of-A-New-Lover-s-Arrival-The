package game.objects;

import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.geom.Rectangle;
import org.newdawn.slick.state.StateBasedGame;

import game.control.GameParams;

public class Shoot extends GameObject {
	
	private float x, y, h, w;
	
	private Rectangle boundBox;
	
	private int cont = 0;
	
	private boolean isValid;

	private boolean enemy;
	
	public Shoot(float x, float y){
		w = GameParams.screenY/100;
		h = w;
		
		this.x = x + w/2;
		this.y = y + h;
		
		boundBox = new Rectangle(x,y,h,w);
		
		isValid = true;
	}
	
	public Shoot(float x, float y, boolean isEnemy){
		w = GameParams.screenY/100;
		h = w;
		
		this.x = x + w/2;
		this.y = y + h;
		
		boundBox = new Rectangle(x,y,h,w);
		enemy = isEnemy;
		isValid = true;
	}
	
	public void render(GameContainer gameContainer, StateBasedGame sbGame, Graphics graph){
		graph.drawRect(x, y, w, h);
		graph.fill(boundBox);
	}
	
	public void update(GameContainer gameContainer, StateBasedGame sbGame, int delta){
		if(y>0 && cont==0){
			if(enemy){
				y++;
			}else
				y --;
			
			cont = 0;
		}
		boundBox = new Rectangle(x,y,h,w);
		cont++;
		if(enemy){
			if (cont==1)
				cont = 0;
		}else if(cont==GameParams.shootSpeed)
			cont=0;
		//System.out.println(cont);
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
