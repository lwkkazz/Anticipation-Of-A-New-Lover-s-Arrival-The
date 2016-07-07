package game;

import org.newdawn.slick.*;
import org.newdawn.slick.geom.Rectangle;
import org.newdawn.slick.state.*;

import org.lwjgl.input.Mouse;


//@lwkkazz
public class Menu extends BasicGameState{
	
	private Rectangle start, exit;
	
	private Image titleText;
	
	private int mX, mY;
		
	private int mouseEvent;
	
	public Menu(int state){
	}

	@Override
	public void init(GameContainer gameContainer, StateBasedGame sbGame) throws SlickException {
		
		start	= new Rectangle(GameParams.mapScreenX(50)-GameParams.screenX/40,GameParams.mapScreenY(50),(GameParams.screenY/10)*2,GameParams.screenY/20);
		exit	= new Rectangle(GameParams.mapScreenX(50)-GameParams.screenX/40,GameParams.mapScreenY(60),(GameParams.screenY/10)*2,GameParams.screenY/20);

		titleText = new Image("/res/titletext.png");
	}
	

	@Override
	public void render(GameContainer gameContainer, StateBasedGame sbGame, Graphics graph) throws SlickException {

		graph.drawImage(titleText, GameParams.mapScreenX(48), GameParams.mapScreenY(30));
		graph.draw(start);
		graph.drawString("Start Game!", GameParams.mapScreenX(49), GameParams.mapScreenY(51));
		graph.draw(exit);
		graph.drawString("Exit", GameParams.mapScreenX(52), GameParams.mapScreenY(61));

		
		graph.drawString("X: "+mX+"| Y: "+mY, GameParams.mapScreenX(10), GameParams.mapScreenY(10));
	}

	@Override
	public void update(GameContainer gameContainer, StateBasedGame sbGame, int delta) throws SlickException {
		mouseEvent = Mouse.getEventButton();

		mX = Mouse.getX();
		mY = GameParams.screenY-Mouse.getY();
		
		checkClick(sbGame);
	}

	public void checkClick(StateBasedGame sbGame){		
		if(start.contains(mX, mY)){
			if(mouseEvent != -1){
				if(mouseEvent==0){
					Mouse.setCursorPosition(0, GameParams.screenY);
					sbGame.enterState(GameParams.play);
				}
			}
		}if(exit.contains(mX, mY)){
			if(mouseEvent != -1){
				if(mouseEvent==0){
					System.exit(0);
				}
			}
		}
	}
	
	
	@Override
	public int getID() {
		return 0;
	}
}