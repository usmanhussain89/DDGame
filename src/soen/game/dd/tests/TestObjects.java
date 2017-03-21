package soen.game.dd.tests;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import java.awt.Point;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;

import javax.print.DocFlavor.CHAR_ARRAY;

import org.junit.Before;
import org.junit.Test;

import soen.game.dd.fileio.ItemIO;
import soen.game.dd.models.CharacterAttribute;
import soen.game.dd.models.CharacterBuilder;
import soen.game.dd.models.DummyGameEngine;
import soen.game.dd.models.FighterType;
import soen.game.dd.models.Item;
import soen.game.dd.models.ItemType;
import soen.game.dd.models.Map;
import soen.game.dd.models.TankCharacterBuilder;
import soen.game.dd.models.BullyCharacterBuilder;
import soen.game.dd.models.Campaign;
import soen.game.dd.models.Character;

/**
 * This class to test different objects creating, saving, loading. Object like
 * Item, Character, Map and campaign
 * 
 * @author fyounis
 *
 */
public class TestObjects {
	
	public Map map  = new Map(10, 10);
	public Map map2 = new Map(10, 10);
	public Map map3 = new Map(10, 10);
	public Map map4 = new Map(10, 10);
	
	public Map addComponentsToMap(Map map){
		Map editMap = map;
		
		Point point = new Point(1, 1);
		editMap.setEntryPoint(point);
		
		point.setLocation(10 , 10);
		editMap.setEntryPoint(point);
		
		point.setLocation(1, 1);
		editMap.setCharacterPoint(point);
		
		point.setLocation(5, 5);
		editMap.setChestPoint(point);
		
		
		
		
		
		
		return editMap;
		
	}
	
	public Campaign BlackCampaign = new Campaign();
	
	
	
	
	
	
	@Before
	public void initialize() {
		
		

	}
	/**
	 * @author fyounis
	 * this will test the creation of the character
	 */
	@Test
	public void character_Create_Test(){
		
		Item redHelmet = new Item("RedHelmet", ItemType.HELMET, CharacterAttribute.INTELLIGENCE, 2, 5, null);
		Item redArmor = new Item("redArmor", ItemType.ARMOR, CharacterAttribute.INTELLIGENCE, 2, 5, null);
		Item redRing = new Item("redRing", ItemType.RING, CharacterAttribute.INTELLIGENCE, 2, 5, null);
		Item redBoots = new Item("redBoots", ItemType.BOOTS, CharacterAttribute.INTELLIGENCE, 2, 5, null);
		Item redWeapon = new Item("redWeapon", ItemType.WEAPON, CharacterAttribute.INTELLIGENCE, 2, 5, "melee");
		Item redShield = new Item("redShield", ItemType.SHIELD, CharacterAttribute.INTELLIGENCE, 2, 5, null);
		Item redBelt = new Item("redBelt", ItemType.BELT, CharacterAttribute.INTELLIGENCE, 2, 5, null);
		
		
		
		Character character = new Character("Feras", "The Greater", FighterType.BULLY,
				 7, 7, 7, 7, 7, 10, redArmor, redRing, redHelmet, redBoots, redBelt, redWeapon, redShield);
		System.out.println(character);
		
		Character bully = new BullyCharacterBuilder();
		
		
		assertTrue("<Info> : The Helmet is not valid",character.getHelmet().isValid());
		
		
	}
	
