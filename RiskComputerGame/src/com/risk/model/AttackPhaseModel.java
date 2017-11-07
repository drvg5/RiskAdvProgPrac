package com.risk.model;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;

public class AttackPhaseModel {

	public static boolean chooseCountryTobeAttacked(int plyr, HashMap<String, List<String>> territoryMap) {
		boolean attackPossible = false;
		/*
		 * get data from player info entries for the specific player
		 */
		// list for attackers
		List<String> playerAccToPlayerNo = new ArrayList<String>();
		// list for attacked candidates
		List<String> playerNotAccToPlayerNo = new ArrayList<String>();

		// populating the lists declared above
		for (String playerInfoKey : StartUpPhaseModel.playerInfo.keySet()) {
			String[] playerInfoArr = playerInfoKey.split("-");
			if (playerInfoArr[0].equals(String.valueOf(plyr))) {
				playerAccToPlayerNo.add(playerInfoKey);

			} else {
				playerNotAccToPlayerNo.add(playerInfoKey);

			}
		}
		// for generating a stream of pseudorandom numbers
		Random random = new Random();
		String attacker = playerAccToPlayerNo.get(random.nextInt(playerAccToPlayerNo.size()));
		String attacked = playerNotAccToPlayerNo.get(random.nextInt(playerNotAccToPlayerNo.size()));
		String[] keySplit1 = attacker.split("-");
		String territoryAttacker = keySplit1[1];
		String[] keySplit2 = attacked.split("-");
		String territoryAttacked = keySplit2[1];
		for (Map.Entry<String, List<String>> iterate : territoryMap.entrySet()) {
			//
			String[] keyArray = iterate.getKey().split(",");
			String territory = keyArray[1];
			if (territoryAttacker.equals(territory)) {

				for (String country : iterate.getValue()) {
					if (territoryAttacked.equals(country)) {
						attackPossible = true;
					}

				}
			}

		}

		return attackPossible;
	}

	public static void rollDice(boolean attackPossible) {

	}

	public static boolean result() {
		boolean choice = false;
		return choice;
	}

}
