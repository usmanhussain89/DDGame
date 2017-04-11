package soen.game.dd.character.strategys;

import java.awt.Point;
import java.util.List;
import java.util.Random;

import soen.game.dd.models.GameEngine;
import soen.game.dd.models.Character;


/**
 * This class will implement the turn strategy for Aggressive NPC
 * 
 * @author fyounis
 * @author khaled
 */
public class AggressiveNPCStrategy implements Strategy{
	
	GameEngine gameEngine;
	Character character;
	
	
	public AggressiveNPCStrategy(Character c, GameEngine ge){
		this.gameEngine = ge;
		this.character = c;
	}
	
	@Override
	public void turn() {
		
	}

}
