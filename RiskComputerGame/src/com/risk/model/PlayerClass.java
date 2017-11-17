package com.risk.model;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.InputMismatchException;
import java.util.List;
import java.util.Observable;
import java.util.TreeSet;

import com.risk.ui.CardExchangeUI;
import com.risk.ui.FortificationUI;
import com.risk.ui.PlayerDominationView;
import com.risk.ui.ReinforcementsUI;

/**
 * This is an Observable Class which has implementations for state change in the
 * game play
 * 
 * 
 * @author Navjot
 * @author Ashish
 */

public class PlayerClass extends Observable {

	
	public static int players = 0;
	
	public static HashMap<String, List<String>> currentMap;

	public static String msg;

	
	public void gamePlay(int numberOfPlayers, HashMap<String, List<String>> territoryMap,
			HashMap<String, Integer> continentControlValueHashMap) throws InterruptedException {

		currentMap = territoryMap;
		
		PlayerClass.players = numberOfPlayers;
		// startUpPhase method called
		PlayerClass.startUpPhase(numberOfPlayers);

		msg = "startup";
		
		setChanged();
		notifyObservers(this);
		

		int plyr = 1;

		int currentNumberOfPlayers = numberOfPlayers;
		
		PlayerDominationModel playerDominationObj = new PlayerDominationModel();
		
		PlayerDominationView playerDominationView = new PlayerDominationView();
		
		playerDominationObj.addObserver(playerDominationView);
		
		PlayerClass playerClassObj = new PlayerClass();
		
		msg = "roundrobin";
		
		setChanged();

		notifyObservers(this);
		
		
		try {
			System.in.read();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		try {
			System.in.read();
			
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		playerDominationObj.calcDominationValues(StartUpPhaseModel.playerInfo,numberOfPlayers, StartUpPhaseModel.totalTerr);
		
		// round robin for game starts
		
		try {
			System.in.read();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		while (true) {

			// calculate number of players after each round robin to update in case a player
			// is ousted
			currentNumberOfPlayers = PlayerClass.plyrsRemaining();

			// reset plyr to player 1 once all players have got their turn to play
			if (plyr > currentNumberOfPlayers) {
				plyr = 1;
			}
			
			msg = "reinforceHead," + plyr;
			
			setChanged();
			notifyObservers(this);
			
			try {
				System.in.read();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
			
			// reinforcement phase method called
			playerClassObj.reinforcementPhase(plyr, continentControlValueHashMap,currentMap);
			
			

			msg = "reinforce done," + plyr;
			setChanged();
			notifyObservers(msg);
			
			try {
				System.in.read();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
			
			
			try {
				System.in.read();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			

			// attack phase method called
			PlayerClass.attackPhase(plyr, territoryMap);
			msg = "attack done";
			setChanged();
			notifyObservers(msg);

			try {
				System.in.read();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

			playerDominationObj.calcDominationValues(StartUpPhaseModel.playerInfo,numberOfPlayers, StartUpPhaseModel.totalTerr);
			
			try {
				System.in.read();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
			// if one player has all the territories i.e if player has won the game then
			// break out of loop
			boolean victory = PlayerClass.checkPlyrVictory(plyr);
			if (victory) {
				
				msg = plyr + " wins";
				setChanged();
				notifyObservers(this);
				try {
					System.in.read();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				break;
			}

			
			msg = "pre fortification," + plyr;
			setChanged();
			notifyObservers(this);
			
			try {
				System.in.read();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

			// fortification method called
			playerClassObj.fortificationPhase(plyr, territoryMap);
			
			msg = "fortification done," + plyr;
			setChanged();
			notifyObservers(this);

			try {
				System.in.read();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

			plyr++;

		} // end while(true)

	}

	public static void startUpPhase(int numberOfPlayers) {

		StartUpPhaseModel.terrPerPlayerPopulate(numberOfPlayers, StartUpPhaseModel.totalTerr);

		StartUpPhaseModel.assignTerritories(numberOfPlayers, StartUpPhaseModel.countryTaken,
				StartUpPhaseModel.totalTerr);

		StartUpPhaseModel.deployArmiesRandomly(numberOfPlayers);

	}

	public void reinforcementPhase(int plyr, HashMap<String, Integer> continentControlValueHashMap, HashMap<String, List<String>> currentMap) {
		
		ReinforcementPhaseModel reinforcmentModelObj = new ReinforcementPhaseModel();
		ReinforcementsUI uiObj = new ReinforcementsUI();
		CardExchangeUI uiCardObj = new CardExchangeUI();
		
		reinforcmentModelObj.addObserver(uiObj);
		reinforcmentModelObj.addObserver(uiCardObj);
		// call method to calculate reinforcements by number of territories
		String reinTerrMsg = reinforcmentModelObj.calcReinforcementsByTerr(Integer.toString(plyr));
		
		try {
			System.in.read();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
		
		PlayerClass.msg = "reinforcements by number of territories," + reinTerrMsg + ",";
		setChanged();
		notifyObservers(this);

		
		
		
		//call method to calculate reinforcements by cards
		reinforcmentModelObj.calcReinforcementByCards(Integer.toString(plyr));
//		PlayerClass.msg = "reinforcements by exchanging cards|" + reinTerrMsg + "|";
		
		
		setChanged();
		
		notifyObservers(this);
		
		
		try {
			System.in.read();
			
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		//call method to calculate reinforcements if a player owns the whole continent
		ArrayList<String> cntrlValMsg = reinforcmentModelObj.calcReinforcementByCntrlVal(Integer.toString(plyr), continentControlValueHashMap);

		
		try {
			System.in.read();
			
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
		String[] cntrlVal = cntrlValMsg.toArray(new String[cntrlValMsg.size()]);

		PlayerClass.msg = "reinforcements by control value-";

		for (String cntrValIndividual : cntrlVal) {
			PlayerClass.msg = PlayerClass.msg + cntrValIndividual + "-";
		}
		
		setChanged();
		notifyObservers(this);

		// ReinforcementPhaseModel.calculateReinforcement(Integer.toString(plyr));

		reinforcmentModelObj.reinforceRandom(Integer.toString(plyr));
		
		try {
			System.in.read();
			
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	/*
	 * method for initiating the attack phase
	 */
	public static void attackPhase(int plyr, HashMap<String, List<String>> territoryMap) {
//		boolean choice = true;
//		boolean attackPossible = false;
		// Player chooses country to attack.
		AttackPhaseModel.chooseCountryToBeAttacked(plyr, territoryMap);

	}

	public void fortificationPhase(int plyr, HashMap<String, List<String>> territoryMap) {
		
		try {
			System.in.read();
			
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		FortificationPhaseModel fortificationObj = new FortificationPhaseModel();
		
		FortificationUI uiObj = new FortificationUI();
		
		fortificationObj.addObserver(uiObj);
		
		
		FortificationPhaseModel.createFortifySet(Integer.toString(plyr), territoryMap);

		fortificationObj.randomFortification(Integer.toString(plyr));
		
	

	}

	public static boolean checkPlyrVictory(int plyr) {

		TreeSet<Integer> playerCheck = new TreeSet<Integer>();

		for (String playerInfoKey : StartUpPhaseModel.playerInfo.keySet()) {
			String[] playerInfoArr = playerInfoKey.split("-");

			playerCheck.add(Integer.valueOf(playerInfoArr[0]));
		} // end for

		if (playerCheck.size() == 1) {
			return true;
		} // end if

		return false;
	}// end method checkPlyrVictory

	/**
	 * This method counts the number of players present at any instant in the game
	 * 
	 * @return Current Number of Players
	 */
	public static int plyrsRemaining() {

		int currentNumberOfPlyrs = 0;

		TreeSet<Integer> playerCheck = new TreeSet<Integer>();

		for (String playerInfoKey : StartUpPhaseModel.playerInfo.keySet()) {
			String[] playerInfoArr = playerInfoKey.split("-");

			playerCheck.add(Integer.valueOf(playerInfoArr[0]));
		} // end for

		currentNumberOfPlyrs = playerCheck.size();

		return currentNumberOfPlyrs;

	}// end method plyrsRemaining()

}
