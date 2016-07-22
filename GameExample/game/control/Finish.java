package game.control;

import org.newdawn.slick.*;
import org.newdawn.slick.geom.Rectangle;
import org.newdawn.slick.state.*;

import org.lwjgl.input.*;


//@lwkkazz
public class Finish extends BasicGameState{
	
	private Rectangle start, exit;
	
	private Image winText;
	
	private int mX, mY;
		
	private int mouseEvent;
		
	
	public Finish(int state){
	}

	@Override
	public void init(GameContainer gameContainer, StateBasedGame sbGame) throws SlickException {
		
		start	= new Rectangle(GameParams.mapScreenX(50)-GameParams.screenX/40,GameParams.mapScreenY(50),(GameParams.screenY/10)*2,GameParams.screenY/20);
		exit	= new Rectangle(GameParams.mapScreenX(50)-GameParams.screenX/40,GameParams.mapScreenY(60),(GameParams.screenY/10)*2,GameParams.screenY/20);

		winText = new Image("/res/win_text.png");
	}
	

	@Override
	public void render(GameContainer gameContainer, StateBasedGame sbGame, Graphics graph) throws SlickException {

		graph.drawImage(winText, GameParams.mapScreenX(33), GameParams.mapScreenY(25));
		graph.drawString("Score: "+GameParams.score, GameParams.mapScreenX(48), GameParams.mapScreenY(40));
		graph.draw(start);
		graph.drawString("Play again!", GameParams.mapScreenX(49), GameParams.mapScreenY(51));
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
		getInput(gameContainer, sbGame);
	}

	private void getInput(GameContainer gameContainer, StateBasedGame sbGame) throws SlickException{
		if(Keyboard.isKeyDown(Keyboard.KEY_RETURN)){
			Mouse.setCursorPosition(0, GameParams.screenY);
			sbGame.getState(GameParams.play).init(gameContainer, sbGame);
			sbGame.enterState(GameParams.play);
		}
		if(Keyboard.isKeyDown(Keyboard.KEY_ESCAPE)){
			System.exit(0);
		}
	}

	public void checkClick(GameContainer gameContainer, StateBasedGame sbGame) throws SlickException{		
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
		return GameParams.gameWin;
	}
}