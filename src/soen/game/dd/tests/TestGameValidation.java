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

import soen.game.dd.fileio.CharacterIO;
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
 * 
 * @author fyounis This is the second test class
 * here will test Campaign and game
 *
 */
public class TestGameValidation {

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
	private ArrayList<Item> chest2;


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
	 * this test will test if we can add items to a chest
	 */
	@Test
	public void Chest_add_Items_Test1(){
		
		chest2 = new ArrayList<Item>();
		chest2.add(crazyHelmet);
		chest2.add(blackBelt);
		chest2.add(redBelt);
		
		assertTrue(chest2.size()==3);
		
	}
	
	/**
	 * this test will test if we can add items to a chest
	 */
	@Test
	public void Chest_add_Items_Test2(){
		
		chest2 = new ArrayList<Item>();
		chest2.add(crazyHelmet);
		chest2.add(blackBelt);
		chest2.add(redBelt);
		
		chest2.remove(crazyHelmet);
		
		assertTrue(chest.get(0).equals(crazyHelmet));
		
		
		
		
	}
	/**
	 * this test will test if we can add items to a chest
	 */
	@Test
	public void Chest_add_Items_Test3(){
		
		chest2 = new ArrayList<Item>();
		chest2.add(crazyHelmet);
		chest2.add(blackBelt);
		chest2.add(redBelt);
		
		chest2.remove(crazyHelmet);
		
		assertTrue(chest.get(0).equals(crazyHelmet));
		
		assertTrue(chest2.size()==2);
		boolean exist = false;
		for(Item item : chest2){
			if(item==crazyHelmet){
				exist = true;
				}
		}
		
		assertFalse(exist);
		
		
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

		DummyGameEngine testEngine = new DummyGameEngine(munjed, BlackCampaign);
		System.out.println("First flag");

		
		assertEquals("Both Compaings are same",BlackCampaign, testEngine.getCampagin());
		


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
	 * Test Campaign Name
	 */
	@Test
	public void create_Campaign_Test1(){
		
		map.mapSelectedItem = chest;
		map2.mapSelectedItem = chest;
		map3.mapSelectedItem = chest;
		map4.mapSelectedItem = chest;

		BlackCampaign.setCampaignList(map);
		BlackCampaign.setCampaignList(map2);
		BlackCampaign.setCampaignList(map3);
		BlackCampaign.setCampaignList(map4);
		System.out.println("First flag");
		
		assertTrue(BlackCampaign.getCampaignName().equals("BlackCampaign"));
	}
	/**
	 * Test Campaign map size
	 */
	@Test
	public void create_Campaign_Test2(){
		
		map.mapSelectedItem = chest;
		map2.mapSelectedItem = chest;
		map3.mapSelectedItem = chest;
		map4.mapSelectedItem = chest;

		BlackCampaign.setCampaignList(map);
		BlackCampaign.setCampaignList(map2);
		BlackCampaign.setCampaignList(map3);
		BlackCampaign.setCampaignList(map4);
		System.out.println("First flag");
		
		assertTrue(BlackCampaign.getCampaignList().size()==4);
		
	}
	
	/**
	 * Test Campaign map is map added
	 */
	@Test
	public void create_Campaign_Test3(){
		
		map.mapSelectedItem = chest;
		map2.mapSelectedItem = chest;
		map3.mapSelectedItem = chest;
		map4.mapSelectedItem = chest;

		BlackCampaign.setCampaignList(map);
		BlackCampaign.setCampaignList(map2);
		BlackCampaign.setCampaignList(map3);
		BlackCampaign.setCampaignList(map4);
		System.out.println("First flag");
		
		
		boolean found = false;
		for(Map map : BlackCampaign.getCampaignList()){
			if (map == map3){
				found = true; 
				System.out.println(map);
				break;
			}
		}
		assertTrue(found);
	}
	
	
	
	/**
	 * Test looting
	 */
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
			System.out.println("Found the Item: "+item.getName());
			break;
		}
		

		
		assertTrue(found);
		


	}
	

}
