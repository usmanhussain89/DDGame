package soen.game.dd.models;

import java.util.Arrays;

/**
 * Class representing an Item.
 * 
 * @author kelbadawi
 *
 */
public class Item {

	private ItemType itemType;
	private CharacterAttribute enhancedAttribute;
	private int bonusAmount;
	private String name;

	public Item(String name, ItemType itemType, CharacterAttribute enhancedAttribute, int bonusAmount) {
		this.itemType = itemType;
		this.enhancedAttribute = enhancedAttribute;
		this.bonusAmount = bonusAmount;
		this.name = name;
	}

	public Item() {
		// TODO Auto-generated constructor stub
	}

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
