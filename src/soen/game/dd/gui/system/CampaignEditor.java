package soen.game.dd.gui.system;

import java.awt.Dimension;
import java.awt.Point;

import javax.swing.JFrame;

import soen.game.dd.gui.components.JMenuBarComponent;
import soen.game.dd.gui.components.JPanelComponent;
import soen.game.dd.models.Campaign;
import soen.game.dd.statics.content.GameStatics;
import soen.game.dd.statics.content.GameEnums.E_CampaignEditorMode;

/**
 * This class is a campaign Editor which implement jfram for campaign.
 * @author Usman
 *
 */
public class CampaignEditor extends JFrame {
	
	private static final long serialVersionUID = 4664263255574948100L;
	E_CampaignEditorMode campaignEditorMode;// enum

	/**
	 * This is the constructor of the class Initialize the new frame for the Campaign
	 * Editor
	 * 
	 * @param frame
	 * @param title
	 * @param width
	 * @param height
	 * @param map
	 * @param mapEditorMode
	 */
	public CampaignEditor(JFrame frame, String title, int width, int height,Campaign new_campaign, E_CampaignEditorMode campaignEditorMode) {
		if (frame != null) {
			Dimension frameSize = frame.getSize();
			Point p = frame.getLocation();
			setLocation(p.x + frameSize.width / 4, p.y + frameSize.height / 4);
		}
		Campaign campaign = new_campaign;

		this.campaignEditorMode = campaignEditorMode;

		if (E_CampaignEditorMode.Create == this.campaignEditorMode) {
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

		// MenuBar for Campaign Editor
		this.setJMenuBar((new JMenuBarComponent()).getCampaignEditorJMenuBar(campaign, this));

		// load Campaign Grid from Component
		this.setContentPane((new JPanelComponent()).getCampaignEditorGridPanel(campaign, campaignEditorMode, this));
	}
}