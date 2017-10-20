package com.risk.ui;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.beans.PropertyVetoException;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Scanner;
import java.util.Set;
import java.util.StringJoiner;

import javax.swing.JButton;
import javax.swing.JDesktopPane;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JInternalFrame;
import javax.swing.JOptionPane;

import org.apache.commons.io.FilenameUtils;

public class LoadGame {

	HashMap<String, List<String>> continentHashMap = new HashMap<String, List<String>>();
	HashMap<String, Integer> continentCount = new HashMap<String, Integer>();
	List<String> checkDuplicates = new ArrayList<String>();
	final String strTerritory = "[Territories]";
	final String strMap = "[Map]";
	final String strContinent = "[Continents]";
	JInternalFrame jframeUpload;
	boolean validateProcess = true;

	public void LoadMap(JDesktopPane desktop) {

		jframeUpload = new JInternalFrame("Upload");
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

		buttonSelectFile.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent ae) {
				uploadFileProcess();
			}
		});

		buttonCloseUpload.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				closeUploadDialog();
			}
		});

	}

	public boolean uploadFileProcess() {

		boolean checkDuplicate;

		JFileChooser fileChooser = new JFileChooser();
		fileChooser.setBounds(10, 20, 30, 100);
		int returnValue = fileChooser.showOpenDialog(null);
		if (returnValue == JFileChooser.APPROVE_OPTION) {
			jframeUpload.setVisible(false);
			File selectedFile = fileChooser.getSelectedFile();
			String UploadFileName = selectedFile.getName();
			String FileFormat = FilenameUtils.getExtension(UploadFileName);
			// System.out.println("\nUploadFileName:" + UploadFileName + FileFormat );

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
					validateProcess = false;
					jframeUpload.setVisible(true);
				}

				else if (!((Maplist.contains("[Map]") && Maplist.contains("[Continents]")
						&& Maplist.contains("[Territories]")))) {

					JOptionPane.showMessageDialog(null,
							"Invalid Map! File is missing Map or Continent or Territory section", "Upload Error",
							JOptionPane.ERROR_MESSAGE);
					validateProcess = false;
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
								continentCount.put(arrayContinentCount[0], Integer.parseInt(arrayContinentCount[1]));
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
									String[] adjListArray = Arrays.copyOfRange(arrayMapList, 4, arrayMapList.length);
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

					if (continentHashMap.isEmpty()) {
						jframeUpload.setVisible(true);
					} else {
						 System.out.println(continentHashMap);

					}
				}

			} catch (FileNotFoundException e) {
				e.printStackTrace();
			}
		}
		return validateProcess;

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

	public boolean closeUploadDialog() {
		try {
			jframeUpload.setClosed(true);
		} catch (PropertyVetoException e1) {
			validateProcess = false;
			e1.printStackTrace();
		}
		return validateProcess;
	}
}
