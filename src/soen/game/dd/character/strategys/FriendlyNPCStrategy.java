package soen.game.dd.character.strategys;

import soen.game.dd.models.GameEngine;

/**
 * This class will implement the turn strategy for friendly NPC
 * 
 * @author fyounis
 *
 */
public class FriendlyNPCStrategy implements Strategy {

	GameEngine gameEngine;

	public FriendlyNPCStrategy(GameEngine ge){
		this.gameEngine = ge;
	}
	
	@Override
	public void turn() {
		System.out.println("Friendly turn");
	}

}
