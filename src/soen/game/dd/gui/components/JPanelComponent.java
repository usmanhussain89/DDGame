package soen.game.dd.gui.components;

import java.awt.Color;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.List;
import java.awt.Point;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Locale;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;

import soen.game.dd.fileio.CharacterIO;
import soen.game.dd.gui.system.ChestItemWindow;
import soen.game.dd.gui.system.MapEditor;
import soen.game.dd.models.Campaign;
import soen.game.dd.models.CharacterAttribute;
import soen.game.dd.models.FileWriterReader;
import soen.game.dd.models.Item;
import soen.game.dd.models.ItemType;
import soen.game.dd.models.Map;
import soen.game.dd.models.Character;
import soen.game.dd.statics.content.GameEnums.E_CampaignEditorMode;
import soen.game.dd.statics.content.GameEnums.E_CharacterEditorMode;
import soen.game.dd.statics.content.GameEnums.E_ItemEditorMode;
import soen.game.dd.statics.content.GameEnums.E_JFileChooserMode;
import soen.game.dd.statics.content.GameEnums.E_MapEditorMode;
import soen.game.dd.statics.content.GameStatics;

/**
 * This class create the panel for the Map
 * 
 * @author Usman
 *
 */
public class JPanelComponent {

	private E_MapEditorMode mapEditorMode;
	private E_ItemEditorMode itemEditorMode;
	private E_CampaignEditorMode campaignEditorMode;
	// 2D Array of JButton
	private JButton mapButtonsGrid2DArray[][];
	private JButton jButtonEntry;
	private JButton jButtonExit;
	private JButton jButtonCharacter;
	private JButton jButtonOpponent;
	private JButton jButtonChest;
	private Map mapModel = null;
	String itemName = "";
	private Item itemModel = null;
	String characterName = "";

	/**
	 * This method create the JPanel for the Map Editor and return JPanel which
	 * set Content Pane for the frame
	 * 
	 * @param new_mapModel
	 * @param new_parentDimension
	 * @param new_mode
	 * @return JPanel
	 */
	public JPanel getMapEditorGridPanel(Map new_mapModel, Dimension new_parentDimension, E_MapEditorMode new_mode,
			ArrayList<Map> maps, int index) {
		mapEditorMode = new_mode;
		JPanel panel;
		GridLayout gridLayout;

		// panel = new JPanel();
		// When Create Mode Initialize the mapGridSelection to new
		if (E_MapEditorMode.Create == mapEditorMode) {
			new_mapModel.mapGridSelection = new int[new_mapModel.getMapHeight()][new_mapModel.getMapWidth()];
			panel = new JPanel();
			gridLayout = new GridLayout(new_mapModel.getMapHeight(), new_mapModel.getMapWidth(), 3, 3);
			panel.setLayout(gridLayout);
		}

		else {
			panel = new JPanel();
			// gridLayout = new GridLayout(new_mapModel.getMapHeight(),
			// new_mapModel.getMapWidth(), 3, 3);
			gridLayout = new GridLayout(new_mapModel.getMapHeight(), new_mapModel.getMapWidth(), 3, 3);
			panel.setLayout(gridLayout);
		}

		mapButtonsGrid2DArray = new JButton[new_mapModel.getMapHeight()][new_mapModel.getMapWidth()];

		for (int i = 0; i < new_mapModel.getMapHeight(); i++) {
			for (int j = 0; j < new_mapModel.getMapWidth(); j++) {
				mapButtonsGrid2DArray[i][j] = new JButton();
				int value = 0;
				int multiple = 0;

				multiple = new_mapModel.getMapWidth();

				if (i == 0 && j == 0)
					value = 0;
				else
					value = 1 + j + (i * multiple);

				mapButtonsGrid2DArray[i][j].setName(value + ":" + i + ":" + j);

				if (E_MapEditorMode.Create == mapEditorMode) {
					new_mapModel.mapGridSelection[i][j] = 0;
					mapButtonsGrid2DArray[i][j].setBackground(Color.gray);
					// Click event
					addButtonClickEvents(mapButtonsGrid2DArray[i][j], new_mapModel, new_mode, maps, index);
					// Right Click Event
					addMouseClickOnButtonEvents(mapButtonsGrid2DArray[i][j], new_mapModel, new_mode, maps, index);
				}

				else if (E_MapEditorMode.Open == mapEditorMode) {

					if (new_mapModel.mapGridSelection[i][j] == GameStatics.MAP_PATH_POINT) {
						mapButtonsGrid2DArray[i][j].setBackground(Color.green);
					} else if (new_mapModel.mapGridSelection[i][j] == GameStatics.MAP_ENTRY_POINT) {
						mapButtonsGrid2DArray[i][j].setBackground(Color.red);
						mapButtonsGrid2DArray[i][j].setText("Entry");
					} else if (new_mapModel.mapGridSelection[i][j] == GameStatics.MAP_EXIT_POINT) {
						mapButtonsGrid2DArray[i][j].setBackground(Color.red);
						mapButtonsGrid2DArray[i][j].setText("Exit");
					} else if (new_mapModel.mapGridSelection[i][j] == GameStatics.MAP_CHARACTER_POINT) {
						mapButtonsGrid2DArray[i][j].setBackground(Color.white);
						mapButtonsGrid2DArray[i][j].setText("Character");
					} else if (new_mapModel.mapGridSelection[i][j] == GameStatics.MAP_OPPONENT_POINT) {
						mapButtonsGrid2DArray[i][j].setBackground(Color.blue);
						mapButtonsGrid2DArray[i][j].setText("Opponent");
					} else if (new_mapModel.mapGridSelection[i][j] == GameStatics.MAP_CHEST_POINT) {
						mapButtonsGrid2DArray[i][j].setBackground(Color.orange);
						mapButtonsGrid2DArray[i][j].setText("Chest");
					} else {
						mapButtonsGrid2DArray[i][j].setBackground(Color.gray);
					}

					// Click event
					addButtonClickEvents(mapButtonsGrid2DArray[i][j], new_mapModel, new_mode, maps, index);
					// Right Click Event
					addMouseClickOnButtonEvents(mapButtonsGrid2DArray[i][j], new_mapModel, new_mode, maps, index);
				}

				mapButtonsGrid2DArray[i][j].setOpaque(true);
				mapButtonsGrid2DArray[i][j].setBorderPainted(false);
				panel.add(mapButtonsGrid2DArray[i][j]);
			}
		}

		return panel;
	}

