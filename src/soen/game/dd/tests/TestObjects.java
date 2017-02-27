package soen.game.dd.tests;
import org.junit.*;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import org.junit.Test;

import soen.game.dd.models.CharacterAttribute;
import soen.game.dd.models.Item;
import soen.game.dd.models.ItemType;

public class TestObjects {
	
	@Test
	public void item_Create_Test(){
		
		System.out.println("The item_Create_Test is running");
		
		Item redHelmet= new Item("RedHelmet", ItemType.HELMET, CharacterAttribute.INTELLIGENCE, 4);
		Assert.assertTrue("The Item: "+redHelmet.getName()+" is not a valid Item",redHelmet.isValid());
		System.out.println(" The Item: "+redHelmet.getName()+" was created ");
		System.out.println(redHelmet.toString());
		
		Item redHelmet1= new Item("RedHelmet", ItemType.HELMET, CharacterAttribute.INTELLIGENCE, 7);
		Assert.assertFalse("The Item: "+redHelmet.getName()+" is not a valid Item",redHelmet.isValid());
		System.out.println(" The Item: "+redHelmet.getName()+" was created ");
		System.out.println(redHelmet.toString());
		
		Item redHelmet2= new Item("RedHelmet", ItemType.HELMET, CharacterAttribute.INTELLIGENCE, 0);
		Assert.assertFalse("The Item: "+redHelmet.getName()+" is not a valid Item",redHelmet.isValid());
		System.out.println(" The Item: "+redHelmet.getName()+" was created ");
		System.out.println(redHelmet.toString());
		
		Item redHelmet3= new Item("RedHelmet", ItemType.HELMET, CharacterAttribute.INTELLIGENCE, -5);
		Assert.assertFalse("The Item: "+redHelmet.getName()+" is not a valid Item",redHelmet.isValid());
		System.out.println(" The Item: "+redHelmet.getName()+" was created ");
		System.out.println(redHelmet.toString());
		
		Item redHelmet4= new Item("RedHelmet", ItemType.HELMET, CharacterAttribute.DAMAGE_BONUS, 4);
		Assert.assertFalse("The Item: "+redHelmet.getBonusAmount()+" is not a valid Item",redHelmet.isValid());
		System.out.println(" The Item: "+redHelmet.getName()+" was created ");
		System.out.println(redHelmet.toString());
		
	}
	

}
