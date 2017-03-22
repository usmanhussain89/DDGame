package soen.game.dd.models;

import java.awt.Point;
import java.io.Serializable;
import java.util.ArrayList;

import soen.game.dd.statics.content.GameStatics;

/**
 * This class represents a map of the game. Maps can be created using the
 * Dungeon Master
 * 
 * @author kelbadawi
 * 
 */

public class Map implements Serializable {

	private static final long serialVersionUID = -8107798234290328083L;
	String name;
	int mapWidth;
	int mapHeight;
	Point entryPoint;
	Point exitPoint;
	Point characterPoint;
	Point opponentPoint;
	Point chestPoint;
	public boolean isEntryDone;
	public boolean isExitDone;
	public boolean isCharacterDone;
	public boolean isOpponentDone;
	public boolean isChestDone;
	public int mapGridSelection[][];

	// Chest Items variables
	public boolean isChestItemsSelected;
	// public Item mapSelectedItem[];
	public ArrayList<Item> mapSelectedItem = new ArrayList<Item>();

	// Character variables
	public boolean isCharacterSelected;
	public ArrayList<Character> mapCharacters = new ArrayList<Character>();

	public String mapAutoGeneratedDescription;
	public String mapRoutPath;
	public String mapRoutBoundaries;
	public ArrayList<Point> mapRoutPathList;

	/**
	 * Default constructor
	 */
	public Map() {

	}

	public Map(int mapWidth, int mapHeight) {
		this.mapWidth = mapWidth;
		this.mapHeight = mapHeight;
	}

	/**
	 * Checks if the map is valid. For an map to be valid: - it need Not to
	 * width or height should not exceed 30 by 30 should not be a negative
	 * number
	 * 
	 *
	 * @return true if item is valid, false otherwise
	 */
	public boolean isValid() {
		if ((this.mapWidth > 30 || this.mapWidth < 1 || this.mapHeight > 30 || this.mapHeight < 1)) {
			System.out.println(
					"the map of height: " + this.mapHeight + " and  Width: " + this.mapWidth + " are not correct");
			return false;
		}
		System.out.println("Map is valid");
		return true;

	}

	/**
	 * @return the mapName
	 */
	public String getMapName() {
		return name;
	}

	/**
	 * @param mapName
	 *            the mapName to set
	 */
	public void setMapName(String mapName) {
		this.name = mapName;
	}

	/**
	 * @return the width
	 */
	public int getMapWidth() {
		return mapWidth;
	}


	/**
	 * @param width
	 *            the width to set
	 */
	public void setMapWidth(int width) {
		this.mapWidth = width;
	}

	/**
	 * @return the height
	 */
	public int getMapHeight() {
		return mapHeight;
	}

	/**
	 * @param height
	 *            the height to set
	 */
	public void setMapHeight(int height) {
		this.mapHeight = height;
	}

	/**
	 * this returns the entry point of the map
	 * 
	 * @return entryPoint entry point on the map
	 */
	public Point getEntryPoint() {

		for (int i = 0; i < mapHeight; i++){
			for (int j = 0; j < mapWidth; j++){
				if (mapGridSelection[i][j] == GameStatics.MAP_ENTRY_POINT){
					return new Point(i,j);
				}
			}
		}
		return null;
	}

	/**
	 * this method sets the entry point on the map
	 * 
	 * @param new_entryPoint
	 *            entry point on the map
	 */
	public void setEntryPoint(Point new_entryPoint) {
		this.entryPoint = new_entryPoint;
	}

	/**
	 * this method returns the exit point
	 * 
	 * @return exitPoint exit point on the map
	 */
	public Point getExitPoint() {
		return exitPoint;
	}

	/**
	 * this method sets the exit point on the map
	 * 
	 * @param new_exitPoint
	 *            exit point on the map
	 */
	public void setExitPoint(Point new_exitPoint) {
		this.exitPoint = new_exitPoint;
	}

	/**
	 * this method returns the Character point
	 * 
	 * @return characterPoint character point on the map
	 */
	public Point getCharacterPoint() {
		return characterPoint;
	}

	/**
	 * this method sets the character point on the map
	 * 
	 * @param new_characterPoint
	 *            character point on the map
	 */
	public void setCharacterPoint(Point new_characterPoint) {
		int x = (int) new_characterPoint.getX();
		int y = (int) new_characterPoint.getY();

		if (mapGridSelection[x][y] == GameStatics.MAP_PATH_POINT){
			this.characterPoint = new_characterPoint;
			System.out.println(new_characterPoint);
			for (int i = 0; i < mapHeight; i++){
				for (int j = 0; j < mapWidth; j++){
					if (mapGridSelection[i][j] == GameStatics.MAP_CHARACTER_POINT){
						mapGridSelection[i][j] = GameStatics.MAP_PATH_POINT;
					}
				}
			}
			mapGridSelection[(int) new_characterPoint.getX()][(int) new_characterPoint.getY()] = GameStatics.MAP_CHARACTER_POINT;
		}
	}

