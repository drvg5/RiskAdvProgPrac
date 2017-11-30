package com.risk.model;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;
import java.util.Scanner;

import com.risk.utility.RiskConstants;

/**
 * The Class AttackPhaseModel.
 * 
 * The tasks performed by this class are:
 * <ul>
 * <li>Initiating the attack phase
 * <li>Rolling the dice and performing an analysis of the dice rolled
 * <li>Checking if an attack is possible or not
 * <li>Deciding the Winner or Loser
 * </ul>
 * 
 * @author drvg5
 * 
 */
public class AttackPhaseModel {

	/** The no of attacker armies. */
	static int noOfAttackerArmies;

	/** The no of defender armies. */
	static int noOfDefenderArmies;

	/** The attacker key. */
	static String attackerKey;

	/** The defender key. */
	static String defenderKey;

	/** The attacker player. */
	static String attackerPlayer;

	/** The defender player. */
	static String defenderPlayer;

	/** The armies depleted. */
	static boolean armiesDepleted = false;

	/** The attack again. */
	static String attackAgain = "Yes";

	/** The attack same country again. */
	static String attackSameCountryAgain = "Yes";

	/** The input. */
	static Scanner input = new Scanner(System.in);

	/** The card get. */
	static boolean cardGet = false;

	/** The all adjacent countires conquered. */
	static boolean allAdjacentCountiresConquered = false;

	// for getting data from player info entries for the specific player
	/** The player acc to player no. */
	// list for attackers
	static List<String> playerAccToPlayerNo = new ArrayList<String>();

	/** The player not acc to player no. */
	// list for attacked candidates
	static List<String> playerNotAccToPlayerNo = new ArrayList<String>();

	/** The current attacker adj countries. */
	static List<String> currentAttackerAdjCountries = new ArrayList<String>();

	/** The random. */
	static Random random;

