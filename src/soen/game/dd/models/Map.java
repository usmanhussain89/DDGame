package soen.game.dd.models;

import java.io.Serializable;

/**
 * This class represents a map of the game.
 * Maps can be created using the Dungeon Master
 * 
 * @author kelbadawi
 * 
 */

public class Map implements Serializable {
	
	String mapName;
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

	/**
	 * @return the mapName
	 */
	public String getMapName() {
		return mapName;
	}

	/**
	 * @param mapName the mapName to set
	 */
	public void setMapName(String mapName) {
		this.mapName = mapName;
	}

	/**
	 * @return the width
	 */
	public int getMapWidth() {
		return mapWidth;
	}

	/**
	 * @param width the width to set
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
	 * @param height the height to set
	 */
	public void setMapHeight(int height) {
		this.mapHeight = height;
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "Map [mapName=" + mapName + ", width=" + mapWidth + ", height=" + mapHeight + "]";
	}
	
	


}
