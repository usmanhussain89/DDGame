package soen.game.dd.character.strategys;

import soen.game.dd.models.GameEngine;
import soen.game.dd.models.Character;

import java.awt.Point;
import java.util.Random;


/**
 * This class will implement the turn strategy for friendly NPC
 * 
 * @author fyounis
 * @author khaled
 *
 */
public class FriendlyStrategy implements Strategy {

	GameEngine gameEngine;
	Character character;
	Random rand;

	public FriendlyStrategy(Character c, GameEngine ge){
		this.gameEngine = ge;
		this.character = c;
		this.rand = new Random();

	}
	
	@Override
	public void turn() {
		for (int i = 0; i < 3; ++i){
			moveRandom();
		}
			
	}
	
	private Point getFriendlyPosition(){
		return gameEngine.getPositionOfCharacter(character);
	}
	
	private boolean moveRandom() {
		int orientation = rand.nextInt(2);
		boolean success;
		if(orientation == 0){
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
	
	private boolean moveHorizontal(){
		int direction = rand.nextInt(2);
		if(direction == 0){
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

	private boolean moveVertical(){
		int direction = rand.nextInt(2);
		if(direction == 0){
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
	
	private boolean moveLeft(){
		int x = (int) getFriendlyPosition().getX() - 1;
		int y = (int) getFriendlyPosition().getY();
		if (gameEngine.isMoveValid(character, x, y)){
			gameEngine.move(character, x, y);
			return true;
		} else {
			return false;
		}
	}
	
	private boolean moveRight(){
		int x = (int) getFriendlyPosition().getX() + 1;
		int y = (int) getFriendlyPosition().getY();
		if (gameEngine.isMoveValid(character, x, y)){
			gameEngine.move(character, x, y);
			return true;
		} else {
			return false;
		}
	}

	private boolean moveUp(){
		int x = (int) getFriendlyPosition().getX();
		int y = (int) getFriendlyPosition().getY() + 1;
		if (gameEngine.isMoveValid(character, x, y)){
			gameEngine.move(character, x, y);
			return true;
		} else {
			return false;
		}
	}
		
	private boolean moveDown(){
		int x = (int) getFriendlyPosition().getX();
		int y = (int) getFriendlyPosition().getY() - 1;
		if (gameEngine.isMoveValid(character, x, y)){
			gameEngine.move(character, x, y);
			return true;
		} else {
			return false;
		}
	}
	
}
