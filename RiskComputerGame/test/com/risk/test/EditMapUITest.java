package com.risk.test;

import static org.junit.Assert.assertEquals;

import java.util.ArrayList;
import java.util.List;

import org.junit.Test;

import com.risk.ui.EditMapUI;

public class EditMapUITest {

	@Test
	public void checkRepeatValue() {
		boolean checkRepeatValue = false;
		List<String> listToCheckRepeatValue = new ArrayList<String>();
		listToCheckRepeatValue.add("test");
		listToCheckRepeatValue.add("testnew");
		checkRepeatValue = EditMapUI.findDuplicates(listToCheckRepeatValue);

		// assert statements
		assertEquals(false, checkRepeatValue);
	}
	
	@Test
	public void checkNoRepeatValue() {
		boolean checkNoRepeatValue = false;
		List<String> listToCheckNoRepeatValue = new ArrayList<String>();
		listToCheckNoRepeatValue.add("test");
		listToCheckNoRepeatValue.add("test");
		checkNoRepeatValue = EditMapUI.findDuplicates(listToCheckNoRepeatValue);

		// assert statements
		assertEquals(true, checkNoRepeatValue);
	}
}