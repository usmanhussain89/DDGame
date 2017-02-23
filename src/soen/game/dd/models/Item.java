package soen.game.dd.models;

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
	
	public boolean isValid(){
		if (this.bonusAmount > 5 || this.bonusAmount < 1){
			return false;
		}
		if (this.itemType.attributes
	}
	
	

}
