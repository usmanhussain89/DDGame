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

import soen.game.dd.logic.MapValidation;
import soen.game.dd.gui.system.MapEditor;
import soen.game.dd.models.FileWriterReader;
import soen.game.dd.models.Map;
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
	 * @param new_jframe as JFrame
	 * @return JMenuBar
	 */
	public JMenuBar getGameJMenuBar(JFrame new_jframe){
		
		JMenuBar menuBar = new JMenuBar();
		
		JMenu menuFile = new JMenu(GameStatics.MENU_FILE);
		menuBar.add(menuFile);
		
		JMenuItem menuItemCreateMap = new JMenuItem(GameStatics.MENU_ITEM_CREATE_MAP);
		menuFile.add(menuItemCreateMap);
		JMenuItem menuItemOpenMap = new JMenuItem(GameStatics.MENU_ITEM_OPEN_MAP);
		menuFile.add(menuItemOpenMap);
		JMenuItem menuItemCreateCharacter = new JMenuItem(GameStatics.MENU_ITEM_CREATE_CHARACTER);
		menuFile.add(menuItemCreateCharacter);
		JMenuItem menuItemLoadCharacter = new JMenuItem(GameStatics.MENU_ITEM_LOAD_CHARACTER);
		menuFile.add(menuItemLoadCharacter);
		
		/**
		 * This class handle the menu Item on click events
		 */
		
		class MenuItemAction implements ActionListener{

			@Override
			public void actionPerformed(ActionEvent e) {
				//If the Menu option 'Create Map' is clicked
				if(e.getSource().equals(menuItemCreateMap))
				{
					JTextField txtX = new JTextField();
					JTextField txtY = new JTextField();
					
					txtX.addKeyListener(new KeyAdapter() {
						public void KeyTyped(KeyEvent e){
							char ch = e.getKeyChar();
							if (!(Character.isDigit(ch) || (ch == KeyEvent.VK_BACK_SPACE)
									|| (ch == KeyEvent.VK_DELETE))) {
								e.consume();
							}
							if (txtX.getText().length() > 2)
								e.consume();
						}
					});
					
					Object[] message = {"Size of X:", txtX, "Size of Y:", txtY};
					
					int option = JOptionPane.showConfirmDialog(null, message, GameStatics.TITLE_MSG_SET_SIZE_OF_MAP, JOptionPane.OK_CANCEL_OPTION);
					
					if(option == JOptionPane.OK_OPTION){
						String x = txtX.getText().trim();
						String y = txtY.getText().trim();
						
						if(x.length() == 0){
							JOptionPane.showMessageDialog(null, String.format(GameStatics.MSG_X_MAY_NOT_EMPTY, "X"));
						}
						
						else if(y.length() == 0){
							JOptionPane.showMessageDialog(null, String.format(GameStatics.MSG_X_MAY_NOT_EMPTY, "Y"));
						}
						
						else if (Integer.parseInt(x) < 1 || Integer.parseInt(x) > 30){
							JOptionPane.showMessageDialog(null, String.format(GameStatics.MSG_X_MUST_BE_IN_RANGE, "X"));
						}
						
						else if (Integer.parseInt(y) < 1 || Integer.parseInt(y) > 30){
							JOptionPane.showMessageDialog(null, String.format(GameStatics.MSG_X_MUST_BE_IN_RANGE, "Y"));
						}
						
						else {
							Map mapModel = new Map();
							mapModel.setMapWidth(Integer.parseInt(x));
							mapModel.setMapHeight(Integer.parseInt(y));
							new MapEditor(new_jframe, GameStatics.TITLE_MAP_EDITOR,
									GameStatics.CHILD_POPUP_WINDOW_WIDTH,
									GameStatics.CHILD_POPUP_WINDOW_HEIGHT, mapModel, E_MapEditorMode.Create);
						}
					}
					else{
						
					}
				}
				else if (e.getSource().equals(menuItemOpenMap)) {
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
										GameStatics.CHILD_POPUP_WINDOW_WIDTH,
										GameStatics.CHILD_POPUP_WINDOW_HEIGHT, mapModel, E_MapEditorMode.Open);
						} 
						else {
							JOptionPane.showMessageDialog(null, GameStatics.MSG_UNABLE_TO_OPEN_FILE);
						}
					} else {
						JOptionPane.showMessageDialog(null, GameStatics.MSG_NO_FILE_SELECTED);
					}
				}
				else if(e.getSource().equals(menuItemCreateCharacter))
				{
					
				}
			}
		}
		
		menuItemCreateMap.addActionListener(new MenuItemAction());
		menuItemOpenMap.addActionListener(new MenuItemAction());
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
