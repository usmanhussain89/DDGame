package soen.game.dd.models;

import java.io.EOFException;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;

/**
 * This class will hold all the methods where we can write or read the objects
 * of maps , campaigns, characters and items
 * 
 * @author fyounis
 *
 */

public class FileWriterReader {

	/**
	 * This method will save an object character into ,txt file with unique name
	 * One object per txt file
	 * 
	 * @author fyounis
	 * @param character
	 */

	public void saveCharacter(Character character) {

		try {

			String link = String.format("characters_%.txt", character.getName());

			FileOutputStream fout = new FileOutputStream(link);
			ObjectOutputStream out = new ObjectOutputStream(fout);

			out.writeObject(character);
			out.flush();
			out.close();
			System.out.println("<info> : The Character: " + character.getName() + " is saved");

		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	/**
	 * This method will load a character that was saved in a .txt file
	 * 
	 * @author fyounis
	 * @param name
	 *            a String of the name of the object
	 * @return Character
	 * @throws FileNotFoundException
	 * @throws IOException
	 * @throws ClassNotFoundException
	 */
	public Character loadCharacter(String name) throws FileNotFoundException, IOException, ClassNotFoundException {

		String link = String.format("characters_%.txt", name);
		ObjectInputStream in = new ObjectInputStream(new FileInputStream(link));
		Character character = (Character) in.readObject();
		return character;

	}

	/**
	 * This method will save an object map into ,txt file with unique name One
	 * object per txt file
	 * 
	 * @author fyounis
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
			return save(map, "Maps.txt");

		else
			return "EXIST";
	}
	
	/**
	 * This is a common method to save the Object onto the file
	 * 
	 * @author Usman
	 * @param o 
	 * @param fileName
	 * @return status
	 */
	private String save(Object o, String fileName) {
		try {
			File file = new File(fileName);
			FileOutputStream fout = null;
			ObjectOutputStream out = null;
			if (!file.exists()) {
				file.createNewFile();
				fout = new FileOutputStream(file.getPath());
				out = new ObjectOutputStream(fout);
			} else {
				fout = new FileOutputStream(file.getPath(), true);
				out = new ObjectOutputStream(fout) {
					protected void writeStreamHeader() throws IOException {
						reset();
					}
				};
			}

			out.writeObject(o);
			out.flush();
			out.close();
			return "SUCCESS";

		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return "ERROR";
		}
	}

	/**
	 * This method will save an object item into txt file with unique name One
	 * object per txt file
	 * 
	 * @author fyounis
	 * @param item
	 */

	public String saveMaps(ArrayList<Map> maps) {

		try {

			File file = new File("Maps.txt");
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

	/**
	 * This method will save an object item into txt file with unique name One
	 * object per txt file
	 * 
	 * @author fyounis
	 * @param item
	 */

	public String saveCampaigns(ArrayList<Campaign> campaigns) {

		try {

			File file = new File("Campaigns.txt");
			FileOutputStream fout = null;
			ObjectOutputStream out = null;

			fout = new FileOutputStream(file.getPath());
			out = new ObjectOutputStream(fout);
			for (Campaign campaign : campaigns) {
				out.writeObject(campaign);

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

	/**
	 * This method will load a map that was saved in a .txt file
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
			File file = new File("Maps.txt");

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
	 * This method will load a map that was saved in a .txt file
	 * 
	 * @param name
	 *            of the map (String)
	 * @return map
	 * @throws FileNotFoundException
	 * @throws IOException
	 * @throws ClassNotFoundException
	 */
	public ArrayList<Campaign> loadCampaigns() {

		ArrayList<Campaign> campaigns = new ArrayList<Campaign>();
		boolean cont = true;
		try {
			File file = new File("Campaigns.txt");

			if (file.exists()) {
				ObjectInputStream in = new ObjectInputStream(new FileInputStream("Campaigns.txt"));
				while (cont) {
					try {
						Campaign campaign = (Campaign) in.readObject();
						if (campaign != null) {
							campaigns.add(campaign);
							System.out.println("Item: " + campaign.getCampaignName() + " is added");
						} else
							cont = false;
					}

					catch (EOFException eof) {
						break;
					}
				}
				in.close();
				return campaigns;
			} else {
				return null;
			}
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}

	/**
	 * This method will save an object item into txt file with unique name One
	 * object per txt file
	 * 
	 * @author fyounis
	 * @param item
	 */

	public String saveItem(Item item) {
		boolean isItemNameExist = false;

		ArrayList<Item> items = loadItems();

		if (items != null) {
			for (Item i : items) {
				if (i.getName().equals(item.getName())) {
					isItemNameExist = true;
					break;
				}
			}
		}

		if (!isItemNameExist)
			return save(item, "Items.txt");

		else
			return "EXIST";
	}

	/**
	 * This method will save an object item into txt file with unique name One
	 * object per txt file
	 * 
	 * @author fyounis
	 * @param item
	 */

	public String saveItems(ArrayList<Item> items) {

		try {

			File file = new File("Items.txt");
			FileOutputStream fout = null;
			ObjectOutputStream out = null;

			fout = new FileOutputStream(file.getPath());
			out = new ObjectOutputStream(fout);
			for (Item item : items) {
				out.writeObject(item);

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

	/**
	 * This method will load a Item that was saved in a .txt file
	 * 
	 * @param name
	 * @return item
	 * @throws FileNotFoundException
	 * @throws IOException
	 * @throws ClassNotFoundException
	 */
	public ArrayList<Item> loadItems() {
		ArrayList<Item> items = new ArrayList<Item>();
		boolean cont = true;
		try {
			File file = new File("Items.txt");

			if (file.exists()) {
				ObjectInputStream in = new ObjectInputStream(new FileInputStream("Items.txt"));
				while (cont) {
					try {
						Item item = (Item) in.readObject();
						if (item != null) {
							items.add(item);
							System.out.println("Item: " + item.getName() + " is added");
						} else
							cont = false;
					}

					catch (EOFException eof) {
						break;
					}
				}
				in.close();
				return items;
			} else {
				return null;
			}
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}

	/**
	 * This method will save an object campaign into txt file with unique name
	 * One object per txt file
	 * 
	 * @author fyounis
	 * @param campaign
	 */

	public String saveCampaign(Campaign campaign) {
		boolean isCampaignNameExist = false;

		ArrayList<Campaign> campaigns = loadCampaigns();

		if (campaigns != null) {
			for (Campaign c : campaigns) {
				if (c.getCampaignList().equals(campaign.getCampaignName())) {
					isCampaignNameExist = true;
					break;
				}
			}
		}

		if (!isCampaignNameExist)
			return save(campaign, "Campaigns.txt");

		else
			return "EXIST";
	}

	/**
	 * This method will load a campaign that was saved in a .txt file
	 * 
	 * @param name
	 * @return campaign
	 * @throws FileNotFoundException
	 * @throws IOException
	 * @throws ClassNotFoundException
	 */
	public Campaign loadCampaign(File file) throws FileNotFoundException, IOException, ClassNotFoundException {

		// String link = String.format("campaign_%.txt", name);
		ObjectInputStream in = new ObjectInputStream(new FileInputStream(file.getPath()));
		Campaign campaign = (Campaign) in.readObject();
		return campaign;
	}
}
