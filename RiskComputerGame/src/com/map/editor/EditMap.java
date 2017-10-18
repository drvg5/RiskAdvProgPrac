package com.map.editor;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
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
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;

import org.apache.commons.io.FilenameUtils;

public class EditMap {

	static JInternalFrame jframeContinent = new JInternalFrame();
	static DefaultTableModel modelToEdit = new DefaultTableModel(
			new Object[] { "Continent List", "Country", "Adjacent List" }, 0);
	static HashMap<String, List<String>> continentHashMapToEdit = new HashMap<String, List<String>>();
	static HashMap<String, Integer> continentControlValueHashMapToEdit = new HashMap<String, Integer>();
	static List<String> continentListToEdit = new ArrayList<String>();

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

		buttonSelectFile.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent ae) {
				JFileChooser fileChooser = new JFileChooser();
				fileChooser.setBounds(10, 20, 10, 10);
				int returnValue = fileChooser.showOpenDialog(null);
				if (returnValue == JFileChooser.APPROVE_OPTION) {
					jframeUpload.setVisible(false);
					File selectedFile = fileChooser.getSelectedFile();
					String UploadFileName = selectedFile.getName();
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

							// for (int i = 0; i < Maplist.size(); i++) {
							// System.out.println(Maplist.get(i));
							// if (Maplist.get(i).isEmpty()) {
							// EmptyLinesCount.add(i);
							// }
							// }
							// System.out.println("Empty lines location:" + EmptyLinesCount);

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
							System.out.println(continentHashMap);
							editMap(continentHashMap, continentControlValueHashMap, desktop);

						} else {
							if (!(FileFormat.matches("map"))) {
								System.out.println("\nFile format(map) is not correct");
							} else if (!(Maplist.contains("[Map]") && Maplist.contains("[Continents]")
									&& Maplist.contains("[Territories]"))) {
								System.out.println("\nFile doesnt contain either Territories or Continents or Maps");
							} else if (!(Maplist.get(2).contains("bmp"))) {
								System.out.println("\nMap doest contain bmp image");
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

	public static void editMap(HashMap<String, List<String>> continentHashMap,
			HashMap<String, Integer> continentControlValueHashMap, JDesktopPane desktop) {

		continentHashMapToEdit = continentHashMap;
		continentControlValueHashMapToEdit = continentControlValueHashMap;
		JTable tableContinent = new JTable();
		List<String> continentList = new ArrayList<String>();
		
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
		// check for push
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
				System.out.println(continentHashMapToEdit);
				System.out.println(continentControlValueHashMapToEdit);
				textCountryToEdit.setText(null);
				textAdjListToEdit.setText(null);
				textContinentControlValueToEdit.setText(null);
			}
		});
		
		tableContinent.addMouseListener(new MouseAdapter() {
		});

		btnDeleteContinent.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				int row = tableContinent.getSelectedRow();
				String strtextContinentToDelete = modelToEdit.getValueAt(row, 0).toString();
				for (Map.Entry<String, List<String>> iterate : continentHashMap.entrySet() )
				{
					String strKey = iterate.getKey();
					String[] strKeyArrayToEdit = strKey.split(",");
					if(strKeyArrayToEdit[0].equals(strtextContinentToDelete))
					{
						continentHashMapToEdit.remove(strKey);
					}
				}
				continentControlValueHashMapToEdit.remove(strtextContinentToDelete);
				//continentHashMapToEdit.remove(strtextContinentToDelete);
				//modelToEdit.removeRow(row);
				reloadModel();
				System.out.println("Delete continent" + continentHashMapToEdit);
			}
		});

		btnDeleteCountry.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				int row = tableContinent.getSelectedRow();
				String strtextCountryToDelete = modelToEdit.getValueAt(row, 1).toString();
				for (Map.Entry<String, List<String>> iterate : continentHashMap.entrySet() )
				{
					String key = iterate.getKey();
					List<String> countryToDelete = new ArrayList<String>();
					List<String> valueList = new ArrayList<String>();
					countryToDelete = iterate.getValue();
					for(String s: countryToDelete)
					{
						if(strtextCountryToDelete.equals(s))
						{
							countryToDelete.remove(s);
							valueList = countryToDelete;
						}
					}
					continentHashMap.put(key, valueList);
				}
				reloadModel();
				System.out.println("Deleterow"+ continentHashMap);
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
		jframeContinent.setSize(800, 600);
		jframeContinent.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		jframeContinent.setVisible(true);
		desktop.add(jframeContinent);
	}

	public static void reloadModel() {
		modelToEdit.setRowCount(0);
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
		}
	}