	/**
	 * Choose country to be attacked.
	 *
	 * @param plyr
	 *            the plyr
	 * @param territoryMap
	 *            the territory map
	 */
	/*
	 * This method is called when the Random Player Behavior is chosen
	 * 
	 * method for checking adjacency of the attacker and defender and then attacking
	 * the defender country
	 * 
	 */
	public static void attackCountryRandomly(int plyr, HashMap<String, List<String>> territoryMap) {
		attackAgain = "Yes";
		attackSameCountryAgain = "Yes";
		noOfAttackerArmies = 0;
		noOfDefenderArmies = 0;
		boolean adjacencyCheck = false;
		boolean noOfArmiesCheck = false;
		boolean checkValidityOfDef = false;

		// populating the lists for attacker and defender
		populateListsForAttackerAndDefender(plyr);

		// randomly select attacker
		random = new Random();

		// global key initialized
		int randomNoGenerated = random.nextInt(playerAccToPlayerNo.size());
		if (randomNoGenerated == 0) {
			// randomly select attacker
			attackerKey = playerAccToPlayerNo.get(randomNoGenerated + 1);
		} else {
			// randomly select attacker
			attackerKey = playerAccToPlayerNo.get(randomNoGenerated);

		}

		// getting number of attacker armies
		noOfAttackerArmies = StartUpPhaseModel.playerInfo.get(attackerKey);

		System.out.println("---------------------------------------------------------------------------");
		System.out.println("****************** ATTACK PHASE FOR PLAYER " + plyr + " BEGINS ***********************");
		System.out.println("---------------------------------------------------------------------------");

		while (attackAgain.trim().equalsIgnoreCase("Yes") || attackAgain.trim().equalsIgnoreCase("Y")) {

			allAdjacentCountiresConquered = false;

			// attacker country changed if one army is remaining
			if (StartUpPhaseModel.playerInfo.get(attackerKey) == 1) {
				randomNoGenerated = random.nextInt(playerAccToPlayerNo.size());
				if (randomNoGenerated == 0) {
					// randomly select attacker
					attackerKey = playerAccToPlayerNo.get(randomNoGenerated + 1);

				} else {
					// randomly select attacker
					attackerKey = playerAccToPlayerNo.get(randomNoGenerated);

				}
				// getting number of attacker armies
				noOfAttackerArmies = StartUpPhaseModel.playerInfo.get(attackerKey);
			}

			// checking if defender selected is adjacent or not
			while (adjacencyCheck == false) {

				List<String> countriesOfotherPlayers = new ArrayList<String>();

				for (String playerInfoKey : playerNotAccToPlayerNo) {
					String[] keysplit = playerInfoKey.split("-");
					countriesOfotherPlayers.add(keysplit[1]);
				}
				if (!currentAttackerAdjCountries.isEmpty()) {
					for (String country : currentAttackerAdjCountries) {
						if (countriesOfotherPlayers.contains(country)) {
							checkValidityOfDef = true;
						}
					}

					if (!checkValidityOfDef) {
						allAdjacentCountiresConquered = true;
						break;
					}

				}

				randomNoGenerated = random.nextInt(playerNotAccToPlayerNo.size());

				if (randomNoGenerated == 0) {
					// randomly select defender
					defenderKey = playerNotAccToPlayerNo.get(randomNoGenerated + 1);
				} else {
					// randomly select defender
					defenderKey = playerNotAccToPlayerNo.get(randomNoGenerated);
				}
				// getting number of defender armies
				noOfDefenderArmies = StartUpPhaseModel.playerInfo.get(defenderKey);

				// checking if defender selected is eligible or not
				currentAttackerAdjCountries.clear();

				adjacencyCheck = checkDefenderAdjacency(plyr, territoryMap);
				checkValidityOfDef = false;

			}
			currentAttackerAdjCountries.clear();

			if (allAdjacentCountiresConquered) {
				System.out.println();
				System.out.println();
				System.out.println("---------------------------------------------------------------------------");

				System.out.println("All Adjacent Countries have been Conquered");
				System.out.println("---------------------------------------------------------------------------");
				System.out.println();
				allAdjacentCountiresConquered = false;

				// generating new attacker
				randomNoGenerated = random.nextInt(playerAccToPlayerNo.size());
				if (randomNoGenerated == 0) {
					// randomly select attacker
					attackerKey = playerAccToPlayerNo.get(randomNoGenerated + 1);

				} else {
					// randomly select attacker
					attackerKey = playerAccToPlayerNo.get(randomNoGenerated);

				}
				// checking if the attacker is in a position to attack or not
				noOfArmiesCheck = checkForAtttackerArmies();

				if (noOfArmiesCheck) {
					// asking user permission to attack
					System.out.println("\n\t--------------------------------------------------------------");
					System.out.println("\t\tDo you want to attack again? ");
					System.out.println("\t\tPlease enter Yes or No :");
					attackAgain = input.next();
				} else {
					System.out.println("\n\t--------------------------------------------------------------");
					System.out.println("\t\tATTACK NOT POSSIBLE AS ANY OF THE ATTACKER ARMIES ARE NOT MORE THAN 1  ");
				}

			} else {
				// attack initiated
				attackProcessRandom(plyr, territoryMap);

				// checking if the attacker is in a position to attack or not
				noOfArmiesCheck = checkForAtttackerArmies();

				if (noOfArmiesCheck) {
					// asking user permission to attack
					System.out.println("\n\t--------------------------------------------------------------");
					System.out.println("\t\tDo you want to attack again? ");
					System.out.println("\t\tPlease enter Yes or No :");
					attackAgain = input.next();
				} else {
					System.out.println("\n\t--------------------------------------------------------------");
					System.out.println("\t\tATTACK NOT POSSIBLE AS ANY OF THE ATTACKER ARMIES ARE NOT MORE THAN 1  ");
				}

			}

			adjacencyCheck = false;

		}

		// // adding cards after attack is completed
		// addCards(attackerPlayer);

		System.out.println("---------------------------------------------------------------------------");
		System.out.println("****************** ATTACK PHASE FOR PLAYER " + plyr + " ENDS *************************");
		System.out.println("---------------------------------------------------------------------------");

	}

