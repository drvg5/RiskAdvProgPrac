package com.risk.ui;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.beans.PropertyVetoException;

import javax.swing.JButton;
import javax.swing.JDesktopPane;
import javax.swing.JFrame;
import javax.swing.JInternalFrame;

import com.risk.model.ConfigureMapModel;
import com.risk.model.EditMapModel;

/**
 * This class contains creates the menu for map editor
 * 
 * @author drvg5
 *
 */
public class MapEditorMenuUI {

	public static void mapEditorNavigation(final JDesktopPane desktop) {
		final JInternalFrame jframeMapEditor = new JInternalFrame("Map Editor");
		JButton jbuttonCreateMap = new JButton("Create Map");
		JButton jbuttonEditMap = new JButton("Edit Map");
		JButton jbuttonMainMenu = new JButton("Close");
		jbuttonCreateMap.setBounds(200, 150, 200, 30);
		jbuttonEditMap.setBounds(200, 200, 200, 30);
		jbuttonMainMenu.setBounds(200, 250, 200, 30);
		jframeMapEditor.setLayout(null);
		jframeMapEditor.add(jbuttonCreateMap);
		jframeMapEditor.add(jbuttonEditMap);
		jframeMapEditor.add(jbuttonMainMenu);
		jframeMapEditor.setSize(600, 400);
		jframeMapEditor.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		jframeMapEditor.setVisible(true);
		desktop.add(jframeMapEditor);

		jbuttonCreateMap.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				try {
					jframeMapEditor.setClosed(true);
				} catch (PropertyVetoException e1) {
					e1.printStackTrace();
				}
				// CreateMap.configureContinent(desktop);
				new ConfigureMapUI().createContinentandCountry(desktop);
			}
		});

		jbuttonEditMap.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				try {
					jframeMapEditor.setClosed(true);
				} catch (PropertyVetoException e1) {
					e1.printStackTrace();
				}
				EditMapUI.fetchMap(desktop);
			}
		});

		jbuttonMainMenu.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				try {
					jframeMapEditor.setClosed(true);
				} catch (PropertyVetoException e1) {
					e1.printStackTrace();
				}
			}
		});

	}

}