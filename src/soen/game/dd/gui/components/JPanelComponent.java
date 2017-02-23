package soen.game.dd.gui.components;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridLayout;

import javax.swing.JButton;
import javax.swing.JPanel;

import soen.game.dd.models.Map;
import soen.game.dd.statics.content.GameEnums.E_MapEditorMode;

/**
 * This class create the panel for the Map
 * 
 * @author Usman
 *
 */
public class JPanelComponent {
	
	private E_MapEditorMode mapEditorMode;
	//2D Array of JButton
	private JButton mapButtonsGrid2DArray[][];
	
	/**
	 * This method create the JPanel for the Map Editor
	 * and return JPanel which set Content Pane for the frame
	 * 
	 * @param new_mapModel
	 * @param new_parentDimension
	 * @param new_mode
	 * @return JPanel
	 */
	public JPanel getMapEditorGridPanel(Map new_mapModel, Dimension new_parentDimension,
			E_MapEditorMode new_mode) {
		mapEditorMode = new_mode;
		JPanel panel;
		GridLayout gridLayout;
		
		panel = new JPanel();
		// When Create Mode Initialize the mapGridSelection to new
		if (E_MapEditorMode.Create == mapEditorMode) {
			new_mapModel.mapGridSelection = new int[new_mapModel.getMapHeight()][new_mapModel.getMapWidth()];
			//panel = new JPanel();
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
				}

				mapButtonsGrid2DArray[i][j].setOpaque(true);
				mapButtonsGrid2DArray[i][j].setBorderPainted(false);
				panel.add(mapButtonsGrid2DArray[i][j]);
			}
		}

		return panel;
	}
}
