package game.models;

/**
 * This class represents a map of the game.
 * Maps can be created using the Dungeon Master
 * 
 * @author kelbadawi
 * @param width Width of the map
 * @param height Height of the map
 */

public class Map {
	
	
	private String mapName;
	private int width;
	private int height;
	
	public Map(int width, int height){
		
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
	public int getWidth() {
		return width;
	}

	/**
	 * @param width the width to set
	 */
	public void setWidth(int width) {
		this.width = width;
	}

	/**
	 * @return the height
	 */
	public int getHeight() {
		return height;
	}

	/**
	 * @param height the height to set
	 */
	public void setHeight(int height) {
		this.height = height;
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "Map [mapName=" + mapName + ", width=" + width + ", height=" + height + "]";
	}
	
	


}
