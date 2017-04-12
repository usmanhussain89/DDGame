package soen.game.dd.gui.components;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;

import soen.game.dd.fileio.GameEngineIO;
import soen.game.dd.models.GameEngine;
import soen.game.dd.statics.content.GameStatics;

/**
 * This class class create JMenuBar for Campaign
 * 
 * @author Usman
 *
 */
public class JMenuBarGameEngineComponent {

	/**
	 * This method create Menu for Campaign Editor Window
	 * 
	 * @param new_campaignModel
	 * @param new_jframe
	 * @return JMenu for Campaign Editor
	 */
	public JMenuBar getGameEngineEditorJMenuBar(GameEngine gameEngine, JFrame new_jframe) {
		JMenuBar menuBar = new JMenuBar();
		JMenu menuFile = new JMenu(GameStatics.MENU_FILE);
		JMenuItem menuItemSave = new JMenuItem(GameStatics.MENU_ITEM_SAVE);
		menuFile.add(menuItemSave);
		JMenuItem menuItemExit = new JMenuItem(GameStatics.MENU_ITEM_EXIT);
		menuFile.add(menuItemExit);

		class MenuItemAction implements ActionListener {

			@Override
			public void actionPerformed(ActionEvent new_event) {
				if (new_event.getSource().equals(menuItemSave)) {
					String gameName = JOptionPane.showInputDialog(new_jframe, "Enter Game name:");
					gameEngine.setGameName(gameName);
					String msg = new GameEngineIO().saveGame(gameEngine);
					if (msg.contains(GameStatics.STATUS_SUCCESS)) {
						JOptionPane.showMessageDialog(null, "Game save successfully");
						closeFrame(new_jframe);
					} else {
						JOptionPane.showMessageDialog(null, "Error!");
					}
				}
			}

		}

		menuItemSave.addActionListener(new MenuItemAction());
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
