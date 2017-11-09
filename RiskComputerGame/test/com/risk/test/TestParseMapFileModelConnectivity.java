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

public class TestParseMapFileModelConnectivity {

	@Test
	public void checkMapValidationConnectivity() {
		ParseMapFileModel testParseMapFileModel = new ParseMapFileModel();
		ParseMapFileModel.checkForCallingUI = false;

		File newFile1 = new File("C:\\Users\\Khashyap\\Documents\\Maps\\Cornwall.map");
		testParseMapFileModel.getMapFile(0, newFile1);
		boolean checkMap = ParseMapFileModel.junitMapValidation;
		// assert statements
		assertEquals(true, checkMap);
	}

}
