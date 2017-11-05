package com.risk.model;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.StringJoiner;

import javax.swing.JDesktopPane;

import com.risk.ui.ConfigureMapUI;
import com.risk.ui.SaveMapUponConfigUI;

/**
 * <h1>Configure Map</h1>
 * <p>
 * <b>This class consists methods to create Continents and Countries.</b>
 * <p>
 * 
 * @author Khashyap
 * @author drvg5 - modified class to implement Modified MVC architecture
 * 
 */

public class ConfigureMapModel {

	ConfigureMapUI configureMapUI = new ConfigureMapUI();
	static HashMap<String, Integer> continentControlValueHashMap = new HashMap<String, Integer>();
	static List<String> listToCheckDuplicateCountry = new ArrayList<String>();
	static List<String> listKeyForHashMap = new ArrayList<String>();
	static List<String> listToCheckDuplicateContinent = new ArrayList<String>();
	static List<String> listToCheckDuplicateCountryCapitalize = new ArrayList<String>();
	static List<String> listToCheckDuplicateContinentCapitalize = new ArrayList<String>();
	String strContinent;
	String strCountry;
	String strControlValue;

	/**
	 * <p>
	 * This method is used to add continents and countries to Map. User can key in
	 * the list of Continents and Countries one by one Continent Control Value can
	 * also be added
	 * 
	 * @param data
	 * 
	 * 
	 */

	public String[] addContinentsAndCountries(String[] data) {

		strContinent = data[0];
		strCountry = data[1];
		strControlValue = data[2];
		String[] dataReturned = new String[3];
		boolean checkCountry = false;
		boolean checkContinentWithCountry = false;
		boolean checkCountryWithContinent = false;

		if (!(listToCheckDuplicateCountry.isEmpty())) {
			for (String str : listToCheckDuplicateCountry) {
				if (str.equals(strCountry)) {
					checkCountry = true;
				}
			}
		}

		if (!(listToCheckDuplicateContinent.isEmpty())) {
			for (String temp : listToCheckDuplicateContinent) {
				if (temp.equals(strCountry)) {
					checkContinentWithCountry = true;
				}
			}
		}

		if (!(listToCheckDuplicateCountry.isEmpty())) {
			for (String temp : listToCheckDuplicateCountry) {
				if (temp.equals(strContinent)) {
					checkCountryWithContinent = true;
				}
			}
		}

		if ((strContinent.isEmpty()) || (strCountry.isEmpty()) || (strControlValue.isEmpty())) {
			configureMapUI.showErrorMessageForSaving(1);
		}

		else if (!(strContinent.matches("^[a-zA-Z]+$")) || !(strCountry.matches("^[a-zA-Z]+$"))) {
			configureMapUI.showErrorMessageForSaving(2);
		}

		else if (checkCountry) {
			configureMapUI.showErrorMessageForSaving(3);
		}

		else if (checkCountryWithContinent) {

			configureMapUI.showErrorMessageForSaving(4);
		}

		else if (checkContinentWithCountry) {
			configureMapUI.showErrorMessageForSaving(5);
		}

		else if (strContinent.equals(strCountry)) {
			configureMapUI.showErrorMessageForSaving(6);
		}

		else if (!(strControlValue.matches("^[0-9]+$"))) {
			configureMapUI.showErrorMessageForSaving(7);
		} else if (!(strControlValue.matches("^[0-9]+$"))) {
			configureMapUI.showErrorMessageForSaving(7);
		}

		else {
			String strContinentCapitalize = strContinent.substring(0, 1).toUpperCase() + strContinent.substring(1);
			String strCountryCapitalize = strCountry.substring(0, 1).toUpperCase() + strCountry.substring(1);
			int continetControlValue = Integer.parseInt(strControlValue);
			StringJoiner joiner = new StringJoiner(",");
			joiner.add(strContinentCapitalize).add(strCountryCapitalize);
			String concatString = joiner.toString();
			listKeyForHashMap.add(concatString);
			continentControlValueHashMap.put(strContinentCapitalize, continetControlValue);
			listToCheckDuplicateContinentCapitalize.add(strContinentCapitalize);
			listToCheckDuplicateCountryCapitalize.add(strCountryCapitalize);
			listToCheckDuplicateContinent.add(strContinent);
			listToCheckDuplicateCountry.add(strCountry);

			dataReturned[0] = strContinentCapitalize;
			dataReturned[1] = strCountryCapitalize;
			dataReturned[2] = strControlValue;

		}
		return dataReturned;

	}

	public void updateOnClick(String[] data) {
		listKeyForHashMap.remove(data[0]);
		listToCheckDuplicateContinent.add(data[1]);
		listToCheckDuplicateCountry.remove(data[2]);
		listToCheckDuplicateContinentCapitalize.add(data[3]);
		listToCheckDuplicateCountryCapitalize.add(data[4]);
		continentControlValueHashMap.remove(data[5]);
	}

	public void updateOnClickOfButton(String[] data) {

		listKeyForHashMap.add(data[0]);
		int continetControlValue = Integer.parseInt(data[2]);
		continentControlValueHashMap.put(data[1], continetControlValue);
		listToCheckDuplicateContinent.add(data[3]);
		listToCheckDuplicateCountry.add(data[4]);
		listToCheckDuplicateContinentCapitalize.add(data[5]);
		listToCheckDuplicateCountryCapitalize.add(data[6]);
	}

	public void inputForAdjacency(JDesktopPane desktop) {
		if ((strContinent.isEmpty()) || (strCountry.isEmpty()) || (strControlValue.isEmpty())) {
			configureMapUI.showErrorMessageForSaving(1);
		} else {
			SaveMapUponConfigUI.saveToFile(listKeyForHashMap, continentControlValueHashMap,
					listToCheckDuplicateContinentCapitalize, listToCheckDuplicateCountryCapitalize, desktop);
		}

	}

}