	/**
	 * Attack aggressively.
	 *
	 * @param playerInfoKey
	 *            the player info key
	 * @param territoryMap
	 *            the territory map
	 */
	/*
	 * This method is called when the Aggressive Player Behavior is chosen
	 * 
	 * method for checking adjacency of the attacker and defender and then attacking
	 * the defender country
	 */
	public static void attackAggressively(String playerInfoKey, HashMap<String, List<String>> territoryMap) {
		attackAgain = "Yes";
		attackSameCountryAgain = "Yes";
		noOfAttackerArmies = 0;
		noOfDefenderArmies = 0;
		boolean adjacencyCheck = false;
		boolean attackPossible = true;
		boolean checkValidityOfDef = false;

		String[] keySplitForPlyrNo = playerInfoKey.split("-");
		allAdjacentCountiresConquered = false;

		// randomly select defender
		random = new Random();

		attackerKey = playerInfoKey;

		// getting number of attacker armies
		noOfAttackerArmies = StartUpPhaseModel.playerInfo.get(playerInfoKey);

		System.out.println("---------------------------------------------------------------------------");
		System.out.println("****************** ATTACK PHASE FOR PLAYER " + playerInfoKey.charAt(0)
				+ " BEGINS ***********************");
		System.out.println("---------------------------------------------------------------------------");

		// checking if defender selected is adjacent or not

		while (attackPossible) {
			while (adjacencyCheck == false) {

				List<String> countriesOfotherPlayers = new ArrayList<String>();

				for (String playerKey : playerNotAccToPlayerNo) {
					String[] keysplit = playerKey.split("-");
					countriesOfotherPlayers.add(keysplit[1]);
				}

				if (!currentAttackerAdjCountries.isEmpty()) {
					for (String country : currentAttackerAdjCountries) {
						if (countriesOfotherPlayers.contains(country)) {
							checkValidityOfDef = true;
						}
					}

					if (checkValidityOfDef == false) {
						allAdjacentCountiresConquered = true;
						break;
					}
				}

				int randomNoGenerated = random.nextInt(playerNotAccToPlayerNo.size());
				if (randomNoGenerated == 0) {
					// randomly select defender
					defenderKey = playerNotAccToPlayerNo.get(randomNoGenerated + 1);
				} else {
					// randomly select defender
					defenderKey = playerNotAccToPlayerNo.get(randomNoGenerated);
				}
				// getting number of defender armies
				noOfDefenderArmies = StartUpPhaseModel.playerInfo.get(defenderKey);

				// checking if defender selected is eligible or not
				currentAttackerAdjCountries.clear();

				adjacencyCheck = checkDefenderAdjacency(Integer.valueOf(keySplitForPlyrNo[0]), territoryMap);
				checkValidityOfDef = false;

			}
			if (allAdjacentCountiresConquered) {
				System.out.println();
				System.out.println();
				System.out.println("---------------------------------------------------------------------------");

				System.out.println("All Adjacent Countries have been Conquered");
				System.out.println("---------------------------------------------------------------------------");
				System.out.println();
				break;
			} else {
				// attack initiated
				attackPossible = attackProcessAggressive(Integer.valueOf(keySplitForPlyrNo[0]), territoryMap);
				adjacencyCheck = false;
			}

		}

		// adding cards after attack is completed
		// addCards(attackerPlayer);
		currentAttackerAdjCountries.clear();
		System.out.println("---------------------------------------------------------------------------");
		System.out.println("****************** ATTACK PHASE FOR PLAYER " + playerInfoKey.charAt(0)
				+ " ENDS *************************");
		System.out.println("---------------------------------------------------------------------------");

	}

	/**
	 * Check for atttacker armies.
	 *
	 * @return true, if successful
	 */
	private static boolean checkForAtttackerArmies() {
		boolean attackPossible = false;
		for (String attacker : playerAccToPlayerNo) {
			if (StartUpPhaseModel.playerInfo.get(attacker) > 1) {
				attackPossible = true;
				break;
			}
		}
		return attackPossible;
	}

	/**
	 * Check defender adjacency.
	 *
	 * @param plyr
	 *            the plyr
	 * @param territoryMap
	 *            the territory map
	 * @return true, if successful
	 */
	private static boolean checkDefenderAdjacency(int plyr, HashMap<String, List<String>> territoryMap) {

		boolean check = false;

		String[] keySplit1 = attackerKey.split("-");
		attackerPlayer = keySplit1[0];
		String territoryAttacker = keySplit1[1];
		String[] keySplit2 = defenderKey.split("-");
		defenderPlayer = keySplit2[0];
		String territoryAttacked = keySplit2[1];
		for (Map.Entry<String, List<String>> iterate : territoryMap.entrySet()) {
			//
			String[] keyArray = iterate.getKey().split(",");
			String territory = keyArray[1];
			if (territoryAttacker.equals(territory)) {
				currentAttackerAdjCountries.addAll(iterate.getValue());

				for (String country : iterate.getValue()) {

					if (territoryAttacked.equals(country)) {

						for (String playerInfoKey : StartUpPhaseModel.playerInfo.keySet()) {
							String[] playerInfoArr = playerInfoKey.split("-");
							if (playerInfoArr[1].equalsIgnoreCase(territoryAttacked)
									&& !(Integer.parseInt(playerInfoArr[0]) == plyr)) {
								check = true;

							}
						}

					}
				}
			}

		}

		return check;

	}

