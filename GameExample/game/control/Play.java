package game.control;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;
import java.util.Random;

import org.lwjgl.input.Keyboard;
import org.newdawn.slick.*;
import org.newdawn.slick.state.*;

import game.levels.Level;
import game.objects.Asteroid;
import game.objects.Enemy;
import game.objects.Player;
import game.objects.Shoot;

public class Play extends BasicGameState{
		
	private List<Asteroid> astro;
	private List<Shoot> shoots;
	private List<Shoot> bossShoots;

	private Sound shootSound;
	private Sound bossDeathSound;
	
	private Level lvl;
	private Random rng;
	private Player player;
	private Enemy boss;
	
	private long bossDeltaTime;
	private long bossShotTimer;
	private long time;
	private long deltaTime;
	private long astroTime;
	private long astroMark;
	private long bossTimer;


	private int pattern;
	private int cont;
	private int masterCont;
	private int score;

	private boolean astroImune;
	private boolean bossFight;
	private boolean callBoss;
	private boolean finishGame;
	
	public Play(int state){
	}

	@Override
	public void init(GameContainer gameContainer, StateBasedGame sbGame) throws SlickException {

		shoots		= Collections.synchronizedList(new ArrayList<Shoot>());
		astro		= Collections.synchronizedList(new ArrayList<Asteroid>());
		bossShoots	= Collections.synchronizedList(new ArrayList<Shoot>());
		
		shootSound		= new Sound("/res/shoot1.wav");
		bossDeathSound		= new Sound("/res/shoot2.wav");
		
		lvl		= new Level();
		rng		= new Random();
		player	= new Player((int) GameParams.mapScreenX(50), (int) GameParams.mapScreenX(50));
		
		bossDeltaTime	= System.currentTimeMillis();
		astroMark		= System.currentTimeMillis();
		time			= System.currentTimeMillis();
		bossTimer		= System.currentTimeMillis();

		GameParams.score	= 0;
		score				= 0;
		cont				= 0;
		
		bossFight = true;
		astroImune = false;
		callBoss = false;		
		
		pattern = rng.nextInt(lvl.getPatternSize());
		
	}

	@Override
	public void render(GameContainer gameContainer, StateBasedGame sbGame, Graphics graph) throws SlickException {
		graph.drawString("SCORE: "+score, GameParams.mapScreenX(90), GameParams.mapScreenY(10));
		
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
		player.update(gameContainer, sbGame, delta);
		
		if(!callBoss){
			generateAstros();
			bossTimer = System.currentTimeMillis();
		}else if(System.currentTimeMillis()-bossTimer<GameParams.bossTime){
			while(bossFight){
				boss = new Enemy();
				bossFight = false;
			}
		}
		
		if(boss!=null){
			boss.update(gameContainer, sbGame, delta, player);			
			bossShots();
		}
		
		checkCollisions(gameContainer, sbGame, delta);
		performRemoves();
		
		if(!finishGame){
			getInput(gameContainer, sbGame);
		}else{
			finishThis(gameContainer, sbGame);
		}
	}
	
	private void finishThis(GameContainer gameContainer, StateBasedGame sbGame) throws SlickException {
		if(player.getBox().getY()>=0){
			player.move(GameParams.finish);
		}else{
			bossTimer = System.currentTimeMillis();
			boss = null;
			finishGame = false;
			GameParams.score = score;
			sbGame.getState(GameParams.gameWin).init(gameContainer, sbGame);
			sbGame.enterState(GameParams.gameWin);
		}
	}

	private void bossShots() {
		bossShotTimer = System.currentTimeMillis() - bossDeltaTime;
		if(bossShotTimer > GameParams.bossShotTimer){
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
				if(!astroImune){
					bossTimer = System.currentTimeMillis();
					boss = null;
					GameParams.score = score;
					sbGame.getState(GameParams.gameOver).init(gameContainer, sbGame);
					sbGame.enterState(GameParams.gameOver);
				}
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
					GameParams.score = score;
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
					score += GameParams.astroScore;
					tiro.setIsValid(false);
					aero.setIsValid(false);
				}
			}
			if(!(boss==null)){
				if(GameParams.trigger(tiro, boss)){
					//Check for player shoots on boss
					tiro.setIsValid(false);
					boss.onHit();
				}
			}
		}		
	}

	private void generateAstros() {
		astroTime = System.currentTimeMillis() - astroMark;
		if(astroTime > GameParams.spawnRate){
			astroMark = System.currentTimeMillis();
			int[][][] temp = lvl.getLevel();
			
			if(cont>=temp[0].length){
				cont = 0;
				masterCont++;
				pattern = rng.nextInt(lvl.getPatternSize());
				if(masterCont==temp.length){
					callBoss = true;
				}
			}
			
			for(int i=0; i < temp[0][cont].length; i ++){
				if(temp[pattern][cont][i]==1)
					astro.add(new Asteroid(i*GameParams.astroOffset));
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
			if(GameParams.debug)
				astroImune=true;
		}
		if(Keyboard.isKeyDown(Keyboard.KEY_B)){
			if(GameParams.debug)
				callBoss=true;
		}
		if(Keyboard.isKeyDown(Keyboard.KEY_ESCAPE)){
			bossTimer = System.currentTimeMillis();
			boss = null;
			GameParams.score = score;
			sbGame.getState(GameParams.menu).init(gameContainer, sbGame);
			sbGame.enterState(GameParams.menu);		
		}
		if(Keyboard.isKeyDown(Keyboard.KEY_SPACE)){
			deltaTime = System.currentTimeMillis() - time;
			if(deltaTime > GameParams.shootRate){
				time = System.currentTimeMillis();
				shootSound.play(10f,0.3f);
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
				bossDeathSound.play(0.1f, 1f);
				boss=null;
				finishGame=true;
			}
		}
	}
	
	@Override
	public int getID() {
		return GameParams.play;
	}
}