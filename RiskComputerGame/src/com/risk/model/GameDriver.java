package com.risk.model;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.InputMismatchException;
import java.util.List;
import java.util.Scanner;

public class GameDriver {

	public static int playerGTTerr = 0;

	public static void startGame(HashMap<String, List<String>> territoryMap) throws InterruptedException {

		int numberOfPlayers = 0;

		while (playerGTTerr != 1) {

			try {
				numberOfPlayers = enterPlayersMenu();

				StartUpPhase.startUpPhase(numberOfPlayers, territoryMap);

				if (playerGTTerr == 0) {
					continue;
				} else
					playerGTTerr = 1;

			} catch (InputMismatchException e) {
				System.out.println("----------------------------------------");
				System.out.println("Please enter only numbers");
				continue;

			}

		}

		for (int plyr = 1; plyr <= numberOfPlayers; plyr++) {

			System.out.println("\n---------------------------------------------------");
			System.out.println("Player " + plyr + " :");
			playerInfoDisplay(Integer.toString(plyr), territoryMap);

		}

		System.out.printf("\n--------------------------------------------------------");
		System.out.printf("--------------------------------------------------------");
		System.out.println("\n\t\t\t\tSTARTUP PHASE COMPLETED");
		System.out.printf("--------------------------------------------------------");

		Thread.sleep(5000);

		// Round Robin
		for (int plyr = 1; plyr <= numberOfPlayers; plyr++) {

			System.out.printf("\n--------------------------------------------------------");
			System.out.printf("--------------------------------------------------------\n");
			System.out.println("PLAYER " + plyr + " : REINFORCEMENT");

			ReinforcementClass.calculateReinforcement(Integer.toString(plyr));
			ReinforcementClass.reinforceRandom(Integer.toString(plyr));
			playerInfoDisplay(Integer.toString(plyr), territoryMap);

			Thread.sleep(5000);

			System.out.printf("\n--------------------------------------------------------");
			System.out.printf("--------------------------------------------------------\n");
			System.out.println("PLAYER " + plyr + " : FORTIFICATION");

			FortificationClass.createFortifySet(Integer.toString(plyr), territoryMap);
			FortificationClass.randomFortification(Integer.toString(plyr));

			if (FortificationClass.fortifySetEmpty == 1) {
				System.out.printf("--------------------------------------------------------\n");
				System.out.println("NO TWO ADJACENT TERRITORIES ARE OWNED BY PLAYER " + plyr);
			}

			else
				playerInfoDisplay(Integer.toString(plyr), territoryMap);

			Thread.sleep(5000);
		}

	}

	/**
	 * 
	 * @return numberOfPlayers
	 */
	public static int enterPlayersMenu() {

		System.out.println("---------------------------------------------");
		System.out.println("Please enter number of players : ");
		int numberOfPlayers = 0;
		String inPlayers;
		Scanner input = new Scanner(System.in);
		numberOfPlayers = input.nextInt();
		return numberOfPlayers;
	}

	public static void playerInfoDisplay(String player, HashMap<String, List<String>> territoryMap) {

		for (String playerInfoKey : StartUpPhase.playerInfo.keySet()) {

			String[] playerInfoVal = playerInfoKey.split("-");

			if (player.equals(playerInfoVal[0]) || player == playerInfoVal[0]) {

				System.out.println("\n\t--------------");
				System.out.println("\t" + "Territory : " + playerInfoVal[1]);

				System.out.println("\t\t" + "Continent : " + playerInfoVal[2]);

				System.out.println("\t\t" + "Armies : " + StartUpPhase.playerInfo.get(playerInfoKey));

				System.out.printf("\t\t" + "Adjacent Countries : ");

				List<String> adjacentList = territoryMap.get(playerInfoVal[2] + "," + playerInfoVal[1]);

				for (String adjacent : adjacentList) {
					System.out.printf(adjacent + "\t");
				}

			}

		}

	}

}
