package com.risk.model;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Observable;
import java.util.Random;
import java.util.TreeMap;
import java.util.TreeSet;


/**
 * This class is an Observable Class. It has methods to carry out the start up phase before the round robin begins.
 * @author Ashish Sharma
 *
 */
public class StartUpPhaseModel extends Observable {

	/**
	 * playerInfo HashMap stores the values corresponding to a particular player
	 * <p>
	 * It stores in <strong>KEY</strong> as a String of combination of player
	 * number, territory, continent each separated by a hyphen "-"
	 * </p>
	 * Examples
	 * <ul>
	 * <li>2-Germany-Europe</li>
	 * <li>3-England-Europe</li>
	 * <li>2-India-Asia</li>
	 * </ul>
	 * <p>
	 * The <strong>VALUE</strong> to which each key points is the number of army
	 * units in that particular territory
	 * </p>
	 */
	public static HashMap<String, Integer> playerInfo = new HashMap<String, Integer>();

	/**
	 * HashMap to store number of territories which must be assigned to each
	 * player initially
	 */
	public static HashMap<String, Integer> terrPerPlayer = new HashMap<String, Integer>();

	/**
	 * This HashMap stores a territory as <strong>KEY</strong> and continent to
	 * which a territory belongs as <strong>VALUE</strong>
	 */
	public static HashMap<String, String> terrCont = new HashMap<String, String>();

	/**
	 * This TreeMap stores the number of territories per continent.
	 */
	public static TreeMap<String, Integer> terrPerCont = new TreeMap<String, Integer>();

	/**
	 * This TreeSet is used to track whether a country has been alloted or not
	 * <p>
	 * Takes in input a String of (country+"-"+(0 or 1)) 0 if not taken; 1 if taken
	 * </p>
	 */
	public static TreeSet<String> countryTaken = new TreeSet<String>();

	/**
	 * This variable is used to store the initial armies after calculation as per the number of players
	 */
	public static int initialArmies = 0;

	/**
	 * This variable is used to store the total number of territories in a map after calculation.
	 */
	public static int totalTerr = 0;

	/**
	 * This variable is used in JUNIT tests.
	 */
	public static boolean junitCheckCount = true;

	// variables for DeployArmiesUI
	
	/**
	 * This variable stores is used in the {@link com.risk.ui.DeployArmiesUI DeployArmiesUI Class methods}
	 */
	private String chosenRandomTerritory;
	
	/**
	 * This variable stores the current player and is used in the {@link com.risk.ui.DeployArmiesUI DeployArmiesUI Class methods}
	 */
	private String currentPlayer;
	
	/**
	 * This variable stores the army units in a territory before deployment in start up phase.
	 * This varibale is used in the {@link com.risk.ui.DeployArmiesUI DeployArmiesUI Class methods}
	 */
	private int beforeDeployUnits;
	
	/**
	 * This variable stores the army units in a territory after deployment in start up phase
	 * This varibale is used in the {@link com.risk.ui.DeployArmiesUI DeployArmiesUI Class methods}
	 */
	private int afterDeployUnits;
	
	/**
	 * This variable stores the remaining army units for a player during deployment in the start up phase.
	 * This varibale is used in the {@link com.risk.ui.DeployArmiesUI DeployArmiesUI Class methods}
	 */
	private int remainingUnits;

	/**
	 * Gets the chosen territory
	 * @return chosenRandomTerritory chosen territory for deployment
	 */
	public String getChosenRandomTerritory() {
		return chosenRandomTerritory;
	}

	/**
	 * Sets the chosen territory
	 * @param chosenRandomTerritory chosen territory for deployment
	 */
	public void setChosenRandomTerritory(String chosenRandomTerritory) {
		this.chosenRandomTerritory = chosenRandomTerritory;
	}

	/**
	 * Gets the current player for whose deployment is being done.
	 * @return currentPlayer current player
	 */
	public String getCurrentPlayer() {
		return currentPlayer;
	}

	/**
	 * Sets the current player for whose deployment is being done.
	 * @param currentPlayer current player
	 */
	public void setCurrentPlayer(String currentPlayer) {
		this.currentPlayer = currentPlayer;
	}

