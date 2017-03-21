package soen.game.dd.models;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.Random;

/**
 * This is Game Engine class 
 * 
 * @author Usman
 *
 */
public class DummyGameEngine implements Runnable{
	
	private Campaign campaign;
	private Character character;
	private Map currentMap;
	private boolean isMapObjFulfil = false;
	private Thread t;
	
	/**
	 * This is constructor of the class which initialize campaign and character object
	 * 
	 * @param campaign
	 * @param character
	 */
	public DummyGameEngine(Campaign campaign, Character character) {
		this.campaign = campaign;
		this.character = character;
	}
	
	
	public void startGameEngine() {
		if (t == null) {
			t = new Thread(this);
			t.start();
		}
	}
	
	/**
	 * This method set the campaign
	 * 
	 * @param campaign
	 */
	public void setCampaign(Campaign campaign) {
		this.campaign = campaign;
	}
	
	/**
	 * This method return the campaign
	 * @return
	 */
	public Campaign getCampagin() {
		return this.campaign;
	}
	
	/**
	 * This method set the Character
	 * 
	 * @param character
	 */
	public void setCharacter(Character character) {
		this.character = character;
	}
	
	/**
	 * This method return the Character
	 * @return
	 */
	public Character getCharacter() {
		return this.character;
	}
	
	/**
	 * This method set the Current Map
	 * 
	 * @param Map
	 */
	public void setCurrentMap(Map map) {
		this.currentMap = map;
	}
	
	/**
	 * This method return the Current Map
	 * @return
	 */
	public Map getCurrentMap() {
		return this.currentMap;
	}
	
	/**
	 * This method set the level of NPC'S and Item to the player character level
	 * 
	 * @param currentMap
	 */
	public void setCharacterItemLevel(Map currentMap, Character character) {
		int playerCharacterLevel = character.getLevel();
		Iterator<Character> iterChars = currentMap.mapCharacters.iterator();
		Iterator<Item> iterItems = currentMap.mapSelectedItem.iterator();
		int indexCharacter = 0;
		int indexItem = 0;
		while (iterChars.hasNext() && indexCharacter < currentMap.mapCharacters.size()) {
			this.currentMap.mapCharacters.get(indexCharacter).setLevel(playerCharacterLevel);
			indexCharacter++;
		}
		
		while (iterItems.hasNext() && indexItem < currentMap.mapSelectedItem.size()) {
			if (playerCharacterLevel >= 1 && playerCharacterLevel <= 4) {
				this.currentMap.mapSelectedItem.get(indexItem).setBonusAmount(1);
				indexItem++;
			}
			
			if (playerCharacterLevel >= 5 && playerCharacterLevel <= 8) {
				this.currentMap.mapSelectedItem.get(indexItem).setBonusAmount(2);
				indexItem++;
			}
			
			if (playerCharacterLevel >= 9 && playerCharacterLevel <= 12) {
				this.currentMap.mapSelectedItem.get(indexItem).setBonusAmount(3);
				indexItem++;
			}
			
			if (playerCharacterLevel >= 13 && playerCharacterLevel <= 16) {
				this.currentMap.mapSelectedItem.get(indexItem).setBonusAmount(4);
				indexItem++;
			}
			
			if (playerCharacterLevel >= 17) {
				this.currentMap.mapSelectedItem.get(indexItem).setBonusAmount(5);
				indexItem++;
			}
		}
	}
	
	/**
	 * This is method added the looted item to the character backpack
	 * @param item
	 * @return backpack status
	 */
	public boolean lootChestItems(Item item) {
		boolean isFull = false;
		
		if (character.getBackpack().size() < 10) {
			this.character.addItemIntoBackpack(item);
			return isFull;
		}
		
		else
			return isFull;
	}
	
	/**
	 * This method exchange the random item from non player character
	 * 
	 * @param playerExchangeItem
	 * @param playerExchangeItemIndex
	 * @param npCharacter
	 */
	public void exchangeWithNPC(Item playerExchangeItem, int playerExchangeItemIndex, Character npCharacter) {
		ArrayList<Item> npCharacterItems = new ArrayList<Item>();
		Random randomGenerator = new Random();
		int index;
		
		npCharacterItems.addAll(npCharacter.getBackpack());
		npCharacterItems.add(npCharacter.getHelmet());
		npCharacterItems.add(npCharacter.getarmor());
		npCharacterItems.add(npCharacter.getShield());
		npCharacterItems.add(npCharacter.getRing());
		npCharacterItems.add(npCharacter.getBelt());
		npCharacterItems.add(npCharacter.getBoots());
		npCharacterItems.add(npCharacter.getWeapon());
		
		index = randomGenerator.nextInt(npCharacterItems.size());
		
		this.character.getBackpack().set(playerExchangeItemIndex, npCharacterItems.get(index));
	}
	
	/**
	 * This method set the objective of current map
	 * 
	 * @param isMapObjectiveFulfill
	 */
	public void setMapObjective(boolean isMapObjectiveFulfill) {
		this.isMapObjFulfil = isMapObjectiveFulfill;
	}
	
	/**
	 * This method return the current map objective
	 * 
	 * @return
	 */
	public boolean getMapObjective() {
		return this.isMapObjFulfil;
	}

	@Override
	public void run() {
		Campaign campaign = this.campaign;
		boolean isMapNPCItemLevelSet = false;
		
		for (Map map : campaign.getCampaignList()) {
			while (!isMapObjFulfil) {
				if (!isMapNPCItemLevelSet) {
					this.currentMap = map;
					setCharacterItemLevel(this.currentMap, this.character);
					isMapNPCItemLevelSet = true;
				}
			}
			this.isMapObjFulfil = false;
			isMapNPCItemLevelSet = false;
		}
	}
}
