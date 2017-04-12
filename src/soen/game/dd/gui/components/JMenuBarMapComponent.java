package soen.game.dd.gui.components;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;

import soen.game.dd.fileio.MapIO;
import soen.game.dd.models.Map;
import soen.game.dd.statics.content.GameStatics;
import soen.game.dd.statics.content.GameEnums.E_MapEditorMode;

/**
 * This class class create JMenuBar for Map
 * 
 * @author Usman
 *
 */
public class JMenuBarMapComponent {

	/**
	 * This method create Menu for Map Editor Window
	 * 
	 * @param new_mapModel
	 * @param new_jframe
	 * @return JMenu for Map Editor
	 */
	public JMenuBar getMapEditorJMenuBar(Map new_mapModel, JFrame new_jframe, ArrayList<Map> maps,
			E_MapEditorMode mapEditorMode, int index) {
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
					String MapValidationStatus = "";

					if (E_MapEditorMode.Create == mapEditorMode) {
						MapValidationStatus = null;
					}

					else {
						MapValidationStatus = null;
					}

					if (MapValidationStatus != null) {
						JOptionPane.showMessageDialog(null, MapValidationStatus);
					} else {
						if (E_MapEditorMode.Create == mapEditorMode) {
							String mapName = JOptionPane.showInputDialog(new_jframe, "Enter Map name:");
							if (!mapName.equals("")) {

								new_mapModel.setMapName(mapName);
								new_mapModel.setMapRoutPath(GameStatics.MAP_ROUT_PATH);
								new_mapModel.setMapRoutBoundaries(GameStatics.MAP_PATH_BOUNDARY_BUTTONS_NAME);
								new_mapModel.getMapRoutPathList();

								String msg = new MapIO().saveMap(new_mapModel);
								if (msg.contains(GameStatics.STATUS_SUCCESS)) {
									JOptionPane.showMessageDialog(null,
											String.format(GameStatics.MSG_MAP_FILE_LOADED_SAVED, "saved"));
									closeFrame(new_jframe);
								} else if (msg.contains(GameStatics.STATUS_EXIST)) {
									JOptionPane.showMessageDialog(null,
											String.format(GameStatics.MSG_DUPLICATE_NAME, "Map name"));
								} else {
									JOptionPane.showMessageDialog(null, msg);
								}
							}
						}

						else {
							String msg = new MapIO().saveMaps(maps);
							if (msg.contains(GameStatics.STATUS_SUCCESS)) {
								JOptionPane.showMessageDialog(null,
										String.format(GameStatics.MSG_MAP_FILE_LOADED_SAVED, "saved"));
								closeFrame(new_jframe);
							} else {
								JOptionPane.showMessageDialog(null, msg);
							}
						}
					}
				}

				else if (new_event.getSource().equals(menuItemExit)) {
					closeFrame(new_jframe);
				}

			}

		}
		menuItemSave.addActionListener(new MenuItemAction());
		menuItemExit.addActionListener(new MenuItemAction());
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
