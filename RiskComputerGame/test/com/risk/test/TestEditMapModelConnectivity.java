package com.risk.test;

import static org.junit.Assert.assertEquals;

import java.io.File;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import com.risk.model.EditMapModel;

public class TestEditMapModelConnectivity {
	@Before
	public void BeforeMethod() {

	}

	@Test
	public void checkEditMapValidationConnectivity() {
		EditMapModel testEditMapModel = new EditMapModel();
		EditMapModel.checkForCallingEditUI = false;
		File newFileToEdit = new File("C:\\Users\\Khashyap\\Documents\\Maps\\Cornwall.map");
		testEditMapModel.getMapFile(0, newFileToEdit);
		boolean checkMapToEdit = EditMapModel.junitMapEditValidation;
		// assert statements
		assertEquals(true, checkMapToEdit);
	}

	@After
	public void AfterMethod() {

	}

}
