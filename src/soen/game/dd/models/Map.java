package soen.game.dd.models;

/**
 * This class represents a map of the game.
 * Maps can be created using the Dungeon Master
 * 
 * @author kelbadawi
 * 
 */

public class Map {
	
	int mapWidth;
	int mapHeight;
	public int mapGridSelection[][];
	
	/**
	 * Default constructor
	 */
	public Map(){
		
	}
	
	public Map(int mapWidth, int mapHeight){
		this.mapWidth = mapWidth;
		this.mapHeight = mapHeight;
	}
	
	public void setMapWidth(int mapWidth){
		this.mapWidth = mapWidth;
	}
	
	public void setMapHeight(int mapHeight){
		this.mapHeight = mapHeight;
	}
	
	public int getMapWidth(){
		return mapWidth;
	}
	
	public int getMapHeight(){
		return mapHeight;
	}
	
	public void saveToFile(){
	}
	
	/**
	 * Load from file would load a previous saved map from a file
	 * @return Returns a Map object
	 */
	public static Map loadFromFile(String filename){
		return null;
	}


}
