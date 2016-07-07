package game;

import org.newdawn.slick.*;
import org.newdawn.slick.state.*;

public class SpaceRush extends StateBasedGame {

	public SpaceRush(String gameName) {
		super(gameName);
		this.addState(new Menu(GameParams.menu));
		this.addState(new Play(GameParams.play));

	}

	public static void main(String[] args) {
		AppGameContainer appGameContainer;
		
		try{
			appGameContainer = new AppGameContainer(new SpaceRush(GameParams.gameName));
			appGameContainer.setDisplayMode(GameParams.screenX, GameParams.screenY, false);
			appGameContainer.start();
		}catch(SlickException err){
			err.printStackTrace();
		}		
	}

	@Override
	public void initStatesList(GameContainer gameContainer) throws SlickException {
		this.getState(GameParams.menu).init(gameContainer, this);
		this.getState(GameParams.play).init(gameContainer, this);
		this.enterState(GameParams.menu);
	}
}
