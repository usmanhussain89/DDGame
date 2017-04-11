package soen.game.dd.models;

import java.awt.Point;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Observable;
import java.util.Random;

import soen.game.dd.character.strategys.AggressiveNPCStrategy;
import soen.game.dd.character.strategys.FriendlyNPCStrategy;
import soen.game.dd.character.strategys.HumanStrategy;
import soen.game.dd.statics.content.GameStatics;

/**
 * This is Game Engine class
 * 
 * @author Usman
 *
 */
public class GameEngine extends Observable {

	private Campaign campaign;
	private Character character;
	private Map currentMap;
	private boolean isMapObjFulfil = false;
	private int currentMapIndex;
	private Point characterPosition;
	private int characterMoved;
	private HashMap<Character, Point> positions;

	/**
	 * This is constructor of the class which initialize campaign and character
	 * object
	 * 
	 * @param campaign
	 * @param character
	 */
	public GameEngine(Campaign campaign, Character character) {
		this.campaign = campaign;
		this.positions = new HashMap<Character, Point>();
		this.character = character;
		this.character.setNPCType(NPCType.PLAYABALE);
		this.currentMapIndex = 0;
		resetCharacterPosition();
	}

	public GameEngine(Character charater, Campaign campaign) {
		this.campaign = campaign;
		this.character = charater;
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
	 * 
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
	 * 
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
			setHitPoints();
			setStrategies();
			setPositions();
			System.out.println("<Game Logging> : Get Ready... We are setting this Map: "+this.currentMap.getMapName());
		}

