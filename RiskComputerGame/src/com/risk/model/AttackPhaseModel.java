package com.risk.model;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;

public class AttackPhaseModel {

	static int noOfAttackerArmies;
	static int noOfDefenderArmies;
	static String attackerKey;
	static String defenderKey;
	static String attackerPlayer;
	static String defenderPlayer;

	static Random random;

	/*
	 * method for checking adjacency of the attacker and defender
	 */
	public static void chooseCountryToBeAttacked(int plyr, HashMap<String, List<String>> territoryMap) {
		boolean attackNotMade = true;
		noOfAttackerArmies = 0;
		noOfDefenderArmies = 0;
		// getting data from player info entries for the specific player
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

		// randomly select attacker and defender
		while (attackNotMade) {
			// for generating a stream of pseudorandom numbers
			random = new Random();
			String attacker = playerAccToPlayerNo.get(random.nextInt(playerAccToPlayerNo.size()));
			// global key initialized
			attackerKey = attacker;
			// getting number of attacker armies
			noOfAttackerArmies = StartUpPhaseModel.playerInfo.get(attacker);
			String attacked = playerNotAccToPlayerNo.get(random.nextInt(playerNotAccToPlayerNo.size()));
			// global key initialized
			defenderKey = attacked;
			// getting number of attacker armies
			noOfDefenderArmies = StartUpPhaseModel.playerInfo.get(attacked);
			String[] keySplit1 = attacker.split("-");
			attackerPlayer = keySplit1[0];
			String territoryAttacker = keySplit1[1];
			String[] keySplit2 = attacked.split("-");
			defenderPlayer = keySplit2[0];
			String territoryAttacked = keySplit2[1];
			for (Map.Entry<String, List<String>> iterate : territoryMap.entrySet()) {
				//
				String[] keyArray = iterate.getKey().split(",");
				String territory = keyArray[1];
				if (territoryAttacker.equals(territory)) {

					for (String country : iterate.getValue()) {
						if (territoryAttacked.equals(country)) {

							System.out.println("Player" + keySplit1[0] + "(Attacker) armies :" + noOfAttackerArmies);
							System.out.println("Player" + keySplit2[0] + "(Defender) armies :" + noOfDefenderArmies);
							System.out.println();
							System.out.println();
							System.out.println();
							System.out.println("Player" + keySplit1[0] + "in" + territoryAttacker + "attacks Player"
									+ keySplit2[0] + "in" + territoryAttacked);
							attackNotMade = false;
						}

					}
				}

			}
		}

	}

	/*
	 * dice is rolled and numbers obtained are used to compute the result
	 */
	public static void rollDice() throws InterruptedException {

		Thread.sleep(1000);
		int diceRollsForAttacker, diceRollsForDefender;
		int[] diceArrayForAttackers = new int[3];
		int[] diceArrayForDefenders = new int[3];

		if (noOfAttackerArmies > 2) {

			diceRollsForAttacker = 3;
			diceArrayForAttackers = AttackPhaseModel.getDiceNumbers(diceRollsForAttacker);
			System.out.println("Player" + attackerPlayer + "Dice Roll : " + diceArrayForDefenders);
			System.out.println();
			System.out.println();

			diceRollsForDefender = 2;
			diceArrayForDefenders = AttackPhaseModel.getDiceNumbers(diceRollsForDefender);
			System.out.println("Player" + defenderPlayer + "Dice Roll : " + diceArrayForDefenders);
			System.out.println();
			System.out.println();
		} else if (noOfAttackerArmies == 2) {

			diceRollsForAttacker = 2;
			diceArrayForAttackers = AttackPhaseModel.getDiceNumbers(diceRollsForAttacker);
			System.out.println("Player" + attackerPlayer + "Dice Roll : " + diceArrayForDefenders);
			System.out.println();
			System.out.println();
			diceRollsForDefender = 2;
			diceArrayForDefenders = AttackPhaseModel.getDiceNumbers(diceRollsForDefender);
			System.out.println("Player" + defenderPlayer + "Dice Roll : " + diceArrayForDefenders);
			System.out.println();
			System.out.println();
		} else if (noOfAttackerArmies == 1) {

			diceRollsForAttacker = 1;
			diceArrayForAttackers = AttackPhaseModel.getDiceNumbers(diceRollsForAttacker);
			System.out.println("Player" + attackerPlayer + "Dice Roll : " + diceArrayForDefenders);
			System.out.println();
			System.out.println();

			diceRollsForDefender = 1;
			diceArrayForDefenders = AttackPhaseModel.getDiceNumbers(diceRollsForDefender);
			System.out.println("Player" + defenderPlayer + "Dice Roll : " + diceArrayForDefenders);
			System.out.println();
			System.out.println();
		}
		// analyzing the numbers obtained to compute the no of armies
		diceRollAnalysis(diceArrayForAttackers, diceArrayForDefenders);

	}

