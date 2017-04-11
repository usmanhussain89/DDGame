package soen.game.dd.character.strategys;

import soen.game.dd.models.GameEngine;

/**
 * This class will implement the Human or player strategy 
 * @author fyounis
 *
 */
public class HumanStrategy implements Strategy{
	
	GameEngine gameEngine;

	public HumanStrategy(GameEngine ge){
		this.gameEngine = ge;
	}
	
	/**
	 * @author kelbadawi
	 * This Method will play the game in its turn
	 */
	@Override
	public void turn() {
		gameEngine.setCharacterMoved(0);
		while(gameEngine.getCharacterMoved() < 3){
			try {
				Thread.sleep(100);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
		
	}
	
	

}
