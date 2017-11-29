package com.risk.behavior;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import com.risk.model.AttackPhaseModel;
import com.risk.model.FortificationPhaseModel;
import com.risk.model.ReinforcementPhaseModel;
import com.risk.model.StartUpPhaseModel;
import com.risk.ui.FortificationUI;
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


		FortificationPhaseModel obj = new FortificationPhaseModel();
		FortificationUI objUI = new FortificationUI();
		obj.addObserver(objUI);
		obj.cheaterFortification(player);

	}

	@Override
	public void attack(String player, HashMap<String, List<String>> territoryMap) {

		for (Entry<String, Integer> toPrint : StartUpPhaseModel.playerInfo.entrySet()) {
			System.out.println("before cheating");

			System.out.println(toPrint.getKey() + "\t" + "\t" + toPrint.getValue());
		}

		// method to find adjacent countries and conquer them all
		AttackPhaseModel.conquerAllAdjacentCountries(player, territoryMap);

		for (Entry<String, Integer> toPrint : StartUpPhaseModel.playerInfo.entrySet()) {
			System.out.println("after cheating");

			System.out.println(toPrint.getKey() + "\t" + "\t" + toPrint.getValue());
		}
	}

}
