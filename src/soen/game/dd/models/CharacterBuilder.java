package soen.game.dd.models;

/**
 * This class implements builder pattern
 * 
 * @author Usman
 *
 */
public abstract class CharacterBuilder extends Character {
	/**
	 * @author Munjed This the product to be constructed by the builder
	 *         (character fighter type)
	 */
	protected Character fighterTypeProduct;

	/**
	 * @author Munjed This the product to be character fighter type product
	 * @return fighterTypeProduct
	 */
	public Character getCharacter() {
		fighterTypeProduct.callSetChanged();
		return fighterTypeProduct;
	}

	/**
	 * This method create new fighter character
	 * 
	 * @param character
	 */
	public void createNewCharacter(Character character) {
		fighterTypeProduct = character;
	}

	/**
	 * Abstract method of setAbilityScores
	 */
	public abstract void setAbilityScores();
}
