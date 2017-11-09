package com.risk.test;

import static org.junit.Assert.assertEquals;

import java.util.ArrayList;
import java.util.List;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import com.risk.ui.EditMapUI;

public class TestEditMap {
	@Before
	public void BeforeMethod() {

	}

	@Test
	public void testnNoDuplicatesInList() {
		List<String> testDuplicates = new ArrayList<String>();
		testDuplicates.add("test");
		testDuplicates.add("testnew");
		boolean check = EditMapUI.findDuplicates(testDuplicates);
		// assert statements
		assertEquals(false, check);
		testDuplicates.clear();
	}

	@After
	public void AfterMethod() {

	}

}
