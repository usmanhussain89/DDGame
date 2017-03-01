package soen.game.dd.gui.components;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;

import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JTextField;

import soen.game.dd.gui.system.CampaignEditor;
import soen.game.dd.gui.system.ItemEditor;
import soen.game.dd.gui.system.MapEditor;
import soen.game.dd.logic.MapValidation;
import soen.game.dd.models.Campaign;
import soen.game.dd.models.FileWriterReader;
import soen.game.dd.models.Item;
import soen.game.dd.models.Map;
import soen.game.dd.statics.content.GameEnums.E_CampaignEditorMode;
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
							if (!(Character.isDigit(ch) || (ch == KeyEvent.VK_BACK_SPACE)
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
									mapModel, E_MapEditorMode.Create);
						}
					} else {

					}
				} else if (e.getSource().equals(menuItemOpenMap)) {
					JFileChooser fileChooser = new JFileChooserComponent().getJFileChooser(E_JFileChooserMode.MapOpen);
					int result = fileChooser.showOpenDialog(new_jframe);
					if (result == JFileChooser.APPROVE_OPTION) {
						File file = fileChooser.getSelectedFile();
						Map mapModel = null;
						try {
							mapModel = new FileWriterReader().loadMap(file);
						} catch (FileNotFoundException e1) {
							e1.printStackTrace();
						} catch (ClassNotFoundException e1) {
							e1.printStackTrace();
						} catch (IOException e1) {
							e1.printStackTrace();
						}
						if (mapModel != null) {
							new MapEditor(new_jframe, GameStatics.TITLE_MAP_EDITOR,
									GameStatics.CHILD_POPUP_WINDOW_WIDTH, GameStatics.CHILD_POPUP_WINDOW_HEIGHT,
									mapModel, E_MapEditorMode.Open);
						} else {
							JOptionPane.showMessageDialog(null, GameStatics.MSG_UNABLE_TO_OPEN_FILE);
						}
					} else {
						JOptionPane.showMessageDialog(null, GameStatics.MSG_NO_FILE_SELECTED);
					}
				} 
				
				else if (e.getSource().equals(menuItemCreateItem)) {
					new ItemEditor(new_jframe, GameStatics.TITLE_ITEM_EDITOR, GameStatics.CHILD_POPUP_WINDOW_WIDTH,
							GameStatics.CHILD_POPUP_WINDOW_HEIGHT, E_ItemEditorMode.Create);
				}
				
				else if (e.getSource().equals(menuItemOpenItem)){
					
				}
				
				else if (e.getSource().equals(menuItemCreateCampaign)){
					new CampaignEditor(new_jframe, GameStatics.TITLE_CAMPAIGN_EDITOR, GameStatics.CHILD_POPUP_WINDOW_WIDTH,
							GameStatics.CHILD_POPUP_WINDOW_HEIGHT, null, E_CampaignEditorMode.Create);
				}
				
				else if (e.getSource().equals(menuItemOpenCampaign)){

					JFileChooser fileChooser = new JFileChooserComponent().getJFileChooser(E_JFileChooserMode.CampaignOpen);
					int result = fileChooser.showOpenDialog(new_jframe);
					if (result == JFileChooser.APPROVE_OPTION) {
						File file = fileChooser.getSelectedFile();
						Campaign campaignModel = null;
						try {
							campaignModel = new FileWriterReader().loadCampaign(file);
						} catch (FileNotFoundException e1) {
							e1.printStackTrace();
						} catch (ClassNotFoundException e1) {
							e1.printStackTrace();
						} catch (IOException e1) {
							e1.printStackTrace();
						}
						if (campaignModel != null) {
							new CampaignEditor(new_jframe, GameStatics.TITLE_CAMPAIGN_EDITOR, GameStatics.CHILD_POPUP_WINDOW_WIDTH,
									GameStatics.CHILD_POPUP_WINDOW_HEIGHT,campaignModel, E_CampaignEditorMode.Open);					
							
						} else {
							JOptionPane.showMessageDialog(null, GameStatics.MSG_UNABLE_TO_OPEN_FILE);
						}
					} else {
						JOptionPane.showMessageDialog(null, GameStatics.MSG_NO_FILE_SELECTED);
					}
				}
				
				else
				{
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
		menuItemExit.addActionListener(new MenuItemAction());
		return menuBar;
	}

	/**
	 * This method create Menu for Map Editor Window
	 * 
	 * @param new_mapModel
	 * @param new_jframe
	 * @return JMenu for Map Editor
	 */
	public JMenuBar getMapEditorJMenuBar(Map new_mapModel, JFrame new_jframe) {
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
					String MapValidationStatus = (new MapValidation()).mapValidations(new_mapModel);

					if (MapValidationStatus != null) {
						JOptionPane.showMessageDialog(null, MapValidationStatus);
					} else {
						JFileChooser fileChooser = new JFileChooserComponent()
								.getJFileChooser(E_JFileChooserMode.MapSave);
						int result = fileChooser.showSaveDialog(null);
						if (result == JFileChooser.APPROVE_OPTION) {
							File file = fileChooser.getSelectedFile();

							new_mapModel.setMapName(file.getName());
							new_mapModel.setMapRoutPath(GameStatics.MAP_ROUT_PATH);
							new_mapModel.setMapRoutBoundaries(GameStatics.MAP_PATH_BOUNDARY_BUTTONS_NAME);
							new_mapModel.getMapRoutPathList();

							String msg = new FileWriterReader().saveMap(file, new_mapModel);
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
		JMenuItem menuItemSave = new JMenuItem(GameStatics.MENU_ITEM_SAVE);
		menuFile.add(menuItemSave);
		JMenuItem menuItemExit = new JMenuItem(GameStatics.MENU_ITEM_EXIT);
		menuFile.add(menuItemExit);

		class MenuItemAction implements ActionListener {

			@Override
			public void actionPerformed(ActionEvent new_event) {
				System.out.println("hi");
				if (new_event.getSource().equals(menuItemSave)) {
					if (item.getName() != null){
						if (item.isValid()){

						} else {
							JOptionPane.showMessageDialog(null, "Error in saving the Item. Please verify all fields");
						}
					}
				}
			}
		}
		
		menuItemSave.addActionListener(new MenuItemAction());
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
	public JMenuBar getCampaignEditorJMenuBar(Campaign campaign, JFrame new_jframe) {
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
					if (campaign != null){
						if(campaign.getCampaignList().size() > 0)
						{
							JFileChooser fileChooser = new JFileChooserComponent()
									.getJFileChooser(E_JFileChooserMode.CampaignSave);
							int result = fileChooser.showSaveDialog(null);
							if (result == JFileChooser.APPROVE_OPTION) {
								File file = fileChooser.getSelectedFile();

								String msg = new FileWriterReader().saveCampaign(file, campaign);
								if (msg.contains(GameStatics.STATUS_SUCCESS)) {
									JOptionPane.showMessageDialog(null,
											String.format(GameStatics.MSG_CAMPAIGN_FILE_LOADED_SAVED, "saved"));
									closeFrame(new_jframe);
								} else {
									JOptionPane.showMessageDialog(null, msg);
								}
							}
						}
						
						else
						{
							JOptionPane.showMessageDialog(null, GameStatics.MSG_SELECT_MORE_MAP);
						}
					}
					else
					{
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
}
