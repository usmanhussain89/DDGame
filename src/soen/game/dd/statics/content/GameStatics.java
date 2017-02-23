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
	public static final String MENU_ITEM_CREATE_MAP = "Create Map";
	public static final String MENU_ITEM_LOAD_MAP = "Load Map";
	public static final String MENU_ITEM_CREATE_CHARACTER = "Create Character";
	public static final String MENU_ITEM_LOAD_CHARACTER = "Load Character";
	public static final String MENU_ITEM_EXIT = "Exit";
	public static final String MENU_ITEM_SAVE = "SAVE";
	
	// Popup Title
	public static final String TITLE_MSG_SET_SIZE_OF_MAP = "SET SIZE OF MAP";
	
	// Prompt Messages
	public static final String MSG_X_MAY_NOT_EMPTY = "Size of %s may not be empty";
	public static final String MSG_X_MUST_BE_IN_RANGE = "Size of %s must Lie between 1 - 30";
	
	//Window Titles
	public static final String MAP_MODE_CREATE = "(CREATE)";
	public static final String MAP_MODE_OPEN = "(OPEN)";
	public static final String TITLE_MAP_EDITOR = "MAP EDITOR";
}
