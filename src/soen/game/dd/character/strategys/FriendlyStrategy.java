package soen.game.dd.character.strategys;

import soen.game.dd.models.GameEngine;
import soen.game.dd.models.Character;

import java.awt.Point;
import java.io.Serializable;
import java.util.Random;

/**
 * This class will implement the turn strategy for friendly NPC
 * 
 * @author fyounis
 * @author khaled
 *
 */
public class FriendlyStrategy implements Strategy, Serializable {

	private static final long serialVersionUID = 1L;
	GameEngine gameEngine;
	Character character;
	Random rand;

	/**
	 * This is the constructor of Friendly strategy
	 * 
	 * @param c
	 * @param ge
	 */
	public FriendlyStrategy(Character c, GameEngine ge) {
		this.gameEngine = ge;
		this.character = c;
		this.rand = new Random();
	}

	/**
	 * This method implement turn mechanism for Friendly Strategy
	 */
	@Override
	public void turn() {
		for (int i = 0; i < 3; ++i) {
			moveRandom();
			if (gameEngine.withinOneSpace(getFriendlyPosition(), gameEngine.getChestPosition())) {
				System.out.println("Taking chest");
				gameEngine.interactWith(character, gameEngine.getChestPosition());
			}
		}

	}

	/**
	 * This method return Friendly position
	 * 
	 * @return
	 */
	private Point getFriendlyPosition() {
		return gameEngine.getPositionOfCharacter(character);
	}

	/**
	 * This method return Friendly Character random move
	 * 
	 * @return
	 */
	private boolean moveRandom() {
		int orientation = rand.nextInt(2);
		boolean success;
		if (orientation == 0) {
			success = moveHorizontal();
			if (!success) {
				success = moveVertical();
			}
			return success;
		} else {
			success = moveVertical();
			if (!success) {
				success = moveHorizontal();
			}
			return success;
		}
	}

	/**
	 * This method check horizontal move
	 * 
	 * @return
	 */
	private boolean moveHorizontal() {
		int direction = rand.nextInt(2);
		if (direction == 0) {
			boolean success = moveRight();
			if (!success) {
				success = moveLeft();
			}
			return success;
		} else {
			boolean success = moveLeft();
			if (!success) {
				success = moveRight();
			}
			return success;
		}
	}

	/**
	 * This method check vertical move
	 * 
	 * @return
	 */
	private boolean moveVertical() {
		int direction = rand.nextInt(2);
		if (direction == 0) {
			boolean success = moveUp();
			if (!success) {
				success = moveDown();
			}
			return success;
		} else {
			boolean success = moveDown();
			if (!success) {
				success = moveUp();
			}
			return success;
		}
	}

	/**
	 * This method check move left
	 * 
	 * @return
	 */
	private boolean moveLeft() {
		int x = (int) getFriendlyPosition().getX() - 1;
		int y = (int) getFriendlyPosition().getY();
		if (gameEngine.isMoveValid(x, y)) {
			gameEngine.move(character, x, y);
			return true;
		} else {
			return false;
		}
	}

	/**
	 * This method check move right
	 * 
	 * @return
	 */
	private boolean moveRight() {
		int x = (int) getFriendlyPosition().getX() + 1;
		int y = (int) getFriendlyPosition().getY();
		if (gameEngine.isMoveValid(x, y)) {
			gameEngine.move(character, x, y);
			return true;
		} else {
			return false;
		}
	}

	/**
	 * This method check move up
	 * 
	 * @return
	 */
	private boolean moveUp() {
		int x = (int) getFriendlyPosition().getX();
		int y = (int) getFriendlyPosition().getY() + 1;
		if (gameEngine.isMoveValid(x, y)) {
			gameEngine.move(character, x, y);
			return true;
		} else {
			return false;
		}
	}

	/**
	 * This method check move down
	 * 
	 * @return
	 */
	private boolean moveDown() {
		int x = (int) getFriendlyPosition().getX();
		int y = (int) getFriendlyPosition().getY() - 1;
		if (gameEngine.isMoveValid(x, y)) {
			gameEngine.move(character, x, y);
			return true;
		} else {
			return false;
		}
	}

}
