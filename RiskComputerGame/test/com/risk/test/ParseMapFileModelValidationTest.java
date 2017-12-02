package com.risk.test;

import static org.junit.Assert.assertEquals;

import java.io.File;

import org.junit.Test;

import com.risk.model.ParseMapFileModel;

public class ParseMapFileModelValidationTest {
	@Test
	public void checkMapValidationFormat() {
		ParseMapFileModel testParseMapFileModel = new ParseMapFileModel();
		ParseMapFileModel.checkForCallingUI = false;
		ParseMapFileModel.junitMapValidation = false;
		File newFile2 = new File("C:\\Users\\Khashyap\\Documents\\GitLocalRepo\\README.md");
		testParseMapFileModel.getMapFile(0, newFile2);
		boolean checkFormat = ParseMapFileModel.junitMapValidation;
		// assert statements
		assertEquals(false, checkFormat);
	}

	
}
