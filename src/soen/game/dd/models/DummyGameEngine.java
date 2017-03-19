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
public class DummyGameEngine {
	
	private Campaign campaign;
	private Character character;
	private Map currentMap;
	
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
		
		while (iterChars.hasNext()) {
			currentMap.mapCharacters.get(indexCharacter).setLevel(playerCharacterLevel);
			indexCharacter++;
		}
		
		while (iterItems.hasNext()) {
			if (playerCharacterLevel >= 1 && playerCharacterLevel <= 4) {
				currentMap.mapSelectedItem.get(indexItem).setBonusAmount(1);
				indexItem++;
			}
			
			if (playerCharacterLevel >= 5 && playerCharacterLevel <= 8) {
				currentMap.mapSelectedItem.get(indexItem).setBonusAmount(2);
				indexItem++;
			}
			
			if (playerCharacterLevel >= 9 && playerCharacterLevel <= 12) {
				currentMap.mapSelectedItem.get(indexItem).setBonusAmount(3);
				indexItem++;
			}
			
			if (playerCharacterLevel >= 13 && playerCharacterLevel <= 16) {
				currentMap.mapSelectedItem.get(indexItem).setBonusAmount(4);
				indexItem++;
			}
			
			if (playerCharacterLevel >= 17) {
				currentMap.mapSelectedItem.get(indexItem).setBonusAmount(5);
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
		
		if (character.getBackpack().size() < 20) {
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
}
