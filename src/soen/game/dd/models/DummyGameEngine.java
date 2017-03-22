package soen.game.dd.models;

import java.awt.Point;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Observable;
import java.util.Random;

import soen.game.dd.statics.content.GameStatics;

/**
 * This is Game Engine class 
 * 
 * @author Usman
 *
 */
public class DummyGameEngine extends Observable{
	
	private Campaign campaign;
	private Character character;
	private Map currentMap;
	private boolean isMapObjFulfil = false;
	private Thread t;
	private int currentMapIndex;
	private Point characterPosition;
	
	/**
	 * This is constructor of the class which initialize campaign and character object
	 * 
	 * @param campaign
	 * @param character
	 */
	public DummyGameEngine(Campaign campaign, Character character) {
		this.campaign = campaign;
		this.character = character;
		this.character.setNPCType(NPCType.PLAYABALE);
		this.currentMapIndex = 0;
		resetCharacterPosition();
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
	 */
	public void setCurrentMap() {
		if (currentMapIndex < campaign.getCampaignList().size()) {
			this.currentMap = campaign.getCampaignList().get(currentMapIndex);
			setCharacterItemLevel(this.currentMap, character);
		}
		
		else
			this.currentMap = null;
	}
	
	/**
	 * This method return the Current Map
	 * @return
	 */
	public Map getCurrentMap() {
		/*if (currentMapIndex >= campaign.getCampaignList().size()){
			return null;
		}*/
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
	public boolean lootChestItems(List<Item> items) {
		
		for (Item item : items){
			if (character.getBackpack().size() < 10) {
				System.out.println("Adding item " + item.getName());
				this.character.addItemIntoBackpack(item);
				this.character.notifyObservers();
			}
		}
		
		return character.getBackpack().size() == 10;
	}
	
	/**
	 * This method exchange the random item from non player character
	 * 
	 * @param playerExchangeItem
	 * @param playerExchangeItemIndex
	 * @param npCharacter
	 */
	public void exchangeWithNPC(Item playerExchangeItem) {
		Random randomGenerator = new Random();
		int index;
		Character npCharacter = getCurrentMap().getFriendlyCharacter();
				
		index = randomGenerator.nextInt(npCharacter.getBackpack().size());
		Item itemToGive = npCharacter.getBackpack().get(index);
		
		this.character.addItemIntoBackpack(itemToGive);
		this.character.removeItemFromBackpack(playerExchangeItem);
		
		npCharacter.addItemIntoBackpack(playerExchangeItem);
		npCharacter.removeItemFromBackpack(itemToGive);
		
		setChanged();
		this.character.notifyObservers();
		npCharacter.notifyObservers();
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

	
	public boolean nextMap(){
		currentMapIndex++;
		setCurrentMap();
		if (getCurrentMap() != null) {
			resetCharacterPosition();
			setChanged();
			return true;
		}
		
		else {
			setChanged();
			return false;
		}
	}
	
	public void resetCharacterPosition(){
		setCurrentMap();
		characterPosition = getCurrentMap().getEntryPoint();
	}

	public Point getCharacterPosition(){
		return characterPosition;
	}

	public void interactWith(int x, int y) {
		int pathPoint = getCurrentMap().mapGridSelection[x][y];
		if (pathPoint == GameStatics.MAP_PATH_POINT)
			characterPosition = new Point(x,y);
		else if (pathPoint == GameStatics.MAP_CHEST_POINT){
			lootChestItems(getCurrentMap().mapSelectedItem);
			getCurrentMap().mapSelectedItem = new ArrayList<Item>();
		}
		/*else if (pathPoint == GameStatics.MAP_OPPONENT_POINT && getCurrentMap().){
			lootChestItems(getCurrentMap().mapSelectedItem);
			getCurrentMap().mapSelectedItem = new ArrayList<Item>();
		}*/
		else if (pathPoint == GameStatics.MAP_CHARACTER_POINT){
			
		}
		setChanged();
	}
	
	/**
	 * This method uses the hit method to calculate the hit points
	 * @param playable
	 * @param hostile
	 * @return
	 */
	public int encounter(Character playable, Character hostile) {
		System.out.println(hostile.getHitPoint());
		if (hostile.getHitPoint() > 0) {
			hostile.hitPoint -= new Hit().getDamagePoint(playable, hostile, NPCType.HOSTILE, playable.getWeapon());
			System.out.println(hostile.hitPoint);
			if (hostile.getHitPoint() <= 0)
				return 0;
			else
				return (int) hostile.getHitPoint();
		}
		else
			return 0;
	}
	
	/**
	 * This method is to set Hit points
	 */
	public void setHitPoints() {
		int index = 0;
		for (Character character : getCurrentMap().mapCharacters){
			if (character.getNPCType() == NPCType.HOSTILE){
				character.setMaxHitPoint();
				character.setHitpoint(character.getMaxHitPoint());
				getCurrentMap().mapCharacters.set(index, character);
			}
			index++;
		}
	}
	
	/**
	 * This is method added the looted item to the character backpack
	 * @param item
	 * @return backpack status
	 */
	public boolean lootHostileItems(List<Item> items) {
		
		for (Item item : items){
			if (character.getBackpack().size() < 10) {
				System.out.println("Adding item " + item.getName());
				this.character.addItemIntoBackpack(item);
				this.character.notifyObservers();
			}
		}
		
		return character.getBackpack().size() == 10;
	}
}
