package com.risk.ui;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;

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

import com.risk.model.SaveMapUponConfigModel;


/**
 * <h1>Save Map Upon ConfigUI</h1>
 * <p>
 * <b>This class consists static methods to save map file to local folder </b>
 * User would be able to configure Adjacency between Countries.
 * <p>
 * 
 * @author Khashyap
 * @version 1.0
 */
public class SaveMapUponConfigUI {

	/** Hash map to store the Map attributes */
	static HashMap<String, List<String>> mainHashMap = new HashMap<String, List<String>>();

	/** Hash map to store control value of Continents */
	static HashMap<String, Integer> continentControlValueHashMap = new HashMap<String, Integer>();

	/** List consists of both Continent and Country */
	static List<String> listContinentCountry = new ArrayList<String>();

	/** Initial list before configuring adjacency */
	static List<String> listFirstEntry = new ArrayList<String>();

	/** Frame to display data */
	static JInternalFrame jframeAdjCountryList = new JInternalFrame();

	/** Desktop pane for user friendly view of application */
	static JDesktopPane desktopSaveMap;

	/** Model to bind data to panel */
	static DefaultTableModel modelAdjacenyList = new DefaultTableModel(
			new Object[] { "Continent and Country", "Adjacency List" }, 0);

	/**
	 * <p>
	 * This method is used to save file to local folder.
	 *
	 * @param keyForHashMap
	 *            List holds the values of both Countries and Continents for which
	 *            adjacency need to be configured
	 * @param controlValueHashmap
	 *            This HashMap stores the Continent and its control value
	 * @param listToCheckDuplicateContinent
	 *            List consists of all Continents
	 * @param listToCheckDuplicateCountry
	 *            List consists of all Countries
	 * @param desktop
	 *            This is to bind the InternalFrame with Main window frame
	 */

