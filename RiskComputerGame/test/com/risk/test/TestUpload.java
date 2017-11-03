package com.risk.test;

import com.risk.ui.MainMenuUI;
import com.risk.ui.UploadMapUI;

import junit.framework.Test;
import junit.framework.TestCase;
import junit.framework.TestSuite;

public class TestUpload extends TestCase {
	public static void main(String args[]) {
		junit.textui.TestRunner.run(suite());
	}

	public static Test suite() {
		return new TestSuite(TestUpload.class);
	}

	protected void setUp() {
		MainMenuUI.startGame();
		UploadMapUI.LoadMap(MainMenuUI.desktop);
	}

	public void testUploadedFile() {

	}
}
