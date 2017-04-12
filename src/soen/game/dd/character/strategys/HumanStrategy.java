package soen.game.dd.character.strategys;

import java.io.Serializable;

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

	public HumanStrategy(GameEngine ge) {
		this.gameEngine = ge;
	}

	@Override
	public void turn() {
		gameEngine.setCharacterMoved(0);
		while (gameEngine.getCharacterMoved() < 3) {
			try {
				Thread.sleep(100);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}

	}

	@Override
	public String toString() {
		return "HumanStrategy []" + " This is Human Strategy!";
	}

}
