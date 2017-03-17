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

import soen.game.dd.models.Character;
import soen.game.dd.models.Item;

public class CharacterIO extends ModelIO {
	
	private final String FILENAME = "characters.txt";

	/**
	 * This method will save the character to the file
	 * 
	 * @author kelbadawi
	 * @param character
	 */

	public String saveCharacter(Character character) {
		boolean isCharacterNameExist = false;

		ArrayList<Character> characters = loadCharacters();

		if (characters != null) {
			for (Character c : characters) {
				if (c.getName().equals(character.getName())) {
					isCharacterNameExist = true;
					break;
				}
			}
		}

		if (!isCharacterNameExist)
			return save(character, "Characters.txt");

		else
			return "EXIST";
	}
	
	public String saveCharacters(ArrayList<Character> characters) {

		try {

			File file = new File(FILENAME);
			FileOutputStream fout = null;
			ObjectOutputStream out = null;

			fout = new FileOutputStream(file.getPath());
			out = new ObjectOutputStream(fout);
			for (Character character : characters) {
				out.writeObject(character);

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
	public ArrayList<Character> loadCharacters() {

		ArrayList<Character> characters = new ArrayList<Character>();
		try {
			File file = new File(FILENAME);

			if (file.exists()) {
				ObjectInputStream in = new ObjectInputStream(new FileInputStream(FILENAME));
				while (true) {
					try {
						Character character = (Character) in.readObject();
						if (character != null) {
							characters.add(character);
							System.out.println("Character: " + character.getName() + " is added");
						} else {
							break;
						}
					}
					catch (EOFException eof) {
						break;
					}
				}
				in.close();
				return characters;
			} else {
				return null;
			}
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}
	
}
