package com.risk.model;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.beans.PropertyVetoException;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Scanner;
import java.util.Set;
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
			new Object[] { "Continent List", "Country", "Adjacent List", "Control Value" }, 0);
	static HashMap<String, List<String>> continentHashMapToEdit = new HashMap<String, List<String>>();
	static HashMap<String, Integer> continentControlValueHashMapToEdit = new HashMap<String, Integer>();
	static List<String> continentListToEdit = new ArrayList<String>();
	static String UploadFileName;
	static List<String> checkDuplicates = new ArrayList<String>();

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
		jframeUpload.setLayout(null);
		jframeUpload.setSize(300, 300);
		jframeUpload.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		JButton buttonSelectFile = new JButton("Upload");
		buttonSelectFile.setBounds(100, 100, 100, 30);
		JButton buttonCloseUpload = new JButton("Close");
		buttonCloseUpload.setBounds(100, 150, 100, 30);
		jframeUpload.add(buttonSelectFile);
		jframeUpload.add(buttonCloseUpload);
		jframeUpload.setVisible(true);
		desktop.add(jframeUpload);

		// Button to upload file and populate in Default Model.
		// This will call Edit Map method after fetching data

		buttonSelectFile.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent ae) {
				boolean checkDuplicate;
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

						if (!((FileFormat.equals("map") || (FileFormat.equals("txt"))))) {
							JOptionPane.showMessageDialog(null, "Invalid Map!File extension is wrong", "Upload Error",
									JOptionPane.ERROR_MESSAGE);
							jframeUpload.setVisible(true);
						}

						else if (!((Maplist.contains("[Map]") && Maplist.contains("[Continents]")
								&& Maplist.contains("[Territories]")))) {

							JOptionPane.showMessageDialog(null,
									"Invalid Map! File is missing Map or Continent or Territory section",
									"Upload Error", JOptionPane.ERROR_MESSAGE);
							jframeUpload.setVisible(true);

						} else {
							mainloop: for (int i = 0; i < Maplist.size(); i++) {
								if (Maplist.get(i).startsWith(strMap.trim())) {
								}
								if (Maplist.get(i).startsWith(strContinent.trim())) {

									for (int j = i + 1; j <= 20; j++) {
										if ((Maplist.get(j).isEmpty())) {
											break;
										}
										String strContinentCount = Maplist.get(j);
										String[] arrayContinentCount = strContinentCount.split("=");
										String strContinentCapitalize = arrayContinentCount[0].substring(0, 1)
												.toUpperCase() + arrayContinentCount[0].substring(1);
										continentControlValueHashMap.put(strContinentCapitalize,
												Integer.parseInt(arrayContinentCount[1]));
									}
								}
								if (Maplist.get(i).startsWith(strTerritory.trim())) {

									for (int temp = i + 1; temp < Maplist.size(); temp++) {

										if ((Maplist.get(temp).isEmpty())) {

										} else {
											String strMapList = Maplist.get(temp);
											String[] arrayMapList = strMapList.split(",");
											checkDuplicates.add(arrayMapList[0]);
											checkDuplicate = findDuplicates(checkDuplicates);
											if (checkDuplicate) {
												JOptionPane.showMessageDialog(null, "Invalid Map! Duplicate Countries",
														"Load Error", JOptionPane.ERROR_MESSAGE);
												checkDuplicates.clear();
												continentHashMap.clear();
												break mainloop;
											}
											String[] adjListArray = Arrays.copyOfRange(arrayMapList, 4,
													arrayMapList.length);
											List<String> adjCountries = new ArrayList<>();
											adjCountries.addAll(Arrays.asList(adjListArray));
											List<String> adjCountriesCapitalize = new ArrayList<>();
											for (String capital : adjCountries) {
												capital = capital.substring(0, 1).toUpperCase() + capital.substring(1);
												adjCountriesCapitalize.add(capital);
											}
											StringJoiner joiner = new StringJoiner(",");
											String strContinetCapitalize = arrayMapList[3].substring(0, 1).toUpperCase()
													+ arrayMapList[3].substring(1);
											;
											String strCountryCapitalize = arrayMapList[0].substring(0, 1).toUpperCase()
													+ arrayMapList[0].substring(1);
											;
											joiner.add(strContinetCapitalize).add(strCountryCapitalize);
											String concatString = joiner.toString();
											continentHashMap.put(concatString, adjCountriesCapitalize);
										}
									}
								}
							}

							if (continentHashMap.isEmpty()) {
								jframeUpload.setVisible(true);
							} else {
								editMap(continentHashMap, continentControlValueHashMap, desktop);
							}
						}

					} catch (FileNotFoundException e) {
						e.printStackTrace();
					}
				}
			}
		});

		buttonCloseUpload.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				try {
					jframeUpload.setClosed(true);
				} catch (PropertyVetoException e1) {
					e1.printStackTrace();
				}
			}
		});
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
		List<String> listContinentToCheck = new ArrayList<String>();
		List<String> listCountryToCheck = new ArrayList<String>();
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
		btnAddAll.setBounds(100, 400, 100, 25);
		JButton btnUpdate = new JButton("Update");
		btnUpdate.setBounds(200, 400, 100, 25);
		JButton btnDeleteContinent = new JButton("Delete Continent");
		btnDeleteContinent.setBounds(570, 270, 180, 25);
		JButton btnDeleteCountry = new JButton("Delete Country");
		btnDeleteCountry.setBounds(570, 310, 180, 25);
		JButton btnDeleteAdj = new JButton("Delete Adjacency");
		btnDeleteAdj.setBounds(570, 350, 180, 25);
		JButton btnSaveEditedMap = new JButton("Save");
		btnSaveEditedMap.setBounds(570, 390, 180, 25);

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
					modelToEdit.addRow(new Object[] { strKeyArrayToEdit[0], strKeyArrayToEdit[1], printWithoutBraces,
							continentControlValueHashMapToEdit.get(strKeyArrayToEdit[0]) });
				}
			}
		}

		for (Map.Entry<String, List<String>> hash : continentHashMapToEdit.entrySet()) {
			String stringKeyToCheck = hash.getKey();
			String[] strKeyArrayToCheck = stringKeyToCheck.split(",");
			listContinentToCheck.add(strKeyArrayToCheck[0]);
			listCountryToCheck.add(strKeyArrayToCheck[1]);
		}

		// Button to add all attribute values to Map

		btnAddAll.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				boolean checkCountry = false;
				String strContinentToAdd = textContinentToEdit.getText().trim().toLowerCase();
				String strCountryToAdd = textCountryToEdit.getText().trim().toLowerCase();
				String strAdjListToAdd = textAdjListToEdit.getText().toLowerCase();
				String strControlValueToAdd = textContinentControlValueToEdit.getText().trim();

				if ((strContinentToAdd.isEmpty()) || (strCountryToAdd.isEmpty()) || (strAdjListToAdd.isEmpty())
						|| (strControlValueToAdd.isEmpty())) {
					JOptionPane.showMessageDialog(null, "Oops!Please enter values", "Error", JOptionPane.ERROR_MESSAGE);
				}

				else if (!(strContinentToAdd.matches("^[a-zA-Z]+$")) || !(strCountryToAdd.matches("^[a-zA-Z]+$"))) {
					JOptionPane.showMessageDialog(null, "Only Alphabets are allowed", "Error in Continent and Country",
							JOptionPane.ERROR_MESSAGE);
				}

				else if (!(strControlValueToAdd.matches("^[0-9]+$"))) {
					JOptionPane.showMessageDialog(null, "Only Numbers are  allowed", "Error in Control Value",
							JOptionPane.ERROR_MESSAGE);
				}

				else {

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

					if (!(listCountryToCheck.isEmpty())) {
						for (String str : listCountryToCheck) {
							if (str.equals(strCountryCapitalize)) {
								checkCountry = true;
							}
						}
					}

					if (checkCountry) {
						JOptionPane.showMessageDialog(null, "Invalid Map!" + strCountryCapitalize + " already exists in Country List",
								"Error", JOptionPane.ERROR_MESSAGE);
					}

					else {
						StringJoiner joiner = new StringJoiner(",");
						joiner.add(strContinentCapitalize).add(strCountryCapitalize);
						String concatString = joiner.toString();
						//modelToEdit.addRow(new Object[] { strContinentCapitalize, strCountryCapitalize,
						//		listAdjCountrytoAddCapitalize, continetControlValue });
						continentHashMapToEdit.put(concatString, listAdjCountrytoAddCapitalize);
						continentControlValueHashMapToEdit.put(strContinentCapitalize, continetControlValue);
						
						for (String str:listAdjCountrytoAddCapitalize)
						{
							String conc = strContinentCapitalize + "," + str ;
							List<String> fetchLinks = new ArrayList<String>();
							fetchLinks.add(strCountryCapitalize);
							continentHashMapToEdit.put(conc, fetchLinks);
						}
						reloadModel();
						
						textCountryToEdit.setText(null);
						textAdjListToEdit.setText(null);
						textContinentControlValueToEdit.setText(null);
					}
				}
			}
		});

		// Button to Delete Continent. Selects value in row and deletes continent
		// completely

		btnDeleteAdj.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				
			}
		});
		
		btnUpdate.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				
			}
		});
		
		
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

		// Button to save all edited changes to same Map file

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
				String textFileNameToShow = textFileName.split("\\.", 2)[0];
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
				JOptionPane.showMessageDialog(null, textFileNameToShow + " \t is saved");
				jframeContinent.setVisible(false);
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
		jframeContinent.add(btnUpdate);
		jframeContinent.add(btnDeleteContinent);
		jframeContinent.add(btnDeleteCountry);
		jframeContinent.add(btnDeleteAdj);
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
					modelToEdit.addRow(new Object[] { strKeyArrayToEdit[0], strKeyArrayToEdit[1], printWithoutBraces,continentControlValueHashMapToEdit.get(strKeyArrayToEdit[0]) });
				}

			}
		}
	}

	public static boolean findDuplicates(List<String> listContainingDuplicates) {
		final Set<String> set = new HashSet<String>();
		boolean sendToValidate = false;
		for (String str : listContainingDuplicates) {
			if (!set.add(str)) {
				sendToValidate = true;
			}
		}
		return sendToValidate;
	}
}
