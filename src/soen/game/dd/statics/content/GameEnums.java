package soen.game.dd.statics.content;

/**
 * This class contains all the Enums of overall Game
 * 
 * @author Usman
 *
 */
public class GameEnums {

	/**
	 * Enumerators to express Map Editor Mode
	 * 
	 */
	public enum E_MapEditorMode {
		Create, Open
	}

	/**
	 * Enumerators For Characters on map
	 * 
	 */
	public enum E_MapSelectCharacter {
		Friendly, Hostile
	}

	/**
	 * Enumerators For JFile Chooser Mode
	 * 
	 */
	public enum E_JFileChooserMode {
		MapOpen, MapSave, CampaignSave, CampaignOpen, ItemSave, ItemOpen
	}

	/**
	 * Enumerators to used in Map Validation which the direction of map
	 * validation iteration
	 * 
	 */
	public enum E_MapValidationDirecton {
		Initial, Up, Down, Left, Right
	}

	/**
	 * Enumerators to express Item Editor Mode
	 * 
	 */
	public enum E_ItemEditorMode {
		Create, Open
	}

	/**
	 * Enumerators to express Campaign Editor Mode
	 * 
	 */
	public enum E_CampaignEditorMode {
		Create, Open
	}

	/**
	 * Enumerators to express Character Editor Mode
	 * 
	 */
	public enum E_CharacterEditorMode {
		Create, Open
	}
}
