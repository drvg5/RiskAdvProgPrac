package com.risk.behavior;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Observable;
import java.util.Random;
import java.util.TreeSet;

import com.risk.model.ReinforcementPhaseModel;
import com.risk.model.StartUpPhaseModel;

public class BenevolantBehaviorImpl extends Observable implements PlayerBehavior {

	@Override
	public void reinforce(String player) {

		ReinforcementPhaseModel obj = new ReinforcementPhaseModel();
		obj.setMsgUI("total reinforcement print," + player);

		setChanged();

		notifyObservers(this);

		int reinforcementArmies = ReinforcementPhaseModel.reinforcement.get(player);

		int leastArmies = 0;

		// create a list of keys from playerInfo HashMap
		// for only the player concerned with the least armies
		List<String> weakestTerritoryList = new ArrayList<String>();

		TreeSet<Integer> unitsSet = new TreeSet<Integer>();

		// populate unitsSet
		for (String playerInfoKey : StartUpPhaseModel.playerInfo.keySet()) {

			String[] playerVals = playerInfoKey.split("-");

			if (playerVals[0].equals(player) || playerVals[0] == player) {

				unitsSet.add(StartUpPhaseModel.playerInfo.get(playerInfoKey));

			} // end if(playerVals[0].equals(player) || playerVals[0] == player)

		} // end for(String playerInfoKey : StartUpPhaseModel.playerInfo.keySet())

		leastArmies = unitsSet.first();

		// populate weakestTerritoryList
		for (String playerInfoKey : StartUpPhaseModel.playerInfo.keySet()) {

			String[] playerVals = playerInfoKey.split("-");

			if (playerVals[0].equals(player) || playerVals[0] == player) {

				if (StartUpPhaseModel.playerInfo.get(playerInfoKey) == leastArmies) {

					weakestTerritoryList.add(playerInfoKey);

				} // end if(StartUpPhaseModel.playerInfo.get(playerInfoKey) > highestArmies)

			} // end if(playerVals[0].equals(player) || playerVals[0] == player)

		} // end for(String playerInfoKey : StartUpPhaseModel.playerInfo.keySet()

		Random randomKey = new Random();

		// loop until all reinforcement armies have been assigned
		while (reinforcementArmies != 0) {

			// choose strongest territory to put armies into
			String randomWeakestKey = weakestTerritoryList.get(randomKey.nextInt(weakestTerritoryList.size()));

			int playerInfoValue = StartUpPhaseModel.playerInfo.get(randomWeakestKey);

			// playerInfoValue = playerInfoValue + 1;

			playerInfoValue = playerInfoValue + reinforcementArmies;

			StartUpPhaseModel.playerInfo.put(randomWeakestKey, playerInfoValue);

			String[] playerKeySplit = randomWeakestKey.split("-");
			// reinforcementArmies--;

			reinforcementArmies = 0;

			obj.setTotalReinforcementArmies(reinforcementArmies);
			obj.setLatestArmies(playerInfoValue);

			setChanged();
			obj.setMsgUI("placementView," + playerKeySplit[0] + "," + playerKeySplit[1]);
			notifyObservers(this);

		}

	}

	@Override
	public void fortify(int player) {
		// TODO Auto-generated method stub

	}

	@Override
	public void attack(String player, HashMap<String, List<String>> territoryMap) {
		System.out.println("--------------------------");
		System.out.println("ATTACK PHASE UNINITIATED DUE TO ADOPTION OF BENEVOLENT STRATEGY ");
		System.out.println("--------------------------");

	}

}
