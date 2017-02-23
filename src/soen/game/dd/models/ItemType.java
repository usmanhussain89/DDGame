package soen.game.dd.models;

/**
 * Represents each item: Helmet, Armor, Shield, etc. Each item type must define
 * within it the attributes it is allowed to change. Helmet is shown as an
 * example.
 * 
 * @author kelbadawi
 *
 */
public enum ItemType {

	HELMET() {

		CharacterAttribute[] attributes = { CharacterAttribute.INTELLIGENCE, CharacterAttribute.WISDOM,
				CharacterAttribute.ARMOR_CLASS };

	},

	ARMOR() {
		CharacterAttribute[] attributes = { CharacterAttribute.ARMOR_CLASS };
	},

	SHIELD() {
		CharacterAttribute[] attributes = { CharacterAttribute.ARMOR_CLASS };
	},

	RING() {
		CharacterAttribute[] attributes = { CharacterAttribute.ARMOR_CLASS, CharacterAttribute.CONSTITUTION,
				CharacterAttribute.WISDOM, CharacterAttribute.CHARISMA };
	},
	BELT() {
		CharacterAttribute[] attributes = { CharacterAttribute.CONSTITUTION, CharacterAttribute.STRENGTH };
	},
	BOOTS() {
		CharacterAttribute[] attributes = { CharacterAttribute.ARMOR_CLASS, CharacterAttribute.DEXTERITY };
	},
	WEAPON() {
		CharacterAttribute[] attributes = { CharacterAttribute.ATTACK_BONUS, CharacterAttribute.DAMAGE_BONUS };
	},

}
