package maingame;

import org.newdawn.slick.*;
import org.newdawn.slick.state.*;
import org.lwjgl.input.Keyboard;

import java.util.ArrayList;
import java.util.*;
//@lwkkazz
public class Menu extends BasicGameState{

	int i = 0;
	
	long time, deltaTime;
	
	List<Asteroid> astro;
	List<Shoot> shoots;
	
	Player player;
	
	boolean canShoot = true;
	
	public Menu(int state){
	}

	@Override
	public void init(GameContainer gameContainer, StateBasedGame sbGame) throws SlickException {

		player = new Player((int) GameParams.mapScreenX(50), (int) GameParams.mapScreenX(50));
		Keyboard.enableRepeatEvents(false);
		
		shoots = Collections.synchronizedList(new ArrayList<Shoot>());
		time = System.currentTimeMillis();
	}

	@Override
	public void render(GameContainer gameContainer, StateBasedGame sbGame, Graphics graph) throws SlickException {
		player.render(gameContainer, sbGame, graph);
		for(Shoot tiro:shoots)
			tiro.render(gameContainer, sbGame, graph);
		//for(Asteroid aero:astro)
			//aero.render(gameContainer, sbGame, graph);
	}

	@Override
	public void update(GameContainer gameContainer, StateBasedGame sbGame, int delta) throws SlickException {
		getInput();
		generateAstros();
		for(Shoot tiro:shoots){
			if(tiro.y>0)
				tiro.update(gameContainer, sbGame, delta);
		}
		
		//for(Asteroid aero:astro)
			//aero.update(gameContainer, sbGame, delta);
		
		performRemoves();
	}
	
	private void generateAstros() {
		// TODO Auto-generated method stub
		
	}

	private void getInput(){
		if(Keyboard.isKeyDown(Keyboard.KEY_UP)){				
			player.move(Keyboard.KEY_UP);
		}	
		if(Keyboard.isKeyDown(Keyboard.KEY_DOWN)){				
			player.move(Keyboard.KEY_DOWN);
		}		
		if(Keyboard.isKeyDown(Keyboard.KEY_LEFT)){
			player.move(Keyboard.KEY_LEFT);
		}				
		if(Keyboard.isKeyDown(Keyboard.KEY_RIGHT)){
			player.move(Keyboard.KEY_RIGHT);
		}
		if(Keyboard.isKeyDown(Keyboard.KEY_SPACE)){
			deltaTime = System.currentTimeMillis() - time;
			if(deltaTime > GameParams.shootRate){
				time = System.currentTimeMillis();
				shoots.add(new Shoot(player.getX()+GameParams.screenX/200,player.getY()));
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
		/*
		synchronized(astro){
			Iterator<Asteroid> i = astro.iterator();
			while(i.hasNext()){
				Asteroid aero =  i.next();
				if(aero.y>=GameParams.screenY){
					i.remove();
				}
			}
		}*/
		
		
	}
	
	@Override
	public int getID() {
		return 0;
	}
}