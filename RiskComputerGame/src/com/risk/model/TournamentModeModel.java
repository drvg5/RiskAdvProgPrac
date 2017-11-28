package com.risk.model;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Scanner;
import java.util.Set;
import java.util.StringJoiner;

import org.apache.commons.io.FilenameUtils;

import com.risk.ui.TournamentModeUI;
import com.risk.ui.UploadMapUI;

public class TournamentModeModel {

	/** To check Territory values in uploaded file */
	final String strTerritory = "[Territories]";

	/** To check Map values in uploaded file */
	final String strMap = "[Map]";

	/** To check Continent values in uploaded file */
	final String strContinent = "[Continents]";
	public static boolean checkForCallingTournamentUI = true;
	TournamentModeUI tournamentModeUI = new TournamentModeUI();
	public static boolean junitMapValidationTournament;

	static LinkedHashMap<String, List<String>> continentHashMapTotal = new LinkedHashMap<String, List<String>>();
	static LinkedHashMap<String, Integer> continentCountTotal = new LinkedHashMap<String, Integer>();

	static int count = 0;

	public void loadHashMap(int returnValue, File file, int intcountOfMapselectedValue, int intcountOfGameselectedValue,
			List<String> selectedPlayerModeList) {
		boolean checkDuplicate;
		HashMap<String, List<String>> continentHashMap = new HashMap<String, List<String>>();
		HashMap<String, Integer> continentCount = new HashMap<String, Integer>();

		if (returnValue == 0) {
			String UploadFileName = file.getName();
			String fileNameWithoutExtension = FilenameUtils.removeExtension(UploadFileName);
			String FileFormat = FilenameUtils.getExtension(UploadFileName);
			List<String> connectivityCheck = new ArrayList<String>();
			List<String> continentListCheckNow = new ArrayList<String>();
			List<String> seperator = new ArrayList<String>();
			seperator.add(fileNameWithoutExtension);
			boolean checkConnected = false;
			List<String> checkDuplicates = new ArrayList<String>();
			try {
				Scanner scanner = new Scanner(file);
				List<String> Maplist = new ArrayList<>();
				String line = "";
				while (scanner.hasNext()) {
					line = scanner.nextLine();
					Maplist.add(line);
				}
				scanner.close();

				if (!((FileFormat.equals("map") || (FileFormat.equals("txt"))))) {
					if (checkForCallingTournamentUI) {
						tournamentModeUI.showErrorMessageForUpload(1);

					}
				}

				else if (!((Maplist.contains("[Map]") && Maplist.contains("[Continents]")
						&& Maplist.contains("[Territories]")))) {
					if (checkForCallingTournamentUI) {
						tournamentModeUI.showErrorMessageForUpload(2);
					}

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
										if (checkForCallingTournamentUI) {
											tournamentModeUI.showErrorMessageForUpload(4);
										}

										checkDuplicates.clear();
										continentHashMap.clear();
										break mainloop;
									}
									if (checkForCallingTournamentUI) {
										// tournamentModeUI.closeUpload();

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

					Iterator<Map.Entry<String, List<String>>> iter = continentHashMap.entrySet().iterator();
					while (iter.hasNext()) {
						Map.Entry<String, List<String>> entry = iter.next();
						String strKey = entry.getKey();
						String[] strKeyArrayToCheck = strKey.split(",");
						Iterator<Map.Entry<String, List<String>>> innerIter = continentHashMap.entrySet().iterator();
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

					for (Map.Entry<String, List<String>> entry : continentHashMap.entrySet()) {
						String strKey = entry.getKey();
						String[] strKeyArrayToEdit = strKey.split(",");
						if (!(continentListCheckNow.contains(strKeyArrayToEdit[0]))) {
							continentListCheckNow.add(strKeyArrayToEdit[0]);
						}
					}

					int sizeOfCont = continentListCheckNow.size();
					Set<String> hs = new HashSet<>();
					hs.addAll(connectivityCheck);
					connectivityCheck.clear();
					connectivityCheck.addAll(hs);
					Collection<String> collectionAdjCont = connectivityCheck;
					Collection<String> collectionTotalCont = continentListCheckNow;
					collectionTotalCont.removeAll(collectionAdjCont);
					if (collectionTotalCont.isEmpty() || sizeOfCont == 1) {
						checkConnected = true;
					}

					if (continentHashMap.isEmpty()) {
					}

					else if (!(checkConnected)) {
						if (checkForCallingTournamentUI) {
							tournamentModeUI.showErrorMessageForUpload(3);
						}

					}

					else {
						count++;
						String split = "split" + count;
						junitMapValidationTournament = true;
						continentHashMapTotal.putAll(continentHashMap);
						continentCountTotal.putAll(continentCount);
						continentHashMapTotal.put(split, seperator);
						continentCountTotal.put(split, count);

						if (count == intcountOfMapselectedValue) {
							System.out.println(continentHashMapTotal);
							System.out.println(continentCountTotal);
							System.out.println(intcountOfMapselectedValue);
							System.out.println(intcountOfGameselectedValue);
							System.out.println(selectedPlayerModeList);
							// new GameDriverNew().gameStart(continentHashMap,
							// continentCount,intcountOfMapselectedValue,intcountOfGameselectedValue,selectedPlayerModeList);
						}
					}
				}

			} catch (FileNotFoundException e) {
				e.printStackTrace();
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
