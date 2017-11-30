package com.risk.behavior;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Observable;
import java.util.Random;
import java.util.TreeSet;

import com.risk.model.FortificationPhaseModel;
import com.risk.model.ReinforcementPhaseModel;
import com.risk.model.StartUpPhaseModel;
import com.risk.ui.FortificationUI;
import com.risk.ui.ReinforcementsUI;

/**
 * The Class BenevolantBehaviorImpl implements methods of interface {@link com.risk.behavior.PlayerBehavior PlayerBehavior}
 * as per the BENEVOLENT player behaviour
 * 
 */
public class BenevolantBehaviorImpl implements PlayerBehavior {

	/* (non-Javadoc)
	 * @see com.risk.behavior.PlayerBehavior#reinforce(java.lang.String)
	 */
	@Override

	public void reinforce(String player){

		ReinforcementPhaseModel obj = new ReinforcementPhaseModel();
		ReinforcementsUI objUI = new ReinforcementsUI();
		obj.addObserver(objUI);
		obj.reinforceBenevolent(player);

	}

	/* (non-Javadoc)
	 * @see com.risk.behavior.PlayerBehavior#fortify(java.lang.String)
	 */
	@Override
	public void fortify(String player) {


		FortificationPhaseModel obj = new FortificationPhaseModel();
		FortificationUI objUI = new FortificationUI();
		obj.addObserver(objUI);
		obj.benevolentFortification(player);
		

	}

	/* (non-Javadoc)
	 * @see com.risk.behavior.PlayerBehavior#attack(java.lang.String, java.util.HashMap)
	 */
	@Override
	public void attack(String player, HashMap<String, List<String>> territoryMap) {
		System.out.println("--------------------------");
		System.out.println("ATTACK PHASE UNINITIATED DUE TO ADOPTION OF BENEVOLENT STRATEGY ");
		System.out.println("--------------------------");

	}

}
