package soen.game.dd.character.strategys;

import java.io.Serializable;

import soen.game.dd.models.GameEngine;

/**
 * This class will implement the turn strategy for the computer player
 * 
 * @author fyounis
 *
 */
public class ComputerStrategy implements Strategy,Serializable {

	private static final long serialVersionUID = 1L;
	GameEngine gameEngine;

	public ComputerStrategy(GameEngine ge){
		this.gameEngine = ge;
	}
	
	@Override
	public void turn() {
	}

}
