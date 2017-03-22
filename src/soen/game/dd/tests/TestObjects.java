package soen.game.dd.tests;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import java.awt.Point;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.junit.Before;
import org.junit.Test;

import soen.game.dd.fileio.ItemIO;
import soen.game.dd.fileio.MapIO;
import soen.game.dd.models.BullyCharacterBuilder;
import soen.game.dd.models.Campaign;
import soen.game.dd.models.Character;
import soen.game.dd.models.CharacterAttribute;
import soen.game.dd.models.CharacterBuilder;
import soen.game.dd.models.DummyGameEngine;
import soen.game.dd.models.Fighter;
import soen.game.dd.models.FighterType;
import soen.game.dd.models.Item;
import soen.game.dd.models.ItemType;
import soen.game.dd.models.Map;
import soen.game.dd.models.TankCharacterBuilder;
import soen.game.dd.models.WeaponType;

/**
 * This class to test different objects creating, saving, loading. Object like
 * Item, Character, Map and campaign
 * 
 * @author fyounis
 *
 */
public class TestObjects {

	private Map map = new Map(10, 10);
	private Map map2 = new Map(10, 10);
	private Map map3 = new Map(10, 10);
	private Map map4 = new Map(10, 10);

	private Campaign BlackCampaign;
	private Item redHelmet;
	private Item crazyHelmet;
	private Item redArmor;
	private Item redRing;
	private Item redBoots;
	private Item redWeapon;
	private Item redShield;
	private Item redBelt;
	private Item blackBelt;
	private Item crazyWeapon;

	private Character redFeras;
	private ArrayList<Item> chest;

	public Map addComponentsToMap(Map map) {
		Map editMap = map;

		Point point = new Point(1, 1);
		editMap.setEntryPoint(point);

		point.setLocation(10, 10);
		editMap.setExitPoint(point);

		point.setLocation(1, 1);
		editMap.setCharacterPoint(point);

		point.setLocation(5, 5);
		editMap.setChestPoint(point);

		return editMap;

	}

	@Before
	public void initialize() {
		redHelmet = new Item("RedHelmet", ItemType.HELMET, CharacterAttribute.INTELLIGENCE, 1, 1,
				WeaponType.NotAWeapon);
		crazyHelmet = new Item("crazyHelmet", ItemType.HELMET, CharacterAttribute.INTELLIGENCE, 5, 5,
				WeaponType.NotAWeapon);
		redArmor = new Item("redArmor", ItemType.ARMOR, CharacterAttribute.INTELLIGENCE, 2, 5, WeaponType.NotAWeapon);
		redRing = new Item("redRing", ItemType.RING, CharacterAttribute.INTELLIGENCE, 2, 5, WeaponType.NotAWeapon);
		redBoots = new Item("redBoots", ItemType.BOOTS, CharacterAttribute.INTELLIGENCE, 2, 5, WeaponType.NotAWeapon);
		redWeapon = new Item("redWeapon", ItemType.WEAPON, CharacterAttribute.INTELLIGENCE, 1, 1, WeaponType.MELEE);
		crazyWeapon = new Item("crazyWeapon", ItemType.WEAPON, CharacterAttribute.INTELLIGENCE, 5, 5, WeaponType.MELEE);
		redShield = new Item("redShield", ItemType.SHIELD, CharacterAttribute.INTELLIGENCE, 2, 5,
				WeaponType.NotAWeapon);
		redBelt = new Item("redBelt", ItemType.BELT, CharacterAttribute.INTELLIGENCE, 1, 1, WeaponType.NotAWeapon);
		blackBelt = new Item("BlackBelt", ItemType.BELT, CharacterAttribute.INTELLIGENCE, 5, 5, WeaponType.NotAWeapon);

		redFeras = new Character("Feras", "The Greater", FighterType.BULLY, 7, 7, 7, 7, 7, 10, redArmor, redRing,
				redHelmet, redBoots, redBelt, redWeapon, redShield);

		BlackCampaign = new Campaign();
		BlackCampaign.setCampaignName("BlackCampaign");

		chest = new ArrayList<Item>();
		chest.add(crazyHelmet);
		chest.add(blackBelt);
	}

