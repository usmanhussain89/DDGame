package soen.game.dd.models;

import java.util.List;

/**
 * This class set ability score for NimbleCharacter
 * 
 * @author Usman
 *
 */
public class NimbleCharacterBuilder extends CharacterBuilder {

	private static final long serialVersionUID = 1L;

	/**
	 * This method set ability scores for Nimble Character
	 */
	public void setAbilityScores() {
		// The following loop is made sort the scores retrieved from 4d6 dices
		// from
		// big to small so we can then manipulate their assignment to fighter
		// type
		// preference

		int[] die = new int[6];
		int average = 0;
		for (int i = 0; i < 6; i++) {
			die[i] = roll4d6();
			average += die[i];
		}
		for (int i = 0; i < 6; i++) {
			int max = die[i];
			int index = i;
			for (int j = i; j < 6; j++) {
				if (max < die[j]) {
					max = die[j];
					index = j;
				}
			}
			int temp = die[i];
			die[i] = max;
			die[index] = temp;
		}
		average = average / 3;
		fighterTypeProduct.dexterity = die[0] * 18 / die[0];
		fighterTypeProduct.constitution = die[1] * 18 / die[0];
		fighterTypeProduct.strength = die[2] * 18 / die[0];
		fighterTypeProduct.intelligence = die[3] * 18 / die[0];
		fighterTypeProduct.charisma = die[4] * 18 / die[0];
		fighterTypeProduct.wisdom = die[5] * 18 / die[0];

		List<Item> listItem = fighterTypeProduct.getEquippedItems();

		for (Item item : listItem) {
			if (item.getEnhancedAttribute() == CharacterAttribute.CHARISMA) {
				fighterTypeProduct.charisma += item.getBonusAmount();
			}
		}

		for (Item item : listItem) {
			if (item.getEnhancedAttribute() == CharacterAttribute.CONSTITUTION) {
				fighterTypeProduct.constitution += item.getBonusAmount();
			}
		}

		for (Item item : listItem) {
			if (item.getEnhancedAttribute() == CharacterAttribute.INTELLIGENCE) {
				fighterTypeProduct.intelligence += item.getBonusAmount();
			}
		}

		for (Item item : listItem) {
			if (item.getEnhancedAttribute() == CharacterAttribute.DEXTERITY) {
				fighterTypeProduct.dexterity += item.getBonusAmount();
			}
		}

		for (Item item : listItem) {
			if (item.getEnhancedAttribute() == CharacterAttribute.CONSTITUTION) {
				fighterTypeProduct.constitution += item.getBonusAmount();
			}
		}

		for (Item item : listItem) {
			if (item.getEnhancedAttribute() == CharacterAttribute.DAMAGE_BONUS) {
				fighterTypeProduct.damageBonus += item.getBonusAmount();
			}
		}

		for (Item item : listItem) {
			if (item.getEnhancedAttribute() == CharacterAttribute.STRENGTH) {
				fighterTypeProduct.strength += item.getBonusAmount();
			}
		}

		for (Item item : listItem) {
			if (item.getEnhancedAttribute() == CharacterAttribute.INTELLIGENCE) {
				fighterTypeProduct.intelligence += item.getBonusAmount();
			}
		}

		for (Item item : listItem) {
			if (item.getEnhancedAttribute() == CharacterAttribute.WISDOM) {
				fighterTypeProduct.wisdom += item.getBonusAmount();
			}

		}

		setChanged();
	}
}