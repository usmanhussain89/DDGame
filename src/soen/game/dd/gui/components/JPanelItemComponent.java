package soen.game.dd.gui.components;

import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JCheckBox;
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
import soen.game.dd.weapon.enchantments.BurningDecorator;
import soen.game.dd.weapon.enchantments.EnchantmentTypes;
import soen.game.dd.weapon.enchantments.FreezingDecorator;
import soen.game.dd.weapon.enchantments.FrighteningDecorator;
import soen.game.dd.weapon.enchantments.PacifyingDecorator;
import soen.game.dd.weapon.enchantments.SlayingDecorator;
import soen.game.dd.weapon.enchantments.Weapon;
import soen.game.dd.weapon.enchantments.WeaponBasic;
import soen.game.dd.statics.content.GameEnums.E_ItemEditorMode;

/**
 * This class create jpanel for Items
 * 
 * @author Usman
 *
 */
public class JPanelItemComponent {

	String itemName = "";
	private E_ItemEditorMode itemEditorMode;
	List<Item> items;
	Item item;
	Weapon weapon;

	/**
	 * This method create the JPanel for the Item Editor and return JPanel which
	 * set Content Pane for the frame
	 * 
	 * @param new_mode
	 * 
	 * @return JPanel
	 */
	public JPanel getItemEditorGridPanel(Item pItem, Dimension new_parentDimension, E_ItemEditorMode new_mode) {
		item = pItem;
		items = new ItemIO().loadItems();
		itemEditorMode = new_mode;
		JPanel panel;

		panel = new JPanel();
		panel.setLayout(null);

		JLabel lblItemName = new JLabel("Item Name: ");
		lblItemName.setBounds(40, 30, 80, 25);
		panel.add(lblItemName);

		JTextField txtItemName = new JTextField(40);
		txtItemName.setBounds(170, 30, 120, 25);
		panel.add(txtItemName);
		if (itemEditorMode == E_ItemEditorMode.Open) {
			txtItemName.setEditable(false);
			txtItemName.setText(item.getName());
		}

		JLabel lblItemType = new JLabel("Item Type: " + item.getItemType().toString());
		lblItemType.setBounds(40, 75, 200, 25);
		panel.add(lblItemType);

		JComboBox cbWeaponType = new JComboBox();

		JLabel lblCharacterAttr = new JLabel("Character Attribute: ");
		lblCharacterAttr.setBounds(40, 120, 120, 25);
		panel.add(lblCharacterAttr);

		JComboBox cbCharacterAttr = new JComboBox(item.getItemType().getAllowedAttributes().toArray());
		cbCharacterAttr.setBounds(170, 120, 120, 25);
		panel.add(cbCharacterAttr);

		JLabel lblBonusAmount = new JLabel("Bonus Amount: ");
		lblBonusAmount.setBounds(40, 165, 100, 25);
		panel.add(lblBonusAmount);

		Integer[] bonuses = new Integer[] { 1, 2, 3, 4, 5 };
		JComboBox cbBonusAmount = new JComboBox(bonuses);
		cbBonusAmount.setBounds(170, 165, 100, 25);
		panel.add(cbBonusAmount);
		JCheckBox cbFreezing = new JCheckBox("Freezing");
		JCheckBox cbBurning = new JCheckBox("Burning");
		JCheckBox cbPacifying = new JCheckBox("Pacifying");
		JCheckBox cbSlaying = new JCheckBox("Slaying");
		JCheckBox cbFrightening = new JCheckBox("Frightening");

		if (item.getItemType() == ItemType.WEAPON) {

			Weapon weapon = (Weapon) item;

			JLabel lblWeaponType = new JLabel("Weapon Type :");
			lblWeaponType.setBounds(40, 210, 90, 25);
			panel.add(lblWeaponType);

			cbWeaponType.addItem(WeaponType.MELEE);
			cbWeaponType.addItem(WeaponType.RANGED);
			cbWeaponType.setBounds(170, 210, 120, 25);
			panel.add(cbWeaponType);

			cbFreezing.setBounds(40, 240, 100, 25);
			panel.add(cbFreezing);
			if (weapon.getEnchantments().indexOf(EnchantmentTypes.Freezing) >= 0) {
				cbFreezing.setSelected(true);
			}

			cbBurning.setBounds(170, 240, 100, 25);
			panel.add(cbBurning);
			if (weapon.getEnchantments().indexOf(EnchantmentTypes.Burning) >= 0) {
				cbFreezing.setSelected(true);
			}

			cbPacifying.setBounds(300, 240, 100, 25);
			panel.add(cbPacifying);
			if (weapon.getEnchantments().indexOf(EnchantmentTypes.Pacifying) >= 0) {
				cbFreezing.setSelected(true);
			}

			cbSlaying.setBounds(40, 270, 100, 25);
			panel.add(cbSlaying);
			if (weapon.getEnchantments().indexOf(EnchantmentTypes.Slaying) >= 0) {
				cbFreezing.setSelected(true);
			}

			cbFrightening.setBounds(170, 270, 100, 25);
			panel.add(cbFrightening);
			if (weapon.getEnchantments().indexOf(EnchantmentTypes.Frightening) >= 0) {
				cbFreezing.setSelected(true);
			}
		}

		JButton btnAddItem = new JButton("Save Item");
		btnAddItem.setBounds(40, 300, 120, 25);
		panel.add(btnAddItem);

		cbCharacterAttr.setSelectedItem(item.getEnhancedAttribute());
		cbBonusAmount.setSelectedItem(item.getBonusAmount());
		itemName = item.getName();

		// action listener for Add item
		btnAddItem.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				if (E_ItemEditorMode.Create == new_mode) {
					itemName = txtItemName.getText();
				}

				if (!itemName.equals("")) {
					WeaponType weaponType = null;
					if (item.getItemType() == ItemType.WEAPON) {
						Weapon weaponBasic = (WeaponBasic) item;
						if (cbFrightening.isSelected()) {
							weaponBasic = new FrighteningDecorator(weaponBasic);
						}
						if (cbSlaying.isSelected()) {
							weaponBasic = new SlayingDecorator(weaponBasic);
						}
						if (cbPacifying.isSelected()) {
							weaponBasic = new PacifyingDecorator(weaponBasic);
						}
						if (cbBurning.isSelected()) {
							weaponBasic = new BurningDecorator(weaponBasic);
						}
						if (cbFreezing.isSelected()) {
							weaponBasic = new FreezingDecorator(weaponBasic);
						}
						weaponBasic.setItemType(ItemType.WEAPON);
						item = weaponBasic;
						weaponType = (WeaponType) cbWeaponType.getSelectedItem();
					} else
						weaponType = WeaponType.NotAWeapon;

					item.setName(itemName);
					item.setWeaponType(weaponType);
					item.setCharacterAttribute((CharacterAttribute) cbCharacterAttr.getSelectedItem());
					item.setBonusAmount((Integer) cbBonusAmount.getSelectedItem());
					if (item.isValid()) {
						String msg = "";
						if (E_ItemEditorMode.Open == new_mode) {
							for (int i = 0; i < items.size(); ++i) {
								if (items.get(i).getName() == item.getName()) {
									items.set(i, item);
									break;
								}
							}
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
						} else if (msg.contains(GameStatics.STATUS_EXIST)) {
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