	/**
	 * Gets the the army units in a territory before deployment in start up phase.
	 * @return beforeDeployUnits army units in a territory before deployment
	 */
	public int getBeforeDeployUnits() {
		return beforeDeployUnits;
	}

	/**
	 * Sets the the army units in a territory before deployment in start up phase.
	 * @param beforeDeployUnits army units in a territory before deployment
	 */
	public void setBeforeDeployUnits(int beforeDeployUnits) {
		this.beforeDeployUnits = beforeDeployUnits;
	}

	/**
	 * Gets the army units in a territory after deployment in start up phase.
	 * @return afterDeployUnits army units in a territory after deployment
	 */
	public int getAfterDeployUnits() {
		return afterDeployUnits;
	}

	/**
	 * Sets the army units in a territory after deployment in start up phase.
	 * @param afterDeployUnits army units in a territory after deployment
	 */
	public void setAfterDeployUnits(int afterDeployUnits) {
		this.afterDeployUnits = afterDeployUnits;
	}

	/**
	 * Gets the remaining army units for a player during deployment in the start up phase.
	 * @return remainingUnits remaining army units for a player
	 */
	public int getRemainingUnits() {
		return remainingUnits;
	}

	/**
	 * Sets the remaining army units for a player during deployment in the start up phase.
	 * @param remainingUnits remaining army units for a player
	 */
	public void setRemainingUnits(int remainingUnits) {
		this.remainingUnits = remainingUnits;
	}

	/**
	 * This method populates {@link StartUpPhaseModel#countryTaken} TreeSet and
	 * {@link StartUpPhaseModel#terrCont} HashMap and HashMap to Number of
	 * Territories per Continent
	 * <p>
	 * This method calls method {@link StartUpPhaseModel#terrPerPlayerPopulate} to
	 * populate the {@link StartUpPhaseModel#terrPerPlayer} HashMap and then calls
	 * {@link StartUpPhaseModel#assignTerritories} to assign territories randomly
	 * </p>
	 * 
	 * @param numberOfPlayers
	 *            Total number of players in a game
	 * @param territoryMap
	 *            HashMap which stores the Game Map values
	 */
	public static void preStartUp(int numberOfPlayers, HashMap<String, List<String>> territoryMap) {

		// populate countryTaken and terrCont
		for (String terrMapKey : territoryMap.keySet()) {

			// String continentCount = null;
			totalTerr++;

			String[] keySplit = terrMapKey.split(",");
			String continent = keySplit[0];
			String territory = keySplit[1];

			// populate countryTaken
			countryTaken.add(territory + "-" + 0);

			// populate TerrCont
			terrCont.put(territory, continent);

			// populate terrPerCont
			int continentCount = 0;
			if (terrPerCont.containsKey(continent)) {

				continentCount = terrPerCont.get(continent);

			}

			terrPerCont.put(continent, continentCount + 1);

		}

		if (numberOfPlayers > totalTerr) {

			System.out.println("Players can not be greater than the number of territories");

			GameDriverNew.playerGTTerr = 0;

			return;
		}

		GameDriverNew.playerGTTerr = 1;

		/*
		 * terrPerPlayerPopulate(numberOfPlayers,totalTerr);
		 * 
		 * 
		 * assignTerritories(numberOfPlayers, countryTaken, totalTerr);
		 * 
		 * 
		 * deployArmiesRandomly(numberOfPlayers);
		 * 
		 */

	}

	/**
	 * This method populates the {@link StartUpPhaseModel#terrPerPlayer} HashMap
	 * 
	 * @param numberOfPlayers
	 *            Number of Players in a game
	 * @param numberOfTerr
	 *            Number of territories in a whole map
	 */

