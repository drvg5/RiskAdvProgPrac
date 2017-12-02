package com.risk.test;

import static org.junit.Assert.assertEquals;

import java.util.Arrays;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import com.risk.model.ConfigureMapModel;

public class ConfigureMapModelTest {
	@Before
	public void BeforeMethod() {

	}

	@Test
	public void validInput() {
		ConfigureMapModel objConfigureMapModel = new ConfigureMapModel();
		boolean checkValidInput = false;
		String[] data = { "America", "Canada", "2" };
		String[] dataReceived = {};
		dataReceived = objConfigureMapModel.addContinentsAndCountries(data);
		if (Arrays.equals(data, dataReceived)) {
			checkValidInput = true;
		}

		// assert statements
		assertEquals(true, checkValidInput);
	}

	@Test
	public void invalidInput() {
		ConfigureMapModel objConfigureMapModel = new ConfigureMapModel();
		boolean checkInvalidInput = false;
		String[] data = { "America", "Canada" };
		String[] dataReceived = {};
		try {
			dataReceived = objConfigureMapModel.addContinentsAndCountries(data);
		} catch (Exception ex) {
		}
		if (Arrays.equals(data, dataReceived)) {
			checkInvalidInput = true;
		}

		// assert statements
		assertEquals(false, checkInvalidInput);
	}
	
	
	@Test
	public void checkUpdateOnClick() {
		ConfigureMapModel objConfigureMapModel = new ConfigureMapModel();
		String[] dataClick = { "America,Canada", "america","canada","America", "Canada", "America" };
		objConfigureMapModel.updateOnClick(dataClick);
		// assert statements
		assertEquals(true, ConfigureMapModel.junitUpdateOnClick);
	}
	
	
	
	@After
	public void AfterMethod() {

	}

}
