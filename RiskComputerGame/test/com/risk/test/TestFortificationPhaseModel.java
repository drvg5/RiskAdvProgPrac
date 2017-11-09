package com.risk.test;

import static org.junit.Assert.assertEquals;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.TreeSet;

import org.junit.Test;

import com.risk.model.FortificationPhaseModel;
import com.risk.model.StartUpPhaseModel;

public class TestFortificationPhaseModel {
	
	@Test
	public void FortificationPhaseCheck() {
		//FortificationPhaseModel objFortificationPhaseModel = new FortificationPhaseModel();
		HashMap<String, Integer> hashMapToCheck = new HashMap<String, Integer>();
		boolean checkFortify = false;
		HashMap<String,List<String>> territoryMap = new HashMap<String,List<String>>();
		HashMap<String, String> terrCont = new HashMap<String, String>();
		List<String> list1 = new ArrayList<String>();
		List<String> list2 = new ArrayList<String>();
		TreeSet<String> fortifySetToCheck = new TreeSet<String>();
		fortifySetToCheck.add("India-Pak");
		fortifySetToCheck.add("Pak-India");
		list1.add("Pak");
		list2.add("India");
		territoryMap.put("Asia,India", list1);
		territoryMap.put("Asia,Pak", list2);
		terrCont.put("India", "Asia");
		terrCont.put("Pak", "Asia");
		hashMapToCheck.put("2-India-Asia", 1);
		hashMapToCheck.put("2-Pak-Asia", 1);
		StartUpPhaseModel.playerInfo = hashMapToCheck;
		StartUpPhaseModel.terrCont = terrCont;
		FortificationPhaseModel.createFortifySet("2", territoryMap);
		//System.out.println(FortificationPhaseModel.fortifySet);
		if(fortifySetToCheck.equals(FortificationPhaseModel.fortifySet))
		{
			checkFortify =true;
		}
		
		//asserts
		assertEquals(true, checkFortify);
	}

}
