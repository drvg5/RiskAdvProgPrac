package com.map.editor;

import java.awt.Dimension;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import com.map.play.PlayGame;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import com.map.play.PlayGame;
import javax.swing.JDesktopPane;
import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.KeyStroke;

<<<<<<< HEAD
public class MainMenu {
	public static void main(String args[]) {
		
		JFrame jframeMain = new JFrame("Risk Game");
		JButton jbuttonNewGame = new JButton("New Game");
		JButton jbuttonMapEditor = new JButton("Map Editor");
		JButton jbuttonRules = new JButton("Rules");
		jbuttonNewGame.setBounds(200, 150, 200, 30);
		jbuttonMapEditor.setBounds(200, 200, 200, 30);
		jbuttonRules.setBounds(200, 250, 200, 30);
		jframeMain.setLayout(null);
		jframeMain.add(jbuttonNewGame);
		jframeMain.add(jbuttonMapEditor);
		jframeMain.add(jbuttonRules);
		jframeMain.setSize(600, 400);
		jframeMain.setLocationRelativeTo(null);
		jframeMain.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		jframeMain.setVisible(true);
=======
/**
 * Game starter class for initiating the application 
 * 
 * The tasks performed by this class are:
 * <ul>
 * <li>Initializing a virtual desktop
 * <li>Populate a menu drop-down
 * <li>Implement action listeners for dropdown options
 * </ul>
 * 
 * 
 * @author drvg5
 * @see JFrame, ActionListener
 *
 */
public class MainMenu extends JFrame implements ActionListener {

	/**
	 * id created for deserialization
	 */
	private static final long serialVersionUID = 6123466052474474690L;
>>>>>>> refs/remotes/origin/master

	// Container for creating creating a multiple-document interface
	JDesktopPane desktop;

<<<<<<< HEAD
			@Override
			public void actionPerformed(ActionEvent e) {
				PlayGame.LoadMap();
			}
		});
=======
	public MainMenu() {
		super("RISK");

		// indenting from each edge of the computer screen.
		int inset = 100;
		Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
		setBounds(inset, inset, screenSize.width - inset * 2, screenSize.height - inset * 2);
>>>>>>> refs/remotes/origin/master

		// Setting up the GUI.
		desktop = new JDesktopPane();
		setContentPane(desktop);
		setJMenuBar(populateMenuDropdown());

		// making frames draggable
		desktop.setDragMode(JDesktopPane.LIVE_DRAG_MODE);
	}

	public static void main(String[] args) {

		startGame();
	}

	/**
	 * GUI for Risk game displayed.
	 */
	private static void startGame() {
		// Decorating the window.
		JFrame.setDefaultLookAndFeelDecorated(true);

		// Window created.
		MainMenu frame = new MainMenu();
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		// Window displayed.
		frame.setVisible(true);
	}

	protected JMenuBar populateMenuDropdown() {
		JMenuBar menuBar = new JMenuBar();

		// Set up the lone menu.
		JMenu menu = new JMenu("Menu");
		menu.setMnemonic(KeyEvent.VK_M);
		menuBar.add(menu);

		// menu item 1.
		JMenuItem menuItem = new JMenuItem("New Game");
		menuItem.setMnemonic(KeyEvent.VK_N);
		menuItem.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_N, ActionEvent.ALT_MASK));
		menuItem.setActionCommand("New Game");
		menuItem.addActionListener(this);
		menu.add(menuItem);

		// menu item 2.
		menuItem = new JMenuItem("Map Editor");
		menuItem.setMnemonic(KeyEvent.VK_E);
		menuItem.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_E, ActionEvent.ALT_MASK));
		menuItem.setActionCommand("Map Editor");
		menuItem.addActionListener(this);
		menu.add(menuItem);

		// Set up the second menu item.
		menuItem = new JMenuItem("Quit");
		menuItem.setMnemonic(KeyEvent.VK_Q);
		menuItem.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_Q, ActionEvent.ALT_MASK));
		menuItem.setActionCommand("quit");
		menuItem.addActionListener(this);
		menu.add(menuItem);

		return menuBar;
	}

	// Event handling for menu selections.
	public void actionPerformed(ActionEvent e) {
		if ("New Game".equals(e.getActionCommand())) { // new
			PlayGame.LoadMap(desktop);
		} else if ("Map Editor".equals(e.getActionCommand())) {
			MapEditorMenu.mapEditorNavigation(desktop);
		} else {
			quit();
		}
	}

	// close the application.
	protected void quit() {
		System.exit(0);
	}

}
