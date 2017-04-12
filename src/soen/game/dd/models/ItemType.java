package soen.game.dd.models;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Represents each item type: Helmet, Armor, Shield, etc. Each item type must
 * define within it the attributes it is allowed to change.
 * 
 * @author fyounis
 *
 */
public enum ItemType {
	HELMET(new ArrayList<CharacterAttribute>(
			Arrays.asList(CharacterAttribute.INTELLIGENCE, CharacterAttribute.WISDOM, CharacterAttribute.ARMOR_CLASS))),

	ARMOR(new ArrayList<CharacterAttribute>(Arrays.asList(CharacterAttribute.ARMOR_CLASS))),

	SHIELD(new ArrayList<CharacterAttribute>(Arrays.asList(CharacterAttribute.ARMOR_CLASS))),

	RING(new ArrayList<CharacterAttribute>(
			Arrays.asList(CharacterAttribute.ARMOR_CLASS, CharacterAttribute.WISDOM, CharacterAttribute.CHARISMA))),

	BELT(new ArrayList<CharacterAttribute>(
			Arrays.asList(CharacterAttribute.CONSTITUTION, CharacterAttribute.STRENGTH))),

	BOOTS(new ArrayList<CharacterAttribute>(
			Arrays.asList(CharacterAttribute.ARMOR_CLASS, CharacterAttribute.DEXTERITY))),

	WEAPON(new ArrayList<CharacterAttribute>(
			Arrays.asList(CharacterAttribute.ATTACK_BONUS, CharacterAttribute.DAMAGE_BONUS)));

	private List<CharacterAttribute> allowedAttributes;

	ItemType(List<CharacterAttribute> aA) {
		allowedAttributes = aA;
	}

	/**
	 * This method return Allowed Attributes
	 * 
	 * @return
	 */
	public List<CharacterAttribute> getAllowedAttributes() {
		return allowedAttributes;
	}

}
