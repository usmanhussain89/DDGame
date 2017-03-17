package soen.game.dd.gui.system;

import java.awt.Dimension;
import java.awt.Point;
import java.util.ArrayList;

import javax.swing.JFrame;

import soen.game.dd.gui.components.JMenuBarComponent;
import soen.game.dd.gui.components.JPanelComponent;
import soen.game.dd.models.Map;
import soen.game.dd.models.Character;
import soen.game.dd.statics.content.GameStatics;
import soen.game.dd.statics.content.GameEnums.E_CharacterEditorMode;

/**
 * This is Map Editor class
 * 
 * @author Khaled
 *
 */

public class CharacterEditor extends JFrame {

	private static final long serialVersionUID = -1931373119455341534L;
	Character character;
	E_CharacterEditorMode characterEditorMode;// enum

	/**
	 * This is the constructor of the class Initialize the new frame for the Map
	 * Editor
	 * 
	 * @param frame
	 * @param title
	 * @param width
	 * @param height
	 * @param map
	 * @param characterEditorMode
	 */
	public CharacterEditor(JFrame frame, String title, int width, int height,
			E_CharacterEditorMode characterEditorMode, ArrayList<Character> characters) {
		if (frame != null) {
			Dimension frameSize = frame.getSize();
			Point p = frame.getLocation();
			setLocation(p.x + frameSize.width / 4, p.y + frameSize.height / 4);
		}

		this.character = new Character();
		this.characterEditorMode = characterEditorMode;

		if (E_CharacterEditorMode.Create == this.characterEditorMode) {
			title += " " + GameStatics.CHARACTER_MODE_CREATE;
		} else {
			title += " " + GameStatics.CHARACTER_MODE_OPEN;
		}

		// --- Set Map Editor Windows Properties
		this.setTitle(title);
		this.setPreferredSize(new Dimension(width, height));
		this.setMaximumSize(new Dimension(width, height));
		this.setMinimumSize(new Dimension(width, height));
		this.setResizable(false);
		this.setLocationRelativeTo(null);
		this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		this.setVisible(true);

		// MenuBar for Map Editor
		this.setJMenuBar((new JMenuBarComponent()).getCharacterEditorJMenuBar(character, this, characterEditorMode));

		// load Map Grid from Component
		this.setContentPane((new JPanelComponent()).getCharacterEditorGridPanel(character, characterEditorMode, frame, characters));
	}
}