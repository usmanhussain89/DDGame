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
public class HumanStrategy implements Strategy, Serializable {

	private static final long serialVersionUID = 1L;
	GameEngine gameEngine;
	RangeDetection range;

	/**
	 * This is the constructor for Human Strategy
	 * 
	 * @param ge
	 */
	public HumanStrategy(GameEngine ge) {
		this.gameEngine = ge;
		this.range = new RangeDetection(ge);
	}

	/**
	 * This method implement turn mechanism for human strategy
	 */
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

	/**
	 * This method return round over
	 * 
	 * @return
	 */
	private boolean isRoundOver() {
		boolean moveCompleted = gameEngine.getCharacterMoved() < 3;
		boolean attackCompleted = gameEngine.getCharacterAttacked() == 1
				|| range.anyEnemyWithinRange(gameEngine.getCharacter()) == null;
		boolean lootCompleted = gameEngine.getCharacterLooted() == 1
				|| !gameEngine.withinOneSpace(gameEngine.getChestPosition(), gameEngine.getCharacterPosition());
		return moveCompleted && attackCompleted && lootCompleted;
	}

	/**
	 * This method return String
	 * 
	 * @return
	 */
	@Override
	public String toString() {
		return "Human Strategy!";
	}

}
