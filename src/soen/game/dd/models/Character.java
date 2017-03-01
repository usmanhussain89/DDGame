

package soen.game.dd.models;


import java.io.Serializable;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * This class for creating and modifying all the Character fighter or Monster
 * Implement  Serializable in the interface so the objects of this class can be saved into file
 * @author fyounis
 */
public class Character implements Serializable {

	private String name;
	private String description;
	private int level;
	private int abilityScores;
	private int abilityModifier;
	private int hitPoint;
	private int armorClass;
	private int attackBonus;
	private int damageBonus;
	private int multipleAttacks;
	
	private Item armore;
	private Item ring;
	private Item helmet;
	private Item boots;
	private Item belt;
	private Item sword;
	private Item shield;
	private List<Item> backpack;
	
	
	
	public Character(String name, String description, int level, int abilityScores, int abilityModifier, int hitPoint,
			int armorClass, int attackBonus, int damageBonus, int multipleAttacks, Item armore, Item ring,
			Item helmet, Item boots, Item belt, Item sword, Item shield) {
		super();
		this.name = name;
		this.description = description;
		this.level = level;
		this.abilityScores = abilityScores;
		this.abilityModifier = abilityModifier;
		this.hitPoint = hitPoint;
		this.armorClass = armorClass;
		this.attackBonus = attackBonus;
		this.damageBonus = damageBonus;
		this.multipleAttacks = multipleAttacks;
		this.armore = armore;
		this.ring = ring;
		this.helmet = helmet;
		this.boots = boots;
		this.belt = belt;
		this.sword = sword;
		this.shield = shield;
		backpack = new ArrayList<Item>(Arrays.asList());
	}

	/**
	 * @param Item the Item to add into backpack
	 */
	
	public void addItemIntoBackpack(Item item){
		if(backpack.size() < 10){
		backpack.add(item);
		}
		else {
			System.out.println("The Maximum backpack size is reached: "+backpack.size());
			}
	}
	
	/**
	 * @return the backpack list of items
	 */
	
	public List<Item> getItemIntoBackpack(){
		return backpack;
	}
	/**
	 * @return the name
	 */
	public String getName() {
		return name;
	}
	
	/**
	 * @return the description
	 */
	public String getDescription() {
		return description;
	}
	
	/**
	 * @return the level
	 */
	public int getLevel() {
		return level;
	}
	/**
	 * @param level the level to set
	 */
	public void setLevel(int level) {
		this.level = level;
	}
	/**
	 * @return the abilityScores
	 */
	public int getAbilityScores() {
		return abilityScores;
	}
	/**
	 * @param abilityScores the abilityScores to set
	 */
	public void setAbilityScores(int abilityScores) {
		this.abilityScores = abilityScores;
	}
	/**
	 * @return the abilityModifier
	 */
	public int getAbilityModifier() {
		return abilityModifier;
	}
	/**
	 * @param abilityModifier the abilityModifier to set
	 */
	public void setAbilityModifier(int abilityModifier) {
		this.abilityModifier = abilityModifier;
	}
	/**
	 * @return the hitPoint
	 */
	public int getHitPoint() {
		return hitPoint;
	}
	/**
	 * @param hitPoint the hitPoint to set
	 */
	public void setHitPoint(int hitPoint) {
		this.hitPoint = hitPoint;
	}
	/**
	 * @return the armorClass
	 */
	public int getArmorClass() {
		return armorClass;
	}
	/**
	 * @param armorClass the armorClass to set
	 */
	public void setArmorClass(int armorClass) {
		this.armorClass = armorClass;
	}
	/**
	 * @return the attackBonus
	 */
	public int getAttackBonus() {
		return attackBonus;
	}
	/**
	 * @param attackBonus the attackBonus to set
	 */
	public void setAttackBonus(int attackBonus) {
		this.attackBonus = attackBonus;
	}
	/**
	 * @return the damageBonus
	 */
	public int getDamageBonus() {
		return damageBonus;
	}
	/**
	 * @param damageBonus the damageBonus to set
	 */
	public void setDamageBonus(int damageBonus) {
		this.damageBonus = damageBonus;
	}
	/**
	 * @return the multipleAttacks
	 */
	public int getMultipleAttacks() {
		return multipleAttacks;
	}
	/**
	 * @param multipleAttacks the multipleAttacks to set
	 */
	public void setMultipleAttacks(int multipleAttacks) {
		this.multipleAttacks = multipleAttacks;
	}
	/**
	 * @return the armore
	 */
	public Item getArmore() {
		return armore;
	}
	/**
	 * @param armore the armore to set
	 */
	public void setArmore(Item armore) {
		this.armore = armore;
	}
	/**
	 * @return the ring
	 */
	public Item getRing() {
		return ring;
	}
	/**
	 * @param ring the ring to set
	 */
	public void setRing(Item ring) {
		this.ring = ring;
	}
	/**
	 * @return the helmet
	 */
	public Item getHelmet() {
		return helmet;
	}
	/**
	 * @param helmet the helmet to set
	 */
	public void setHelmet(Item helmet) {
		this.helmet = helmet;
	}
	/**
	 * @return the boots
	 */
	public Item getBoots() {
		return boots;
	}
	/**
	 * @param boots the boots to set
	 */
	public void setBoots(Item boots) {
		this.boots = boots;
	}
	/**
	 * @return the belt
	 */
	public Item getBelt() {
		return belt;
	}
	/**
	 * @param belt the belt to set
	 */
	public void setBelt(Item belt) {
		this.belt = belt;
	}
	/**
	 * @return the sword
	 */
	public Item getSword() {
		return sword;
	}
	/**
	 * @param sword the sword to set
	 */
	public void setSword(Item sword) {
		this.sword = sword;
	}
	/**
	 * @return the shield
	 */
	public Item getShield() {
		return shield;
	}
	/**
	 * @param shield the shield to set
	 */
	public void setShield(Item shield) {
		this.shield = shield;
	}
	
	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "Character [name=" + name + ", description=" + description + ", level=" + level + ", abilityScores="
				+ abilityScores + ", abilityModifier=" + abilityModifier + ", hitPoint=" + hitPoint + ", armorClass="
				+ armorClass + ", attackBonus=" + attackBonus + ", damageBonus=" + damageBonus + ", multipleAttacks="
				+ multipleAttacks + ", armore=" + armore + ", ring=" + ring + ", helmet=" + helmet + ", boots=" + boots
				+ ", belt=" + belt + ", sword=" + sword + ", shield=" + shield + "]";
	}
	
	
	

}


