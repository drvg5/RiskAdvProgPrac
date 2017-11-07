package com.risk.model;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;

public class AttackPhaseModel {

	static int noOfAttackerArmies;
	static int noOfDefenderArmies;
	static Random random;

	public static boolean chooseCountryTobeAttacked(int plyr, HashMap<String, List<String>> territoryMap) {
		boolean attackPossible = false;
		noOfAttackerArmies = 0;
		noOfDefenderArmies = 0;
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
		random = new Random();
		String attacker = playerAccToPlayerNo.get(random.nextInt(playerAccToPlayerNo.size()));
		// getting number of attacker armies
		noOfAttackerArmies = StartUpPhaseModel.playerInfo.get(attacker);
		String attacked = playerNotAccToPlayerNo.get(random.nextInt(playerNotAccToPlayerNo.size()));
		// getting number of attacker armies
		noOfDefenderArmies = StartUpPhaseModel.playerInfo.get(attacked);
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
						System.out.println("Player" + keySplit1[0] + "in" + territoryAttacker + "attacks Player"
								+ keySplit2[0] + "in" + territoryAttacked);
					}

				}
			}

		}

		return attackPossible;
	}

	public static void rollDice(boolean attackPossible) {
		int diceRollsForAttacker, diceRollsForDefender;
		int[] diceArrayForAttackers = new int[3];
		int[] diceArrayForDefenders = new int[3];

		if (noOfAttackerArmies > 2) {
			diceRollsForDefender = 2;
			diceArrayForDefenders = AttackPhaseModel.getDiceNumbers(diceRollsForDefender);
			diceRollsForAttacker = 3;
			diceArrayForAttackers = AttackPhaseModel.getDiceNumbers(diceRollsForAttacker);

		} else if (noOfAttackerArmies == 2) {
			diceRollsForDefender = 2;
			diceArrayForDefenders = AttackPhaseModel.getDiceNumbers(diceRollsForDefender);
			diceRollsForAttacker = 2;
			diceArrayForAttackers = AttackPhaseModel.getDiceNumbers(diceRollsForAttacker);

		} else if (noOfAttackerArmies == 1) {
			diceRollsForDefender = 1;
			diceArrayForDefenders = AttackPhaseModel.getDiceNumbers(diceRollsForDefender);
			diceRollsForAttacker = 1;
			diceArrayForAttackers = AttackPhaseModel.getDiceNumbers(diceRollsForAttacker);
		} 

	}

	public static int[] getDiceNumbers(int diceRolls) {
		random = new Random();
		int[] diceArray = new int[3];

		switch (diceRolls) {
		case 1:
			diceArray[0] = random.nextInt(6);
			break;
		case 2:
			diceArray[0] = random.nextInt(6);
			diceArray[1] = random.nextInt(6);

			break;
		case 3:
			diceArray[0] = random.nextInt(6);
			diceArray[1] = random.nextInt(6);
			diceArray[2] = random.nextInt(6);
			break;
		default:
			break;
		}
		return diceArray;
	}

	public static boolean result() {
		boolean choice = false;
		return choice;
	}

}
