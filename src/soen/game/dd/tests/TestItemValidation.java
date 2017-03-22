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
 * 
 * @author fyounis
 *	This class is the third test class using Junit
 */

public class TestItemValidation {
	
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
}
