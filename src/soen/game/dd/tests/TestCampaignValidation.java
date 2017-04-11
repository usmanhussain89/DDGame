package soen.game.dd.tests;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import java.awt.Point;
import java.util.ArrayList;
import java.util.List;

import org.junit.Before;
import org.junit.Test;

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
import soen.game.dd.models.WeaponType;
import soen.game.dd.statics.content.GameStatics;

public class TestCampaignValidation {

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
	
	
	
}
