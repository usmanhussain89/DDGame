package soen.game.dd.gui.components;

import java.awt.Container;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;

import soen.game.dd.fileio.CharacterIO;
import soen.game.dd.fileio.ItemIO;
import soen.game.dd.models.Character;
import soen.game.dd.models.Item;
import soen.game.dd.models.ItemType;
import soen.game.dd.statics.content.GameStatics;
import soen.game.dd.statics.content.GameEnums.E_CharacterEditorMode;

public class JPanelCharacterComponent {
	
	String characterName = "";
	
	public Container getCharacterEditorGridPanel(Character pCharacter, E_CharacterEditorMode characterEditorMode,
			JFrame frame, ArrayList<Character> characters) {
		JPanel panel;
		Character character = pCharacter;
		ArrayList<Item> items = new ItemIO().loadItems();

		class ItemComboItem {
			private Item value;
			private String label;

			public ItemComboItem(Item value) {
				this.value = value;
				this.label = value.getName();
			}

			public Item getValue() {
				return this.value;
			}

			@SuppressWarnings("unused")
			public String getLabel() {
				return this.label;
			}

			@Override
			public String toString() {
				return label;
			}
		}

		class ComboItemListGenerator {
			private ItemType itemType;

			public ComboItemListGenerator(ItemType type) {
				this.itemType = type;
			}

			public ItemComboItem[] generateList() {
				ArrayList<ItemComboItem> combo_items = new ArrayList<ItemComboItem>();
				if (itemType == null) {
					for (Item i : items) {
						combo_items.add(new ItemComboItem(i));
					}
				} else {
					for (Item i : items) {
						if (i.getItemType() == itemType) {
							combo_items.add(new ItemComboItem(i));
						}
					}
				}
				return combo_items.toArray(new ItemComboItem[combo_items.size()]);
			}
		}

		panel = new JPanel();
		panel.setLayout(null);

		JLabel lblCharacterName = new JLabel("Character Name: ");
		lblCharacterName.setBounds(40, 30, 100, 25);
		panel.add(lblCharacterName);

		JTextField txtCharacterName = new JTextField(30);
		txtCharacterName.setBounds(160, 30, 150, 25);
		panel.add(txtCharacterName);
		
		JComboBox<String> cbCharacterName = new JComboBox<String>();
		cbCharacterName.setBounds(170, 30, 120, 25);
		cbCharacterName.setEditable(true);
		cbCharacterName.setVisible(false);
		if (E_CharacterEditorMode.Open == characterEditorMode) {
			txtCharacterName.setVisible(false);
			cbCharacterName.setVisible(true);
			int index = 0;

			for (Character i : characters) {
				if (i != null) {
					cbCharacterName.addItem(i.getName());
					cbCharacterName.setName("" + index);
					index++;
				}
			}

			panel.add(cbCharacterName);
		}

		else {
			txtCharacterName.setVisible(true);
			cbCharacterName.setVisible(false);

			panel.add(txtCharacterName);
		}

		JLabel lblSelectHelmet = new JLabel("Helmet: ");
		lblSelectHelmet.setBounds(40, 75, 100, 25);
		panel.add(lblSelectHelmet);

		JComboBox<ItemComboItem> cbHelmet = new JComboBox<ItemComboItem>(
				new ComboItemListGenerator(ItemType.HELMET).generateList());
		cbHelmet.setBounds(170, 75, 120, 25);
		panel.add(cbHelmet);

		JLabel lblSelectArmor = new JLabel("Armor: ");
		lblSelectArmor.setBounds(40, 100, 100, 25);
		panel.add(lblSelectArmor);

		JComboBox<ItemComboItem> cbArmor = new JComboBox<ItemComboItem>(
				new ComboItemListGenerator(ItemType.ARMOR).generateList());
		cbArmor.setBounds(170, 100, 120, 25);
		panel.add(cbArmor);

		JLabel lblSelectShield = new JLabel("Shield: ");
		lblSelectShield.setBounds(40, 125, 100, 25);
		panel.add(lblSelectShield);

		JComboBox<ItemComboItem> cbShield = new JComboBox<ItemComboItem>(
				new ComboItemListGenerator(ItemType.SHIELD).generateList());
		cbShield.setBounds(170, 125, 120, 25);
		panel.add(cbShield);

		JLabel lblSelectRing = new JLabel("Ring: ");
		lblSelectRing.setBounds(40, 150, 100, 25);
		panel.add(lblSelectRing);

		JComboBox<ItemComboItem> cbRing = new JComboBox<ItemComboItem>(
				new ComboItemListGenerator(ItemType.RING).generateList());
		cbRing.setBounds(170, 150, 120, 25);
		panel.add(cbRing);

		JLabel lblSelectBelt = new JLabel("Belt: ");
		lblSelectBelt.setBounds(40, 175, 100, 25);
		panel.add(lblSelectBelt);

		JComboBox<ItemComboItem> cbBelt = new JComboBox<ItemComboItem>(
				new ComboItemListGenerator(ItemType.BELT).generateList());
		cbBelt.setBounds(170, 175, 120, 25);
		panel.add(cbBelt);

		JLabel lblSelectBoots = new JLabel("Boots: ");
		lblSelectBoots.setBounds(40, 200, 100, 25);
		panel.add(lblSelectBoots);

		JComboBox<ItemComboItem> cbBoots = new JComboBox<ItemComboItem>(
				new ComboItemListGenerator(ItemType.BOOTS).generateList());
		cbBoots.setBounds(170, 200, 120, 25);
		panel.add(cbBoots);

		JLabel lblSelectWeapon = new JLabel("Weapon: ");
		lblSelectWeapon.setBounds(40, 225, 100, 25);
		panel.add(lblSelectWeapon);

		JComboBox<ItemComboItem> cbWeapon = new JComboBox<ItemComboItem>(
				new ComboItemListGenerator(ItemType.WEAPON).generateList());
		cbWeapon.setBounds(170, 225, 120, 25);
		panel.add(cbWeapon);

		// Items

		ArrayList<JComboBox<ItemComboItem>> backpack = new ArrayList<JComboBox<ItemComboItem>>();
		for (int i = 0; i < 10; ++i) {
			JLabel lblSelectItem1 = new JLabel("Item " + i + ": ");
			lblSelectItem1.setBounds(40, 250 + i * 25, 100, 25);
			panel.add(lblSelectItem1);

			JComboBox<ItemComboItem> combobox = new JComboBox<ItemComboItem>(
					new ComboItemListGenerator(null).generateList());
			combobox.setBounds(170, 250 + i * 25, 120, 25);
			panel.add(combobox);

			backpack.add(combobox);
		}

		JButton btnSave = new JButton("Save");
		btnSave.setBounds(40, 525, 100, 25);
		panel.add(btnSave);
		
		if (E_CharacterEditorMode.Open == characterEditorMode) {
			Character character1 = characters.get(cbCharacterName.getSelectedIndex());
			cbHelmet.setSelectedItem(character1.getHelmet());
			cbArmor.setSelectedItem(character1.getarmor());
			cbBoots.setSelectedItem(character1.getBoots());
			cbBelt.setSelectedItem(character1.getBelt());
			cbRing.setSelectedItem(character1.getRing());
			cbShield.setSelectedItem(character1.getShield());
			for(int i = 0; i < 10; ++i){
				backpack.get(i).setSelectedItem(character1.getItemIntoBackpack().get(i));
			}
			characterName = character1.getName();
		}

		
		cbCharacterName.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				System.out.println(getCharacter());
				cbHelmet.setSelectedItem(getCharacter().getHelmet());
				cbArmor.setSelectedItem(getCharacter().getarmor());
				cbBoots.setSelectedItem(getCharacter().getBoots());
				cbBelt.setSelectedItem(getCharacter().getBelt());
				cbRing.setSelectedItem(getCharacter().getRing());
				cbShield.setSelectedItem(getCharacter().getShield());
				for(int i = 0; i < 10; ++i){
					System.out.println(getCharacter().getItemIntoBackpack().get(i));
					backpack.get(i).setSelectedItem(getCharacter().getItemIntoBackpack().get(i));
				}
				characterName = getCharacter().getName();
			}
			
