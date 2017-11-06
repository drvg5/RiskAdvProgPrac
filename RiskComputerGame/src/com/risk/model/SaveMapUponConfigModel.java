package com.risk.model;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.risk.ui.SaveMapUponConfigUI;

public class SaveMapUponConfigModel {

	SaveMapUponConfigUI saveMapUponConfigUI = new SaveMapUponConfigUI();

	public HashMap<String, List<String>> addAdjacencyForCountries(String strHashMapKey, String strAdjListToUpdate,
			List<String> listToCheckDuplicateCountry, HashMap<String, List<String>> mainHashMap) {

		boolean checkExist = false;
		boolean CheckEqual = false;
		boolean doNothing = false;
		String[] strHashMapKeySplit = strHashMapKey.split(",");
		List<String> listAdjCountry = new ArrayList<String>();
		List<String> listAdjCountryCapitalize = new ArrayList<String>();
		listAdjCountry = new ArrayList<String>(Arrays.asList(strAdjListToUpdate.split(",")));
		for (String capital : listAdjCountry) {
			capital = capital.substring(0, 1).toUpperCase() + capital.substring(1);
			listAdjCountryCapitalize.add(capital);
		}

		for (String st : listAdjCountryCapitalize) {
			if (st.equals(strHashMapKeySplit[1])) {
				CheckEqual = true;
			}
		}
		if (!(listToCheckDuplicateCountry.containsAll(listAdjCountryCapitalize))) {
			checkExist = true;
		}

		if (CheckEqual) {
			if (CheckEqual) {
				saveMapUponConfigUI.showErrorMessageAdjacency("checkEqual");

			}
		}

		else if (checkExist) {
			saveMapUponConfigUI.showErrorMessageAdjacency("checkExist");
		}

		else {

			List<String> checkExistingAdj = new ArrayList<String>();
			List<String> empty = new ArrayList<String>();
			checkExistingAdj = mainHashMap.get(strHashMapKey);

			for (String strOut : checkExistingAdj) {
				for (String strIn : listAdjCountryCapitalize) {
					if (strOut.equals(strIn)) {
						doNothing = true;
					}
				}
			}

			if (checkExistingAdj.equals(empty)) {
				mainHashMap.put(strHashMapKey, listAdjCountryCapitalize);
			}

			else if (doNothing) {
			}

			else {

				checkExistingAdj.addAll(listAdjCountryCapitalize);
				mainHashMap.replace(strHashMapKey, checkExistingAdj);
			}

			String getAllKeys;
			String[] getIndividual;
			for (String tempAdj : listAdjCountryCapitalize) {
				if (!(doNothing)) {
					for (Map.Entry<String, List<String>> maplist : mainHashMap.entrySet()) {
						List<String> fetchLinksFromHashMap = new ArrayList<String>();
						List<String> fetchLinksToAddHashMap = new ArrayList<String>();
						getAllKeys = maplist.getKey();
						getIndividual = getAllKeys.split(",");
						if (getIndividual[1].equals(tempAdj)) {
							fetchLinksFromHashMap = mainHashMap.get(getAllKeys);
							if (!fetchLinksFromHashMap.equals(empty)) {
								fetchLinksToAddHashMap.addAll(fetchLinksFromHashMap);
							}
							fetchLinksToAddHashMap.add(strHashMapKeySplit[1]);
							mainHashMap.replace(getAllKeys, fetchLinksToAddHashMap);
						}
					}

				}
			}
		}

		return mainHashMap;
	}

}