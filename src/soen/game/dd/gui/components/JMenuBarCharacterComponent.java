package soen.game.dd.gui.components;

import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;

import soen.game.dd.gui.system.CharacterEditor;
import soen.game.dd.models.Character;
import soen.game.dd.statics.content.GameStatics;
import soen.game.dd.statics.content.GameEnums.E_CharacterEditorMode;

/**
 * This class class create JMenuBar for Character
 * @author Usman
 *
 */
public class JMenuBarCharacterComponent {
	
	/**
	 * This mehtod create menu bar for character editor
	 * 
	 * @param character
	 * @param characterEditor
	 * @param characterEditorMode
	 * @return
	 */
	public JMenuBar getCharacterEditorJMenuBar(Character character, CharacterEditor characterEditor, E_CharacterEditorMode characterEditorMode) {
		JMenuBar menuBar = new JMenuBar();
		JMenu menuFile = new JMenu(GameStatics.MENU_FILE);
		JMenuItem menuItemExit = new JMenuItem(GameStatics.MENU_CHARACTER_EXIT);
		menuFile.add(menuItemExit);

		menuBar.add(menuFile);
		return menuBar;
	}
	
	/**
	 * This method closes the frame of the application
	 * 
	 * @param new_jframe
	 *            the frame of the application
	 */
	public void closeFrame(JFrame new_jframe) {
		new_jframe.dispose();
	}
}
