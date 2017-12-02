package com.risk.test;

import static org.junit.Assert.assertEquals;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import org.junit.Test;

import com.risk.model.TournamentModeModel;

public class TournamentModeModelTest {

	@Test
	public void checkInvalidMap() {
		TournamentModeModel objTournamentModeModel = new TournamentModeModel();
		List<String> listToCheck = new ArrayList<String>();
		listToCheck.add("test");

		TournamentModeModel.checkForCallingTournamentUI = false;
		TournamentModeModel.junitMapValidationT = false;
		File newFile2 = new File("C:\\Users\\Khashyap\\Documents\\GitLocalRepo\\README.md");
		objTournamentModeModel.loadHashMap(0, newFile2, 1, 1, listToCheck);
		boolean checkFormat = TournamentModeModel.junitMapValidationT;
		// assert statements
		assertEquals(false, checkFormat);
	}
	
	@Test
	public void checkvalidMap()
	{
		TournamentModeModel objTournamentModeModel = new TournamentModeModel();
		List<String> listToCheck = new ArrayList<String>();
		listToCheck.add("test");	
		File checkFile = new File("C:\\Users\\Khashyap\\Documents\\Maps\\Cornwall.map");
		objTournamentModeModel.loadHashMap(0, checkFile, 1, 1, listToCheck);
		boolean checkFormat = TournamentModeModel.junitMapValidationT;
		assertEquals(true, checkFormat);
	}

}
