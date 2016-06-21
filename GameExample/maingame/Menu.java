package maingame;

import org.newdawn.slick.*;
import org.newdawn.slick.state.*;
import org.lwjgl.input.Keyboard;

import java.util.ArrayList;
import java.util.*;
//@lwkkazz
public class Menu extends BasicGameState{

	int i = 0;
	int x, y;
	
	long time, deltaTime;
	
	List<Asteroid> astro;
	
	List<Shoot> shoots;
	
	boolean canShoot = true;
	
	public Menu(int state){
	}

	@Override
	public void init(GameContainer gameContainer, StateBasedGame sbGame) throws SlickException {
		x = (int) GameParams.mapScreenX(50);
		y = (int) GameParams.mapScreenY(50);
		
		Keyboard.enableRepeatEvents(false);
		
		shoots = Collections.synchronizedList(new ArrayList<Shoot>());
		time = System.currentTimeMillis();
	}

	@Override
	public void render(GameContainer gameContainer, StateBasedGame sbGame, Graphics graph) throws SlickException {
		graph.drawOval(x, y, GameParams.screenX/100, GameParams.screenX/50);
		for(Shoot tiro:shoots)
			tiro.render(gameContainer, sbGame, graph);
		for(Asteroid aero:astro)
			aero.render(gameContainer, sbGame, graph);
	}

	@Override
	public void update(GameContainer gameContainer, StateBasedGame sbGame, int delta) throws SlickException {
		getInput();
		for(Shoot tiro:shoots){
			if(tiro.y>0)
				tiro.update(gameContainer, sbGame, delta);
		}
		for(Asteroid aero:astro)
			aero.update(gameContainer, sbGame, delta);
		
		performRemoves();
	}
	
	private void getInput(){
		if(Keyboard.isKeyDown(Keyboard.KEY_UP)){				
			if(y>0)
				y -= GameParams.screenY/500;
		}	
		if(Keyboard.isKeyDown(Keyboard.KEY_DOWN)){				
			if(y+GameParams.screenX/100<GameParams.screenY)
				y += GameParams.screenY/500;
		}		
		if(Keyboard.isKeyDown(Keyboard.KEY_LEFT)){
			if(x>0)
				x -= GameParams.screenY/500;
		}				
		if(Keyboard.isKeyDown(Keyboard.KEY_RIGHT)){
			if(x+GameParams.screenX/100<GameParams.screenX)
				x += GameParams.screenY/500;
		}
		if(Keyboard.isKeyDown(Keyboard.KEY_SPACE)){
			deltaTime = System.currentTimeMillis() - time;
			if(deltaTime > GameParams.shootRate){
				time = System.currentTimeMillis();
				shoots.add(new Shoot(x+GameParams.screenX/200,y));
			}		
		}
	}

	private void performRemoves(){
		synchronized(shoots){
			Iterator<Shoot> i = shoots.iterator();
			while(i.hasNext()){
				Shoot tiro =  i.next();
				if(tiro.y<=0){
					i.remove();
				}
			}
		}
		
		synchronized(astro){
			Iterator<Asteroid> i = astro.iterator();
			while(i.hasNext()){
				Asteroid aero =  i.next();
				if(aero.y>=GameParams.screenY){
					i.remove();
				}
			}
		}
		
		
	}
	
	@Override
	public int getID() {
		return 0;
	}
}