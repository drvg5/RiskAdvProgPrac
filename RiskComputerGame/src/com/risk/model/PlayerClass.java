package com.risk.model;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.InputMismatchException;
import java.util.List;
import java.util.Observable;
import java.util.TreeSet;

/**
 * This is an Observable Class which has implementations for state change in the game play 
 * 
 * 
 * @author Navjot  
 * @author Ashish
 */

public class PlayerClass extends Observable {
	


	public static String msg;

	public void gamePlay(int numberOfPlayers, HashMap<String, List<String>> territoryMap,
			HashMap<String, Integer> continentControlValueHashMap) throws InterruptedException {

		// startUpPhase method called
		PlayerClass.startUpPhase(numberOfPlayers);

		msg = "startup completed";
		setChanged();

		notifyObservers(msg);

		Thread.sleep(5000);

		int plyr = 1;

		int currentNumberOfPlayers = numberOfPlayers;
		
		PlayerClass playerClassObj = new PlayerClass();
		
		// round robin for game starts
		while (true) {

			// calculate number of players after each round robin to update in case a player
			// is ousted
			currentNumberOfPlayers = PlayerClass.plyrsRemaining();

			// reset plyr to player 1 once all players have got their turn to play
			if (plyr > currentNumberOfPlayers) {
				plyr = 1;
			}

			
			

			// reinforcement phase method called
			playerClassObj.reinforcementPhase(plyr,continentControlValueHashMap);


			msg = "reinforce done";
			setChanged();
			notifyObservers(msg);

			Thread.sleep(5000);

			// attack phase method called
			PlayerClass.attackPhase(plyr, territoryMap);
			msg = "attack done";
			setChanged();
			notifyObservers(msg);

			Thread.sleep(5000);

			// if one player has all the territories i.e if player has won the game then
			// break out of loop
			boolean victory = PlayerClass.checkPlyrVictory(plyr);
			if (victory) {
				break;
			}

			Thread.sleep(5000);

			// fortification method called
			PlayerClass.fortificationPhase(plyr, territoryMap);
			msg = "fortification done";
			setChanged();
			notifyObservers(msg);

			Thread.sleep(5000);

			plyr++;

		} // end while(true)

	}

	public static void startUpPhase(int numberOfPlayers) {

		StartUpPhaseModel.terrPerPlayerPopulate(numberOfPlayers, StartUpPhaseModel.totalTerr);

		StartUpPhaseModel.assignTerritories(numberOfPlayers, StartUpPhaseModel.countryTaken,
				StartUpPhaseModel.totalTerr);

		StartUpPhaseModel.deployArmiesRandomly(numberOfPlayers);

	}



	public  void reinforcementPhase(int plyr, HashMap<String, Integer> continentControlValueHashMap){
		
		//call method to calculate reinforcements by number of territories
		String reinTerrMsg = ReinforcementPhaseModel.calcReinforcementsByTerr(Integer.toString(plyr));
		
		PlayerClass.msg = "reinforcements by number of territories," + reinTerrMsg + ",";
		setChanged();
		notifyObservers(this);
		
		
		//call method to calculate reinforcements by cards
		String cardExgMsg = ReinforcementPhaseModel.calcReinforcementByCards(Integer.toString(plyr));
		PlayerClass.msg = "reinforcements by exchanging cards|" + reinTerrMsg + "|";
		setChanged();
		notifyObservers(this);
		
		
		//call method to calculate reinforcements if a player owns the whole continent
		ArrayList<String> cntrlValMsg = ReinforcementPhaseModel.calcReinforcementByCntrlVal(Integer.toString(plyr), continentControlValueHashMap);
		String[] cntrlVal = cntrlValMsg.toArray(new String[cntrlValMsg.size()]);
		
		PlayerClass.msg = "reinforcements by control value-";
		
		for(String cntrValIndividual : cntrlVal){
			PlayerClass.msg = PlayerClass.msg + cntrValIndividual + "-";
		}
		setChanged();
		notifyObservers(this);
		
		
		
		//ReinforcementPhaseModel.calculateReinforcement(Integer.toString(plyr));
		
		ReinforcementPhaseModel.reinforceRandom(Integer.toString(plyr));
	}

	
	/*
	 * method for initiating the attack phase
	 */
	public static void attackPhase(int plyr, HashMap<String, List<String>> territoryMap) {
		boolean choice = true;
		boolean attackPossible = false;
		while (choice) {
			// Player chooses country to attack.
			AttackPhaseModel.chooseCountryToBeAttacked(plyr, territoryMap);
			// country is attacked and armies are deducted
			// based on the dice roll obtained
			try {
				AttackPhaseModel.rollDice();
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			// choose to keep attacking or not
			choice = AttackPhaseModel.result();
		}

	}

	
	public static void fortificationPhase(int plyr,HashMap<String, List<String>> territoryMap){
		
		
		FortificationPhaseModel.createFortifySet(Integer.toString(plyr), territoryMap);
		
		FortificationPhaseModel.randomFortification(Integer.toString(plyr));
		
	}
	

	
	
	
	public static boolean checkPlyrVictory(int plyr){
		
		TreeSet<Integer> playerCheck = new TreeSet<Integer>();
		
		for(String playerInfoKey : StartUpPhaseModel.playerInfo.keySet()){
			String[] playerInfoArr = playerInfoKey.split("-");
			
			playerCheck.add(Integer.valueOf(playerInfoArr[0]));
		}//end for
		
		if(playerCheck.size() == 1){
			return true;
		}//end if
		
		return false;
	}//end method checkPlyrVictory
	
	
	/**
	 * This method counts the number of players present at any instant in the game
	 * @return Current Number of Players
	 */
	public static int plyrsRemaining(){
		
		int currentNumberOfPlyrs = 0;
		
		TreeSet<Integer> playerCheck = new TreeSet<Integer>();
		
		for(String playerInfoKey : StartUpPhaseModel.playerInfo.keySet()){
			String[] playerInfoArr = playerInfoKey.split("-");
			
			playerCheck.add(Integer.valueOf(playerInfoArr[0]));
		}//end for
		
		currentNumberOfPlyrs = playerCheck.size();
		
		return currentNumberOfPlyrs;
		
	}//end method plyrsRemaining()
	
	
}
