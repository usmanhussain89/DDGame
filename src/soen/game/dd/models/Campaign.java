package soen.game.dd.models;

import java.io.Serializable;
import java.util.LinkedList;

/**
 * This class represents a campaign. It will store a list of maps for each
 * campaign.
 * 
 * @author kelbadawi
 *
 */
public class Campaign implements Serializable {

	private static final long serialVersionUID = -3877623745831844946L;
	private String campaignName;
	private LinkedList<Map> listMap = new LinkedList<Map>();

	/**
	 * This method return Campaign Name
	 * 
	 * @return campaignName
	 */
	public String getCampaignName() {
		return campaignName;
	}

	/**
	 * This method set Campaign Name
	 * 
	 * @param campaignName
	 */
	public void setCampaignName(String campaignName) {
		this.campaignName = campaignName;
	}

	/**
	 * This method return Campaign contain list of maps
	 * 
	 * @return listMap
	 */
	public LinkedList<Map> getCampaignList() {
		return listMap;
	}

	/**
	 * This method set map in campaign list
	 * 
	 * @param campaignName
	 */
	public void setCampaignList(Map map) {
		listMap.add(map);
		System.out.println("added this map "+map);
	}

	/**
	 * This method remove Map from campaign lit
	 * 
	 * @param index
	 */
	public void removeMapFromList(int index) {
		listMap.remove(index);
	}
}
