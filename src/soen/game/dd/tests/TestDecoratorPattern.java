package soen.game.dd.tests;

import static org.junit.Assert.assertEquals;

import java.awt.Point;
import java.util.ArrayList;

import org.junit.Before;
import org.junit.Test;

import soen.game.dd.models.Campaign;
import soen.game.dd.models.Character;
import soen.game.dd.models.CharacterAttribute;
import soen.game.dd.models.FighterType;
import soen.game.dd.models.Item;
import soen.game.dd.models.ItemType;
import soen.game.dd.models.Map;
import soen.game.dd.models.WeaponType;
import soen.game.dd.weapon.enchantments.BurningDecorator;
import soen.game.dd.weapon.enchantments.FreezingDecorator;
import soen.game.dd.weapon.enchantments.PacifyingDecorator;
import soen.game.dd.weapon.enchantments.SlayingDecorator;
import soen.game.dd.weapon.enchantments.Weapon;
import soen.game.dd.weapon.enchantments.WeaponBasic;

public class TestDecoratorPattern {

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
	private Character blackMunjed;
	private Character blueFeras;
	private Character blueMunjed;
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
		blackMunjed = new Character("Munjed", "The Greater", FighterType.BULLY, 7, 7, 7, 7, 7, 10, redArmor, redRing,
				redHelmet, redBoots, redBelt, redWeapon, redShield);

		BlackCampaign = new Campaign();
		BlackCampaign.setCampaignName("BlackCampaign");

		chest = new ArrayList<Item>();
		chest.add(crazyHelmet);
		chest.add(blackBelt);
	}

	@Test
	public void CreateDecortorItem() {
		Weapon crazyDarkWeapon = new PacifyingDecorator(
				new SlayingDecorator(new PacifyingDecorator(new WeaponBasic())));
		crazyDarkWeapon.setName("crazyDarkWeapon");
		System.out.println(crazyDarkWeapon.getEnchantments().toString());
		assertEquals("[Pacifying, Slaying, Pacifying]", crazyDarkWeapon.getEnchantments().toString());
	}

	@Test
	public void CreateDecortorItem2() {
		Weapon knightsSlayerWeapon = new BurningDecorator(
				new FreezingDecorator(new SlayingDecorator(new WeaponBasic())));
		knightsSlayerWeapon.setName("knightsSlayerWeapon");
		System.out.println(knightsSlayerWeapon.getEnchantments().toString());
		System.out.println(
				"Test what enchantments were created or added to the Weapon: " + knightsSlayerWeapon.getName());
		assertEquals("[Slaying, Freezing, Burning]", knightsSlayerWeapon.getEnchantments().toString());

	}

	@Test
	public void CreateDecortorItem3() {
		Weapon hopeDestroyerWeapon = new BurningDecorator(
				new FreezingDecorator(new SlayingDecorator(new WeaponBasic())));
		hopeDestroyerWeapon.setName("hopeDestroyerWeapon");
		System.out.println(hopeDestroyerWeapon.getEnchantments().toString());
		System.out.println(hopeDestroyerWeapon.getEnchantments().size());
		System.out.println(
				"Test how many enchantments were created or added to the Weapon: " + hopeDestroyerWeapon.getName());
		assertEquals(3, hopeDestroyerWeapon.getEnchantments().size());

	}

}
