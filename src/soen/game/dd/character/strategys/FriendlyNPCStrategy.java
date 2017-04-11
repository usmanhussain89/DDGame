package soen.game.dd.character.strategys;

import soen.game.dd.models.GameEngine;
import soen.game.dd.statics.content.GameStatics;
import soen.game.dd.models.Character;

import java.awt.Point;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;


/**
 * This class will implement the turn strategy for friendly NPC
 * 
 * @author fyounis
 * @author khaled
 *
 */
public class FriendlyNPCStrategy implements Strategy {

	GameEngine gameEngine;
	Character character;

	public FriendlyNPCStrategy(Character c, GameEngine ge){
		this.gameEngine = ge;
		this.character = c;
	}
	
	@Override
	public void turn() {
		for (int i = 0; i < 3; ++i){
			Random rand = new Random();
			Point newPoint = null;
			int direction = rand.nextInt(2);
			if (direction == 0){
				List<Point> xDirections = getXDirections();
				if (xDirections.size() > 0){
					int step = rand.nextInt(xDirections.size());
					newPoint = xDirections.get(step);
				}
			}else {
				List<Point> yDirections = getYDirections();
				if (yDirections.size() > 0){
					int step = rand.nextInt(yDirections.size());
					newPoint = yDirections.get(step);
				}
			}
			gameEngine.move(character, (int)newPoint.getX(), (int)newPoint.getY());
			gameEngine.notifyObservers();

		}
	}
	
	private Point getCurrentPoint(){
		return gameEngine.getPositionOfCharacter(character);
	}
	
	public List<Point> getXDirections() {
		List<Point> xDirections = new ArrayList<Point>();
		int currentX = (int) getCurrentPoint().getX();
		if (currentX > 0){
			Point possiblePoint = new Point(currentX - 1, (int)getCurrentPoint().getY());
			if (gameEngine.getCurrentMap().mapGridSelection[(int)possiblePoint.getX()][(int)possiblePoint.getY()] == GameStatics.MAP_PATH_POINT){
				xDirections.add(possiblePoint);
			}
		}
		if (currentX < gameEngine.getCurrentMap().getMapWidth() - 1){
			Point possiblePoint = new Point(currentX + 1, (int)getCurrentPoint().getY());
			if (gameEngine.getCurrentMap().mapGridSelection[(int)possiblePoint.getX()][(int)possiblePoint.getY()] == GameStatics.MAP_PATH_POINT){
				xDirections.add(possiblePoint);
			}
		}
		return xDirections;
	}
	
	public List<Point> getYDirections() {
		List<Point> yDirections = new ArrayList<Point>();
		int currentY = (int) getCurrentPoint().getY();
		if (currentY > 0){
			Point possiblePoint = new Point((int) getCurrentPoint().getX(), currentY - 1);
			if (gameEngine.getCurrentMap().mapGridSelection[(int)possiblePoint.getX()][(int)possiblePoint.getY()] == GameStatics.MAP_PATH_POINT){
				yDirections.add(possiblePoint);
			}
		}
		if (currentY < gameEngine.getCurrentMap().getMapHeight() - 1){
			Point possiblePoint = new Point((int) getCurrentPoint().getX(), currentY + 1);
			if (gameEngine.getCurrentMap().mapGridSelection[(int)possiblePoint.getX()][(int)possiblePoint.getY()] == GameStatics.MAP_PATH_POINT){
				yDirections.add(possiblePoint);
			}
		}
		return yDirections;
	}

}
