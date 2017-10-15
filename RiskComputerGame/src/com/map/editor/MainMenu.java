package com.map.editor;

import java.awt.Dimension;
import com.map.play.PlayGame;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;

import javax.swing.JButton;
import javax.swing.JDesktopPane;
import javax.swing.JFrame;
import javax.swing.JInternalFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.KeyStroke;

public class MainMenu extends JFrame implements ActionListener {

	/**
	 * 
	 */
	private static final long serialVersionUID = 6123466052474474690L;

	// Container for creating creating a multiple-document interface
	JDesktopPane desktop;

	public MainMenu() {
		super("RISK");

		// indenting from each edge of the computer screen.
		int inset = 100;
		Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
		setBounds(inset, inset, screenSize.width - inset * 2, screenSize.height - inset *2);

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
			// createNewGameFrame();
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

	// public static void main(String args[]) {
	//
	//
	//
	//
	//
	//
	// JFrame jframeMain = new JFrame("Risk Game");
	// JButton jbuttonNewGame = new JButton("New Game");
	// JButton jbuttonMapEditor = new JButton("Map Editor");
	// JButton jbuttonRules = new JButton("Rules");
	// jbuttonNewGame.setBounds(200, 150, 200, 30);
	// jbuttonMapEditor.setBounds(200, 200, 200, 30);
	// jbuttonRules.setBounds(200, 250, 200, 30);
	// jframeMain.setLayout(null);
	// jframeMain.add(jbuttonNewGame);
	// jframeMain.add(jbuttonMapEditor);
	// jframeMain.add(jbuttonRules);
	// jframeMain.setSize(600, 400);
	// jframeMain.setLocationRelativeTo(null);
	// jframeMain.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	// jframeMain.setVisible(true);
	//
	// jbuttonNewGame.addActionListener(new ActionListener() {
	//
	// @Override
	// public void actionPerformed(ActionEvent e) {
	//
	//
	// }
	// });
	//
	// jbuttonMapEditor.addActionListener(new ActionListener() {
	//
	// @Override
	// public void actionPerformed(ActionEvent e) {
	// MapEditorMenu.mapEditorNavigation();
	// }
	// });
	//
	// jbuttonRules.addActionListener(new ActionListener() {
	//
	// @Override
	// public void actionPerformed(ActionEvent e) {
	//
	// }
	// });
	// }

}
