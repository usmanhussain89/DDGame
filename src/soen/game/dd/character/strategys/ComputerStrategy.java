package soen.game.dd.character.strategys;

import java.awt.Point;
import java.io.Serializable;

import soen.game.dd.logic.RangeDetection;
import soen.game.dd.models.Character;
import soen.game.dd.models.GameEngine;

/**
 * This class will implement the turn strategy for the computer player
 * 
 * @author fyounis
 *
 */
public class ComputerStrategy implements Strategy, Serializable {

	private static final long serialVersionUID = 1L;
	GameEngine gameEngine;
	Character character;

	/**
	 * This is the constructor of Computer Strategy
	 * 
	 * @param ge
	 */
	public ComputerStrategy(Character c, GameEngine ge) {
		this.gameEngine = ge;
		this.character = c;
	}

	/**
	 * this will implement how the computer player strategy
	 */
	@Override
	public void turn() {
		for (int i = 0; i < 3; ++i) {
			if (!isNextToPlayer()) {
				boolean success = moveHorizontal();
				if (!success) {
					moveVertical();
				}
			}
			if (gameEngine.withinOneSpace(getPlayerPosition(), gameEngine.getChestPosition())) {
				System.out.println("Taking chest");
				gameEngine.interactWith(character, gameEngine.getChestPosition());
			}
			attackNearbyEnemies();
			if (gameEngine.withinOneSpace(getPlayerPosition(), gameEngine.getCurrentMap().getExitPoint())){
				if(gameEngine.getMapObjective()==true){
					
					break;
				}
				
			}
		}
	}

	/**
	 * This method attack any nearby enemy
	 */
	private void attackNearbyEnemies() {
		RangeDetection rangeDetection = new RangeDetection(gameEngine);
		Character nearCharacter = rangeDetection.anyEnemyWithinRange(character);
		if (nearCharacter != null) {
			System.out.println("Aggressive is attacking");
			gameEngine.attack(character, nearCharacter);
		}
	}

	/**
	 * This method check if player next to Aggressive player
	 * 
	 * @return
	 */
	private boolean isNextToPlayer() {
		Point playerPosition = getPlayerPosition();
		Point aggressivePosition = getAggressivePosition();

		if (((int) playerPosition.getX() == (int) aggressivePosition.getX())
				&& (Math.abs((int) playerPosition.getY() - (int) aggressivePosition.getY()) <= 1)) {
			return true;
		}
		if (((int) playerPosition.getY() == (int) aggressivePosition.getY())
				&& (Math.abs((int) playerPosition.getX() - (int) aggressivePosition.getX()) <= 1)) {
			return true;
		}
		return false;
	}

	/**
	 * This method return the aggressive player position
	 * 
	 * @return
	 */
	private Point getAggressivePosition() {
		return gameEngine.getPositionOfCharacter(character);
	}

	private Point getPlayerPosition() {
		return gameEngine.getPositionOfCharacter(gameEngine.getCharacter());
	}

	/**
	 * This method check if its move to horizontal
	 * 
	 * @return
	 */
	private boolean moveHorizontal() {
		int offset;
		if (getAggressivePosition().getX() - getPlayerPosition().getX() < 0) {
			offset = 1;
		} else {
			offset = -1;
		}

		int x = (int) getAggressivePosition().getX() + offset;
		int y = (int) getAggressivePosition().getY();
		if (gameEngine.isMoveValid(x, y)) {
			gameEngine.move(character, x, y);
			return true;
		} else {
			return false;
		}
	}

	/**
	 * This method check if its move to vertical
	 * 
	 * @return
	 */
	private boolean moveVertical() {
		int offset;
		if (getAggressivePosition().getY() - getPlayerPosition().getY() < 0) {
			offset = 1;
		} else {
			offset = -1;
		}

		int x = (int) getAggressivePosition().getX();
		int y = (int) getAggressivePosition().getY() + offset;
		if (gameEngine.isMoveValid(x, y)) {
			gameEngine.move(character, x, y);
			return true;
		} else {
			return false;
		}
	}

}
