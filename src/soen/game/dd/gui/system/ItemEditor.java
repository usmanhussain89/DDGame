package soen.game.dd.gui.system;

import java.awt.Dimension;
import java.awt.Point;
import java.util.ArrayList;

import javax.swing.JFrame;

import soen.game.dd.gui.components.JMenuBarComponent;
import soen.game.dd.gui.components.JPanelComponent;
import soen.game.dd.models.Item;
import soen.game.dd.statics.content.GameEnums.E_ItemEditorMode;
import soen.game.dd.statics.content.GameStatics;

/**
 * This class implement jframe for items.
 * @author Usman
 *
 */
public class ItemEditor extends JFrame {

	private static final long serialVersionUID = -5651767974169426284L;
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
	public ItemEditor(JFrame frame, String title, int width, int height, E_ItemEditorMode itemEditorMode, ArrayList<Item> items) {
		if (frame != null) {
			Dimension frameSize = frame.getSize();
			Point p = frame.getLocation();
			setLocation(p.x + frameSize.width / 4, p.y + frameSize.height / 4);
		}
		Item item = new Item();

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
		this.setJMenuBar((new JMenuBarComponent()).getItemEditorJMenuBar(item, this));

		// load Map Grid from Component
		this.setContentPane((new JPanelComponent()).getItemEditorGridPanel(item, null, itemEditorMode, items));
	}
}
