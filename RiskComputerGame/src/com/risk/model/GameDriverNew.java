package com.risk.model;

import java.util.*;
import com.risk.ui.*;

public class GameDriverNew extends Object{
	
	public static int playerGTTerr = 0;
	
	PlayerClass playerModel;
	PhaseUI phaseView;
	
	GameDriverNew(){
		
		playerModel = new PlayerClass();
		phaseView = new PhaseUI();
		
		playerModel.addObserver(phaseView);
	}
	
	public static void main(String[] args){
		
		GameDriverNew gameDriver = new GameDriverNew();
		
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
		
		
		
		HashMap<String, Integer> continentControlValueHashMap = new HashMap<String, Integer>();
		continentControlValueHashMap.put("North America",5);
		continentControlValueHashMap.put("Europe",4);
		continentControlValueHashMap.put("Asia",7);
		
		
		
		gameDriver.gameStart(territoryMap, continentControlValueHashMap);
		
	}
	
	public void gameStart(HashMap<String,List<String>> territoryMap, HashMap<String, Integer> continentControlValueHashMap){
		
		
		
		int numberOfPlayers;
		
		while(playerGTTerr != 1)	{
			
			try{
				numberOfPlayers = enterPlayersMenu();
				
				StartUpPhaseModel.preStartUp(numberOfPlayers, territoryMap);
				
				try {
					
					//new PlayerClass().gamePlay(numberOfPlayers, territoryMap, continentControlValueHashMap);
					playerModel.gamePlay(numberOfPlayers, territoryMap, continentControlValueHashMap);
					
				} catch (InterruptedException e) {
					
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				
				if(playerGTTerr == 0){
					continue;
				}
				else
					playerGTTerr = 1;
				
		
			}
			catch(InputMismatchException e){
				System.out.println("----------------------------------------");
				System.out.println("Please enter only numbers : ");
				continue;
				
			}
		
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
		
		Scanner input = new Scanner(System.in);
		numberOfPlayers = input.nextInt();
		return numberOfPlayers;
	}
}
