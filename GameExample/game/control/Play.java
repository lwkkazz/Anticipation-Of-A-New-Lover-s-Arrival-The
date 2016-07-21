package game.control;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;

import org.lwjgl.input.Keyboard;
import org.newdawn.slick.*;
import org.newdawn.slick.state.*;

import game.levels.LevelOne;
import game.objects.Asteroid;
import game.objects.Enemy;
import game.objects.Player;
import game.objects.Shoot;

public class Play extends BasicGameState{

	private int cont=0;
	
	private long time, deltaTime, astroTime, astroMark, bossTimer;
	
	private LevelOne lvl;
	
	private List<Asteroid> astro;
	private List<Shoot> shoots;
	private List<Shoot> bossShoots;

	
	private Player player;
		
	private Enemy boss;
	
	int valor =0;

	private boolean bossFight;

	private long bossDeltaTime;

	private long bossShotTimer;

	private boolean astroImune = false;
	
	public Play(int state){
	}

	@Override
	public void init(GameContainer gameContainer, StateBasedGame sbGame) throws SlickException {

		player = new Player((int) GameParams.mapScreenX(50), (int) GameParams.mapScreenX(50));
		Keyboard.enableRepeatEvents(false);
		
		shoots		= Collections.synchronizedList(new ArrayList<Shoot>());
		astro		= Collections.synchronizedList(new ArrayList<Asteroid>());
		bossShoots	= Collections.synchronizedList(new ArrayList<Shoot>());

		bossDeltaTime = astroMark = time = System.currentTimeMillis();
		
		bossFight = true;
		
		lvl = new LevelOne();
		bossTimer = System.currentTimeMillis();
	}

	@Override
	public void render(GameContainer gameContainer, StateBasedGame sbGame, Graphics graph) throws SlickException {
		player.render(gameContainer, sbGame, graph);
		
		if(boss!=null){
			boss.render(gameContainer, sbGame, graph);
		
		for(Shoot tiro:bossShoots)
			tiro.render(gameContainer, sbGame, graph);
		}
		for(Shoot tiro:shoots)
			tiro.render(gameContainer, sbGame, graph);
		for(Asteroid aero:astro)
			aero.render(gameContainer, sbGame, graph);
	}

	@Override
	public void update(GameContainer gameContainer, StateBasedGame sbGame, int delta) throws SlickException {
				
		if(System.currentTimeMillis()-bossTimer<GameParams.bossTime){
			generateAstros();
		}else{
			while(bossFight){
			boss = new Enemy();
			bossFight = false;
			}
		}

		player.update(gameContainer, sbGame, delta);
		
		if(boss!=null){
			boss.update(gameContainer, sbGame, delta, player);
			bossShots();
		}
		getInput(gameContainer, sbGame);
		checkCollisions(gameContainer, sbGame, delta);
		performRemoves();
	}
	
	private void bossShots() {
		bossShotTimer = System.currentTimeMillis() - bossDeltaTime;
		if(bossShotTimer > 300){
			bossDeltaTime = System.currentTimeMillis();
			bossShoots.add(boss.getShoot());
		}		
	}

	private void checkCollisions(GameContainer gameContainer, StateBasedGame sbGame, int delta) 
			throws SlickException {

		for(Shoot tiro:bossShoots){
			if(tiro.getBox().getY()<=GameParams.screenY-1){
				tiro.update(gameContainer, sbGame, delta);
			}else{
				tiro.setIsValid(false);
			}
			if(GameParams.trigger(player, tiro)){
				//Check for boss shoots on player
				bossTimer = System.currentTimeMillis();
				boss = null;
				sbGame.getState(GameParams.gameOver).init(gameContainer, sbGame);
				sbGame.enterState(GameParams.gameOver);
			}
		}
		
		
		for(Asteroid aero:astro){
			if(aero.getBox().getY()<=GameParams.screenY-1){
				aero.update(gameContainer, sbGame, delta);
			}else{
				aero.setIsValid(false);
			}
			
			if(GameParams.trigger(player, aero)){
				//Check for asteroid hits on player
				if(!astroImune ){
					bossTimer = System.currentTimeMillis();
					boss = null;
					sbGame.getState(GameParams.gameOver).init(gameContainer, sbGame);
					sbGame.enterState(GameParams.gameOver);
				}
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
				if(!(boss==null)){
					if(GameParams.trigger(tiro, boss)){
						//Check for player shoots on boss
						tiro.setIsValid(false);
						boss.onHit();
						System.out.println("HIT! Life remaining "+boss.getLife());
					}
				}
			}		
	}

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

	private void getInput(GameContainer gameContainer, StateBasedGame sbGame) throws SlickException{
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
		if(Keyboard.isKeyDown(Keyboard.KEY_D)){
				astroImune=true;
		}
		if(Keyboard.isKeyDown(Keyboard.KEY_ESCAPE)){
			bossTimer = System.currentTimeMillis();
			boss = null;
			sbGame.getState(GameParams.menu).init(gameContainer, sbGame);
			sbGame.enterState(GameParams.menu);		
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
		
		synchronized(bossShoots){
			Iterator<Shoot> i = bossShoots.iterator();
			while(i.hasNext()){
				Shoot bossShoots =  i.next();
				if(!bossShoots.isValid()){
					i.remove();
				}
			}
		}
		
		if(!(boss==null)){
			if(!boss.isValid()){
				boss=null;
			}
		}
	}
	
	@Override
	public int getID() {
		return 1;
	}
}