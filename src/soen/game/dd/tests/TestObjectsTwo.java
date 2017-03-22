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
 * This is the Second test class using Junit
 *
 */
public class TestObjectsTwo {

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
	 * This test to test wearing_Items_Should_Correctly_Influence_Character_Abilities_Test2
	 */
	@Test
	public void wearing_Items_Should_Correctly_Influence_Character_Abilities_Test2() {

		Character jackTheGreate = new Character("Feras", "The Greater", FighterType.BULLY, 7, 7, 7, 10, 10, 10,
				redArmor, redRing, redHelmet, redBoots, redBelt, redWeapon, redShield);
		jackTheGreate.setIntelligenceModifier(2.0);
		jackTheGreate.setAbilityModifier();
		jackTheGreate.setAttackBonus();

		int weakerItems = jackTheGreate.getDamageBonus();
		System.out.println("The attack bonus is: " + weakerItems);
		System.out.println("the Damage bonus is :" + jackTheGreate.getDamageBonus());

		jackTheGreate.setWeapon(crazyWeapon);
		jackTheGreate.setBelt(blackBelt);
		jackTheGreate.setHelmet(crazyHelmet);
		jackTheGreate.setAbilityModifier();
		jackTheGreate.setAttackBonus();

		int strongerItems = jackTheGreate.getDamageBonus();

		System.out.println("the armor bonus is :" + jackTheGreate.getArmorClass());

		System.out.println("The attack bonus is: " + strongerItems);
		System.out.println("the Damage bonus is :" + jackTheGreate.getDamageBonus());

		assertTrue((strongerItems > weakerItems));

	}

	/**
	 * This will test false create Item
	 */
	@Test
	public void create_Item_Is_Not_Valiad_Test() {
		Item redHelmet4 = new Item("RedHelmet4", ItemType.HELMET, CharacterAttribute.DAMAGE_BONUS, 4, 2,
				WeaponType.NotAWeapon);
		assertFalse("The Item: " + redHelmet4.getBonusAmount() + " is not a valid Item", redHelmet4.isValid());
		System.out.println(" The Item: " + redHelmet4.getName() + " was created ");
		System.out.println(redHelmet4.toString() + "\n");
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

	/**
	 * This Test will test load a Character
	 */

	@Test
	public void loadCharacter_Test() {

		CharacterIO characterIO = new CharacterIO();

		String characterName = redFeras.getName();
		System.out.println(characterName);
		characterIO.saveCharacter(redFeras);
		ArrayList<Character> characterList = characterIO.loadCharacters();

		boolean found = false;

		for (Character character : characterList) {
			if (character.getName().equalsIgnoreCase(characterName)) {
				found = true;
				System.out.println(character.getName());
				break;

			}
			assertTrue("Not found",found);
		}
	}

}
