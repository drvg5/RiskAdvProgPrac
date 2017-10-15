package com.map.editor;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.beans.PropertyVetoException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.StringJoiner;

import javax.swing.JButton;
import javax.swing.JDesktopPane;
import javax.swing.JFrame;
import javax.swing.JInternalFrame;
import javax.swing.JLabel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumn;

public class CreateMap {

	static HashMap<String, HashMap<String, List<String>>> continentHashMap = new HashMap<String, HashMap<String, List<String>>>();
	static HashMap<String, List<String>> territoryHashMap = new HashMap<String, List<String>>();
	static String TxtCountry;
	static String TxtAdjCountry;
	static JInternalFrame jframeContinent = new JInternalFrame();

	public static void configureContinent(JDesktopPane desktop) {
		
 		JTable tableContinent = new JTable();
		List<String> continentList = new ArrayList<String>();
		DefaultTableModel modelContinent = new DefaultTableModel(new Object[] { "Continent List", "Configure"}, 0);
		JTextField textContinent = new JTextField();
		textContinent.setBounds(20, 220, 100, 25);
		JButton btnAdd = new JButton("Add Continent");
		btnAdd.setBounds(150, 220, 200, 25);
		JButton btnClose = new JButton("Close");
		btnClose.setBounds(150, 280, 200, 25);
		JButton btnAddCountry = new JButton("Configure Countries");
		btnAddCountry.setBounds(150, 270, 200, 25);
		// for (Map.Entry<?, ?> entry : continentHashMap.entrySet())
		for (int counter = 0; counter < continentList.size(); counter++) {
			modelContinent.addRow(new Object[] { continentList.get(counter) });
		}
		tableContinent.setModel(modelContinent);
		tableContinent.getColumnModel().getColumn(1).setCellRenderer(new ButtonRenderer());

		btnAdd.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub

				String continent = textContinent.getText();
				continentList.add(continent);
				modelContinent.addRow(new Object[] { continent,"Configure Continents" });
				// continentHashMap.put(continent, territoryHashMap);
				// System.out.println(continentHashMap);
				// System.out.println(continentList);
				textContinent.setText(null);
			}
		});
		
		btnClose.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
 				try {
					jframeContinent.setClosed(true);
				} catch (PropertyVetoException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}

			}
		});

		btnAddCountry.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
 
				//addCountry(continentList);
			}
		});
		JScrollPane panel = new JScrollPane(tableContinent);
		panel.setBounds(0, 0, 880, 220);
		jframeContinent = new JInternalFrame("Add Continent");
		jframeContinent.setLayout(null);
		jframeContinent.add(panel);
		jframeContinent.add(textContinent);
		jframeContinent.add(btnAdd);
		jframeContinent.add(btnClose);
		jframeContinent.setSize(700, 400);
 		jframeContinent.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		jframeContinent.setVisible(true);
		desktop.add(jframeContinent);

	}

	//public static void addCountry(List<String> continentList)
	public static void addCountry(String strContinent){

		JFrame frameCountry = new JFrame();
		JTable tableCountry = new JTable();
		String country;
		DefaultTableModel modelContinent = new DefaultTableModel(
				new Object[] { "Continent", "Country", "Adjacent Countries" }, 0);
		tableCountry.setModel(modelContinent);
		TableColumn columnContinent = tableCountry.getColumnModel().getColumn(0);
		//JComboBox<String> comboContinent = new JComboBox<String>();
		//comboContinent.setModel(new DefaultComboBoxModel(continentList.toArray()));
		//columnContinent.setCellEditor(new DefaultCellEditor(comboContinent));
		JLabel textCountryLabel = new JLabel("Country:");
		textCountryLabel.setBounds(20, 220, 50, 25);
		JLabel textAdjLabel = new JLabel("Ajacent List:");
		textAdjLabel.setBounds(20, 250, 70, 25);
		JTextField textCountry = new JTextField();
		textCountry.setBounds(80, 220, 80, 25);
		JTextField textAdjacent = new JTextField();
		textAdjacent.setBounds(120, 250, 300, 25);
		JButton btnAdd = new JButton("Add Country and Adjacent List");
		btnAdd.setBounds(130, 280, 300, 25);
		JButton btnComplete = new JButton("Complete");
		btnComplete.setBounds(130, 320, 100, 25);

		btnAdd.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				TxtCountry = textCountry.getText();
				TxtAdjCountry = textAdjacent.getText();
				StringJoiner joiner = new StringJoiner(",");
				joiner.add(strContinent).add(TxtCountry);
				String concatString = joiner.toString();
				List<String> adjCountryList = new ArrayList<String>();
				adjCountryList = new ArrayList<String>(Arrays.asList(TxtAdjCountry.split(",")));
				modelContinent.addRow(new Object[] {strContinent , TxtCountry, adjCountryList });
				territoryHashMap.put(concatString, adjCountryList);
				textCountry.setText(null);
				textAdjacent.setText(null);
			}
		});

//		comboContinent.addActionListener(new ActionListener() {
//
//			@Override
//			public void actionPerformed(ActionEvent e) {
//				// TODO Auto-generated method stub
//				// System.out.println(e.getItem() + " " + e.getStateChange() );
//				//System.out.println(e.getActionCommand());
//				String newSelection = (String) comboContinent.getSelectedItem();
//				StringJoiner joiner = new StringJoiner(",");
//				joiner.add(newSelection).add(TxtCountry);
//				String concatString = joiner.toString();
//				territoryHashMap.put(concatString, adjCountryList);
//				System.out.println(territoryHashMap);
//			}
//		});

		JScrollPane panelCountry = new JScrollPane(tableCountry);
		panelCountry.setBounds(0, 0, 880, 220);
		frameCountry.setLayout(null);
		frameCountry.add(panelCountry);
		frameCountry.add(textCountryLabel);
		frameCountry.add(textCountry);
		frameCountry.add(textAdjLabel);
		frameCountry.add(textAdjacent);
		frameCountry.add(btnAdd);
		frameCountry.add(btnComplete);
		frameCountry.setSize(1000, 400);
		frameCountry.setLocationRelativeTo(null);
		frameCountry.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		frameCountry.setVisible(true);

		btnComplete.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				frameCountry.dispose();
				frameCountry.setVisible(false);
				jframeContinent.setVisible(true);
			}
		});

	}
}
