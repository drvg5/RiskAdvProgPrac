package com.risk.test;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;

@RunWith(Suite.class)
@Suite.SuiteClasses({

		TestEditMap.class, 
		TestConfigureMapModel.class,
		TestParseMapFile.class})

public class TestRiskSuite {

}
