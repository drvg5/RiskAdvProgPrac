package com.risk.behavior;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import com.risk.model.AttackPhaseModel;
import com.risk.model.ReinforcementPhaseModel;
import com.risk.model.StartUpPhaseModel;
import com.risk.ui.ReinforcementsUI;

/**
 * The Class CheaterBehaviorImpl implements methods of interface {@link com.risk.behavior.PlayerBehavior PlayerBehavior}
 * as per the CHEATER player behaviour.
 */
public class CheaterBehaviorImpl implements PlayerBehavior {

	/* (non-Javadoc)
	 * @see com.risk.behavior.PlayerBehavior#reinforce(java.lang.String)
	 */
	@Override
	public void reinforce(String player) {

		ReinforcementPhaseModel obj = new ReinforcementPhaseModel();
		ReinforcementsUI objUI = new ReinforcementsUI();
		obj.addObserver(objUI);
		obj.reinforceCheater(player);

	}

	/* (non-Javadoc)
	 * @see com.risk.behavior.PlayerBehavior#fortify(java.lang.String)
	 */
	@Override
	public void fortify(String player) {
		// TODO Auto-generated method stub

	}

	/* (non-Javadoc)
	 * @see com.risk.behavior.PlayerBehavior#attack(java.lang.String, java.util.HashMap)
	 */
	@Override
	public void attack(String player, HashMap<String, List<String>> territoryMap) {

		System.out.println("---------------------------------------------------------------------------");
		System.out.println("****************** ATTACK PHASE FOR PLAYER " + player + " BEGINS ***********************");
		System.out.println("---------------------------------------------------------------------------");
		System.out.println();
		System.out.println();
		System.out.println("---------------------------------------------------------------------------");

		System.out.println("CHEATER STRATEGY TO BE IMPLEMENTED");
		System.out.println("---------------------------------------------------------------------------");

		System.out.println();
		System.out.println("---------------------------------------------------------------------------");

		System.out.println("PLAYER " + player + " STATS BEFORE CHEATING");
		System.out.println("---------------------------------------------------------------------------");

		System.out.println();
		System.out.println();

		for (Entry<String, Integer> toPrint : StartUpPhaseModel.playerInfo.entrySet()) {
			System.out.println("---------------------------------------------------------------------------");
			System.out.println(toPrint.getKey() + "\t" + "\t" + toPrint.getValue());
			System.out.println("---------------------------------------------------------------------------");

		}

		// method to find adjacent countries and conquer them all
		AttackPhaseModel.conquerAllAdjacentCountries(player, territoryMap);

		System.out.println("---------------------------------------------------------------------------");

		System.out.println("CHEATER STRATEGY IMPLEMENTED");

		System.out.println("---------------------------------------------------------------------------");
		System.out.println();

		System.out.println("---------------------------------------------------------------------------");
		System.out.println("PLAYER " + player + " STATS AFTER CHEATING");
		System.out.println("---------------------------------------------------------------------------");

		System.out.println();
		System.out.println();

		for (Entry<String, Integer> toPrint : StartUpPhaseModel.playerInfo.entrySet()) {
			System.out.println("---------------------------------------------------------------------------");
			System.out.println(toPrint.getKey() + "\t" + "\t" + toPrint.getValue());
			System.out.println("---------------------------------------------------------------------------");

		}
	}

}
