
package soen.game.dd.models;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * This class for creating and modifying all the Character fighter or Monster
 * Implement Serializable in the interface so the objects of this class can be
 * saved into file
 * 
 * @author fyounis
 */
public class Character implements Serializable {
	private static final long serialVersionUID = -4450845749856986590L;
	private String name;
	private String description;
	private FighterType fighterType; // ADDED BUILD 2
	private int level;
	private double maxHitPoint;
	private NPCType npcType;
	

	private double hitPoint;
	private int armorClass;
	private int attackBonus;
	private int damageBonus;
	private int multipleAttacks;

	// items
	private Item armor;
	private Item ring;
	private Item helmet;
	private Item boots;
	private Item belt;
	private Item weapon;
	private Item shield;
	private List<Item> backpack;

	// ability scores are labeled by the name of the attributes
	private int strength;
	private int constitution;
	private int wisdom;
	private int dexterity;
	private int intelligence;
	private int charisma;

	// double were used to avoid the mismatch type error when using the floor()
	// method
	private double strengthModifier;
	private double constitutionModifier;
	private double wisdomModifier;
	private double dexterityModifier;
	private double intelligenceModifier;
	private double charismaModifier;

	public Character() {
		backpack = new ArrayList<Item>(Arrays.asList());
	}

	public Character(String name, String description, FighterType fighterType, int level, int abilityScores, int abilityModifier, int hitPoint,
			int armorClass, int attackBonus, int damageBonus, int multipleAttacks, Item armor, Item ring, Item helmet,
			Item boots, Item belt, Item weapon, Item shield) {
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
		backpack = new ArrayList<Item>(Arrays.asList());
	}

	/**
	 * @param Item
	 *            the Item to add into backpack
	 */

	public void addItemIntoBackpack(Item item) {
		if (backpack.size() < 10) {
			backpack.add(item);
		} else {
			System.out.println("The Maximum backpack size is reached: " + backpack.size());
		}
	}

	private List<Item> getEquippedItems() {
		return Arrays.asList(armor, ring, boots, weapon, shield, helmet);
	}
	
	public void setName(String name){
		this.name = name;
	}

	/**
	 * @return the backpack list of items
	 */

