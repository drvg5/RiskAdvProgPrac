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

import javax.swing.JOptionPane;

import org.apache.commons.io.FilenameUtils;

import com.risk.ui.EditMapUI;


/**
 * <h1>Edit Map Model</h1>
 * <p>
 * <b>This class consists static methods to save map file to local folder </b>
 * User would be able to configure Adjacency between Countries.
 * <p>
 * 
 * @author Khashyap
 * @version 1.0
 */

public class EditMapModel {

	/** object created to class EditMapUI */
	EditMapUI editMapUI = new EditMapUI();

	/** Hash map to edit the Map attributes */
	static HashMap<String, List<String>> continentHashMapToEdit = new HashMap<String, List<String>>();

	/** Hash map to edit control value of particular Continent. */
	static HashMap<String, Integer> continentControlValueHashMapToEdit = new HashMap<String, Integer>();

	/** List to edit Continents. */
	static List<String> continentListToEdit = new ArrayList<String>();

	/** To store file uploaded by User */
	static String UploadFileName;

	/** List to check for any duplicates */

	static List<String> checkDuplicates = new ArrayList<String>();

	public static boolean checkForCallingEditUI = true;
	
	public static boolean junitMapEditValidation;

	/**
	 * <p>
	 * This method is used to fetch text or map file from local folder to Edit User
	 * can edit Continents, Countries and adjacency links between Countries. File is
	 * already loaded in Default local folder location User can select from existing
	 * files
	 *
	 * @param returnValue
	 *            Checks for successful upload
	 * @param file
	 *            File uploaded by User
	 * 
	 */

