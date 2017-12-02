package com.risk.test;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;

@RunWith(Suite.class)
@Suite.SuiteClasses({

	ResourceManagerTest.class,GameModeUITest.class,PlayerClassTest.class,FortificationPhaseModelTest.class, ReinforcementPhaseModelTest.class,StartupPhaseModelTest.class,
		EditMapUITest.class, EditMapModelValidationTest.class, EditMapModelConnectivityTest.class,
		ConfigureMapModelTest.class, ParseMapFileModelValidationTest.class, ParseMapFileModelConnectivityTest.class})

public class RiskSuiteTest {

}
