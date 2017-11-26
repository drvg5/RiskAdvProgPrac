package com.risk.ui;

import java.awt.Dimension;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import javax.swing.JDesktopPane;
import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.KeyStroke;

/**
 * Game starter class for initiating the application
 * 
 * The tasks performed by this class are:
 * <ul>
 * <li>Initializing a virtual desktop
 * <li>Populate a menu drop-down
 * <li>Implement action listeners for drop down options
 * </ul>
 * 
 * 
 * @author drvg5
 * @see JFrame, ActionListener
 *
 */
public class MainMenuUI extends JFrame implements ActionListener {

	/**
	 * id created for deserialization
	 */
	private static final long serialVersionUID = 6123466052474474690L;

	// Container for creating creating a multiple-document interface
	JDesktopPane desktop;

	public MainMenuUI() {
		super("RISK");

		// indenting from each edge of the computer screen.
		int inset = 100;
		Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
		setBounds(inset, inset, screenSize.width - inset * 2, screenSize.height - inset * 2);

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
		MainMenuUI frame = new MainMenuUI();
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		// Window displayed.
		frame.setVisible(true);
	}

	/**
	 * Method for populating the MEnu drop down
	 * 
	 */
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
			GameModeUI.GameModeWindow(desktop);
 		} else if ("Map Editor".equals(e.getActionCommand())) {
			MapEditorMenuUI.mapEditorNavigation(desktop);

		} else {
			quit();
		}
	}

	// close the application.
	protected void quit() {
		System.exit(0);
	}

}
