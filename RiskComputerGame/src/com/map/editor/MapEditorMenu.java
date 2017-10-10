package com.map.editor;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;

public class MapEditorMenu {

	public static void mapEditorNavigation() {
		JFrame jframeMapEditor = new JFrame("Map Editor");
		JButton jbuttonCreateMap = new JButton("Create Map");
		JButton jbuttonEditMap = new JButton("Edit Map");
		JButton jbuttonMainMenu = new JButton("Main Menu");
		jbuttonCreateMap.setBounds(200, 150, 200, 30);
		jbuttonEditMap.setBounds(200, 200, 200, 30);
		jbuttonMainMenu.setBounds(200, 250, 200, 30);
		jframeMapEditor.setLayout(null);
		jframeMapEditor.add(jbuttonCreateMap);
		jframeMapEditor.add(jbuttonEditMap);
		jframeMapEditor.add(jbuttonMainMenu);
		jframeMapEditor.setSize(600, 400);
		jframeMapEditor.setLocationRelativeTo(null);
		jframeMapEditor.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		jframeMapEditor.setVisible(true);

		jbuttonCreateMap.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				CreateMap.configureContinent();
			}
		});

		jbuttonEditMap.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {

			}
		});

		jbuttonMainMenu.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				MainMenu.main(null);
			}
		});

	}

}
