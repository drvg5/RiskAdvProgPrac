package com.risk.model;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.StringJoiner;

import javax.swing.JDesktopPane;

import com.risk.ui.ConfigureMapUI;
import com.risk.ui.SaveMapUponConfigUI;


/**
 * <h1>Configure Map Model</h1>
 * <p>
 * <b>This class consists methods to create Continents and Countries.</b>
 * <p>
 * 
 * @author Khashyap
 * @author drvg5 - modified class to implement Modified MVC architecture
 * 
 */

public class ConfigureMapModel {

	/** Object created for ConfigureMapUI class */
	ConfigureMapUI configureMapUI = new ConfigureMapUI();

	/** HashMap to store Continent Control Value */
	static HashMap<String, Integer> continentControlValueHashMap = new HashMap<String, Integer>();

	/** List to check duplicate country. */
	static List<String> listToCheckDuplicateCountry = new ArrayList<String>();

	/** List consists of Continent and Country before assigning adjacency */
	static List<String> listKeyForHashMap = new ArrayList<String>();

	/** List to check duplicate continent. */
	static List<String> listToCheckDuplicateContinent = new ArrayList<String>();

	/** List to check duplicate Countries with first letter in Capital */
	static List<String> listToCheckDuplicateCountryCapitalize = new ArrayList<String>();

	/** List to check duplicate Continents with first letter in Capital */
	static List<String> listToCheckDuplicateContinentCapitalize = new ArrayList<String>();

	/** To store Continent */
	String strContinent;

	/** To store Country */
	String strCountry;

	/** To store Continent Control Value */
	String strControlValue;
	
	/** Unit Testing */
	public static boolean junitUpdateOnClick = false;
	
	 
	/**
	 * <p>
	 * This method is used to add continents and countries to Map. User can key in
	 * the list of Continents and Countries one by one Continent Control Value can
	 * also be added
	 *
	 * @param data
	 *            String array which consists of Continents,Countries and Control
	 *            value
	 * @return the string[] returns all data after storing in variables
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
			if (!(listToCheckDuplicateContinentCapitalize.contains(strContinentCapitalize))) {
				listToCheckDuplicateContinentCapitalize.add(strContinentCapitalize);
			}
			if (!(listToCheckDuplicateCountryCapitalize.contains(strCountryCapitalize))) {
				listToCheckDuplicateCountryCapitalize.add(strCountryCapitalize);
			}
			if (!(listToCheckDuplicateContinent.contains(strContinent))) {
				listToCheckDuplicateContinent.add(strContinent);
			}
			if (!(listToCheckDuplicateCountry.contains(strCountry))) {
				listToCheckDuplicateCountry.add(strCountry);
			}
			dataReturned[0] = strContinentCapitalize;
			dataReturned[1] = strCountryCapitalize;
			dataReturned[2] = strControlValue;

		}
		return dataReturned;

	}

	/**
	 * <p>
	 * This method is used to remove any incorrect entry while configuring
	 * continents and countries to Map.
	 * 
	 * @param data
	 *            String array which consists of Continents,Countries and Control
	 *            value
	 * 
	 */

	public void updateOnClick(String[] data) {
		listKeyForHashMap.remove(data[0]);
		listToCheckDuplicateContinent.remove(data[1]);
		listToCheckDuplicateCountry.remove(data[2]);
		listToCheckDuplicateContinentCapitalize.remove(data[3]);
		listToCheckDuplicateCountryCapitalize.remove(data[4]);
		continentControlValueHashMap.remove(data[5]);
		junitUpdateOnClick=true;
	}

	/**
	 * <p>
	 * This method is used to update incorrect entry while configuring continents
	 * and countries to Map.
	 * 
	 * @param data
	 *            String array which consists of Continents,Countries and Control
	 *            value
	 * 
	 */

	public void updateOnClickOfButton(String[] data) {

		listKeyForHashMap.add(data[0]);
		int continetControlValue = Integer.parseInt(data[2]);
		continentControlValueHashMap.put(data[1], continetControlValue);
		listToCheckDuplicateContinent.add(data[3]);
		listToCheckDuplicateCountry.add(data[4]);
		listToCheckDuplicateContinentCapitalize.add(data[5]);
	}

	/**
	 * <p>
	 * This method will pass input of configured continents and countries to
	 * SaveMapConfigUI Class .
	 *
	 * @param desktop
	 *            To bind the InternalFrame with Main window frame
	 */

	public void inputForAdjacency(JDesktopPane desktop) {
		if ((strContinent.isEmpty()) || (strCountry.isEmpty()) || (strControlValue.isEmpty())) {
			configureMapUI.showErrorMessageForSaving(1);
		} else {
			SaveMapUponConfigUI.saveToFile(listKeyForHashMap, continentControlValueHashMap,
					listToCheckDuplicateContinentCapitalize, listToCheckDuplicateCountryCapitalize, desktop);
		}

	}

}
