package soen.game.dd.fileio;

import java.io.EOFException;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;

import soen.game.dd.models.Item;

/**
 * This class save and load Item to/from file
 * 
 * @author Usman
 *
 */
public class ItemIO extends ModelIO {
	private final String FILENAME = "items.txt";
	
	/**
	 * This method will save item to the file
	 * 
	 * @author Usman
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
			return save(item, FILENAME);

		else
			return "EXIST";
	}
	
	/**
	 * This method will load a Item that was saved in a .txt file
	 * 
	 * @return ArrayList<Item> items
	 */
	public ArrayList<Item> loadItems() {
		ArrayList<Item> items = new ArrayList<Item>();
		boolean cont = true;
		try {
			File file = new File("Items.txt");

			if (file.exists()) {
				ObjectInputStream in = new ObjectInputStream(new FileInputStream(FILENAME));
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
	 * This method will multiple items on the file
	 * 
	 * @author Usman
	 * @param ArrayList<Item> items
	 */

	public String saveItems(ArrayList<Item> items) {

		try {

			File file = new File(FILENAME);
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
}
