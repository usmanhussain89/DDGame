package soen.game.dd.gui.components;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;

import javax.swing.JComboBox;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JTextField;

import soen.game.dd.fileio.CampaignIO;
import soen.game.dd.fileio.CharacterIO;
import soen.game.dd.fileio.ItemIO;
import soen.game.dd.fileio.MapIO;
import soen.game.dd.gui.system.CampaignEditor;
import soen.game.dd.gui.system.CharacterEditor;
import soen.game.dd.gui.system.GameEngineEditor;
import soen.game.dd.gui.system.ItemEditor;
import soen.game.dd.gui.system.MapEditor;
import soen.game.dd.logic.MapValidation;
import soen.game.dd.models.Campaign;
import soen.game.dd.models.Item;
import soen.game.dd.models.ItemType;
import soen.game.dd.models.Character;
import soen.game.dd.models.Map;
import soen.game.dd.statics.content.GameEnums.E_CampaignEditorMode;
import soen.game.dd.statics.content.GameEnums.E_CharacterEditorMode;
import soen.game.dd.statics.content.GameEnums.E_ItemEditorMode;
import soen.game.dd.statics.content.GameEnums.E_JFileChooserMode;
import soen.game.dd.statics.content.GameEnums.E_MapEditorMode;
import soen.game.dd.statics.content.GameStatics;

/**
 * This class is responsible for menu items of the Game
 * 
 * @author Usman
 *
 */
public class JMenuBarComponent {

