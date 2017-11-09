package com.risk.test;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;

@RunWith(Suite.class)
@Suite.SuiteClasses({

		TestFortificationPhaseModel.class, TestReinforcementPhaseModel.class, TestStartupPhaseModel.class,
		TestEditMapUI.class, TestEditMapModelValidation.class, TestEditMapModelConnectivity.class,
		TestConfigureMapModel.class, TestParseMapFileModelValidation.class, TestParseMapFileModelConnectivity.class })

public class TestRiskSuite {

}
