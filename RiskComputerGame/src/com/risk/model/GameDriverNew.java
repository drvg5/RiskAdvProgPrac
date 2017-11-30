package com.risk.model;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.InputMismatchException;
import java.util.List;
import java.util.Scanner;

import com.risk.ui.PhaseUI;
import com.risk.ui.PlayerDominationView;
import com.risk.utility.RiskConstants;


/**
 * The Class GameDriverNew launches the single game mode, tournament game mode and the load game method.
 */
public class GameDriverNew extends Object {

	/** The playerGTTerr is flag which goes to 1 when the number of players entered by the user is greater than the number of territories on the map*/
	public static int playerGTTerr = 0;

	/** The PlayerClass object. */
	PlayerClass playerModel;
	
	/** The PhaseUI object. */
	PhaseUI phaseView;
	
	/** The PlayerDominationView object. */
	PlayerDominationView dominationView;
	
	/** The strategies HashMap stores the behaviour of each player. */
	public static HashMap<Integer, String> strategies = new HashMap<Integer, String>();

	/**
	 * Instantiates a new game driver new.
	 */
	public GameDriverNew() {

		playerModel = new PlayerClass();
		phaseView = new PhaseUI();
		dominationView = new PlayerDominationView();

		playerModel.addObserver(dominationView);
		playerModel.addObserver(phaseView);
	}

	/**
	 * The main method.
	 *
	 * @param args the arguments
	 */
	public static void main(String[] args) {

		GameDriverNew gameDriver = new GameDriverNew();

		HashMap<String, List<String>> territoryMap = new HashMap<String, List<String>>();

		List<String> adjList = new ArrayList<String>();
		adjList.add("China");
		adjList.add("Pakistan");
		adjList.add("Sri Lanka");
		territoryMap.put("Asia,India", adjList);

		adjList = new ArrayList<String>();
		adjList.add("Afghanistan");
		adjList.add("Iran");
		adjList.add("India");
		territoryMap.put("Asia,Pakistan", adjList);

		adjList = new ArrayList<String>();
		adjList.add("India");
		adjList.add("Japan");
		adjList.add("Russia");
		territoryMap.put("Asia,China", adjList);

		adjList = new ArrayList<String>();
		adjList.add("China");
		adjList.add("Russia");
		territoryMap.put("Asia,Japan", adjList);

		adjList = new ArrayList<String>();
		adjList.add("Pakistan");
		adjList.add("Iran");
		territoryMap.put("Asia,Afghanistan", adjList);

		adjList = new ArrayList<String>();
		adjList.add("China");
		adjList.add("Japan");
		adjList.add("Canada");
		territoryMap.put("Asia,Russia", adjList);

		adjList = new ArrayList<String>();
		adjList.add("Pakistan");
		adjList.add("Afghanistan");
		adjList.add("Greece");
		territoryMap.put("Asia,Iran", adjList);

		adjList = new ArrayList<String>();
		adjList.add("India");
		territoryMap.put("Asia,Sri Lanka", adjList);

		adjList = new ArrayList<String>();
		adjList.add("France");
		adjList.add("Ireland");
		territoryMap.put("Europe,England", adjList);

		adjList = new ArrayList<String>();
		adjList.add("England");
		adjList.add("Spain");
		adjList.add("Germany");
		territoryMap.put("Europe,France", adjList);

		adjList = new ArrayList<String>();
		adjList.add("France");
		adjList.add("Italy");
		territoryMap.put("Europe,Spain", adjList);

		adjList = new ArrayList<String>();
		adjList.add("France");
		adjList.add("Ukraine");
		territoryMap.put("Europe,Germany", adjList);

		adjList = new ArrayList<String>();
		adjList.add("Spain");
		adjList.add("Greece");
		adjList.add("Germany");
		territoryMap.put("Europe,Italy", adjList);

		adjList = new ArrayList<String>();
		adjList.add("England");
		adjList.add("Canada");
		territoryMap.put("Europe,Ireland", adjList);

		adjList = new ArrayList<String>();
		adjList.add("Russia");
		adjList.add("Germany");
		territoryMap.put("Europe,Ukraine", adjList);

		adjList = new ArrayList<String>();
		adjList.add("Italy");
		adjList.add("Iran");
		territoryMap.put("Europe,Greece", adjList);

		adjList = new ArrayList<String>();
		adjList.add("USA");
		territoryMap.put("North America,Mexico", adjList);

		adjList = new ArrayList<String>();
		adjList.add("Canada");
		territoryMap.put("North America,USA", adjList);

		adjList = new ArrayList<String>();
		adjList.add("Russia");
		adjList.add("Ireland");
		adjList.add("USA");
		territoryMap.put("North America,Canada", adjList);

		HashMap<String, Integer> continentControlValueHashMap = new HashMap<String, Integer>();
		continentControlValueHashMap.put("North America", 5);
		continentControlValueHashMap.put("Europe", 4);
		continentControlValueHashMap.put("Asia", 7);

		ArrayList<String> cardsList = new ArrayList<String>();

		cardsList.add(RiskConstants.CARD_1);
		cardsList.add(RiskConstants.CARD_2);
		cardsList.add(RiskConstants.CARD_3);

		ReinforcementPhaseModel.playerCards.put("1-2", cardsList);

		cardsList = new ArrayList<String>();
		cardsList.add(RiskConstants.CARD_1);
		cardsList.add(RiskConstants.CARD_1);
		cardsList.add(RiskConstants.CARD_3);
		cardsList.add(RiskConstants.CARD_2);
		cardsList.add(RiskConstants.CARD_1);

		// gameDriver.gameStart(territoryMap, continentControlValueHashMap);
		
		gameDriver.gameModeSingle(territoryMap,continentControlValueHashMap);

	}

