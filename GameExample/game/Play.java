package game;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;

import org.lwjgl.input.Keyboard;
import org.newdawn.slick.*;
import org.newdawn.slick.state.*;

import levels.LevelOne;

public class Play extends BasicGameState{

	private int cont=0;
	
	private long time, deltaTime, astroTime, astroMark, bossTimer;
	
	private LevelOne lvl;
	
	private List<Asteroid> astro;
	private List<Shoot> shoots;
	
	private Player player;
		
	
	int valor =0;
	
	public Play(int state){
	}

	@Override
	public void init(GameContainer gameContainer, StateBasedGame sbGame) throws SlickException {

		player = new Player((int) GameParams.mapScreenX(50), (int) GameParams.mapScreenX(50));
		Keyboard.enableRepeatEvents(false);
		
		shoots	= Collections.synchronizedList(new ArrayList<Shoot>());
		astro	= Collections.synchronizedList(new ArrayList<Asteroid>());
		astroMark = time = System.currentTimeMillis();
		
		lvl = new LevelOne();
		bossTimer = System.currentTimeMillis();
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
		
		if(System.currentTimeMillis()-bossTimer<GameParams.bossTime){
			generateAstros();
		}else{
			//TODO callBossRoutine();
		}
		
		player.update(gameContainer, sbGame, delta);
		
		for(Asteroid aero:astro){
			System.out.println(delta);
			if(aero.getBox().getY()<=GameParams.screenY-1){
				aero.update(gameContainer, sbGame, delta);
			}else{
				aero.setIsValid(false);
			}
			
			if(GameParams.trigger(player, aero)){
				//System.exit(0);
				//sbGame.enterState(GameParams.menu);
			}
		}
		
			for(Shoot tiro:shoots){
					if(tiro.getBox().getY()>0){
						tiro.update(gameContainer, sbGame, delta);
					}else{
						tiro.setIsValid(false);
					}			
				for(Asteroid aero:astro){

					if(GameParams.trigger(tiro, aero)){
						tiro.setIsValid(false);
						aero.setIsValid(false);
					}
				}
			}
			
		
		performRemoves();
	}
	
	//TODO
	private void generateAstros() {
		astroTime = System.currentTimeMillis() - astroMark;
		
		if(astroTime > GameParams.spawnRate){
			astroMark = System.currentTimeMillis();
			int[][] temp = lvl.getLevel();
			
			if(cont>=temp.length)
				cont = 0;
			
			for(int i=0; i < temp[cont].length; i ++){
				if(temp[cont][i]==1)
					astro.add(new Asteroid(i*10));
			}
			cont++;
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
		if(Keyboard.isKeyDown(Keyboard.KEY_ESCAPE)){
			System.exit(0);
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