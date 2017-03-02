package soen.game.dd.tests;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;

import org.junit.Before;
import org.junit.Test;

import soen.game.dd.models.CharacterAttribute;
import soen.game.dd.models.FileWriterReader;
import soen.game.dd.models.Item;
import soen.game.dd.models.ItemType;
import soen.game.dd.models.Map;

/**
 * This class to test different objects creating, saving, loading. Object like
 * Item, Character, Map and campaign
 * 
 * @author fyounis
 *
 */
public class TestObjects {

	@Before
	public void initialize() {

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
		FileWriterReader fileWriterReader = new FileWriterReader();
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
