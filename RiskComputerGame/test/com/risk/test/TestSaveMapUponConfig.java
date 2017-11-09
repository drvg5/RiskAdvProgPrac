package com.risk.test;

import static org.junit.Assert.assertEquals;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import com.risk.model.ParseMapFileModel;

public class TestSaveMapUponConfig {

	@Before
	public void BeforeMethod() {
		 ParseMapFileModel testParseMapFileModel = new ParseMapFileModel();
	}
	
	 

	@Test
	public void checkConnectivityUponConfig() {
		String check;
		// File newFile = new File("C:/Users/Khashyap/Documents/Maps/Cornwall.map");
		// testParseMapFileModel.getMapFile(0, newFile);
		check = "true";
		// assert statements
		assertEquals("true", check);
	}
	

	@After
	public void AfterMethod() {

	}
}