	/**
	 * THis test will test the dummy engine of the game
	 * 
	 */
	@Test
	public void engine_Create_Test(){
		
		
		Item redHelmet = new Item("RedHelmet", ItemType.HELMET, CharacterAttribute.INTELLIGENCE, 2, 5, null);
		Item redArmor = new Item("redArmor", ItemType.ARMOR, CharacterAttribute.INTELLIGENCE, 2, 5, null);
		Item redRing = new Item("redRing", ItemType.RING, CharacterAttribute.INTELLIGENCE, 2, 5, null);
		Item redBoots = new Item("redBoots", ItemType.BOOTS, CharacterAttribute.INTELLIGENCE, 2, 5, null);
		Item redWeapon = new Item("redWeapon", ItemType.WEAPON, CharacterAttribute.INTELLIGENCE, 2, 5, "melee");
		Item redShield = new Item("redShield", ItemType.SHIELD, CharacterAttribute.INTELLIGENCE, 2, 5, null);
		Item redBelt = new Item("redBelt", ItemType.BELT, CharacterAttribute.INTELLIGENCE, 2, 5, null);
		
		
		addComponentsToMap(map);
		addComponentsToMap(map2);
		addComponentsToMap(map3);
		addComponentsToMap(map4);

		BlackCampaign.setCampaignList(map);
		BlackCampaign.setCampaignList(map2);
		BlackCampaign.setCampaignList(map3);
		BlackCampaign.setCampaignList(map4);
		
		Character munjed = new Character("Feras", "The Greater", FighterType.BULLY,
				 7, 7, 7, 7, 7, 10, redArmor, redRing, redHelmet, redBoots, redBelt, redWeapon, redShield);
		
		DummyGameEngine testEngine = new DummyGameEngine(BlackCampaign, munjed);
		
	}
	/**
	 * This test will test create campaign 
	 */
	@Test
	public void campaign_Create_Test(){
		Map map  = new Map(5, 6);
		Map map2 = new Map(8, 7);
		Map map3 = new Map(10, 22);
		Map map4 = new Map(22, 22);
		
		Campaign RedCampaign = new Campaign();
		RedCampaign.setCampaignList(map);
		RedCampaign.setCampaignList(map2);
		RedCampaign.setCampaignList(map3);
		RedCampaign.setCampaignList(map4);
		
	}
	/**
	 * @author fyounis
	 * This will test the creation of the characterBuilder_Create_Test
	 */
	@Test
	public void characterBuilder_Create_Test(){
		
		Item redHelmet = new Item("RedHelmet", ItemType.HELMET, CharacterAttribute.INTELLIGENCE, 2, 5, null);
		Item redArmor = new Item("redArmor", ItemType.ARMOR, CharacterAttribute.INTELLIGENCE, 2, 5, null);
		Item redRing = new Item("redRing", ItemType.RING, CharacterAttribute.INTELLIGENCE, 2, 5, null);
		Item redBoots = new Item("redBoots", ItemType.BOOTS, CharacterAttribute.INTELLIGENCE, 2, 5, null);
		Item redWeapon = new Item("redWeapon", ItemType.WEAPON, CharacterAttribute.INTELLIGENCE, 2, 5, "melee");
		Item redShield = new Item("redShield", ItemType.SHIELD, CharacterAttribute.INTELLIGENCE, 2, 5, null);
		Item redBelt = new Item("redBelt", ItemType.BELT, CharacterAttribute.INTELLIGENCE, 2, 5, null);
		
		Character character = new Character("Feras", "The Greater", FighterType.BULLY,
				 10, 10, 10, 10, 5 ,5, redArmor, redRing, redHelmet, redBoots, redBelt, redWeapon, redShield);
		
		CharacterBuilder bully = new BullyCharacterBuilder();
		CharacterBuilder tank  = new TankCharacterBuilder();
		
		Character bullyRed;
		Character tankRed;
		
		
		
	}

	/**
	 * This Test will test the validation of an item
	 */
	@Test
	public void item_Create_Test() {

		System.out.println("The item_Create_Test is running");

		Item redHelmet = new Item("RedHelmet", ItemType.HELMET, CharacterAttribute.INTELLIGENCE, 2, 5, "melee");
		assertTrue("The Item: " + redHelmet.getName() + " is not a valid Item", redHelmet.isValid());
		System.out.println(" The Item: " + redHelmet.getName() + " was created ");
		System.out.println(redHelmet.toString() + "\n");

		Item redHelmet5 = new Item("RedHelmet5", ItemType.HELMET, CharacterAttribute.INTELLIGENCE, 5, 8, "melee");
		assertTrue("The Item: " + redHelmet5.getName() + " is not a valid Item", redHelmet5.isValid());
		System.out.println(" The Item: " + redHelmet5.getName() + " was created ");
		System.out.println(redHelmet5.toString() + "\n");

		Item redHelmet4 = new Item("RedHelmet4", ItemType.HELMET, CharacterAttribute.DAMAGE_BONUS, 4, 2, "ranged");
		assertFalse("The Item: " + redHelmet4.getBonusAmount() + " is not a valid Item", redHelmet4.isValid());
		System.out.println(" The Item: " + redHelmet4.getName() + " was created ");
		System.out.println(redHelmet4.toString() + "\n");

	}

