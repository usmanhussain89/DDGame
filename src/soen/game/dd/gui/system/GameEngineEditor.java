package soen.game.dd.gui.system;

import java.awt.Dimension;
import java.util.Observable;
import java.util.Observer;

import javax.swing.JFrame;
import javax.swing.JPanel;

import soen.game.dd.gui.components.JMenuBarGameEngineComponent;
import soen.game.dd.gui.components.JPanelGameComponent;
import soen.game.dd.models.Campaign;
import soen.game.dd.models.Character;
import soen.game.dd.models.GameEngine;
import soen.game.dd.statics.content.GameStatics;

/**
 * This class create view of Game Engine
 * 
 * @author Usman
 * @author Khaled
 *
 */
public class GameEngineEditor extends JFrame implements Observer {

	GameEngine gameEngine;
	JPanel currentPanel;
	JPanelGameComponent panelComponent;

	/**
	 * This Constructor initialize Game Engine object
	 * 
	 * @param character
	 *
	 * @param campaign
	 */
	public GameEngineEditor(Character character, Campaign campaign) {
		this.gameEngine = new GameEngine(campaign, character);
		this.gameEngine.setCurrentMap();
		this.gameEngine.addObserver(this);
		initializeFrame();
		currentPanel = null;
	}

	/**
	 * This Constructor initialize Game Engine object by passing passing
	 * GameEgine
	 * 
	 * @param GameEngine
	 */
	public GameEngineEditor(GameEngine gameEngine) {
		this.gameEngine = gameEngine;
		this.gameEngine.setCurrentMap();
		this.gameEngine.addObserver(this);
		initializeFrame();
		currentPanel = null;
	}

	/**
	 * This method initialize frame of map
	 */
	public void initializeFrame() {
		this.setTitle("Playing game. Map " + gameEngine.getCurrentMap().getMapName());
		this.setPreferredSize(
				new Dimension(GameStatics.CHILD_POPUP_WINDOW_WIDTH + 50, GameStatics.CHILD_POPUP_WINDOW_HEIGHT));
		this.setMaximumSize(
				new Dimension(GameStatics.CHILD_POPUP_WINDOW_WIDTH + 50, GameStatics.CHILD_POPUP_WINDOW_HEIGHT));
		this.setMinimumSize(
				new Dimension(GameStatics.CHILD_POPUP_WINDOW_WIDTH + 50, GameStatics.CHILD_POPUP_WINDOW_HEIGHT));
		this.setResizable(false);
		this.setLocationRelativeTo(null);
		this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		this.setVisible(true);
		this.panelComponent = new JPanelGameComponent(gameEngine);
		this.setJMenuBar((new JMenuBarGameEngineComponent()).getGameEngineEditorJMenuBar(gameEngine, this));
		this.setContentPane(this.panelComponent.getPanel());
	}

	/**
	 * this method refresh panel of the frame
	 */
	public void refreshPanel() {
		if (gameEngine.getCurrentMap() == null) {
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
