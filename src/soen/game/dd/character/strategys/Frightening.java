package soen.game.dd.character.strategys;

import soen.game.dd.models.GameEngine;

/**
 * This class will implement the turn strategy Target runs away from character for a number of turns equal to the 
 * enchantment bonus of the weapon. 
 * @author fyounis
 *
 */
public class Frightening implements Strategy{
	
	
	GameEngine gameEngine;

	public Frightening(GameEngine ge){
		this.gameEngine = ge;
	}

	private int runAwayTurns; 	// this param will be  to the enchantment bonus of the weapon. 


	@Override
	public void turn() {
		// TODO Auto-generated method stub
		
		runAwayTurns--;
		
	}
	
	/**
	 * @param runAwayTurns the runAwayTurns to set
	 */
	public void setRunAwayTurns(int runAwayTurns) {
		this.runAwayTurns = runAwayTurns;
	}


	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "Frightening [runAwayTurns=" + runAwayTurns + "]" + " I am so scared I am running away like a sissy";
	}
}