	/**
	 * Actually Map Grid contains buttons inside each cell of grid and on click
	 * of these button we perform certain actions this method implements logic
	 * behind the on click of button
	 * 
	 * @param new_button
	 *            Type JButton
	 * @param new_mapModel
	 *            Type MapModel
	 */
	private void addButtonClickEvents(JButton new_button, Map new_mapModel, E_MapEditorMode new_mode,
			ArrayList<Map> maps, int index) {
		new_button.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				JButton btn = ((JButton) e.getSource());
				String[] nameArry = btn.getName().split(":");
				int _i = Integer.parseInt(nameArry[1]);
				int _j = Integer.parseInt(nameArry[2]);

				// If the last point was a wall Point
				if (new_mapModel.mapGridSelection[_i][_j] == GameStatics.MAP_WALL_POINT) {
					btn.setBackground(Color.green);
					new_mapModel.mapGridSelection[_i][_j] = GameStatics.MAP_PATH_POINT;
				}
				// If the last point was a path point
				else if (new_mapModel.mapGridSelection[_i][_j] == GameStatics.MAP_PATH_POINT) {
					new_mapModel.mapGridSelection[_i][_j] = GameStatics.MAP_WALL_POINT;
					btn.setBackground(Color.gray);
				}
				// the last point was an Entry point
				else if (new_mapModel.mapGridSelection[_i][_j] == GameStatics.MAP_ENTRY_POINT) {
					new_mapModel.mapGridSelection[_i][_j] = GameStatics.MAP_WALL_POINT;
					btn.setBackground(Color.gray);
					btn.setText("");
					new_mapModel.isEntryDone = false;
					jButtonEntry = null;
				}
				// If the last point was an Exit Point
				else if (new_mapModel.mapGridSelection[_i][_j] == GameStatics.MAP_EXIT_POINT) {
					new_mapModel.mapGridSelection[_i][_j] = GameStatics.MAP_WALL_POINT;
					btn.setBackground(Color.gray);
					btn.setText("");
					new_mapModel.isExitDone = false;
					jButtonExit = null;
				}
				// If the last point was an Character Point
				else if (new_mapModel.mapGridSelection[_i][_j] == GameStatics.MAP_CHARACTER_POINT) {
					new_mapModel.mapGridSelection[_i][_j] = GameStatics.MAP_WALL_POINT;
					btn.setBackground(Color.gray);
					btn.setText("");
					new_mapModel.isCharacterDone = false;
					jButtonCharacter = null;
				}
				// If the last point was an Opponent Point
				else if (new_mapModel.mapGridSelection[_i][_j] == GameStatics.MAP_OPPONENT_POINT) {
					new_mapModel.mapGridSelection[_i][_j] = GameStatics.MAP_WALL_POINT;
					btn.setBackground(Color.gray);
					btn.setText("");
					new_mapModel.isOpponentDone = false;
					jButtonOpponent = null;
				}
				// If the last point was an Chest Point
				else if (new_mapModel.mapGridSelection[_i][_j] == GameStatics.MAP_CHEST_POINT) {
					if (JOptionPane.showConfirmDialog(null, "Do you want to edit items in the chest", "WARNING",
							JOptionPane.YES_NO_OPTION) == JOptionPane.NO_OPTION) {
						new_mapModel.mapGridSelection[_i][_j] = GameStatics.MAP_WALL_POINT;
						btn.setBackground(Color.gray);
						btn.setText("");
						new_mapModel.isChestDone = false;
						new_mapModel.setChestPoint(null);
						new_mapModel.mapSelectedItem.clear();
						jButtonChest = null;
					} else {
						ArrayList<Item> items = new ArrayList<Item>();

						items = new FileWriterReader().loadItems();
						if (items != null)
							new ChestItemWindow(new_mapModel, items);

						else {
							JOptionPane.showMessageDialog(null,
									"No Items are created, Please create the items");
						}
					}
				}

				if (E_MapEditorMode.Open == new_mode) {
					maps.set(index, new_mapModel);
				}
			}
		});
	}

	/**
	 * Actually Map Grid contains buttons inside each cell of grid and on right
	 * click on these button we perform certain actions this method implements
	 * logic behind the on right click of button
	 * 
	 * @param new_button
	 *            Type JButton
	 * @param new_mapModel
	 *            Type MapModel
	 */
	private void addMouseClickOnButtonEvents(JButton new_button, Map new_mapModel, E_MapEditorMode new_mode,
			ArrayList<Map> maps, int index) {
		new_button.addMouseListener(new MouseAdapter() {
			public void mouseClicked(MouseEvent e) {

				// When Right button Clicked of Mouse
				if (e.getButton() == MouseEvent.BUTTON1) {
				}
				// When Middle button Clicked of Mouse
				else if (e.getButton() == MouseEvent.BUTTON2) {
				}
				// When Left button Clicked of Mouse
				else if (e.getButton() == MouseEvent.BUTTON3) {
					JButton btn = ((JButton) e.getSource());
					String[] nameArry = btn.getName().split(":");
					int _i = Integer.parseInt(nameArry[1]);
					int _j = Integer.parseInt(nameArry[2]);

					// if the last point was an Entry Button
					if (new_mapModel.mapGridSelection[_i][_j] == GameStatics.MAP_ENTRY_POINT) {
						// Change mapGridSelection value
						new_mapModel.mapGridSelection[_i][_j] = GameStatics.MAP_WALL_POINT;
						btn.setBackground(Color.gray);
						btn.setText("");
						jButtonEntry = null;
						new_mapModel.isEntryDone = false;
						new_mapModel.setEntryPoint(null);
					}
					// if the last point was an Exit Button
					else if (new_mapModel.mapGridSelection[_i][_j] == GameStatics.MAP_EXIT_POINT) {
						new_mapModel.mapGridSelection[_i][_j] = GameStatics.MAP_WALL_POINT;
						btn.setBackground(Color.gray);
						btn.setText("");
						jButtonExit = null;
						new_mapModel.isExitDone = false;
						new_mapModel.setExitPoint(null);
					}
					// If the last point was an Character Button
					else if (new_mapModel.mapGridSelection[_i][_j] == GameStatics.MAP_CHARACTER_POINT) {
						new_mapModel.mapGridSelection[_i][_j] = GameStatics.MAP_WALL_POINT;
						btn.setBackground(Color.gray);
						btn.setText("");
						jButtonCharacter = null;
						new_mapModel.isCharacterDone = false;
						new_mapModel.setCharacterPoint(null);
					}
					// If the last point was an Opponent Button
					else if (new_mapModel.mapGridSelection[_i][_j] == GameStatics.MAP_OPPONENT_POINT) {
						new_mapModel.mapGridSelection[_i][_j] = GameStatics.MAP_WALL_POINT;
						btn.setBackground(Color.gray);
						btn.setText("");
						jButtonOpponent = null;
						new_mapModel.isOpponentDone = false;
						new_mapModel.setOpponentPoint(null);
					}
					// If the last point was an Chest Button
					else if (new_mapModel.mapGridSelection[_i][_j] == GameStatics.MAP_CHEST_POINT) {
						if (JOptionPane.showConfirmDialog(null, "Do you want to remove chest from the map?", "WARNING",
								JOptionPane.YES_NO_OPTION) == JOptionPane.YES_OPTION) {
							new_mapModel.mapGridSelection[_i][_j] = GameStatics.MAP_WALL_POINT;
							btn.setBackground(Color.gray);
							btn.setText("");
							jButtonChest = null;
							new_mapModel.isChestDone = false;
							new_mapModel.setChestPoint(null);
							new_mapModel.mapSelectedItem.clear();
						}
					}
					// if the last point was Path and Wall button
					else if (new_mapModel.mapGridSelection[_i][_j] == GameStatics.MAP_WALL_POINT
							|| new_mapModel.mapGridSelection[_i][_j] == GameStatics.MAP_PATH_POINT) {
						// Check Entry is already Selected
						if (!new_mapModel.isEntryDone) {
							btn.setBackground(Color.RED);
							btn.setText("Entry");
							jButtonEntry = btn;
							new_mapModel.mapGridSelection[_i][_j] = GameStatics.MAP_ENTRY_POINT;
							new_mapModel.setEntryPoint(new Point(_i, _j));
							new_mapModel.isEntryDone = true;
						} else if (!new_mapModel.isExitDone) {
							btn.setBackground(Color.RED);
							btn.setText("Exit");
							jButtonExit = btn;
							new_mapModel.mapGridSelection[_i][_j] = GameStatics.MAP_EXIT_POINT;
							new_mapModel.setExitPoint(new Point(_i, _j));
							new_mapModel.isExitDone = true;
						} else if (!new_mapModel.isChestDone) {
							btn.setBackground(Color.ORANGE);
							btn.setText("Chest");
							jButtonChest = btn;
							new_mapModel.mapGridSelection[_i][_j] = GameStatics.MAP_CHEST_POINT;
							new_mapModel.setChestPoint(new Point(_i, _j));
							new_mapModel.isChestDone = true;

							int option = JOptionPane.showConfirmDialog(null, GameStatics.MAP_CHEST_CONFIRM_DIALOG,
									GameStatics.TITLE_ADD_CHEST_ITEMS, JOptionPane.OK_CANCEL_OPTION);

							if (option == JOptionPane.OK_OPTION) {
								ArrayList<Item> items = new ArrayList<Item>();

								items = new FileWriterReader().loadItems();
								if (items != null)
									new ChestItemWindow(new_mapModel, items);

								else {
									JOptionPane.showMessageDialog(null,
											"No Items are created, Please create the items");
								}
							}

						} else if (!new_mapModel.isCharacterDone || !new_mapModel.isOpponentDone) {
							if (!new_mapModel.isCharacterDone) {
								btn.setBackground(Color.WHITE);
								btn.setText("Character");
								jButtonCharacter = btn;
								new_mapModel.mapGridSelection[_i][_j] = GameStatics.MAP_CHARACTER_POINT;
								new_mapModel.setCharacterPoint(new Point(_i, _j));
								new_mapModel.isCharacterDone = true;
							} else if (!new_mapModel.isOpponentDone) {
								btn.setBackground(Color.BLUE);
								btn.setText("Opponent");
								jButtonOpponent = btn;
								new_mapModel.mapGridSelection[_i][_j] = GameStatics.MAP_OPPONENT_POINT;
								new_mapModel.setOpponentPoint(new Point(_i, _j));
								new_mapModel.isOpponentDone = true;
							}
						} else {
							JOptionPane.showMessageDialog(null, "All Points Already Selected");
						}
					}

					if (E_MapEditorMode.Open == new_mode) {
						maps.set(index, new_mapModel);
					}
				}
			}
		});
	}

	/**
	 * This method create the JPanel for the Item Editor and return JPanel which
	 * set Content Pane for the frame
	 * 
	 * @param new_mode
	 * @return JPanel
	 */
	public JPanel getItemEditorGridPanel(Item item, Dimension new_parentDimension, E_ItemEditorMode new_mode,
			ArrayList<Item> items) {

		itemEditorMode = new_mode;
		JPanel panel;
		// JTextField txtItemName = null;

		panel = new JPanel();
		;
		panel.setLayout(null);

		JLabel lblItemName = new JLabel("Item Name: ");
		lblItemName.setBounds(40, 30, 80, 25);
		panel.add(lblItemName);

		JTextField txtItemName = new JTextField(40);
		txtItemName.setBounds(170, 30, 120, 25);
		txtItemName.setVisible(false);

		JComboBox<String> cbItemName = new JComboBox<String>();
		cbItemName.setBounds(170, 30, 120, 25);
		cbItemName.setEditable(true);
		cbItemName.setVisible(false);
		if (E_ItemEditorMode.Open == new_mode) {
			txtItemName.setVisible(false);
			cbItemName.setVisible(true);
			int index = 0;

			for (Item i : items) {
				if (i != null) {
					cbItemName.addItem(i.getName());
					cbItemName.setName("" + index);
					index++;
				}
			}

			panel.add(cbItemName);
		}

		else {
			txtItemName.setVisible(true);
			cbItemName.setVisible(false);

			panel.add(txtItemName);
		}

		JLabel lblItemType = new JLabel("Item Type: ");
		lblItemType.setBounds(40, 75, 80, 25);
		;
		panel.add(lblItemType);

		JComboBox cbItemType = new JComboBox(ItemType.values());
		cbItemType.setBounds(170, 75, 120, 25);
		panel.add(cbItemType);

		JLabel lblCharacterAttr = new JLabel("Character Attribute: ");
		lblCharacterAttr.setBounds(40, 120, 120, 25);
		panel.add(lblCharacterAttr);

		JComboBox cbCharacterAttr = new JComboBox(ItemType.HELMET.getAllowedAttributes().toArray());
		cbCharacterAttr.setBounds(170, 120, 120, 25);
		panel.add(cbCharacterAttr);

		JLabel lblBonusAmount = new JLabel("Bonus Amount: ");
		lblBonusAmount.setBounds(40, 165, 100, 25);
		panel.add(lblBonusAmount);

		Integer[] bonuses = new Integer[] { 1, 2, 3, 4, 5 };
		JComboBox cbBonusAmount = new JComboBox(bonuses);
		cbBonusAmount.setBounds(170, 165, 100, 25);
		panel.add(cbBonusAmount);

		JButton btnAddItem = new JButton("Save Item");
		btnAddItem.setBounds(40, 230, 120, 25);
		panel.add(btnAddItem);

		if (E_ItemEditorMode.Open == new_mode) {
			cbItemName.setSelectedItem(items.get(0).getName());
			cbItemType.setSelectedItem(items.get(0).getItemType());
			cbCharacterAttr.setSelectedItem(items.get(0).getEnhancedAttribute());
			cbBonusAmount.setSelectedItem(items.get(0).getBonusAmount());
			itemName = items.get(0).getName();
		}

		cbItemType.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				System.out.println(cbItemType.getSelectedItem());
				cbCharacterAttr.removeAllItems();
				for (CharacterAttribute c : ((ItemType) cbItemType.getSelectedItem()).getAllowedAttributes()) {
					cbCharacterAttr.addItem(c);
				}
			}
		});
		cbItemName.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				cbItemType.setSelectedItem(items.get(cbItemName.getSelectedIndex()).getItemType());
				cbCharacterAttr.setSelectedItem(items.get(cbItemName.getSelectedIndex()).getEnhancedAttribute());
				cbBonusAmount.setSelectedItem(items.get(cbItemName.getSelectedIndex()).getBonusAmount());
				itemName = items.get(cbItemName.getSelectedIndex()).getName();
			}
		});

		btnAddItem.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				if (E_ItemEditorMode.Create == new_mode) {
					itemName = txtItemName.getText();
				}

				if (!itemName.equals("")) {
					item.setName(itemName);
					item.setItemType((ItemType) cbItemType.getSelectedItem());
					item.setCharacterAttribute((CharacterAttribute) cbCharacterAttr.getSelectedItem());
					item.setBonusAmount((Integer) cbBonusAmount.getSelectedItem());
					if (item.isValid()) {
						String msg = "";
						if (E_ItemEditorMode.Open == new_mode) {
							items.set(cbItemName.getSelectedIndex(), item);
							msg = new FileWriterReader().saveItems(items);
						}

						else {
							msg = new FileWriterReader().saveItem(item);
						}
						if (msg.contains(GameStatics.STATUS_SUCCESS)) {
							JOptionPane.showMessageDialog(null,
									String.format(GameStatics.MSG_ITEM_FILE_LOADED_SAVED, "saved"));
							if (E_ItemEditorMode.Create == new_mode) {
								txtItemName.setText("");
							}
						} else if(msg.contains(GameStatics.STATUS_EXIST)) {
							JOptionPane.showMessageDialog(null,
									String.format(GameStatics.MSG_DUPLICATE_NAME, "Item name"));
						} else {
							JOptionPane.showMessageDialog(null, msg);
						}
					}
				}
			}
		});

		return panel;
	}

	/**
	 * This method create the JPanel for the Campaign Editor and return JPanel
	 * which set Content Pane for the frame
	 * 
	 * @param new_mode
	 * @return JPanel
	 */
	public JPanel getCampaignEditorGridPanel(Campaign campaign, E_CampaignEditorMode new_mode, JFrame frame,
			ArrayList<Map> maps, ArrayList<Campaign> campaigns, int index) {

		campaignEditorMode = new_mode;
		JPanel panel;

		panel = new JPanel();
		panel.setLayout(null);

		JLabel lblCampaignName = new JLabel("Campaign Name: ");
		lblCampaignName.setBounds(40, 30, 100, 25);
		panel.add(lblCampaignName);

		JTextField txtCampaignName = new JTextField(30);
		txtCampaignName.setBounds(160, 30, 150, 25);
		panel.add(txtCampaignName);
		
		if(E_CampaignEditorMode.Open == new_mode)
			txtCampaignName.setEnabled(false);

		JLabel lblSelectMap = new JLabel("Select Map: ");
		lblSelectMap.setBounds(40, 75, 100, 25);
		panel.add(lblSelectMap);

		JComboBox<String> cbMapName = new JComboBox<String>();
		cbMapName.setBounds(160, 75, 100, 25);

		for (Map map : maps) {
			cbMapName.addItem(map.getMapName());
		}
		panel.add(cbMapName);

		JButton btnAddMap = new JButton("Add Map");
		btnAddMap.setBounds(280, 75, 90, 25);
		panel.add(btnAddMap);

		JLabel lblSelectedMap = new JLabel("Selected Map: ");
		lblSelectedMap.setBounds(40, 120, 100, 25);
		panel.add(lblSelectedMap);

		List mapList = new List();
		mapList.setBounds(160, 120, 150, 150);
		panel.add(mapList);

		JButton btnRemoveMap = new JButton("Remove Map");
		btnRemoveMap.setBounds(160, 290, 130, 25);
		// btnRemoveMap.setVisible(false);
		panel.add(btnRemoveMap);

		if (E_CampaignEditorMode.Open == new_mode) {
			txtCampaignName.setText(campaigns.get(index).getCampaignName());
			cbMapName.setSelectedIndex(index);
			System.out.println(campaigns.get(index).getCampaignList().size());
			for (Map m : campaigns.get(index).getCampaignList()) {
				mapList.add(m.getMapName());
			}
		}

		class PanelAction implements ActionListener {
			@Override
			public void actionPerformed(ActionEvent e) {

				if (e.getSource().equals(btnAddMap)) {

					System.out.print(cbMapName.getSelectedIndex() + " " + maps.size());
					campaign.setCampaignList(maps.get(cbMapName.getSelectedIndex()));
					campaign.setCampaignName(txtCampaignName.getText());
					mapList.add(maps.get(cbMapName.getSelectedIndex()).getMapName());
					btnRemoveMap.setVisible(true);
				}

				else if (e.getSource().equals(btnRemoveMap)) {
					if (mapList.getSelectedIndex() >= 0) {
						int index = mapList.getSelectedIndex();
						mapList.remove(index);
						campaign.removeMapFromList(index);
					}
				}
			}

		}
		btnAddMap.addActionListener(new PanelAction());
		btnRemoveMap.addActionListener(new PanelAction());

		return panel;
	}

	public Container getCharacterEditorGridPanel(Character pCharacter, E_CharacterEditorMode characterEditorMode,
			JFrame frame, ArrayList<Character> characters) {
		JPanel panel;
		Character character = pCharacter;
		ArrayList<Item> items = new FileWriterReader().loadItems();

		class ItemComboItem {
			private Item value;
			private String label;

			public ItemComboItem(Item value) {
				this.value = value;
				this.label = value.getName();
			}

			public Item getValue() {
				return this.value;
			}

			@SuppressWarnings("unused")
			public String getLabel() {
				return this.label;
			}

			@Override
			public String toString() {
				return label;
			}
		}

		class ComboItemListGenerator {
			private ItemType itemType;

			public ComboItemListGenerator(ItemType type) {
				this.itemType = type;
			}

			public ItemComboItem[] generateList() {
				ArrayList<ItemComboItem> combo_items = new ArrayList<ItemComboItem>();
				if (itemType == null) {
					for (Item i : items) {
						combo_items.add(new ItemComboItem(i));
					}
				} else {
					for (Item i : items) {
						if (i.getItemType() == itemType) {
							combo_items.add(new ItemComboItem(i));
						}
					}
				}
				return combo_items.toArray(new ItemComboItem[combo_items.size()]);
			}
		}

		panel = new JPanel();
		panel.setLayout(null);

		JLabel lblCharacterName = new JLabel("Character Name: ");
		lblCharacterName.setBounds(40, 30, 100, 25);
		panel.add(lblCharacterName);

		JTextField txtCharacterName = new JTextField(30);
		txtCharacterName.setBounds(160, 30, 150, 25);
		panel.add(txtCharacterName);
		
		JComboBox<String> cbCharacterName = new JComboBox<String>();
		cbCharacterName.setBounds(170, 30, 120, 25);
		cbCharacterName.setEditable(true);
		cbCharacterName.setVisible(false);
		if (E_CharacterEditorMode.Open == characterEditorMode) {
			txtCharacterName.setVisible(false);
			cbCharacterName.setVisible(true);
			int index = 0;

			for (Character i : characters) {
				if (i != null) {
					cbCharacterName.addItem(i.getName());
					cbCharacterName.setName("" + index);
					index++;
				}
			}

			panel.add(cbCharacterName);
		}

		else {
			txtCharacterName.setVisible(true);
			cbCharacterName.setVisible(false);

			panel.add(txtCharacterName);
		}

		JLabel lblSelectHelmet = new JLabel("Helmet: ");
		lblSelectHelmet.setBounds(40, 75, 100, 25);
		panel.add(lblSelectHelmet);

		JComboBox<ItemComboItem> cbHelmet = new JComboBox<ItemComboItem>(
				new ComboItemListGenerator(ItemType.HELMET).generateList());
		cbHelmet.setBounds(170, 75, 120, 25);
		panel.add(cbHelmet);

		JLabel lblSelectArmor = new JLabel("Armor: ");
		lblSelectArmor.setBounds(40, 100, 100, 25);
		panel.add(lblSelectArmor);

		JComboBox<ItemComboItem> cbArmor = new JComboBox<ItemComboItem>(
				new ComboItemListGenerator(ItemType.ARMOR).generateList());
		cbArmor.setBounds(170, 100, 120, 25);
		panel.add(cbArmor);

		JLabel lblSelectShield = new JLabel("Shield: ");
		lblSelectShield.setBounds(40, 125, 100, 25);
		panel.add(lblSelectShield);

		JComboBox<ItemComboItem> cbShield = new JComboBox<ItemComboItem>(
				new ComboItemListGenerator(ItemType.SHIELD).generateList());
		cbShield.setBounds(170, 125, 120, 25);
		panel.add(cbShield);

		JLabel lblSelectRing = new JLabel("Ring: ");
		lblSelectRing.setBounds(40, 150, 100, 25);
		panel.add(lblSelectRing);

		JComboBox<ItemComboItem> cbRing = new JComboBox<ItemComboItem>(
				new ComboItemListGenerator(ItemType.RING).generateList());
		cbRing.setBounds(170, 150, 120, 25);
		panel.add(cbRing);

		JLabel lblSelectBelt = new JLabel("Belt: ");
		lblSelectBelt.setBounds(40, 175, 100, 25);
		panel.add(lblSelectBelt);

		JComboBox<ItemComboItem> cbBelt = new JComboBox<ItemComboItem>(
				new ComboItemListGenerator(ItemType.BELT).generateList());
		cbBelt.setBounds(170, 175, 120, 25);
		panel.add(cbBelt);

		JLabel lblSelectBoots = new JLabel("Boots: ");
		lblSelectBoots.setBounds(40, 200, 100, 25);
		panel.add(lblSelectBoots);

		JComboBox<ItemComboItem> cbBoots = new JComboBox<ItemComboItem>(
				new ComboItemListGenerator(ItemType.BOOTS).generateList());
		cbBoots.setBounds(170, 200, 120, 25);
		panel.add(cbBoots);

		JLabel lblSelectWeapon = new JLabel("Weapon: ");
		lblSelectWeapon.setBounds(40, 225, 100, 25);
		panel.add(lblSelectWeapon);

		JComboBox<ItemComboItem> cbWeapon = new JComboBox<ItemComboItem>(
				new ComboItemListGenerator(ItemType.WEAPON).generateList());
		cbWeapon.setBounds(170, 225, 120, 25);
		panel.add(cbWeapon);

		// Items

		ArrayList<JComboBox<ItemComboItem>> backpack = new ArrayList<JComboBox<ItemComboItem>>();
		for (int i = 0; i < 10; ++i) {
			JLabel lblSelectItem1 = new JLabel("Item " + i + ": ");
			lblSelectItem1.setBounds(40, 250 + i * 25, 100, 25);
			panel.add(lblSelectItem1);

			JComboBox<ItemComboItem> combobox = new JComboBox<ItemComboItem>(
					new ComboItemListGenerator(null).generateList());
			combobox.setBounds(170, 250 + i * 25, 120, 25);
			panel.add(combobox);

			backpack.add(combobox);
		}

		JButton btnSave = new JButton("Save");
		btnSave.setBounds(40, 525, 100, 25);
		panel.add(btnSave);
		
		if (E_CharacterEditorMode.Open == characterEditorMode) {
			Character character1 = characters.get(cbCharacterName.getSelectedIndex());
			cbHelmet.setSelectedItem(character1.getHelmet());
			cbArmor.setSelectedItem(character1.getarmor());
			cbBoots.setSelectedItem(character1.getBoots());
			cbBelt.setSelectedItem(character1.getBelt());
			cbRing.setSelectedItem(character1.getRing());
			cbShield.setSelectedItem(character1.getShield());
			for(int i = 0; i < 10; ++i){
				backpack.get(i).setSelectedItem(character1.getItemIntoBackpack().get(i));
			}
			characterName = character1.getName();
		}

		
		cbCharacterName.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				System.out.println(getCharacter());
				cbHelmet.setSelectedItem(getCharacter().getHelmet());
				cbArmor.setSelectedItem(getCharacter().getarmor());
				cbBoots.setSelectedItem(getCharacter().getBoots());
				cbBelt.setSelectedItem(getCharacter().getBelt());
				cbRing.setSelectedItem(getCharacter().getRing());
				cbShield.setSelectedItem(getCharacter().getShield());
				for(int i = 0; i < 10; ++i){
					System.out.println(getCharacter().getItemIntoBackpack().get(i));
					backpack.get(i).setSelectedItem(getCharacter().getItemIntoBackpack().get(i));
				}
				characterName = getCharacter().getName();
			}
			
			private Character getCharacter() {
				return characters.get(cbCharacterName.getSelectedIndex());
			}
		});

		btnSave.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				String msg = "";
				if (E_CharacterEditorMode.Create == characterEditorMode) {
					characterName = txtCharacterName.getText();
				}

				System.out.println(((ItemComboItem)cbRing.getSelectedItem()).getValue());
				if (!characterName.equals("")) {
					character.setName(characterName);
					character.setHelmet(getItemFrom(cbHelmet));
					character.setarmor(getItemFrom(cbArmor));
					character.setBoots(getItemFrom(cbBoots));
					character.setBelt(getItemFrom(cbBelt));
					character.setRing(getItemFrom(cbRing));
					character.setShield(getItemFrom(cbShield));
					for (JComboBox<ItemComboItem> backpackItem : backpack){
						character.addItemIntoBackpack(getItemFrom(backpackItem));
					}
					System.out.println(character);
					if (E_CharacterEditorMode.Create == characterEditorMode){
						msg = new CharacterIO().saveCharacter(character);
					} else {
						characters.set(cbCharacterName.getSelectedIndex(), character);
						msg = new CharacterIO().saveCharacters(characters);
					}
					if (msg.contains(GameStatics.STATUS_SUCCESS)) {
					JOptionPane.showMessageDialog(null,
							String.format(GameStatics.MSG_CHARACTER_FILE_LOADED_SAVED, "saved"));
						if (E_CharacterEditorMode.Create == characterEditorMode) {
							txtCharacterName.setText("");
						} 
					} else if(msg.contains(GameStatics.STATUS_EXIST)) {
					JOptionPane.showMessageDialog(null,
							String.format(GameStatics.MSG_DUPLICATE_NAME, "Character name"));
					} else {
						System.out.println(msg);
						JOptionPane.showMessageDialog(null, msg);
					}
				}
			}
			
			private Item getItemFrom(JComboBox<ItemComboItem> comboBox){
				if (comboBox.getSelectedItem() != null){
					return ((ItemComboItem)comboBox.getSelectedItem()).getValue();
				} else {
					return null;
				}
			}
		});
		
		return panel;
	}

	/**
	 * This method create the JPanel for the Chest Window and return JPanel
	 * which set Content Pane for the frame
	 * 
	 * @param new_mapModel
	 * @return JPanel
	 */
	public JPanel getMapChestGridPanel(Map new_mapModel, ArrayList<Item> items, JFrame frame) {
		JPanel panel = new JPanel();
		panel.setLayout(null);
		int index = 0;

		JLabel lblItemName = new JLabel("Item Name: ");
		lblItemName.setBounds(40, 30, 80, 25);
		panel.add(lblItemName);

		JComboBox<String> cbItemName = new JComboBox<String>();
		cbItemName.setBounds(130, 30, 120, 25);
		cbItemName.setEditable(true);
		for (Item i : items) {
			if (i != null) {
				cbItemName.addItem(i.getName());
				cbItemName.setName("" + index);
				index++;
			}
		}

		panel.add(cbItemName);

		List mapList = new List();
		mapList.setBounds(40, 75, 150, 150);
		panel.add(mapList);

		JButton btnAddItem = new JButton("Select Item");
		btnAddItem.setBounds(40, 245, 100, 25);
		panel.add(btnAddItem);

		JButton btnRemoveItem = new JButton("Remove Item");
		btnRemoveItem.setBounds(150, 245, 120, 25);
		panel.add(btnRemoveItem);
		
		if(new_mapModel.mapSelectedItem != null) {
			for(Item i : new_mapModel.mapSelectedItem) {
				mapList.add(i.getName());
			}
		}

		btnRemoveItem.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				if (cbItemName.getSelectedItem() != null) {
					if (items.get(cbItemName.getSelectedIndex()) == null)
						System.out.println("Null");
					else {
						mapList.remove((String) cbItemName.getSelectedItem());
						new_mapModel.mapSelectedItem.remove(items.get(cbItemName.getSelectedIndex()));

						if (new_mapModel.mapSelectedItem.size() > 0)
							new_mapModel.isChestDone = true;
						else
							new_mapModel.isChestDone = false;
					}
				}
			}
		});

		JButton btnSave = new JButton("Save");
		btnSave.setBounds(40, 290, 80, 25);
		panel.add(btnSave);

		btnSave.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				if (new_mapModel.mapSelectedItem.size() > 0) {
					JOptionPane.showMessageDialog(null, "Items has been added to chest.");
					frame.dispose();
				}

				else {
					JOptionPane.showMessageDialog(null, "Please select items.");
				}
			}
		});

		btnAddItem.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				if (cbItemName.getSelectedItem() != null) {
					mapList.add((String) cbItemName.getSelectedItem());
					if (items.get(cbItemName.getSelectedIndex()) == null)
						System.out.println("Null");
					else {
						Item i = items.get(cbItemName.getSelectedIndex());
						new_mapModel.isChestDone = true;
						System.out.println(cbItemName.getSelectedIndex() + " " + items.get(cbItemName.getSelectedIndex()));
						new_mapModel.mapSelectedItem.add(items.get(cbItemName.getSelectedIndex()));
					}
				}
			}
		});
		


		return panel;
	}
}
