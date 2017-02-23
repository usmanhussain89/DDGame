package soen.game.dd.gui.system;

import java.awt.BorderLayout;
import java.awt.Canvas;
import java.awt.Dimension;
import java.awt.Frame;

import javax.swing.JFrame;
import javax.swing.JMenuBar;

import soen.game.dd.gui.components.JMenuBarComponent;
import soen.game.dd.statics.content.GameStatics;

/**
 * This is the main class of the game
 * responsible for initialization game
 * 
 * @author Usman
 */
public class Game extends Canvas {
	
	private JFrame frame;
	private int height;
	private int width;
	private String title;
	
	private JMenuBarComponent jMenuBarComponent;
	private JMenuBar jMenuBar;
	
	/**
	 * This is the Constructor of class Game
	 * initialize object of Jframe class and set properties of the Component
	 */
	private Game(){
		
		//Setting Game frame
		width = GameStatics.WINDOW_WIDTH;
		height = GameStatics.WINDOW_HEIGHT;
		title = "Dungeons and Dragons by Team 18";
		
		frame = new JFrame();
		frame.setTitle(title);
		frame.setPreferredSize(new Dimension(width, height));
		frame.setMaximumSize(new Dimension(width,height));
		frame.setMinimumSize(new Dimension(width, height));
		frame.setResizable(false);
		frame.setLocationRelativeTo(null);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().removeAll();
		frame.setLayout(new BorderLayout());
		jMenuBarComponent = new JMenuBarComponent();
		jMenuBar = jMenuBarComponent.getGameJMenuBar(frame);
		frame.setJMenuBar(jMenuBar);
		frame.setVisible(true);
	}
	
	/**
	 * Main method of the class which create the Game Instance and starts the Game
	 * 
	 * @param args contains the supplied command-line arguments as an arrays of the String object
	 */
	public static void main(String[] args) {
		System.out.println("Game Start");
		Game g = new Game();
	}
}
