/**
 * 
 */
/**
 * @author drvg5
 *
 */
package com.risk.test;

import static org.junit.Assert.assertEquals;

import java.io.File;
import org.junit.Test;

import com.risk.model.ParseMapFileModel;


public class TestParseMapFile {

	
	
	@Test
	public void checkMapValidation() {
		ParseMapFileModel testParseMapFileModel = new ParseMapFileModel();
 		ParseMapFileModel.checkForCallingUI = false;

		File newFile1 = new File("C:\\Users\\Khashyap\\Documents\\Maps\\Cornwall.map");
		testParseMapFileModel.getMapFile(0, newFile1);
		boolean checkMap = ParseMapFileModel.junitMapValidation;
		// assert statements
				assertEquals(true, checkMap);
	}
	
	@Test
	public void checkMapValidationFormat() {
		ParseMapFileModel testParseMapFileModel = new ParseMapFileModel();
 		ParseMapFileModel.checkForCallingUI = false;

		File newFile2 = new File("C:\\Users\\Khashyap\\Documents\\GitLocalRepo\\README.md");
		testParseMapFileModel.getMapFile(0, newFile2);
		boolean checkFormat = ParseMapFileModel.junitMapValidation;
		// assert statements
				assertEquals(false, checkFormat);
	}
	
	}
	