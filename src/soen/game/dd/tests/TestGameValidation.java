package soen.game.dd.tests;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import java.awt.Point;
import java.util.ArrayList;
import java.util.List;

import org.junit.Before;
import org.junit.Test;

import soen.game.dd.character.strategys.HumanStrategy;
import soen.game.dd.logic.RangeDetection;
import soen.game.dd.models.BullyCharacterBuilder;
import soen.game.dd.models.Campaign;
import soen.game.dd.models.Character;
import soen.game.dd.models.CharacterAttribute;
import soen.game.dd.models.CharacterBuilder;
import soen.game.dd.models.Fighter;
import soen.game.dd.models.FighterType;
import soen.game.dd.models.GameEngine;
import soen.game.dd.models.Item;
import soen.game.dd.models.ItemType;
import soen.game.dd.models.Map;
import soen.game.dd.models.NPCType;
import soen.game.dd.models.WeaponType;
import soen.game.dd.statics.content.GameStatics;

/**
 * 
 * @author fyounis This is the second test class here will test Campaign and
 *         game
 *
 */
public class TestGameValidation {

	private Map map;
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
	private ArrayList<Item> chest2;

	public Map addComponentsToMap(Map map) {

		Point point = new Point(1, 1);
		map.setEntryPoint(point);

		point.setLocation(10, 10);
		map.setExitPoint(point);

		point.setLocation(1, 1);
		map.setCharacterPoint(point);

		point.setLocation(5, 5);
		map.setChestPoint(point);

		return map;

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
		chest.add(crazyHelmet);
		chest.add(blackBelt);
		chest.add(crazyHelmet);
		chest.add(blackBelt);
		chest.add(crazyHelmet);
		chest.add(blackBelt);
		chest.add(crazyHelmet);
		chest.add(blackBelt);
		chest.add(crazyHelmet);
		chest.add(blackBelt);

		map = new Map(5, 5);
	}

	/**
	 * @author fyounis test the d20 sorting for the turn() to determine who will
	 *         play first
	 */
	@Test

	public void turn_Sorting_Test() {
		Character munjed = new Character("munjed", "The Greater", FighterType.BULLY, 7, 7, 7, 7, 7, 10, redArmor,
				redRing, redHelmet, redBoots, redBelt, redWeapon, redShield);
		munjed.setNPCType(NPCType.FRINDLY);

		Character feras = new Character("Feras", "The Greater", FighterType.BULLY, 10, 10, 10, 10, 5, 5, redArmor,
				redRing, redHelmet, redBoots, redBelt, redWeapon, redShield);
		feras.setNPCType(NPCType.PLAYABALE);

		Character zombi1 = new Character("zombi1", "The Greater", FighterType.BULLY, 9, 8, 6, 10, 5, 5, redArmor,
				redRing, redHelmet, redBoots, redBelt, redWeapon, redShield);
		zombi1.setNPCType(NPCType.HOSTILE);
		Character zombi2 = new Character("zombi2", "The Greater", FighterType.BULLY, 9, 6, 8, 9, 5, 5, redArmor,
				redRing, redHelmet, redBoots, redBelt, redWeapon, redShield);
		zombi1.setNPCType(NPCType.HOSTILE);

		map.mapSelectedItem = chest;
		map2.mapSelectedItem = chest;
		map3.mapSelectedItem = chest;
		map4.mapSelectedItem = chest;

		map.mapGridSelection = new int[5][5];
		map.mapGridSelection[0][0] = GameStatics.MAP_ENTRY_POINT;
		map.mapGridSelection[0][1] = GameStatics.MAP_PATH_POINT;
		map.mapGridSelection[1][2] = GameStatics.MAP_PATH_POINT;
		map.mapGridSelection[1][3] = GameStatics.MAP_PATH_POINT;
		map.mapGridSelection[1][4] = GameStatics.MAP_PATH_POINT;
		map.mapGridSelection[2][4] = GameStatics.MAP_PATH_POINT;
		map.mapGridSelection[3][4] = GameStatics.MAP_PATH_POINT;
		map.mapGridSelection[4][4] = GameStatics.MAP_EXIT_POINT;

		BlackCampaign.setCampaignList(map);
		BlackCampaign.setCampaignList(map2);
		BlackCampaign.setCampaignList(map3);
		BlackCampaign.setCampaignList(map4);

		map.mapCharacters.add(feras);
		map.mapCharacters.add(munjed);
		map.mapCharacters.add(zombi1);

		GameEngine testEngine = new GameEngine(BlackCampaign, munjed);
		testEngine.setCurrentMap();
		testEngine.resetCharacterPosition();
		testEngine.getPositions().put(feras, new Point(1, 1));
		testEngine.getPositions().put(munjed, new Point(2, 3));
		testEngine.getPositions().put(zombi1, new Point(2, 1));
		List<Character> fightersList = testEngine.getOrderedCharacters();
		System.out.println(fightersList.toString());

	}

