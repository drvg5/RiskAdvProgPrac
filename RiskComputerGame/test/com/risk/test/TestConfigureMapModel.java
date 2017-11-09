package com.risk.test;

import static org.junit.Assert.assertEquals;

import java.util.Arrays;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import com.risk.model.ConfigureMapModel;

public class TestConfigureMapModel {
	@Before
	public void BeforeMethod() {

	}

	@Test
	public void validInput() {
		ConfigureMapModel objConfigureMapModel = new ConfigureMapModel();
		boolean check =false;
		String[] data = { "America", "Canada" ,"2"};
		String[] dataReceived = {};
		dataReceived = objConfigureMapModel.addContinentsAndCountries(data);
		if(Arrays.equals(data, dataReceived))
		{
			check = true;
		}
		
		// assert statements
		assertEquals(true, check);
	}

	@After
	public void AfterMethod() {

	}

}