	public static void terrPerPlayerPopulate(int numberOfPlayers, int numberOfTerr) {

		if (numberOfPlayers > numberOfTerr) {
			junitCheckCount = false;
		}

		int initialTerr = numberOfTerr / numberOfPlayers;

		int roundUpNumber = (int) Math.ceil((double) numberOfTerr / numberOfPlayers);

		for (int pl = 1; pl <= numberOfPlayers; pl++) {
			terrPerPlayer.put(String.valueOf(pl), initialTerr);
		}

		int remainingTerr = numberOfTerr - (initialTerr * numberOfPlayers);

		Random randomPlayer = new Random();
		List<String> listOfPlayerKeys = new ArrayList<String>(terrPerPlayer.keySet());

		int remCount = remainingTerr;

		// get random Players and assign them remaining countries one by one
		while (remCount != 0) {

			String randomPlayerKey = listOfPlayerKeys.get(randomPlayer.nextInt(numberOfPlayers));

			if (terrPerPlayer.get(randomPlayerKey) < roundUpNumber) {
				int newPlayerArmies = terrPerPlayer.get(randomPlayerKey) + 1;
				terrPerPlayer.put(randomPlayerKey, newPlayerArmies);
				remCount--;
			}

		}

	} // end terrPerPlayerPopulate

	/**
	 * This method assigns territories randomly to the players and populate the
	 * {@link StartUpPhaseModel#playerInfo} HashMap
	 * <p>
	 * This method while assigning also checks whether a country has already been
	 * assigned or not and that a player does not get all the countries in a
	 * continent
	 * </p>
	 * 
	 * @param numberOfPlayers
	 *            Total number of players in a game
	 * @param countryTaken
	 *            Country taken by player
	 * @param numberOfTerr
	 *            Number Of Territories
	 */
	public static void assignTerritories(int numberOfPlayers, TreeSet<String> countryTaken, int numberOfTerr) {

		StartUpPhaseModel.setInitialArmies(numberOfPlayers);

		// Creating a List of TreeSet countryTaken elements

		Random random = new Random();

		for (int pl = 1; pl <= numberOfPlayers; pl++) {

			// count of total territories each player can have in a map
			int countPT = 0;

			for (String plyr : terrPerPlayer.keySet()) {

				if (plyr.equals(Integer.toString(pl)) || plyr == String.valueOf(pl)) {
					countPT = terrPerPlayer.get(plyr);

				} else
					continue;
			}

			// loop until the player gets the randomly decided total number of territories
			// in a map

			while (countPT != 0) {

				List<String> list = new ArrayList<String>(countryTaken);

				// get territory randomly from list of country taken
				String randomVal = list.get(random.nextInt(list.size()));

				String[] randomTerr = randomVal.split("-");

				// get continent for randomCountry from terrCont

				String randomTerrContinent = terrCont.get(randomTerr[0]);

				// get randomTerrContinent count
				int randomTerrContinentCount = 0;
				randomTerrContinentCount = terrPerCont.get(randomTerrContinent);

				// playerInfo HashMap iterate to calculate the randomTerrContinent count the
				// player has
				int continentTerrPlayerCount = 0;

				for (String plyrVals : playerInfo.keySet()) {
					String[] plyrInfo = plyrVals.split("-");
					if (plyrInfo[0].equals(Integer.toString(pl)) || plyrInfo[0] == String.valueOf(pl)) {
						if (randomTerrContinent.equals(plyrInfo[2]) || randomTerrContinent == plyrInfo[2]) {
							continentTerrPlayerCount++;
						}
					}
				}

				String playerKey = null;

				if (randomTerr[1].equals("0")) {

					// Compare the continent territories value and the player has territories
					// already in a continent
					// to make sure not all territories of a continent has been assigned to a player
					int terr = randomTerrContinentCount - 1;

					if (continentTerrPlayerCount < terr) {
						continentTerrPlayerCount = 0;

						playerKey = String.valueOf(pl) + "-" + randomTerr[0] + "-" + randomTerrContinent;
						playerInfo.put(playerKey, 0);

						// modify countryTaken to indicate that country has been assigned to a player
						countryTaken.add(randomTerr[0] + "-0");

						countryTaken.remove(randomVal);

						// decrease number of territory each player further can have
						countPT--;
					} // end if(continentTerrPlayerCount < terr)

				} // end if(randomTerr[1].equals("0"))

			} // end while(countPT != 0)

		} // end for(int pl = 1;pl<=numberOfPlayers;pl++)

	}// end assignTerritories

