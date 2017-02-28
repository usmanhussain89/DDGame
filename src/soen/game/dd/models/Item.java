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

	/**
	 * Checks if the item is valid. For an item to be valid: - it needs to
	 * enhance an attribute that this particular item type - it can only enhance
	 * between 1 and 5
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

}
