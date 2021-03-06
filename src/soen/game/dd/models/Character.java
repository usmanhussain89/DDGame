
package soen.game.dd.models;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Observable;

import soen.game.dd.character.strategys.Strategy;

/**
 * This class for creating and modifying all the Character fighter or Monster
 * Implement Serializable in the interface so the objects of this class can be
 * saved into file
 * 
 * @author fyounis
 */
public class Character extends Observable implements Serializable {
	private static final long serialVersionUID = -4450845749856986590L;
	protected String name;
	public String description;
	public FighterType fighterType; // ADDED BUILD 2
	public CharacterStatus characterStatus; // ADDED BUILD 3
	public int level;
	private double maxHitPoint;
	private NPCType npcType;

	// Strategy type
	private Strategy strategy; // ADDED BUILD 3

	public double hitPoint;
	public static int armorClass;
	public int attackBonus;
	public int damageBonus;
	public int multipleAttacks;

	// items
	public Item armor;
	public Item ring;
	public Item helmet;
	public Item boots;
	public Item belt;
	public Item weapon;
	public Item shield;
	protected List<Item> backpack;

	// ability scores are labeled by the name of the attributes
	protected int strength;
	protected int constitution;
	protected int wisdom;
	protected int dexterity;
	protected int intelligence;
	protected int charisma;

	// double were used to avoid the mismatch type error when using the floor()
	// method
	private double strengthModifier;
	private double constitutionModifier;
	private double wisdomModifier;
	protected double dexterityModifier;
	private double intelligenceModifier;
	private double charismaModifier;

	// Builder pattern:
	private CharacterBuilder builder; // ADDED BUILD 2
	private int burned;

	/**
	 * No argument constructor of Character class
	 */
	public Character() {
		backpack = new ArrayList<Item>(Arrays.asList());
		setMaxHitPoint();
		setHitpoint(getMaxHitPoint());
	}

