package com.risk.behavior;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Observable;
import java.util.Random;

import com.risk.model.ReinforcementPhaseModel;
import com.risk.model.StartUpPhaseModel;

public class RandomBehaviorImpl extends Observable implements PlayerBehavior {

	@Override
	public void reinforce(String player) {

		ReinforcementPhaseModel obj = new ReinforcementPhaseModel();
		obj.setMsgUI("total reinforcement print," + player);

		setChanged();
		notifyObservers(this);

		int reinforcementArmies = ReinforcementPhaseModel.reinforcement.get(player);

		// create a list of keys from playerInfo HashMap
		// for only the player concerned
		List<String> playerInfoKeyList = new ArrayList<String>();

		for (String playerInfoKey : StartUpPhaseModel.playerInfo.keySet()) {

			String[] playerVals = playerInfoKey.split("-");
			if (playerVals[0].equals(player) || playerVals[0] == player) {

				playerInfoKeyList.add(playerInfoKey);
			}

		}

		Random randomKey = new Random();

		// loop until all reinforcement armies have been assigned
		while (reinforcementArmies != 0) {

			// choose territory randomly to put armies into
			String randomPlayerKey = playerInfoKeyList.get(randomKey.nextInt(playerInfoKeyList.size()));

			int playerInfoValue = StartUpPhaseModel.playerInfo.get(randomPlayerKey);
			playerInfoValue = playerInfoValue + 1;

			StartUpPhaseModel.playerInfo.put(randomPlayerKey, playerInfoValue);

			String[] playerKeySplit = randomPlayerKey.split("-");
			reinforcementArmies--;

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
		// TODO Auto-generated method stub

	}

}
