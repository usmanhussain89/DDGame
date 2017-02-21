package soen.game.dd.gui.components;

import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;

import soen.game.dd.statics.content.GameStatics;

public class JMenuBarComponent {
	
	public JMenuBar getGameJMenuBar(final JFrame new_jframe){
		
		JMenuBar menuBar = new JMenuBar();
		
		JMenu menuFile = new JMenu(GameStatics.MENU_FILE);
		menuBar.add(menuFile);
		
		final JMenuItem menuItemCreateMap = new JMenuItem(GameStatics.MENU_ITEM_CREATE_MAP);
		menuFile.add(menuItemCreateMap);
		final JMenuItem menuItemLoadMap = new JMenuItem(GameStatics.MENU_ITEM_LOAD_MAP);
		menuFile.add(menuItemLoadMap);
		final JMenuItem menuItemCreateCharacter = new JMenuItem(GameStatics.MENU_ITEM_CREATE_CHARACTER);
		menuFile.add(menuItemCreateCharacter);
		final JMenuItem menuItemLoadCharacter = new JMenuItem(GameStatics.MENU_ITEM_LOAD_CHARACTER);
		menuFile.add(menuItemLoadCharacter);
		
		return menuBar;
	}
}
