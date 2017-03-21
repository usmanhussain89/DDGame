package soen.game.dd.gui.components;

import java.awt.Color;
import java.awt.GridLayout;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;

import soen.game.dd.gui.system.JFrameInventoryView;
import soen.game.dd.models.Campaign;
import soen.game.dd.models.Character;
import soen.game.dd.models.DummyGameEngine;
import soen.game.dd.models.Map;
import soen.game.dd.statics.content.GameStatics;
import soen.game.dd.statics.content.GameEnums.E_MapEditorMode;

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
	
	public JPanelGameComponent(){
		panel = new JPanel();
	}
	
	public JPanel getPanel(){
		return panel;
	}
	
	public void refreshPanel(DummyGameEngine gameEngine) {
		panel.removeAll();
		ImageIcon imgIcon = new ImageIcon("Character.png");
		Image img = imgIcon.getImage();
		Map new_mapModel = gameEngine.getCurrentMap();
		GridLayout gridLayout;
		gridLayout = new GridLayout(new_mapModel.getMapHeight(), new_mapModel.getMapWidth(), 3, 3);
		panel.setLayout(gridLayout);

		mapButtonsGrid2DArray = new JButton[new_mapModel.getMapHeight()][new_mapModel.getMapWidth()];
		
		JButton inventoryViewer = new JButton("Next Map");
		inventoryViewer.setBounds(700, 0, 200, 25);
		
		inventoryViewer.addActionListener(new ActionListener(){

			@Override
			public void actionPerformed(ActionEvent e) {
				gameEngine.nextMap();
				gameEngine.notifyObservers();
			}
			
		});
		
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
					imgIcon = new ImageIcon(img.getScaledInstance(GameStatics.CHILD_POPUP_WINDOW_WIDTH/new_mapModel.getMapWidth() - 5, GameStatics.CHILD_POPUP_WINDOW_HEIGHT/new_mapModel.getMapHeight() - 5, java.awt.Image.SCALE_SMOOTH));
					mapButtonsGrid2DArray[i][j].setBackground(Color.red);
					mapButtonsGrid2DArray[i][j].setIcon(imgIcon);
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

				mapButtonsGrid2DArray[i][j].setOpaque(true);
				mapButtonsGrid2DArray[i][j].setBorderPainted(false);
				panel.add(mapButtonsGrid2DArray[i][j]);
			}
		}

		this.panel.revalidate();
		this.panel.repaint();
	}
	
}
