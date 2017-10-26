
package com.risk.model;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.StringJoiner;

import javax.swing.JButton;
import javax.swing.JDesktopPane;
import javax.swing.JFrame;
import javax.swing.JInternalFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;

/**
 * <h1>Save Map</h1>
 * <p>
 * <b>This class consists static methods to save map file to local folder </b>
 * User would be able to configure Adjacency between Countries.
 * <p>
 * 
 * @author Khashyap
 * @version 1.0
 */

public class SaveMap {

	static HashMap<String, List<String>> mainHashMap = new HashMap<String, List<String>>();
	static HashMap<String, Integer> continentControlValueHashMap = new HashMap<String, Integer>();
	static List<String> listContinentCountry = new ArrayList<String>();
	static JInternalFrame jframeAdjCountryList = new JInternalFrame();
	static JDesktopPane desktopSaveMap;
	static List<String> listFirstEntry = new ArrayList<String>();
	static DefaultTableModel modelAdjacenyList = new DefaultTableModel(
			new Object[] { "Continent and Country", "Adjacency List" }, 0);

	/**
	 * <p>
	 * This method is used to save file to local folder
	 * 
	 * @param keyForHashMap
	 *            This list holds the values of both Countries and Continents for
	 *            which adjacency need to be configured
	 * @param controlValueHashmap
	 *            This HashMap stores the Continent and its control value
	 * @param listToCheckDuplicateContinent
	 *            List which consists of all Continents
	 * @param listToCheckDuplicateCountry
	 *            List which consists of all Countries
	 * @param desktop
	 *            This is to bind the InternalFrame with Main window frame
	 * 
	 */

