package soen.game.dd.fileio;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;


/**
 * This class and its children define implement the saving and loading capability to and from files.
 * ModelIO would contain the common methods that all other Models would need to have
 * @author Khaled
 *
 */
public class ModelIO {

	/**
	 * This is a common method to save the Object onto the file
	 * 
	 * @author Usman
	 * @param o 
	 * @param fileName
	 * @return status
	 */
	protected String save(Object o, String fileName) {
		try {
			File file = new File(fileName);
			FileOutputStream fout = null;
			ObjectOutputStream out = null;
			if (!file.exists()) {
				file.createNewFile();
				fout = new FileOutputStream(file.getPath());
				out = new ObjectOutputStream(fout);
			} else {
				fout = new FileOutputStream(file.getPath(), true);
				out = new ObjectOutputStream(fout) {
					protected void writeStreamHeader() throws IOException {
						reset();
					}
				};
			}

			out.writeObject(o);
			out.flush();
			out.close();
			return "SUCCESS";

		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return "ERROR";
		}
	}
}
