package soen.game.dd.fileio;

import java.io.EOFException;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;

import soen.game.dd.models.Map;

/**
 * This class save and load Map to/from file
 * 
 * @author Usman
 *
 */
public class MapIO extends ModelIO{
	
	private final String FILENAME = "maps.txt";
	
	/**
	 * This method will save the map to the file
	 * 
	 * @author Usman
	 * @param map
	 */
	public String saveMap(Map map) {
		boolean isMapNameExist = false;

		ArrayList<Map> maps = loadMaps();

		if (maps != null) {
			for (Map m : maps) {
				if (m.getMapName().equals(map.getMapName())) {
					isMapNameExist = true;
					break;
				}
			}
		}

		if (!isMapNameExist)
			return save(map, FILENAME);

		else
			return "EXIST";
	}
	
	/**
	 * This method will load maps that was saved in a .txt file
	 * 
	 * @param name
	 *            of the map (String)
	 * @return map
	 * @throws FileNotFoundException
	 * @throws IOException
	 * @throws ClassNotFoundException
	 */
	public ArrayList<Map> loadMaps() {

		ArrayList<Map> maps = new ArrayList<Map>();
		boolean cont = true;
		try {
			File file = new File(FILENAME);

			if (file.exists()) {
				ObjectInputStream in = new ObjectInputStream(new FileInputStream("Maps.txt"));
				while (cont) {
					try {
						Map map = (Map) in.readObject();
						if (map != null) {
							maps.add(map);
							System.out.println("Item: " + map.getMapName() + " is added");
						} else
							cont = false;
					}

					catch (EOFException eof) {
						break;
					}
				}
				in.close();
				return maps;
			} else {
				return null;
			}
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}
	
	/**
	 * This method will save maps to the file
	 * 
	 * @author fyounis
	 * @param ArrayList<Map> maps
	 */

	public String saveMaps(ArrayList<Map> maps) {

		try {

			File file = new File(FILENAME);
			FileOutputStream fout = null;
			ObjectOutputStream out = null;

			fout = new FileOutputStream(file.getPath());
			out = new ObjectOutputStream(fout);
			for (Map map : maps) {
				out.writeObject(map);

			}
			out.flush();
			out.close();

			return "SUCCESS";

		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return "ERROR";
		}

	}
}