	public static void saveToFile(List<String> keyForHashMap, HashMap<String, Integer> controlValueHashmap,
			List<String> listToCheckDuplicateContinent, List<String> listToCheckDuplicateCountry,
			JDesktopPane desktop) {
		continentControlValueHashMap = controlValueHashmap;
		listContinentCountry = keyForHashMap;
		JTable tableAdjacencyList = new JTable();

		JLabel labelAdjList = new JLabel("Adjacent Countries");
		labelAdjList.setBounds(20, 420, 130, 25);
		JTextField textAdjList = new JTextField();
		textAdjList.setBounds(150, 420, 300, 25);
		JLabel labelAdjMessage = new JLabel("<-----Please Enter Comma seperated");
		labelAdjMessage.setForeground(Color.RED);
		labelAdjMessage.setBounds(450, 420, 300, 25);
		JButton btnAddAdjList = new JButton("Add Adjacency List");
		btnAddAdjList.setBounds(180, 450, 320, 25);
		JButton btnSaveToMap = new JButton("Save Map");
		btnSaveToMap.setBounds(510, 450, 150, 25);

		for (String str : listContinentCountry) {
			mainHashMap.put(str, listFirstEntry);
		}

		// Setting values to DataModel from HashMap

		for (Map.Entry<String, List<String>> iterate : mainHashMap.entrySet()) {
			modelAdjacenyList.addRow(new Object[] { iterate.getKey(), iterate.getValue() });
		}

		tableAdjacencyList.setModel(modelAdjacenyList);

		tableAdjacencyList.addMouseListener(new MouseAdapter() {
		});

		// Button to add Adjacency list between all Countries

		btnAddAdjList.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {

				int row = tableAdjacencyList.getSelectedRow();
				boolean checkExist = false;
				boolean CheckEqual = false;
				if (row > -1) {
					String strHashMapKey = modelAdjacenyList.getValueAt(row, 0).toString();
					String[] strHashMapKeySplit = strHashMapKey.split(",");
					String strAdjListToUpdate = textAdjList.getText().toString().toLowerCase();
					List<String> listAdjCountry = new ArrayList<String>();
					List<String> listAdjCountryCapitalize = new ArrayList<String>();
					listAdjCountry = new ArrayList<String>(Arrays.asList(strAdjListToUpdate.split(",")));
					for (String capital : listAdjCountry) {
						capital = capital.substring(0, 1).toUpperCase() + capital.substring(1);
						listAdjCountryCapitalize.add(capital);
					}

					for (String st : listAdjCountryCapitalize) {
						if (st.equals(strHashMapKeySplit[1])) {
							CheckEqual = true;
						}
					}
					if (!(listToCheckDuplicateCountry.containsAll(listAdjCountryCapitalize))) {
						checkExist = true;
					}

					if (CheckEqual) {
						JOptionPane.showMessageDialog(null, "You cannot enter same country", "Error",
								JOptionPane.ERROR_MESSAGE);
					}

					else if (checkExist) {
						JOptionPane.showMessageDialog(null, "You can link only countries listed here", "Error",
								JOptionPane.ERROR_MESSAGE);
					}

					else {
						mainHashMap.put(strHashMapKey, listAdjCountryCapitalize);
					
						List<String> empty = new ArrayList<String>();
						String getAllKeys;
						String[] getIndividual;
						for (String tempAdj : listAdjCountryCapitalize) {

							for (Map.Entry<String, List<String>> maplist : mainHashMap.entrySet()) {
								List<String> fetchLinks = new ArrayList<String>();
								List<String> fetchLinks2 = new ArrayList<String>();
								getAllKeys = maplist.getKey();
								getIndividual= getAllKeys.split(",");
								if (getIndividual[1].equals(tempAdj)) {
									fetchLinks = mainHashMap.get(getAllKeys);
									if (!fetchLinks.equals(empty)) {
										fetchLinks2.addAll(fetchLinks);
									}
									fetchLinks2.add(strHashMapKeySplit[1]);
									mainHashMap.replace(getAllKeys, fetchLinks2);
								}
							}

						}

						modelAdjacenyList.setValueAt(listAdjCountryCapitalize, row, 1);
						reloadModel();
						textAdjList.setText(null);
					}
				} else {
					JOptionPane.showMessageDialog(null, "Please select a row to Update", "Error",
							JOptionPane.ERROR_MESSAGE);
				}
			}
		});

		// Button to Save File to local path after linking all Countries

		btnSaveToMap.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {

				// TODO Auto-generated method stub
				FileWriter fstream;
				BufferedWriter out;
				List<String> continentListToPrint = new ArrayList<String>();
				String textFileName;
				boolean checkToSave = false;
				List<String> toCheck = new ArrayList<String>();
				for (int t = 0; t < modelAdjacenyList.getRowCount(); t++) {
					if (modelAdjacenyList.getValueAt(t, 1).equals(toCheck)) {
						checkToSave = true;
					}
				}
				if (checkToSave) {
					JOptionPane.showMessageDialog(null, "Invalid Map! All Countires need to have adjacent link",
							"Adjacency Error", JOptionPane.ERROR_MESSAGE);
				}

				else {
					textFileName = JOptionPane.showInputDialog("Please Enter Map name");
					try {

						fstream = new FileWriter("C:/Users/Khashyap/Documents/Maps/" + textFileName + ".txt");
						out = new BufferedWriter(fstream);
						out.write("[Map]");
						out.write(System.getProperty("line.separator"));
						out.write(System.getProperty("line.separator"));
						out.write("[Continents]");
						out.write(System.getProperty("line.separator"));
						for (Map.Entry<String, Integer> temp : continentControlValueHashMap.entrySet()) {

							continentListToPrint.add(temp.getKey());
							out.write(temp.getKey() + "=" + temp.getValue());
							out.write(System.getProperty("line.separator"));
						}
						out.write(System.getProperty("line.separator"));
						out.write("[Territories]");
						out.write(System.getProperty("line.separator"));
						for (String obj : continentListToPrint) {
							for (Map.Entry<String, List<String>> iterate : mainHashMap.entrySet()) {
								String strKey = iterate.getKey();
								String[] strKeyArray = strKey.split(",");
								if (obj.equals(strKeyArray[0])) {
									String printWithoutBraces = iterate.getValue().toString()
											.replaceAll("(^\\[|\\s|\\]$)", "");
									out.write(strKeyArray[1] + "," + "50" + "," + "50" + "," + strKeyArray[0] + ","
											+ printWithoutBraces);
									out.write(System.getProperty("line.separator"));
								}

							}
							out.write(System.getProperty("line.separator"));
						}
						out.close();
					} catch (IOException e1) {
						e1.printStackTrace();
					}
					JOptionPane.showMessageDialog(null, textFileName + " \t is saved");
					jframeAdjCountryList.setVisible(false);
				}
			}
		});

		JScrollPane panel = new JScrollPane(tableAdjacencyList);
		panel.setVisible(true);
		panel.setBounds(0, 0, 780, 380);
		jframeAdjCountryList = new JInternalFrame("Saving File");
		jframeAdjCountryList.setLayout(null);
		jframeAdjCountryList.add(panel);
		jframeAdjCountryList.add(labelAdjList);
		jframeAdjCountryList.add(labelAdjMessage);
		jframeAdjCountryList.add(textAdjList);
		jframeAdjCountryList.add(btnSaveToMap);
		jframeAdjCountryList.add(btnAddAdjList);
		jframeAdjCountryList.setSize(800, 600);
		jframeAdjCountryList.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		jframeAdjCountryList.setVisible(true);
		desktop.add(jframeAdjCountryList);
	}

	/**
	 * <p>
	 * This method is used to reload Data model after changes in HashMap value
	 * 
	 */

	public static void reloadModel() {
		modelAdjacenyList.setRowCount(0);
		for (Map.Entry<String, List<String>> iterate : mainHashMap.entrySet()) {
			modelAdjacenyList.addRow(new Object[] { iterate.getKey(), iterate.getValue() });
		}

	}
}
