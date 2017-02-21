package soen.game.dd.models;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

/**
 * This class will hold all the methods where we can write or read the objects of maps , campaigns, characters
 * and items
 * @author fyounis
 *
 */

public class FileWriterReader {
	
	
	public void saveCharacter(Character character){
		
		  
		  try {
			  
			FileOutputStream fout=new FileOutputStream("characters.txt");
			ObjectOutputStream out=new ObjectOutputStream(fout);
			
			out.writeObject(character);
			out.flush();
			out.close();
			System.out.println("<info> : The Character: "+character.getName()+" is saved");

		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} 
		
	}
	
	public Character loadCharacter() throws FileNotFoundException, IOException, ClassNotFoundException{
		
		ObjectInputStream in=new ObjectInputStream(new FileInputStream("characters.txt"));
		Character character = (Character)in.readObject();
		return character;
		
		
		
	}
	
	public void saveMap(Map map){
		
		  
		  try {
			  
			FileOutputStream fout=new FileOutputStream("map.txt");
			ObjectOutputStream out=new ObjectOutputStream(fout);
			
			out.writeObject(map);
			out.flush();
			out.close();
			System.out.println("<info> : The Map : "+map.getMapName()+" is saved");

		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} 
		
	}
	
	public Map loadMap() throws FileNotFoundException, IOException, ClassNotFoundException{
		
		ObjectInputStream in=new ObjectInputStream(new FileInputStream("map.txt"));
		Map map = (Map)in.readObject();
		System.out.println("<info> : the Loading of the Map "+ map.getMapName()+" was secusseful"); 
		return map;
		
		
		
	}

}
