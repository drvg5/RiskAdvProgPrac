package com.risk.test;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

import com.risk.model.StartUpPhaseModel;

public class PlayerClassTest {
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


}
