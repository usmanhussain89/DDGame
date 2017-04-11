package soen.game.dd.character.strategys;

import soen.game.dd.models.GameEngine;

/**
 * This class will implement the turn strategy for Aggressive NPC
 * @author fyounis
 *
 */
public class AggressiveNPCStrategy implements Strategy{
	
	GameEngine gameEngine;

	public AggressiveNPCStrategy(GameEngine ge){
		this.gameEngine = ge;
	}
	
	@Override
	public void turn() {
		System.out.println("Aggressive turn");
	}

}
