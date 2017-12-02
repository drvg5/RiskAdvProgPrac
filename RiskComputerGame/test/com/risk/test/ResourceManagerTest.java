package com.risk.test;

import static org.junit.Assert.assertEquals;

import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.TreeSet;

import org.junit.Test;

import com.risk.model.ResourceManager;
import com.risk.model.SaveAndLoadGame;

public class ResourceManagerTest {

	@SuppressWarnings("null")
	@Test
	public void saveMainMap() {
		boolean checknow = false;
		SaveAndLoadGame objSaveAndLoadGame = new SaveAndLoadGame();
		HashMap<String, List<String>> mainMapToCheck = new HashMap<String, List<String>>();
		List<String> listToCheck = new ArrayList<String>();
		listToCheck.add("test");
		mainMapToCheck.put("Check1", listToCheck);
		objSaveAndLoadGame.mainMapToSave = mainMapToCheck;
		try {
			ResourceManager.save(objSaveAndLoadGame, "SaveMainMap");
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		File f = new File("C:\\Users\\Khashyap\\Documents\\GitLocalRepo\\RiskComputerGame\\SaveMainMap");
		if (f.exists()) {
			checknow = true;
		} else {
		}
		assertEquals(true, checknow);
	}

	@Test
	public void saveStrategies() {
		boolean checknow = false;
		SaveAndLoadGame objSaveAndLoadGame = new SaveAndLoadGame();
		HashMap<Integer, String> strategiesToTest = new HashMap<Integer, String>();
		strategiesToTest.put(1, "Test");
		objSaveAndLoadGame.strategiesToSave = strategiesToTest;
		try {
			ResourceManager.save(objSaveAndLoadGame, "SaveStrategies");
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		File checkFile = new File("C:\\Users\\Khashyap\\Documents\\GitLocalRepo\\RiskComputerGame\\SaveStrategies");
		if (checkFile.exists()) {
			checknow = true;
		} else {
		}
		assertEquals(true, checknow);
	}

	@Test
	public void saveNumberOfPlayers() {
		boolean checknow = false;
		SaveAndLoadGame objSaveAndLoadGame = new SaveAndLoadGame();
		int i = 2;
		objSaveAndLoadGame.currentPlyrIndexToSave = i;
		try {
			ResourceManager.save(objSaveAndLoadGame, "numberofplayers");
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		File checkFile = new File("C:\\Users\\Khashyap\\Documents\\GitLocalRepo\\RiskComputerGame\\numberofplayers");
		if (checkFile.exists()) {
			checknow = true;
		} else {
		}
		assertEquals(true, checknow);

	}

	@Test
	public void savePlyrStrategy() {
		boolean checknow = false;
		SaveAndLoadGame objSaveAndLoadGame = new SaveAndLoadGame();
		String now = "cheater";
		objSaveAndLoadGame.currentPlyrStrategyToSave = now;
		try {
			ResourceManager.save(objSaveAndLoadGame, "PlyrStrategy");
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		File checkFile = new File("C:\\Users\\Khashyap\\Documents\\GitLocalRepo\\RiskComputerGame\\PlyrStrategy");
		if (checkFile.exists()) {
			checknow = true;
		} else {
		}
		assertEquals(true, checknow);
	}
	
	
	@Test
	public void saveState() {
		boolean checknow = false;
		SaveAndLoadGame objSaveAndLoadGame = new SaveAndLoadGame();
		String nowState = "Reinforcement";
		objSaveAndLoadGame.state = nowState;
		try {
			ResourceManager.save(objSaveAndLoadGame, "State");
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		File checkFile = new File("C:\\Users\\Khashyap\\Documents\\GitLocalRepo\\RiskComputerGame\\State");
		if (checkFile.exists()) {
			checknow = true;
		} else {
		}
		assertEquals(true, checknow);
	}

	
	@Test
	public void savetotalTerr() {
		boolean checknow = false;
		SaveAndLoadGame objSaveAndLoadGame = new SaveAndLoadGame();
		int j = 5;
		objSaveAndLoadGame.totalTerrToSave = j;
		try {
			ResourceManager.save(objSaveAndLoadGame, "totalTerr");
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		File checkFile = new File("C:\\Users\\Khashyap\\Documents\\GitLocalRepo\\RiskComputerGame\\totalTerr");
		if (checkFile.exists()) {
			checknow = true;
		} else {
		}
		assertEquals(true, checknow);

	}
	
	
	
	@Test
	public void checkCurrentPlayer() {
		boolean checknow = false;
		SaveAndLoadGame objSaveAndLoadGame = new SaveAndLoadGame();
		int k = 4;
		objSaveAndLoadGame.currentPlyrIndexToSave = k;
		try {
			ResourceManager.save(objSaveAndLoadGame, "currentPlayer");
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		File checkFile = new File("C:\\Users\\Khashyap\\Documents\\GitLocalRepo\\RiskComputerGame\\currentPlayer");
		if (checkFile.exists()) {
			checknow = true;
		} else {
		}
		assertEquals(true, checknow);

	}
	
	
	@Test
	public void saveControlValueMap() {
		boolean checknow = false;
		SaveAndLoadGame objSaveAndLoadGame = new SaveAndLoadGame();
		HashMap<String, Integer> continentControlValueHashMapToCheck = new HashMap<String, Integer>();
		continentControlValueHashMapToCheck.put("TestControl", 1);
		objSaveAndLoadGame.continentControlValueHashMapToSave = continentControlValueHashMapToCheck;
		try {
			ResourceManager.save(objSaveAndLoadGame, "SaveControlMap");
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		File f = new File("C:\\Users\\Khashyap\\Documents\\GitLocalRepo\\RiskComputerGame\\SaveControlMap");
		if (f.exists()) {
			checknow = true;
		} else {
		}
		assertEquals(true, checknow);
	}
	
	
	@Test
	public void saveCountryTaken() {
		boolean checknow = false;
		SaveAndLoadGame objSaveAndLoadGame = new SaveAndLoadGame();
		TreeSet<String> countryToCheck = new TreeSet<String>();
		countryToCheck.add("Canada");
		objSaveAndLoadGame.countryTakenToSave = countryToCheck;
		try {
			ResourceManager.save(objSaveAndLoadGame, "CountryTaken");
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		File f = new File("C:\\Users\\Khashyap\\Documents\\GitLocalRepo\\RiskComputerGame\\CountryTaken");
		if (f.exists()) {
			checknow = true;
		} else {
		}
		assertEquals(true, checknow);
	}
	
	
	@Test
	public void saveReinforcement() {
		boolean checknow = false;
		SaveAndLoadGame objSaveAndLoadGame = new SaveAndLoadGame();
		HashMap<String, Integer> reinforcementToCheck = new HashMap<String, Integer>();
		reinforcementToCheck.put("Testreinfo", 2);
		objSaveAndLoadGame.reinforcementToSave = reinforcementToCheck;
		try {
			ResourceManager.save(objSaveAndLoadGame, "reinforcement");
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		File f = new File("C:\\Users\\Khashyap\\Documents\\GitLocalRepo\\RiskComputerGame\\reinforcement");
		if (f.exists()) {
			checknow = true;
		} else {
		}
		assertEquals(true, checknow);
	}
	
	
}
