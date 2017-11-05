package com.risk.model;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.StringJoiner;

import javax.swing.JInternalFrame;
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
	
	
