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
 *  Map 
 * 
 * @author fyounis
 *
 */
public class TestMapValidation {

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
	 * @author fyounis this will test the creation of the character
	 */
	
	

	


	

	

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
		

		assertEquals(true, map.isEntryDone);
		assertEquals(true, map.isExitDone);
		assertEquals(true, map.isChestDone);
	
	}
	/**
	 * This Test will test the validation of an map
	 */
	@Test
	public void mapValidity_Test4() {
		Map map = new Map();
		
		map.isCharacterDone = true;
		map.isOpponentDone = true;

		
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
			if (map.getMapName().equalsIgnoreCase("Map3")){
				found = true;
				break;
			}
			
		}
		
		assertTrue(found);
		
	}
	
	/**
	 * This test will test the false map create
	 */
	@Test
	public void create_Map_Is_Not_Valiad_Test() {

		Map map = new Map(44, 22);
		assertFalse("the Map: " + map.getMapName() + " is valid", map.isValid());

		Map map2 = new Map(-5, 10);
		assertFalse(map2.isValid());

		Map map3 = new Map(5, 77);
		assertFalse(map3.isValid());

		Map map4 = new Map(-5, -15);
		assertFalse(map4.isValid());
	}
}
