package soen.game.dd.gui.components;

import java.awt.Color;
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
import soen.game.dd.statics.content.GameEnums.E_CampaignEditorMode;
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
								new ChestItemWindow();
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
	public JPanel getItemEditorGridPanel(Item item, Dimension new_parentDimension, E_ItemEditorMode new_mode) {
		
		
		itemEditorMode = new_mode;
		JPanel panel;
		GridLayout flowLayout;

		panel = new JPanel();
		flowLayout = new GridLayout(4, 2);
		panel.setLayout(flowLayout);

		JLabel lblItemName = new JLabel();
		lblItemName.setText("Item Name: ");

		JTextField txtItemName = new JTextField(20);
		txtItemName.getDocument().addDocumentListener(new DocumentListener(){

			@Override
			public void insertUpdate(DocumentEvent e) {
				item.setName(txtItemName.getText());
			}

			@Override
			public void removeUpdate(DocumentEvent e) {
				item.setName(txtItemName.getText());
			}

			@Override
			public void changedUpdate(DocumentEvent e) {
				item.setName(txtItemName.getText());
			}
		
		});

		JLabel lblItemType = new JLabel();
		lblItemType.setText("Item Type: ");

		JComboBox txtComboBox = new JComboBox(ItemType.values());
		txtComboBox.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e){
				item.setItemType((ItemType) txtComboBox.getSelectedItem());
			}
		});

		JLabel lblCharacterAttr = new JLabel();
		lblCharacterAttr.setText("Character Attribute: ");

		JComboBox cbCharacterAttr = new JComboBox(CharacterAttribute.values());
		cbCharacterAttr.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e){
				item.setCharacterAttribute((CharacterAttribute) cbCharacterAttr.getSelectedItem());
			}
		});

		JLabel lblBonusAmount = new JLabel();
		lblBonusAmount.setText("Bonus Amount: ");

		Integer[] bonuses = new Integer[] { 1, 2, 3, 4, 5 };
		JComboBox cbBonusAmount = new JComboBox(bonuses);
		cbBonusAmount.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e){
				item.setBonusAmount((Integer) cbBonusAmount.getSelectedItem());
			}
		});

		panel.add(lblItemName);
		panel.add(txtItemName);
		panel.add(lblItemType);
		panel.add(txtComboBox);
		panel.add(lblCharacterAttr);
		panel.add(cbCharacterAttr);
		panel.add(lblBonusAmount);
		panel.add(cbBonusAmount);

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
}
