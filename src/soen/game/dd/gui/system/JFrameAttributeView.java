package soen.game.dd.gui.system;

import java.awt.Dimension;
import java.util.Observable;
import java.util.Observer;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

import soen.game.dd.models.Character;

/**
 * This class create Attribute view of ability score
 * @author Usman
 *
 */
public class JFrameAttributeView extends JFrame implements Observer {

	private static final long serialVersionUID = 8826936854256209812L;
	Character character;
	JPanel panel;
	
	/**
	 * this constructor initialize JFrameAttributeView
	 * @param character
	 */
	public JFrameAttributeView(Character character){
		this.character = character;
		character.addObserver(this);
		initializeJPanel();
		initializeFrame();
		refreshJPanel();
	}
	
	/**
	 * this method initialize jpanel
	 */
	public void initializeJPanel() {
		panel = new JPanel();
		panel.setLayout(null);
	}
	
	/**
	 * this method initialize frame
	 */
	public void initializeFrame() {
		this.setTitle("Attributes for " + character.getName());
		this.setPreferredSize(new Dimension(200, 200));
		this.setMaximumSize(new Dimension(200, 200));
		this.setMinimumSize(new Dimension(200, 200));
		this.setResizable(false);
		this.setLocationRelativeTo(null);
		this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		this.setVisible(true);
		
		this.setContentPane(panel);
	}
	
	/**
	 * the method return jpanel
	 * @return
	 */
	public JPanel getJPanel() {
		return this.panel;
	}
	
	/**
	 * This method refresh jpanel 
	 */
	public void refreshJPanel() {
		
		panel.removeAll();
		panel.add(getAttrLabel("HP", "" + character.getHitPoint() + "/" + character.getMaxHitPoint(), 20));
		panel.add(getAttrLabel("Strength", character.getStrength(), 40));
		panel.add(getAttrLabel("Constitution", character.getConsitution(), 60));
		panel.add(getAttrLabel("Wisdom", character.getWisdom(), 80));
		panel.add(getAttrLabel("Dexterity", character.getDexterity(), 100));
		panel.add(getAttrLabel("Intelligence", character.getIntelligence(), 120));
		panel.add(getAttrLabel("Charisma", character.getCharisma(), 140));
		if (character.getCharacterStatus() != null){
			panel.add(getAttrLabel("Status Effect", character.getCharacterStatus().toString(), 160));
		}
		
		panel.repaint();
	}
	
	/**
	 * this method return attribute label
	 * @param attrName
	 * @param attrVal
	 * @param yPos
	 * @return
	 */
	private JLabel getAttrLabel(String attrName, String attrVal, int yPos){
		JLabel label = new JLabel(attrName + ": " + attrVal);
		label.setBounds(30, yPos, 125, 25);
		return label;
	}
	
	private JLabel getAttrLabel(String attrName, int attrVal, int yPos){
		return getAttrLabel(attrName, "" + attrVal, yPos);
	}
	
	/**
	 * update is called when notifyobservers will call
	 */
	@Override
	public void update(Observable o, Object arg) {
		System.out.println("Notified");
		refreshJPanel();
	}
	
}
