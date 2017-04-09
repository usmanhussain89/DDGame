package soen.game.dd.gui.components;

import java.awt.Color;
import java.awt.GridLayout;
import java.awt.Image;
import java.awt.Point;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;
import java.util.List;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

import soen.game.dd.fileio.CharacterIO;
import soen.game.dd.gui.system.JFrameAttributeView;
import soen.game.dd.gui.system.JFrameInventoryView;
import soen.game.dd.models.Campaign;
import soen.game.dd.models.Character;
import soen.game.dd.models.GameEngine;
import soen.game.dd.models.Item;
import soen.game.dd.models.Map;
import soen.game.dd.models.NPCType;
import soen.game.dd.statics.content.GameStatics;
import soen.game.dd.statics.content.GameEnums.E_MapEditorMode;

/**
 * This class create JPanel for Game Component
 * 
 * @author Usman
 *
 */
public class JPanelGameComponent {
	
	private E_MapEditorMode mapEditorMode;
	// 2D Array of JButton
	private JButton mapButtonsGrid2DArray[][];
	private JButton jButtonEntry;
	private JButton jButtonExit;
	private JButton jButtonCharacter;
	private JButton jButtonOpponent;
	private JButton jButtonChest;
	private JPanel panel;
	private GameEngine gameEngine;
	private Map new_mapModel;
	
	/**
	 * Constructor of the class initialize panel and gameEngine
	 * 
	 * @param gameEngine
	 */
	public JPanelGameComponent(GameEngine gameEngine){
		panel = new JPanel();
		this.gameEngine = gameEngine;
	}
	
	/**
	 * The method return Jpanel
	 * @return
	 */
	public JPanel getPanel(){
		return panel;
	}
	
