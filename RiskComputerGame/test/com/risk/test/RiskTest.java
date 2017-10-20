package com.risk.test;

import junit.framework.Test;
import junit.framework.TestCase;
import junit.framework.TestSuite;

/**
 * Test class containing a Test Suite comprising of test cases 
 * for unit testing the application
 * 
 * 
 * 
 * @author drvg5
 * @see TestCase
 *
 */
public class RiskTest extends TestCase {

	public static void main(String args[]) {
		junit.textui.TestRunner.run(suite());
	}

	public static Test suite() {
		return new TestSuite(RiskTest.class);
	}

}