		else
			this.currentMap = null;
	}
	
	public HashMap<Character, Point> getPositions(){
		return positions;
	}
	
	public void setPositions() {
		for (int i = 0; i < this.currentMap.getMapHeight(); i++) {
			for (int j = 0; j < this.currentMap.getMapWidth(); j++) {
				if (this.currentMap.mapGridSelection[i][j] == GameStatics.MAP_ENTRY_POINT){
					positions.put(getCharacter(), new Point(i,j));
				}
				if (this.currentMap.mapGridSelection[i][j] == GameStatics.MAP_CHARACTER_POINT){
					positions.put(this.currentMap.getFriendlyCharacter(), new Point(i,j));
				}
				if (this.currentMap.mapGridSelection[i][j] == GameStatics.MAP_OPPONENT_POINT){
					positions.put(this.currentMap.getHostileCharacter(), new Point(i,j));
				}
			}
		}
	}

	public void startGame(){
		System.out.println("<Game Logging> : The Game is STARTED GOOD LUCK!");
		new Thread(new Runnable() {
		     public void run() {
		    	List<Character> characters = getOrderedCharacters();
		 		while(true){
		 			for (Character character : characters){
		 				character.getStrategy().turn();
		 			}
		 		}}
		}).start();
	}

	private void setStrategies() {
		getCharacter().setStrategy(new HumanStrategy(this));
		for (Character character : getCurrentMap().mapCharacters){
			setStrategyForCharacter(character);
		}
		
	}

	private void setStrategyForCharacter(Character character) {
		switch(character.getNPCType()){
			case FRINDLY:
				character.setStrategy(new FriendlyNPCStrategy(character, this));
				break;
			case HOSTILE:
				character.setStrategy(new AggressiveNPCStrategy(character, this));
				break;
		}
	}

	/**
	 * This method return the Current Map
	 * 
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
		int indexCharacterbackpackItem = 0;
		while (iterChars.hasNext() && indexCharacter < currentMap.mapCharacters.size()) {
			indexCharacterbackpackItem = 0;
			this.currentMap.mapCharacters.get(indexCharacter).setLevel(playerCharacterLevel);

			if (playerCharacterLevel >= 1 && playerCharacterLevel <= 4) {
				this.currentMap.mapCharacters.get(indexCharacter).getarmor().setBonusAmount(1);
				this.currentMap.mapCharacters.get(indexCharacter).getRing().setBonusAmount(1);
				this.currentMap.mapCharacters.get(indexCharacter).getHelmet().setBonusAmount(1);
				this.currentMap.mapCharacters.get(indexCharacter).getBoots().setBonusAmount(1);
				this.currentMap.mapCharacters.get(indexCharacter).getBelt().setBonusAmount(1);
				this.currentMap.mapCharacters.get(indexCharacter).getWeapon().setBonusAmount(1);
				this.currentMap.mapCharacters.get(indexCharacter).getShield().setBonusAmount(1);
				System.out.println("<Game Logging> : The Player Level: "+playerCharacterLevel);
				System.out.println("<Game Logging> : The Map Level is set to 1");
			}
			if (playerCharacterLevel >= 5 && playerCharacterLevel <= 8) {
				this.currentMap.mapCharacters.get(indexCharacter).getarmor().setBonusAmount(2);
				this.currentMap.mapCharacters.get(indexCharacter).getRing().setBonusAmount(2);
				this.currentMap.mapCharacters.get(indexCharacter).getHelmet().setBonusAmount(2);
				this.currentMap.mapCharacters.get(indexCharacter).getBoots().setBonusAmount(2);
				this.currentMap.mapCharacters.get(indexCharacter).getBelt().setBonusAmount(2);
				this.currentMap.mapCharacters.get(indexCharacter).getWeapon().setBonusAmount(2);
				this.currentMap.mapCharacters.get(indexCharacter).getShield().setBonusAmount(2);
				System.out.println("<Game Logging> : The Player Level: "+playerCharacterLevel);
				System.out.println("<Game Logging> : The Map Level is set to 2");
			}
			if (playerCharacterLevel >= 9 && playerCharacterLevel <= 12) {
				this.currentMap.mapCharacters.get(indexCharacter).getarmor().setBonusAmount(3);
				this.currentMap.mapCharacters.get(indexCharacter).getRing().setBonusAmount(3);
				this.currentMap.mapCharacters.get(indexCharacter).getHelmet().setBonusAmount(3);
				this.currentMap.mapCharacters.get(indexCharacter).getBoots().setBonusAmount(3);
				this.currentMap.mapCharacters.get(indexCharacter).getBelt().setBonusAmount(3);
				this.currentMap.mapCharacters.get(indexCharacter).getWeapon().setBonusAmount(3);
				this.currentMap.mapCharacters.get(indexCharacter).getShield().setBonusAmount(3);
				System.out.println("<Game Logging> : The Player Level: "+playerCharacterLevel);
				System.out.println("<Game Logging> : The Map Level is set to 3");
			}
			if (playerCharacterLevel >= 13 && playerCharacterLevel <= 16) {
				this.currentMap.mapCharacters.get(indexCharacter).getarmor().setBonusAmount(4);
				this.currentMap.mapCharacters.get(indexCharacter).getRing().setBonusAmount(4);
				this.currentMap.mapCharacters.get(indexCharacter).getHelmet().setBonusAmount(4);
				this.currentMap.mapCharacters.get(indexCharacter).getBoots().setBonusAmount(4);
				this.currentMap.mapCharacters.get(indexCharacter).getBelt().setBonusAmount(4);
				this.currentMap.mapCharacters.get(indexCharacter).getWeapon().setBonusAmount(4);
				this.currentMap.mapCharacters.get(indexCharacter).getShield().setBonusAmount(4);
				System.out.println("<Game Logging> : The Player Level: "+playerCharacterLevel);
				System.out.println("<Game Logging> : The Map Level is set to 4");
			}
			if (playerCharacterLevel >= 17) {
				this.currentMap.mapCharacters.get(indexCharacter).getarmor().setBonusAmount(5);
				this.currentMap.mapCharacters.get(indexCharacter).getRing().setBonusAmount(5);
				this.currentMap.mapCharacters.get(indexCharacter).getHelmet().setBonusAmount(5);
				this.currentMap.mapCharacters.get(indexCharacter).getBoots().setBonusAmount(5);
				this.currentMap.mapCharacters.get(indexCharacter).getBelt().setBonusAmount(5);
				this.currentMap.mapCharacters.get(indexCharacter).getWeapon().setBonusAmount(5);
				this.currentMap.mapCharacters.get(indexCharacter).getShield().setBonusAmount(5);
				System.out.println("<Game Logging> : The Player Level: "+playerCharacterLevel);
				System.out.println("<Game Logging> : The Map Level is set to 5");
			}

			if (this.currentMap.mapCharacters.get(indexCharacter).getBackpack() != null) {
				for (Item item : this.currentMap.mapCharacters.get(indexCharacter).getBackpack()) {
					if (playerCharacterLevel >= 1 && playerCharacterLevel <= 4) {
						this.currentMap.mapCharacters.get(indexCharacter).getBackpack()
								.set(indexCharacterbackpackItem, item).setBonusAmount(1);
						indexCharacterbackpackItem++;
					}

					if (playerCharacterLevel >= 5 && playerCharacterLevel <= 8) {
						this.currentMap.mapCharacters.get(indexCharacter).getBackpack()
								.set(indexCharacterbackpackItem, item).setBonusAmount(2);
						indexCharacterbackpackItem++;
					}

					if (playerCharacterLevel >= 9 && playerCharacterLevel <= 12) {
						this.currentMap.mapCharacters.get(indexCharacter).getBackpack()
								.set(indexCharacterbackpackItem, item).setBonusAmount(3);
						indexCharacterbackpackItem++;
					}

					if (playerCharacterLevel >= 13 && playerCharacterLevel <= 16) {
						this.currentMap.mapCharacters.get(indexCharacter).getBackpack()
								.set(indexCharacterbackpackItem, item).setBonusAmount(4);
						indexCharacterbackpackItem++;
					}

					if (playerCharacterLevel >= 17) {
						this.currentMap.mapCharacters.get(indexCharacter).getBackpack()
								.set(indexCharacterbackpackItem, item).setBonusAmount(5);
						indexCharacterbackpackItem++;
					}
				}
			}
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
	 * 
	 * @param item
	 * @return backpack status
	 */
	public boolean lootChestItems(List<Item> items) {

		for (Item item : items) {
			if (character.getBackpack().size() < 10) {
				this.character.addItemIntoBackpack(item);
				this.character.notifyObservers();
				System.out.println("<Game Logging> : Looting this item: "+item.getName());	
			}
		}

		System.out.println("<Game Logging> : Can not Loot anymore! the backpack is full");
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

		System.out.println("<Game Logging> : you: "+this.character.getName()+" are Exchanging with NPC: "+npCharacter.getName());
		
		index = randomGenerator.nextInt(npCharacter.getBackpack().size());
		Item itemToGive = npCharacter.getBackpack().get(index);

		this.character.addItemIntoBackpack(itemToGive);
		System.out.println("<Game Logging> : you gave this Item: "+itemToGive.getName()+" to the NPC: "+npCharacter.getName());
		this.character.removeItemFromBackpack(playerExchangeItem);

		npCharacter.addItemIntoBackpack(playerExchangeItem);
		System.out.println("<Game Logging> : you got this Item: "+playerExchangeItem.getName()+" From the NPC: "+npCharacter.getName());
		npCharacter.removeItemFromBackpack(itemToGive);

		setChanged();
		this.character.notifyObservers();
		npCharacter.notifyObservers();
		System.out.println("<Game Logging> : "+playerExchangeItem.getName()+" Says: Thank you and GoodBye");
		System.out.println("<Game Logging> : "+npCharacter.getName()+" The NPC ansert: You are welcome GoodBye");

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

	public boolean nextMap() {
		currentMapIndex++;
		setCurrentMap();
		if (getCurrentMap() != null) {
			setChanged();
			return true;
		}

		else {
			setChanged();
			return false;
		}
	}

	public void resetCharacterPosition() {
		setCurrentMap();
		characterPosition = getCurrentMap().getEntryPoint();
	}

	public Point getCharacterPosition() {
		return characterPosition;
	}
	
	public Point getPositionOfCharacter(Character character) {
		return positions.get(character);
	}
	
	public boolean withinOneSpace(Point point1, Point point2){
		if ((Math.abs(point1.getX() - point2.getX()) == 1) && (point1.getY() == point2.getY())){
			return true;
		}
		if ((Math.abs(point1.getY() - point2.getY()) == 1) && (point1.getX() == point2.getX())){
			return true;
		}	
		return false;
	}
	
	public void move(Character character, int x, int y) {
		int pathPoint = getCurrentMap().mapGridSelection[x][y];
		if (pathPoint == GameStatics.MAP_PATH_POINT){
			if (withinOneSpace(getPositionOfCharacter(character), new Point(x, y))){
				positions.put(character, new Point(x, y));
				setChanged();
				characterMoved++;
				System.out.println("<Game Logging> : The "+character.getName()+" moved to the point"+positions.toString());

			}
		}
	}

	public void interactWith(int x, int y) {
		int pathPoint = getCurrentMap().mapGridSelection[x][y];
		if (pathPoint == GameStatics.MAP_PATH_POINT){
			characterPosition = new Point(x, y); 
			System.out.println("<Game Logging> : Moved to the point"+positions.toString());
		}
		
		else if (pathPoint == GameStatics.MAP_CHEST_POINT) {
			System.out.println("<Game Logging> : Let the Loot begins");
			lootChestItems(getCurrentMap().mapSelectedItem);
			getCurrentMap().mapSelectedItem = new ArrayList<Item>();

		}
		/*
		 * else if (pathPoint == GameStatics.MAP_OPPONENT_POINT &&
		 * getCurrentMap().){ lootChestItems(getCurrentMap().mapSelectedItem);
		 * getCurrentMap().mapSelectedItem = new ArrayList<Item>(); }
		 */
		characterMoved++;
		setChanged();
	}

	/**
	 * This method uses the hit method to calculate the hit points
	 * 
	 * @param playable
	 * @param hostile
	 * @return
	 */
	public int encounter(Character playable, Character hostile) {
		System.out.println("<Game Logging> : Let the Encounter begain!");
		System.out.println("<Game Logging> : Fight! Fight! Fight!");
		System.out.println("<Game Logging> : The hostile: "+hostile.getName()+" HP before fight is: "+hostile.getHitPoint());
		if (hostile.getHitPoint() > 0) {
			hostile.hitPoint -= new Hit().getDamagePoint(playable, hostile, NPCType.HOSTILE, playable.getWeapon());
			hostile.callSetChanged();
			hostile.notifyObservers();
			System.out.println("<Game Logging> : The "+hostile.getName()+" Got hit and his HP is now: "+hostile.getHitPoint());
			if (hostile.getHitPoint() <= 0){
				System.out.println("<Game Logging> : The "+hostile.getName()+" is Dead already and his HP now: "+hostile.getHitPoint());
				return 0;
			}
			else
				return (int) hostile.getHitPoint();
		} else
			return 0;
	}

	/**
	 * This method is to set Hit points
	 * no need for logging it is done from the character class
	 */
	public void setHitPoints() {
		for (Character character : getCharacters()) {
			character.setMaxHitPoint();
			character.setHitpoint(character.getMaxHitPoint());
		}
	}
	
	public List<Character> getCharacters() {
		List<Character> characters = new ArrayList<Character>();
		characters = (List<Character>) this.getCurrentMap().mapCharacters.clone();
		characters.add(getCharacter());
		return characters;
	}
	
	/**
	 * Get map character using D20roll
	 * @return list of characters
	 */
	public List<Character> getOrderedCharacters(){
		List<Character> characters = new ArrayList<Character>();

		HashMap<Integer, Character> charactersMap = new HashMap<Integer, Character>();
		Integer[] d20Dies = new Integer[getCharacters().size()];
		int index = 0;
		
		for (Character c : getCharacters()) {
			boolean diceStatus = true;
			while (diceStatus) {
				System.out.println("<Game Logging> : Roll the d20 Dic to determine the order of: "+c.getName()+" and his type is: "+c.getNPCType());
				int d20 = d20Dice();
				if (checkUniqueDice(d20, d20Dies)) {
					d20Dies[index] = d20;
					charactersMap.put(d20, c);
					diceStatus = false;
					index++;
					System.out.println("<Game Logging> : Rolled the Dic and after applying d20 roles his value is: "+d20);
					
				}
			}
		}
		
		Arrays.sort(d20Dies, Collections.reverseOrder());
		
		for (Integer i : d20Dies) {
			characters.add(charactersMap.get(i));
		}
		/**
		 * @author fyounis
		 * to print out the Characters order after applying the d20 rolls 
		 */
		
		int index2 = 0;
		for (Character character : characters) {
			index2++;
			System.out.println("<Game Logging> : This player"+character.getName()+" will play "+index2);
		}
		
		return characters;
	}
	
	/**
	 * Generate random score
	 * 
	 * @return
	 */
	private int d20Dice() {
		int score = (int) (Math.random() * 20) + 1;
		System.out.println("<Game Logging> : the d20 Dice was played and it was = "+score);

		return score;
	}
	
	/**
	 * This method checks unique dice
	 * 
	 * @param dice
	 * @param d20Dices
	 * @return
	 */
	private boolean checkUniqueDice(int dice, Integer[] d20Dices) {
		boolean status = true;
		
		
		for (Integer d : d20Dices) {
			if (d != null) {
				if (dice == d) {
					return false;
				}
			}
		}
		
		return status;
	}

	/**
	 * This is method added the looted item to the character backpack
	 * 
	 * @param item
	 * @return backpack status
	 */
	public boolean lootHostileItems(List<Item> items) {

		for (Item item : items) {
			if (character.getBackpack().size() < 10) {
				System.out.println("<Game Logging> : Looting this item: "+item);
				this.character.addItemIntoBackpack(item);
				this.character.notifyObservers();
			}
		}

		System.out.println("<Game Logging> : can not Loot The backpack is full!");
		return character.getBackpack().size() == 10;
	}

	public void setCharacterMoved(int i) {
		characterMoved = 0;
	}

	public int getCharacterMoved() {
		return characterMoved;
	}
}
