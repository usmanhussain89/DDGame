package soen.game.dd.gui.components;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;

import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JTextField;

import soen.game.dd.gui.system.CampaignEditor;
import soen.game.dd.gui.system.CharacterEditor;
import soen.game.dd.gui.system.ItemEditor;
import soen.game.dd.gui.system.MapEditor;
import soen.game.dd.logic.MapValidation;
import soen.game.dd.models.Campaign;
import soen.game.dd.models.FileWriterReader;
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
				} else if (e.getSource().equals(menuItemOpenMap)) {
					ArrayList<Map> maps = null;
					maps = new FileWriterReader().loadMaps();
					
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

				else if (e.getSource().equals(menuItemCreateItem)) {
					new ItemEditor(new_jframe, GameStatics.TITLE_ITEM_EDITOR, GameStatics.CHILD_POPUP_WINDOW_WIDTH,
							GameStatics.CHILD_POPUP_WINDOW_HEIGHT, E_ItemEditorMode.Create, null);
				}

				else if (e.getSource().equals(menuItemOpenItem)) {
					ArrayList<Item> items = new ArrayList<Item>();

					items = new FileWriterReader().loadItems();

					if (items != null) {
						new ItemEditor(new_jframe, GameStatics.TITLE_ITEM_EDITOR, GameStatics.CHILD_POPUP_WINDOW_WIDTH,
								GameStatics.CHILD_POPUP_WINDOW_HEIGHT, E_ItemEditorMode.Open, items);
					} else {
						JOptionPane.showMessageDialog(null, "No Items are created, Please create the items");
					}
				}

				else if (e.getSource().equals(menuItemCreateCharacter)) {
					new CharacterEditor(new_jframe, GameStatics.TITLE_CHARACTER_EDITOR,
							GameStatics.CHILD_POPUP_WINDOW_WIDTH, GameStatics.CHILD_POPUP_WINDOW_WIDTH, null,
							E_CharacterEditorMode.Create);

				}

				else if (e.getSource().equals(menuItemOpenCharacter)) {

				}

				else if (e.getSource().equals(menuItemCreateCampaign)) {
					ArrayList<Map> maps = null;
					maps = new FileWriterReader().loadMaps();

					if (maps != null) {
						new CampaignEditor(new_jframe, GameStatics.TITLE_CAMPAIGN_EDITOR,
								GameStatics.CHILD_POPUP_WINDOW_WIDTH, GameStatics.CHILD_POPUP_WINDOW_HEIGHT,
								new Campaign(), E_CampaignEditorMode.Create, maps, null, 0);
					}

					else {
						JOptionPane.showMessageDialog(null, GameStatics.MSG_MAP_NOT_FOUND);
					}
				}

				else if (e.getSource().equals(menuItemOpenCampaign)) {

					ArrayList<Campaign> campaigns = null;
					campaigns = new FileWriterReader().loadCampaigns();
					
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
							maps = new FileWriterReader().loadMaps();

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

				else if (e.getSource().equals(menuItemPlay)) {
					
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
						MapValidationStatus = (new MapValidation()).mapValidations(new_mapModel);
					}

					else {
						MapValidationStatus = (new MapValidation()).mapValidations(maps.get(index));
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

								String msg = new FileWriterReader().saveMap(new_mapModel);
								if (msg.contains(GameStatics.STATUS_SUCCESS)) {
									JOptionPane.showMessageDialog(null,
											String.format(GameStatics.MSG_MAP_FILE_LOADED_SAVED, "saved"));
									closeFrame(new_jframe);
								} else if(msg.contains(GameStatics.STATUS_EXIST)) {
									JOptionPane.showMessageDialog(null,
											String.format(GameStatics.MSG_DUPLICATE_NAME, "Map name"));
								} else {
									JOptionPane.showMessageDialog(null, msg);
								}
							}
						}

						else {
							String msg = new FileWriterReader().saveMaps(maps);
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

	/**
	 * This method create Menu for Campaign Editor Window
	 * 
	 * @param new_campaignModel
	 * @param new_jframe
	 * @return JMenu for Campaign Editor
	 */
	public JMenuBar getCampaignEditorJMenuBar(Campaign campaign, JFrame new_jframe, E_CampaignEditorMode new_mode,
			ArrayList<Campaign> campaigns, int index) {
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
					if (campaign != null) {
						System.out.println(campaign.getCampaignList().size());
						if (campaign.getCampaignList().size() > 1) {
							if (E_CampaignEditorMode.Create == new_mode) {
								if (!campaign.getCampaignName().equals("")) {

									String msg = new FileWriterReader().saveCampaign(campaign);
									if (msg.contains(GameStatics.STATUS_SUCCESS)) {
										JOptionPane.showMessageDialog(null,
												String.format(GameStatics.MSG_CAMPAIGN_FILE_LOADED_SAVED, "saved"));
										closeFrame(new_jframe);
									} else if(msg.contains(GameStatics.STATUS_EXIST)) {
										JOptionPane.showMessageDialog(null,
												String.format(GameStatics.MSG_DUPLICATE_NAME, "Campaign name"));
									} else {
										JOptionPane.showMessageDialog(null, msg);
									}
								} else {
									JOptionPane.showMessageDialog(null, "Enter Campaign name first");
								}
							}

							else {
								campaigns.set(index, campaign);
								String msg = new FileWriterReader().saveCampaigns(campaigns);
								if (msg.contains(GameStatics.STATUS_SUCCESS)) {
									JOptionPane.showMessageDialog(null,
											String.format(GameStatics.MSG_CAMPAIGN_FILE_LOADED_SAVED, "saved"));
									closeFrame(new_jframe);
								} else {
									JOptionPane.showMessageDialog(null, msg);
								}
							}
						}

						else {
							JOptionPane.showMessageDialog(null, "Select more than one map");
						}
					} else {
						JOptionPane.showMessageDialog(null, GameStatics.MSG_NO_CAMPAIGN_CREATED);
					}
				}
			}
		}

		menuItemSave.addActionListener(new MenuItemAction());
		menuBar.add(menuFile);
		return menuBar;
	}

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

	public JMenuBar getCharacterEditorJMenuBar(Character character, CharacterEditor characterEditor) {
		JMenuBar menuBar = new JMenuBar();
		JMenu menuFile = new JMenu(GameStatics.MENU_FILE);
		JMenuItem menuItemSave = new JMenuItem(GameStatics.MENU_CHARACTER_SAVE);
		menuFile.add(menuItemSave);
		JMenuItem menuItemExit = new JMenuItem(GameStatics.MENU_CHARACTER_EXIT);
		menuFile.add(menuItemExit);

		class MenuItemAction implements ActionListener {

			@Override
			public void actionPerformed(ActionEvent new_event) {
				if (new_event.getSource().equals(menuItemSave)) {
					System.out.println(ItemType.ARMOR.getAllowedAttributes());
				}
			}
		}

		menuItemSave.addActionListener(new MenuItemAction());
		menuBar.add(menuFile);
		return menuBar;
	}
}
