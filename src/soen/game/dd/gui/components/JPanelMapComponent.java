package soen.game.dd.gui.components;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.Image;
import java.awt.Point;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

import soen.game.dd.fileio.CharacterIO;
import soen.game.dd.fileio.ItemIO;
import soen.game.dd.gui.system.ChestItemWindow;
import soen.game.dd.models.Item;
import soen.game.dd.models.Map;
import soen.game.dd.models.NPCType;
import soen.game.dd.statics.content.GameStatics;
import soen.game.dd.statics.content.GameEnums.E_MapEditorMode;
import soen.game.dd.models.Campaign;
import soen.game.dd.models.Character;

public class JPanelMapComponent {
	
	private E_MapEditorMode mapEditorMode;
	// 2D Array of JButton
	private JButton mapButtonsGrid2DArray[][];
	private JButton jButtonEntry;
	private JButton jButtonExit;
	private JButton jButtonCharacter;
	private JButton jButtonOpponent;
	private JButton jButtonChest;
	
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
		return createPanel(new_mapModel, new_mode, maps, index);
	}
	
	public JPanel createPanel(Map new_mapModel, E_MapEditorMode new_mode, ArrayList<Map> maps, int index) {
		mapEditorMode = new_mode;
		JPanel panel = new JPanel();
		GridLayout gridLayout;
		ImageIcon imgIcon = new ImageIcon("Character.png");
		Image img = imgIcon.getImage();

		// panel = new JPanel();
		// When Create Mode Initialize the mapGridSelection to new
		if (E_MapEditorMode.Create == mapEditorMode) {
			new_mapModel.mapGridSelection = new int[new_mapModel.getMapHeight()][new_mapModel.getMapWidth()];
			gridLayout = new GridLayout(new_mapModel.getMapHeight(), new_mapModel.getMapWidth(), 3, 3);
			panel.setLayout(gridLayout);
		}
		
		else if (E_MapEditorMode.Play == mapEditorMode) {
			if (index < maps.size())
			{
				new_mapModel = maps.get(index);
				gridLayout = new GridLayout(new_mapModel.getMapHeight(), new_mapModel.getMapWidth(), 3, 3);
				panel.setLayout(gridLayout);
			}
		}

		else {
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

				else if (E_MapEditorMode.Open == mapEditorMode || E_MapEditorMode.Play == mapEditorMode) {

					if (new_mapModel.mapGridSelection[i][j] == GameStatics.MAP_PATH_POINT) {
						mapButtonsGrid2DArray[i][j].setBackground(Color.green);
					} else if (new_mapModel.mapGridSelection[i][j] == GameStatics.MAP_ENTRY_POINT) {
						if (E_MapEditorMode.Open == mapEditorMode) {
							mapButtonsGrid2DArray[i][j].setBackground(Color.red);
							mapButtonsGrid2DArray[i][j].setText("Entry");
						}
						else {
							imgIcon = new ImageIcon(img.getScaledInstance(GameStatics.CHILD_POPUP_WINDOW_WIDTH/new_mapModel.getMapWidth() - 5, GameStatics.CHILD_POPUP_WINDOW_HEIGHT/new_mapModel.getMapHeight() - 5, java.awt.Image.SCALE_SMOOTH));
							mapButtonsGrid2DArray[i][j].setBackground(Color.red);
							mapButtonsGrid2DArray[i][j].setIcon(imgIcon);
						}
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
					
					if (E_MapEditorMode.Open == mapEditorMode) {
						// Click event
						addButtonClickEvents(mapButtonsGrid2DArray[i][j], new_mapModel, new_mode, maps, index);
						// Right Click Event
						addMouseClickOnButtonEvents(mapButtonsGrid2DArray[i][j], new_mapModel, new_mode, maps, index);
					}
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
					/*new_mapModel.mapGridSelection[_i][_j] = GameStatics.MAP_WALL_POINT;
					btn.setBackground(Color.gray);
					btn.setText("");
					new_mapModel.isCharacterDone = false;
					jButtonCharacter = null;*/
					if (JOptionPane.showConfirmDialog(null, "Do you want to change character ?", "WARNING",
							JOptionPane.YES_NO_OPTION) == JOptionPane.YES_OPTION) {

						ArrayList<Character> characters = new ArrayList<Character>();
						characters = new CharacterIO().loadCharacters();
						int index = 0;
						int selectedIndex = 0;
						String[] characterNames = new String[characters.size()];
						String selectedCharacter = "";
						
						for (Character c : new_mapModel.mapCharacters) {
							if (c.getNPCType() == NPCType.FRINDLY) {
								selectedCharacter = c.getName();
								break;
							}
							selectedIndex++;
						}

						for (Character c : characters) {
							characterNames[index] = c.getName();
							index++;
						}

						if (characters != null) {
							String characterName = (String) JOptionPane.showInputDialog(null, "Selected Character : "+selectedCharacter+"\n\nSelect Character :\n",
									"Characters Dialog", JOptionPane.PLAIN_MESSAGE, null, characterNames, "select");
							
							if (characterName != null) {
								for (Character c : characters) {
									if(c.getName().equals(characterName)) {
										c.setNPCType(NPCType.FRINDLY);
										new_mapModel.mapCharacters.remove(selectedIndex);
										new_mapModel.mapCharacters.add(c);
										break;
									}
								}
							}
						}

						else {
							JOptionPane.showMessageDialog(null,
									"No Characters are created, Please create the character first");
						}
					}
				}
				// If the last point was an Opponent Point
				else if (new_mapModel.mapGridSelection[_i][_j] == GameStatics.MAP_OPPONENT_POINT) {
					/*new_mapModel.mapGridSelection[_i][_j] = GameStatics.MAP_WALL_POINT;
					btn.setBackground(Color.gray);
					btn.setText("");
					new_mapModel.isOpponentDone = false;
					jButtonOpponent = null;*/
					if (JOptionPane.showConfirmDialog(null, "Do you want to change character ?", "WARNING",
							JOptionPane.YES_NO_OPTION) == JOptionPane.YES_OPTION) {

						ArrayList<Character> characters = new ArrayList<Character>();
						characters = new CharacterIO().loadCharacters();
						int index = 0;
						int selectedIndex = 0;
						String[] characterNames = new String[characters.size()];
						String selectedCharacter = "";
						
						for (Character c : new_mapModel.mapCharacters) {
							if (c.getNPCType() == NPCType.HOSTILE) {
								selectedCharacter = c.getName();
								break;
							}
							selectedIndex++;
						}

						for (Character c : characters) {
							characterNames[index] = c.getName();
							index++;
						}

						if (characters != null) {
							String characterName = (String) JOptionPane.showInputDialog(null, "Selected Character : "+selectedCharacter+"\n\nSelect Character :\n",
									"Characters Dialog", JOptionPane.PLAIN_MESSAGE, null, characterNames, "select");
							
							if (characterName != null) {
								for (Character c : characters) {
									if(c.getName().equals(characterName)) {
										c.setNPCType(NPCType.HOSTILE);
										new_mapModel.mapCharacters.remove(selectedIndex);
										new_mapModel.mapCharacters.add(c);
										break;
									}
								}
							}
						}

						else {
							JOptionPane.showMessageDialog(null,
									"No Characters are created, Please create the character first");
						}
					}
				}
				// If the last point was an Chest Point
				else if (new_mapModel.mapGridSelection[_i][_j] == GameStatics.MAP_CHEST_POINT) {
					if (JOptionPane.showConfirmDialog(null, "Do you want to edit items in the chest", "WARNING",
							JOptionPane.YES_NO_OPTION) == JOptionPane.NO_OPTION) {
						/*new_mapModel.mapGridSelection[_i][_j] = GameStatics.MAP_WALL_POINT;
						btn.setBackground(Color.gray);
						btn.setText("");
						new_mapModel.isChestDone = false;
						new_mapModel.setChestPoint(null);
						new_mapModel.mapSelectedItem.clear();
						jButtonChest = null;*/
					} else {
						ArrayList<Item> items = new ArrayList<Item>();

						items = new ItemIO().loadItems();
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
						if (JOptionPane.showConfirmDialog(null, "Do you want to remove hostile characer ?", "WARNING",
								JOptionPane.YES_NO_OPTION) == JOptionPane.YES_OPTION) {
							int index = 0;
							new_mapModel.mapGridSelection[_i][_j] = GameStatics.MAP_WALL_POINT;
							btn.setBackground(Color.gray);
							btn.setText("");
							jButtonCharacter = null;
							new_mapModel.isCharacterDone = false;
							new_mapModel.setCharacterPoint(null);
							for(Character c : new_mapModel.mapCharacters) {
								if (c.getNPCType() == NPCType.FRINDLY) {
									new_mapModel.mapCharacters.remove(index);
								}
								index++;
							}
						}
					}
					// If the last point was an Opponent Button
					else if (new_mapModel.mapGridSelection[_i][_j] == GameStatics.MAP_OPPONENT_POINT) {
						if (JOptionPane.showConfirmDialog(null, "Do you want to remove hostile characer ?", "WARNING",
								JOptionPane.YES_NO_OPTION) == JOptionPane.YES_OPTION) {
							int index = 0;
							new_mapModel.mapGridSelection[_i][_j] = GameStatics.MAP_WALL_POINT;
							btn.setBackground(Color.gray);
							btn.setText("");
							jButtonOpponent = null;
							new_mapModel.isOpponentDone = false;
							new_mapModel.setOpponentPoint(null);
							for(Character c : new_mapModel.mapCharacters) {
								if (c.getNPCType() == NPCType.HOSTILE) {
									new_mapModel.mapCharacters.remove(index);
								}
								index++;
							}
						}
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

								items = new ItemIO().loadItems();
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
								
								int option = JOptionPane.showConfirmDialog(null, GameStatics.MAP_CHARACTER_CONFIRM_DIALOG,
										GameStatics.TITLE_ADD_CHARACTER, JOptionPane.OK_CANCEL_OPTION);

								if (option == JOptionPane.OK_OPTION) {
									ArrayList<Character> characters = new ArrayList<Character>();
									characters = new CharacterIO().loadCharacters();
									int index = 0;
									String[] characterNames = new String[characters.size()];

									for (Character c : characters) {
										characterNames[index] = c.getName();
										index++;
									}

									if (characters != null) {
										String characterName = (String) JOptionPane.showInputDialog(null, "Select Character :\n",
												"Characters Dialog", JOptionPane.PLAIN_MESSAGE, null, characterNames, "select");
										
										if (!characterName.equals("")) {
											for (Character c : characters) {
												if(c.getName().equals(characterName)) {
													c.setNPCType(NPCType.FRINDLY);
													new_mapModel.mapCharacters.add(c);
													break;
												}
											}
										}
									}

									else {
										JOptionPane.showMessageDialog(null,
												"No Characters are created, Please create the character first");
									}
								}
							} else if (!new_mapModel.isOpponentDone) {
								btn.setBackground(Color.BLUE);
								btn.setText("Opponent");
								jButtonOpponent = btn;
								new_mapModel.mapGridSelection[_i][_j] = GameStatics.MAP_OPPONENT_POINT;
								new_mapModel.setOpponentPoint(new Point(_i, _j));
								new_mapModel.isOpponentDone = true;
								int option = JOptionPane.showConfirmDialog(null, GameStatics.MAP_CHARACTER_CONFIRM_DIALOG,
										GameStatics.TITLE_ADD_CHARACTER, JOptionPane.OK_CANCEL_OPTION);

								if (option == JOptionPane.OK_OPTION) {
									ArrayList<Character> characters = new ArrayList<Character>();
									characters = new CharacterIO().loadCharacters();
									int index = 0;
									String[] characterNames = new String[characters.size()];

									for (Character c : characters) {
										characterNames[index] = c.getName();
										index++;
									}

									if (characters != null) {
										String characterName = (String) JOptionPane.showInputDialog(null, "Select Character :\n",
												"Characters Dialog", JOptionPane.PLAIN_MESSAGE, null, characterNames, "select");
										
										if (!characterName.equals("")) {
											for (Character c : characters) {
												if(c.getName().equals(characterName)) {
													c.setNPCType(NPCType.HOSTILE);
													new_mapModel.mapCharacters.add(c);
													break;
												}
											}
										}
									}

									else {
										JOptionPane.showMessageDialog(null,
												"No Characters are created, Please create the character first");
									}
								}
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
	 * This method create Jpanel for play mode
	 * 
	 * @param campaign
	 * @param character
	 * @param new_mode
	 * @return Jpanel
	 */
	public JPanel getMapEditorPlayGridPanel(Campaign campaign, Character character, E_MapEditorMode new_mode) {
		return createPanel(campaign.getCampaignList().get(0), new_mode, new ArrayList<Map>(campaign.getCampaignList()), 0);
	}
}
