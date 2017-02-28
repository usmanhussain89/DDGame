package soen.game.dd.tests;
import org.junit.*;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import org.junit.Test;

import soen.game.dd.models.CharacterAttribute;
import soen.game.dd.models.Item;
import soen.game.dd.models.ItemType;

/**
 * This class to test different objects creating, saving, loading. Object like Item, Character, Map and campaign
 * 
 * @author fyounis
 *
 */
public class TestObjects {
	
	
	@Test
	public void item_Create_Test(){
		
		System.out.println("The item_Create_Test is running");
		
		Item redHelmet= new Item("RedHelmet", ItemType.HELMET, CharacterAttribute.INTELLIGENCE, 2);
		redHelmet.isValid();
		assertTrue("The Item: "+redHelmet.getName()+" is not a valid Item",redHelmet.isValid());
		System.out.println(" The Item: "+redHelmet.getName()+" was created ");
		System.out.println(redHelmet.toString());
		
		Item redHelmet1= new Item("RedHelmet", ItemType.HELMET, CharacterAttribute.INTELLIGENCE, 7);
		assertFalse("The Item: "+redHelmet1.getName()+" is not a valid Item",redHelmet1.isValid());
		System.out.println(" The Item: "+redHelmet.getName()+" was created ");
		System.out.println(redHelmet.toString());
		
		Item redHelmet2= new Item("RedHelmet", ItemType.HELMET, CharacterAttribute.INTELLIGENCE, 0);
		assertFalse("The Item: "+redHelmet2.getName()+" is not a valid Item",redHelmet2.isValid());
		System.out.println(" The Item: "+redHelmet.getName()+" was created ");
		System.out.println(redHelmet.toString());
		
		Item redHelmet3= new Item("RedHelmet", ItemType.HELMET, CharacterAttribute.INTELLIGENCE, -5);
		assertFalse("The Item: "+redHelmet3.getName()+" is not a valid Item",redHelmet3.isValid());
		System.out.println(" The Item: "+redHelmet.getName()+" was created ");
		System.out.println(redHelmet.toString());
		
		Item redHelmet4= new Item("RedHelmet", ItemType.HELMET, CharacterAttribute.DAMAGE_BONUS, 4);
		assertFalse("The Item: "+redHelmet4.getBonusAmount()+" is not a valid Item",redHelmet4.isValid());
		System.out.println(" The Item: "+redHelmet.getName()+" was created ");
		System.out.println(redHelmet.toString());
		
	}
	

}
