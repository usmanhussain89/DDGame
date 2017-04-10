package soen.game.dd.gui.components;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;

import soen.game.dd.fileio.CampaignIO;
import soen.game.dd.models.Campaign;
import soen.game.dd.statics.content.GameStatics;
import soen.game.dd.statics.content.GameEnums.E_CampaignEditorMode;

/**
 * This class class create JMenuBar for Campaign
 * 
 * @author Usman
 *
 */
public class JMenuBarCampaignComponent {

	/**
	 * This method create Menu for Campaign Editor Window
	 * 
	 * @param new_campaignModel
	 * @param new_jframe
	 * @return JMenu for Campaign Editor
	 */
	public JMenuBar getCampaignEditorJMenuBar(Campaign campaign, JFrame new_jframe, E_CampaignEditorMode new_mode,
			ArrayList<Campaign> campaigns, int index) {
		JMenuBar menuBar = new JMenuBar();
		JMenu menuFile = new JMenu(GameStatics.MENU_FILE);
		JMenuItem menuItemSave = new JMenuItem(GameStatics.MENU_ITEM_SAVE);
		menuFile.add(menuItemSave);
		JMenuItem menuItemExit = new JMenuItem(GameStatics.MENU_ITEM_EXIT);
		menuFile.add(menuItemExit);

		class MenuItemAction implements ActionListener {

			@Override
			public void actionPerformed(ActionEvent new_event) {
				if (new_event.getSource().equals(menuItemSave)) {
					if (campaign != null) {
						System.out.println(campaign.getCampaignList().size());
						if (campaign.getCampaignList().size() > 1) {
							if (E_CampaignEditorMode.Create == new_mode) {
								if (!campaign.getCampaignName().equals("")) {

									String msg = new CampaignIO().saveCampaign(campaign);
									if (msg.contains(GameStatics.STATUS_SUCCESS)) {
										JOptionPane.showMessageDialog(null,
												String.format(GameStatics.MSG_CAMPAIGN_FILE_LOADED_SAVED, "saved"));
										closeFrame(new_jframe);
									} else if (msg.contains(GameStatics.STATUS_EXIST)) {
										JOptionPane.showMessageDialog(null,
												String.format(GameStatics.MSG_DUPLICATE_NAME, "Campaign name"));
									} else {
										JOptionPane.showMessageDialog(null, msg);
									}
								} else {
									JOptionPane.showMessageDialog(null, "Enter Campaign name first");
								}
							}

							else {
								campaigns.set(index, campaign);
								String msg = new CampaignIO().saveCampaigns(campaigns);
								if (msg.contains(GameStatics.STATUS_SUCCESS)) {
									JOptionPane.showMessageDialog(null,
											String.format(GameStatics.MSG_CAMPAIGN_FILE_LOADED_SAVED, "saved"));
									closeFrame(new_jframe);
								} else {
									JOptionPane.showMessageDialog(null, msg);
								}
							}
						}

						else {
							JOptionPane.showMessageDialog(null, "Select more than one map");
						}
					} else {
						JOptionPane.showMessageDialog(null, GameStatics.MSG_NO_CAMPAIGN_CREATED);
					}
				}
			}
		}

		menuItemSave.addActionListener(new MenuItemAction());
		menuBar.add(menuFile);
		return menuBar;
	}

	/**
	 * This method closes the frame of the application
	 * 
	 * @param new_jframe
	 *            the frame of the application
	 */
	public void closeFrame(JFrame new_jframe) {
		new_jframe.dispose();
	}
}