	/**
	 * this method returns the Opponent point
	 * 
	 * @return opponentPoint opponent point on the map
	 */
	public Point getOpponentPoint() {
		return opponentPoint;
	}

	/**
	 * this method sets the opponent point on the map
	 * 
	 * @param new_opponentPoint
	 *            opponent point on the map
	 */
	public void setOpponentPoint(Point new_opponentPoint) {
		this.opponentPoint = new_opponentPoint;
	}

	/**
	 * this method returns the Chest point
	 * 
	 * @return chestPoint chest point on the map
	 */
	public Point getChestPoint() {
		return chestPoint;
	}
	
	/**
	 * This method return the friend character on the map
	 * @return
	 */
	public Character getFriendlyCharacter(){
		for (Character character : mapCharacters){
			if (character.getNPCType() == NPCType.FRINDLY){
				return character;
			}
		}
		return null;
	}
	
	/**
	 * This method return the hostile character on the map
	 * @return
	 */
	public Character getHostileCharacter(){
		for (Character character : mapCharacters){
			if (character.getNPCType() == NPCType.HOSTILE || character.getNPCType() == NPCType.DEAD){
				return character;
			}
		}
		return null;
	}
	
	/**
	 * This method return the hostile character index on the map
	 * @return
	 */
	public int getHostileCharacterIndex(){
		int index = 0;
		for (Character character : mapCharacters){
			if (character.getNPCType() == NPCType.HOSTILE){
				return index;
			}
			index++;
		}
		return index-1;
	}

	/**
	 * this method sets the chest point on the map
	 * 
	 * @param new_chestPoint
	 *            chest point on the map
	 */
	public void setChestPoint(Point new_chestPoint) {
		this.chestPoint = new_chestPoint;
	}

	/**
	 * This method gets map description
	 * 
	 * @return the map auto generated description
	 */
	public String getMapAutoGeneratedDescription() {
		return mapAutoGeneratedDescription;
	}

	/**
	 * This method sets the map auto generated description
	 * 
	 * @param new_mapAutoGeneratedDescription
	 *            the description
	 */
	public void setMapAutoGeneratedDescription(String new_mapAutoGeneratedDescription) {
		this.mapAutoGeneratedDescription = new_mapAutoGeneratedDescription;
	}

	/**
	 * this method gets map grid selection
	 * 
	 * @return mapGridSelection map grid selection
	 */
	public int[][] getMapGridSelection() {
		return mapGridSelection;
	}

	/**
	 * this method sets the map grid selection
	 * 
	 * @param new_mapGridSelection
	 *            map grid selection
	 */
	public void setMapGridSelection(int[][] new_mapGridSelection) {
		this.mapGridSelection = new_mapGridSelection;
	}

	/**
	 * @return the mapRoutPath
	 */
	public String getMapRoutPath() {
		return mapRoutPath;
	}

	/**
	 * @param new_mapRoutPath
	 *            the mapRoutPath to set
	 */
	public void setMapRoutPath(String new_mapRoutPath) {
		this.mapRoutPath = new_mapRoutPath;
	}

	/**
	 * @return the mapRoutBoundaries
	 */
	public String getMapRoutBoundaries() {
		return mapRoutBoundaries;
	}

	/**
	 * @param new_mapRoutBoundaries
	 *            the mapRoutBoundaries to set
	 */
	public void setMapRoutBoundaries(String new_mapRoutBoundaries) {
		this.mapRoutBoundaries = new_mapRoutBoundaries;
	}

	/**
	 * @return the mapRoutPathList
	 */
	public ArrayList<Point> getMapRoutPathList() {
		if (mapRoutPath != null) {
			String[] tempList = mapRoutPath.split(";");
			mapRoutPathList = new ArrayList<Point>();
			for (int i = 0; i < tempList.length; i++) {
				String[] tempListShort = tempList[i].split(",");
				mapRoutPathList.add(new Point(Integer.parseInt(tempListShort[0]), Integer.parseInt(tempListShort[1])));
			}
		}
		return mapRoutPathList;
	}

	/**
	 * @param new_mapRoutPathList
	 *            the mapRoutPathList to set
	 */
	public void setMapRoutPathList(ArrayList<Point> new_mapRoutPathList) {
		this.mapRoutPathList = new_mapRoutPathList;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "Map [mapName=" + name + ", width=" + mapWidth + ", height=" + mapHeight + "]";
	}

}