	/**
	 * @author fyounis test the d20 sorting for the turn() to determine who will
	 *         play first
	 */
	@Test

	public void effective_Range_Test() {
		Character munjed = new Character("munjed", "The Greater", FighterType.BULLY, 7, 7, 7, 7, 7, 10, redArmor,
				redRing, redHelmet, redBoots, redBelt, redWeapon, redShield);
		munjed.setNPCType(NPCType.FRINDLY);

		Character feras = new Character("Feras", "The Greater", FighterType.BULLY, 10, 10, 10, 10, 5, 5, redArmor,
				redRing, redHelmet, redBoots, redBelt, redWeapon, redShield);
		feras.setNPCType(NPCType.PLAYABALE);

		Character zombi1 = new Character("zombi1", "The Greater", FighterType.BULLY, 9, 8, 6, 10, 5, 5, redArmor,
				redRing, redHelmet, redBoots, redBelt, redWeapon, redShield);
		zombi1.setNPCType(NPCType.HOSTILE);
		Character zombi2 = new Character("zombi2", "The Greater", FighterType.BULLY, 9, 6, 8, 9, 5, 5, redArmor,
				redRing, redHelmet, redBoots, redBelt, redWeapon, redShield);
		zombi1.setNPCType(NPCType.HOSTILE);

		map.mapSelectedItem = chest;
		map2.mapSelectedItem = chest;
		map3.mapSelectedItem = chest;
		map4.mapSelectedItem = chest;

		map.mapGridSelection = new int[5][5];
		map.mapGridSelection[0][0] = GameStatics.MAP_ENTRY_POINT;
		map.mapGridSelection[0][1] = GameStatics.MAP_PATH_POINT;
		map.mapGridSelection[1][2] = GameStatics.MAP_PATH_POINT;
		map.mapGridSelection[1][3] = GameStatics.MAP_PATH_POINT;
		map.mapGridSelection[1][4] = GameStatics.MAP_PATH_POINT;
		map.mapGridSelection[2][4] = GameStatics.MAP_PATH_POINT;
		map.mapGridSelection[3][4] = GameStatics.MAP_PATH_POINT;
		map.mapGridSelection[4][4] = GameStatics.MAP_EXIT_POINT;

		BlackCampaign.setCampaignList(map);
		BlackCampaign.setCampaignList(map2);
		BlackCampaign.setCampaignList(map3);
		BlackCampaign.setCampaignList(map4);

		map.mapCharacters.add(feras);
		map.mapCharacters.add(munjed);
		map.mapCharacters.add(zombi1);

		GameEngine testEngine = new GameEngine(BlackCampaign, munjed);
		testEngine.setCurrentMap();
		testEngine.resetCharacterPosition();
		testEngine.getPositions().put(feras, new Point(1, 1));
		testEngine.getPositions().put(munjed, new Point(2, 3));
		testEngine.getPositions().put(zombi1, new Point(2, 1));
		List<Character> fightersList = testEngine.getOrderedCharacters();

		RangeDetection rangeDetc = new RangeDetection(testEngine);
		assertTrue(rangeDetc.isEnemyWithinRange(zombi1, munjed));
	}

	/**
	 * Character move on the map
	 */
	@Test
	public void character_Move() {
		map.mapSelectedItem = chest;
		map2.mapSelectedItem = chest;
		map3.mapSelectedItem = chest;
		map4.mapSelectedItem = chest;

		map.mapGridSelection = new int[5][5];
		map.mapGridSelection[0][0] = GameStatics.MAP_ENTRY_POINT;
		map.mapGridSelection[0][1] = GameStatics.MAP_PATH_POINT;
		map.mapGridSelection[1][2] = GameStatics.MAP_PATH_POINT;
		map.mapGridSelection[1][3] = GameStatics.MAP_PATH_POINT;
		map.mapGridSelection[1][4] = GameStatics.MAP_PATH_POINT;
		map.mapGridSelection[2][4] = GameStatics.MAP_PATH_POINT;
		map.mapGridSelection[3][4] = GameStatics.MAP_PATH_POINT;
		map.mapGridSelection[4][4] = GameStatics.MAP_EXIT_POINT;

		BlackCampaign.setCampaignList(map);
		BlackCampaign.setCampaignList(map2);
		BlackCampaign.setCampaignList(map3);
		BlackCampaign.setCampaignList(map4);

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

		GameEngine testEngine = new GameEngine(BlackCampaign, munjed);
		testEngine.setCurrentMap();

		testEngine.resetCharacterPosition();
		assertTrue("", testEngine.getCharacterPosition().equals(new Point(0, 0)));

	}

