package soen.game.dd.models;

import java.util.ArrayList;
import java.util.Arrays;

public class NPC extends Character{
	private NPCType NPCType;
	
	/**
	 * @author Munjed
	 * The default constructor it extend character attributes to define the type of character
	 * @param NPC
	 */
	
	public NPC(String name, String description, FighterType fighterType, int level, int abilityScores, int abilityModifier, int hitPoint,
			int armorClass, int attackBonus, int damageBonus, int multipleAttacks, Item armor, Item ring, Item helmet,
			Item boots, Item belt, Item weapon, Item shield,NPCType NPCType) {
		super();
		this.name = name;
		this.description = description;
		this.fighterType = fighterType;
		this.level = level;
		this.hitPoint = hitPoint;
		this.armorClass = armorClass;
		this.attackBonus = attackBonus;
		this.damageBonus = damageBonus;
		this.multipleAttacks = multipleAttacks;
		this.armor = armor;
		this.ring = ring;
		this.helmet = helmet;
		this.boots = boots;
		this.belt = belt;
		this.weapon = weapon;
		this.shield = shield;
		this.NPCType = NPCType;
		backpack = new ArrayList<Item>(Arrays.asList());
	}
	public NPC(NPCType NPC){
		this.NPCType=NPC;
	}
}
