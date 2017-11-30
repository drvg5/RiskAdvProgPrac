package com.risk.behavior;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import com.risk.model.AttackPhaseModel;
import com.risk.model.ReinforcementPhaseModel;
import com.risk.model.StartUpPhaseModel;
import com.risk.ui.ReinforcementsUI;

public class CheaterBehaviorImpl implements PlayerBehavior {

	@Override
	public void reinforce(String player) {

		ReinforcementPhaseModel obj = new ReinforcementPhaseModel();
		ReinforcementsUI objUI = new ReinforcementsUI();
		obj.addObserver(objUI);
		obj.reinforceCheater(player);

	}

	@Override
	public void fortify(String player) {
		// TODO Auto-generated method stub

	}

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