	/**
	 * Attack process.
	 *
	 * @param plyr
	 *            the plyr
	 * @param territoryMap
	 *            the territory map
	 * @return true, if successful
	 */
	static boolean attackProcessAggressive(int plyr, HashMap<String, List<String>> territoryMap) {
		boolean attackPossible = true;
		String[] keySplit1 = attackerKey.split("-");
		attackerPlayer = keySplit1[0];
		String territoryAttacker = keySplit1[1];
		String[] keySplit2 = defenderKey.split("-");
		defenderPlayer = keySplit2[0];
		String territoryAttacked = keySplit2[1];
		boolean checkAttackerCountries = true;
		for (Map.Entry<String, List<String>> iterate : territoryMap.entrySet()) {
			//
			String[] keyArray = iterate.getKey().split(",");
			String territory = keyArray[1];
			if (territoryAttacker.equals(territory)) {

				for (String country : iterate.getValue()) {

					if (territoryAttacked.equals(country)) {
						while (checkAttackerCountries) {

							// attackSameCountryAgain
							noOfAttackerArmies = StartUpPhaseModel.playerInfo.get(attackerKey);

							noOfDefenderArmies = StartUpPhaseModel.playerInfo.get(defenderKey);

							System.out.println("\n\n\t\t" + "PLAYER " + keySplit1[0] + " : Attacking Territory - "
									+ territoryAttacker + " --> number of armies : " + noOfAttackerArmies);
							System.out.println("\t\tPLAYER " + keySplit2[0] + ": Defending Territory - "
									+ territoryAttacked + " --> number of armies : " + noOfDefenderArmies);
							System.out.println();
							System.out.println();
							System.out.println("\t\tPLAYER " + keySplit1[0] + " from " + territoryAttacker
									+ " attacks on " + territoryAttacked + " owned by PLAYER " + keySplit2[0]);
							System.out.println();
							System.out.println();
							// Dice Rolled
							// country is attacked and armies are deducted
							// based on the dice roll obtained
							try {
								rollDice();
								populateListsForAttackerAndDefender(plyr);

							} catch (InterruptedException e) {
								e.printStackTrace();
							}
							if (StartUpPhaseModel.playerInfo.get(defenderKey) != null
									&& StartUpPhaseModel.playerInfo.get(defenderKey) > 0) {
								if (StartUpPhaseModel.playerInfo.get(attackerKey) > 1) {
									checkAttackerCountries = true;

								} else {
									System.out.println(
											"\n\t--------------------------------------------------------------");
									System.out.println(
											"\t\tATTACK NOT POSSIBLE AS ATTACKER ARMIES ARE NOT MORE THAN 1  ");
									attackPossible = false;
									return attackPossible;
								}

							} else {
								System.out
										.println("\n\t--------------------------------------------------------------");
								System.out.println("\t\tATTACK NOT POSSIBLE AS DEFENDER ARMIES ARE DEPLETED ");
								attackPossible = true;
								return attackPossible;
							}

						}

					}
				}
			}

		}
		return attackPossible;

	}

	/**
	 * Attack process.
	 *
	 * @param plyr
	 *            the plyr
	 * @param territoryMap
	 *            the territory map
	 */
	static void attackProcessRandom(int plyr, HashMap<String, List<String>> territoryMap) {
		String[] keySplit1 = attackerKey.split("-");
		attackerPlayer = keySplit1[0];
		String territoryAttacker = keySplit1[1];
		String[] keySplit2 = defenderKey.split("-");
		defenderPlayer = keySplit2[0];
		String territoryAttacked = keySplit2[1];
		for (Map.Entry<String, List<String>> iterate : territoryMap.entrySet()) {
			//
			String[] keyArray = iterate.getKey().split(",");
			String territory = keyArray[1];
			if (territoryAttacker.equals(territory)) {

				for (String country : iterate.getValue()) {

					if (territoryAttacked.equals(country)) {
						while (attackSameCountryAgain.trim().equalsIgnoreCase("Yes")
								|| attackSameCountryAgain.trim().equalsIgnoreCase("Y")) {

							// attackSameCountryAgain
							noOfAttackerArmies = StartUpPhaseModel.playerInfo.get(attackerKey);

							noOfDefenderArmies = StartUpPhaseModel.playerInfo.get(defenderKey);

							System.out.println("\n\n\t\t" + "PLAYER " + keySplit1[0] + " : Attacking Territory - "
									+ territoryAttacker + " --> number of armies : " + noOfAttackerArmies);
							System.out.println("\t\tPLAYER " + keySplit2[0] + ": Defending Territory - "
									+ territoryAttacked + " --> number of armies : " + noOfDefenderArmies);
							System.out.println();
							System.out.println();
							System.out.println("\t\tPLAYER " + keySplit1[0] + " from " + territoryAttacker
									+ " attacks on " + territoryAttacked + " owned by PLAYER " + keySplit2[0]);
							System.out.println();
							System.out.println();
							// Dice Rolled
							// country is attacked and armies are deducted
							// based on the dice roll obtained
							try {
								rollDice();
								populateListsForAttackerAndDefender(plyr);

							} catch (InterruptedException e) {
								e.printStackTrace();
							}
							if (StartUpPhaseModel.playerInfo.get(defenderKey) != null
									&& StartUpPhaseModel.playerInfo.get(defenderKey) > 0) {
								if (StartUpPhaseModel.playerInfo.get(attackerKey) > 1) {
									System.out.println(
											"\t--------------------------------------------------------------");
									System.out.println("\t\tDo you want to attack the Same Country again? ");
									System.out.println("\t\tPlease enter  Yes or No : ");
									attackSameCountryAgain = input.next();
									if (!attackSameCountryAgain.trim().equalsIgnoreCase("Yes")
											&& !attackSameCountryAgain.trim().equalsIgnoreCase("Y")) {
										return;
									}
								} else {
									return;
								}

							} else {
								return;
							}

						}

					}
				}
			}

		}

	}

