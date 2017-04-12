package soen.game.dd.character.strategys;

import java.io.Serializable;

import soen.game.dd.models.Character;

/**
 * This class will implement the turn strategy Freezing. Target cannot move for
 * a number of turns equal to the enchantment bonus of the weapon.
 * 
 * @author fyounis
 * @author khaled
 *
 */
public class FrozenStrategy implements Strategy, Serializable {

	private static final long serialVersionUID = 1L;
	private int remainingTurns; // equal to the enchantment bonus of the weapon.
	private Character character;
	private Strategy oldStrategy;

	/**
	 * This is the constructor for Frozen Strategy
	 * 
	 * @param c
	 * @param remainingTurns
	 */
	public FrozenStrategy(Character c, int remainingTurns) {
		oldStrategy = c.getStrategy();
		this.remainingTurns = remainingTurns;
	}

	/**
	 * This method implement turn mechanism for Frozen Strategy
	 */
	@Override
	public void turn() {
		if (remainingTurns-- == 0) {
			character.setStrategy(oldStrategy);
			character.setCharacterStatus(null);
		}
	}

	/**
	 * This method return old strategy
	 * 
	 * @return
	 */
	public Strategy getOldStrategy() {
		return oldStrategy;
	}

	/**
	 * This method return String
	 * 
	 * @return
	 */
	@Override
	public String toString() {
		return "The Strategy is changed to " + "This is a Freezing Strategy";
	}

}
