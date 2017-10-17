package com.map.editor;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
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
					// System.out.println("\nUploadFileName:" + UploadFileName);

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

	public static void editMap(HashMap<String, List<String>> continentHashMapToEdit,
			HashMap<String, Integer> continentControlValueHashMapToEdit, JDesktopPane desktop) {

		JTable tableContinent = new JTable();
		List<String> continentList = new ArrayList<String>();
		List<String> continentListToEdit = new ArrayList<String>();
		DefaultTableModel modelContinent = new DefaultTableModel(
				new Object[] { "Continent List", "Country", "Adjacent List" }, 0);
		JLabel labelContinent = new JLabel("Continent");
		labelContinent.setBounds(20, 260, 60, 25);
		JTextField textContinent = new JTextField();
		textContinent.setBounds(150, 260, 100, 25);
		JLabel labelCountry = new JLabel("Country");
		labelCountry.setBounds(20, 290, 60, 25);
		JTextField textCountry = new JTextField();
		textCountry.setBounds(150, 290, 100, 25);
		JLabel labelContinentControlValue = new JLabel("Control Value");
		labelContinentControlValue.setBounds(20, 350, 130, 25);
		JTextField textContinentControlValue = new JTextField();
		textContinentControlValue.setBounds(150, 350, 50, 25);
		JLabel labelAdjList = new JLabel("Adjacent Countries");
		labelAdjList.setBounds(20, 320, 130, 25);
		JTextField textAdjList = new JTextField();
		textAdjList.setBounds(150, 320, 200, 25);
		JLabel labelAdjMessage = new JLabel("<-----Please Enter Comma seperated");
		labelAdjMessage.setForeground(Color.RED);
		labelAdjMessage.setBounds(360, 320, 300, 25);
		JButton btnAddAll = new JButton("Add");
		btnAddAll.setBounds(600, 260, 100, 25);
		JButton btnDelete = new JButton("Update");
		btnDelete.setBounds(600, 290, 100, 25);
		JButton btnSave = new JButton("Delete");
		btnSave.setBounds(600, 320, 100, 25);
		for (Map.Entry<String, Integer> temp : continentControlValueHashMapToEdit.entrySet()) {

			continentListToEdit.add(temp.getKey());

		}

		for (String obj : continentListToEdit) {

			for (Map.Entry<String, List<String>> entry : continentHashMapToEdit.entrySet()) {
				String strKey = entry.getKey();
				String[] strKeyArrayToEdit = strKey.split(",");
				if (obj.equals(strKeyArrayToEdit[0])) {
					String printWithoutBraces = entry.getValue().toString().replaceAll("(^\\[|\\s|\\]$)", "");
					modelContinent
							.addRow(new Object[] { strKeyArrayToEdit[0], strKeyArrayToEdit[1], printWithoutBraces });
				}

			}
		}
		tableContinent.setModel(modelContinent);
		JScrollPane panel = new JScrollPane(tableContinent);
		panel.setVisible(true);
		panel.setBounds(0, 0, 780, 250);
		jframeContinent = new JInternalFrame("Add Continent");
		jframeContinent.setLayout(null);
		jframeContinent.add(panel);
		jframeContinent.add(labelContinent);
		jframeContinent.add(labelCountry);
		jframeContinent.add(labelContinentControlValue);
		jframeContinent.add(labelAdjList);
		jframeContinent.add(labelAdjMessage);
		jframeContinent.add(textContinent);
		jframeContinent.add(textCountry);
		jframeContinent.add(textContinentControlValue);
		jframeContinent.add(textAdjList);
		jframeContinent.add(btnAddAll);
		jframeContinent.add(btnSave);
		jframeContinent.add(btnDelete);
		jframeContinent.setSize(800, 600);
		jframeContinent.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		jframeContinent.setVisible(true);
		desktop.add(jframeContinent);
	}
}