	/**
	 * This Test will test the validation of an item
	 */

	@Test
	public void item_Create_Test2() {

		System.out.println("The item_Create_Test2 is running");

		Item redHelmet1 = new Item("RedHelmet1", ItemType.HELMET, CharacterAttribute.INTELLIGENCE, 7, 10, "ranged");
		assertFalse("The Item: " + redHelmet1.getName() + " is not a valid Item", redHelmet1.isValid());
		System.out.println(" The Item: " + redHelmet1.getName() + " was NOT created ");
		System.out.println(redHelmet1.toString() + "\n");

		Item redHelmet2 = new Item("RedHelmet2", ItemType.HELMET, CharacterAttribute.INTELLIGENCE, 0, 0, "melee");
		assertFalse("The Item: " + redHelmet2.getName() + " is not a valid Item", redHelmet2.isValid());
		System.out.println(" The Item: " + redHelmet2.getName() + " was NOT created ");
		System.out.println(redHelmet2.toString() + "\n");

		Item redHelmet3 = new Item("RedHelmet3", ItemType.HELMET, CharacterAttribute.INTELLIGENCE, -5, 1, "melee");
		assertFalse("The Item: " + redHelmet3.getName() + " is not a valid Item", redHelmet3.isValid());
		System.out.println(" The Item: " + redHelmet3.getName() + " was NOT created ");
		System.out.println(redHelmet3.toString() + "\n");

	}

	/**
	 * This Test will test the validation of an map
	 */
	@Test
	public void mapValidity_Test() {
		Map map = new Map(25, 20);
		int x = map.getMapWidth();
		int y = map.getMapHeight();

		assertEquals(x, 25);
		assertEquals(y, 20);
	}

	/**
	 * This Test will test the validation of an map
	 */
	@Test
	public void mapValidity_Test2() {

		Map map = new Map(44, 22);
		assertFalse("the Map: " + map.getMapName() + " is valid", map.isValid());

		Map map2 = new Map(-5, 10);
		assertFalse(map2.isValid());

		Map map3 = new Map(5, 77);
		assertFalse(map3.isValid());

		Map map4 = new Map(-5, -15);
		assertFalse(map4.isValid());

		Map map5 = new Map(24, 24);
		assertTrue(map5.isValid());

	}

	/**
	 * This Test will test the validation of an map
	 */
	@Test
	public void mapValidity_Test3() {
		Map map = new Map();
		map.isEntryDone = true;
		map.isExitDone = true;
		map.isChestDone = true;
		map.isCharacterDone = true;
		map.isOpponentDone = true;

		assertEquals(true, map.isEntryDone);
		assertEquals(true, map.isExitDone);
		assertEquals(true, map.isChestDone);
		assertEquals(true, map.isCharacterDone);
		assertEquals(true, map.isOpponentDone);
	}

	/**
	 * This Test will test the validation saving an item
	 */
	@Test

	public void saveLoadObjects_Test() throws FileNotFoundException, ClassNotFoundException, IOException {

		// create new item Object
		Item blackHelmet = new Item("Black Helmet", ItemType.HELMET, CharacterAttribute.INTELLIGENCE, 4, 4, "ranged");
		// save item object
		ItemIO fileWriterReader = new ItemIO();
		fileWriterReader.saveItem(blackHelmet);
		ArrayList<Item> listItem = fileWriterReader.loadItems();

		boolean found = false;
		for (Item item : listItem) {
			if (item.getName().equalsIgnoreCase("Black Helmet")) {
				found = true;
				break;
			}

		}
		assertTrue(found);

	}
}
