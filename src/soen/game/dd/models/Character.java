

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
	//private int abilityScores;
	//private int abilityModifier;
	private double hitPoint;
	private int armorClass;
	private int attackBonus;
	private int damageBonus;
	private int multipleAttacks;
	
	private Item armor;
	private Item ring;
	private Item helmet;
	private Item boots;
	private Item belt;
	private Item weapon;
	private Item shield;
	private List<Item> backpack;
	
	//ability scores are labeled by the name of the attributes
	private int stregth;
	private int constitution;
	private int wisdom;
	private int dexterity;
	private int intelligence;
	private int charisma;
	
	//double were used to avoid the mismatch type error when using the floor() method
	private double stregthModifier;
	private double constitutionModifier;
	private double wisdomModifier;
	private double dexterityModifier;
	private double intelligenceModifier;
	private double charismaModifier;
	
	
	
	
	public Character(String name, String description, int level, int abilityScores, int abilityModifier, int hitPoint,
			int armorClass, int attackBonus, int damageBonus, int multipleAttacks, Item armor, Item ring,
			Item helmet, Item boots, Item belt, Item weapon, Item shield) {
		super();
		this.name = name;
		this.description = description;
		this.level = level;
		//this.abilityScores = abilityScores;
		//this.abilityModifier = abilityModifier;
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
	
	private List<Item> getEquippedItems(){
		return Arrays.asList(armor, ring, boots, weapon, shield);
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
	/* This seems not correct, you need each ability score not a total score
	/**
	 * @author Munjed
	 * @return the abilityScores
	 /
	public int getAbilityScores() {
		//abilityScores=roll()+getAbilityModifier();
		return abilityScores;
	}*/

	/**
	 * @param abilityScores the abilityScores to set
	 */
	public void setAbilityScores() {
		//using 4d6 from method die4d6()
		this.stregth=roll4d6();
		this.dexterity=roll4d6();
		this.wisdom=roll4d6();
		this.intelligence=roll4d6();
		this.charisma=roll4d6();
		this.constitution=roll4d6();	
	}
	/* This seems not correct, you need each abilitymodifier score not a total score
	/**
	 * @return the abilityModifier
	 /
	public int getAbilityModifier() {
		return abilityModifier;
	}*/
	/**
	 * @author Munjed
	 * @param abilityModifier the abilityModifier to set
	 */
	public void setAbilityModifier() {
		//creating modifiers from ability scores
		this.stregthModifier=Math.floor((stregth-10)/2);
		this.charismaModifier=Math.floor((charisma-10)/2);
		this.intelligenceModifier=Math.floor((intelligence-10)/2);
		this.wisdomModifier=Math.floor((wisdom-10)/2);
		this.constitutionModifier=Math.floor((constitution-10)/2);
		this.dexterityModifier=Math.floor((dexterity-10)/2);
	}
	/**@author Munjed
	 * @return the hitPoint
	 */
	public double getHitPoint() {
		// in the future this should be <public double getHitPoint(character.class)>
		//1st find all con modifier from:
		//a) class, if fighter we use highest 1d10 for 1st class
		//b) then we use the average of 1d10 rounded up=6 + con Mod for each level-up
		hitPoint=(10+constitutionModifier)+(level-1)*(6+constitutionModifier);
		return hitPoint;
	}
	/*
	 * @param hitPoint the hitPoint to set
	 *
	public void setHitPoint(int hitPoint) {
		this.hitPoint = hitPoint;
	}*/
	/**
	 * @return the armorClass dexterity + all armor modifiers in equipment
	 */
	public int getArmorClass() {
		
		return armorClass;
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
	public void setAttackBonus() {	
		if(weapon.getWeaponType()=="melee")
			this.attackBonus = (int) (level+weapon.getBonusAmount()+stregthModifier);
		else if(weapon.getWeaponType()=="ranged")
			this.attackBonus = (int) (level+weapon.getBonusAmount()+dexterityModifier);
	}
	
	
	/**
	 * @return the damageBonus
	 */
	public int getDamageBonus() {
		if(weapon.getWeaponType()=="melee"){
			return this.damageBonus=(int)(stregthModifier+weapon.getBonusAmount());
		}
		return 0;
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
	 * @return the armor
	 */
	public Item getarmor() {
		return armor;
	}
	/**@author Munjed
	 * @param armor the armor to set
	 */

	public void setarmor(Item item) {
		if (item.getItemType() == ItemType.ARMOR)
			this.armor = item;
		else 
			System.out.println("Inavalid type: item is not an armor");
	}
	/**
	 * @return the ring
	 */
	public Item getRing() {
		return ring;
	}
	
	/**@author Munjed
	 * @param ring the ring to set
	 */

	public void setRing(Item item) {
		if (item.getItemType() == ItemType.RING)
			this.ring = item;
		else 
			System.out.println("Inavalid type: item is not an armor");
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

	public void setHelmet(Item item) {
		if (item.getItemType() == ItemType.HELMET) {
			this.helmet = item;
		}
		else {
			System.out.println("Inavalid type: item is not a helmet");
		}
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

	public void setBoots(Item item) {
		if (item.getItemType() == ItemType.BOOTS)
			this.boots = item;
		else 
			System.out.println("Inavalid type: item is not boots");
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
	public void setBelt(Item item) {
		if (item.getItemType() == ItemType.BELT)
			this.belt = item;
		else 
			System.out.println("Inavalid type: item is not a belt");
	}
	/**
	 * @return the sword
	 */
	public Item getWeapon() {
		return weapon;
	}
	/**
	 * @param sword the sword to set
	 */

	public void setWeapon(Item item) {
		if (item.getItemType() == ItemType.WEAPON)
			this.weapon = item;
		else 
			System.out.println("Inavalid type: item is not a weapon");
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

	public void setShield(Item item) {
		if (item.getItemType() == ItemType.BOOTS)
			this.shield = item;
		else 
			System.out.println("Inavalid type: item is not a shield");
	}
	
	/**
	 * @author Munjed
	 * Roll dice method, uses 4d6 and returns the score
	 * @para score Score is the number obtained from rolling the d6 4 times then summing the maximum 3 outcomes
	 * @return the dice score for 4d6 implementation
	 */
	public int roll4d6(){
		  // Roll the dice by setting each of the dice to be
        // a random number between 1 and 6.
		int[] die = new int[4];
    die[0] = (int)(Math.random()*6) + 1;
    die[1] = (int)(Math.random()*6) + 1;
    die[2] = (int)(Math.random()*6) + 1;
    die[3] = (int)(Math.random()*6) + 1;
    int min=die[1];
    for(int i=1;i<=3;i++){
    	if(min>die[i])
    		min=die[i];
    }
    int score=(die[0]+die[1]+die[2]+die[3]-min);
		
		return score;
	}
	
	/**
	 *@author Munjed
	 *
	 */
	
	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "Character [name=" + name + ", description=" + description + ", level=" + level + ", ability Scores="
				+ " stregth	constitution	wisdom	dexterity	intelligence	charisma: "
				+" "+ stregth	+" "+ constitution	+" "+ wisdom	+" "+ dexterity+" "+ 	intelligence +" "+ charisma + ", ability modifiers=" 
				+" stregthModifier	constitutionModifier	wisdomModifier	dexterityModifier	intelligenceModifier	charismaModifier "
				+" "+stregthModifier+" "+constitutionModifier	+" "+wisdomModifier	+" "+dexterityModifier	+" "+intelligenceModifier	+" "+charismaModifier+" "
				+ ", hitPoint=" + hitPoint + ", armorClass="
				+ armorClass + ", attackBonus=" + attackBonus + ", damageBonus=" + damageBonus + ", multipleAttacks="
				+ multipleAttacks + ", armor=" + armor + ", ring=" + ring + ", helmet=" + helmet + ", boots=" + boots
				+ ", belt=" + belt + ", weapon=" + weapon + ", shield=" + shield + "]";
	}
	
	
	

}