	/**
	 * This method initially assigns armies to the territories randomly and add
	 * these to appropriate places in the {@link StartUpPhaseModel#playerInfo}
	 * HashMap
	 * <p>
	 * Each player is given 10 armies initially
	 * </p>
	 * 
	 * @param numberOfPlayers
	 *            Number Of Players
	 */
	public void deployArmiesRandomly(int numberOfPlayers) {

		Random randomCountry = new Random();

		int maxArmies;

		TreeMap<String, Integer> armiesRemainingPerPlyr = new TreeMap<String, Integer>();

		// initialize armiesRemainingPerPlyr
		for (int pl = 1; pl <= numberOfPlayers; pl++) {

			armiesRemainingPerPlyr.put(String.valueOf(pl), StartUpPhaseModel.initialArmies);

		}

		// round robin for putting armies one by one in territories

		while (!armiesRemainingPerPlyr.isEmpty()) {

			for (int pl = 1; pl <= numberOfPlayers; pl++) {

				if (!armiesRemainingPerPlyr.containsKey(String.valueOf(pl))) {

					continue;

				}

				int remArmies = armiesRemainingPerPlyr.get(String.valueOf(pl));

				// list for keeping the all territories of a particular player
				List<String> playerAllTerritoriesList = new ArrayList<String>();

				// populate playerAllTerritoriesList
				for (String playerInfo : StartUpPhaseModel.playerInfo.keySet()) {

					String[] playerInfoArr = playerInfo.split("-");
					if (playerInfoArr[0].equals(Integer.toString(pl)) || playerInfoArr[0] == Integer.toString(pl)) {

						playerAllTerritoriesList.add(playerInfo);

					} // end if (playerInfoArr[0].equals(Integer.toString(pl)) ...

				} // end for (String playerInfo : StartUpPhase...

				// list for keeping the territories of a particular player with zero armies
				List<String> playerZeroUnitTerritoryList = new ArrayList<String>();

				// populate playerZeroUnitTerritoryList
				for (String playerInfo : StartUpPhaseModel.playerInfo.keySet()) {

					String[] playerInfoArr = playerInfo.split("-");
					if (playerInfoArr[0].equals(Integer.toString(pl)) || playerInfoArr[0] == Integer.toString(pl)) {

						if (StartUpPhaseModel.playerInfo.get(playerInfo) == 0) {

							playerZeroUnitTerritoryList.add(playerInfo);

						}

					} // end if (playerInfoArr[0].equals(Integer.toString(pl)) ...

				} // end for (String playerInfo : StartUpPhase...

				if (!playerZeroUnitTerritoryList.isEmpty()) {

					// if there are any territories which has 0 units then prefer picking up
					// randomly these over others with one or more units

					// choose territory randomly to put armies into
					String randomChosenCountry = playerZeroUnitTerritoryList
							.get(randomCountry.nextInt(playerZeroUnitTerritoryList.size()));

					int playerInfoValue = StartUpPhaseModel.playerInfo.get(randomChosenCountry);

					playerInfoValue = playerInfoValue + 1;

					StartUpPhaseModel.playerInfo.put(randomChosenCountry, playerInfoValue);

					armiesRemainingPerPlyr.put(String.valueOf(pl), remArmies - 1);

					if (remArmies == 1) {
						armiesRemainingPerPlyr.remove(String.valueOf(pl));
					}

					setCurrentPlayer(String.valueOf(pl));
					setBeforeDeployUnits((playerInfoValue - 1));
					setAfterDeployUnits(playerInfoValue);
					setChosenRandomTerritory(randomChosenCountry.split("-")[1].toUpperCase());
					setRemainingUnits((remArmies - 1));

					setChanged();
					notifyObservers(this);

					try {
						System.in.read();

					} catch (IOException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}

				} else {

					// if no territories with 0 units remain then pick from playerAllTerritoriesList

					// choose territory randomly to put armies into
					String randomChosenCountry = playerAllTerritoriesList
							.get(randomCountry.nextInt(playerAllTerritoriesList.size()));

					int playerInfoValue = StartUpPhaseModel.playerInfo.get(randomChosenCountry);

					playerInfoValue = playerInfoValue + 1;

					StartUpPhaseModel.playerInfo.put(randomChosenCountry, playerInfoValue);

					armiesRemainingPerPlyr.put(String.valueOf(pl), remArmies - 1);

					if (remArmies == 1) {
						armiesRemainingPerPlyr.remove(String.valueOf(pl));
					}

					setCurrentPlayer(String.valueOf(pl));
					setBeforeDeployUnits((playerInfoValue - 1));
					setAfterDeployUnits(playerInfoValue);
					setChosenRandomTerritory(randomChosenCountry.split("-")[1].toUpperCase());
					setRemainingUnits((remArmies - 1));

					setChanged();
					notifyObservers(this);

					try {
						System.in.read();

					} catch (IOException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}

			} // end for (int pl = 1; pl <= numberOf...

		} // end while(!armiesRemainingPerPlyr.isEmpty())

		// loop for round robin for placing armies end

		// for (int pl = 1; pl <= numberOfPlayers; pl++) {
		//
		// List<String> playerCountryList = new ArrayList<String>();
		//
		// maxArmies = StartUpPhaseModel.initialArmies;
		//
		//
		// // populate playerCountryList
		// for (String playerInfo : StartUpPhaseModel.playerInfo.keySet()) {
		//
		// String[] playerInfoArr = playerInfo.split("-");
		// if (playerInfoArr[0].equals(Integer.toString(pl)) || playerInfoArr[0] ==
		// Integer.toString(pl)) {
		// playerCountryList.add(playerInfo);
		// }
		// }
		//
		//
		// //assign 1 army to each country first
		// for(String playerKey : StartUpPhaseModel.playerInfo.keySet()){
		//
		// String[] playerKeySplit = playerKey.split("-");
		//
		// if(playerKeySplit[0].equals(Integer.toString(pl)) || playerKeySplit[0] ==
		// Integer.toString(pl)){
		//
		// StartUpPhaseModel.playerInfo.put(playerKey, 1);
		//
		// maxArmies--;
		// }
		//
		//
		// }
		//
		//
		// //distribute remaining armies to territories until remaining armies count is
		// 0 for a player
		// while (maxArmies != 0) {
		//
		// // choose territory randomly to put armies into
		// String randomChosenCountry =
		// playerCountryList.get(randomCountry.nextInt(playerCountryList.size()));
		//
		// int playerInfoValue = StartUpPhaseModel.playerInfo.get(randomChosenCountry);
		// playerInfoValue = playerInfoValue + 1;
		//
		// StartUpPhaseModel.playerInfo.put(randomChosenCountry, playerInfoValue);
		//
		// maxArmies--;
		// } // end while(armiesCount != 0)
		//
		// } // end for(int pl = 1;pl<=numberOfPlayers;pl++)

	}// end deployArmiesRandoml(int numberOfPlayers)
	
	/**
	 * This method sets the initial number of armies as per number of players playing in a game.
	 * @param numberOfPlayers number of players
	 */
	public static void setInitialArmies(int numberOfPlayers) {

		if (numberOfPlayers == 2) {
			StartUpPhaseModel.initialArmies = 40;
		} else if (numberOfPlayers == 3) {
			StartUpPhaseModel.initialArmies = 35;

		} else if (numberOfPlayers == 4) {
			StartUpPhaseModel.initialArmies = 30;

		} else if (numberOfPlayers == 5) {
			StartUpPhaseModel.initialArmies = 25;

		} else {
			StartUpPhaseModel.initialArmies = 20;

		}

	}

	public static void main(String[] args) {

		HashMap<String, List<String>> territoryMap = new HashMap<String, List<String>>();
		territoryMap.put("Asia,India", null);
		territoryMap.put("Asia,China", null);
		territoryMap.put("Asia,Pakistan", null);
		territoryMap.put("Asia,Japan", null);
		territoryMap.put("Asia,Afghanistan", null);

		territoryMap.put("Europe,England", null);
		territoryMap.put("Europe,France", null);
		territoryMap.put("Europe,Spain", null);
		territoryMap.put("Europe,Germany", null);
		territoryMap.put("Europe,Italy", null);

		territoryMap.put("North America,Mexico", null);
		territoryMap.put("North America,USA", null);

		territoryMap.put("South America,Brazil", null);
		territoryMap.put("South America,Colombia", null);

		int numberOfPlayers = 6;
		// startUpPhase(numberOfPlayers,territoryMap);
	}

}