	/**
	 * This method implement menu bar of the Game
	 * 
	 * @param new_jframe
	 *            as JFrame
	 * @return JMenuBar
	 */
	public JMenuBar getGameJMenuBar(JFrame new_jframe) {

		JMenuBar menuBar = new JMenuBar();

		JMenu menuFile = new JMenu(GameStatics.MENU_FILE);
		menuBar.add(menuFile);

		JMenuItem menuItemPlay = new JMenuItem(GameStatics.MENU_ITEM_PLAY);
		menuFile.add(menuItemPlay);

		JMenuItem menuItemCreateMap = new JMenuItem(GameStatics.MENU_ITEM_CREATE_MAP);
		menuFile.add(menuItemCreateMap);
		JMenuItem menuItemOpenMap = new JMenuItem(GameStatics.MENU_ITEM_OPEN_MAP);
		menuFile.add(menuItemOpenMap);

		JMenuItem menuItemCreateCharacter = new JMenuItem(GameStatics.MENU_ITEM_CREATE_CHARACTER);
		menuFile.add(menuItemCreateCharacter);
		JMenuItem menuItemOpenCharacter = new JMenuItem(GameStatics.MENU_ITEM_OPEN_CHARACTER);
		menuFile.add(menuItemOpenCharacter);

		JMenuItem menuItemCreateItem = new JMenuItem(GameStatics.MENU_ITEM_CREATE_ITEM);
		menuFile.add(menuItemCreateItem);
		JMenuItem menuItemOpenItem = new JMenuItem(GameStatics.MENU_ITEM_OPEN_ITEM);
		menuFile.add(menuItemOpenItem);

		JMenuItem menuItemCreateCampaign = new JMenuItem(GameStatics.MENU_ITEM_CREATE_CAMPAIGN);
		menuFile.add(menuItemCreateCampaign);
		JMenuItem menuItemOpenCampaign = new JMenuItem(GameStatics.MENU_ITEM_OPEN_CAMPAIGN);
		menuFile.add(menuItemOpenCampaign);

		JMenuItem menuItemExit = new JMenuItem(GameStatics.MENU_ITEM_EXIT);
		menuFile.add(menuItemExit);
		/**
		 * This class handle the menu Item on click events
		 */

		class MenuItemAction implements ActionListener {

			@Override
			public void actionPerformed(ActionEvent e) {
				// If the Menu option 'Create Map' is clicked
				if (e.getSource().equals(menuItemCreateMap)) {
					JTextField txtX = new JTextField();
					JTextField txtY = new JTextField();

					txtX.addKeyListener(new KeyAdapter() {
						public void KeyTyped(KeyEvent e) {
							char ch = e.getKeyChar();
							if (!(java.lang.Character.isDigit(ch) || (ch == KeyEvent.VK_BACK_SPACE)
									|| (ch == KeyEvent.VK_DELETE))) {
								e.consume();
							}
							if (txtX.getText().length() > 2)
								e.consume();
						}
					});

					Object[] message = { "Size of X:", txtX, "Size of Y:", txtY };

					int option = JOptionPane.showConfirmDialog(null, message, GameStatics.TITLE_MSG_SET_SIZE_OF_MAP,
							JOptionPane.OK_CANCEL_OPTION);

					if (option == JOptionPane.OK_OPTION) {
						String x = txtX.getText().trim();
						String y = txtY.getText().trim();

						if (x.length() == 0) {
							JOptionPane.showMessageDialog(null, String.format(GameStatics.MSG_X_MAY_NOT_EMPTY, "X"));
						}

						else if (y.length() == 0) {
							JOptionPane.showMessageDialog(null, String.format(GameStatics.MSG_X_MAY_NOT_EMPTY, "Y"));
						}

						else if (Integer.parseInt(x) < 1 || Integer.parseInt(x) > 30) {
							JOptionPane.showMessageDialog(null, String.format(GameStatics.MSG_X_MUST_BE_IN_RANGE, "X"));
						}

						else if (Integer.parseInt(y) < 1 || Integer.parseInt(y) > 30) {
							JOptionPane.showMessageDialog(null, String.format(GameStatics.MSG_X_MUST_BE_IN_RANGE, "Y"));
						}

						else {
							Map mapModel = new Map();
							mapModel.setMapWidth(Integer.parseInt(x));
							mapModel.setMapHeight(Integer.parseInt(y));
							new MapEditor(new_jframe, GameStatics.TITLE_MAP_EDITOR,
									GameStatics.CHILD_POPUP_WINDOW_WIDTH, GameStatics.CHILD_POPUP_WINDOW_HEIGHT,
									mapModel, E_MapEditorMode.Create, null, 0);
						}
					} else {

					}
				} else if (e.getSource().equals(menuItemOpenMap)) { // Open Map
					ArrayList<Map> maps = null;
					maps = new MapIO().loadMaps();

					if (maps != null) {
						int index = 0;
						System.out.println(maps.size() + " size");
						String[] mapNames = new String[maps.size()];

						for (Map m : maps) {
							mapNames[index] = m.getMapName();
							index++;
						}
						String mapName = (String) JOptionPane.showInputDialog(new_jframe, "Select Map :\n",
								"Maps Dialog", JOptionPane.PLAIN_MESSAGE, null, mapNames, "select");
						index = 0;
						for (Map mapModel : maps) {
							if (mapModel.getMapName().equals(mapName)) {
								new MapEditor(new_jframe, GameStatics.TITLE_MAP_EDITOR,
										GameStatics.CHILD_POPUP_WINDOW_WIDTH, GameStatics.CHILD_POPUP_WINDOW_HEIGHT,
										mapModel, E_MapEditorMode.Open, maps, index);
								break;
							}
							index++;
						}
					} else {
						JOptionPane.showMessageDialog(null, GameStatics.MSG_MAP_NOT_FOUND);
					}
					// } else {
					// JOptionPane.showMessageDialog(null,
					// GameStatics.MSG_NO_FILE_SELECTED);
					// }
				}

				else if (e.getSource().equals(menuItemCreateItem)) { // Create
																		// Item
					new ItemEditor(new_jframe, GameStatics.TITLE_ITEM_EDITOR, GameStatics.CHILD_POPUP_WINDOW_WIDTH,
							GameStatics.CHILD_POPUP_WINDOW_HEIGHT, E_ItemEditorMode.Create, null);
				}

				else if (e.getSource().equals(menuItemOpenItem)) { // Open Item
					ArrayList<Item> items = new ArrayList<Item>();

					items = new ItemIO().loadItems();

					if (items != null) {
						new ItemEditor(new_jframe, GameStatics.TITLE_ITEM_EDITOR, GameStatics.CHILD_POPUP_WINDOW_WIDTH,
								GameStatics.CHILD_POPUP_WINDOW_HEIGHT, E_ItemEditorMode.Open, items);
					} else {
						JOptionPane.showMessageDialog(null, "No Items are created, Please create the items");
					}
				}

				else if (e.getSource().equals(menuItemCreateCharacter)) { // Create
																			// Character
					new CharacterEditor(new_jframe, GameStatics.TITLE_CHARACTER_EDITOR,
							GameStatics.CHILD_POPUP_WINDOW_WIDTH, GameStatics.CHILD_POPUP_WINDOW_HEIGHT + 100,
							E_CharacterEditorMode.Create, null);

				}

				else if (e.getSource().equals(menuItemOpenCharacter)) { // Open
																		// Character
					ArrayList<Character> characters = new ArrayList<Character>();

					characters = new CharacterIO().loadCharacters();

					if (characters != null) {
						new CharacterEditor(new_jframe, GameStatics.TITLE_CHARACTER_EDITOR,
								GameStatics.CHILD_POPUP_WINDOW_WIDTH, GameStatics.CHILD_POPUP_WINDOW_HEIGHT + 100,
								E_CharacterEditorMode.Open, characters);
					} else {
						JOptionPane.showMessageDialog(null, "No Characters are created, Please create the characters");
					}
				}

				else if (e.getSource().equals(menuItemCreateCampaign)) { // Create
																			// Campaign
					ArrayList<Map> maps = null;
					maps = new MapIO().loadMaps();

					if (maps != null) {
						new CampaignEditor(new_jframe, GameStatics.TITLE_CAMPAIGN_EDITOR,
								GameStatics.CHILD_POPUP_WINDOW_WIDTH, GameStatics.CHILD_POPUP_WINDOW_HEIGHT,
								new Campaign(), E_CampaignEditorMode.Create, maps, null, 0);
					}

					else {
						JOptionPane.showMessageDialog(null, GameStatics.MSG_MAP_NOT_FOUND);
					}
				}

				else if (e.getSource().equals(menuItemOpenCampaign)) { // Open
																		// Campaign

					ArrayList<Campaign> campaigns = null;
					campaigns = new CampaignIO().loadCampaigns();

					if (campaigns != null) {
						int index = 0;
						System.out.println(campaigns.size() + " size");
						String[] campaignNames = new String[campaigns.size()];

						for (Campaign c : campaigns) {
							campaignNames[index] = c.getCampaignName();
							index++;
						}
						String mapName = (String) JOptionPane.showInputDialog(new_jframe, "Select Map :\n",
								"Maps Dialog", JOptionPane.PLAIN_MESSAGE, null, campaignNames, "select");
						index = 0;
						for (Campaign campaignModel : campaigns) {
							ArrayList<Map> maps = null;
							maps = new MapIO().loadMaps();

							if (campaignModel.getCampaignName().equals(mapName)) {
								new CampaignEditor(new_jframe, GameStatics.TITLE_MAP_EDITOR,
										GameStatics.CHILD_POPUP_WINDOW_WIDTH, GameStatics.CHILD_POPUP_WINDOW_HEIGHT,
										campaignModel, E_CampaignEditorMode.Open, maps, campaigns, index);
								break;
							}
							index++;
						}
					} else {
						JOptionPane.showMessageDialog(null, "No Campaign Found !");
					}
				}

				else if (e.getSource().equals(menuItemPlay)) { // Play Game

					ArrayList<Character> characters = new CharacterIO().loadCharacters();
					Character character = null;
					JComboBox<String> cbCharacter = new JComboBox<String>();

					for (Character c : characters) {
						cbCharacter.addItem(c.getName());
					}

					Object[] messageCharacter = { "SELECT CHARACTER:", cbCharacter };

					int optionCharacter = JOptionPane.showConfirmDialog(null, messageCharacter,
							GameStatics.TITLE_MSG_SET_SIZE_OF_MAP, JOptionPane.OK_CANCEL_OPTION);

					if (optionCharacter == JOptionPane.OK_OPTION) {
						for (Character c : characters) {
							if (c.getName().equals((String) cbCharacter.getSelectedItem())) {
								character = c;
								break;
							}
						}

						ArrayList<Campaign> campaigns = new CampaignIO().loadCampaigns();
						Campaign campaign = null;
						JComboBox<String> cbCampaigns = new JComboBox<String>();

						for (Campaign ca : campaigns) {
							cbCampaigns.addItem(ca.getCampaignName());
						}
						Object[] messageCampaign = { "Size of X:", cbCampaigns };

						if (JOptionPane.showConfirmDialog(null, messageCampaign, GameStatics.TITLE_MSG_SET_SIZE_OF_MAP,
								JOptionPane.OK_CANCEL_OPTION) == 0) {
							for (Campaign ca : campaigns) {
								if (cbCampaigns.getSelectedItem().equals(ca.getCampaignName())) {
									campaign = ca;
									break;
								}
							}

							if (campaign != null && character != null) {
								// new MapEditor(new_jframe,
								// GameStatics.TITLE_MAP_PlAY,
								// GameStatics.CHILD_POPUP_WINDOW_WIDTH,
								// GameStatics.CHILD_POPUP_WINDOW_HEIGHT,
								// campaign, character, E_MapEditorMode.Play);
								new GameEngineEditor(character, campaign);
							}
						}
					}
				}

				else {
					closeFrame(new_jframe);
				}
			}
		}

		menuItemCreateMap.addActionListener(new MenuItemAction());
		menuItemOpenMap.addActionListener(new MenuItemAction());
		menuItemCreateItem.addActionListener(new MenuItemAction());
		menuItemOpenItem.addActionListener(new MenuItemAction());
		menuItemCreateCampaign.addActionListener(new MenuItemAction());
		menuItemOpenCampaign.addActionListener(new MenuItemAction());
		menuItemCreateCharacter.addActionListener(new MenuItemAction());
		menuItemOpenCharacter.addActionListener(new MenuItemAction());
		menuItemExit.addActionListener(new MenuItemAction());
		menuItemPlay.addActionListener(new MenuItemAction());
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