package soen.game.dd.character.strategys;

import java.awt.Point;
import java.io.Serializable;
import java.util.List;
import java.util.Random;

import soen.game.dd.models.GameEngine;
import soen.game.dd.logic.RangeDetection;
import soen.game.dd.models.Character;


/**
 * This class will implement the turn strategy for Aggressive NPC
 * 
 * @author fyounis
 * @author khaled
 */
public class AggressiveNPCStrategy implements Strategy,Serializable {
	
	private static final long serialVersionUID = 1L;
	GameEngine gameEngine;
	Character character;
	
	
	public AggressiveNPCStrategy(Character c, GameEngine ge){
		this.gameEngine = ge;
		this.character = c;
	}
	
	@Override
	public void turn() {
		for (int i = 0; i < 3; ++i){
			if (!isNextToPlayer()){
				boolean success = moveHorizontal();
				if (! success) {
					moveVertical();
				}
			}
			if (gameEngine.withinOneSpace(getAggressivePosition(), gameEngine.getChestPosition())){
				System.out.println("Taking chest");
				gameEngine.interactWith(character, gameEngine.getChestPosition());
			}
			attackNearbyEnemies();
		}

	}
	

	private void attackNearbyEnemies() {
		RangeDetection rangeDetection = new RangeDetection(gameEngine);
		Character nearCharacter = rangeDetection.anyEnemyWithinRange(character);
		if (nearCharacter != null) {
			System.out.println("Aggressive is attacking");
			gameEngine.attack(character, nearCharacter);
		}
	}

	private boolean isNextToPlayer(){
		Point playerPosition = getPlayerPosition();
		Point aggressivePosition = getAggressivePosition();
		
		if (((int)playerPosition.getX() == (int)aggressivePosition.getX()) &&
				(Math.abs((int)playerPosition.getY() - (int)aggressivePosition.getY()) <= 1)){
			return true;
		}
		if (((int)playerPosition.getY() == (int)aggressivePosition.getY()) &&
				(Math.abs((int)playerPosition.getX() - (int)aggressivePosition.getX()) <= 1)){
			return true;
		}
		return false;
	}
	
	private Point getAggressivePosition(){
		return gameEngine.getPositionOfCharacter(character);
	}
	
	private Point getPlayerPosition(){
		return gameEngine.getPositionOfCharacter(gameEngine.getCharacter());
	}
	
	private boolean moveHorizontal(){
		int offset;
		if (getAggressivePosition().getX() - getPlayerPosition().getX() < 0){
			offset = 1;
		} else {
			offset = -1;
		}
	
		int x = (int) getAggressivePosition().getX() + offset;
		int y = (int) getAggressivePosition().getY();
		if (gameEngine.isMoveValid(x, y)){
			gameEngine.move(character, x, y);
			return true;
		} else {
			return false;
		}
	}

	private boolean moveVertical(){
		int offset;
		if (getAggressivePosition().getY() - getPlayerPosition().getY() < 0){
			offset = 1;
		} else {
			offset = -1;
		}
	
		int x = (int) getAggressivePosition().getX();
		int y = (int) getAggressivePosition().getY() + offset;
		if (gameEngine.isMoveValid(x, y)){
			gameEngine.move(character, x, y);
			return true;
		} else {
			return false;
		}
	}

}
