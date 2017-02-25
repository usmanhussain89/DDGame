package soen.game.dd.models;

import java.util.Arrays;

/**
 * Class representing an Item.
 * @author kelbadawi
 *
 */
public class Item {
	
	private ItemType itemType;
	private CharacterAttribute enhancedAttribute;
	private int bonusAmount;

	public Item(ItemType itemType, CharacterAttribute enhancedAttribute, int bonusAmount){
		this.itemType = itemType;
		this.enhancedAttribute = enhancedAttribute;
		this.bonusAmount = bonusAmount;
	}

	/**
	 * Checks if the item is valid. For an item to be valid:
	 * - it needs to enhance an attribute that this particular item type
	 * - it can only enhance between 1 and 5
	 *
	 * @return true if item is valid, false otherwise
	 */
	public boolean isValid(){
		if (this.bonusAmount > 5 || this.bonusAmount < 1){
			return false;
		}
		if (!Arrays.asList(this.itemType.getAllowedAttributes()).contains(this.enhancedAttribute)){
			return false;
		}
		return true;
	}
	
}
