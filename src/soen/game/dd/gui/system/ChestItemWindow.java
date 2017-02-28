package soen.game.dd.gui.system;

import java.awt.Dimension;
import java.awt.Point;

import javax.swing.JFrame;

import soen.game.dd.gui.components.JMenuBarComponent;
import soen.game.dd.statics.content.GameStatics;

public class ChestItemWindow extends JFrame{

	private static final long serialVersionUID = -116918410514404414L;
	
	/**
	 * This is the constructor of the class
	 * Initialize the new frame for the Map Editor
	 * 
	 */
	public ChestItemWindow() {
		
		// --- Set Map Editor Windows Properties
		this.setTitle(GameStatics.TITLE_ADD_CHEST_ITEMS);
		this.setPreferredSize(new Dimension(GameStatics.CHEST_ITEM_POPUP_WINDOW_WIDTH, GameStatics.CHEST_ITEM_POPUP_WINDOW_WIDTH));
		this.setMaximumSize(new Dimension(GameStatics.CHEST_ITEM_POPUP_WINDOW_WIDTH, GameStatics.CHEST_ITEM_POPUP_WINDOW_WIDTH));
		this.setMinimumSize(new Dimension(GameStatics.CHEST_ITEM_POPUP_WINDOW_WIDTH, GameStatics.CHEST_ITEM_POPUP_WINDOW_WIDTH));
		this.setResizable(false);
		this.setLocationRelativeTo(null);
		this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		this.setVisible(true);
		
		// MenuBar for Map Editor
		this.setJMenuBar((new JMenuBarComponent()).getMapChestItemJMenuBar(this));
		
		//load Map Grid from Component
		//this.setContentPane((new JPanelComponent()).getMapEditorGridPanel(map, null, mapEditorMode));
	}
}