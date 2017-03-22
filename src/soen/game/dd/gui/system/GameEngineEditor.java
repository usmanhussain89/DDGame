package soen.game.dd.gui.system;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.Image;
import java.util.ArrayList;
import java.util.Observable;
import java.util.Observer;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;

import soen.game.dd.gui.components.JPanelGameComponent;
import soen.game.dd.models.Campaign;
import soen.game.dd.models.Character;
import soen.game.dd.models.DummyGameEngine;
import soen.game.dd.models.Map;
import soen.game.dd.statics.content.GameStatics;
import soen.game.dd.statics.content.GameEnums.E_MapEditorMode;

/**
 * This class create view of Game Engine
 * @author Usman
 *
 */
public class GameEngineEditor extends JFrame implements Observer {
	
	DummyGameEngine gameEngine;
	JPanel currentPanel;
	JPanelGameComponent panelComponent;
	
	/**
	 * This Constructor initialize Game Engine object
	 * 
	 * @param character
	 * @param campaign
	 */
	public GameEngineEditor(Character character, Campaign campaign){
		this.gameEngine = new DummyGameEngine(campaign, character);
		this.gameEngine.setCurrentMap();
		this.gameEngine.addObserver(this);
		initializeFrame();
		currentPanel = null;
		refreshPanel();
	}
	
	/**
	 * This method initialize frame of map
	 */
	public void initializeFrame() {
		this.setTitle("Playing game. Map " + gameEngine.getCurrentMap().getMapName());
		this.setPreferredSize(new Dimension(GameStatics.CHILD_POPUP_WINDOW_WIDTH+50, GameStatics.CHILD_POPUP_WINDOW_HEIGHT));
		this.setMaximumSize(new Dimension(GameStatics.CHILD_POPUP_WINDOW_WIDTH+50, GameStatics.CHILD_POPUP_WINDOW_HEIGHT));
		this.setMinimumSize(new Dimension(GameStatics.CHILD_POPUP_WINDOW_WIDTH+50, GameStatics.CHILD_POPUP_WINDOW_HEIGHT));
		this.setResizable(false);
		this.setLocationRelativeTo(null);
		this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		this.setVisible(true);
		this.panelComponent = new JPanelGameComponent(gameEngine);
		
		this.setContentPane(this.panelComponent.getPanel());
	}
	
	/**
	 * this method refresh panel of the frame
	 */
	public void refreshPanel(){
		if (gameEngine.getCurrentMap() == null){
			this.dispose();
		} else {
			this.panelComponent.refreshPanel();
		}
	}
	
	/**
	 * update is called when notifyobservers will call
	 */
	@Override
	public void update(Observable o, Object arg) {
		System.out.println("Redrawing");
		refreshPanel();
	}

}
