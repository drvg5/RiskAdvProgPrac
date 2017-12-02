package com.risk.test;

import static org.junit.Assert.assertEquals;

import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import org.junit.Test;

import com.risk.model.ResourceManager;
import com.risk.model.SaveAndLoadGame;

public class GameModeUITest {

	@Test
	public void loadNumberOfPlayers() {
		try {
			boolean checkSave = false;
			SaveAndLoadGame data = (SaveAndLoadGame) ResourceManager.load("numberofplayers");
			int i = data.currentPlyrIndexToSave;
			if(i ==2)
			{
				checkSave = true;
			}
			else
			{
			}
			assertEquals(true, checkSave);
		} catch (Exception exLoad) {

		}
	}

	
	@Test
	public void loadMainMap() {
		try {
		boolean checkSave = false;
		SaveAndLoadGame data = (SaveAndLoadGame) ResourceManager.load("SaveMainMap");
		String test = null;
		for ( String key : data.mainMapToSave.keySet() ) {
		    test =  key ;
		}
		
		if (test.equals("Check1")) {
			checkSave = true;
		} else {
		}
		assertEquals(true, checkSave);
		}
		catch (Exception exLoad) {

		}
	}

	
	@Test
	public void loadStrategies() {
		try {
		boolean checkSave = false;
		SaveAndLoadGame data = (SaveAndLoadGame) ResourceManager.load("SaveStrategies");
		String checkString = null;
		for ( String key : data.mainMapToSave.keySet() ) {
			checkString =  key ;
		}
		
		if (checkString.equals("test")) {
			checkSave = true;
		} else {
		}
		assertEquals(true, checkSave);
		}
		catch (Exception exLoad) {

		}
	}
	
	
	@Test
	public void loadNumberOfPlayersToCheck() {
		try {
		boolean checkSave = false;
		SaveAndLoadGame data = (SaveAndLoadGame) ResourceManager.load("numberofplayers");
		int  count = data.currentPlyrIndexToSave;
		
		
		if (count == 2) {
			checkSave = true;
		} else {
		}
		assertEquals(true, checkSave);
		}
		catch (Exception exLoad) {

		}
	}
	
	@Test
	public void loadPlyrStrategy() {
		try {
		boolean checkSave = false;
		SaveAndLoadGame data = (SaveAndLoadGame) ResourceManager.load("PlyrStrategy");
		String  strategy = data.currentPlyrStrategyToSave;
		
		
		if (strategy.equals("cheater")) {
			checkSave = true;
		} else {
		}
		assertEquals(true, checkSave);
		}
		catch (Exception exLoad) {

		}
	}
	
	
	@Test
	public void loadState() {
		try {
		boolean checkSave = false;
		SaveAndLoadGame data = (SaveAndLoadGame) ResourceManager.load("State");
		String checkState = data.state;
		
		
		if (checkState.equals("Reinforcement")) {
			checkSave = true;
		} else {
		}
		assertEquals(true, checkSave);
		}
		catch (Exception exLoad) {

		}
	}
	
	
	
	@Test
	public void loadtotalTerr() {
		try {
		boolean checkSave = false;
		SaveAndLoadGame data = (SaveAndLoadGame) ResourceManager.load("totalTerr");
		int total = data.totalTerrToSave;
		
		
		if (total == 5) {
			checkSave = true;
		} else {
		}
		assertEquals(true, checkSave);
		}
		catch (Exception exLoad) {

		}
	}
	
	
	
	
	@Test
	public void loadCurrentPlayer() {
		try {
		boolean checkSave = false;
		SaveAndLoadGame data = (SaveAndLoadGame) ResourceManager.load("currentPlayer");
		int current = data.currentPlyrIndexToSave;
		
		
		if (current == 4) {
			checkSave = true;
		} else {
		}
		assertEquals(true, checkSave);
		}
		catch (Exception exLoad) {

		}
	}
	
	
	@Test
	public void LoadReinforcement() {
		try {
		boolean checkSave = false;
		SaveAndLoadGame data = (SaveAndLoadGame) ResourceManager.load("reinforcement");
		
		int count = data.reinforcementToSave.get("Testreinfo");
		
		if (count == 2) {
			checkSave = true;
		} else {
		}
		assertEquals(true, checkSave);
		}
		catch (Exception exLoad) {

		}
	}
	
	
	@Test
	public void LoadControlValueMap() {
		try {
		boolean checkSave = false;
		SaveAndLoadGame data = (SaveAndLoadGame) ResourceManager.load("SaveControlMap");
		
		int count = data.continentControlValueHashMapToSave.get("TestControl");
		
		if (count == 1) {
			checkSave = true;
		} else {
		}
		assertEquals(true, checkSave);
		}
		catch (Exception exLoad) {

		}
	}
	
	
	
	

	@Test
	public void checkForFile() {
		File newFile = new File("C:\\Users\\Khashyap\\Documents\\GitLocalRepo\\RiskComputerGame\\SaveToLoadAgain");
		boolean checkFile = false;
		// GameModeUI.class
		if (newFile.exists()) {
			// do something
			checkFile = true;
		}
		// StartUpPhaseModel.setInitialArmies(num);
		// assert statements
		assertEquals(true, checkFile);

	}

}
