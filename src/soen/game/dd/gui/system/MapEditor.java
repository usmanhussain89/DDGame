package soen.game.dd.gui.system;

import java.awt.Dimension;
import java.awt.Point;
import java.util.ArrayList;

import javax.swing.JFrame;

import soen.game.dd.gui.components.JMenuBarComponent;
import soen.game.dd.gui.components.JPanelMapComponent;
import soen.game.dd.models.Map;
import soen.game.dd.statics.content.GameStatics;
import soen.game.dd.statics.content.GameEnums.E_MapEditorMode;

/**
 * This is Map Editor class
 * 
 * @author Usman
 *
 */

public class MapEditor extends JFrame {

	private static final long serialVersionUID = -1931373119455341534L;
	Map map;
	E_MapEditorMode mapEditorMode;// enum

	/**
	 * This is the constructor of the class Initialize the new frame for the Map
	 * Editor
	 * 
	 * @param frame
	 * @param title
	 * @param width
	 * @param height
	 * @param map
	 * @param mapEditorMode
	 */
	public MapEditor(JFrame frame, String title, int width, int height, Map map, E_MapEditorMode mapEditorMode,
			ArrayList<Map> maps, int index) {
		if (frame != null) {
			Dimension frameSize = frame.getSize();
			Point p = frame.getLocation();
			setLocation(p.x + frameSize.width / 4, p.y + frameSize.height / 4);
		}

		this.map = map;
		this.mapEditorMode = mapEditorMode;

		if (E_MapEditorMode.Create == this.mapEditorMode) {
			title += " " + GameStatics.MAP_MODE_CREATE;
		} else {
			title += " " + GameStatics.MAP_MODE_OPEN;
		}

		// --- Set Map Editor Windows Properties
		this.setTitle(title);
		this.setPreferredSize(new Dimension(width, height));
		this.setMaximumSize(new Dimension(width, height));
		this.setMinimumSize(new Dimension(width, height));
		this.setResizable(false);
		this.setLocationRelativeTo(null);
		this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		this.setVisible(true);

		// MenuBar for Map Editor
		this.setJMenuBar((new JMenuBarComponent()).getMapEditorJMenuBar(map, this, maps, mapEditorMode, index));

		// load Map Grid from Component
		this.setContentPane((new JPanelMapComponent()).getMapEditorGridPanel(map, null, mapEditorMode, maps, index));
	}
}