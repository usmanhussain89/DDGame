package soen.game.dd.character.strategys;

import java.io.Serializable;
import soen.game.dd.logic.RangeDetection;
import soen.game.dd.models.GameEngine;

/**
 * This class will implement the Human or player strategy
 * 
 * @author fyounis
 *
 */
public class HumanStrategy implements Strategy,Serializable {

	private static final long serialVersionUID = 1L;
	GameEngine gameEngine;
	RangeDetection range;

	public HumanStrategy(GameEngine ge) {
		this.gameEngine = ge;
		this.range = new RangeDetection(ge);
	}

	@Override
	public void turn() {
		gameEngine.setCharacterMoved(0);
		gameEngine.setCharacterLooted(0);
		gameEngine.setCharacterAttacked(0);

		while (!isRoundOver()) {
			try {
				Thread.sleep(100);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
	}

	private boolean isRoundOver(){
		boolean moveCompleted = gameEngine.getCharacterMoved() < 3;		
		boolean attackCompleted = gameEngine.getCharacterAttacked() == 1 || range.anyEnemyWithinRange(gameEngine.getCharacter()) == null;
		boolean lootCompleted = gameEngine.getCharacterLooted() == 1 || !gameEngine.withinOneSpace(gameEngine.getChestPosition(), gameEngine.getCharacterPosition());
		return moveCompleted && attackCompleted && lootCompleted;
	}
	
	@Override
	public String toString() {
		return "HumanStrategy []" + " This is Human Strategy!";
	}

}
