package com.risk.behavior;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Observable;
import java.util.Random;
import java.util.TreeSet;

import com.risk.model.AttackPhaseModel;
import com.risk.model.ReinforcementPhaseModel;
import com.risk.model.StartUpPhaseModel;
import com.risk.ui.ReinforcementsUI;

public class CheaterBehaviorImpl implements PlayerBehavior {

	@Override
	public void reinforce(String player){
		
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
		
		//add this
		//AttackPhaseModel.conquerAllAdjacentCountries(player,territoryMap);
	}

}
