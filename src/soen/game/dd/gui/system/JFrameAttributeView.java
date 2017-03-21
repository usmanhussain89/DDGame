package soen.game.dd.gui.system;

import java.awt.Dimension;
import java.util.Observable;
import java.util.Observer;

import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

import soen.game.dd.models.Character;

public class JFrameAttributeView extends JFrame implements Observer {

	private static final long serialVersionUID = 8826936854256209812L;
	Character character;
	JPanel panel;
	
	public JFrameAttributeView(Character character){
		this.character = character;
		character.addObserver(this);
		initializeJPanel();
		initializeFrame();
		refreshJPanel();
	}
	
	public void initializeJPanel() {
		panel = new JPanel();
		panel.setLayout(null);
	}
	
	public void initializeFrame() {
		this.setTitle("Attributes for " + character.getName());
		this.setPreferredSize(new Dimension(200, 170));
		this.setMaximumSize(new Dimension(200, 170));
		this.setMinimumSize(new Dimension(200, 170));
		this.setResizable(false);
		this.setLocationRelativeTo(null);
		this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		this.setVisible(true);
		
		this.setContentPane(panel);
	}
	
	public JPanel getJPanel() {
		return this.panel;
	}
	
	public void refreshJPanel() {

		panel.removeAll();
		panel.add(getAttrLabel("Strength", character.getStrength(), 20));
		panel.add(getAttrLabel("Constitution", character.getConsitution(), 40));
		panel.add(getAttrLabel("Wisdom", character.getWisdom(), 60));
		panel.add(getAttrLabel("Dexterity", character.getDexterity(), 80));
		panel.add(getAttrLabel("Intelligence", character.getIntelligence(), 100));
		panel.add(getAttrLabel("Charisma", character.getCharisma(), 120));
		
		panel.repaint();
	}
	
	private JLabel getAttrLabel(String attrName, int attrVal, int yPos){
		JLabel label = new JLabel(attrName + ": " + attrVal);
		label.setBounds(30, yPos, 125, 25);
		return label;
	}

	@Override
	public void update(Observable o, Object arg) {
		System.out.println("Notified");
		refreshJPanel();
	}
	
}
