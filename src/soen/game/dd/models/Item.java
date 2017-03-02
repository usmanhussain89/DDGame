package soen.game.dd.models;

import java.io.Serializable;

/**
 * Class representing an Item.
 * 
 * @author kelbadawi
 *
 */
public class Item implements Serializable{
	
	private static final long serialVersionUID = -3013661310703782558L;
	private ItemType itemType;
	private CharacterAttribute enhancedAttribute;
	private int bonusAmount;
	private String name;
	private int itemStat; //for weapon base attack and Armor wearable "armor value"
	private String weaponType; //this to determine if its a melee or ranged weapon if the item type is a Weapon o.w. its NULL

	

	public Item(String name, ItemType itemType, CharacterAttribute enhancedAttribute, int bonusAmount, int itemStat, String weaponType) {
		this.itemType = itemType;
		this.enhancedAttribute = enhancedAttribute;
		this.bonusAmount = bonusAmount;
		this.name = name;
		this.itemStat=itemStat;
		this.weaponType=weaponType;
	}
	
	public Item()
	{}


	/**
	 * Checks if the item is valid. For an item to be valid: - it needs to
	 * enhance an attribute that this particular item type had - it can only enhance
	 * between 1 and 5 bonus point
	 *
	 * @return true if item is valid, false otherwise
	 */
	public boolean isValid() {
		if (this.bonusAmount > 5 || this.bonusAmount < 1) {
			System.out.println("Bounus Amount: " + this.bonusAmount + " is not correct");
			return false;
		}

		
		if (!this.itemType.getAllowedAttributes().contains(this.enhancedAttribute)) {
			return false;
		}
		System.out.println("Item is valid");
		return true;

	}

	/**
	 * @return the itemType
	 */
	public ItemType getItemType() {
		return itemType;
	}

	/**
	 * @return the enhancedAttribute
	 */
	public CharacterAttribute getEnhancedAttribute() {
		return enhancedAttribute;
	}

	/**
	 * @return the bonusAmount
	 */
	public int getBonusAmount() {
		return bonusAmount;
	}

	/**
	 * @return the name
	 */
	public String getName() {
		return name;
	}
	
	public void setName(String name){
		this.name = name;
	}
	
	/**
	 * @author Munjed
	 * @return itemStat
	 */
	public int getItemStat() {
		return itemStat;
	}
	/**
	 * @author Munjed
	 * @param itemStat value for weapon base attack and Armor wearable "armor value"
	 */
	public void setItemStat(int itemStat) {
		this.itemStat = itemStat;
	}
	
	/**
	 * @author Munjed
	 * @return weaponType the type of the weapon, melee or ranged
	 */

	public String getWeaponType() {
		return weaponType;
	}
	
	/**
	 * @author Munjed
	 * @param weaponType is the type of weapon melee or ranged
	 */
	public void setWeaponType(String weaponType) {
		this.weaponType = weaponType;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "Item [itemType=" + itemType + ", enhancedAttribute=" + enhancedAttribute + ", bonusAmount="
				+ bonusAmount + ", name=" + name + "]";
	}

	public void setBonusAmount(Integer bonusAmount) {
		this.bonusAmount = bonusAmount;
		
	}

	public void setCharacterAttribute(CharacterAttribute characterAttribute) {
		this.enhancedAttribute = characterAttribute;
		
	}

	public void setItemType(ItemType itemType) {
		this.itemType = itemType;
	}

}