	public void getMapFile(int returnValue, File file) {
		final HashMap<String, List<String>> continentHashMap = new HashMap<String, List<String>>();
		final HashMap<String, Integer> continentControlValueHashMap = new HashMap<String, Integer>();
		final String strTerritory = "[Territories]";
		final String strMap = "[Map]";
		final String strContinent = "[Continents]";

		boolean checkDuplicate;

		if (returnValue == 0) {
			if (checkForCallingEditUI) {
			editMapUI.closeUploadForEdit();
			}
			UploadFileName = file.getName();
			String FileFormat = FilenameUtils.getExtension(UploadFileName);
			if (!(UploadFileName.isEmpty()))
				try {
					Scanner scanner = new Scanner(file);
					List<String> Maplist = new ArrayList<>();
					String line = "";
					List<String> connectivityCheckEdit = new ArrayList<String>();
					List<String> continentListCheckEdit = new ArrayList<String>();
					boolean checkConnectedEdit = false;
					while (scanner.hasNext()) {
						line = scanner.nextLine();
						Maplist.add(line);
					}
					scanner.close();

					if (!((FileFormat.equals("map") || (FileFormat.equals("txt"))))) {
						if (checkForCallingEditUI) {
							editMapUI.showErrorMessageForEditMap(1);
							editMapUI.showUploadForEdit();
						}
					}

					else if (!((Maplist.contains("[Map]") && Maplist.contains("[Continents]")
							&& Maplist.contains("[Territories]")))) {
						if (checkForCallingEditUI) {
							editMapUI.showErrorMessageForEditMap(2);
							editMapUI.showUploadForEdit();
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
									String strContinentCapitalize = arrayContinentCount[0].substring(0, 1).toUpperCase()
											+ arrayContinentCount[0].substring(1);
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
											if (checkForCallingEditUI) {
												editMapUI.showErrorMessageForEditMap(4);
											}
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

						Iterator<Map.Entry<String, List<String>>> iter = continentHashMap.entrySet().iterator();
						while (iter.hasNext()) {
							Map.Entry<String, List<String>> entry = iter.next();
							String strKey = entry.getKey();
							String[] strKeyArrayToCheck = strKey.split(",");
							Iterator<Map.Entry<String, List<String>>> innerIter = continentHashMap.entrySet()
									.iterator();
							while (innerIter.hasNext()) {
								Map.Entry<String, List<String>> innerEntry = innerIter.next();
								String strinnerKey = innerEntry.getKey();
								String[] strinnerKeyArrayToCheck = strinnerKey.split(",");
								List<String> innerList = innerEntry.getValue();
								if (!((strKeyArrayToCheck[0]).equals(strinnerKeyArrayToCheck[0]))) {
									for (int inner = 0; inner < innerList.size(); inner++) {
										if (strKeyArrayToCheck[1].equals(innerList.get(inner))) {
											if (!(connectivityCheckEdit.contains(strKeyArrayToCheck[1]))) {
												connectivityCheckEdit.add(strKeyArrayToCheck[0]);
											}
										}
									}
								}
							}

						}

						for (Map.Entry<String, List<String>> entry : continentHashMap.entrySet()) {
							String strKey = entry.getKey();
							String[] strKeyArrayToEdit = strKey.split(",");
							if (!(continentListCheckEdit.contains(strKeyArrayToEdit[0]))) {
								continentListCheckEdit.add(strKeyArrayToEdit[0]);
							}
						}

						int sizeOfCont = continentListCheckEdit.size();
						Set<String> hs = new HashSet<>();
						hs.addAll(connectivityCheckEdit);
						connectivityCheckEdit.clear();
						connectivityCheckEdit.addAll(hs);
						Collection<String> collectionAdjCont = connectivityCheckEdit;
						Collection<String> collectionTotalCont = continentListCheckEdit;
						collectionTotalCont.removeAll(collectionAdjCont);
						if (collectionTotalCont.isEmpty() || sizeOfCont == 1) {
							checkConnectedEdit = true;
						}

						if (continentHashMap.isEmpty()) {
							if (checkForCallingEditUI) {
								editMapUI.showUploadForEdit();
							}

						} else if (!(checkConnectedEdit)) {
							if (checkForCallingEditUI) {
								editMapUI.showErrorMessageForEditMap(3);
							}

						} else {
							junitMapEditValidation = true;
							if (checkForCallingEditUI) {
								editMapUI.editMap(continentHashMap, continentControlValueHashMap);
							}
						}
					}

				} catch (FileNotFoundException e) {
					e.printStackTrace();
				}

		}

	}

	/**
	 * <p>
	 * This method is used to add any new continent or country to existing map file.
	 *
	 * @param strContinentCapitalize
	 *            Consists of continent with first letter in capital
	 * @param strCountryCapitalize
	 *            Consists of country with first letter in capital
	 * @param continetControlValue
	 *            Continent control value
	 * @param listAdjCountrytoAddCapitalize
	 *            Consists of adjacency list with first letter in capital
	 * @param listCountryToCheck
	 *            Consists of country list to validate
	 * @param continentHashMapToEdit
	 *            Hashmap to edit
	 * @return Hashmap after editing
	 */

	public HashMap<String, List<String>> addDataEnteredForEditMap(String strContinentCapitalize,
			String strCountryCapitalize, int continetControlValue, List<String> listAdjCountrytoAddCapitalize,
			List<String> listCountryToCheck, HashMap<String, List<String>> continentHashMapToEdit) {

		List<String> listEmpty = new ArrayList<String>();
		String getAllKeys;
		String[] getIndividual;
		StringJoiner joiner = new StringJoiner(",");
		joiner.add(strContinentCapitalize).add(strCountryCapitalize);
		String concatString = joiner.toString();
		continentHashMapToEdit.put(concatString, listAdjCountrytoAddCapitalize);
		listCountryToCheck.add(strCountryCapitalize);

		for (String str : listAdjCountrytoAddCapitalize) {
			String conc = strContinentCapitalize + "," + str;
			if (!(listCountryToCheck.contains(str))) {
				if (!(continentHashMapToEdit.containsKey(conc))) {
					continentHashMapToEdit.put(conc, listEmpty);
					listCountryToCheck.add(str);
				}
			}
		}
		for (String tempAdj : listAdjCountrytoAddCapitalize) {

			for (Map.Entry<String, List<String>> maplist : continentHashMapToEdit.entrySet()) {
				List<String> fetchLinksFromHashMap = new ArrayList<String>();
				List<String> fetchLinksToAddHashMap = new ArrayList<String>();
				getAllKeys = maplist.getKey();
				getIndividual = getAllKeys.split(",");
				if (getIndividual[1].equals(tempAdj)) {
					fetchLinksFromHashMap = continentHashMapToEdit.get(getAllKeys);
					if (!fetchLinksFromHashMap.equals(listEmpty)) {
						fetchLinksToAddHashMap.addAll(fetchLinksFromHashMap);
					}
					fetchLinksToAddHashMap.add(strCountryCapitalize);
					continentHashMapToEdit.replace(getAllKeys, fetchLinksToAddHashMap);
				}
			}

		}
		return continentHashMapToEdit;
	}

	/**
	 * <p>
	 * This method is used to delete continent selected by user.
	 *
	 * @param strtextContinentToDelete
	 *            Continent value to delete
	 * @param continentHashMapToEdit
	 *            the continent hash map to edit
	 * @return continentHashMapToEdit Hashmap value after deletion of Continent
	 */

	public HashMap<String, List<String>> deleteContinent(String strtextContinentToDelete,
			HashMap<String, List<String>> continentHashMapToEdit) {
		List<String> ToDelete = new ArrayList<String>();
		List<String> ToDeleteInOther = new ArrayList<String>();
		for (Map.Entry<String, List<String>> iterate : continentHashMapToEdit.entrySet()) {
			String strKey = iterate.getKey();
			String[] strKeyArrayToEdit = strKey.split(",");
			if (strKeyArrayToEdit[0].equals(strtextContinentToDelete)) {
				ToDelete.add(strKey);
				ToDeleteInOther.add(strKeyArrayToEdit[1]);
			}
		}

		for (String str : ToDelete) {
			continentHashMapToEdit.remove(str);
		}

		Iterator<Map.Entry<String, List<String>>> iter = continentHashMapToEdit.entrySet().iterator();
		while (iter.hasNext()) {
			Map.Entry<String, List<String>> entry = iter.next();
			List<String> list = entry.getValue();
			for (String strTo : ToDeleteInOther) {
				for (int i = 0; i < list.size(); i++) {
					if (list.get(i).equals(strTo)) {
						list.remove(i);
					}
				}
			}
		}

		return continentHashMapToEdit;

	}

	/**
	 * <p>
	 * This method is used to delete Country selected by user.
	 *
	 * @param strtextContinentToDelete
	 *            Continent value to delete
	 * @param strtextCountryToDelete
	 *            Country value to delete
	 * @param continentHashMapToEdit
	 *            the continent hash map to edit
	 * @return continentHashMapToEdit Hashmap value after deletion of Country
	 */

	public HashMap<String, List<String>> deleteCountry(String strtextContinentToDelete, String strtextCountryToDelete,
			HashMap<String, List<String>> continentHashMapToEdit) {
		StringJoiner joiner = new StringJoiner(",");
		joiner.add(strtextContinentToDelete).add(strtextCountryToDelete);
		String concatString = joiner.toString();
		// String Key = strtextContinentToDelete + "," + strtextCountryToDelete;
		continentHashMapToEdit.remove(concatString);

		Iterator<Map.Entry<String, List<String>>> iter = continentHashMapToEdit.entrySet().iterator();
		while (iter.hasNext()) {
			Map.Entry<String, List<String>> entry = iter.next();
			List<String> list = entry.getValue();
			for (int i = 0; i < list.size(); i++) {
				if (list.get(i).equals(strtextCountryToDelete)) {
					list.remove(i);
				}
			}
		}
		return continentHashMapToEdit;

	}

	/**
	 * <p>
	 * This method is used to delete adjacency selected by user.
	 *
	 * @param conti
	 *            Continent value to delete
	 * @param countr
	 *            Country value to delete
	 * @param listAdjCountryToRemoveCapitalize
	 *            List of adjacent countries to be deleted
	 * @param continentHashMapToEdit
	 *            the continent hash map to edit
	 * @return continentHashMapToEdit Hash map value after deletion of Adjacency
	 */

	public HashMap<String, List<String>> deleteAdjacency(String conti, String countr,
			List<String> listAdjCountryToRemoveCapitalize, HashMap<String, List<String>> continentHashMapToEdit) {
		StringJoiner joiner = new StringJoiner(",");
		joiner.add(conti).add(countr);
		String concatString = joiner.toString();

		for (String str : listAdjCountryToRemoveCapitalize) {

			continentHashMapToEdit.get(concatString).remove(str);
		}

		for (String tempVar : listAdjCountryToRemoveCapitalize) {
			Iterator<Map.Entry<String, List<String>>> iter = continentHashMapToEdit.entrySet().iterator();
			while (iter.hasNext()) {
				Map.Entry<String, List<String>> entry = iter.next();
				String strKey = entry.getKey();
				String[] strKeyArrayToDelete = strKey.split(",");
				if (strKeyArrayToDelete[1].equals(tempVar)) {
					continentHashMapToEdit.get(strKey).remove(countr);
				}
			}
		}
		return continentHashMapToEdit;
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
