package com.risk.model;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.StringJoiner;

import javax.swing.JInternalFrame;
<<<<<<< HEAD
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;

import com.risk.ui.SaveMapUponEditUI;

public class SaveMapUponEditModel {

	static String strContinentSelectedInRow;
	static String strCountrySelectedInRow;

	public void removeRecord(String strtextContinentToUpdate, String strtextCountryToUpdate,
			String strtextAdjToUpdate) {
		int count = 0;

		String strtextContinentToChangeCapitalize = strtextContinentToUpdate.substring(0, 1).toUpperCase()
				+ strtextContinentToUpdate.substring(1);
		String strtextCountryToChangeCapitalize = strtextCountryToUpdate.substring(0, 1).toUpperCase()
				+ strtextCountryToUpdate.substring(1);
		List<String> listAdjCountryToRemove = new ArrayList<String>();
		List<String> listAdjCountryToRemoveCapitalize = new ArrayList<String>();
		listAdjCountryToRemove = new ArrayList<String>(Arrays.asList(strtextAdjToUpdate.split(",")));
		StringJoiner joinerToDelete = new StringJoiner(",");
		for (String capital : listAdjCountryToRemove) {
			capital = capital.substring(0, 1).toUpperCase() + capital.substring(1);
			listAdjCountryToRemoveCapitalize.add(capital);
		}

		joinerToDelete.add(strtextContinentToChangeCapitalize).add(strtextCountryToChangeCapitalize);
		String concatStringToDelete = joinerToDelete.toString();

		for (String str : listAdjCountryToRemoveCapitalize) {

			SaveMapUponEditUI.hashMapToUpdate.get(concatStringToDelete).remove(str);
		}

		for (String tempVar : listAdjCountryToRemoveCapitalize) {
			Iterator<Map.Entry<String, List<String>>> iter = SaveMapUponEditUI.hashMapToUpdate.entrySet().iterator();
			while (iter.hasNext()) {
				Map.Entry<String, List<String>> entry = iter.next();
				String strKey = entry.getKey();
				String[] strKeyArrayToDelete = strKey.split(",");
				if (strKeyArrayToDelete[1].equals(tempVar)) {
					SaveMapUponEditUI.hashMapToUpdate.get(strKey).remove(strtextCountryToChangeCapitalize);
				}
			}
		}

		strContinentSelectedInRow = strtextContinentToChangeCapitalize;
		strCountrySelectedInRow = strtextCountryToChangeCapitalize;
		for (int t = 0; t < SaveMapUponEditUI.modelToUpdate.getRowCount(); t++) {
			if (SaveMapUponEditUI.modelToUpdate.getValueAt(t, 0).equals(strtextContinentToChangeCapitalize)) {
				count++;
			}
		}
		if (count < 2) {
			SaveMapUponEditUI.hashMapControlToUpdate.remove(strtextContinentToChangeCapitalize);
		}
		SaveMapUponEditUI.hashMapToUpdate.remove(concatStringToDelete);

	}

	public void updateRecord(String strAdjToUpdate, String strContinentToUpdate, String strCountryToUpdate,
			String strContinentControlValueToUpdate) {
		boolean checkCountry = false;
		boolean checkEqual = false;
		List<String> listAdjCountry = new ArrayList<String>();
		List<String> listAdjCountryCapitalize = new ArrayList<String>();
		listAdjCountry = new ArrayList<String>(Arrays.asList(strAdjToUpdate.split(",")));
		for (String capital : listAdjCountry) {
			capital = capital.substring(0, 1).toUpperCase() + capital.substring(1);
			listAdjCountryCapitalize.add(capital);
		}
		String strContinentToUpdateCapitalize = strContinentToUpdate.substring(0, 1).toUpperCase()
				+ strContinentToUpdate.substring(1);
		String strCountryToUpdateCapitalize = strCountryToUpdate.substring(0, 1).toUpperCase()
				+ strCountryToUpdate.substring(1);

		for (String st : listAdjCountryCapitalize) {
			if (st.equals(strCountryToUpdateCapitalize)) {
				checkEqual = true;
			}
		}

		if (!(SaveMapUponEditUI.countryList.containsAll(listAdjCountryCapitalize))) {
			checkCountry = true;
		}

		if (checkEqual) {
			new SaveMapUponEditUI().errorMessageForUpdate(1);
		}

		else if (checkCountry) {
			new SaveMapUponEditUI().errorMessageForUpdate(2);
		}

		else {
			SaveMapUponEditUI.continentList.remove(strContinentSelectedInRow);
			SaveMapUponEditUI.countryList.remove(strContinentSelectedInRow);
			StringJoiner joinerToUpdateHashMap = new StringJoiner(",");
			joinerToUpdateHashMap.add(strContinentToUpdateCapitalize).add(strCountryToUpdateCapitalize);
			String concatString = joinerToUpdateHashMap.toString();
			int continetControlValue = Integer.parseInt(strContinentControlValueToUpdate);
			SaveMapUponEditUI.hashMapControlToUpdate.put(strContinentToUpdateCapitalize, continetControlValue);
			SaveMapUponEditUI.hashMapToUpdate.put(concatString, listAdjCountryCapitalize);
			for (String tempAdj : listAdjCountryCapitalize) {

				for (Map.Entry<String, List<String>> maplist : SaveMapUponEditUI.hashMapToUpdate.entrySet()) {
					List<String> fetchLinksFromHashMap = new ArrayList<String>();
					List<String> fetchLinksToAddHashMap = new ArrayList<String>();
					String getAllKeys = maplist.getKey();
					String[] getIndividual = getAllKeys.split(",");
					if (getIndividual[1].equals(tempAdj)) {
						fetchLinksFromHashMap = SaveMapUponEditUI.hashMapToUpdate.get(getAllKeys);
						fetchLinksToAddHashMap.addAll(fetchLinksFromHashMap);
						fetchLinksToAddHashMap.add(strCountryToUpdateCapitalize);
						SaveMapUponEditUI.hashMapToUpdate.replace(getAllKeys, fetchLinksToAddHashMap);
					}
				}

			}

			if (!(SaveMapUponEditUI.continentList.contains(strContinentToUpdateCapitalize))) {
				SaveMapUponEditUI.continentList.add(strContinentToUpdateCapitalize);
			}

			if (!(SaveMapUponEditUI.countryList.contains(strCountryToUpdateCapitalize))) {
				SaveMapUponEditUI.countryList.add(strCountryToUpdateCapitalize);
			}

			SaveMapUponEditUI.reloadModel();
		}

	}
}
=======
import javax.swing.table.DefaultTableModel;

