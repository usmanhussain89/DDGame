package soen.game.dd.tests;

import static org.junit.Assert.assertTrue;

import java.awt.Point;
import java.util.ArrayList;

import org.junit.Before;
import org.junit.Test;

import junit.framework.Assert;
import soen.game.dd.character.strategys.FriendlyStrategy;
import soen.game.dd.character.strategys.FrightenedStrategy;
import soen.game.dd.character.strategys.FrozenStrategy;
import soen.game.dd.character.strategys.Strategy;
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
import soen.game.dd.models.NPCType;
import soen.game.dd.models.WeaponType;
/**
 * This class will test the different Character Strategies 
 * @author fyounis
 *
 */
public class TestCharacterStrategy {

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
	private GameEngine selverEngine;

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

		map.mapSelectedItem = chest;
		map2.mapSelectedItem = chest;
		map3.mapSelectedItem = chest;
		map4.mapSelectedItem = chest;
		map.setMapName("Map1");
		map2.setMapName("Map2");
		map3.setMapName("Map3");
		map4.setMapName("Map4");

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

		GameEngine selverEngine = new GameEngine(redFeras, BlackCampaign);
	}

	/**
	 * test if frozen strategy work
	 */
	@Test
	public void set_Strategy_Test() {

		redFeras.setStrategy(new FrozenStrategy(redFeras, 3));
		assertTrue("the Strataegy type Changed to Freezing", redFeras.getStrategy() instanceof FrozenStrategy);

	}
	/**
	 * test if FrightenedStrategy
	 */

	@Test
	public void set_Strategy_Test2() {

		redFeras.setStrategy(new FrightenedStrategy(redFeras, selverEngine, 3));
		assertTrue("the Strataegy type Changed to Freezing", redFeras.getStrategy() instanceof FrightenedStrategy);

	}

	/**
	 * test if we can change strategy
	 */
	@Test
	public void changeStrategy_Test() {
		redFeras.setStrategy(new FrozenStrategy(redFeras, 3));
		redFeras.setStrategy(new FrightenedStrategy(redFeras, selverEngine, 3));
		redFeras.setNPCType(NPCType.PLAYABALE);
		assertTrue("the Strataegy type Changed to Freezing", redFeras.getStrategy() instanceof FrightenedStrategy);
	}

	/**
	 * @author f_youni test if the frozen strategy is moved back to the old
	 *         strategy after 3 trun()s
	 */
	@Test
	public void changeStrategy4() {

		redFeras.setStrategy(new FriendlyStrategy(redFeras, selverEngine));
		redFeras.setStrategy(new FrozenStrategy(redFeras, 3));
		redFeras.getStrategy().turn();
		redFeras.getStrategy().turn();
		redFeras.getStrategy().turn();
		Assert.assertEquals(FriendlyStrategy.class, ((redFeras.getStrategy()).getClass()));
		
	}
	/**
	 * @author f_youni test if the frozen strategy old strategy is saved 
	 */
	@Test
	public void changeStrategy5() {

		redFeras.setStrategy(new FriendlyStrategy(redFeras, selverEngine));
		redFeras.setStrategy(new FrozenStrategy(redFeras, 3));
		redFeras.getStrategy().turn();
		redFeras.getStrategy().turn();
		Assert.assertEquals(FriendlyStrategy.class, ((FrozenStrategy) redFeras.getStrategy()).getOldStrategy().getClass());
		
	}
	
}