	/**
	 * Character can not get wear more than one Item type
	 */
	@Test
	public void character_addMoreThanOneItemType_Test() {

		Character blackFeras = new Character("Feras", "The Greater", FighterType.BULLY, 7, 7, 7, 7, 7, 10, redArmor,
				redRing, redHelmet, redArmor, redBelt, redWeapon, redShield);
		blackFeras.setHelmet(redArmor);
		assertEquals("<Info> : This is not Helmot Type Item", redHelmet, blackFeras.getHelmet());

	}
	
	/**
	 * this test will test wearing items should correctly influence the character’s abilities
	 */
	
	@Test
	public void wearing_Items_Should_Correctly_Influence_Character_Abilities_Test() {

		Character jackTheGreate = new Character("Feras", "The Greater", FighterType.BULLY, 7, 7, 7, 7, 7, 10, redArmor,
				redRing, redHelmet, redBoots, redBelt, redWeapon, redShield);
		jackTheGreate.setIntelligenceModifier(2.0);
		jackTheGreate.setAbilityModifier();
		jackTheGreate.setAttackBonus();
		
		int weakerItems = jackTheGreate.getAttackBonus();
		System.out.println("The attack bonus is: "+weakerItems);
		
		
		jackTheGreate.setWeapon(crazyWeapon);
		jackTheGreate.setBelt(blackBelt);
		jackTheGreate.setHelmet(crazyHelmet);
		jackTheGreate.setAbilityModifier();
		jackTheGreate.setAttackBonus();

		int strongerItems = jackTheGreate.getAttackBonus();

		System.out.println("The attack bonus is: "+strongerItems);
		
		
		assertTrue((strongerItems > weakerItems));
		
	}

	/**
	 * @author fyounis this will test the creation of the character
	 */
	@Test
	public void character_Create_Test() {

		Character character = new Character("Feras", "The Greater", FighterType.BULLY, 7, 7, 7, 7, 7, 10, redArmor,
				redRing, redHelmet, redBoots, redBelt, redWeapon, redShield);
		System.out.println(character);

		assertTrue("<Info> : The Helmet is not valid", character.getHelmet().isValid());
		
	}
	/**
	 * this test will test the value of random attribute
	 */
	@Test
	public void character_Attributes_Test() {

		Character character = new Character("Feras", "The Greater", FighterType.BULLY, 7, 7, 7, 7, 7, 10, redArmor,
				redRing, redHelmet, redBoots, redBelt, redWeapon, redShield);
		System.out.println(character);

		assertTrue("<Info> : The Helmet is not valid", character.getHelmet().isValid());
		assertTrue("<Info> : the multipleAttacks: " + character.getMultipleAttacks() + " is not  equal what was set",
				character.getMultipleAttacks() == 10);
	}