	public static void saveToFile(List<String> keyForHashMap, HashMap<String, Integer> controlValueHashmap,
			final List<String> listToCheckDuplicateContinent, final List<String> listToCheckDuplicateCountry,
			JDesktopPane desktop) {
		continentControlValueHashMap = controlValueHashmap;
		listContinentCountry = keyForHashMap;
		final JTable tableAdjacencyList = new JTable();
		JLabel labelAdjList = new JLabel("Adjacent Countries");
		labelAdjList.setBounds(20, 420, 130, 25);
		final JTextField textAdjList = new JTextField();
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
				if (row > -1) {
					String strHashMapKey = modelAdjacenyList.getValueAt(row, 0).toString();
					String strAdjListToUpdate = textAdjList.getText().toString().toLowerCase();
					mainHashMap = new SaveMapUponConfigModel().addAdjacencyForCountries(strHashMapKey,
							strAdjListToUpdate, listToCheckDuplicateCountry, mainHashMap);

					reloadModel();
					textAdjList.setText(null);

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
				boolean checkConnected = false;
				boolean checkConnectedCountry = false;
				List<String> connectivityCheckCountry = new ArrayList<String>();
				List<String> countryListCheckNow = new ArrayList<String>();
				List<String> toCheck = new ArrayList<String>();
				List<String> connectivityCheck = new ArrayList<String>();
				for (int t = 0; t < modelAdjacenyList.getRowCount(); t++) {
					if (modelAdjacenyList.getValueAt(t, 1).equals(toCheck)) {
						checkToSave = true;
					}
				}

				Iterator<Map.Entry<String, List<String>>> iter = mainHashMap.entrySet().iterator();
				while (iter.hasNext()) {
					Map.Entry<String, List<String>> entry = iter.next();
					String strKey = entry.getKey();
					String[] strKeyArrayToCheck = strKey.split(",");
					Iterator<Map.Entry<String, List<String>>> innerIter = mainHashMap.entrySet().iterator();
					while (innerIter.hasNext()) {
						Map.Entry<String, List<String>> innerEntry = innerIter.next();
						String strinnerKey = innerEntry.getKey();
						String[] strinnerKeyArrayToCheck = strinnerKey.split(",");
						List<String> innerList = innerEntry.getValue();
						if (!((strKeyArrayToCheck[0]).equals(strinnerKeyArrayToCheck[0]))) {
							for (int inner = 0; inner < innerList.size(); inner++) {
								if (strKeyArrayToCheck[1].equals(innerList.get(inner))) {
									if (!(connectivityCheck.contains(strKeyArrayToCheck[1]))) {
										connectivityCheck.add(strKeyArrayToCheck[0]);
									}
								}
							}
						}
					}

				}

				// Check for connectivity between Countries in Continents
				Iterator<Map.Entry<String, List<String>>> iterCountry = mainHashMap.entrySet().iterator();
				while (iterCountry.hasNext()) {
					Map.Entry<String, List<String>> entryCountry = iterCountry.next();
					String strKeyCountry = entryCountry.getKey();
					String[] strKeyArrayToCheckCountry = strKeyCountry.split(",");
					List<String> selectedCountryList = fetchCountries(strKeyArrayToCheckCountry[0]);
					List<String> listToCheckCountry = entryCountry.getValue();
					// Iterator<Map.Entry<String, List<String>>> innerIterCountry =
					// continentHashMap.entrySet().iterator();
					for (String eachCountry : listToCheckCountry) {
						if (selectedCountryList.contains(eachCountry)) {
							if (!(connectivityCheckCountry.contains(eachCountry))) {
								connectivityCheckCountry.add(eachCountry);
							}
						}
					}
				}

				// Current Country list
				for (Map.Entry<String, List<String>> entry : mainHashMap.entrySet()) {
					String strKeyForCountry = entry.getKey();
					String[] strCountry = strKeyForCountry.split(",");
					if (!(countryListCheckNow.contains(strCountry[1]))) {
						countryListCheckNow.add(strCountry[1]);
					}
				}

				int sizeOfCont = listToCheckDuplicateContinent.size();
				Set<String> hs = new HashSet<>();
				hs.addAll(connectivityCheck);
				connectivityCheck.clear();
				connectivityCheck.addAll(hs);
				Collection<String> collectionAdjCont = connectivityCheck;
				Collection<String> collectionTotalCont = listToCheckDuplicateContinent;
				collectionTotalCont.removeAll(collectionAdjCont);

				Set<String> setCountry = new HashSet<>();
				setCountry.addAll(connectivityCheckCountry);
				connectivityCheckCountry.clear();
				connectivityCheckCountry.addAll(setCountry);
				Collection<String> collectionAdjCountry = connectivityCheckCountry;
				Collection<String> collectionTotalCountry = countryListCheckNow;
				collectionTotalCountry.removeAll(collectionAdjCountry);

				if (collectionTotalCont.isEmpty() || sizeOfCont == 1) {
					checkConnected = true;
				}

				if (collectionTotalCountry.isEmpty()) {
					checkConnectedCountry = true;
				}

				if (checkToSave) {
					JOptionPane.showMessageDialog(null, "Invalid Map! All Countries need to have adjacent link",
							"Adjacency Error", JOptionPane.ERROR_MESSAGE);
				}

				else if (!(checkConnected)) {
					JOptionPane.showMessageDialog(null,
							"Invalid Map! Not a connected graph. Check adjacency for" + collectionTotalCont,
							"Adjacency Error", JOptionPane.ERROR_MESSAGE);
				}

				else if (!(checkConnectedCountry)) {
					JOptionPane.showMessageDialog(null,
							"Invalid Map! Not a connected graph. Check adjacency for Territories"
									+ collectionTotalCountry,
							"Adjacency Error", JOptionPane.ERROR_MESSAGE);
				}

				else {
					textFileName = JOptionPane.showInputDialog("Please Enter Map name");
					if (textFileName != null && !textFileName.isEmpty()) {
						try {

							fstream = new FileWriter("C:/Users/ashis/Documents/Maps/" + textFileName + ".txt");
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
	 * This method is used to reload Data model after changes in HashMap value.
	 *
	 * @author Khashyap
	 */

	public static void reloadModel() {
		modelAdjacenyList.setRowCount(0);
		for (Map.Entry<String, List<String>> iterate : mainHashMap.entrySet()) {
			modelAdjacenyList.addRow(new Object[] { iterate.getKey(), iterate.getValue() });
		}

	}

	/**
	 * This methods displays all error messages to User.
	 *
	 * @author Dhruv
	 * @param message
	 *            the error message
	 */

	public void showErrorMessageAdjacency(String message) {
		if (message.equals("checkEqual")) {
			JOptionPane.showMessageDialog(null, "You cannot enter same country", "Error", JOptionPane.ERROR_MESSAGE);
		}

		else if (message.equals("checkExist")) {
			JOptionPane.showMessageDialog(null, "You can link only countries listed here", "Error",
					JOptionPane.ERROR_MESSAGE);
		}

	}

	/**
	 * <p>
	 * This method is used to return Countries from particular Continent.
	 *
	 * @param toCheck Continent value
	 *          
	 * @return toSend list value with countries
	 */
	
	
	private static List<String> fetchCountries(String toCheck) {
		// TODO Auto-generated method stub
		List<String> toSend = new ArrayList<String>();
		for (Map.Entry<String, List<String>> entryPopulate : mainHashMap.entrySet()) {

			String strKeyForCountryPopulate = entryPopulate.getKey();
			String[] strCountryPopulate = strKeyForCountryPopulate.split(",");
			if (strCountryPopulate[0].equals(toCheck)) {
				if (!(toSend.contains(strCountryPopulate[1]))) {
					toSend.add(strCountryPopulate[1]);
				}
			}
		}
		return toSend;
	}
}
