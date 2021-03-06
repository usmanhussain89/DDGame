package soen.game.dd.gui.components;

import java.io.File;

import javax.swing.JFileChooser;
import javax.swing.filechooser.FileNameExtensionFilter;

import soen.game.dd.statics.content.GameEnums.E_JFileChooserMode;

/**
 * This class allows us to select a file from system.
 * 
 * @author Usman
 * 
 */
public class JFileChooserComponent {

	private JFileChooser fileChooser;

	/**
	 * The Method gives you JFileChooser Save or Open mode according to provided
	 * input.
	 * 
	 * @param new_fileChooserMode
	 *            the mode of a file open and on the base of this argument it
	 *            behave accordingly.
	 * @return a file
	 */
	public JFileChooser getJFileChooser(E_JFileChooserMode new_fileChooserMode) {
		fileChooser = new JFileChooser();
		fileChooser.setCurrentDirectory(new File(System.getProperty("user.home")));
		fileChooser.setAcceptAllFileFilterUsed(false);
		
		if (new_fileChooserMode == E_JFileChooserMode.MapSave) {
			fileChooser.setDialogTitle("Dungron and Dragons Map Save");
		}
		else if (new_fileChooserMode == E_JFileChooserMode.MapOpen) {
			fileChooser.setDialogTitle("Dungron and Dragons Map file");
			fileChooser.setFileSelectionMode(JFileChooser.FILES_ONLY);
		}
		else if (new_fileChooserMode == E_JFileChooserMode.CampaignSave) {
			fileChooser.setDialogTitle("Dungron and Dragons Campaign Save");
		}
		else if (new_fileChooserMode == E_JFileChooserMode.CampaignOpen) {
			fileChooser.setDialogTitle("Dungron and Dragons Campaign file");
			fileChooser.setFileSelectionMode(JFileChooser.FILES_ONLY);
		}
		else if (new_fileChooserMode == E_JFileChooserMode.ItemSave) {
			fileChooser.setDialogTitle("Dungron and Dragons Item Save");
		}
		return fileChooser;
	}

	/**
	 * @return the fileChooser
	 */
	public JFileChooser getFileChooser() {
		return fileChooser;
	}

}