	/**
	 * THis test will test the dummy engine of the game campaign 
	 * 
	 */
	@Test
	public void engine_Create_Test() {

		/*addComponentsToMap(map);
		addComponentsToMap(map2);
		addComponentsToMap(map3);
		addComponentsToMap(map4);
*/
		map.mapSelectedItem = chest;
		map2.mapSelectedItem = chest;
		map3.mapSelectedItem = chest;
		map4.mapSelectedItem = chest;

		BlackCampaign.setCampaignList(map);
		BlackCampaign.setCampaignList(map2);
		BlackCampaign.setCampaignList(map3);
		BlackCampaign.setCampaignList(map4);
		System.out.println("First flag");

		Character munjed = new Character("Feras", "The Greater", FighterType.BULLY, 7, 7, 7, 7, 7, 10, redArmor,
				redRing, redHelmet, redBoots, redBelt, redWeapon, redShield);
		System.out.println("First flag");
		
		Character character = new Character("Feras", "The Greater", FighterType.BULLY, 10, 10, 10, 10, 5, 5, redArmor,
				redRing, redHelmet, redBoots, redBelt, redWeapon, redShield);
		Point point = new Point(1, 1);

		Fighter fighterBully = new Fighter();
		Fighter fighterTank = new Fighter();

		CharacterBuilder bully = new BullyCharacterBuilder();
		

		fighterBully.setCharacterBuilder(bully);
		fighterBully.createFighter(character);
		System.out.println(fighterBully);

		DummyGameEngine testEngine = new DummyGameEngine(character, BlackCampaign);
		System.out.println("First flag");

		
		assertEquals("Both Compaings are same",BlackCampaign, testEngine.getCampagin());
		


	}
	@Test
	public void loot_Test() {

		/*addComponentsToMap(map);
		addComponentsToMap(map2);
		addComponentsToMap(map3);
		addComponentsToMap(map4);
*/
		map.mapSelectedItem = chest;
		map2.mapSelectedItem = chest;
		map3.mapSelectedItem = chest;
		map4.mapSelectedItem = chest;

		BlackCampaign.setCampaignList(map);
		BlackCampaign.setCampaignList(map2);
		BlackCampaign.setCampaignList(map3);
		BlackCampaign.setCampaignList(map4);
		System.out.println("First flag");

		Character munjed = new Character("Feras", "The Greater", FighterType.BULLY, 7, 7, 7, 7, 7, 10, redArmor,
				redRing, redHelmet, redBoots, redBelt, redWeapon, redShield);
		System.out.println("2 flag");
		
		Character character = new Character("Feras", "The Greater", FighterType.BULLY, 10, 10, 10, 10, 5, 5, redArmor,
				redRing, redHelmet, redBoots, redBelt, redWeapon, redShield);

		Fighter fighterBully = new Fighter();
		Fighter fighterTank = new Fighter();

		CharacterBuilder bully = new BullyCharacterBuilder();
		

		fighterBully.setCharacterBuilder(bully);
		fighterBully.createFighter(character);
		System.out.println(fighterBully);

		DummyGameEngine testEngine = new DummyGameEngine(munjed, BlackCampaign);
		System.out.println("3 flag");
		
		/*chest.add(crazyHelmet);
		chest.add(blackBelt);*/
		
		testEngine.lootChestItems(chest);
		List<Item> lootedItem =  munjed.getItemIntoBackpack();
		boolean found = false;
		for(Item item : lootedItem){
			found = false;
			if(item.getName().equalsIgnoreCase("crazyHelmet"));
			found = true;
			break;
		}
		

		
		assertTrue(found);
		


	}
	public void engine_Map_Validation_Test() {

		/*addComponentsToMap(map);
		addComponentsToMap(map2);
		addComponentsToMap(map3);
		addComponentsToMap(map4);
*/
		map.mapSelectedItem = chest;
		map2.mapSelectedItem = chest;
		map3.mapSelectedItem = chest;
		map4.mapSelectedItem = chest;

		BlackCampaign.setCampaignList(map);
		BlackCampaign.setCampaignList(map2);
		BlackCampaign.setCampaignList(map3);
		BlackCampaign.setCampaignList(map4);
		System.out.println("First flag");

		Character munjed = new Character("Feras", "The Greater", FighterType.BULLY, 7, 7, 7, 7, 7, 10, redArmor,
				redRing, redHelmet, redBoots, redBelt, redWeapon, redShield);
		System.out.println("First flag");
		
		Character character = new Character("Feras", "The Greater", FighterType.BULLY, 10, 10, 10, 10, 5, 5, redArmor,
				redRing, redHelmet, redBoots, redBelt, redWeapon, redShield);

		Fighter fighterBully = new Fighter();
		Fighter fighterTank = new Fighter();

		CharacterBuilder bully = new BullyCharacterBuilder();
		

		fighterBully.setCharacterBuilder(bully);
		fighterBully.createFighter(character);
		System.out.println(fighterBully);

		DummyGameEngine testEngine = new DummyGameEngine(BlackCampaign, character);
		System.out.println("First flag");

		
		assertEquals("This is not the Map",map, testEngine.getCampagin().getCampaignList().get(0));
		
	}

