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

	/**
	 * This method create the JPanel for the Map Editor and return JPanel which
	 * set Content Pane for the frame
	 * 
	 * @param new_mapModel
	 * @param new_parentDimension
	 * @param new_mode
	 * @return JPanel
	 */
	public JPanel getMapEditorGridPanel(Map new_mapModel, Dimension new_parentDimension, E_MapEditorMode new_mode) {
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
					addButtonClickEvents(mapButtonsGrid2DArray[i][j], new_mapModel);
					// Right Click Event
					addMouseClickOnButtonEvents(mapButtonsGrid2DArray[i][j], new_mapModel);
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
					addButtonClickEvents(mapButtonsGrid2DArray[i][j], new_mapModel);
					// Right Click Event
					addMouseClickOnButtonEvents(mapButtonsGrid2DArray[i][j], new_mapModel);
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
	private void addButtonClickEvents(JButton new_button, Map new_mapModel) {
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
					new_mapModel.mapGridSelection[_i][_j] = GameStatics.MAP_WALL_POINT;
					btn.setBackground(Color.gray);
					btn.setText("");
					new_mapModel.isChestDone = false;
					jButtonChest = null;
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
	private void addMouseClickOnButtonEvents(JButton new_button, Map new_mapModel) {
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
						new_mapModel.mapGridSelection[_i][_j] = GameStatics.MAP_WALL_POINT;
						btn.setBackground(Color.gray);
						btn.setText("");
						jButtonChest = null;
						new_mapModel.isChestDone = false;
						new_mapModel.setChestPoint(null);
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
								
								else
								{
									JOptionPane.showMessageDialog(null, "No Items are created, Please create the items");
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
	public JPanel getItemEditorGridPanel(Item item, Dimension new_parentDimension, E_ItemEditorMode new_mode, ArrayList<Item> items) {
		
		
		itemEditorMode = new_mode;
		JPanel panel;
		//JTextField txtItemName = null;

		panel = new JPanel();;
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
		if(E_ItemEditorMode.Open == new_mode)
		{
			txtItemName.setVisible(false);
			cbItemName.setVisible(true);
			int index = 0;
			
			for(Item i : items)
			{
				if(i != null)
				{
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
		lblItemType.setBounds(40, 75, 80, 25);;
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
		
		JButton btnAddItem = new JButton("Add New Item");
		btnAddItem.setBounds(40, 230, 120, 25);
		panel.add(btnAddItem);
		
		if(E_ItemEditorMode.Open == new_mode)
		{
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
				for(CharacterAttribute c : ((ItemType) cbItemType.getSelectedItem()).getAllowedAttributes())
				{
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
				if(E_ItemEditorMode.Create == new_mode)
				{
					itemName = txtItemName.getText();
				}
				
				if(!itemName.equals(""))
				{
					item.setName(itemName);
					item.setItemType((ItemType)cbItemType.getSelectedItem());
					item.setCharacterAttribute((CharacterAttribute)cbCharacterAttr.getSelectedItem());
					item.setBonusAmount((Integer)cbBonusAmount.getSelectedItem());
					if (item.isValid()){
						String msg = "";
						if(E_ItemEditorMode.Open == new_mode)
						{
							items.set(cbItemName.getSelectedIndex(), item);
							msg = new FileWriterReader().saveItems(items);
						}
						
						else 
						{
							msg = new FileWriterReader().saveItem(item);
						}
						if (msg.contains(GameStatics.STATUS_SUCCESS)) {
							JOptionPane.showMessageDialog(null,
							String.format(GameStatics.MSG_ITEM_FILE_LOADED_SAVED, "saved"));
							itemName = "";
							} 
						else {
							JOptionPane.showMessageDialog(null, msg);
						}
					}
				}
			}
		});

		return panel;
	}
	
	/**
	 * This method create the JPanel for the Campaign Editor and return JPanel which
	 * set Content Pane for the frame
	 * 
	 * @param new_mode
	 * @return JPanel
	 */
	public JPanel getCampaignEditorGridPanel(Campaign campaign, E_CampaignEditorMode new_mode, JFrame frame) {
		
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
		
		JLabel lblSelectMap = new JLabel("Select Map: ");
		lblSelectMap.setBounds(40, 75, 100, 25);
		panel.add(lblSelectMap);
		
		JTextField txtBrowseFile = new JTextField(30);
		txtBrowseFile.setBounds(160, 75, 150, 25);
		panel.add(txtBrowseFile);
		
		JButton btnBrowseFile = new JButton("Browse");
		btnBrowseFile.setBounds(320, 75, 80, 25);
		panel.add(btnBrowseFile);
		
		JButton btnAddMap = new JButton("Add Map");
		btnAddMap.setBounds(420, 75, 90, 25);
		btnAddMap.setVisible(false);
		panel.add(btnAddMap);
		
		JLabel lblSelectedMap = new JLabel("Selected Map: ");
		lblSelectedMap.setBounds(40, 120, 100, 25);
		panel.add(lblSelectedMap);
		
		List mapList = new List();
		mapList.setBounds(160,120,150,150);
		panel.add(mapList);
		
		JButton btnRemoveMap = new JButton("Remove Map");
		btnRemoveMap.setBounds(160,290,130,25);
		btnRemoveMap.setVisible(true);
		panel.add(btnRemoveMap);
		
		class PanelAction implements ActionListener
		{
			Map new_mapModel = null;
			@Override
			public void actionPerformed(ActionEvent e) {
				
				if (e.getSource().equals(btnBrowseFile)) {
					JFileChooser fileChooser = new JFileChooserComponent().getJFileChooser(E_JFileChooserMode.MapOpen);
					int result = fileChooser.showOpenDialog(frame);
					if (result == JFileChooser.APPROVE_OPTION) {
						File file = fileChooser.getSelectedFile();
						try {
							new_mapModel = new FileWriterReader().loadMap(file);
						} catch (FileNotFoundException e1) {
							e1.printStackTrace();
						} catch (ClassNotFoundException e1) {
							e1.printStackTrace();
						} catch (IOException e1) {
							e1.printStackTrace();
						}
						if (new_mapModel != null) {
							mapModel = new_mapModel;
							txtBrowseFile.setText(new_mapModel.getMapName());
							btnAddMap.setVisible(true);
						} else {
							btnAddMap.setVisible(false);
							JOptionPane.showMessageDialog(null, GameStatics.MSG_UNABLE_TO_OPEN_FILE);
						}
					} else {
						JOptionPane.showMessageDialog(null, GameStatics.MSG_NO_FILE_SELECTED);
					}
				}
				
				else if (e.getSource().equals(btnAddMap)) {
					if(mapModel != null){
						campaign.setCampaignList(mapModel);
						campaign.setCampaignName(txtCampaignName.getText());
						mapList.add(mapModel.getMapName());
						btnAddMap.setVisible(false);
						txtBrowseFile.setText("");
					}
				}
				
				else if (e.getSource().equals(btnRemoveMap)) {
					if(mapList.getSelectedIndex() >= 0)
					{
						int index = mapList.getSelectedIndex();
						mapList.remove(index);
						campaign.removeMapFromList(index);
					}
				}
			}
			
		}
		btnBrowseFile.addActionListener(new PanelAction());
		btnAddMap.addActionListener(new PanelAction());
		btnRemoveMap.addActionListener(new PanelAction());
		
		if(E_CampaignEditorMode.Open == new_mode)
		{
			txtCampaignName.setText(campaign.getCampaignName());
			
			for (Map map : campaign.getCampaignList())
			{
				mapList.add(map.getMapName());
			}
		}
		
		return panel;
	}

	public Container getCharacterEditorGridPanel(Character pCharacter,
			E_CharacterEditorMode characterEditorMode, JFrame frame) {
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

		    public String getLabel() {
		        return this.label;
		    }

		    @Override
		    public String toString() {
		        return label;
		    }
		}
		
		class ComboItemListGenerator{
			private ItemType itemType;
			
			public ComboItemListGenerator(ItemType type){
				this.itemType = type;
			}
			
			public ItemComboItem[] generateList(){
				ArrayList<ItemComboItem> combo_items = new ArrayList<ItemComboItem>();
				for(Item i : items){
					if(i.getItemType() == itemType){
						combo_items.add(new ItemComboItem(i));
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
		
		JLabel lblSelectHelmet = new JLabel("Helmet: ");
		lblSelectHelmet.setBounds(40, 75, 100, 25);
		panel.add(lblSelectHelmet);
		
		JComboBox<ItemComboItem> cbHelmet = new JComboBox<ItemComboItem>(new ComboItemListGenerator(ItemType.HELMET).generateList());
		cbHelmet.setBounds(170, 75, 120, 25);
		panel.add(cbHelmet);
		
		
		JLabel lblSelectArmor = new JLabel("Armor: ");
		lblSelectArmor.setBounds(40, 100, 100, 25);
		panel.add(lblSelectArmor);
		
		JComboBox<ItemComboItem> cbArmor = new JComboBox<ItemComboItem>(new ComboItemListGenerator(ItemType.ARMOR).generateList());
		cbArmor.setBounds(170, 100, 120, 25);
		panel.add(cbArmor);
		
		
		JLabel lblSelectShield = new JLabel("Shield: ");
		lblSelectShield.setBounds(40, 125, 100, 25);
		panel.add(lblSelectShield);
		
		JComboBox<ItemComboItem> cbShield = new JComboBox<ItemComboItem>(new ComboItemListGenerator(ItemType.SHIELD).generateList());
		cbShield.setBounds(170, 125, 120, 25);
		panel.add(cbShield);
		
		
		JLabel lblSelectRing = new JLabel("Ring: ");
		lblSelectRing.setBounds(40, 150, 100, 25);
		panel.add(lblSelectRing);
		
		JComboBox<ItemComboItem> cbRing = new JComboBox<ItemComboItem>(new ComboItemListGenerator(ItemType.RING).generateList());
		cbRing.setBounds(170, 150, 120, 25);
		panel.add(cbRing);
		
		
		JLabel lblSelectBelt = new JLabel("Belt: ");
		lblSelectBelt.setBounds(40, 175, 100, 25);
		panel.add(lblSelectBelt);
		
		JComboBox<ItemComboItem> cbBelt = new JComboBox<ItemComboItem>(new ComboItemListGenerator(ItemType.BELT).generateList());
		cbBelt.setBounds(170, 175, 120, 25);
		panel.add(cbBelt);
		
		
		JLabel lblSelectBoots = new JLabel("Boots: ");
		lblSelectBoots.setBounds(40, 200, 100, 25);
		panel.add(lblSelectBoots);
		
		JComboBox<ItemComboItem> cbBoots = new JComboBox<ItemComboItem>(new ComboItemListGenerator(ItemType.BOOTS).generateList());
		cbBoots.setBounds(170, 200, 120, 25);
		panel.add(cbBoots);
		
		
		JLabel lblSelectWeapon = new JLabel("Weapon: ");
		lblSelectWeapon.setBounds(40, 225, 100, 25);
		panel.add(lblSelectWeapon);
		
		JComboBox<ItemComboItem> cbWeapon = new JComboBox<ItemComboItem>(new ComboItemListGenerator(ItemType.WEAPON).generateList());
		cbWeapon.setBounds(170, 225, 120, 25);
		panel.add(cbWeapon);
		return panel;
	}
	
	/**
	 * This method create the JPanel for the Chest Window and return JPanel which
	 * set Content Pane for the frame
	 * 
	 * @param new_mapModel
	 * @return JPanel
	 */
	public JPanel getMapChestGridPanel(Map new_mapModel, ArrayList<Item> items) {
		JPanel panel = new JPanel();
		//ArrayList<Item> items = new ArrayList<Item>();
		int index = 0;
		
		//items = new FileWriterReader().loadItems();
		
		//if(items != null) {
			JLabel lblItemName = new JLabel("Item Name: ");
			lblItemName.setBounds(40, 30, 80, 25);
			panel.add(lblItemName);

			JComboBox<String> cbItemName = new JComboBox<String>();
			cbItemName.setBounds(170, 30, 120, 25);
			cbItemName.setEditable(true);
			for(Item i : items)
			{
				if(i != null)
				{
						cbItemName.addItem(i.getName());
						cbItemName.setName("" + index);
						index++;
				}
			}
				
			panel.add(cbItemName);
			
			List mapList = new List();
			mapList.setBounds(160,75,150,150);
			panel.add(mapList);
			
			JButton btnAddItem = new JButton("Add Item");
			btnAddItem.setBounds(40, 245, 100, 25);
			panel.add(btnAddItem);
			
			btnAddItem.addActionListener(new ActionListener() {
				
				@Override
				public void actionPerformed(ActionEvent e) {
					if(cbItemName.getSelectedItem() != null)
					{
						mapList.add((String)cbItemName.getSelectedItem());
						if (items.get(cbItemName.getSelectedIndex()) == null)
							System.out.println("Null");
						new_mapModel.mapSelectedItem.add(items.get(cbItemName.getSelectedIndex()));
					}
				}
			});
		/*}
		else
		{
			JOptionPane.showMessageDialog(null, "No Items are created, Please create the items");
		}*/
		
		return panel;
	}
}
