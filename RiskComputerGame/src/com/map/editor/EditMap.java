package com.map.editor;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Scanner;
import java.util.StringJoiner;

import javax.swing.JButton;
import javax.swing.JDesktopPane;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JInternalFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;

import org.apache.commons.io.FilenameUtils;

/**
 * <h1>Edit Map</h1>
 * <p>
 * <b>This class consists static methods to save map file to local folder </b>
 * User would be able to configure Adjacency between Countries.
 * <p>
 * 
 * @author Khashyap
 * @version 1.0
 */

public class EditMap {

	static JInternalFrame jframeContinent = new JInternalFrame();
	static DefaultTableModel modelToEdit = new DefaultTableModel(
			new Object[] { "Continent List", "Country", "Adjacent List" }, 0);
	static HashMap<String, List<String>> continentHashMapToEdit = new HashMap<String, List<String>>();
	static HashMap<String, Integer> continentControlValueHashMapToEdit = new HashMap<String, Integer>();
	static List<String> continentListToEdit = new ArrayList<String>();
	static String UploadFileName;

	/**
	 * <p>
	 * This method is used to fetch text or map file from local folder to Edit User
	 * can edit Continents, Countries and adjacency links between Countries. File is
	 * already loaded in Default local folder location User can select from existing
	 * files
	 * 
	 * @param desktop
	 *            This is to bind the InternalFrame with Main window frame
	 * 
	 */

