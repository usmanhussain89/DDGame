package soen.game.dd.character.strategys;

/**
 * This class will implement the turn strategy Freezing.
 * Target cannot move for a number of turns equal to the enchantment bonus of 
 * the weapon.
 * @author fyounis
 *
 */
public class Freezing implements Strategy{
	
	private int FrozenTurns; // equal to the enchantment bonus of the weapon.

	

	@Override
	public void turn() {
		// TODO Auto-generated method stub
		FrozenTurns --;
		
	}
	
	/**
	 * @param frozenTurns the frozenTurns to set
	 */
	public void setFrozenTurns(int frozenTurns) {
		FrozenTurns = frozenTurns;
	}
	
	
	
	
}