	/**
	 * This method refresh the jpanel with the new components
	 * 
	 */
	public void refreshPanel() {
		panel.removeAll();
		ImageIcon imgIcon = new ImageIcon("Character.png");
		Image img = imgIcon.getImage();
		new_mapModel = gameEngine.getCurrentMap();
		GridLayout gridLayout;
		gridLayout = new GridLayout(new_mapModel.getMapHeight(), new_mapModel.getMapWidth(), 3, 3);
		panel.setLayout(gridLayout);
		gameEngine.setHitPoints();

		mapButtonsGrid2DArray = new JButton[new_mapModel.getMapHeight()][new_mapModel.getMapWidth()];
		
		/*JButton inventoryViewer = new JButton("Next Map");
		inventoryViewer.setBounds(700, 0, 200, 25);
		
		inventoryViewer.addActionListener(new ActionListener(){

			@Override
			public void actionPerformed(ActionEvent e) {
				gameEngine.nextMap();
				gameEngine.notifyObservers();
			}
			
		});*/
		
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


				if (new_mapModel.mapGridSelection[i][j] == GameStatics.MAP_PATH_POINT) {
					mapButtonsGrid2DArray[i][j].setBackground(Color.green);
				} else if (new_mapModel.mapGridSelection[i][j] == GameStatics.MAP_ENTRY_POINT) {
					//imgIcon = new ImageIcon(img.getScaledInstance(GameStatics.CHILD_POPUP_WINDOW_WIDTH/new_mapModel.getMapWidth() - 5, GameStatics.CHILD_POPUP_WINDOW_HEIGHT/new_mapModel.getMapHeight() - 5, java.awt.Image.SCALE_SMOOTH));
					mapButtonsGrid2DArray[i][j].setBackground(Color.red);
					//mapButtonsGrid2DArray[i][j].setIcon(imgIcon);
				} else if (new_mapModel.mapGridSelection[i][j] == GameStatics.MAP_EXIT_POINT) {
					mapButtonsGrid2DArray[i][j].setBackground(Color.red);
					mapButtonsGrid2DArray[i][j].setText("Exit");
				} else if (new_mapModel.mapGridSelection[i][j] == GameStatics.MAP_CHARACTER_POINT) {
					mapButtonsGrid2DArray[i][j].setBackground(Color.white);
					mapButtonsGrid2DArray[i][j].setText("Friendly");
				} else if (new_mapModel.mapGridSelection[i][j] == GameStatics.MAP_OPPONENT_POINT) {
					mapButtonsGrid2DArray[i][j].setBackground(Color.blue);
					if (gameEngine.getCurrentMap().getHostileCharacter().getNPCType().equals(NPCType.HOSTILE))
						mapButtonsGrid2DArray[i][j].setText("Hostile");
					else
						mapButtonsGrid2DArray[i][j].setText("Dead");
				} else if (new_mapModel.mapGridSelection[i][j] == GameStatics.MAP_CHEST_POINT) {
					mapButtonsGrid2DArray[i][j].setBackground(Color.orange);
					mapButtonsGrid2DArray[i][j].setText("Chest");
				} else {
					mapButtonsGrid2DArray[i][j].setBackground(Color.gray);
				}
				
				
				mapButtonsGrid2DArray[i][j].setOpaque(true);
				mapButtonsGrid2DArray[i][j].setBorderPainted(false);
				panel.add(mapButtonsGrid2DArray[i][j]);
				
				mapButtonsGrid2DArray[i][j].addMouseListener(new InteractionListener());
			}
			
		}
		Point characterPosition = gameEngine.getCharacterPosition();
		int x = (int) characterPosition.getX();
		int y = (int) characterPosition.getY();
		mapButtonsGrid2DArray[x][y].setBackground(Color.CYAN);
		mapButtonsGrid2DArray[x][y].setText("YOU");
		
		this.panel.revalidate();
		this.panel.repaint();
	}
	
	/**
	 * this class implement mouseAdapter listener for Interaction
	 * 
	 * @author Usman
	 *
	 */
	class InteractionListener extends MouseAdapter{
		
		/**
		 * This method is called when mouse clicked
		 */
		public void mouseClicked(MouseEvent e) {
			JButton button = (JButton) e.getSource();
			String[] coordinates = button.getName().split(":");
			int x = Integer.parseInt(coordinates[1]);
			int y = Integer.parseInt(coordinates[2]);
			
			Point characterPosition = gameEngine.getCharacterPosition();
			int xCharacter = (int) characterPosition.getX();
			int yCharacter = (int) characterPosition.getY();

			if (x == xCharacter && y == yCharacter) {
				if (e.getButton() == MouseEvent.BUTTON3){
					new JFrameAttributeView(gameEngine.getCharacter());
				} else {
					new JFrameInventoryView(gameEngine.getCharacter(), true);
				}
				return; //Don't do anything else
			}
			
		
			if (new_mapModel.mapGridSelection[x][y] == GameStatics.MAP_CHARACTER_POINT) {
				if (e.isShiftDown()){
					e.consume();
					if (e.getButton() == MouseEvent.BUTTON3){
						new JFrameAttributeView(gameEngine.getCurrentMap().getFriendlyCharacter());
					} else {
						new JFrameInventoryView(gameEngine.getCurrentMap().getFriendlyCharacter(), false);
					}
					return; //Don't do anything else
				} else {
					Item itemToSwap = getItemToSwap();
					if (itemToSwap != null)
						gameEngine.exchangeWithNPC(itemToSwap);
				}
			}
			
			if (new_mapModel.mapGridSelection[x][y] == GameStatics.MAP_OPPONENT_POINT && mapButtonsGrid2DArray[x][y].getText().equals("Hostile")) {
				if (e.isShiftDown()){
					e.consume();
					if (e.getButton() == MouseEvent.BUTTON3){
						new JFrameAttributeView(gameEngine.getCurrentMap().getHostileCharacter());
					} else {
						new JFrameInventoryView(gameEngine.getCurrentMap().getHostileCharacter(), false);
					}
					return; //Don't do anything else
				} else {
					Character hostileCharacter = gameEngine.getCurrentMap().getHostileCharacter();
					
					if (hostileCharacter != null) {
						if (gameEngine.encounter(gameEngine.getCharacter(), hostileCharacter) == 0) {
							hostileCharacter.setNPCType(NPCType.DEAD);
							gameEngine.getCurrentMap().mapCharacters.set(gameEngine.getCurrentMap().getHostileCharacterIndex(), hostileCharacter);
							JOptionPane.showMessageDialog(null, "Rest in peace....");
							mapButtonsGrid2DArray[x][y].setText("Dead");
						}
					}
				}
			}
			
			if (new_mapModel.mapGridSelection[x][y] == GameStatics.MAP_OPPONENT_POINT && mapButtonsGrid2DArray[x][y].getText().equals("Dead")) {
				Character hostileCharacter = gameEngine.getCurrentMap().getHostileCharacter();
				gameEngine.lootHostileItems(hostileCharacter.getBackpack());
			}
			
			if (new_mapModel.mapGridSelection[x][y] == GameStatics.MAP_CHEST_POINT || new_mapModel.mapGridSelection[x][y] == GameStatics.MAP_PATH_POINT) {
				gameEngine.interactWith(x,y);			
				gameEngine.notifyObservers();
			}
			
			if (new_mapModel.mapGridSelection[x][y] == GameStatics.MAP_EXIT_POINT) {
				Character hostileCharacter = gameEngine.getCurrentMap().getHostileCharacter();
				
				if (hostileCharacter.getNPCType() == NPCType.DEAD) {
					JOptionPane.showMessageDialog(null, "Congrats you achieve the map objective");
					gameEngine.getCharacter().levelUp();
					if (!gameEngine.nextMap()) {
						JOptionPane.showMessageDialog(null, "Campaign end....");
					}
					gameEngine.notifyObservers();
				}
			}
		}
		
		/**
		 * This method swap items between character and non playing character
		 * @return
		 */
		public Item getItemToSwap(){
			List<Item> items = gameEngine.getCharacter().getBackpack();
			JComboBox<String> cbItem = new JComboBox<String>();
			
			for(Item i : items) {
				cbItem.addItem(i.getName());
			}
			
			Object[] messageCharacter = { "SELECT Item:", cbItem };

			int optionCharacter = JOptionPane.showConfirmDialog(null, messageCharacter, "Swap with friendly guy",
					JOptionPane.OK_CANCEL_OPTION);

			if (optionCharacter == JOptionPane.OK_OPTION) {
				for(Item i : items)
				{
					if(i.getName().equals((String)cbItem.getSelectedItem()))
					{
						return i;
					}
				}
			}
			return null;
		}
	}
	
	
}
