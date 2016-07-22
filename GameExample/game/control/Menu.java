package game.control;

import org.newdawn.slick.*;
import org.newdawn.slick.geom.Rectangle;
import org.newdawn.slick.state.*;

import org.lwjgl.input.*;


//@lwkkazz
public class Menu extends BasicGameState{
	
	private Rectangle start, exit;
	
	private Image titleText;
	
	private int mX, mY;
		
	private int mouseEvent;
		
	private boolean[] code = new boolean[6];// = {false,false,false,false,false,false};
	
	public Menu(int state){
		for(int i=0;i<code.length;i++)
			code[i]=false;
	}

	@Override
	public void init(GameContainer gameContainer, StateBasedGame sbGame) throws SlickException {
		
		start	= new Rectangle(GameParams.mapScreenX(50)-GameParams.screenX/40,GameParams.mapScreenY(50),(GameParams.screenY/10)*2,GameParams.screenY/20);
		exit	= new Rectangle(GameParams.mapScreenX(50)-GameParams.screenX/40,GameParams.mapScreenY(60),(GameParams.screenY/10)*2,GameParams.screenY/20);

		titleText = new Image("/res/title_text.png");
	}
	

	@Override
	public void render(GameContainer gameContainer, StateBasedGame sbGame, Graphics graph) throws SlickException {

		graph.drawImage(titleText, GameParams.mapScreenX(33), GameParams.mapScreenY(30));
		graph.draw(start);
		graph.drawString("Start game!", GameParams.mapScreenX(49), GameParams.mapScreenY(51));
		graph.draw(exit);
		graph.drawString("Exit", GameParams.mapScreenX(52), GameParams.mapScreenY(61));

		if(GameParams.debug)
			graph.drawString("X: "+mX+"| Y: "+mY+" DEBUG", GameParams.mapScreenX(10), GameParams.mapScreenY(10));
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
		if(Keyboard.isKeyDown(Keyboard.KEY_UP)){
			code[0] = true;
		}
		if(code[0]){
			if(Keyboard.isKeyDown(Keyboard.KEY_DOWN)){
				code[1]=true;
			}
		}
		if(code[1]){
			if(Keyboard.isKeyDown(Keyboard.KEY_LEFT)){
				code[2]=true;
			}
		}
		if(code[2]){
			if(Keyboard.isKeyDown(Keyboard.KEY_RIGHT)){
				code[3]=true;
			}
		}
		if(code[3]){
			if(Keyboard.isKeyDown(Keyboard.KEY_B)){
				code[4]=true;
			}
		}
		if(code[4]){
			if(Keyboard.isKeyDown(Keyboard.KEY_A)){
				code[5]=true;
			}
		}if(code[5])
			GameParams.debug=true;
		
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
		return GameParams.menu;
	}
}