package game.control;

import org.lwjgl.input.Mouse;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.geom.Rectangle;
import org.newdawn.slick.state.BasicGameState;
import org.newdawn.slick.state.StateBasedGame;



public class GameOver extends BasicGameState {
	
	private Rectangle start, exit;
		
	private int mX, mY;
		
	private int mouseEvent;
	
	private Image gameOver;
	
	public GameOver(int state){
	}

	@Override
	public void init(GameContainer gameContainer, StateBasedGame sbGame) throws SlickException {
		
		start	= new Rectangle(GameParams.mapScreenX(50)-GameParams.screenX/40,GameParams.mapScreenY(50),(GameParams.screenY/10)*2,GameParams.screenY/20);
		exit	= new Rectangle(GameParams.mapScreenX(50)-GameParams.screenX/40,GameParams.mapScreenY(60),(GameParams.screenY/10)*2,GameParams.screenY/20);

		gameOver = new Image("/res/game_over.png");
		Mouse.updateCursor();
	}
	

	@Override
	public void render(GameContainer gameContainer, StateBasedGame sbGame, Graphics graph) throws SlickException {

		graph.drawImage(gameOver, GameParams.mapScreenX(33), GameParams.mapScreenY(25));
		graph.drawString("Score: "+GameParams.score, GameParams.mapScreenX(48), GameParams.mapScreenY(40));
		graph.draw(start);
		graph.drawString("Start again!", GameParams.mapScreenX(49), GameParams.mapScreenY(51));
		graph.draw(exit);
		graph.drawString("Exit", GameParams.mapScreenX(52), GameParams.mapScreenY(61));

		graph.drawString("X: "+mX+"| Y: "+mY, GameParams.mapScreenX(10), GameParams.mapScreenY(10));
	}

	@Override
	public void update(GameContainer gameContainer, StateBasedGame sbGame, int delta) throws SlickException {
		mouseEvent = Mouse.getEventButton();

		mX = Mouse.getX();
		mY = GameParams.screenY-Mouse.getY();
		
		checkClick(gameContainer, sbGame);
	}

	public void checkClick(GameContainer gameContainer, StateBasedGame sbGame)  throws SlickException {		
		if(start.contains(mX, mY)){
			if(mouseEvent==0){
				Mouse.setCursorPosition(0, GameParams.screenY);
				sbGame.getState(GameParams.play).init(gameContainer, sbGame);
				sbGame.enterState(GameParams.play);
			}
		}if(exit.contains(mX, mY)){
			if(mouseEvent==0){
				System.exit(0);
			}
		}
	}

	@Override
	public int getID() {
		return 2;
	}
}
