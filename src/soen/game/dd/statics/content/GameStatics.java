package soen.game.dd.statics.content;

/**
 * This class contains all the static constant of overall Game
 * 
 * @author Usman
 *
 */
public class GameStatics {

	// Game Statistics
	public static final int WINDOW_WIDTH = 840;
	public static final int WINDOW_HEIGHT = (int) WINDOW_WIDTH / 12 * 9;

	public static final int CHILD_POPUP_WINDOW_WIDTH = (int) WINDOW_WIDTH - 100;
	public static final int CHILD_POPUP_WINDOW_HEIGHT = (int) WINDOW_HEIGHT - 100;

	// MENU Items
	public static final String MENU_FILE = "FILE";
	public static final String MENU_ITEM_PLAY = "PLAY";
	public static final String MENU_ITEM_CREATE_MAP = "CREATE MAP";
	public static final String MENU_ITEM_OPEN_MAP = "OPEN MAP";
	public static final String MENU_ITEM_CREATE_CHARACTER = "CREATE CHARACTER";
	public static final String MENU_ITEM_OPEN_CHARACTER = "OPEN CHARACTER";
	public static final String MENU_ITEM_CREATE_ITEM = "CREATE ITEM";
	public static final String MENU_ITEM_OPEN_ITEM = "OPEN ITEM";
	public static final String MENU_ITEM_CREATE_CAMPAIGN = "CREATE CAMPAIGN";
	public static final String MENU_ITEM_OPEN_CAMPAIGN = "OPEN CAMPAIGN";
	public static final String MENU_ITEM_EXIT = "EXIT";
	public static final String MENU_ITEM_SAVE = "SAVE";
	public static final String MENU_CHARACTER_EXIT = "EXIT";
	public static final String MENU_CHARACTER_SAVE = "SAVE";

	// Popup Title
	public static final String TITLE_MSG_SET_SIZE_OF_MAP = "SET SIZE OF MAP";

	// Prompt Messages
	public static final String MSG_X_MAY_NOT_EMPTY = "Size of %s may not be empty";
	public static final String MSG_X_MUST_BE_IN_RANGE = "Size of %s must Lie between 1 - 30";

	// Window Titles
	public static final String MAP_MODE_CREATE = "(CREATE)";
	public static final String MAP_MODE_OPEN = "(OPEN)";
	public static final String CHARACTER_MODE_OPEN = "(OPEN)";
	public static final String CHARACTER_MODE_CREATE = "(CREATE)";
	public static final String TITLE_MAP_EDITOR = "MAP EDITOR";
	public static final String ITEM_MODE_CREATE = "(CREATE)";
	public static final String ITEM_MODE_OPEN = "(OPEN)";
	public static final String TITLE_ITEM_EDITOR = "ITEM EDITOR";
	public static final String TITLE_CAMPAIGN_EDITOR = "CAMPAIGN EDITOR";
	public static final String TITLE_CHARACTER_EDITOR = "CHARACTER EDITOR";
	public static final String TITLE_MAP_PlAY = "PLAY MODE";

	// Map Tiles Variables
	public static final int MAP_ENTRY_POINT = 2;
	public static final int MAP_EXIT_POINT = 3;
	public static final int MAP_PATH_POINT = 1;
	public static final int MAP_WALL_POINT = 0;
	public static final int MAP_CHARACTER_POINT = 4;
	public static final int MAP_OPPONENT_POINT = 5;
	public static final int MAP_CHEST_POINT = 6;
	public static final int MAP_PLAYER_POINT = 8;


	// Map Chest Items
	public static final String TITLE_ADD_CHEST_ITEMS = "Add Chest Items";
	public static final String MAP_CHEST_CONFIRM_DIALOG = "Do you want to add items to the chest ?";
	public static final int CHEST_ITEM_POPUP_WINDOW_WIDTH = (int) CHILD_POPUP_WINDOW_WIDTH - 250;
	public static final int CHEST_ITEM_WINDOW_HEIGHT = (int) CHILD_POPUP_WINDOW_HEIGHT - 250;
	
	// Map NPC
	public static final String TITLE_ADD_CHARACTER = "Add Character";
	public static final String MAP_CHARACTER_CONFIRM_DIALOG = "Do you want to add character to the map ?";

	// Map Variables
	public static String MAP_ROUT_PATH;
	public static String MAP_PATH_BOUNDARY_BUTTONS_NAME = "";

	// Status
	public static final String STATUS_SUCCESS = "SUCCESS";
	public static final String STATUS_EXIST = "EXIST";

	// Prompt Messages
	public static final String MSG_MAP_FILE_LOADED_SAVED = "Map file %s successfully!";
	public static final String MSG_UNABLE_TO_OPEN_FILE = "Unable to open file";
	public static final String MSG_NO_FILE_SELECTED = "No file selected";
	public static final String MSG_NO_CAMPAIGN_CREATED = "No Campaign created";
	public static final String MSG_SELECT_MORE_MAP = "Select more than one Map";
	public static final String MSG_CAMPAIGN_FILE_LOADED_SAVED = "Campaign file %s successfully!";
	public static final String MSG_ITEM_FILE_LOADED_SAVED = "Item %s successfully!";
	public static final String MSG_MAP_NOT_FOUND = "Maps not found.";
	public static final String MSG_DUPLICATE_NAME = "%s must be unique";
	public static final String MSG_CHARACTER_FILE_LOADED_SAVED = "Item %s successfully";
}
