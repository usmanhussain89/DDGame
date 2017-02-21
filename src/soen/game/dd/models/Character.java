/**
 * This class for creating and modifying all the Character fighter or Monster
 * Implement  Serializable in the interface so the objects of this class can be saved into file
 * @author fyounis
 */

package soen.game.dd.models;


import java.io.Serializable;

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
	
	private ItemType armore;
	private ItemType ring;
	private ItemType helmet;
	private ItemType boots;
	private ItemType belt;
	private ItemType sword;
	private ItemType shield;
	/**
	 * @return the name
	 */
	public String getName() {
		return name;
	}
	/**
	 * @param name the name to set
	 */
	public void setName(String name) {
		this.name = name;
	}
	/**
	 * @return the description
	 */
	public String getDescription() {
		return description;
	}
	/**
	 * @param description the description to set
	 */
	public void setDescription(String description) {
		this.description = description;
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
	public ItemType getArmore() {
		return armore;
	}
	/**
	 * @param armore the armore to set
	 */
	public void setArmore(ItemType armore) {
		this.armore = armore;
	}
	/**
	 * @return the ring
	 */
	public ItemType getRing() {
		return ring;
	}
	/**
	 * @param ring the ring to set
	 */
	public void setRing(ItemType ring) {
		this.ring = ring;
	}
	/**
	 * @return the helmet
	 */
	public ItemType getHelmet() {
		return helmet;
	}
	/**
	 * @param helmet the helmet to set
	 */
	public void setHelmet(ItemType helmet) {
		this.helmet = helmet;
	}
	/**
	 * @return the boots
	 */
	public ItemType getBoots() {
		return boots;
	}
	/**
	 * @param boots the boots to set
	 */
	public void setBoots(ItemType boots) {
		this.boots = boots;
	}
	/**
	 * @return the belt
	 */
	public ItemType getBelt() {
		return belt;
	}
	/**
	 * @param belt the belt to set
	 */
	public void setBelt(ItemType belt) {
		this.belt = belt;
	}
	/**
	 * @return the sword
	 */
	public ItemType getSword() {
		return sword;
	}
	/**
	 * @param sword the sword to set
	 */
	public void setSword(ItemType sword) {
		this.sword = sword;
	}
	/**
	 * @return the shield
	 */
	public ItemType getShield() {
		return shield;
	}
	/**
	 * @param shield the shield to set
	 */
	public void setShield(ItemType shield) {
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


