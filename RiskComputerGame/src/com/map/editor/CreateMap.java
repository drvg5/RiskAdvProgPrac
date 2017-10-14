package com.map.editor;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;

public class CreateMap {

	static HashMap<String, HashMap<String, List<String>>> continentHashMap = new HashMap<String, HashMap<String, List<String>>>();
	static HashMap<String, List<String>> territoryHashMap = new HashMap<String, List<String>>();
	
	public static void configureContinent() {
		JFrame jframeContinent = new JFrame();
		JTable tableContinent = new JTable();
		List<String> continentList = new ArrayList<String>();
		DefaultTableModel modelContinent = new DefaultTableModel(new Object[] { "Continent List", "Add Countries" }, 0);
		JTextField textContinent = new JTextField();
		textContinent.setBounds(20, 220, 100, 25);
		JButton btnAdd = new JButton("Add Continent");
		btnAdd.setBounds(150, 220, 200, 25);

		for (Map.Entry<?, ?> entry : continentHashMap.entrySet()) {
			modelContinent.addRow(new Object[] { entry.getKey(), entry.getValue() });
		}
		tableContinent.setModel(modelContinent);
		tableContinent.getColumnModel().getColumn(1).setCellRenderer(new ButtonRenderer());
		;
		btnAdd.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub

				String continent = textContinent.getText();
				continentList.add(continent);
				modelContinent.addRow(new Object[] { continent, "Add Country" });
				continentHashMap.put(continent, territoryHashMap);
				// System.out.println(continentHashMap);
				// System.out.println(continentList);
				textContinent.setText(null);
			}
		});
		JScrollPane panel = new JScrollPane(tableContinent);
		panel.setBounds(0, 0, 880, 220);
		jframeContinent = new JFrame("Add Continent");
		jframeContinent.setLayout(null);
		jframeContinent.add(panel);
		jframeContinent.add(textContinent);
		jframeContinent.add(btnAdd);
		jframeContinent.setSize(700, 400);
		jframeContinent.setLocationRelativeTo(null);
		jframeContinent.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		jframeContinent.setVisible(true);

	}

	public static void AddCountry(String strContinent) {

		JFrame framecountry = new JFrame(strContinent);
		JTable tablecountry = new JTable();
		DefaultTableModel modelcontinent = new DefaultTableModel(
				new Object[] { "Country", "Adjacent Countries" }, 0);
		tablecountry.setModel(modelcontinent);
		JLabel textCountryLabel = new JLabel("Country:");
		textCountryLabel.setBounds(20, 220, 50, 25);
		JLabel textadjLabel = new JLabel("Ajacent List:");
		textadjLabel.setBounds(20, 250, 70, 25);
		JTextField textCountry = new JTextField();
		textCountry.setBounds(80, 220, 80, 25);
		JTextField textadjacent = new JTextField();
		textadjacent.setBounds(120, 250, 300, 25);
		JButton btnAdd = new JButton("Add Country and Adjacent List");
		btnAdd.setBounds(130, 280, 300, 25);
		JButton btnComplete = new JButton("Complete");
		btnComplete.setBounds(130, 320, 100, 25);

		btnAdd.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				String country = textCountry.getText();
				// continentList.add(continent);
				String adjcountry = textadjacent.getText();
				List<String> adjCountryList = new ArrayList<String>();
				adjCountryList = new ArrayList<String>(Arrays.asList(adjcountry.split(",")));
				modelcontinent.addRow(new Object[] { country, adjCountryList });
				territoryHashMap.put(country, adjCountryList);
				textCountry.setText(null);
				textadjacent.setText(null);
			}
		});
		

		JScrollPane panelCountry = new JScrollPane(tablecountry);
		panelCountry.setBounds(0, 0, 880, 220);
		framecountry.setLayout(null);
		framecountry.add(panelCountry);
		framecountry.add(textCountryLabel);
		framecountry.add(textCountry);
		framecountry.add(textadjLabel);
		framecountry.add(textadjacent);
		framecountry.add(btnAdd);
		framecountry.add(btnComplete);
		framecountry.setSize(1000, 400);
		framecountry.setLocationRelativeTo(null);
		framecountry.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		framecountry.setVisible(true);
		
		btnComplete.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				System.out.println("strContinent:"+strContinent);
				continentHashMap.put(strContinent, territoryHashMap);
				System.out.println(continentHashMap);
				//framecountry.dispose();
				//framecountry.setVisible(false);
				
			}
		});
		


	}
}
