package game.objects;

import java.util.Random;

import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.geom.Rectangle;
import org.newdawn.slick.state.StateBasedGame;

import game.control.GameParams;

public class Enemy extends GameObject{


	private int x, y, w, h;
	
	private Rectangle boundBox;

	private boolean isValid, enter, moveRight;

	private int cont=0;
	
	private Random rng;
	
	private int leftWall, rightWall;

	private long time;

	private long deltaTime;
	
	public Enemy(){
		isValid		= true;
		moveRight	= true;
		enter		= true;
		
		y = -200;
		x = (int)GameParams.mapScreenX(50);
		w = GameParams.screenX/70;
		h = w*2;
		
		rng = new Random();
		boundBox = new Rectangle(x,y,w,h);
	}
	
	public void render(GameContainer gameContainer, StateBasedGame sbGame, Graphics graph){
		graph.drawOval(x, y, w, h);
	}
	
	public void update(GameContainer gameContainer, StateBasedGame sbGame, int delta, Player player){	
		
		leftWall	= (int)player.getBox().getCenterX()-GameParams.screenX/7;
		
		rightWall	= (int)player.getBox().getCenterX()+GameParams.screenX/7;

		if(enter){
			
			if(cont==0){
				y ++;
			}
			
			cont ++;
			
			if(cont==20){
				cont=0;
			}
		
			if(y >= GameParams.mapScreenY(15))
				enter=false;
		}else{
			boundBox = new Rectangle(x,y,w,h);
			loversArival(gameContainer, sbGame, delta, player);
		}
		
	}
	
	private void loversArival(GameContainer gameContainer, StateBasedGame sbGame, int delta, Player player) {
			if(moveRight){
				x++;
				
				if(rng.nextInt(1000)<=1)
					moveRight=false;
				
				if(x >= rightWall)
					moveRight = false;
			}else{
				x--;
				
				if(rng.nextInt(1000)<=1)
					moveRight=true;
				
				if(x <= leftWall)
					moveRight = true;
			}
			
			deltaTime = System.currentTimeMillis() - time;
			if(deltaTime > 500){
				time = System.currentTimeMillis();
				//TODO
			}		

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

	public Shoot getShoot() {
		return new Shoot(boundBox.getX()-boundBox.getWidth()/2+GameParams.screenX/200,boundBox.getY()+h,true);
	}
}
