package soen.game.dd.gui.system;

import java.awt.Dimension;
import java.awt.Point;

import javax.swing.JFrame;

import soen.game.dd.gui.components.JMenuBarComponent;
import soen.game.dd.gui.components.JPanelComponent;
import soen.game.dd.statics.content.GameEnums.E_ItemEditorMode;
import soen.game.dd.statics.content.GameStatics;

public class ItemEditor extends JFrame {

	E_ItemEditorMode itemEditorMode;// enum

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
	public ItemEditor(JFrame frame, String title, int width, int height, E_ItemEditorMode itemEditorMode) {
		if (frame != null) {
			Dimension frameSize = frame.getSize();
			Point p = frame.getLocation();
			setLocation(p.x + frameSize.width / 4, p.y + frameSize.height / 4);
		}

		this.itemEditorMode = itemEditorMode;

		if (E_ItemEditorMode.Create == this.itemEditorMode) {
			title += " " + GameStatics.ITEM_MODE_CREATE;
		} else {
			title += " " + GameStatics.ITEM_MODE_OPEN;
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
		this.setJMenuBar((new JMenuBarComponent()).getItemEditorJMenuBar(this));

		// load Map Grid from Component
		this.setContentPane((new JPanelComponent()).getItemEditorGridPanel(itemEditorMode));
	}
}
