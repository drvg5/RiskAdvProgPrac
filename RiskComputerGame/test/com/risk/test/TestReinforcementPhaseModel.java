package com.risk.test;

import static org.junit.Assert.assertEquals;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.TreeMap;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import com.risk.model.ReinforcementPhaseModel;
import com.risk.model.StartUpPhaseModel;

public class TestReinforcementPhaseModel {

	@Test
	public void ReinforcementarmiesCountByTerritory() {

		// ReinforcementPhaseModel objReinforcementPhaseModel = new
		// ReinforcementPhaseModel();
		String returnvalue = null;
		String ToCheck = "10,3";
		boolean validate = false;
		HashMap<String, Integer> hashMapToCheck = new HashMap<String, Integer>();
		hashMapToCheck.put("2-A-Europe", 1);
		hashMapToCheck.put("2-B-Europe", 1);
		hashMapToCheck.put("2-C-Europe", 1);
		hashMapToCheck.put("2-D-Europe", 1);
		hashMapToCheck.put("2-E-Europe", 1);
		hashMapToCheck.put("2-F-Europe", 1);
		hashMapToCheck.put("2-G-Europe", 1);
		hashMapToCheck.put("2-H-Europe", 1);
		hashMapToCheck.put("2-I-Europe", 1);
		hashMapToCheck.put("2-J-Europe", 1);

		StartUpPhaseModel.playerInfo = hashMapToCheck;
		returnvalue = ReinforcementPhaseModel.calcReinforcementsByTerr("2");
		if (returnvalue.equals(ToCheck)) {
			validate = true;
		}

		// assert statements
		assertEquals(true, validate);
		StartUpPhaseModel.playerInfo.clear();
	}

	@Test
	public void ReinforcementarmiesCountByControlValue() {
		HashMap<String, Integer> continentControlValueHashMap = new HashMap<String, Integer>();
		boolean checkReinforcement = false;
		TreeMap<String, Integer> terrPerCont = new TreeMap<String, Integer>();
		HashMap<String, Integer> hashMapToCheck = new HashMap<String, Integer>();
		HashMap<String, Integer> reinforcement = new HashMap<String, Integer>();
		continentControlValueHashMap.put("Europe", 3);
		hashMapToCheck.put("2-A-Europe", 1);
		hashMapToCheck.put("2-B-Europe", 1);
		terrPerCont.put("Europe", 2);
		reinforcement.put("2", 5);
		StartUpPhaseModel.playerInfo = hashMapToCheck;
		StartUpPhaseModel.terrPerCont = terrPerCont;
		ReinforcementPhaseModel.reinforcement = reinforcement;
		ReinforcementPhaseModel.calcReinforcementByCntrlVal("2", continentControlValueHashMap);
		if (ReinforcementPhaseModel.reinforcement.get("2") == 8) {
			checkReinforcement = true;
		}

		// assert statements
		assertEquals(true, checkReinforcement);
		StartUpPhaseModel.playerInfo.clear();
		StartUpPhaseModel.terrPerCont.clear();
		ReinforcementPhaseModel.reinforcement.clear();

	}

}
