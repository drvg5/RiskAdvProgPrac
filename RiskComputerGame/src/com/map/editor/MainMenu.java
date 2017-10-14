package com.map.editor;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import javax.swing.JFrame;

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

		jbuttonNewGame.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				

			}
		});

		jbuttonMapEditor.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				MapEditorMenu.mapEditorNavigation();
			}
		});

		jbuttonRules.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {

			}
		});
	}

}
