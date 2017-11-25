package com.risk.ui;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.beans.PropertyVetoException;

import javax.swing.JButton;
import javax.swing.JDesktopPane;
import javax.swing.JFrame;
import javax.swing.JInternalFrame;

public class GameModeUI {

	static JInternalFrame jframeGameMode;

	/**
	 * <p>
	 * This method is used to load map file from local folder to play game
	 * 
	 * @author Khashyap
	 * @param desktop
	 *            To bind the InternalFrame with Main window frame
	 * 
	 */

	public static void GameModeWindow(final JDesktopPane desktop) {

		jframeGameMode = new JInternalFrame("Game Mode");
		jframeGameMode.setLayout(null);
		jframeGameMode.setSize(400, 300);
		jframeGameMode.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		JButton singlePlayerBbutton = new JButton("Single Game Mode");
		singlePlayerBbutton.setBounds(100, 100, 200, 30);
		JButton multiPlayerBbutton = new JButton("Tournament Mode");
		multiPlayerBbutton.setBounds(100, 150, 200, 30);
		JButton buttonCloseUpload = new JButton("Close");
		buttonCloseUpload.setBounds(100, 200, 200, 30);
		jframeGameMode.add(singlePlayerBbutton);
		jframeGameMode.add(multiPlayerBbutton);
		jframeGameMode.add(buttonCloseUpload);
		jframeGameMode.setVisible(true);
		desktop.add(jframeGameMode);

		singlePlayerBbutton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent ae) {
				UploadMapUI.LoadMap(desktop);
				try {
					jframeGameMode.setClosed(true);
				} catch (PropertyVetoException e1) {
					e1.printStackTrace();
				}
			}
		});

		multiPlayerBbutton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent ae) {
				try {
					jframeGameMode.setClosed(true);
				} catch (PropertyVetoException e1) {
					e1.printStackTrace();
				}

			}
		});

		buttonCloseUpload.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				try {
					jframeGameMode.setClosed(true);
				} catch (PropertyVetoException e1) {
					e1.printStackTrace();
				}
			}
		});

	}

	/**
	 * <p>
	 * This method is used to close upload button
	 * 
	 */

	public void closeUpload() {
		try {
			jframeGameMode.setClosed(true);
		} catch (PropertyVetoException e1) {
			e1.printStackTrace();
		}

	}

}