	/**
	 * This test will test create campaign
	 */
	@Test
	public void campaign_Create_Test() {
		Map map = new Map(5, 6);
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
	 * @author fyounis This will test the creation of the
	 *         characterBuilder_Create_Test
	 */
	@Test
	public void characterBuilder_Create_Test() {

		Character character = new Character("Feras", "The Greater", FighterType.BULLY, 10, 10, 10, 10, 5, 5, redArmor,
				redRing, redHelmet, redBoots, redBelt, redWeapon, redShield);

		Fighter fighterBully = new Fighter();
		Fighter fighterTank = new Fighter();

		CharacterBuilder bully = new BullyCharacterBuilder();
		CharacterBuilder tank = new TankCharacterBuilder();

		fighterBully.setCharacterBuilder(bully);
		fighterBully.createFighter(character);
		System.out.println(fighterBully);

		fighterTank.setCharacterBuilder(tank);
		fighterTank.createFighter(character);

	}

	/**
	 * This Test will test the validation of an item
	 */
	@Test
	public void item_Create_Test() {

		System.out.println("The item_Create_Test is running");

		Item redHelmet = new Item("RedHelmet", ItemType.HELMET, CharacterAttribute.INTELLIGENCE, 2, 5,
				WeaponType.NotAWeapon);
		assertTrue("The Item: " + redHelmet.getName() + " is not a valid Item", redHelmet.isValid());
		System.out.println(" The Item: " + redHelmet.getName() + " was created ");
		System.out.println(redHelmet.toString() + "\n");

		Item redHelmet5 = new Item("RedHelmet5", ItemType.HELMET, CharacterAttribute.INTELLIGENCE, 5, 8,
				WeaponType.NotAWeapon);
		assertTrue("The Item: " + redHelmet5.getName() + " is not a valid Item", redHelmet5.isValid());
		System.out.println(" The Item: " + redHelmet5.getName() + " was created ");
		System.out.println(redHelmet5.toString() + "\n");

		Item redHelmet4 = new Item("RedHelmet4", ItemType.HELMET, CharacterAttribute.DAMAGE_BONUS, 4, 2,
				WeaponType.NotAWeapon);
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

		Item redHelmet1 = new Item("RedHelmet1", ItemType.HELMET, CharacterAttribute.INTELLIGENCE, 7, 10,
				WeaponType.NotAWeapon);
		assertFalse("The Item: " + redHelmet1.getName() + " is not a valid Item", redHelmet1.isValid());
		System.out.println(" The Item: " + redHelmet1.getName() + " was NOT created ");
		System.out.println(redHelmet1.toString() + "\n");

		Item redHelmet2 = new Item("RedHelmet2", ItemType.HELMET, CharacterAttribute.INTELLIGENCE, 0, 0,
				WeaponType.NotAWeapon);
		assertFalse("The Item: " + redHelmet2.getName() + " is not a valid Item", redHelmet2.isValid());
		System.out.println(" The Item: " + redHelmet2.getName() + " was NOT created ");
		System.out.println(redHelmet2.toString() + "\n");

		Item redHelmet3 = new Item("RedHelmet3", ItemType.HELMET, CharacterAttribute.INTELLIGENCE, -5, 1,
				WeaponType.NotAWeapon);
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
	 * This Test will test load a map 
	 */
	
	@Test
	public void loadMap_Test(){
		
		MapIO mapIO = new MapIO();
		
		map3.setMapName("Map3");
		mapIO.saveMap(map3);
		ArrayList<Map> mapList = mapIO.loadMaps();
		
		boolean found = false;
		
		for (Map map : mapList){
			if (map.getMapName().equalsIgnoreCase("Map3"));
			found = true;
			break;
		}
		
		assertTrue(found);
		
	}
	/**
	 * This Test will test the validation saving an item
	 */
	@Test
	public void saveLoadItem_Test() throws FileNotFoundException, ClassNotFoundException, IOException {

		
		// save item object
		ItemIO fileWriterReader = new ItemIO();
		String message = fileWriterReader.saveItem(crazyHelmet);
		System.out.println(message);
		ArrayList<Item> listItem = fileWriterReader.loadItems();

		boolean found = false;
		for (Item item : listItem) {
			if (item.getName().equalsIgnoreCase("crazyHelmet")) {
				found = true;
				break;
			}

		}
		assertTrue(found);

	}
}