	/**
	 * Test if the engine load the campaign
	 */
	@Test
	public void campaign_Is_Loaded_Test() {
		map.mapSelectedItem = chest;
		map2.mapSelectedItem = chest;
		map3.mapSelectedItem = chest;
		map4.mapSelectedItem = chest;

		map.mapGridSelection = new int[5][5];
		map.mapGridSelection[0][0] = GameStatics.MAP_ENTRY_POINT;
		map.mapGridSelection[0][1] = GameStatics.MAP_PATH_POINT;
		map.mapGridSelection[1][2] = GameStatics.MAP_PATH_POINT;
		map.mapGridSelection[1][3] = GameStatics.MAP_PATH_POINT;
		map.mapGridSelection[1][4] = GameStatics.MAP_PATH_POINT;
		map.mapGridSelection[2][4] = GameStatics.MAP_PATH_POINT;
		map.mapGridSelection[3][4] = GameStatics.MAP_PATH_POINT;
		map.mapGridSelection[4][4] = GameStatics.MAP_EXIT_POINT;

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

		GameEngine testEngine = new GameEngine(BlackCampaign, munjed);
		testEngine.setCurrentMap();

		assertEquals("Both Compaings are same", BlackCampaign, testEngine.getCampagin());
	}

	/**
	 * Character move on the map
	 */
	@Test
	public void setCharacter_Strategy_Playable_Test() {
		map.mapSelectedItem = chest;
		map2.mapSelectedItem = chest;
		map3.mapSelectedItem = chest;
		map4.mapSelectedItem = chest;

		map.mapGridSelection = new int[5][5];
		map.mapGridSelection[0][0] = GameStatics.MAP_ENTRY_POINT;
		map.mapGridSelection[0][1] = GameStatics.MAP_PATH_POINT;
		map.mapGridSelection[1][2] = GameStatics.MAP_PATH_POINT;
		map.mapGridSelection[1][3] = GameStatics.MAP_PATH_POINT;
		map.mapGridSelection[1][4] = GameStatics.MAP_PATH_POINT;
		map.mapGridSelection[2][4] = GameStatics.MAP_PATH_POINT;
		map.mapGridSelection[3][4] = GameStatics.MAP_PATH_POINT;
		map.mapGridSelection[4][4] = GameStatics.MAP_EXIT_POINT;

		BlackCampaign.setCampaignList(map);
		BlackCampaign.setCampaignList(map2);
		BlackCampaign.setCampaignList(map3);
		BlackCampaign.setCampaignList(map4);
		System.out.println("First flag");

		Character munjed = new Character("Munjed", "The Greater", FighterType.BULLY, 7, 7, 7, 7, 7, 10, redArmor,
				redRing, redHelmet, redBoots, redBelt, redWeapon, redShield);
		System.out.println("First flag");

		Point point = new Point(1, 1);

		GameEngine testEngine = new GameEngine(BlackCampaign, munjed);
		testEngine.setCurrentMap();

		System.out.println(munjed.getStrategy());
		assertTrue(munjed.getStrategy() instanceof HumanStrategy);
		RangeDetection rangeDetc = new RangeDetection(testEngine);
	}

	/**
	 * THis test will test the engine of the game campaign
	 * 
	 */
	@Test
	public void engine_Create_Test() {

		/*
		 * addComponentsToMap(map); addComponentsToMap(map2);
		 * addComponentsToMap(map3); addComponentsToMap(map4);
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

		GameEngine testEngine = new GameEngine(munjed, BlackCampaign);
		System.out.println("First flag");

		assertEquals("Both Compaings are same", BlackCampaign, testEngine.getCampagin());

	}

	/**
	 * Test Map validation from the engine Engine.setMap
	 */

	public void engine_Map_Validation_Test() {

		/*
		 * addComponentsToMap(map); addComponentsToMap(map2);
		 * addComponentsToMap(map3); addComponentsToMap(map4);
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

		GameEngine testEngine = new GameEngine(BlackCampaign, character);
		System.out.println("First flag");

		assertEquals("This is not the Map", map, testEngine.getCampagin().getCampaignList().get(0));

	}

	/**
	 * Test looting
	 */
	@Test
	public void loot_Test() {

		/*
		 * addComponentsToMap(map); addComponentsToMap(map2);
		 * addComponentsToMap(map3); addComponentsToMap(map4);
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

		GameEngine testEngine = new GameEngine(munjed, BlackCampaign);
		System.out.println("3 flag");

		/*
		 * chest.add(crazyHelmet); chest.add(blackBelt);
		 */
		System.out.println("<Info> : the size of the backpack before looting: " + munjed.getBackpack().size());
		testEngine.lootChestItems(munjed, chest);
		List<Item> lootedItem = munjed.getItemIntoBackpack();
		System.out.println("<Info> : the size of the backpack after looting:  " + munjed.getBackpack().size());
		boolean found = false;
		for (Item item : lootedItem) {
			found = false;
			if (item.getName().equalsIgnoreCase("crazyHelmet"))
				;
			found = true;
			System.out.println("Found the Item: " + item.getName());
			break;
		}

		assertTrue(found);

	}

}