	public List<Item> getItemIntoBackpack() {
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
	 * @param level
	 *            the level to set
	 */
	public void setLevel(int level) {
		this.level = level;
	}
	
	
	public FighterType getFighterType(){
		return fighterType;
	}

	/**
	 * @author Munjed 
	 * This method calculates all ability scores at random using
	 * 4d6. If fighter type is different then attributes will be randomized
	 * with a little bit of bias toward players' preference. 
	 * @param abilityScores
	 *            the abilityScores to set
	 */
	public void setAbilityScores() {
		//The following loop is made sort the scores retrieved from 4d6 dices from
		//big to small so we can then manipulate their assignment to fighter type 
		//preference
		
		int[] die = new int[6];
		for(int i=0; i<6;i++)
			die[i]=roll4d6();
		for(int i=0;i<6;i++){
			int max=die[i];
			int index=i;
			for(int j=i;j<6;j++){
				if(max<die[j]){
					max=die[j];
					index=j;
				}
			}
			int temp=die[i];
			die[i]=max;
			die[index]=temp;
		}
		//The addition for the first three attribute is to make sure that the built character
		// is actually with respect to preference in case numbers randomly were the same
		
		//BULLY implementation		
		if(getFighterType()==FighterType.BULLY){
			this.strength = die[0]+4;
			this.constitution = die[1]+2;
			this.dexterity = die[2]+1;
			this.intelligence = die[3];
			this.charisma = die[4];
			this.wisdom = die[5];
		}
		
		//NIMBLE implementation		
		else if(getFighterType()==FighterType.NIMBLE){
			this.dexterity = die[0]+4;
			this.constitution = die[1]+2;
			this.strength= die[2]+1;
			this.intelligence = die[3];
			this.charisma = die[4];
			this.wisdom = die[5];
		}
		
		//TANK implementation		
		else if(getFighterType()==FighterType.NIMBLE){
			this.constitution = die[0]+4;
			this.dexterity = die[1]+2;
			this.strength= die[2]+1;
			this.intelligence = die[3];
			this.charisma = die[4];
			this.wisdom = die[5];
		}
		
		//Default randomized character
		else{
			this.strength = roll4d6();
			this.dexterity = roll4d6();
			this.wisdom = roll4d6();
			this.intelligence = roll4d6();
			this.charisma = roll4d6();
			this.constitution = roll4d6();
		}

	}

	/**
	 * @author Munjed This method finds the ability modifier with respect to d&d
	 *         20d every modifier is based on its corresponding attribute The
	 *         equation is: Mod=RoundDown((attr-10)/2)
	 * @param abilityModifier
	 *            the abilityModifier to set
	 */
	public void setAbilityModifier() {
		// creating modifiers from ability scores
		this.strengthModifier = Math.floor((strength - 10) / 2);
		this.charismaModifier = Math.floor((charisma - 10) / 2);
		this.intelligenceModifier = Math.floor((intelligence - 10) / 2);
		this.wisdomModifier = Math.floor((wisdom - 10) / 2);
		this.constitutionModifier = Math.floor((constitution - 10) / 2);
		this.dexterityModifier = Math.floor((dexterity - 10) / 2);
	}

	/**
	 * @author Munjed 
	 * This method consider the fighter class only, in the 2nd
	 * build it will be\ adjusted to stretch other classes.... each
	 * class has different way of calculating their maxHitpoints in 20d dnd
	 * @return the maxhitPoint
	 */
	public void setMaxHitPoint() {
		maxHitPoint=10+constitutionModifier;
		for(int i=1;i<level;i++)
		{
			maxHitPoint+=constitutionModifier+roll1d10();
		}	
	}
	/**
	 * @author Munjed
	 * @return maxHitPoint potions hp increase can not exceed maxhitpoint
	 */
	
	public double getMaxHitPoint() {
		
		return maxHitPoint;
	}
	
	/**
	 * @author Munjed
	 * This method will be invoked each time a map is cleared unlness
	 * the player is less than 20
	 */
	public void levelUp(){
		if(level!=20){
			level++;
			maxHitPoint=getMaxHitPoint()+constitutionModifier+roll1d10();
		}
	}
	
	/**
	 * @author Munjed
	 * @return HitPoint if zero the character dies
	 */
	public double getHitPoint() {
		return hitPoint;
	}
	/**
	 * @author Munjed This method, although gets the value for armor class, it
	 *         also calculates it
	 * @param armorClass
	 *            integer that represents the armor class
	 * @return the armorClass dexterity + all armor modifiers in equipment
	 */

	public int getArmorClass() {
		ArrayList<Item> listItem = (ArrayList<Item>) getEquippedItems();
		armorClass = (int) (10 + dexterityModifier);
		for (Item item : listItem) {
			if (item.getEnhancedAttribute() == CharacterAttribute.ARMOR_CLASS) {
				armorClass += item.getBonusAmount();
			}
		}
		return armorClass;
	}

	/**
	 * @return the attackBonus
	 */
	public int getAttackBonus() {
		return attackBonus;
	}

	/**
	 * @param attackBonus
	 *            the attackBonus to set
	 */
	public void setAttackBonus() {
		if (weapon.getWeaponType() == "melee")
			this.attackBonus = (int) (level + weapon.getBonusAmount() + strengthModifier);
		else if (weapon.getWeaponType() == "ranged")
			this.attackBonus = (int) (level + weapon.getBonusAmount() + dexterityModifier);
	}

	/**
	 * @return the damageBonus
	 */
	public int getDamageBonus() {
		if (weapon.getWeaponType() == "melee") {
			return this.damageBonus = (int) (strengthModifier + weapon.getBonusAmount());
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
	 * @param multipleAttacks
	 *            the multipleAttacks to set
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

	/**
	 * @author Munjed
	 * @param armor
	 *            the armor to set
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

	/**
	 * @author Munjed
	 * @param ring
	 *            the ring to set
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
	 * @param helmet
	 *            the helmet to set
	 */

	public void setHelmet(Item item) {
		if (item.getItemType() == ItemType.HELMET) {
			this.helmet = item;
		} else {
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
	 * @param boots
	 *            the boots to set
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
	 * @param belt
	 *            the belt to set
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
	 * @param sword
	 *            the sword to set
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
	 * @param shield
	 *            the shield to set
	 */

	public void setShield(Item item) {
		if (item.getItemType() == ItemType.SHIELD)
			this.shield = item;
		else
			System.out.println("Inavalid type: item is not a shield");
	}

	/**
	 * @author Munjed Roll dice method, uses 4d6 and returns the score
	 * @para score Score is the number obtained from rolling the d6 4 times then
	 *       summing the maximum 3 outcomes
	 * @return the dice score for 4d6 implementation
	 */
	public int roll4d6() {
		// Roll the dice by setting each of the dice to be
		// a random number between 1 and 6.
		int[] die = new int[4];
		die[0] = (int) (Math.random() * 6) + 1;
		die[1] = (int) (Math.random() * 6) + 1;
		die[2] = (int) (Math.random() * 6) + 1;
		die[3] = (int) (Math.random() * 6) + 1;
		int min = die[1];
		for (int i = 1; i <= 3; i++) {
			if (min > die[i])
				min = die[i];
		}
		int score = (die[0] + die[1] + die[2] + die[3] - min);

		return score;
	}
	public int roll1d10(){
		
		int score = (int) (Math.random() * 10) + 1;
		
		return score;
		
	}

	/**
	 * @author Munjed
	 *
	 */

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "Character [name=" + name + ", description=" + description + ", level=" + level + ", ability Scores="
				+ " strength	constitution	wisdom	dexterity	intelligence	charisma: " + " " + strength + " "
				+ constitution + " " + wisdom + " " + dexterity + " " + intelligence + " " + charisma
				+ ", ability modifiers="
				+ " strengthModifier	constitutionModifier	wisdomModifier	dexterityModifier	intelligenceModifier	charismaModifier "
				+ " " + strengthModifier + " " + constitutionModifier + " " + wisdomModifier + " " + dexterityModifier
				+ " " + intelligenceModifier + " " + charismaModifier + " " + ", hitPoint=" + hitPoint + ", armorClass="
				+ armorClass + ", attackBonus=" + attackBonus + ", damageBonus=" + damageBonus + ", multipleAttacks="
				+ multipleAttacks + ", armor=" + armor + ", ring=" + ring + ", helmet=" + helmet + ", boots=" + boots
				+ ", belt=" + belt + ", weapon=" + weapon + ", shield=" + shield + "]";
	}
	
	/**
	 * This method set the type of Non Player Character on the map
	 * @param npcType
	 */
	public void setNPCType(NPCType npcType) {
		this.npcType = npcType;
	}
	
	/**
	 * This method get the Non Player CharacterType
	 * 
	 * @return NPCType
	 */
	public NPCType getNPCType()
	{
		return this.npcType;
	}
}