	/**
	 * Populate lists for attacker and defender.
	 *
	 * @param plyr
	 *            the plyr
	 */
	private static void populateListsForAttackerAndDefender(int plyr) {

		// removing elements of the list as data has changed
		playerAccToPlayerNo.clear();

		playerNotAccToPlayerNo.clear();

		for (String playerInfoKey : StartUpPhaseModel.playerInfo.keySet()) {
			String[] playerInfoArr = playerInfoKey.split("-");
			if (playerInfoArr[0].equals(String.valueOf(plyr))) {
				playerAccToPlayerNo.add(playerInfoKey);

			} else {
				playerNotAccToPlayerNo.add(playerInfoKey);

			}
		}
	}

	/**
	 * Find country with max armies.
	 *
	 * @param player
	 *            the player
	 * @return the string
	 */
	public static String findCountryWithMaxArmies(String player) {

		// populating the lists for attacker and defender
		populateListsForAttackerAndDefender(Integer.valueOf(player));
		// find country with max armies
		// Integer highestNoOfArmies =
		// StartUpPhaseModel.playerInfo.get(playerAccToPlayerNo.get(0));
		// String countryHigh = playerAccToPlayerNo.get(0);

		int highestNoOfArmies = 0;
		String countryHigh = "";
		for (String playerInfoKey : playerAccToPlayerNo) {
			if (StartUpPhaseModel.playerInfo.get(playerInfoKey) > highestNoOfArmies) {
				highestNoOfArmies = StartUpPhaseModel.playerInfo.get(playerInfoKey);
				countryHigh = playerInfoKey;
			}
		}

		return countryHigh;

	}

	/**
	 * Conquer all adjacent countries.
	 *
	 * @param player
	 *            the player
	 * @param territoryMap
	 *            the territory map
	 */
	public static void conquerAllAdjacentCountries(String player, HashMap<String, List<String>> territoryMap) {

		// populating the lists for attacker and defender
		populateListsForAttackerAndDefender(Integer.valueOf(player));

		// find adjacent countries
		String[] keysplit1 = {};
		String[] keysplit2 = {};

		// first store all the countries owned by the player
		List<String> countriesOfPlayerChosen = new ArrayList<String>();

		for (String playerInfoKey : playerAccToPlayerNo) {
			keysplit1 = playerInfoKey.split("-");
			countriesOfPlayerChosen.add(keysplit1[1]);
		}

		// store countries of other players along with the playerInfo keys

		HashMap<String, String> countriesOfOtherPlayers = new HashMap<String, String>();

		for (String playerInfoKey : playerNotAccToPlayerNo) {
			keysplit1 = playerInfoKey.split("-");
			countriesOfOtherPlayers.put(keysplit1[1], playerInfoKey);
		}

		for (String playerInfoKey : countriesOfPlayerChosen) {
			// keysplit1 = playerInfoKey.split("-");

			for (String iterate : territoryMap.keySet()) {
				keysplit2 = iterate.split(",");
				if (playerInfoKey.equals(keysplit2[1])) {

					changePlayerInfoDataForCheater(player, territoryMap.get(iterate), countriesOfOtherPlayers,
							countriesOfPlayerChosen);
					break;
				}
			}

		}

	}

	/**
	 * Change player info data for cheater.
	 *
	 * @param player
	 *            the player
	 * @param listOfAdjacentCountries
	 *            the list of adjacent countries
	 * @param countriesOfOtherPlayers
	 *            the countries of other players
	 * @param countriesOfPlayerChosen
	 *            the countries of player chosen
	 */
	// private static void changePlayerInfoDataForCheater(String player,
	// List<String> listOfAdjacentCountries,
	// HashMap<String, String> countriesOfOtherPlayers, List<String>
	// countriesOfPlayerChosen) {
	//
	// String value = "";
	// String modifiedKey = "";
	// String[] keySplit = {};
	// Integer armies = 0;
	// for (String country : listOfAdjacentCountries) {
	// // checking if attacker's countries are present in the adjacent countries
	// list
	// if (!countriesOfPlayerChosen.contains(country)) {
	// for (String iterate : countriesOfOtherPlayers.keySet()) {
	// if (country.equalsIgnoreCase(iterate)) {
	//
	// // changing key value in playerInfo
	// // i.e. changing player data
	// value = countriesOfOtherPlayers.get(iterate);
	// keySplit = value.split("-");
	//
	// keySplit[0] = player;
	// modifiedKey = keySplit[0] + "-" + keySplit[1] + "-" + keySplit[2];
	// armies = StartUpPhaseModel.playerInfo.get(value);
	// StartUpPhaseModel.playerInfo.put(modifiedKey, armies);
	// StartUpPhaseModel.playerInfo.remove(iterate);
	// break;
	//
	// }
	// }
	// }
	// else
	// {
	// continue;
	// }
	// }
	//
	// }

