package com.risk.model;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Scanner;
import java.util.Set;
import java.util.StringJoiner;

import org.apache.commons.io.FilenameUtils;

import com.risk.ui.UploadMapUI;


/**
 * <h1>Parse MapFile Model</h1>
 * <p>
 * <b>This class consists methods to read map file and save to HashMap for game
 * play </b>
 * <p>
 * .
 *
 * @author Khashyap
 * @version 1.0
 */

public class ParseMapFileModel {

	/** Hash map to store Map attributes */
	HashMap<String, List<String>> continentHashMap = new HashMap<String, List<String>>();

	/** Hash map to check control value of Continent */
	HashMap<String, Integer> continentCount = new HashMap<String, Integer>();

	/** List to check duplicates. */
	List<String> checkDuplicates = new ArrayList<String>();

	/** Object creation for UploadMapUI */
	UploadMapUI uploadMapUI = new UploadMapUI();

	/** To check Territory values in uploaded file */
	final String strTerritory = "[Territories]";

	/** To check Map values in uploaded file */
	final String strMap = "[Map]";

	/** To check Continent values in uploaded file */
	final String strContinent = "[Continents]";

	public static boolean junitMapValidation;

	public static boolean checkForCallingUI = true;

	/**
	 * <p>
	 * This method is used to fetch text or map file from local folder and store in
	 * Hash map to play.
	 *
	 * @param returnValue            Checks for successful upload
	 * @param file            File selected by User
	 */

	public void getMapFile(int returnValue, File file) {

		boolean checkDuplicate;

		if (returnValue == 0) {
			String UploadFileName = file.getName();
			String FileFormat = FilenameUtils.getExtension(UploadFileName);
			List<String> connectivityCheck = new ArrayList<String>();
			List<String> connectivityCheckCountry = new ArrayList<String>();
			List<String> continentListCheckNow = new ArrayList<String>();
			List<String> countryListCheckNow = new ArrayList<String>();
			boolean checkConnected = false;
			boolean checkConnectedCountry = false;
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
					if (checkForCallingUI) {
						uploadMapUI.showErrorMessageForUpload(1);

					}
				}

				else if (!((Maplist.contains("[Map]") && Maplist.contains("[Continents]")
						&& Maplist.contains("[Territories]")))) {
					if (checkForCallingUI) {
						uploadMapUI.showErrorMessageForUpload(2);
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
										if (checkForCallingUI) {
											uploadMapUI.showErrorMessageForUpload(4);
										}

										checkDuplicates.clear();
										continentHashMap.clear();
										break mainloop;
									}
									if (checkForCallingUI) {
										uploadMapUI.closeUpload();

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

					// Check for connectivity between Continents
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

					// Check for connectivity between Countries in Continents
					Iterator<Map.Entry<String, List<String>>> iterCountry = continentHashMap.entrySet().iterator();
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

					// Current Continent List
					for (Map.Entry<String, List<String>> entry : continentHashMap.entrySet()) {
						String strKey = entry.getKey();
						String[] strKeyArrayToEdit = strKey.split(",");
						if (!(continentListCheckNow.contains(strKeyArrayToEdit[0]))) {
							continentListCheckNow.add(strKeyArrayToEdit[0]);
						}
					}

					// Current Country list
					for (Map.Entry<String, List<String>> entry : continentHashMap.entrySet()) {
						String strKeyForCountry = entry.getKey();
						String[] strCountry = strKeyForCountry.split(",");
						if (!(countryListCheckNow.contains(strCountry[1]))) {
							countryListCheckNow.add(strCountry[1]);
						}
					}

					// Logic to build Collection to check connectivity between Continents
					int sizeOfCont = continentListCheckNow.size();
					Set<String> hs = new HashSet<>();
					hs.addAll(connectivityCheck);
					connectivityCheck.clear();
					connectivityCheck.addAll(hs);
					Collection<String> collectionAdjCont = connectivityCheck;
					Collection<String> collectionTotalCont = continentListCheckNow;
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

					if (continentHashMap.isEmpty()) {
					}

					else if (!(checkConnected)) {
						if (checkForCallingUI) {
							uploadMapUI.showErrorMessageForUpload(3);
						}

					}

					else if (!(checkConnectedCountry)) {
						if (checkForCallingUI) {
							uploadMapUI.showErrorMessageForUpload(4);
						}
					}

					else {
						junitMapValidation = true;
						System.out
								.println("---------------------------------------------------------------------------");
						System.out.println("*******************MAP ATTRIBUTES****************************");
						System.out
								.println("---------------------------------------------------------------------------");
						System.out.println("*CONTINENT & COUNTRY*      *ADJACENCY LIST*");
						System.out
								.println("---------------------------------------------------------------------------");
						for (Map.Entry<String, List<String>> toPrint : continentHashMap.entrySet()) {
							System.out.println(toPrint.getKey() + "\t" + "\t" + toPrint.getValue());
						}
						// new GameDriverNew().gameStart(continentHashMap, continentCount);
						new GameDriverNew().gameModeSingle(continentHashMap, continentCount);

					}
				}

			} catch (FileNotFoundException e) {
				e.printStackTrace();
			}
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

	private List<String> fetchCountries(String toCheck) {
		// TODO Auto-generated method stub
		List<String> toSend = new ArrayList<String>();
		for (Map.Entry<String, List<String>> entryPopulate : continentHashMap.entrySet()) {

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

	/**
	 * <p>
	 * This method is used to check any duplicates.
	 *
	 * @param listContainingDuplicates
	 *            List to check
	 * @return sendToValidate boolean value after checking condition
	 */

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
