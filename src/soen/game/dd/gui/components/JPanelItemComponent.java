package soen.game.dd.gui.components;

import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;

import soen.game.dd.fileio.ItemIO;
import soen.game.dd.models.CharacterAttribute;
import soen.game.dd.models.Item;
import soen.game.dd.models.ItemType;
import soen.game.dd.models.WeaponType;
import soen.game.dd.statics.content.GameStatics;
import soen.game.dd.statics.content.GameEnums.E_ItemEditorMode;

/**
 * This class create jpanel for Items
 * @author Usman
 *
 */
public class JPanelItemComponent {
	
	String itemName = "";
	private E_ItemEditorMode itemEditorMode;
	
	/**
	 * This method create the JPanel for the Item Editor and return JPanel which
	 * set Content Pane for the frame
	 * 
	 * @param new_mode
	 * @return JPanel
	 */
	public JPanel getItemEditorGridPanel(Item item, Dimension new_parentDimension, E_ItemEditorMode new_mode,
			ArrayList<Item> items) {

		itemEditorMode = new_mode;
		JPanel panel;
		// JTextField txtItemName = null;

		panel = new JPanel();
		;
		panel.setLayout(null);

		JLabel lblItemName = new JLabel("Item Name: ");
		lblItemName.setBounds(40, 30, 80, 25);
		panel.add(lblItemName);

		JTextField txtItemName = new JTextField(40);
		txtItemName.setBounds(170, 30, 120, 25);
		txtItemName.setVisible(false);

		JComboBox<String> cbItemName = new JComboBox<String>();
		cbItemName.setBounds(170, 30, 120, 25);
		cbItemName.setEditable(true);
		cbItemName.setVisible(false);
		if (E_ItemEditorMode.Open == new_mode) {
			txtItemName.setVisible(false);
			cbItemName.setVisible(true);
			int index = 0;

			for (Item i : items) {
				if (i != null) {
					cbItemName.addItem(i.getName());
					cbItemName.setName("" + index);
					index++;
				}
			}

			panel.add(cbItemName);
		}

		else {
			txtItemName.setVisible(true);
			cbItemName.setVisible(false);

			panel.add(txtItemName);
		}

		JLabel lblItemType = new JLabel("Item Type: ");
		lblItemType.setBounds(40, 75, 80, 25);
		;
		panel.add(lblItemType);

		JComboBox cbItemType = new JComboBox(ItemType.values());
		cbItemType.setBounds(170, 75, 120, 25);
		panel.add(cbItemType);
		
		JLabel lblWeaponType = new JLabel("Weapon Type :");
		lblWeaponType.setBounds(40, 120, 90, 25);
		panel.add(lblWeaponType);

		JComboBox cbWeaponType = new JComboBox();
		cbWeaponType.addItem(WeaponType.NotAWeapon);
		cbWeaponType.setBounds(170, 120, 120, 25);
		panel.add(cbWeaponType);

		JLabel lblCharacterAttr = new JLabel("Character Attribute: ");
		lblCharacterAttr.setBounds(40, 165, 120, 25);
		panel.add(lblCharacterAttr);

		JComboBox cbCharacterAttr = new JComboBox(ItemType.HELMET.getAllowedAttributes().toArray());
		cbCharacterAttr.setBounds(170, 165, 120, 25);
		panel.add(cbCharacterAttr);

		JLabel lblBonusAmount = new JLabel("Bonus Amount: ");
		lblBonusAmount.setBounds(40, 210, 100, 25);
		panel.add(lblBonusAmount);

		Integer[] bonuses = new Integer[] { 1, 2, 3, 4, 5 };
		JComboBox cbBonusAmount = new JComboBox(bonuses);
		cbBonusAmount.setBounds(170, 210, 100, 25);
		panel.add(cbBonusAmount);

		JButton btnAddItem = new JButton("Save Item");
		btnAddItem.setBounds(40, 250, 120, 25);
		panel.add(btnAddItem);

		if (E_ItemEditorMode.Open == new_mode) {
			cbItemName.setSelectedItem(items.get(0).getName());
			cbItemType.setSelectedItem(items.get(0).getItemType());
			cbCharacterAttr.setSelectedItem(items.get(0).getEnhancedAttribute());
			cbBonusAmount.setSelectedItem(items.get(0).getBonusAmount());
			itemName = items.get(0).getName();
		}
		//Action listener for combobo item type
		cbItemType.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				System.out.println(cbItemType.getSelectedItem());
				if (cbItemType.getSelectedItem().toString().equals("WEAPON")) {
					cbWeaponType.removeAllItems();
					cbWeaponType.addItem(WeaponType.MELEE);
					cbWeaponType.addItem(WeaponType.RANGED);
				}
				
				else {
					cbWeaponType.removeAllItems();
					cbWeaponType.addItem(WeaponType.NotAWeapon);
				}
					
				cbCharacterAttr.removeAllItems();
				for (CharacterAttribute c : ((ItemType) cbItemType.getSelectedItem()).getAllowedAttributes()) {
					cbCharacterAttr.addItem(c);
				}
			}
		});
		//action listener for item name
		cbItemName.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				cbItemType.setSelectedItem(items.get(cbItemName.getSelectedIndex()).getItemType());
				cbCharacterAttr.setSelectedItem(items.get(cbItemName.getSelectedIndex()).getEnhancedAttribute());
				cbBonusAmount.setSelectedItem(items.get(cbItemName.getSelectedIndex()).getBonusAmount());
				itemName = items.get(cbItemName.getSelectedIndex()).getName();
			}
		});
		
		//action listener for Add item
		btnAddItem.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				if (E_ItemEditorMode.Create == new_mode) {
					itemName = txtItemName.getText();
				}

				if (!itemName.equals("")) {
					WeaponType weaponType = null;
					if (cbItemType.getSelectedItem().toString().equals("WEAPON"))
						weaponType = (WeaponType)cbWeaponType.getSelectedItem();
					else
						weaponType = weaponType.NotAWeapon;
					
					item.setName(itemName);
					item.setItemType((ItemType) cbItemType.getSelectedItem());
					item.setWeaponType(weaponType);
					item.setCharacterAttribute((CharacterAttribute) cbCharacterAttr.getSelectedItem());
					item.setBonusAmount((Integer) cbBonusAmount.getSelectedItem());
					if (item.isValid()) {
						String msg = "";
						if (E_ItemEditorMode.Open == new_mode) {
							items.set(cbItemName.getSelectedIndex(), item);
							msg = new ItemIO().saveItems(items);
						}

						else {
							msg = new ItemIO().saveItem(item);
						}
						if (msg.contains(GameStatics.STATUS_SUCCESS)) {
							JOptionPane.showMessageDialog(null,
									String.format(GameStatics.MSG_ITEM_FILE_LOADED_SAVED, "saved"));
							if (E_ItemEditorMode.Create == new_mode) {
								txtItemName.setText("");
							}
						} else if(msg.contains(GameStatics.STATUS_EXIST)) {
							JOptionPane.showMessageDialog(null,
									String.format(GameStatics.MSG_DUPLICATE_NAME, "Item name"));
						} else {
							JOptionPane.showMessageDialog(null, msg);
						}
					}
				}
			}
		});

		return panel;
	}
}
