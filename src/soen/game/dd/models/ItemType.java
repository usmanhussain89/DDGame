package game.models;

/**
 * Represents each item: Helmet, Armor, Shield, etc.
 * Each item type must define within it the attributes it is allowed to change. Helmet is shown as an example.
 * @author kelbadawi
 *
 */
public enum ItemType {
	
	HELMET() {
		
		@Override
		public CharacterAttribute[] changeableAttributes(){
			CharacterAttribute[] attributes = {
					CharacterAttribute.INTELLIGENCE,
					CharacterAttribute.WISDOM,
					CharacterAttribute.ARMOR_CLASS
					};
			return attributes;
		}
	};

	public abstract CharacterAttribute[] changeableAttributes();
	
}
