package soen.game.dd.logic;

import java.awt.Point;
import soen.game.dd.models.Character;
import soen.game.dd.models.GameEngine;
import soen.game.dd.statics.content.GameStatics;

/**
 * This is a range detection helper class for the gameengine.
 * Detects whether two characters are in range with each other, and who is within the vicinity of one character
 * @author khaledel-badawi
 *
 */
public class RangeDetection {
	
	private GameEngine gameEngine;
	
	/**
	 * This is the constructor of Range detection passing passing GameEngine object
	 * @param ge
	 */
	public RangeDetection(GameEngine ge){
		this.gameEngine = ge;
	}
	
	/**
	 * Check if enemy is within the range
	 * @param attacker
	 * @return
	 */
	public Character anyEnemyWithinRange(Character attacker){
		for (Character character : gameEngine.getPositions().keySet()){
			if (character != attacker){
				if (isEnemyWithinRange(attacker, character)){
					return character;
				}
			}
		}
		return null;
	}
	
	/**
	 * Check if enemy is within the range
	 * @param attacker
	 * @param defender
	 * @return
	 */
	public boolean isEnemyWithinRange(Character attacker, Character defender) {
		Point attackerPosition = gameEngine.getPositionOfCharacter(attacker);
		Point  defenderPosition = gameEngine.getPositionOfCharacter(defender);
		boolean sameX = attackerPosition.x == defenderPosition.x;
		boolean sameY = attackerPosition.y == defenderPosition.y;
		if ( !sameX && !sameY){
			return false;
		}
		if (sameX){
			return checkVertical(attackerPosition, defenderPosition, attacker.getWeaponRange());
		}
		return checkHorizontal(attackerPosition, defenderPosition, attacker.getWeaponRange());
	}
	
	/**
	 * Check range vertically
	 * 
	 * @param attackerPosition
	 * @param defenderPosition
	 * @param attackerRange
	 * @return
	 */
	private boolean checkVertical(Point attackerPosition, Point defenderPosition, int attackerRange) {
		Point belowPoint;
		Point abovePoint;
		if (defenderPosition.y > attackerPosition.y){
			belowPoint = defenderPosition;
			abovePoint = attackerPosition;
		} else {
			abovePoint = defenderPosition;
			belowPoint = attackerPosition;
		}
		for(int i = 1 ; i <= attackerRange; ++i){
			int checkPoint = gameEngine.getCurrentMap().mapGridSelection[abovePoint.x][abovePoint.y + i];
			if (checkPoint == GameStatics.MAP_CHEST_POINT || checkPoint == GameStatics.MAP_WALL_POINT){
				return false;
			}
			if (checkPoint == GameStatics.MAP_PATH_POINT){
				continue;
			}
			if (belowPoint.y == abovePoint.y + i){
				return true;
			}
		}
		return false;
	}

	/**
	 * Check range horizontally
	 * 
	 * @param attackerPosition
	 * @param defenderPosition
	 * @param attackerRange
	 * @return
	 */
	private boolean checkHorizontal(Point attackerPosition, Point defenderPosition, int attackerRange) {
		Point leftPoint;
		Point rightPoint;
		if (defenderPosition.x < attackerPosition.x){
			leftPoint = defenderPosition;
			rightPoint = attackerPosition;
		} else {
			rightPoint = defenderPosition;
			leftPoint = attackerPosition;
		}
		for(int i = 1 ; i <= attackerRange; ++i){
			int checkPoint = gameEngine.getCurrentMap().mapGridSelection[leftPoint.x + i][leftPoint.y];
			if (checkPoint == GameStatics.MAP_CHEST_POINT || checkPoint == GameStatics.MAP_WALL_POINT){
				return false;
			}
			if (checkPoint == GameStatics.MAP_PATH_POINT){
				continue;
			}
			if (leftPoint.x + i == rightPoint.x){
				return true;
			}
		}
		return false;		
	}

}
