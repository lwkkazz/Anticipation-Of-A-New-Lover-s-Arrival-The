package game;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;

import org.lwjgl.input.Keyboard;
import org.newdawn.slick.*;
import org.newdawn.slick.state.*;

public class Play extends BasicGameState{

	private int cont=0;
	
	private long time, deltaTime, astroTime, astroMark;
	
	private List<Asteroid> astro;
	private List<Shoot> shoots;
	
	private Player player;
		
	public Play(int state){
	}

	@Override
	public void init(GameContainer gameContainer, StateBasedGame sbGame) throws SlickException {

		player = new Player((int) GameParams.mapScreenX(50), (int) GameParams.mapScreenX(50));
		Keyboard.enableRepeatEvents(false);
		
		shoots	= Collections.synchronizedList(new ArrayList<Shoot>());
		astro	= Collections.synchronizedList(new ArrayList<Asteroid>());
		astroMark = time = System.currentTimeMillis();
	}

	@Override
	public void render(GameContainer gameContainer, StateBasedGame sbGame, Graphics graph) throws SlickException {
		player.render(gameContainer, sbGame, graph);
		
		for(Shoot tiro:shoots)
			tiro.render(gameContainer, sbGame, graph);
		for(Asteroid aero:astro)
			aero.render(gameContainer, sbGame, graph);
	}

	@Override
	public void update(GameContainer gameContainer, StateBasedGame sbGame, int delta) throws SlickException {
		getInput();
		generateAstros();
		
		player.update(gameContainer, sbGame, delta);
		
		for(Asteroid aero:astro){
			if(aero.getBox().getY()<=GameParams.screenY-1){
				aero.update(gameContainer, sbGame, delta);
			}else{
				aero.setIsValid(false);
			}
			
			if(GameParams.trigger(player, aero)){
				sbGame.enterState(GameParams.menu);
			}
			
			for(Shoot tiro:shoots){
		
				if(tiro.getBox().getY()>0){
					tiro.update(gameContainer, sbGame, delta);
				}else{
					tiro.setIsValid(false);
				}			
			
				if(GameParams.trigger(tiro, aero)){
					tiro.setIsValid(false);
					aero.setIsValid(false);
				}
			}
			
		}
		performRemoves();
	}
	
	private void generateAstros() {
		astroTime = System.currentTimeMillis() - astroMark;
		
		if(astroTime > 100){
			astroMark = System.currentTimeMillis();
			astro.add(new Asteroid(cont));
			cont+=30;
			if(cont > GameParams.screenX)
				cont = 0;
		}
		
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
				shoots.add(new Shoot(player.getX()-player.getBox().getWidth()/2+GameParams.screenX/200,player.getY()));
			}		
		}
	}

	private void performRemoves(){
		synchronized(shoots){
			Iterator<Shoot> i = shoots.iterator();
			while(i.hasNext()){
				Shoot tiro =  i.next();
				if(!tiro.isValid()){
					i.remove();
				}
			}
		}
		
		synchronized(astro){
			Iterator<Asteroid> i = astro.iterator();
			while(i.hasNext()){
				Asteroid aero =  i.next();
				if(!aero.isValid()){
					i.remove();
				}
			}
		}	
	}
	@Override
	public int getID() {
		return 1;
	}
}