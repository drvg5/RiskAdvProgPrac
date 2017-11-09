package com.risk.test;

import static org.junit.Assert.assertEquals;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import org.junit.Test;

import com.risk.model.EditMapModel;
import com.risk.ui.EditMapUI;

public class TestEditMapModelValidation {
	@Test
	public void checkEditMapValidationFormat() {
		EditMapModel testEditMapModel = new EditMapModel();
		EditMapModel.checkForCallingEditUI = false;
		EditMapModel.junitMapEditValidation = false;
		File newFile2 = new File("C:\\Users\\Khashyap\\Documents\\GitLocalRepo\\README.md");
		testEditMapModel.getMapFile(0, newFile2);
		boolean checkFormat = EditMapModel.junitMapEditValidation;
		// assert statements
		assertEquals(false, checkFormat);
	}
	@Test
	public void checkDuplicates() {
		boolean checkDuplicate = false;
		List<String> listToCheckDuplicate = new ArrayList<String>();
		listToCheckDuplicate.add("first");
		listToCheckDuplicate.add("second");
		checkDuplicate = EditMapModel.findDuplicates(listToCheckDuplicate);

		// assert statements
		assertEquals(false, checkDuplicate);
	}
	
	@Test
	public void checkNoDuplicates() {
		boolean checkNoDuplicate = false;
		List<String> listToCheckNoDuplicate = new ArrayList<String>();
		listToCheckNoDuplicate.add("first");
		listToCheckNoDuplicate.add("first");
		checkNoDuplicate = EditMapUI.findDuplicates(listToCheckNoDuplicate);

		// assert statements
		assertEquals(true, checkNoDuplicate);
	}

}
