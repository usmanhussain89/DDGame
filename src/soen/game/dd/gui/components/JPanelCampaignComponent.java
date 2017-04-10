package soen.game.dd.gui.components;

import java.awt.List;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

import soen.game.dd.models.Campaign;
import soen.game.dd.models.Map;
import soen.game.dd.statics.content.GameEnums.E_CampaignEditorMode;

/**
 * This class create Jpanel for Campaign Component
 * 
 * @author Usman
 *
 */
public class JPanelCampaignComponent {
	private E_CampaignEditorMode campaignEditorMode;

	/**
	 * This method create the JPanel for the Campaign Editor and return JPanel
	 * which set Content Pane for the frame
	 * 
	 * @param new_mode
	 * @return JPanel
	 */
	public JPanel getCampaignEditorGridPanel(Campaign campaign, E_CampaignEditorMode new_mode, JFrame frame,
			ArrayList<Map> maps, ArrayList<Campaign> campaigns, int index) {

		campaignEditorMode = new_mode;
		JPanel panel;

		panel = new JPanel();
		panel.setLayout(null);

		JLabel lblCampaignName = new JLabel("Campaign Name: ");
		lblCampaignName.setBounds(40, 30, 100, 25);
		panel.add(lblCampaignName);

		JTextField txtCampaignName = new JTextField(30);
		txtCampaignName.setBounds(160, 30, 150, 25);
		panel.add(txtCampaignName);

		if (E_CampaignEditorMode.Open == new_mode)
			txtCampaignName.setEnabled(false);

		JLabel lblSelectMap = new JLabel("Select Map: ");
		lblSelectMap.setBounds(40, 75, 100, 25);
		panel.add(lblSelectMap);

		JComboBox<String> cbMapName = new JComboBox<String>();
		cbMapName.setBounds(160, 75, 100, 25);

		for (Map map : maps) {
			cbMapName.addItem(map.getMapName());
		}
		panel.add(cbMapName);

		JButton btnAddMap = new JButton("Add Map");
		btnAddMap.setBounds(280, 75, 90, 25);
		panel.add(btnAddMap);

		JLabel lblSelectedMap = new JLabel("Selected Map: ");
		lblSelectedMap.setBounds(40, 120, 100, 25);
		panel.add(lblSelectedMap);

		List mapList = new List();
		mapList.setBounds(160, 120, 150, 150);
		panel.add(mapList);

		JButton btnRemoveMap = new JButton("Remove Map");
		btnRemoveMap.setBounds(160, 290, 130, 25);
		// btnRemoveMap.setVisible(false);
		panel.add(btnRemoveMap);

		if (E_CampaignEditorMode.Open == new_mode) {
			txtCampaignName.setText(campaigns.get(index).getCampaignName());
			cbMapName.setSelectedIndex(index);
			System.out.println(campaigns.get(index).getCampaignList().size());
			for (Map m : campaigns.get(index).getCampaignList()) {
				mapList.add(m.getMapName());
			}
		}
		/**
		 * This class implements ActionListener for PanelAction
		 * 
		 * @author Usman
		 *
		 */
		class PanelAction implements ActionListener {
			@Override
			public void actionPerformed(ActionEvent e) {

				if (e.getSource().equals(btnAddMap)) {

					System.out.print(cbMapName.getSelectedIndex() + " " + maps.size());
					campaign.setCampaignList(maps.get(cbMapName.getSelectedIndex()));
					campaign.setCampaignName(txtCampaignName.getText());
					mapList.add(maps.get(cbMapName.getSelectedIndex()).getMapName());
					btnRemoveMap.setVisible(true);
				}

				else if (e.getSource().equals(btnRemoveMap)) {
					if (mapList.getSelectedIndex() >= 0) {
						int index = mapList.getSelectedIndex();
						mapList.remove(index);
						campaign.removeMapFromList(index);
					}
				}
			}

		}
		btnAddMap.addActionListener(new PanelAction());
		btnRemoveMap.addActionListener(new PanelAction());

		return panel;
	}
}
