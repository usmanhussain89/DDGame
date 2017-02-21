/**
 * This class for creating and modifying all the Character fighter or Monster
 * Implement  Serializable in the interface so the objects of this class can be saved into file
 * @author fyounis
 */

package soen.game.dd.models;


import java.io.Serializable;

public class Character implements Serializable {

	private int level;
	private int abilityScores;
	private int abilityModifier;
	private int hitPoint;
	private int armorClass;
	private int attackBonus;
	private int damageBonus;
	private int multipleAttacks;
	private ItemType armore;
	private ItemType ring;
	private ItemType helmet;
	private ItemType boots;
	private ItemType belt;
	private ItemType sword;
	private ItemType shield;
	

}
