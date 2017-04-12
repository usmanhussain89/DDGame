package soen.game.dd.character.strategys;

import soen.game.dd.models.GameEngine;

import java.awt.Point;

import soen.game.dd.models.Character;

/**
 * This class will implement the turn strategy Target runs away from character for a number of turns equal to the 
 * enchantment bonus of the weapon. 
 * @author fyounis
 * @author khaled
 */
public class FrightenedStrategy implements Strategy{
	
	
	GameEngine gameEngine;
	Character character;
	int remainingTurns;
	Strategy oldStrategy;

	public FrightenedStrategy(Character c, GameEngine ge, int remainingTurns){
		this.gameEngine = ge;
		this.character = c;
		this.remainingTurns = remainingTurns;
		this.oldStrategy = c.getStrategy();
	}

	@Override
	public void turn() {
		if (remainingTurns-- > 0){
			for (int i = 0; i < 3; ++i){
				boolean success = moveHorizontal();
				if (! success) {
					moveVertical();
				}
			}
		} else {
			character.setStrategy(oldStrategy);
		}
	}
	
	private Point getFrightenedPosition(){
		return gameEngine.getPositionOfCharacter(character);
	}
	
	private Point getPlayerPosition(){
		return gameEngine.getPositionOfCharacter(gameEngine.getCharacter());
	}
	
	private boolean moveHorizontal(){
		int offset;
		if (getFrightenedPosition().getX() - getPlayerPosition().getX() > 0){
			offset = 1;
		} else {
			offset = -1;
		}
	
		int x = (int) getFrightenedPosition().getX() + offset;
		int y = (int) getFrightenedPosition().getY();
		if (gameEngine.isMoveValid(x, y)){
			gameEngine.move(character, x, y);
			return true;
		} else {
			return false;
		}
	}

	private boolean moveVertical(){
		int offset;
		if (getFrightenedPosition().getY() - getPlayerPosition().getY() > 0){
			offset = 1;
		} else {
			offset = -1;
		}
	
		int x = (int) getFrightenedPosition().getX();
		int y = (int) getFrightenedPosition().getY() + offset;
		if (gameEngine.isMoveValid(x, y)){
			gameEngine.move(character, x, y);
			return true;
		} else {
			return false;
		}
	}

	@Override
	public String toString() {
		return "Frightening [runAwayTurns=" + remainingTurns + "]" + " I am so scared I am running away like a sissy";
	}
}
