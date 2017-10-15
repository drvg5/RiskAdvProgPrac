package com.map.play;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.beans.PropertyVetoException;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Scanner;
import java.util.StringJoiner;
import javax.swing.JButton;
import javax.swing.JDesktopPane;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JInternalFrame;

import org.apache.commons.io.FilenameUtils;

public class PlayGame {

	public static void main(String[] args) {
	}

	public static void LoadMap(JDesktopPane desktop) {
		HashMap<String, List<String>> continentHashMap = new HashMap<String, List<String>>();
		HashMap<String, Integer> continentCount = new HashMap<String, Integer>();
		final String strTerritory = "[Territories]";
		final String strMap = "[Map]";
		final String strContinet = "[Continents]";
		JInternalFrame jframeUpload = new JInternalFrame("Upload");
		jframeUpload.setLayout(null);

		jframeUpload.setSize(300, 300);
		jframeUpload.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		JButton buttonSelectFile = new JButton("Select File");
		buttonSelectFile.setBounds(100, 100, 100, 30);
		JButton buttonCloseUpload = new JButton("Close");
		buttonCloseUpload.setBounds(100, 150, 100, 30);
		jframeUpload.add(buttonSelectFile);
		jframeUpload.add(buttonCloseUpload);
		jframeUpload.setVisible(true);
		desktop.add(jframeUpload);

		buttonSelectFile.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent ae) {
				JFileChooser fileChooser = new JFileChooser();
				fileChooser.setBounds(10, 20, 30, 100);
 
				int returnValue = fileChooser.showOpenDialog(null);
				if (returnValue == JFileChooser.APPROVE_OPTION) {
					File selectedFile = fileChooser.getSelectedFile();
					String UploadFileName = selectedFile.getName();
					String FileFormat = FilenameUtils.getExtension(UploadFileName);
					System.out.println("\nUploadFileName:" + UploadFileName);

					try {
						Scanner scanner = new Scanner(selectedFile);
						List<String> Maplist = new ArrayList<>();
						String line = "";
						while (scanner.hasNext()) {
							line = scanner.nextLine();
							Maplist.add(line);
						}
						scanner.close();
						if (Maplist.contains("[Map]") && Maplist.contains("[Continents]")
								&& Maplist.contains("[Territories]") && Maplist.get(2).contains("bmp")
								&& FileFormat.matches("map")) {

							for (int i = 0; i < Maplist.size(); i++) {
								if (Maplist.get(i).startsWith(strMap.trim())) {
								}
								if (Maplist.get(i).startsWith(strContinet.trim())) {

									for (int j = i + 1; j <= 20; j++) {
										if ((Maplist.get(j).isEmpty())) {
											break;
										}
										String strContinentCount = Maplist.get(j);
										String[] arrayContinentCount = strContinentCount.split("=");
										continentCount.put(arrayContinentCount[0],
												Integer.parseInt(arrayContinentCount[1]));
									}
									System.out.println(continentCount);
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
							System.out.println("\nMap Status:");
							System.out.println("\nMap is uploaded successfully");
							System.out.println(continentHashMap);

						} else {
							if (!(FileFormat.matches("map"))) {
								System.out.println("\nFile format(map) is not correct");
							} else if (!(Maplist.contains("[Map]") && Maplist.contains("[Continents]")
									&& Maplist.contains("[Territories]"))) {
								System.out.println("\nFile does not contain either Territories or Continents or Maps");
							} else if (!(Maplist.get(2).contains("bmp"))) {
								System.out.println("\nMap does not contain bmp image");
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
}