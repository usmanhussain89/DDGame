package soen.game.dd.gui.components;

import java.awt.Container;
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
import javax.swing.JTextField;

import soen.game.dd.fileio.CharacterIO;
import soen.game.dd.fileio.ItemIO;
import soen.game.dd.gui.system.JFrameAttributeView;
import soen.game.dd.gui.system.JFrameInventoryView;
import soen.game.dd.models.BullyCharacterBuilder;
import soen.game.dd.models.Character;
import soen.game.dd.models.CharacterBuilder;
import soen.game.dd.models.Fighter;
import soen.game.dd.models.FighterType;
import soen.game.dd.models.Item;
import soen.game.dd.models.ItemType;
import soen.game.dd.models.NimbleCharacterBuilder;
import soen.game.dd.models.TankCharacterBuilder;
import soen.game.dd.statics.content.GameStatics;
import soen.game.dd.statics.content.GameEnums.E_CharacterEditorMode;

/**
 * This class create Jpanel for Character Component
 * 
 * @author Usman
 *
 */
public class JPanelCharacterComponent {

	String characterName = "";
	Character character;

	public Container getCharacterEditorGridPanel(Character pCharacter, E_CharacterEditorMode characterEditorMode,
			JFrame frame, ArrayList<Character> characters) {
		JPanel panel;
		character = pCharacter;
		ArrayList<Item> items = new ItemIO().loadItems();

		/**
		 * This class is used to set ComboItem
		 * 
		 * @author Usman
		 *
		 */
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

		/**
		 * This class generate Comboitem list for generator
		 * 
		 * @author Usman
		 *
		 */
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
		lblCharacterName.setBounds(40, 25, 100, 25);
		panel.add(lblCharacterName);

		JTextField txtCharacterName = new JTextField(30);
		txtCharacterName.setBounds(160, 25, 150, 25);
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

		JLabel lblLevel = new JLabel("Level: ");
		lblLevel.setBounds(40, 50, 100, 25);
		panel.add(lblLevel);

		JTextField txtLevel = new JTextField();
		txtLevel.setBounds(170, 50, 100, 25);
		panel.add(txtLevel);

		JLabel lblFighterType = new JLabel("Fighter Type: ");
		lblFighterType.setBounds(40, 75, 100, 25);
		panel.add(lblFighterType);

		JComboBox<FighterType> cbFighterType = new JComboBox<FighterType>(FighterType.values());
		cbFighterType.setBounds(170, 75, 120, 25);
		panel.add(cbFighterType);

		JLabel lblSelectHelmet = new JLabel("Helmet: ");
		lblSelectHelmet.setBounds(40, 100, 100, 25);
		panel.add(lblSelectHelmet);

		JComboBox<ItemComboItem> cbHelmet = new JComboBox<ItemComboItem>(
				new ComboItemListGenerator(ItemType.HELMET).generateList());
		cbHelmet.setBounds(170, 100, 120, 25);
		panel.add(cbHelmet);

		JLabel lblSelectArmor = new JLabel("Armor: ");
		lblSelectArmor.setBounds(40, 125, 100, 25);
		panel.add(lblSelectArmor);

		JComboBox<ItemComboItem> cbArmor = new JComboBox<ItemComboItem>(
				new ComboItemListGenerator(ItemType.ARMOR).generateList());
		cbArmor.setBounds(170, 125, 120, 25);
		panel.add(cbArmor);

		JButton attributeViewer = new JButton("Attribute Viewer");
		attributeViewer.setBounds(300, 125, 200, 25);
		panel.add(attributeViewer);

		attributeViewer.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				new JFrameAttributeView(character);
			}

		});

		JLabel lblSelectShield = new JLabel("Shield: ");
		lblSelectShield.setBounds(40, 150, 100, 25);
		panel.add(lblSelectShield);

		JComboBox<ItemComboItem> cbShield = new JComboBox<ItemComboItem>(
				new ComboItemListGenerator(ItemType.SHIELD).generateList());
		cbShield.setBounds(170, 150, 120, 25);
		panel.add(cbShield);

		JButton inventoryViewer = new JButton("Inventory Viewer");
		inventoryViewer.setBounds(300, 150, 200, 25);
		panel.add(inventoryViewer);

		inventoryViewer.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				new JFrameInventoryView(character, true);
			}

		});

		JLabel lblSelectRing = new JLabel("Ring: ");
		lblSelectRing.setBounds(40, 175, 100, 25);
		panel.add(lblSelectRing);

		JComboBox<ItemComboItem> cbRing = new JComboBox<ItemComboItem>(
				new ComboItemListGenerator(ItemType.RING).generateList());
		cbRing.setBounds(170, 175, 120, 25);
		panel.add(cbRing);

		JLabel lblSelectBelt = new JLabel("Belt: ");
		lblSelectBelt.setBounds(40, 200, 100, 25);
		panel.add(lblSelectBelt);

		JComboBox<ItemComboItem> cbBelt = new JComboBox<ItemComboItem>(
				new ComboItemListGenerator(ItemType.BELT).generateList());
		cbBelt.setBounds(170, 200, 120, 25);
		panel.add(cbBelt);

		JLabel lblSelectBoots = new JLabel("Boots: ");
		lblSelectBoots.setBounds(40, 225, 100, 25);
		panel.add(lblSelectBoots);

		JComboBox<ItemComboItem> cbBoots = new JComboBox<ItemComboItem>(
				new ComboItemListGenerator(ItemType.BOOTS).generateList());
		cbBoots.setBounds(170, 225, 120, 25);
		panel.add(cbBoots);

		JLabel lblSelectWeapon = new JLabel("Weapon: ");
		lblSelectWeapon.setBounds(40, 250, 100, 25);
		panel.add(lblSelectWeapon);

		JComboBox<ItemComboItem> cbWeapon = new JComboBox<ItemComboItem>(
				new ComboItemListGenerator(ItemType.WEAPON).generateList());
		cbWeapon.setBounds(170, 250, 120, 25);
		panel.add(cbWeapon);

		// Items

		JLabel lblSelectItem = new JLabel("Backpack: ");
		lblSelectItem.setBounds(40, 275, 100, 25);
		panel.add(lblSelectItem);

		List backpack_view = new List();
		backpack_view.setBounds(170, 275, 150, 150);
		panel.add(backpack_view);

		JComboBox<ItemComboItem> cbBackpack = new JComboBox<ItemComboItem>(
				new ComboItemListGenerator(null).generateList());
		cbBackpack.setBounds(40, 450, 120, 25);
		panel.add(cbBackpack);

		JButton btnAddItem = new JButton("Add item");
		btnAddItem.setBounds(170, 450, 90, 25);
		panel.add(btnAddItem);

		JButton btnRemoveItem = new JButton("Remove item");
		btnRemoveItem.setBounds(250, 450, 130, 25);
		panel.add(btnRemoveItem);

		JButton btnSave = new JButton("Save");
		btnSave.setBounds(40, 500, 100, 25);
		panel.add(btnSave);

		/**
		 * This class Action listener for character loading
		 * 
		 * @author Usman
		 *
		 */
		class CharacterLoader implements ActionListener {

			/**
			 * This method load Character
			 */
			public void load() {
				character = characters.get(cbCharacterName.getSelectedIndex());
				txtLevel.setText(String.valueOf(character.getLevel()));
				setSelectedItem(cbHelmet, character.getHelmet());
				setSelectedItem(cbArmor, character.getarmor());
				setSelectedItem(cbBoots, character.getBoots());
				setSelectedItem(cbBelt, character.getBelt());
				setSelectedItem(cbRing, character.getRing());
				setSelectedItem(cbShield, character.getShield());
				backpack_view.removeAll();
				for (Item item : character.getBackpack()) {
					backpack_view.add(item.getName());
				}
				characterName = character.getName();
			}

			// Action Performed
			@Override
			public void actionPerformed(ActionEvent e) {
				load();
			}

			/**
			 * This method set selected item
			 * 
			 * @param comboBox
			 * @param item
			 */
			private void setSelectedItem(JComboBox<ItemComboItem> comboBox, Item item) {
				for (int i = 0; i < comboBox.getItemCount(); ++i) {
					if (comboBox.getItemAt(i).getValue().getName().equals(item.getName())) {
						comboBox.setSelectedIndex(i);
					}
				}
			}

		}
		;

		if (E_CharacterEditorMode.Open == characterEditorMode) {
			new CharacterLoader().load();
		}

		/**
		 * This class implement ActionListener for PanelAction
		 * 
		 * @author Usman
		 *
		 */
		class PanelAction implements ActionListener {
			@Override
			public void actionPerformed(ActionEvent e) {

				if (e.getSource().equals(btnAddItem)) {
					Item item = getItemFrom(cbBackpack);
					if (character.addItemIntoBackpack(item)) {
						backpack_view.add(item.getName());
					}
				}

				else if (e.getSource().equals(btnRemoveItem)) {
					System.out.println(backpack_view.getSelectedIndex());
					if (backpack_view.getSelectedIndex() >= 0) {
						int index = backpack_view.getSelectedIndex();
						character.removeItemNameFromBackpack(backpack_view.getItem(index));
						backpack_view.remove(index);
					}
				}
			}

			private Item getItemFrom(JComboBox<ItemComboItem> comboBox) {
				if (comboBox.getSelectedItem() != null) {
					return ((ItemComboItem) comboBox.getSelectedItem()).getValue();
				} else {
					return null;
				}
			}

		}

		btnAddItem.addActionListener(new PanelAction());
		btnRemoveItem.addActionListener(new PanelAction());

		cbCharacterName.addActionListener(new CharacterLoader());

		btnSave.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				String msg = "";
				if (E_CharacterEditorMode.Create == characterEditorMode) {
					characterName = txtCharacterName.getText();
				}

				if (!characterName.equals("")) {
					character.setName(characterName);
					character.setLevel(Integer.parseInt(txtLevel.getText()));
					character.setHelmet(getItemFrom(cbHelmet));
					character.setarmor(getItemFrom(cbArmor));
					character.setBoots(getItemFrom(cbBoots));
					character.setBelt(getItemFrom(cbBelt));
					character.setRing(getItemFrom(cbRing));
					character.setShield(getItemFrom(cbShield));
					character.setWeapon(getItemFrom(cbWeapon));
					character.setMaxHitPoint();
					character.setHitpoint(character.getMaxHitPoint());

					if (cbFighterType.getSelectedItem() != null) {
						Fighter fighter = new Fighter();
						// Design builder implmentation heres
						CharacterBuilder characterBuilder = null;
						if (cbFighterType.getSelectedItem() == FighterType.BULLY) {
							characterBuilder = new BullyCharacterBuilder();
						}
						if (cbFighterType.getSelectedItem() == FighterType.NIMBLE) {
							characterBuilder = new NimbleCharacterBuilder();
						}
						if (cbFighterType.getSelectedItem() == FighterType.TANK) {
							characterBuilder = new TankCharacterBuilder();
						}
						fighter.setCharacterBuilder(characterBuilder);
						fighter.createFighter(character);
						character = fighter.getCharacter();
					}
					character.notifyObservers();

					System.out.println(character);
					if (E_CharacterEditorMode.Create == characterEditorMode) {
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
					} else if (msg.contains(GameStatics.STATUS_EXIST)) {
						JOptionPane.showMessageDialog(null,
								String.format(GameStatics.MSG_DUPLICATE_NAME, "Character name"));
					} else {
						System.out.println(msg);
						JOptionPane.showMessageDialog(null, msg);
					}
				}
			}

			private Item getItemFrom(JComboBox<ItemComboItem> comboBox) {
				if (comboBox.getSelectedItem() != null) {
					return ((ItemComboItem) comboBox.getSelectedItem()).getValue();
				} else {
					return null;
				}
			}
		});

		return panel;
	}
}
