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
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map.Entry;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

import soen.game.dd.fileio.CharacterIO;
import soen.game.dd.gui.system.JFrameAttributeView;
import soen.game.dd.gui.system.JFrameInventoryView;
import soen.game.dd.models.Campaign;
import soen.game.dd.models.Character;
import soen.game.dd.models.CharacterAttribute;
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
		this.gameEngine.startGame();
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


				if (new_mapModel.mapGridSelection[i][j] == GameStatics.MAP_PATH_POINT) {
					mapButtonsGrid2DArray[i][j].setBackground(Color.green);
				} else if (new_mapModel.mapGridSelection[i][j] == GameStatics.MAP_ENTRY_POINT) {
					//imgIcon = new ImageIcon(img.getScaledInstance(GameStatics.CHILD_POPUP_WINDOW_WIDTH/new_mapModel.getMapWidth() - 5, GameStatics.CHILD_POPUP_WINDOW_HEIGHT/new_mapModel.getMapHeight() - 5, java.awt.Image.SCALE_SMOOTH));
					mapButtonsGrid2DArray[i][j].setBackground(Color.red);
					//mapButtonsGrid2DArray[i][j].setIcon(imgIcon);
				} else if (new_mapModel.mapGridSelection[i][j] == GameStatics.MAP_EXIT_POINT) {
					mapButtonsGrid2DArray[i][j].setBackground(Color.red);
					mapButtonsGrid2DArray[i][j].setText("Exit");
//				} else if (new_mapModel.mapGridSelection[i][j] == GameStatics.MAP_CHARACTER_POINT) {
//					mapButtonsGrid2DArray[i][j].setBackground(Color.white);
//					mapButtonsGrid2DArray[i][j].setText("Friendly");
//				} else if (new_mapModel.mapGridSelection[i][j] == GameStatics.MAP_OPPONENT_POINT) {
//					mapButtonsGrid2DArray[i][j].setBackground(Color.blue);
//					if (gameEngine.getCurrentMap().getHostileCharacter().getNPCType().equals(NPCType.HOSTILE))
//						mapButtonsGrid2DArray[i][j].setText("Hostile");
//					else
//						mapButtonsGrid2DArray[i][j].setText("Dead");
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
		for (Point point : gameEngine.getDangerPoints()){
			mapButtonsGrid2DArray[point.x][point.y].setBackground(Color.MAGENTA);
		}
		HashMap<Character, Point> positions = gameEngine.getPositions();
		for (Entry<Character, Point> entry : positions.entrySet()) {
		    Character key = entry.getKey();
		    Point value1 = entry.getValue();
		    if (key.getNPCType() == NPCType.PLAYABALE){
				mapButtonsGrid2DArray[(int) value1.getX()][(int) value1.getY()].setBackground(Color.CYAN);
				mapButtonsGrid2DArray[(int) value1.getX()][(int) value1.getY()].setText("YOU");
		    }
		    if (key.getNPCType() == NPCType.FRINDLY){
				mapButtonsGrid2DArray[(int) value1.getX()][(int) value1.getY()].setBackground(Color.white);
				mapButtonsGrid2DArray[(int) value1.getX()][(int) value1.getY()].setText("Friendly");
		    }
		    if (key.getNPCType() == NPCType.HOSTILE){
				mapButtonsGrid2DArray[(int) value1.getX()][(int) value1.getY()].setBackground(Color.blue);
				mapButtonsGrid2DArray[(int) value1.getX()][(int) value1.getY()].setText("Hostile");
		    }				    
		    if (key.getNPCType() == NPCType.DEAD){
				mapButtonsGrid2DArray[(int) value1.getX()][(int) value1.getY()].setBackground(Color.blue);
				mapButtonsGrid2DArray[(int) value1.getX()][(int) value1.getY()].setText("Dead");
		    }
		}
		

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
			
			Point characterPosition = gameEngine.getPositionOfCharacter(gameEngine.getCharacter());
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
					
					if (hostileCharacter != null && gameEngine.getCharacterAttacked() == 0) {
						gameEngine.attack(gameEngine.getCharacter(), hostileCharacter);
					}
				}
			}
			
			if (new_mapModel.mapGridSelection[x][y] == GameStatics.MAP_OPPONENT_POINT && mapButtonsGrid2DArray[x][y].getText().equals("Dead")) {
				Character hostileCharacter = gameEngine.getCurrentMap().getHostileCharacter();
				gameEngine.lootHostileItems(hostileCharacter.getBackpack());
			}
			
			if (new_mapModel.mapGridSelection[x][y] == GameStatics.MAP_CHEST_POINT) {
				gameEngine.interactWith(gameEngine.getCharacter(), x,y);			
				gameEngine.notifyObservers();
			}
			
			if (new_mapModel.mapGridSelection[x][y] == GameStatics.MAP_PATH_POINT) {
				if (gameEngine.getCharacterMoved() < 3){
					gameEngine.move(gameEngine.getCharacter(), x,y);			
					gameEngine.notifyObservers();
				}
			}
			
			
			if (new_mapModel.mapGridSelection[x][y] == GameStatics.MAP_EXIT_POINT) {
				Character hostileCharacter = gameEngine.getCurrentMap().getHostileCharacter();
				
				if (hostileCharacter.getNPCType() == NPCType.DEAD) {
					JOptionPane.showMessageDialog(null, "Congrats you achieve the map objective");
					gameEngine.getCharacter().levelUp();
					if(gameEngine.getCharacter().getLevel() == 4 || gameEngine.getCharacter().getLevel() == 6 || gameEngine.getCharacter().getLevel() == 8 || gameEngine.getCharacter().getLevel() == 12 || gameEngine.getCharacter().getLevel() == 14 || gameEngine.getCharacter().getLevel() == 16 || gameEngine.getCharacter().getLevel() == 19 ) {
						JList<CharacterAttribute> list = new JList(new CharacterAttribute[] {CharacterAttribute.DEXTERITY, CharacterAttribute.WISDOM, CharacterAttribute.STRENGTH, CharacterAttribute.ATTACK_BONUS, CharacterAttribute.CONSTITUTION, CharacterAttribute.CHARISMA});
						boolean flag = true;
						while(flag) {
							JOptionPane.showMessageDialog(
									  null, list, "Multi-Select Example", JOptionPane.PLAIN_MESSAGE);
							System.out.println(Arrays.toString(list.getSelectedIndices()));
							if(list.getSelectedIndices().length>0 && list.getSelectedIndices().length <= 2) {
								flag = false;
								if(list.getSelectedIndices().length == 1) {
									for (int index : list.getSelectedIndices()) {
										switch(index) {
										case 0:
											System.out.println("Dexterity Before : " + gameEngine.getCharacter().getDexterity());
											gameEngine.getCharacter().setDexterity(gameEngine.getCharacter().getDexterity() + 2);
											System.out.println("Dexterity After : " + gameEngine.getCharacter().getDexterity());
											break;
										case 1:
											System.out.println("Wisdom Before : " + gameEngine.getCharacter().getWisdom());
											gameEngine.getCharacter().setWisdom(gameEngine.getCharacter().getWisdom() + 2);
											System.out.println("Wisdom After : " + gameEngine.getCharacter().getWisdom());
											break;
										case 2:
											System.out.println("Strength Before : " + gameEngine.getCharacter().getStrength());
											gameEngine.getCharacter().setStrength(gameEngine.getCharacter().getStrength() + 2);
											System.out.println("Strength Before : " + gameEngine.getCharacter().getStrength());
											break;
										case 3:
											System.out.println("AttackBonus Before : " + gameEngine.getCharacter().getAttackBonus());
											gameEngine.getCharacter().setAttackBonus(gameEngine.getCharacter().getAttackBonus() + 2);
											System.out.println("AttackBonus Before : " + gameEngine.getCharacter().getAttackBonus());
											break;
										case 4:
											System.out.println("Constitution Before : " + gameEngine.getCharacter().getConstitution());
											gameEngine.getCharacter().setConstitution(gameEngine.getCharacter().getConstitution() + 2);
											System.out.println("Constitution Before : " + gameEngine.getCharacter().getConstitution());
											break;
										case 5:
											System.out.println("Charisma Before : " + gameEngine.getCharacter().getCharisma());
											gameEngine.getCharacter().setCharisma(gameEngine.getCharacter().getCharisma() + 2);
											System.out.println("Charisma Before : " + gameEngine.getCharacter().getCharisma());
											break;
										}
									}
								}
								if(list.getSelectedIndices().length == 2) {
									for (int index : list.getSelectedIndices()) {
										switch(index) {
										case 0:
											System.out.println("Dexterity Before : " + gameEngine.getCharacter().getDexterity());
											gameEngine.getCharacter().setDexterity(gameEngine.getCharacter().getDexterity() + 1);
											System.out.println("Dexterity After : " + gameEngine.getCharacter().getDexterity());
											break;
										case 1:
											System.out.println("Wisdom Before : " + gameEngine.getCharacter().getWisdom());
											gameEngine.getCharacter().setWisdom(gameEngine.getCharacter().getWisdom() + 1);
											System.out.println("Wisdom After : " + gameEngine.getCharacter().getWisdom());
											break;
										case 2:
											System.out.println("Strength Before : " + gameEngine.getCharacter().getStrength());
											gameEngine.getCharacter().setStrength(gameEngine.getCharacter().getStrength() + 1);
											System.out.println("Strength Before : " + gameEngine.getCharacter().getStrength());
											break;
										case 3:
											System.out.println("AttackBonus Before : " + gameEngine.getCharacter().getAttackBonus());
											gameEngine.getCharacter().setAttackBonus(gameEngine.getCharacter().getAttackBonus() + 1);
											System.out.println("AttackBonus Before : " + gameEngine.getCharacter().getAttackBonus());
											break;
										case 4:
											System.out.println("Constitution Before : " + gameEngine.getCharacter().getConstitution());
											gameEngine.getCharacter().setConstitution(gameEngine.getCharacter().getConstitution() + 1);
											System.out.println("Constitution Before : " + gameEngine.getCharacter().getConstitution());
											break;
										case 5:
											System.out.println("Charisma Before : " + gameEngine.getCharacter().getCharisma());
											gameEngine.getCharacter().setCharisma(gameEngine.getCharacter().getCharisma() + 1);
											System.out.println("Charisma Before : " + gameEngine.getCharacter().getCharisma());
											break;
										}
									}
								}
							} else {
								System.out.println("Select only one or two attributes");
							}
						}
					}
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
