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

/**
 * This class save and load Campaign to/from file
 * 
 * @author Usman
 *
 */
public class CampaignIO extends ModelIO{
	
	private final String FILENAME = "campaigns.txt";
	
	/**
	 * This method will save campaign to the file
	 * 
	 * @author Usman
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
			return save(campaign, FILENAME);

		else
			return "EXIST";
	}
	
	/**
	 * This method will load campaigns form the file
	 * 
	 * @return ArrayList<Campaign> campaigns
	 */
	public ArrayList<Campaign> loadCampaigns() {

		ArrayList<Campaign> campaigns = new ArrayList<Campaign>();
		boolean cont = true;
		try {
			File file = new File(FILENAME);

			if (file.exists()) {
				ObjectInputStream in = new ObjectInputStream(new FileInputStream(FILENAME));
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
	 * This method will save multiple campaigns to the file
	 * 
	 * @author Usman
	 * @param ArrayList<Campaign> campaigns
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
