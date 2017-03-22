package soen.game.dd.gui.system;

import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;
import java.util.Observable;
import java.util.Observer;

import javax.swing.DefaultListModel;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JPanel;

import soen.game.dd.models.Character;
import soen.game.dd.models.Item;
import soen.game.dd.models.ItemType;

/**
 * This class create Inventory view
 * @author Usman
 *
 */
public class JFrameInventoryView extends javax.swing.JFrame implements Observer {

	private static final long serialVersionUID = -7056608434410809795L;
	Character character;
	JPanel panel;
	boolean enabled;
	
	/**
	 * this constructor initialize inventory view object
	 * 
	 * @param character
	 * @param enabled
	 */
	public JFrameInventoryView(Character character, boolean enabled){
		this.character = character;
		this.enabled = enabled;
		character.addObserver(this);
		initializeJPanel();
		initializeFrame();
		refreshJPanel();
	}
	
	/**
	 * this method initialize Jpanel
	 */
	public void initializeJPanel() {
		panel = new JPanel();
		panel.setLayout(null);
	}
	
	/**
	 * this method initialize Frame
	 */
	public void initializeFrame() {
		this.setTitle("Inventory for " + character.getName());
		this.setPreferredSize(new Dimension(400, 500));
		this.setMaximumSize(new Dimension(400, 500));
		this.setMinimumSize(new Dimension(400, 500));
		this.setResizable(false);
		this.setLocationRelativeTo(null);
		this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		this.setVisible(true);
		
		this.setContentPane(panel);
	}
	
	/**
	 * update is called when notifyobservers will call
	 */
	@Override
	public void update(Observable o, Object arg) {
		refreshJPanel();
	}
	
	/**
	 * the method return panel
	 * @return
	 */
	public JPanel getJPanel() {
		return this.panel;
	}
	
	/**
	 * the method refresh jpanel
	 * @return
	 */
	public void refreshJPanel() {

		panel.removeAll();
		panel.add(getEquipmentDropdown(ItemType.WEAPON, character.getWeapon(), 20));
		panel.add(getEquipmentDropdown(ItemType.HELMET, character.getHelmet(), 50));
		panel.add(getEquipmentDropdown(ItemType.SHIELD, character.getShield(), 80));
		panel.add(getEquipmentDropdown(ItemType.ARMOR, character.getarmor(), 110));
		panel.add(getEquipmentDropdown(ItemType.BOOTS, character.getBoots(), 140));
		panel.add(getEquipmentDropdown(ItemType.BELT, character.getBelt(), 170));
		panel.add(getEquipmentDropdown(ItemType.RING, character.getRing(), 200));
		
		panel.add(getBackpackList());
		panel.revalidate();
		panel.repaint();
	}
	
	/**
	 * the method return jpanel of getEquipmentDropdown
	 * @return
	 */
	private JPanel getEquipmentDropdown(ItemType itemtype, Item equippedItem, int yPos){
		JPanel subpanel = new JPanel();
		subpanel.setLayout(null);
		JLabel label = new JLabel(itemtype.toString());
		label.setBounds(0, 0, 125, 25);
		subpanel.add(label);
		
		List<ItemComboBox> selectableChoices = new ArrayList<ItemComboBox>();
		if (equippedItem != null){
			selectableChoices.add(new ItemComboBox(equippedItem));
		}
		for (Item item : character.getBackpack()){
			if (item.getItemType() == itemtype){
				selectableChoices.add(new ItemComboBox(item));
			}
		}
		JComboBox<ItemComboBox> jComboBox = new JComboBox<ItemComboBox>(selectableChoices.toArray(new ItemComboBox[selectableChoices.size()]));
		jComboBox.setBounds(150, 0, 120, 25);
		jComboBox.setEnabled(enabled);
		
		jComboBox.addActionListener(new EquipmentChangeActionListener());
		
		subpanel.add(jComboBox);

		subpanel.setBounds(40, yPos, 300, 30);
		return subpanel;
	}
	
	/**
	 * This method return backpack item
	 * @return
	 */
	private JList<ItemComboBox> getBackpackList(){
		DefaultListModel<ItemComboBox> backpackItems = new DefaultListModel<ItemComboBox>();
		for (Item item : character.getBackpack()){
			backpackItems.addElement(new ItemComboBox(item));
		}
		JList<ItemComboBox> backpack = new JList<ItemComboBox>(backpackItems);

		backpack.setBounds(40, 230, 300, 300);
		return backpack;
	}
	
	/**
	 * This class implements ActionListener for EquipmentChange
	 * @author Usman
	 *
	 */
	class EquipmentChangeActionListener implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent e) {
			@SuppressWarnings("unchecked")
			JComboBox<ItemComboBox> jComboBox = (JComboBox<ItemComboBox>) e.getSource();
			Item itemToEquip = ((ItemComboBox)jComboBox.getSelectedItem()).getValue();
			System.out.println(itemToEquip);
			ItemType itemType = itemToEquip.getItemType();
			switch(itemType){
			case HELMET:
				character.addItemIntoBackpack(character.getHelmet());
				character.setHelmet(itemToEquip);
				break;
			case ARMOR:
				character.addItemIntoBackpack(character.getarmor());
				character.setarmor(itemToEquip);
				break;
			case BELT:
				character.addItemIntoBackpack(character.getBelt());
				character.setBelt(itemToEquip);
				break;
			case BOOTS:
				character.addItemIntoBackpack(character.getBoots());
				character.setBoots(itemToEquip);
				break;
			case RING:
				character.addItemIntoBackpack(character.getRing());
				character.setRing(itemToEquip);
				break;
			case SHIELD:
				character.addItemIntoBackpack(character.getShield());
				character.setShield(itemToEquip);
				break;
			case WEAPON:
				character.addItemIntoBackpack(character.getWeapon());
				character.setWeapon(itemToEquip);
				break;
			}
			character.removeItemFromBackpack(itemToEquip);
			character.notifyObservers();
		}
		
	}
	
	/**
	 * This class is for ItemcomboBox
	 * @author Usman
	 *
	 */
	class ItemComboBox {
		
		private Item value;
		private String label;

		public ItemComboBox(Item value) {
			this.value = value;
			this.label = value.getName();
		}

		public Item getValue() {
			return this.value;
		}

		public String getLabel() {
			return this.label;
		}

		@Override
		public String toString() {
			return label;
		}
		
	}

	
}
