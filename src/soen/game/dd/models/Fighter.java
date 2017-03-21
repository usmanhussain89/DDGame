package soen.game.dd.models;

/**
 * This class create different types of fighter using builder pattern
 * 
 * @author Usman
 *
 */
public class Fighter {
	private CharacterBuilder characterBuilder;
	
	/**
	 * This method set the type of character
	 * @param cb
	 */
	public void setCharacterBuilder(CharacterBuilder cb) {
		this.characterBuilder = cb;
	}
	
	/**
	 * This method return the character
	 * @return
	 */
	public Character getCharacter() {
		return characterBuilder.getCharacter();
	}
	
	/**
	 * This method create the new fighter
	 */
	public void createFighter(Character fighter) {
		characterBuilder.createNewCharacter(fighter);
		characterBuilder.setAbilityScores();
	}
}
