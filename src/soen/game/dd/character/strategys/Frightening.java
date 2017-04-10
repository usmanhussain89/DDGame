package soen.game.dd.character.strategys;

/**
 * This class will implement the turn strategy Target runs away from character for a number of turns equal to the 
 * enchantment bonus of the weapon. 
 * @author fyounis
 *
 */
public class Frightening implements Strategy{
	
	
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


}
