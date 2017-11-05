package com.risk.test;

import junit.framework.Test;
import junit.framework.TestSuite;

public class RiskTestAll {

	public static Test suite() {
		TestSuite testSuite = new TestSuite("Testing the Risk Game");

		testSuite.addTestSuite(UploadTest.class);
		return testSuite;

	}

}