	private static void changePlayerInfoDataForCheater(String player, List<String> listOfAdjacentCountries,
			HashMap<String, String> countriesOfOtherPlayers, List<String> countriesOfPlayerChosen) {

		String value = "";
		String modifiedKey = "";
		String[] keySplit = {};
		Integer armies = 0;
		int c=3;
 
		for (String country : countriesOfOtherPlayers.keySet()) {
			if (listOfAdjacentCountries.contains(country) && !(countriesOfPlayerChosen.contains(country))) {

				value = countriesOfOtherPlayers.get(country);
				keySplit = value.split("-");

				keySplit[0] = player;
				modifiedKey = keySplit[0] + "-" + keySplit[1] + "-" + keySplit[2];
				armies = StartUpPhaseModel.playerInfo.get(value);
 				StartUpPhaseModel.playerInfo.put(modifiedKey, c++);
				StartUpPhaseModel.playerInfo.remove(value);
			}
		}
	}

	/**
	 * Roll dice.
	 *
	 * @throws InterruptedException
	 *             the interrupted exception
	 */
	/*
	 * dice is rolled and numbers obtained are used to compute the result
	 */
	public static void rollDice() throws InterruptedException {

		Thread.sleep(1000);
		int diceRollsForAttacker, diceRollsForDefender;
		List<Integer> diceArrayForAttackers = new ArrayList<Integer>();
		List<Integer> diceArrayForDefenders = new ArrayList<Integer>();

		if (noOfAttackerArmies > 2) {

			diceRollsForAttacker = 3;
			diceArrayForAttackers = AttackPhaseModel.getDiceNumbers(diceRollsForAttacker);
			System.out.print("\t\tPLAYER " + attackerPlayer + " Dice Roll : ");
			for (Integer integer : diceArrayForAttackers) {
				System.out.print(integer + " ");

			}

			System.out.println();
			System.out.println();

			diceRollsForDefender = 2;
			diceArrayForDefenders = AttackPhaseModel.getDiceNumbers(diceRollsForDefender);
			System.out.print("\t\tPLAYER " + defenderPlayer + " Dice Roll : ");
			for (Integer integer : diceArrayForDefenders) {
				System.out.print(integer + " ");

			}

			System.out.println();
			System.out.println();
		} else if (noOfAttackerArmies == 2) {

			diceRollsForAttacker = 2;
			diceArrayForAttackers = AttackPhaseModel.getDiceNumbers(diceRollsForAttacker);
			System.out.print("\t\tPLAYER " + attackerPlayer + " Dice Roll : ");
			for (Integer integer : diceArrayForAttackers) {
				System.out.print(integer + " ");

			}

			System.out.println();
			System.out.println();
			diceRollsForDefender = 2;
			diceArrayForDefenders = AttackPhaseModel.getDiceNumbers(diceRollsForDefender);
			System.out.print("\t\tPLAYER " + defenderPlayer + " Dice Roll : ");
			for (Integer integer : diceArrayForDefenders) {
				System.out.print(integer + " ");

			}
			System.out.println();
			System.out.println();
		} else if (noOfAttackerArmies == 1) {

			diceRollsForAttacker = 1;
			diceArrayForAttackers = AttackPhaseModel.getDiceNumbers(diceRollsForAttacker);
			System.out.println("\t\tPLAYER " + attackerPlayer + " Dice Roll : ");
			for (Integer integer : diceArrayForAttackers) {
				System.out.print(integer + " ");

			}
			System.out.println();
			System.out.println();

			diceRollsForDefender = 1;
			diceArrayForDefenders = AttackPhaseModel.getDiceNumbers(diceRollsForDefender);
			System.out.print("\t\tPLAYER " + defenderPlayer + " Dice Roll : ");

			for (Integer integer : diceArrayForDefenders) {
				System.out.print(integer + " ");

			}

			System.out.println();
			System.out.println();
		}

		// analyzing the numbers obtained to compute the no of armies
		diceRollAnalysis(diceArrayForAttackers, diceArrayForDefenders);

	}

	/**
	 * Dice roll analysis.
	 *
	 * @param diceArrayForAttackersList
	 *            the dice array for attackers list
	 * @param diceArrayForDefendersList
	 *            the dice array for defenders list
	 * @throws InterruptedException
	 *             the interrupted exception
	 */
	/*
	 * analyzing the numbers obtained to compute the no of armies thus making
	 * changes in player data
	 */
	private static void diceRollAnalysis(List<Integer> diceArrayForAttackersList,
			List<Integer> diceArrayForDefendersList) throws InterruptedException {

		Collections.sort(diceArrayForAttackersList);
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
				--armiesObtainedForDefender;

			} else {
				--armiesObtainedForAttacker;

			}
			if (attacker2ndBest > minDefender2ndRoll) {
				--armiesObtainedForDefender;
			} else {
				--armiesObtainedForAttacker;
			}