	public static void fetchMap(JDesktopPane desktop) {
		HashMap<String, List<String>> continentHashMap = new HashMap<String, List<String>>();
		HashMap<String, Integer> continentControlValueHashMap = new HashMap<String, Integer>();
		final String strTerritory = "[Territories]";
		final String strMap = "[Map]";
		final String strContinent = "[Continents]";
		JInternalFrame jframeUpload = new JInternalFrame("Upload");
		jframeUpload.setSize(150, 70);
		jframeUpload.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		JButton buttonSelectFile = new JButton("Select File");
		buttonSelectFile.setBounds(10, 10, 80, 30);

		// Button to upload file and populate in Default Model.
		// This will call Edit Map method after fetching data

		buttonSelectFile.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent ae) {
				JFileChooser fileChooser = new JFileChooser();
				fileChooser.setBounds(10, 20, 10, 10);
				int returnValue = fileChooser.showOpenDialog(null);
				if (returnValue == JFileChooser.APPROVE_OPTION) {
					jframeUpload.setVisible(false);
					File selectedFile = fileChooser.getSelectedFile();
					UploadFileName = selectedFile.getName();
					String FileFormat = FilenameUtils.getExtension(UploadFileName);

					try {
						Scanner scanner = new Scanner(selectedFile);
						List<String> Maplist = new ArrayList<>();
						String line = "";
						while (scanner.hasNext()) {
							line = scanner.nextLine();
							Maplist.add(line);
						}
						scanner.close();
						if ((Maplist.contains("[Map]") && Maplist.contains("[Continents]")
								&& Maplist.contains("[Territories]"))
								&& (FileFormat.matches("map") || (FileFormat.matches("txt")))) {

							for (int i = 0; i < Maplist.size(); i++) {
								if (Maplist.get(i).startsWith(strMap.trim())) {
								}
								if (Maplist.get(i).startsWith(strContinent.trim())) {

									for (int j = i + 1; j <= 20; j++) {
										if ((Maplist.get(j).isEmpty())) {
											break;
										}
										String strContinentCount = Maplist.get(j);
										String[] arrayContinentCount = strContinentCount.split("=");
										continentControlValueHashMap.put(arrayContinentCount[0],
												Integer.parseInt(arrayContinentCount[1]));
									}
								}
								if (Maplist.get(i).startsWith(strTerritory.trim())) {

									for (int temp = i + 1; temp < Maplist.size(); temp++) {

										if ((Maplist.get(temp).isEmpty())) {

										} else {
											String strMapList = Maplist.get(temp);
											String[] arrayMapList = strMapList.split(",");
											String[] adjListArray = Arrays.copyOfRange(arrayMapList, 4,
													arrayMapList.length);
											List<String> adjCountries = new ArrayList<>();
											adjCountries.addAll(Arrays.asList(adjListArray));
											StringJoiner joiner = new StringJoiner(",");
											joiner.add(arrayMapList[3]).add(arrayMapList[0]);
											String concatString = joiner.toString();
											continentHashMap.put(concatString, adjCountries);
										}
									}
								}
							}
							editMap(continentHashMap, continentControlValueHashMap, desktop);

						} else {
							if (!((FileFormat.equals("map") || (FileFormat.equals("txt"))))) {
								JOptionPane.showMessageDialog(null, "File extension is wrong", "Upload Error",
										JOptionPane.ERROR_MESSAGE);
							} else if (!(Maplist.contains("[Map]") && Maplist.contains("[Continents]")
									&& Maplist.contains("[Territories]"))) {

							}
						}
						// scanner.close();
					} catch (FileNotFoundException e) {
						e.printStackTrace();
					}
				}
			}
		});

		jframeUpload.add(buttonSelectFile);
		jframeUpload.setVisible(true);
		desktop.add(jframeUpload);
	}

	/**
	 * <p>
	 * This method is used to Edit Map attributes like Continents, Countries and
	 * adjacency links After update, changes is saved to same text files which is
	 * loaded initially
	 * 
	 * @param continentHashMap
	 *            Contains all attribute values
	 * 
	 * @param continentControlValueHashMap
	 *            Contains Continent and its control value.
	 * 
	 * @param desktop
	 *            This is to bind the InternalFrame with Main window frame
	 * 
	 */

	public static void editMap(HashMap<String, List<String>> continentHashMap,
			HashMap<String, Integer> continentControlValueHashMap, JDesktopPane desktop) {

		continentHashMapToEdit = continentHashMap;
		continentControlValueHashMapToEdit = continentControlValueHashMap;
		JTable tableContinent = new JTable();
		JLabel labelContinentToEdit = new JLabel("Continent");
		labelContinentToEdit.setBounds(20, 260, 60, 25);
		JTextField textContinentToEdit = new JTextField();
		textContinentToEdit.setBounds(150, 260, 100, 25);
		JLabel labelCountryToEdit = new JLabel("Country");
		labelCountryToEdit.setBounds(20, 290, 60, 25);
		JTextField textCountryToEdit = new JTextField();
		textCountryToEdit.setBounds(150, 290, 100, 25);
		JLabel labelContinentControlValueToEdit = new JLabel("Control Value");
		labelContinentControlValueToEdit.setBounds(20, 350, 130, 25);
		JTextField textContinentControlValueToEdit = new JTextField();
		textContinentControlValueToEdit.setBounds(150, 350, 50, 25);
		JLabel labelAdjListToEdit = new JLabel("Adjacent Countries");
		labelAdjListToEdit.setBounds(20, 320, 130, 25);
		JTextField textAdjListToEdit = new JTextField();
		textAdjListToEdit.setBounds(150, 320, 200, 25);
		JLabel labelAdjMessageToEdit = new JLabel("<-----Please Enter Comma seperated");
		labelAdjMessageToEdit.setForeground(Color.RED);
		labelAdjMessageToEdit.setBounds(360, 320, 300, 25);
		JButton btnAddAll = new JButton("Add");
		btnAddAll.setBounds(600, 260, 100, 25);
		JButton btnDeleteContinent = new JButton("Delete Continent");
		btnDeleteContinent.setBounds(600, 290, 200, 25);
		JButton btnDeleteCountry = new JButton("Delete Country");
		btnDeleteCountry.setBounds(600, 320, 200, 25);
		JButton btnSaveEditedMap = new JButton("Save");
		btnSaveEditedMap.setBounds(600, 350, 200, 25);

		// Populates fetched value from file to model

		for (Map.Entry<String, Integer> temp : continentControlValueHashMapToEdit.entrySet()) {
			continentListToEdit.add(temp.getKey());
		}

		for (String obj : continentListToEdit) {
			for (Map.Entry<String, List<String>> entry : continentHashMapToEdit.entrySet()) {
				String strKey = entry.getKey();
				String[] strKeyArrayToEdit = strKey.split(",");
				if (obj.equals(strKeyArrayToEdit[0])) {
					String printWithoutBraces = entry.getValue().toString().replaceAll("(^\\[|\\s|\\]$)", "");
					modelToEdit.addRow(new Object[] { strKeyArrayToEdit[0], strKeyArrayToEdit[1], printWithoutBraces });
				}
			}
		}

		// Button to add all attribute values to Map

		btnAddAll.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				String strContinentToAdd = textContinentToEdit.getText().trim().toLowerCase();
				String strCountryToAdd = textCountryToEdit.getText().trim().toLowerCase();
				String strAdjListToAdd = textAdjListToEdit.getText().toLowerCase();
				String strControlValueToAdd = textContinentControlValueToEdit.getText().trim();
				String strContinentCapitalize = strContinentToAdd.substring(0, 1).toUpperCase()
						+ strContinentToAdd.substring(1);
				String strCountryCapitalize = strCountryToAdd.substring(0, 1).toUpperCase()
						+ strCountryToAdd.substring(1);
				List<String> listAdjCountryToAdd = new ArrayList<String>();
				List<String> listAdjCountrytoAddCapitalize = new ArrayList<String>();
				listAdjCountryToAdd = new ArrayList<String>(Arrays.asList(strAdjListToAdd.split(",")));
				for (String capital : listAdjCountryToAdd) {
					capital = capital.substring(0, 1).toUpperCase() + capital.substring(1);
					listAdjCountrytoAddCapitalize.add(capital);
				}
				int continetControlValue = Integer.parseInt(strControlValueToAdd);
				StringJoiner joiner = new StringJoiner(",");
				joiner.add(strContinentCapitalize).add(strCountryCapitalize);
				String concatString = joiner.toString();
				modelToEdit.addRow(new Object[] { strContinentCapitalize, strCountryCapitalize,
						listAdjCountrytoAddCapitalize, continetControlValue });
				continentHashMapToEdit.put(concatString, listAdjCountrytoAddCapitalize);
				continentControlValueHashMapToEdit.put(strContinentCapitalize, continetControlValue);
				textCountryToEdit.setText(null);
				textAdjListToEdit.setText(null);
				textContinentControlValueToEdit.setText(null);
			}
		});

		// Button to Delete Continent. Selects value in row and deletes continent
		// completely

		btnDeleteContinent.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				int row = tableContinent.getSelectedRow();
				String strtextContinentToDelete = modelToEdit.getValueAt(row, 0).toString();
				List<String> ToDelete = new ArrayList<String>();
				for (Map.Entry<String, List<String>> iterate : continentHashMap.entrySet()) {
					String strKey = iterate.getKey();
					String[] strKeyArrayToEdit = strKey.split(",");
					if (strKeyArrayToEdit[0].equals(strtextContinentToDelete)) {
						ToDelete.add(strKey);
					}
				}

				for (String str : ToDelete) {
					continentHashMap.remove(str);
				}
				continentControlValueHashMapToEdit.remove(strtextContinentToDelete);
				// continentHashMapToEdit.remove(strtextContinentToDelete);
				// modelToEdit.removeRow(row);
				reloadModel();
			}
		});

		// Button to Delete Country. Selects value in row and deletes Country and its
		// adjacency completely

		btnDeleteCountry.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				int row = tableContinent.getSelectedRow();
				String strtextContinentToDelete = modelToEdit.getValueAt(row, 0).toString();
				String strtextCountryToDelete = modelToEdit.getValueAt(row, 1).toString();
				String Key = strtextContinentToDelete + "," + strtextCountryToDelete;
				continentHashMap.remove(Key);

				Iterator<Map.Entry<String, List<String>>> iter = continentHashMap.entrySet().iterator();
				while (iter.hasNext()) {
					Map.Entry<String, List<String>> entry = iter.next();
					List<String> list = entry.getValue();
					for (int i = 0; i < list.size(); i++) {
						if (list.get(i).equals(strtextCountryToDelete)) {
							list.remove(i);
						}
					}
				}
				reloadModel();
			}
		});

		btnSaveEditedMap.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub

				// TODO Auto-generated method stub
				FileWriter fstream;
				BufferedWriter out;
				List<String> continentListToPrint = new ArrayList<String>();
				String textFileName;
				textFileName = UploadFileName;
				String textFileNameToSave = textFileName.split("\\.", 2)[0];
				try {

					fstream = new FileWriter("C:/Users/Khashyap/Documents/Maps/" + textFileName);
					out = new BufferedWriter(fstream);
					out.write("[Map]");
					out.write(System.getProperty("line.separator"));
					out.write(System.getProperty("line.separator"));
					out.write("[Continents]");
					out.write(System.getProperty("line.separator"));
					for (Map.Entry<String, Integer> temp : continentControlValueHashMapToEdit.entrySet()) {

						continentListToPrint.add(temp.getKey());
						out.write(temp.getKey() + "=" + temp.getValue());
						out.write(System.getProperty("line.separator"));
					}
					out.write(System.getProperty("line.separator"));
					out.write("[Territories]");
					out.write(System.getProperty("line.separator"));
					for (String obj : continentListToPrint) {
						for (Map.Entry<String, List<String>> iterate : continentHashMapToEdit.entrySet()) {
							String strKey = iterate.getKey();
							String[] strKeyArray = strKey.split(",");
							if (obj.equals(strKeyArray[0])) {
								String printWithoutBraces = iterate.getValue().toString().replaceAll("(^\\[|\\s|\\]$)",
										"");
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
				JOptionPane.showMessageDialog(null, textFileNameToSave + " \t is saved");

			}
		});

		tableContinent.setModel(modelToEdit);
		JScrollPane panel = new JScrollPane(tableContinent);
		panel.setVisible(true);
		panel.setBounds(0, 0, 780, 250);
		jframeContinent = new JInternalFrame("Add Continent");
		jframeContinent.setLayout(null);
		jframeContinent.add(panel);
		jframeContinent.add(labelContinentToEdit);
		jframeContinent.add(labelCountryToEdit);
		jframeContinent.add(labelContinentControlValueToEdit);
		jframeContinent.add(labelAdjListToEdit);
		jframeContinent.add(labelAdjMessageToEdit);
		jframeContinent.add(textContinentToEdit);
		jframeContinent.add(textCountryToEdit);
		jframeContinent.add(textContinentControlValueToEdit);
		jframeContinent.add(textAdjListToEdit);
		jframeContinent.add(btnAddAll);
		jframeContinent.add(btnDeleteContinent);
		jframeContinent.add(btnDeleteCountry);
		jframeContinent.add(btnSaveEditedMap);
		jframeContinent.setSize(800, 600);
		jframeContinent.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		jframeContinent.setVisible(true);
		desktop.add(jframeContinent);
	}

	/**
	 * <p>
	 * This method is used to reload Data model after changes in HashMap value
	 * 
	 */

	public static void reloadModel() {
		modelToEdit.setRowCount(0);
		List<String> tempList = new ArrayList<String>();
		for (Map.Entry<String, Integer> temp : continentControlValueHashMapToEdit.entrySet()) {

			tempList.add(temp.getKey());

		}

		for (String obj : tempList) {

			for (Map.Entry<String, List<String>> entry : continentHashMapToEdit.entrySet()) {
				String strKey = entry.getKey();
				String[] strKeyArrayToEdit = strKey.split(",");
				if (obj.equals(strKeyArrayToEdit[0])) {
					String printWithoutBraces = entry.getValue().toString().replaceAll("(^\\[|\\s|\\]$)", "");
					modelToEdit.addRow(new Object[] { strKeyArrayToEdit[0], strKeyArrayToEdit[1], printWithoutBraces });
				}

			}
		}
	}
}