	/**
	 * Game mode single.
	 *
	 * @param territoryMap the territory map
	 * @param continentControlValueHashMap the continent control value hash map
	 */
	public void gameModeSingle(HashMap<String, List<String>> territoryMap,
			HashMap<String, Integer> continentControlValueHashMap) {

		int numberOfPlayers;

		// input the no of players
		numberOfPlayers = enterPlayersMenu();
		// input the strategies for the player
		HashMap<Integer, String> strategies = new HashMap<Integer, String>();
		strategies = enterStrategiesMenu(numberOfPlayers);
		gameStart(territoryMap, continentControlValueHashMap, strategies, false);

	}

	/**
	 * Method gameModeTournament launches the tournament mode for the RISK game.
	 *
	 * @param territoryMap the territory map
	 * @param continentControlValueHashMap the continent control value hash map
	 */
	public void gameModeTournament(HashMap<String, List<String>> territoryMap,
			HashMap<String, Integer> continentControlValueHashMap) {

	}

	/**
	 * This method launches the single mode game and also the previously saved game.
	 *
	 * @param territoryMap the territory map
	 * @param continentControlValueHashMap the continent control value hash map
	 * @param strategies the strategies
	 * @param load true, if a saved game is being loaded. false, if a new game is being loaded
	 */
	public void gameStart(HashMap<String, List<String>> territoryMap,
			HashMap<String, Integer> continentControlValueHashMap, HashMap<Integer, String> strategies, boolean load) {

		
		int numberOfPlayers = strategies.size() ;

		while (playerGTTerr != 1) {

			try {
				// numberOfPlayers = enterPlayersMenu();
				
				if(!load) 
					StartUpPhaseModel.preStartUp(numberOfPlayers, territoryMap);

				try {

					// new PlayerClass().gamePlay(numberOfPlayers, territoryMap,
					// continentControlValueHashMap);
					playerModel.gamePlay(numberOfPlayers, territoryMap, continentControlValueHashMap, strategies,load);

				} catch (InterruptedException e) {

					// TODO Auto-generated catch block
					e.printStackTrace();
				}

				if (playerGTTerr == 0) {
					continue;
				} else
					playerGTTerr = 1;

			} catch (InputMismatchException e) {
				System.out.println("----------------------------------------");
				System.out.println("Please enter only numbers : ");
				continue;

			}

		}

	}

	/**
	 * Enter number of players menu.
	 *
	 * @return numberOfPlayers
	 */
	public static int enterPlayersMenu() {

		int numberOfPlayers = 0;
		
		while(true){
			System.out.println("---------------------------------------------");
			System.out.println("Please enter number of players : ");
	
			
			
			try{
				Scanner input = new Scanner(System.in);
				numberOfPlayers = input.nextInt();
			}
			catch(InputMismatchException e){
				continue;
			}
			break;
		
		}
		return numberOfPlayers;
	}

	/**
	 * Enter player strategies menu.
	 *
	 * @param numberOfPlayers the number of players
	 * @return the hash map
	 */
	public static HashMap<Integer, String> enterStrategiesMenu(int numberOfPlayers) {

		System.out.println("---------------------------------------------");
		System.out.println("Choose player Strategies : ");
		System.out.println("AGGRESSIVE : a ");
		System.out.println("BENEVOLENT : b");
		System.out.println("CHEATER : c");
		System.out.println("RANDOM : r");
		System.out.println("HUMAN : h");

		HashMap<Integer, String> strategies = new HashMap<Integer, String>();

		int count = numberOfPlayers;
		int plyrNo = 1;
		String strategySelected = "";
		while (count != 0) {
			System.out.println("---------------------------------------------");
			System.out.println("Please choose the strategy for player " + plyrNo + " : ");
			Scanner input = new Scanner(System.in);
			strategySelected = input.next();
			strategies.put(plyrNo, strategySelected);
			++plyrNo;
			--count;
		}

		return strategies;
	}
}
