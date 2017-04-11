package soen.game.dd.fileio;

import java.io.EOFException;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;

import soen.game.dd.models.Campaign;
import soen.game.dd.models.GameEngine;

/**
 * This class save and load Game to/from file
 * 
 * @author Usman
 *
 */
public class GameEngineIO extends ModelIO {

	private final String FILENAME = "savegame.txt";

	/**
	 * This method will save campaign to the file
	 * 
	 * @author Usman
	 * @param campaign
	 */

	public String saveGame(GameEngine gameEngine) {
		return save(gameEngine, FILENAME);
	}

	/**
	 * This method will load campaigns form the file
	 * 
	 * @return ArrayList<Campaign> campaigns
	 */
	public GameEngine loadGame() {

		GameEngine gameEngine = null;
		boolean congt = true;
		try {
			File file = new File(FILENAME);

			if (file.exists()) {
				ObjectInputStream in = new ObjectInputStream(new FileInputStream(FILENAME));
				gameEngine = (GameEngine) in.readObject();
			}
		} catch (IOException e) {
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
		return gameEngine;
	}

	/**
	 * This method will save multiple campaigns to the file
	 * 
	 * @author Usman
	 * @param ArrayList<Campaign>
	 *            campaigns
	 */

	public String saveCampaigns(ArrayList<Campaign> campaigns) {

		try {

			File file = new File(FILENAME);
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
}