	/**
	 * This is Constructor of Character class
	 * 
	 * @param name
	 * @param description
	 * @param fighterType
	 * @param level
	 * @param hitPoint
	 * @param armorClass
	 * @param attackBonus
	 * @param damageBonus
	 * @param multipleAttacks
	 * @param armor
	 * @param ring
	 * @param helmet
	 * @param boots
	 * @param belt
	 * @param weapon
	 * @param shield
	 */
	public Character(String name, String description, FighterType fighterType, int level, int hitPoint, int armorClass,
			int attackBonus, int damageBonus, int multipleAttacks, Item armor, Item ring, Item helmet, Item boots,
			Item belt, Item weapon, Item shield) {
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
	 * @return the strategy
	 */
	public Strategy getStrategy() {
		return strategy;
	}

	/**
	 * @param strategy
	 *            the strategy to set
	 */
	public void setStrategy(Strategy strategy) {
		this.strategy = strategy;
		System.out.println("<Game Logging> : The Character: " + name + " Strategy got set to: " + getStrategy());
	}

	/**
	 * @param Item
	 *            the Item to add into backpack
	 */

	public boolean addItemIntoBackpack(Item item) {
		if (backpack.size() < 10) {
			backpack.add(item);
			setChanged();
			System.out.println("<Info> : This Item: " + item.getName() + " been added to the backpack of" + name);
			return true;
		} else {
			System.out.println("<Info> : The Maximum backpack size is reached: " + backpack.size());
			return false;
		}
	}

	/**
	 * Removes an item from the backpack
	 * 
	 * @author Khaled
	 * @param pItem
	 *            Item to remove
	 * @return true if removed, false if not found
	 */
	public boolean removeItemFromBackpack(Item pItem) {
		for (Item item : backpack) {
			if (pItem.equals(item)) {
				backpack.remove(item);
				setChanged();
				System.out.println(
						"<Info> : This Item: " + item.getName() + " been removed from the backpack of " + name);
				return true;
			}
		}
		return false;
	}

	/**
	 * Removes an item from the backpack
	 * 
	 * @author Khaled
	 * @param pItem
	 *            Item to remove
	 * @return true if removed, false if not found
	 * @version Build2
	 */
	public boolean removeItemNameFromBackpack(String pItemName) {
		for (Item item : backpack) {
			if (pItemName.equals(item.getName())) {
				backpack.remove(item);
				System.out.println(
						"<Info> : This Item: " + item.getName() + " been removed from the backpack of " + name);
				return true;
			}
		}
		return false;
	}

	/**
	 * @author Khalid creating the list of equiped items only
	 * @return a list of items equiped
	 */
	protected List<Item> getEquippedItems() {
		return Arrays.asList(armor, ring, boots, weapon, shield, helmet);
	}

	public void setName(String name) {
		this.name = name;
		System.out.println("<Info> : the name of this character was set to: " + name);
	}

	public void callSetChanged() {
		setChanged();
	}

	/**
	 * @return the backpack list of items
	 */

	public List<Item> getItemIntoBackpack() {
		return backpack;
	}

	/**
	 * @author Munjed
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
	 * @return range
	 */
	public int getWeaponRange() {
		return weapon.getRange();
	}

	/**
	 * @param level
	 *            the level to set
	 */
	public void setLevel(int level) {
		this.level = level;
		System.out.println("<Info> : LEVEL of this character: " + name + " set to: " + level + ".");
	}

	/**
	 * This method return constitution
	 * 
	 * @return
	 */
	public int getConstitution() {
		return constitution;
	}

	/**
	 * This method set the constitution
	 * 
	 * @param constitution
	 */
	public void setConstitution(int constitution) {
		this.constitution = constitution;
	}

	/**
	 * This method set attack bonus
	 * 
	 * @param attackBonus
	 */
	public void setAttackBonus(int attackBonus) {
		this.attackBonus = attackBonus;
	}

	/**
	 * This method set Damage bonus
	 * 
	 * @param damageBonus
	 */
	public void setDamageBonus(int damageBonus) {
		this.damageBonus = damageBonus;
	}

	/**
	 * This method set Strength
	 * 
	 * @param strength
	 */
	public void setStrength(int strength) {
		this.strength = strength;
	}

	/**
	 * This method set Wisdom
	 * 
	 * @param wisdom
	 */
	public void setWisdom(int wisdom) {
		this.wisdom = wisdom;
	}

	/**
	 * This method set Intelligence
	 * 
	 * @param intelligence
	 */
	public void setIntelligence(int intelligence) {
		this.intelligence = intelligence;
	}

	/**
	 * This method set Charisma
	 * 
	 * @param charisma
	 */
	public void setCharisma(int charisma) {
		this.charisma = charisma;
	}

	/**
	 * 
	 * @return fighterType Nimble, Tank, or Bully
	 */

	public FighterType getFighterType() {
		return fighterType;
	}

	/**
	 * This method return list of items in back pack
	 * 
	 * @return
	 */
	public List<Item> getBackpack() {
		return backpack;
	}

	/**
	 * This method set Builder
	 * 
	 * @param newCharacterBuilder
	 */
	public void setBuilder(CharacterBuilder newCharacterBuilder) {
		builder = newCharacterBuilder;
	}

	/**
	 * @author Munjed This (BUILD 1 method is OBSELETE) calculates all ability
	 *         scores at random using 4d6. If fighter type is different then
	 *         attributes will be randomized with a little bit of bias toward
	 *         players' preference.
	 * @param abilityScores
	 *            the abilityScores to set
	 */
	/*
	 * public void setAbilityScores() { //The following loop is made sort the
	 * scores retrieved from 4d6 dices from //big to small so we can then
	 * manipulate their assignment to fighter type //preference
	 * 
	 * int[] die = new int[6]; for(int i=0; i<6;i++) die[i]=roll4d6(); for(int
	 * i=0;i<6;i++){ int max=die[i]; int index=i; for(int j=i;j<6;j++){
	 * if(max<die[j]){ max=die[j]; index=j; } } int temp=die[i]; die[i]=max;
	 * die[index]=temp; } //The addition for the first three attribute is to
	 * make sure that the built character // is actually with respect to
	 * preference in case numbers randomly were the same
	 * 
	 * //BULLY implementation if(getFighterType()==FighterType.BULLY){
	 * this.strength = die[0]+4; this.constitution = die[1]+2; this.dexterity =
	 * die[2]+1; this.intelligence = die[3]; this.charisma = die[4]; this.wisdom
	 * = die[5]; }
	 * 
	 * //NIMBLE implementation else if(getFighterType()==FighterType.NIMBLE){
	 * this.dexterity = die[0]+4; this.constitution = die[1]+2; this.strength=
	 * die[2]+1; this.intelligence = die[3]; this.charisma = die[4]; this.wisdom
	 * = die[5]; }
	 * 
	 * //TANK implementation else if(getFighterType()==FighterType.NIMBLE){
	 * this.constitution = die[0]+4; this.dexterity = die[1]+2; this.strength=
	 * die[2]+1; this.intelligence = die[3]; this.charisma = die[4]; this.wisdom
	 * = die[5]; }
	 * 
	 * //Default randomized character else{ this.strength = roll4d6();
	 * this.dexterity = roll4d6(); this.wisdom = roll4d6(); this.intelligence =
	 * roll4d6(); this.charisma = roll4d6(); this.constitution = roll4d6(); }
	 * 
	 * setChanged(); }
	 */

	/**
	 * This method return Strength
	 * 
	 * @return
	 */
	public int getStrength() {
		return this.strength;
	}

	/**
	 * This method return Constitution
	 * 
	 * @return
	 */
	public int getConsitution() {
		return this.constitution;
	}

	/**
	 * This method return Wisdom
	 * 
	 * @return
	 */
	public int getWisdom() {
		return this.wisdom;
	}

	/**
	 * This method set dexterity
	 * 
	 * @param dexterity
	 */
	public void setDexterity(int dexterity) {
		this.dexterity = dexterity;
	}

	/**
	 * This method return dexterity
	 * 
	 * @return
	 */
	public int getDexterity() {
		return this.dexterity;
	}

	/**
	 * This method return intelligence
	 * 
	 * @return
	 */
	public int getIntelligence() {
		return this.intelligence;
	}

	/**
	 * This method return charisma
	 * 
	 * @return
	 */
	public int getCharisma() {
		return this.charisma;
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
		System.out.println("<Info> : The strengthModifier was set to: " + strengthModifier);
		this.charismaModifier = Math.floor((charisma - 10) / 2);
		System.out.println("<Info> : The charismaModifier was set to: " + charismaModifier);
		this.intelligenceModifier = Math.floor((intelligence - 10) / 2);
		System.out.println("<Info> : The intelligenceModifier was set to: " + intelligenceModifier);
		this.wisdomModifier = Math.floor((wisdom - 10) / 2);
		System.out.println("<Info> : The wisdomModifier was set to: " + wisdomModifier);
		this.constitutionModifier = Math.floor((constitution - 10) / 2);
		System.out.println("<Info> : The constitutionModifier was set to: " + constitutionModifier);
		this.dexterityModifier = Math.floor((dexterity - 10) / 2);
		System.out.println("<Info> : The dexterityModifier was set to: " + dexterityModifier);
	}

	/**
	 * This method return StrengthModifier
	 * 
	 * @return
	 */
	public double getStrengthModifier() {
		return strengthModifier;
	}

	/**
	 * This method set StrengthModifier
	 * 
	 * @param strengthModifier
	 */
	public void setStrengthModifier(double strengthModifier) {
		this.strengthModifier = strengthModifier;
	}

	/**
	 * This method return Constitution Modifier
	 * 
	 * @return
	 */
	public double getConstitutionModifier() {
		return constitutionModifier;
	}

	/**
	 * This method set Constitution Modifier
	 * 
	 * @param constitutionModifier
	 */
	public void setConstitutionModifier(double constitutionModifier) {
		this.constitutionModifier = constitutionModifier;
	}

	/**
	 * This method return Wisdom Modifier
	 * 
	 * @return
	 */
	public double getWisdomModifier() {
		return wisdomModifier;
	}

	/**
	 * This method set Wisdom Modifier
	 * 
	 * @param wisdomModifier
	 */
	public void setWisdomModifier(double wisdomModifier) {
		this.wisdomModifier = wisdomModifier;
	}

	/**
	 * This method return dexterity modifier
	 * 
	 * @return
	 */
	public double getDexterityModifier() {
		return dexterityModifier;
	}

	/**
	 * This method set Dexterity Modifier
	 * 
	 * @param dexterityModifier
	 */
	public void setDexterityModifier(double dexterityModifier) {
		this.dexterityModifier = dexterityModifier;
	}

	/**
	 * This method return Intelligence Modifier
	 * 
	 * @return
	 */
	public double getIntelligenceModifier() {
		return intelligenceModifier;
	}

	/**
	 * This method set Intelligence Modifers
	 * 
	 * @param intelligenceModifier
	 */
	public void setIntelligenceModifier(double intelligenceModifier) {
		this.intelligenceModifier = intelligenceModifier;
	}

	/**
	 * This method return CharismaModifier
	 * 
	 * @return
	 */
	public double getCharismaModifier() {
		return charismaModifier;
	}

	/**
	 * This method set CharismaModifier
	 * 
	 * @param charismaModifier
	 */
	public void setCharismaModifier(double charismaModifier) {
		this.charismaModifier = charismaModifier;
	}

	/**
	 * @author Munjed This method consider the fighter class only, in the 2nd
	 *         build it will be\ adjusted to stretch other classes.... each
	 *         class has different way of calculating their maxHitpoints in 20d
	 *         dnd
	 * @return the maxhitPoint
	 */
	public void setMaxHitPoint() {
		maxHitPoint = 10 + constitutionModifier;
		System.out.println(
				"<Game Logging> : The Max HP is: " + maxHitPoint + " after applying constitution Modifier + 10");
		for (int i = 1; i < level; i++) {
			maxHitPoint += constitutionModifier + roll1d10();
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
	 * @author Munjed This method will be invoked each time a map is cleared
	 *         unlness the player is less than 20
	 */
	public void levelUp() {
		if (level != 20) {
			level++;
			maxHitPoint = getMaxHitPoint() + constitutionModifier + roll1d10();
			setAttackBonus();
		}

	}

	/**
	 * @author Munjed This created at the beginning of each map. The Hitpoint
	 * @param maxHitPoint
	 */
	public void setHitpoint(double maxHitPoint) {
		this.hitPoint = maxHitPoint;
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

	public void setArmorClass() {
		ArrayList<Item> listItem = (ArrayList<Item>) getEquippedItems();
		armorClass = (int) (10 + dexterityModifier);
		for (Item item : listItem) {
			if (item.getEnhancedAttribute() == CharacterAttribute.ARMOR_CLASS) {
				armorClass += item.getBonusAmount();
			}
		}
	}

	public static int getArmorClass() {
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
		if (weapon.getWeaponType() == WeaponType.MELEE)
			this.attackBonus = (int) (level + weapon.getBonusAmount() + strengthModifier);
		else if (weapon.getWeaponType() == WeaponType.RANGED)
			this.attackBonus = (int) (level + weapon.getBonusAmount() + dexterityModifier);
	}

	/**
	 * @return the damageBonus
	 */
	public int getDamageBonus() {
		if (weapon.getWeaponType() == WeaponType.MELEE) {
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
		if (item.getItemType() == ItemType.ARMOR) {
			this.armor = item;
			setChanged();
		} else
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
		if (item.getItemType() == ItemType.RING) {
			this.ring = item;
			setChanged();
		} else
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
			setChanged();
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
		if (item.getItemType() == ItemType.BOOTS) {
			this.boots = item;
			setChanged();
		} else
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
		if (item.getItemType() == ItemType.BELT) {
			this.belt = item;
			setChanged();
		} else
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
		if (item.getItemType() == ItemType.WEAPON) {
			this.weapon = item;
			setChanged();
		} else
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
		if (item.getItemType() == ItemType.SHIELD) {
			this.shield = item;
			setChanged();
		} else
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

	public static int roll1d10() {

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

	// BUILD 2 Setter and Getter
	/**
	 * This method set the type of Non Player Character on the map
	 * 
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
	public NPCType getNPCType() {
		return this.npcType;
	}

	// BUILD 3 Setter and Getter
	/**
	 * @author Munjed This method gets the Character Status
	 * @return characterStatus is an ENUM that could be either FROZEN, BURNED,
	 *         SLAYED, FRIGHTENED, OR Pacified
	 */
	public CharacterStatus getCharacterStatus() {
		return characterStatus;
	}

	/**
	 * @author Munjed This method sets the Character Status upon attack
	 * @param characterStatuss
	 *            is an ENUM that could be either FROZEN, BURNED, SLAYED,
	 *            FRIGHTENED, OR Pacified
	 */

	public void setCharacterStatus(CharacterStatus characterStatus) {
		this.characterStatus = characterStatus;
	}

	/**
	 * This method set Burned Counter
	 * 
	 * @param i
	 */
	public void setBurnedCounter(int i) {
		this.burned = i;
	}

	/**
	 * This method decrement Burned
	 */
	public void decrementBurned() {
		if (this.burned-- == 0) {
			this.characterStatus = null;
		}

	}
}