	// analyzing the numbers obtained to compute the no of armies
	// thus making changes in player data
	@SuppressWarnings({ "unchecked", "rawtypes" })
	private static void diceRollAnalysis(int[] diceArrayForAttackers, int[] diceArrayForDefenders)
			throws InterruptedException {
		List diceArrayForAttackersList = Arrays.asList(diceArrayForAttackers);
		Collections.sort(diceArrayForAttackersList);
		List diceArrayForDefendersList = Arrays.asList(diceArrayForDefenders);
		Collections.sort(diceArrayForDefendersList);

		int maxAttacker = 0, attacker2ndBest = 0;
		int maxDefender = 0, minDefender2ndRoll = 0;
		int armiesObtainedForAttacker = 0, armiesObtainedForDefender = 0;

		// Case 1 : attacker and defender both are given more than 1 dice rolls
		if (diceArrayForAttackersList.size() >= 2) {
			maxAttacker = Collections.max(diceArrayForAttackersList);
			attacker2ndBest = (int) diceArrayForAttackersList.get(diceArrayForAttackersList.size() - 2);
			maxDefender = Collections.max(diceArrayForDefendersList);
			minDefender2ndRoll = (int) diceArrayForDefendersList.get(diceArrayForDefendersList.size() - 2);
			if (maxAttacker > maxDefender) {
				++armiesObtainedForAttacker;
				--armiesObtainedForDefender;

			} else {
				--armiesObtainedForAttacker;
				++armiesObtainedForDefender;

			}
			if (attacker2ndBest > minDefender2ndRoll) {
				++armiesObtainedForAttacker;
				--armiesObtainedForDefender;
			} else {
				--armiesObtainedForAttacker;
				++armiesObtainedForDefender;
			}

			// Updating the Player Data

			int noOfAttackerArmies = StartUpPhaseModel.playerInfo.get(attackerKey);
			noOfAttackerArmies = noOfAttackerArmies - armiesObtainedForAttacker;
			// Updating Attacker Data
			StartUpPhaseModel.playerInfo.replace(attackerKey, noOfAttackerArmies);

			int noOfDefenderArmies = StartUpPhaseModel.playerInfo.get(defenderKey);
			noOfDefenderArmies = noOfDefenderArmies - armiesObtainedForDefender;
			// Updating Defender Data
			StartUpPhaseModel.playerInfo.replace(defenderKey, noOfAttackerArmies);

			Thread.sleep(2000);
			System.out.println("Attack Completed");
			System.out.println("Player" + attackerPlayer + "(Attacker) armies :" + noOfAttackerArmies);
			System.out.println("Player" + defenderPlayer + "(Defender) armies :" + noOfDefenderArmies);

			if (noOfDefenderArmies == 0) {

				Thread.sleep(1000);
				System.out.println();
				System.out.println();

				System.out.println("Attacker emerges Victorious");

				Integer val = StartUpPhaseModel.playerInfo.get(defenderKey);
				// assigning defender territory to attacker
				String[] defKeyString = defenderKey.split("-");
				defKeyString[0] = attackerPlayer;
				String modifiedKey = defKeyString[0] + defKeyString[1] + defKeyString[2];
				StartUpPhaseModel.playerInfo.put(modifiedKey, val);
				StartUpPhaseModel.playerInfo.remove(defenderKey);
			}

		}
		// Case 2 : attacker and defender both are given 1 dice roll
		else if (diceArrayForAttackersList.size() == 1) {
			maxAttacker = 0;
			maxDefender = 0;
			if (maxAttacker > maxDefender) {
				++armiesObtainedForAttacker;
				--armiesObtainedForDefender;

			} else {
				--armiesObtainedForAttacker;
				++armiesObtainedForDefender;
			}

			// Updating the Player Data
			int noOfAttackerArmies = StartUpPhaseModel.playerInfo.get(attackerKey);
			noOfAttackerArmies = noOfAttackerArmies - armiesObtainedForAttacker;
			// Updating Attacker Data
			StartUpPhaseModel.playerInfo.replace(attackerKey, noOfAttackerArmies);

			int noOfDefenderArmies = StartUpPhaseModel.playerInfo.get(defenderKey);
			noOfDefenderArmies = noOfDefenderArmies - armiesObtainedForDefender;
			// Updating Defender Data
			StartUpPhaseModel.playerInfo.replace(defenderKey, noOfAttackerArmies);

			if (noOfDefenderArmies == 0) {
				
				Thread.sleep(1000);
				System.out.println();
				System.out.println("Attacker emerges Victorious");
				Integer val = StartUpPhaseModel.playerInfo.get(defenderKey);
				// assigning defender territory to attacker
				String[] defKeyString = defenderKey.split("-");
				defKeyString[0] = attackerPlayer;
				String modifiedKey = defKeyString[0] + "-" + defKeyString[1] + "-" + defKeyString[2];
				StartUpPhaseModel.playerInfo.put(modifiedKey, val);
				StartUpPhaseModel.playerInfo.remove(defenderKey);
			}

		}
	}

	// this method returns the numbers obtained in the dice rolled
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
