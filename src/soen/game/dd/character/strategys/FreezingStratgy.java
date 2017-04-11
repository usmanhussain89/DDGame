package soen.game.dd.character.strategys;

import soen.game.dd.models.GameEngine;

/**
 * This class will implement the turn strategy Freezing. Target cannot move for
 * a number of turns equal to the enchantment bonus of the weapon.
 * 
 * @author fyounis
 *
 */
public class FreezingStratgy implements Strategy {

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.lang.Object#toString()
	 */

	private int FrozenTurns; // equal to the enchantment bonus of the weapon.

	/**
	 * @param frozenTurns
	 *            the frozenTurns to set
	 */
	public void setFrozenTurns(int frozenTurns) {
		FrozenTurns = frozenTurns;
	}

	@Override
	public String toString() {
		return "Freezing [FrozenTurns=" + FrozenTurns + "]" + "This is a Freezing Strategay";
	}

	@Override
	public void turn(GameEngine engin) {
		// TODO Auto-generated method stub

	}

}
