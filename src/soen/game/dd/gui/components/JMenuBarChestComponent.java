package soen.game.dd.gui.components;

import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;

import soen.game.dd.statics.content.GameStatics;

/**
 * This class class create JMenuBar for Chest
 * @author Usman
 *
 */
public class JMenuBarChestComponent {

	/**
	 * This method create Menu for Chest Items Window
	 * 
	 * @param new_jframe
	 * @return JMenu for Chest Menu
	 */
	public JMenuBar getMapChestItemJMenuBar(JFrame new_jframe) {
		JMenuBar menuBar = new JMenuBar();
		JMenu menuFile = new JMenu(GameStatics.MENU_FILE);
		JMenuItem menuItemSave = new JMenuItem(GameStatics.MENU_ITEM_SAVE);
		menuFile.add(menuItemSave);
		JMenuItem menuItemExit = new JMenuItem(GameStatics.MENU_ITEM_EXIT);
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
