package soen.game.dd.gui.components;

import java.awt.List;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

import soen.game.dd.models.Item;
import soen.game.dd.models.Map;

/**
 * This class create the panel for the Map
 * 
 * @author Usman
 *
 */
public class JPanelChestComponent {

	/**
	 * This method create the JPanel for the Chest Window and return JPanel
	 * which set Content Pane for the frame
	 * 
	 * @param new_mapModel
	 * @return JPanel
	 */
	public JPanel getMapChestGridPanel(Map new_mapModel, ArrayList<Item> items, JFrame frame) {
		JPanel panel = new JPanel();
		panel.setLayout(null);
		int index = 0;

		JLabel lblItemName = new JLabel("Item Name: ");
		lblItemName.setBounds(40, 30, 80, 25);
		panel.add(lblItemName);

		JComboBox<String> cbItemName = new JComboBox<String>();
		cbItemName.setBounds(130, 30, 120, 25);
		cbItemName.setEditable(true);
		for (Item i : items) {
			if (i != null) {
				cbItemName.addItem(i.getName());
				cbItemName.setName("" + index);
				index++;
			}
		}

		panel.add(cbItemName);

		List mapList = new List();
		mapList.setBounds(40, 75, 150, 150);
		panel.add(mapList);

		JButton btnAddItem = new JButton("Select Item");
		btnAddItem.setBounds(40, 245, 100, 25);
		panel.add(btnAddItem);

		JButton btnRemoveItem = new JButton("Remove Item");
		btnRemoveItem.setBounds(150, 245, 120, 25);
		panel.add(btnRemoveItem);

		if (new_mapModel.mapSelectedItem != null) {
			for (Item i : new_mapModel.mapSelectedItem) {
				mapList.add(i.getName());
			}
		}

		// ActionListener for Remove items
		btnRemoveItem.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				if (cbItemName.getSelectedItem() != null) {
					if (items.get(cbItemName.getSelectedIndex()) == null)
						System.out.println("Null");
					else {
						mapList.remove((String) cbItemName.getSelectedItem());
						new_mapModel.mapSelectedItem.remove(items.get(cbItemName.getSelectedIndex()));

						if (new_mapModel.mapSelectedItem.size() > 0)
							new_mapModel.isChestDone = true;
						else
							new_mapModel.isChestDone = false;
					}
				}
			}
		});

		JButton btnSave = new JButton("Save");
		btnSave.setBounds(40, 290, 80, 25);
		panel.add(btnSave);

		btnSave.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				if (new_mapModel.mapSelectedItem.size() > 0) {
					JOptionPane.showMessageDialog(null, "Items has been added to chest.");
					frame.dispose();
				}

				else {
					JOptionPane.showMessageDialog(null, "Please select items.");
				}
			}
		});

		// Action Listener for Add items
		btnAddItem.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				if (cbItemName.getSelectedItem() != null) {
					mapList.add((String) cbItemName.getSelectedItem());
					if (items.get(cbItemName.getSelectedIndex()) == null)
						System.out.println("Null");
					else {
						Item i = items.get(cbItemName.getSelectedIndex());
						new_mapModel.isChestDone = true;
						System.out.println(
								cbItemName.getSelectedIndex() + " " + items.get(cbItemName.getSelectedIndex()));
						new_mapModel.mapSelectedItem.add(items.get(cbItemName.getSelectedIndex()));
					}
				}
			}
		});
		return panel;
	}
}