			// Updating the Player Data

			int noOfAttackerArmies = StartUpPhaseModel.playerInfo.get(attackerKey);
			noOfAttackerArmies = noOfAttackerArmies + armiesObtainedForAttacker;
			// Updating Attacker Data
			StartUpPhaseModel.playerInfo.replace(attackerKey, noOfAttackerArmies);

			int noOfDefenderArmies = StartUpPhaseModel.playerInfo.get(defenderKey);
			noOfDefenderArmies = noOfDefenderArmies + armiesObtainedForDefender;
			// Updating Defender Data
			StartUpPhaseModel.playerInfo.replace(defenderKey, noOfDefenderArmies);

			String[] attackerKeySplit = attackerKey.split("-");
			String[] defenderKeySplit = defenderKey.split("-");
			if (noOfDefenderArmies <= 0) {

				Thread.sleep(1000);
				System.out.println();
				System.out.println();
				System.out.println("\t\tDEFENDER ARMIES DEPLETED");

				System.out.println();
				System.out.println();

				System.out.println("\t\tATTACKER CONQUERS THE TERRITORY");

				System.out.println("\n\n\t\t" + "PLAYER " + attackerPlayer + " : Attacking Territory - "
						+ attackerKeySplit[1] + " --> number of armies : " + noOfAttackerArmies);
				System.out.println("\t\t" + "PLAYER " + defenderPlayer + " : Defending Territory - "
						+ defenderKeySplit[1] + " --> number of armies : 0");

				cardGet = true;

				// assigning defender territory to attacker
				String[] defKeyString = defenderKey.split("-");
				defKeyString[0] = attackerPlayer;
				String modifiedKey = defKeyString[0] + "-" + defKeyString[1] + "-" + defKeyString[2];
				// attacking player must place a number of armies
				// in the conquered country which is greater or equal than the number of dice
				// that was used in the attack that
				// resulted in conquering the country
				StartUpPhaseModel.playerInfo.put(modifiedKey, diceArrayForAttackersList.size() - 1);
				noOfDefenderArmies = StartUpPhaseModel.playerInfo.get(modifiedKey);
				StartUpPhaseModel.playerInfo.remove(defenderKey);

				// adding cards after a COUNTRY is conquered is completed
				addCards(attackerPlayer);
				Thread.sleep(2000);

			} else if (noOfAttackerArmies <= 0) {
				System.out.println("\n\n\t\t" + "PLAYER " + attackerPlayer + " : Attacking Territory - "
						+ attackerKeySplit[1] + " --> number of armies : " + "1");
				System.out.println("\t\t" + "PLAYER " + defenderPlayer + " : Defending Territory - "
						+ defenderKeySplit[1] + " --> number of armies : " + noOfDefenderArmies);

				System.out.println();
				System.out.println("\t\tATTACKER ARMIES DEPLETED");
				StartUpPhaseModel.playerInfo.replace(attackerKey, 1);
				noOfAttackerArmies = StartUpPhaseModel.playerInfo.get(attackerKey);

				System.out.println();
				System.out.println("\t\tATTACKER ARMIES REPLENISHED");
				System.out.println();
				System.out.println("\n\n\t\t" + "PLAYER " + attackerPlayer + " : Attacking Territory - "
						+ attackerKeySplit[1] + " --> number of armies : " + "1");

				Thread.sleep(2000);

			} else {
				Thread.sleep(2000);
				System.out.println("\t\tATTACK COMPLETED");
				System.out.println("\n\n\t\t" + "PLAYER " + attackerPlayer + " : Attacking Territory - "
						+ attackerKeySplit[1] + " --> number of armies : " + noOfAttackerArmies);
				System.out.println("\t\t" + "PLAYER " + defenderPlayer + " : Defending Territory - "
						+ defenderKeySplit[1] + " --> number of armies : " + noOfDefenderArmies);

			}

		}
		// Case 2 : attacker and defender both are given 1 dice roll
		else if (diceArrayForAttackersList.size() == 1) {
			maxAttacker = diceArrayForAttackersList.get(0);
			maxDefender = diceArrayForDefendersList.get(0);
			if (maxAttacker > maxDefender) {
				--armiesObtainedForDefender;

			} else {
				--armiesObtainedForAttacker;
			}

			// Updating the Player Data
			int noOfAttackerArmies = StartUpPhaseModel.playerInfo.get(attackerKey);
			noOfAttackerArmies = noOfAttackerArmies + armiesObtainedForAttacker;
			// Updating Attacker Data
			StartUpPhaseModel.playerInfo.replace(attackerKey, noOfAttackerArmies);

			int noOfDefenderArmies = StartUpPhaseModel.playerInfo.get(defenderKey);
			noOfDefenderArmies = noOfDefenderArmies + armiesObtainedForDefender;
			// Updating Defender Data
			StartUpPhaseModel.playerInfo.replace(defenderKey, noOfDefenderArmies);

			String[] attackerKeySplit = attackerKey.split("-");
			String[] defenderKeySplit = defenderKey.split("-");

			if (noOfDefenderArmies <= 0) {

				Thread.sleep(1000);

				System.out.println();
				System.out.println();
				System.out.println("\t\tDEFENDER ARMIES DEPLETED");

				System.out.println();
				System.out.println("\t\tATTACKER CONQUERS THE TERRITORY");

				System.out.println("\n\n\t\t" + "PLAYER " + attackerPlayer + " : Attacking Territory - "
						+ attackerKeySplit[1] + " --> number of armies : " + noOfAttackerArmies);
				System.out.println("\t\t" + "PLAYER " + defenderPlayer + " : Defending Territory - "
						+ defenderKeySplit[1] + " --> number of armies : 0");

				cardGet = true;

				// assigning defender territory to attacker
				String[] defKeyString = defenderKey.split("-");
				defKeyString[0] = attackerPlayer;
				String modifiedKey = defKeyString[0] + "-" + defKeyString[1] + "-" + defKeyString[2];
				StartUpPhaseModel.playerInfo.put(modifiedKey, diceArrayForAttackersList.size() - 1);
				noOfDefenderArmies = StartUpPhaseModel.playerInfo.get(modifiedKey);
				StartUpPhaseModel.playerInfo.remove(defenderKey);
				// adding cards after a COUNTRY is conquered is completed
				addCards(attackerPlayer);
				Thread.sleep(2000);

			} else if (noOfAttackerArmies <= 0) {

				System.out.println("\n\n\t\t" + "PLAYER " + attackerPlayer + " : Attacking Territory - "
						+ attackerKeySplit[1] + " --> number of armies : " + "1");
				System.out.println("\t\t" + "PLAYER " + defenderPlayer + " : Defending Territory - "
						+ defenderKeySplit[1] + " --> number of armies : " + noOfDefenderArmies);

				System.out.println();
				System.out.println("\t\tATACKER ARMIES DEPLETED");

				StartUpPhaseModel.playerInfo.put(attackerKey, 1);
				noOfAttackerArmies = StartUpPhaseModel.playerInfo.get(attackerKey);

				System.out.println();
				System.out.println("\t\tATTACKER ARMIES REPLENISHED");
				System.out.println();
				System.out.println("\n\n\t\t" + "PLAYER " + attackerPlayer + " : Attacking Territory - "
						+ attackerKeySplit[1] + " --> number of armies : " + "1");

				Thread.sleep(2000);

			} else {
				Thread.sleep(2000);
				System.out.println("\t\tATTACK COMPLETED");
				System.out.println("\n\n\t\t" + "PLAYER " + attackerPlayer + " : Attacking Territory - "
						+ attackerKeySplit[1] + " --> number of armies : " + noOfAttackerArmies);
				System.out.println("\t\t" + "PLAYER " + defenderPlayer + " : Defending Territory - "
						+ defenderKeySplit[1] + " --> number of armies : " + noOfDefenderArmies);

			}

		}

	}

	/**
	 * Gets the dice numbers.
	 *
	 * @param dicerolls
	 *            the dicerolls
	 * @return the dice numbers
	 */
	/*
	 * this method generate and returns the numbers obtained in the dice rolled
	 */
	public static List<Integer> getDiceNumbers(int dicerolls) {
		random = new Random();
		List<Integer> diceArrayList = new ArrayList<Integer>();

		switch (dicerolls) {
		case 1:
			diceArrayList.add(random.nextInt(6) + 1);
			break;
		case 2:
			diceArrayList.add(random.nextInt(6) + 1);
			diceArrayList.add(random.nextInt(6) + 1);

			break;
		case 3:
			diceArrayList.add(random.nextInt(6) + 1);
			diceArrayList.add(random.nextInt(6) + 1);
			diceArrayList.add(random.nextInt(6) + 1);
			break;
		default:
			break;
		}
		return diceArrayList;
	}

	/**
	 * Adds the cards.
	 *
	 * @param player
	 *            the player
	 */
	public static void addCards(String player) {

		if (cardGet) {
			Random random = new Random();

			int index = random.nextInt(2);

			String card;

			card = RiskConstants.CARD_TYPES[index];

			System.out.println("\t\t" + "PLAYER " + player + " gets card : " + card);
			ArrayList<String> list = new ArrayList<String>();
			for (String cardKey : ReinforcementPhaseModel.playerCards.keySet()) {

				String[] keySplit = cardKey.split("-");
				if (keySplit[0].equals(player) || keySplit[0] == player) {
					list = ReinforcementPhaseModel.playerCards.get(cardKey);
					list.add(card);
					ReinforcementPhaseModel.playerCards.put(cardKey, list);
					break;
				}

			}
		}
		cardGet = false;
	}

}
