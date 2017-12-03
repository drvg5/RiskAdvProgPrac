package com.risk.model;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.InputMismatchException;
import java.util.List;
import java.util.Random;
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
	
	/** The victoryStatistics keep the statistics for different games. */
	public static HashMap<String, String> victoryStatistics = new HashMap<String, String>();

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
		gameStart(territoryMap, continentControlValueHashMap, strategies, false, false,0,0 );

	}

	/**
	 * Method gameModeTournament launches the tournament mode for the RISK game.
	 *
	 * @param territoryMaps the territory maps
	 * @param continentControlValueHashMaps the continent control value hash maps
	 * @param numberOfMaps the number of maps
	 * @param numberOfGames the number of games
	 * @param behaviorList the behavior list
	 * @param rounds the rounds
	 */
	public void gameModeTournament(HashMap<String ,HashMap<String, List<String>>> territoryMaps,
			HashMap<String,HashMap<String, Integer>> continentControlValueHashMaps, int numberOfMaps,
			int numberOfGames, List<String> behaviorList, int rounds) {
		
		
		
		//HashMap<String, List<String>> [] territoryMaps ;
		
		//maps
		//control value hashmaps
		//player behaviours
		//number of players = strategies
		//number of games
		//maximum number of turns
		
		int map = 1;
		int gameNumber = 1;
		
		int numberOfPlayers = behaviorList.size();
		
		List<String> behaviorListLocal = new ArrayList<String>();
		
		System.out.println("territoryMaps.keySet() :: " + territoryMaps.keySet()); 
		
		HashMap<String ,HashMap<String, List<String>>> gameMapsHashMap = territoryMaps;
		
		//HashMap<String, List<String>> territoryMap = territoryMaps.get(Integer.toString(gameNumber));
		
		//HashMap<String, Integer> continentControlValueHashMap = continentControlValueHashMaps.get(Integer.toString(gameNumber));
		System.out.println("numberOfMaps :: " + numberOfMaps);
		System.out.println("numberOfGames :: " + numberOfGames);
		
		System.out.println("---------------------------------------------------------------------------");
		System.out.println("**************************** TOURNAMENT STARTS ****************************");
		System.out.println("---------------------------------------------------------------------------");
		
		
		//outer loop for switching maps
		while(map <= numberOfMaps){
		
			try {
				System.in.read();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
			HashMap<String, List<String>> territoryMap = territoryMaps.get("M" + map);
			
			HashMap<String, Integer> continentControlValueHashMap = continentControlValueHashMaps.get("M" + map);
			
			gameNumber = 1;
			
			System.out.println("\n\n\n---------------------------------------------------------------------------");
			System.out.println("**************************** NEW MAP M" + map + " LOADED ********************************");
			System.out.println("---------------------------------------------------------------------------");
			
			while(gameNumber <= numberOfGames){
				
				try {
					System.in.read();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				
				System.out.println("Game number : " + gameNumber);
				System.out.println("Total Games : " + numberOfGames);
				System.out.println("Number of Rounds per game : " + rounds);
				System.out.println("\nTerritory map M" + map + " : \n" + territoryMap);
				System.out.println("\nControl Value of Continent on map M" + map + " : \n" + continentControlValueHashMap );
				System.out.println("\n\n\n---------------------------------------------------------------------------");
				System.out.println("****************************** GAME " + gameNumber + " BEGINS ******************************");
				System.out.println("---------------------------------------------------------------------------");
				
				
				HashMap<Integer,String> strategies = new HashMap<Integer,String>();
				
				int player = 1;
				
				for(String behaviour : behaviorList){
					
					behaviorListLocal.add(behaviour);
				}
				
				System.out.println("List of Behaviours Selected -> " + behaviorList);
				
				//populate strategies HashMap
				while(!behaviorListLocal.isEmpty()){
					
					Random random = new Random();
					
					int randomIndex = random.nextInt(behaviorListLocal.size());
					
					String behavior = behaviorListLocal.get(randomIndex);
					
					strategies.put(player,behavior.toLowerCase());
					
					behaviorListLocal.remove(randomIndex);
					
					player++;
				}
				
				System.out.println("Strategy of each player -> " + strategies);
				
				playerGTTerr = 0;

				try {
					System.in.read();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				
				GameDriverNew gameObj = new GameDriverNew();
				//start the game
				gameObj.gameStart(territoryMap, continentControlValueHashMap, strategies, false, true, rounds, 0);
				
				System.out.println("\n\n\n---------------------------------------------------------------------------");
				System.out.println("**************************** GAME " + gameNumber + " ENDS **********************************");
				System.out.println("---------------------------------------------------------------------------");
			
				try {
					System.in.read();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				
				
				String victoryKey = "Map " + map + " - Game " + gameNumber + " ";
				String value = new String();
				if(PlayerClass.victor != null){
					
					value = PlayerClass.victor;
					
				}
				else
					value = " DRAW";
				
				victoryStatistics.put(victoryKey,value);
				
				
				gameNumber ++;
				
				
				if(gameNumber > numberOfGames)
					break;
				
			}
			
			try {
				System.in.read();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
			
			System.out.println("\n\n\n---------------------------------------------------------------------------");
			System.out.println("************************** GAMES ON MAP M " + map + " OVER ********************************");
			System.out.println("---------------------------------------------------------------------------");
			
			map++;
			
			
			if(map > numberOfMaps)
				break;
			
			
		
		}//end while(map <= numberOfMaps)
		
		try {
			System.in.read();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		System.out.println("---------------------------------------------------------------------------");
		System.out.println("***************************** TOURNAMENT ENDS *****************************");
		System.out.println("---------------------------------------------------------------------------");
	
		
		try {
			System.in.read();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
		System.out.println("*************************** VICTORY STATISTICS ****************************");
		System.out.println(victoryStatistics);
		System.exit(0);
		
	}

	/**
	 * This method launches the single mode game and also the previously saved game.
	 *
	 * @param territoryMap the territory map
	 * @param continentControlValueHashMap the continent control value hash map
	 * @param strategies the strategies
	 * @param load true, if a saved game is being loaded. false, if a new game is being loaded
	 * @param tournament the tournament
	 * @param rounds the rounds
	 * @param currentPlayerIndex the current player index
	 */
	public void gameStart(HashMap<String, List<String>> territoryMap,
			HashMap<String, Integer> continentControlValueHashMap, HashMap<Integer, String> strategies, boolean load,boolean tournament, int rounds, int currentPlayerIndex) {

		
		int numberOfPlayers = strategies.size() ;
		
		
		while (playerGTTerr != 1) {
			
			try {
				// numberOfPlayers = enterPlayersMenu();
				
				
				if(!load) 
					
					StartUpPhaseModel.preStartUp(numberOfPlayers, territoryMap);

				try {

					// new PlayerClass().gamePlay(numberOfPlayers, territoryMap,
					// continentControlValueHashMap);
					
					
					
					playerModel.gamePlay(numberOfPlayers, territoryMap, continentControlValueHashMap, strategies,load,tournament,rounds, currentPlayerIndex);

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
