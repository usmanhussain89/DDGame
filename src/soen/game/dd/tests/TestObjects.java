package soen.game.dd.tests;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import java.io.FileNotFoundException;
import java.io.IOException;

import org.junit.Before;
import org.junit.Test;

import soen.game.dd.models.CharacterAttribute;
import soen.game.dd.models.FileWriterReader;
import soen.game.dd.models.Item;
import soen.game.dd.models.ItemType;;

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

	@Test
	public void item_Create_Test() {

		System.out.println("The item_Create_Test is running");

		Item redHelmet = new Item("RedHelmet", ItemType.HELMET, CharacterAttribute.INTELLIGENCE, 2);
		assertTrue("The Item: " + redHelmet.getName() + " is not a valid Item", redHelmet.isValid());
		System.out.println(" The Item: " + redHelmet.getName() + " was created ");
		System.out.println(redHelmet.toString() + "\n");

		Item redHelmet5 = new Item("RedHelmet5", ItemType.HELMET, CharacterAttribute.INTELLIGENCE, 5);
		assertTrue("The Item: " + redHelmet5.getName() + " is not a valid Item", redHelmet5.isValid());
		System.out.println(" The Item: " + redHelmet5.getName() + " was created ");
		System.out.println(redHelmet5.toString() + "\n");

		Item redHelmet1 = new Item("RedHelmet1", ItemType.HELMET, CharacterAttribute.INTELLIGENCE, 7);
		assertFalse("The Item: " + redHelmet1.getName() + " is not a valid Item", redHelmet1.isValid());
		System.out.println(" The Item: " + redHelmet1.getName() + " was created ");
		System.out.println(redHelmet1.toString() + "\n");

		Item redHelmet2 = new Item("RedHelmet2", ItemType.HELMET, CharacterAttribute.INTELLIGENCE, 0);
		assertFalse("The Item: " + redHelmet2.getName() + " is not a valid Item", redHelmet2.isValid());
		System.out.println(" The Item: " + redHelmet2.getName() + " was created ");
		System.out.println(redHelmet2.toString() + "\n");

		Item redHelmet3 = new Item("RedHelmet3", ItemType.HELMET, CharacterAttribute.INTELLIGENCE, -5);
		assertFalse("The Item: " + redHelmet3.getName() + " is not a valid Item", redHelmet3.isValid());
		System.out.println(" The Item: " + redHelmet3.getName() + " was created ");
		System.out.println(redHelmet3.toString() + "\n");

		Item redHelmet4 = new Item("RedHelmet4", ItemType.HELMET, CharacterAttribute.DAMAGE_BONUS, 4);
		assertFalse("The Item: " + redHelmet4.getBonusAmount() + " is not a valid Item", redHelmet4.isValid());
		System.out.println(" The Item: " + redHelmet4.getName() + " was created ");
		System.out.println(redHelmet4.toString() + "\n");

	}

	@Test
	public void saveLoadObjects_Test() throws FileNotFoundException, ClassNotFoundException, IOException {

		// create new item Object
		Item blackHelmet = new Item("Black Helmet", ItemType.HELMET, CharacterAttribute.INTELLIGENCE, 4);
		// save item object
		FileWriterReader fileWriterReader = new FileWriterReader();
		System.out.println("Flag2\n");
		fileWriterReader.saveItem(blackHelmet);
		System.out.println("Flag3\n");
		// load Item Object
		Item item = fileWriterReader.loadItem("Black Helmet");
		System.out.println(item.toString() + "\n");
		assertEquals(blackHelmet, item);

		// create new character Object
		// Character nightFighter = new Character("Night Fighter", description,
		// level, abilityScores, abilityModifier, hitPoint, armorClass,
		// attackBonus, damageBonus, multipleAttacks, armore, ring, helmet,
		// boots, belt, sword, shield)

	}

	@Test
	public void character_Create_Test() {

	}

}
