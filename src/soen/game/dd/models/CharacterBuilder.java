package soen.game.dd.models;

public abstract class CharacterBuilder extends Character {
	/**
	 * @author Munjed
	 * This the product to be constructed by the builder (character fighter type) 
	 */
	protected Character fighterTypeProduct; 
	/**
	 * @author Munjed
	 * This the product to be character fighter type product
	 * @return fighterTypeProduct
	 */
	public Character getCharacter(){
		return fighterTypeProduct;
	}
	
	public void createNewCharacter(){
		fighterTypeProduct= new Character();
	}
	public abstract void setAbilityScores();

}