			private Character getCharacter() {
				return characters.get(cbCharacterName.getSelectedIndex());
			}
		});

		btnSave.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				String msg = "";
				if (E_CharacterEditorMode.Create == characterEditorMode) {
					characterName = txtCharacterName.getText();
				}

				System.out.println(((ItemComboItem)cbRing.getSelectedItem()).getValue());
				if (!characterName.equals("")) {
					character.setName(characterName);
					character.setHelmet(getItemFrom(cbHelmet));
					character.setarmor(getItemFrom(cbArmor));
					character.setBoots(getItemFrom(cbBoots));
					character.setBelt(getItemFrom(cbBelt));
					character.setRing(getItemFrom(cbRing));
					character.setShield(getItemFrom(cbShield));
					for (JComboBox<ItemComboItem> backpackItem : backpack){
						character.addItemIntoBackpack(getItemFrom(backpackItem));
					}
					System.out.println(character);
					if (E_CharacterEditorMode.Create == characterEditorMode){
						msg = new CharacterIO().saveCharacter(character);
					} else {
						characters.set(cbCharacterName.getSelectedIndex(), character);
						msg = new CharacterIO().saveCharacters(characters);
					}
					if (msg.contains(GameStatics.STATUS_SUCCESS)) {
					JOptionPane.showMessageDialog(null,
							String.format(GameStatics.MSG_CHARACTER_FILE_LOADED_SAVED, "saved"));
						if (E_CharacterEditorMode.Create == characterEditorMode) {
							txtCharacterName.setText("");
						} 
					} else if(msg.contains(GameStatics.STATUS_EXIST)) {
					JOptionPane.showMessageDialog(null,
							String.format(GameStatics.MSG_DUPLICATE_NAME, "Character name"));
					} else {
						System.out.println(msg);
						JOptionPane.showMessageDialog(null, msg);
					}
				}
			}
			
			private Item getItemFrom(JComboBox<ItemComboItem> comboBox){
				if (comboBox.getSelectedItem() != null){
					return ((ItemComboItem)comboBox.getSelectedItem()).getValue();
				} else {
					return null;
				}
			}
		});
		
		return panel;
	}
}
