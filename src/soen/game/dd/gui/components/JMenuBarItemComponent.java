package soen.game.dd.gui.components;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;

import soen.game.dd.models.Item;
import soen.game.dd.statics.content.GameStatics;

/**
 * This class class create JMenuBar for Item
 * 
 * @author Usman
 *
 */
public class JMenuBarItemComponent {

	/**
	 * This method create Menu for Item Editor Window
	 * 
	 * @param new_mapModel
	 * @param new_jframe
	 * @return JMenu for Map Editor
	 */
	public JMenuBar getItemEditorJMenuBar(Item item, JFrame new_jframe) {
		JMenuBar menuBar = new JMenuBar();
		JMenu menuFile = new JMenu(GameStatics.MENU_FILE);
		JMenuItem menuItemExit = new JMenuItem(GameStatics.MENU_ITEM_EXIT);
		menuFile.add(menuItemExit);

		class MenuItemAction implements ActionListener {

			@Override
			public void actionPerformed(ActionEvent new_event) {

			}
		}

		menuBar.add(menuFile);
		System.out.println("I'm here");
		return menuBar;
	}
}
