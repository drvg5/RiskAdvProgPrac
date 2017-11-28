package com.risk.behavior;

import java.util.HashMap;
import java.util.List;

import com.risk.model.ReinforcementPhaseModel;
import com.risk.ui.ReinforcementsUI;

public class HumanBehaviorImpl implements PlayerBehavior {

	@Override
	public void reinforce(String player) {


		ReinforcementPhaseModel obj = new ReinforcementPhaseModel();
		ReinforcementsUI objUI = new ReinforcementsUI();
		obj.addObserver(objUI);
		obj.reinforceHuman(player);

	}

	@Override
	public void fortify(int player) {
		// TODO Auto-generated method stub

	}

	@Override
	public void attack(String player, HashMap<String, List<String>> territoryMap) {
		// TODO Auto-generated method stub

	}

}
