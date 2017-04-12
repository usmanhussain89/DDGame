package soen.game.dd.character.strategys;

import soen.game.dd.models.Character;

/**
 * This class will implement the turn strategy Freezing. Target cannot move for
 * a number of turns equal to the enchantment bonus of the weapon.
 * 
 * @author fyounis
 * @author khaled
 *
 */
public class FrozenStrategy implements Strategy {

	private int remainingTurns; // equal to the enchantment bonus of the weapon.
	private Character character;
	private Strategy oldStrategy;

	public FrozenStrategy(Character c, int remainingTurns) {
		oldStrategy = c.getStrategy();
		this.remainingTurns = remainingTurns;
	}

	@Override
	public void turn() {
		if (remainingTurns-- == 0) {
			character.setStrategy(oldStrategy);
			character.setCharacterStatus(null);
		}
	}

	@Override
	public String toString() {
		return "The Strategy is changed to " + "This is a Freezing Strategy";
	}

}