import com.risk.ui.SaveMapUponEditUI;

public class SaveMapUponEditModel {
	
	static List<String> continentList = new ArrayList<String>();
	static List<String> countryList = new ArrayList<String>();
	static String strContinentSelectedInRow;
	static String strCountrySelectedInRow;
	
	public void removeRecord(String strtextContinentToUpdate,String strtextCountryToUpdate,String strtextAdjToUpdate)
	{
		int count = 0;

		String strtextContinentToChangeCapitalize = strtextContinentToUpdate.substring(0, 1).toUpperCase()
				+ strtextContinentToUpdate.substring(1);
		String strtextCountryToChangeCapitalize = strtextCountryToUpdate.substring(0, 1).toUpperCase()
				+ strtextCountryToUpdate.substring(1);
		List<String> listAdjCountryToRemove = new ArrayList<String>();
		List<String> listAdjCountryToRemoveCapitalize = new ArrayList<String>();
		listAdjCountryToRemove = new ArrayList<String>(Arrays.asList(strtextAdjToUpdate.split(",")));
 		StringJoiner joinerToDelete = new StringJoiner(",");
		for (String capital : listAdjCountryToRemove) {
			capital = capital.substring(0, 1).toUpperCase() + capital.substring(1);
			listAdjCountryToRemoveCapitalize.add(capital);
		}

		joinerToDelete.add(strtextContinentToChangeCapitalize).add(strtextCountryToChangeCapitalize);
		String concatStringToDelete = joinerToDelete.toString();

		for (String str : listAdjCountryToRemoveCapitalize) {

			SaveMapUponEditUI.hashMapToUpdate.get(concatStringToDelete).remove(str);
		}

		for (String tempVar : listAdjCountryToRemoveCapitalize) {
			Iterator<Map.Entry<String, List<String>>> iter = SaveMapUponEditUI.hashMapToUpdate.entrySet().iterator();
			while (iter.hasNext()) {
				Map.Entry<String, List<String>> entry = iter.next();
				String strKey = entry.getKey();
				String[] strKeyArrayToDelete = strKey.split(",");
				if (strKeyArrayToDelete[1].equals(tempVar)) {
					SaveMapUponEditUI.hashMapToUpdate.get(strKey).remove(strtextCountryToChangeCapitalize);
				}
			}
		}

		strContinentSelectedInRow = strtextContinentToChangeCapitalize;
		strCountrySelectedInRow = strtextCountryToChangeCapitalize;
		for (int t = 0; t < SaveMapUponEditUI.modelToUpdate.getRowCount(); t++) {
			if (SaveMapUponEditUI.modelToUpdate.getValueAt(t, 0).equals(strtextContinentToChangeCapitalize)) {
				count++;
			}
		}
		if (count < 2) {
			SaveMapUponEditUI.hashMapControlToUpdate.remove(strtextContinentToChangeCapitalize);
		}
		SaveMapUponEditUI.hashMapToUpdate.remove(concatStringToDelete);
	
	}
}
	
	
>>>>>>> branch 'common' of https://github.com/drvg5/RiskAdvProgPrac.git
