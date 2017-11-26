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

public class AggressiveBehaviorImpl extends Observable implements PlayerBehavior {

	@Override
	public void reinforce(String player) {

		ReinforcementPhaseModel obj = new ReinforcementPhaseModel();
		obj.setMsgUI("total reinforcement print," + player);

		setChanged();
		notifyObservers(this);

		int reinforcementArmies = ReinforcementPhaseModel.reinforcement.get(player);

		int highestArmies = 0;

		// create a list of keys from playerInfo HashMap
		// for only the player concerned with the most armies
		List<String> strongestTerritoryList = new ArrayList<String>();

		TreeSet<Integer> unitsSet = new TreeSet<Integer>();

		// populate unitsSet
		for (String playerInfoKey : StartUpPhaseModel.playerInfo.keySet()) {

			String[] playerVals = playerInfoKey.split("-");

			if (playerVals[0].equals(player) || playerVals[0] == player) {

				unitsSet.add(StartUpPhaseModel.playerInfo.get(playerInfoKey));

			} // end if(playerVals[0].equals(player) || playerVals[0] == player)

		} // end for(String playerInfoKey : StartUpPhaseModel.playerInfo.keySet())

		highestArmies = unitsSet.last();

		// populate strongestTerritoryList
		for (String playerInfoKey : StartUpPhaseModel.playerInfo.keySet()) {

			String[] playerVals = playerInfoKey.split("-");

			if (playerVals[0].equals(player) || playerVals[0] == player) {

				if (StartUpPhaseModel.playerInfo.get(playerInfoKey) == highestArmies) {

					strongestTerritoryList.add(playerInfoKey);

				} // end if(StartUpPhaseModel.playerInfo.get(playerInfoKey) > highestArmies)

			} // end if(playerVals[0].equals(player) || playerVals[0] == player)

		} // end for(String playerInfoKey : StartUpPhaseModel.playerInfo.keySet()

		Random randomKey = new Random();

		// loop until all reinforcement armies have been assigned
		while (reinforcementArmies != 0) {

			// choose strongest territory to put armies into
			String randomStrongestKey = strongestTerritoryList.get(randomKey.nextInt(strongestTerritoryList.size()));

			int playerInfoValue = StartUpPhaseModel.playerInfo.get(randomStrongestKey);

			// playerInfoValue = playerInfoValue + 1;

			playerInfoValue = playerInfoValue + reinforcementArmies;

			StartUpPhaseModel.playerInfo.put(randomStrongestKey, playerInfoValue);

			String[] playerKeySplit = randomStrongestKey.split("-");
			// reinforcementArmies--;

			reinforcementArmies = 0;

			obj.setTotalReinforcementArmies(reinforcementArmies);
			obj.setLatestArmies(playerInfoValue);

			setChanged();
			obj.setMsgUI("placementView," + playerKeySplit[0] + "," + playerKeySplit[1]);
			notifyObservers(obj);

		}

	}

	@Override
	public void attack(String player, HashMap<String, List<String>> territoryMap) {

		// find the strongest country of the chosen player
		String playerInfoKey = AttackPhaseModel.findCountryWithMaxArmies(player);
 		AttackPhaseModel.attackAggressively(playerInfoKey, territoryMap);

	}

	@Override
	public void fortify(int player) {

	}

}
