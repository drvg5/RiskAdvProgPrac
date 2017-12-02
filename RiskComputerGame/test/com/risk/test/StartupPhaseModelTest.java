package com.risk.test;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

import com.risk.model.StartUpPhaseModel;

public class StartupPhaseModelTest {

	@Test
	public void validArmyCount() {
		boolean checkValidCount = false;
		int num = 2;
		int value;
		StartUpPhaseModel.setInitialArmies(num);
		value = StartUpPhaseModel.initialArmies;
		if (value == 40) {
			checkValidCount = true;
		}

		// assert statements
		assertEquals(true, checkValidCount);

	}

	@Test
	public void InvalidArmyCount() {
		boolean checkInValidCount = false;
		int num = 2;
		int value;
		StartUpPhaseModel.setInitialArmies(num);
		value = StartUpPhaseModel.initialArmies;
		if (!(value == 40)) {
			checkInValidCount = true;
		}

		// assert statements
		assertEquals(false, checkInValidCount);
	}

	@Test
	public void PlayerTerritoryValidCount() {
		StartUpPhaseModel.junitCheckCount = true;
		boolean checkPlayerTerritoryValidCount;
		int numOfPlayers = 5;
		int numOfTerritories = 10;
		StartUpPhaseModel.terrPerPlayerPopulate(numOfPlayers, numOfTerritories);
		checkPlayerTerritoryValidCount = StartUpPhaseModel.junitCheckCount;

		// assert statements
		assertEquals(true, checkPlayerTerritoryValidCount);
	}
	
	@Test
	public void PlayerTerritoryInvalidCount() {
		StartUpPhaseModel.junitCheckCount=true;
		boolean checkPlayerTerritoryInvalidCount;
		int numOfPlayers = 10;
		int numOfTerritories = 5;
		StartUpPhaseModel.terrPerPlayerPopulate(numOfPlayers, numOfTerritories);
		checkPlayerTerritoryInvalidCount = StartUpPhaseModel.junitCheckCount;

		// assert statements
		assertEquals(false, checkPlayerTerritoryInvalidCount);
	}
}
