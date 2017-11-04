package com.risk.model;

import java.util.HashMap;
import java.util.InputMismatchException;
import java.util.List;
import java.util.Observable;

public class PlayerClass extends Observable {
	
	
	
	public static String msg ; 
	
	
	public void gamePlay(int numberOfPlayers, HashMap<String,List<String>> territoryMap) throws InterruptedException{
		
		//startUpPhase method called
		PlayerClass.startUpPhase(numberOfPlayers);
		
		msg = "startup completed";
		setChanged();
		
		notifyObservers(msg);
		
		Thread.sleep(5000);
		
		
		
		
		int plyr = 1;
		
		int currentNumberOfPlayers = numberOfPlayers;
		
		//round robin for game starts
		while(true){
			
			//calculate number of players after each round robin to update in case a player is ousted
			currentNumberOfPlayers = PlayerClass.plyrsRemaining(StartUpPhaseModel.playerInfo);
			
			
			//reset plyr to player 1 once all players have got their turn to play
			if(plyr > currentNumberOfPlayers ){
				plyr = 1;
			}
			
			
			//reinforcement phase method called
			PlayerClass.reinforcementPhase(plyr);
			msg = "reinforce done";
			setChanged();
			notifyObservers(msg);
			
			
			
			Thread.sleep(5000);
			
			
			//attack phase method called
			PlayerClass.attackPhase(plyr);
			msg = "attack done";
			setChanged();
			notifyObservers(msg);
			
			
			Thread.sleep(5000);
			
			
			//if one player has all the territories i.e if player has won the game then break out of loop
			boolean victory = PlayerClass.checkPlyrVictory(plyr);
			if(victory){
				break;
			}
			
			
			Thread.sleep(5000);
			
			
			//fortification method called
			PlayerClass.fortificationPhase(plyr, territoryMap);
			msg = "fortification done";
			setChanged();
			notifyObservers(msg);
			
			Thread.sleep(5000);
			
			
			plyr++;
			
		}//end while(true)
		
		
	}
	
	
	
	public static void startUpPhase(int numberOfPlayers){
		
		StartUpPhaseModel.terrPerPlayerPopulate(numberOfPlayers,StartUpPhaseModel.totalTerr);
		
		
		StartUpPhaseModel.assignTerritories(numberOfPlayers, StartUpPhaseModel.countryTaken, StartUpPhaseModel.totalTerr);
		
		
		StartUpPhaseModel.deployArmiesRandomly(numberOfPlayers);
		
	}
	
	
	
	public static void reinforcementPhase(int plyr){
		
		//call method to calculate reinforcements by number of territories
		ReinforcementPhaseModel.calcReinforcementsByTerr(Integer.toString(plyr));
		
		//call method to calculate cards
		
		//call method to calculate reinforcements if a player owns the whole continent
		
		
		ReinforcementPhaseModel.calculateReinforcement(Integer.toString(plyr));
		
		ReinforcementPhaseModel.reinforceRandom(Integer.toString(plyr));
	}
	
	
	
	public static void attackPhase(int plyr){
		
	}
	
	
	
	
	public static void fortificationPhase(int plyr,HashMap<String, List<String>> territoryMap){
		
		
		FortificationPhaseModel.createFortifySet(Integer.toString(plyr), territoryMap);
		
		FortificationPhaseModel.randomFortification(Integer.toString(plyr));
	}
	
	
	
	
	public static boolean checkPlyrVictory(int plyr){
		
		return true;
	}
	
	
	
	public static int plyrsRemaining(HashMap<String,Integer> playerInfo){
		
		int currentNumberOfPlyrs = 0;
		
		return currentNumberOfPlyrs;
		
	}
	
	
}
