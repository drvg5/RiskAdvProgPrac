package com.risk.model;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.InputMismatchException;
import java.util.List;
import java.util.Scanner;

public class GameDriverModel {

	public static int playerGTTerr = 0;
	
	public static void main(String[] args) throws InterruptedException{
		
		HashMap<String,List<String>> territoryMap = new HashMap<String,List<String>>();
		
		List<String> adjList = new ArrayList<String>();
		adjList.add("China");
		adjList.add("Pakistan");
		adjList.add("Sri Lanka");
		territoryMap.put("Asia,India",adjList);
		
		adjList = new ArrayList<String>();
		adjList.add("Afghanistan");
		adjList.add("Iran");
		territoryMap.put("Asia,Pakistan",adjList);
		
		adjList = new ArrayList<String>();
		adjList.add("India");
		adjList.add("Japan");
		adjList.add("Russia");
		territoryMap.put("Asia,China",adjList);
		
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
		
		int numberOfPlayers = 0;
		
		while(playerGTTerr != 1)	{
			
				try{
					numberOfPlayers = enterPlayersMenu();
					
				//	StartUpPhaseModel.startUpPhase(numberOfPlayers,territoryMap);
					
					if(playerGTTerr == 0){
						continue;
					}
					else
						playerGTTerr = 1;
					
			
				}
				catch(InputMismatchException e){
					System.out.println("----------------------------------------");
					System.out.println("Please enter only numbers");
					continue;
					
				}
			
		}
		
			
			for(int plyr = 1 ; plyr <= numberOfPlayers; plyr++){
				
				System.out.println("\n---------------------------------------------------");
				System.out.println("Player " + plyr + " :");
				playerInfoDisplay(Integer.toString(plyr),territoryMap);
				
			}
			
			System.out.printf("\n--------------------------------------------------------");
			System.out.printf("--------------------------------------------------------");
			System.out.println("\n\t\t\t\tSTARTUP PHASE COMPLETED");
			System.out.printf("--------------------------------------------------------");
					
			Thread.sleep(5000);
			
			//Round Robin
			for(int plyr = 1 ; plyr <= numberOfPlayers; plyr++){
				
				System.out.printf("\n--------------------------------------------------------");
				System.out.printf("--------------------------------------------------------\n");
				System.out.println("PLAYER " + plyr + " : REINFORCEMENT");
				
				
//				ReinforcementPhaseModel.calculateReinforcement(Integer.toString(plyr));
				ReinforcementPhaseModel.reinforceRandom(Integer.toString(plyr));
				playerInfoDisplay(Integer.toString(plyr),territoryMap);
				
				Thread.sleep(5000);
				
				System.out.printf("\n--------------------------------------------------------");
				System.out.printf("--------------------------------------------------------\n");
				System.out.println("PLAYER " + plyr + " : FORTIFICATION");
				
				FortificationPhaseModel.createFortifySet(Integer.toString(plyr), territoryMap);
				FortificationPhaseModel.randomFortification(Integer.toString(plyr));
				
				if(FortificationPhaseModel.fortifySetEmpty == 1){
					System.out.printf("--------------------------------------------------------\n");
					System.out.println("NO TWO ADJACENT TERRITORIES ARE OWNED BY PLAYER " + plyr);
				}
				
				else
				playerInfoDisplay(Integer.toString(plyr),territoryMap);
				
				Thread.sleep(5000);
			}
			
		
	}
	
	/**
	 * 
	 * @return numberOfPlayers
	 */
	public static int enterPlayersMenu(){
		
		System.out.println("---------------------------------------------");
		System.out.println("Please enter number of players : ");
		int numberOfPlayers = 0;
		String inPlayers;
		Scanner input = new Scanner(System.in);
		numberOfPlayers = input.nextInt();
		return numberOfPlayers;
	}
	
	
	public static void playerInfoDisplay(String player, HashMap<String,List<String>> territoryMap){
		
		
		for(String playerInfoKey : StartUpPhaseModel.playerInfo.keySet()){
			
			String[] playerInfoVal = playerInfoKey.split("-");
			
			if(player.equals(playerInfoVal[0]) || player == playerInfoVal[0]){
				
				System.out.println("\n\t--------------");
				System.out.println("\t" + "Territory : " + playerInfoVal[1]);
				
				System.out.println("\t\t" + "Continent : " + playerInfoVal[2]);
				
				System.out.println("\t\t" + "Armies : " + StartUpPhaseModel.playerInfo.get(playerInfoKey));
				
				System.out.printf("\t\t"+"Adjacent Countries : ");
				
				List<String> adjacentList = territoryMap.get(playerInfoVal[2] + "," + playerInfoVal[1]);
				
				for(String adjacent : adjacentList){
					System.out.printf(adjacent+"\t");
				}
				
			}
			
		}
		
	}
	
}
