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
	 * @param name a String of the name of the object
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
	 * This method will save an object map into ,txt file with unique name
	 * One object per txt file
	 * 
	 * @author fyounis
	 * @param map 
	 */
	public String saveMap(File file, Map map) {

		try {

			//String link = String.format("map_%s.txt", map.getMapName());
			String link = file.getPath() + ".txt";
			FileOutputStream fout = new FileOutputStream(link);
			ObjectOutputStream out = new ObjectOutputStream(fout);
			
			out.writeObject(map);
			out.flush();
			out.close();
			System.out.println("<info> : The Map : " + map.getMapName() + " is saved");
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
	 * @param name of the map (String)
	 * @return map
	 * @throws FileNotFoundException
	 * @throws IOException
	 * @throws ClassNotFoundException
	 */
	public Map loadMap(File file) throws FileNotFoundException, IOException, ClassNotFoundException {
		ObjectInputStream in = new ObjectInputStream(new FileInputStream(file.getPath()));
		Map map = (Map) in.readObject();
		System.out.println("<info> : the Loading of the Map " + map.getMapName() + " was secusseful");
		return map;

	}

	/**
	 * This method will save an object item into txt file with unique name
	 * One object per txt file
	 * 
	 * @author fyounis
	 * @param item
	 */

	public String saveItem(Item item) {

		try {

			
//			String link = String.format("item_%.ser", item.getName());
//			System.out.println(link);
			File file = new File("Items.txt");
			FileOutputStream fout = null;
			ObjectOutputStream out = null;
			if(!file.exists()){
				file.createNewFile();
				fout = new FileOutputStream(file.getPath());
				out = new ObjectOutputStream(fout);
			}
			else {
				fout = new FileOutputStream(file.getPath(), true);
				out = new ObjectOutputStream(fout) {
					protected void writeStreamHeader() throws IOException {
		                reset();
		            }
				};
			}

			out.writeObject(item);
			out.flush();
			out.close();
			System.out.println("<info> : The Item: " + item.getName() + " is saved");
			return "SUCCESS";

		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return "ERROR";
		}

	}
	
	/**
	 * This method will save an object item into txt file with unique name
	 * One object per txt file
	 * 
	 * @author fyounis
	 * @param item
	 */

	public String saveItem(ArrayList<Item> items) {

		try {

			File file = new File("Items.txt");
			FileOutputStream fout = null;
			ObjectOutputStream out = null;
			
			fout = new FileOutputStream(file.getPath());
			out = new ObjectOutputStream(fout);
			for (Item item : items){
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
	public ArrayList<Item> loadItem() {
		ArrayList<Item> items = new ArrayList<Item>();
		boolean cont = true;
		try
		{
			File file = new File("Items.txt");
			
			if (file.exists())
			{
				ObjectInputStream in = new ObjectInputStream(new FileInputStream("Items.txt"));
				while(cont) {
					try {
						Item item = (Item) in.readObject();
						if(item != null)
						{
							items.add(item);
							System.out.println("Item: "+item.getName()+" is added");
						}
						else
							cont = false;
					}
					
					catch (EOFException eof)
					{
						break;
					}
				}
				in.close();
				return items;
			}
			else
			{
				return null;
			}
		}
		catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}
	
	/**
	 * This method will save an object campaign into txt file with unique name One
	 * object per txt file
	 * 
	 * @author fyounis
	 * @param campaign
	 */

	public String saveCampaign(File file, Campaign campaign) {

		try {

			//String link = String.format("Campaign_%.txt", campaign.getCampaignName());
			String link = file.getPath() + ".txt";

			FileOutputStream fout = new FileOutputStream(link);
			ObjectOutputStream out = new ObjectOutputStream(fout);

			out.writeObject(campaign);
			out.flush();
			out.close();
			System.out.println("<info> : The Item: " + campaign.getCampaignName() + " is saved");
			return "SUCCESS";

		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return "ERROR";
		}

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

		//String link = String.format("campaign_%.txt", name);
		ObjectInputStream in = new ObjectInputStream(new FileInputStream(file.getPath()));
		Campaign campaign = (Campaign) in.readObject();
		return campaign;
	}
}
