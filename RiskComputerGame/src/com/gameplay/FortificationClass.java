package com.gameplay;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Random;
import java.util.TreeSet;

public class FortificationClass {

	
	public static TreeSet<String> fortifySet = new TreeSet<String>();
	
	public static void createFortifySet(String player, HashMap<String, List<String>> territoryMap){
		
	   fortifySet.clear();
		
       for(String playerInfoKey : PlayerClass.playerInfo.keySet()){
    	   
    	   String[] keyArray = playerInfoKey.split("-");

    	   if(keyArray[0].equals(player) || keyArray[0] == player){
    		   
    		   
	    	   if(PlayerClass.playerInfo.get(playerInfoKey) >= 1){
	    		   
	    		   
	    		   
	    		   String continent = keyArray[2];
	    		   String territory = keyArray[1];
	    		   String mapKey = continent + "," + territory;
	    		   
	    		  
	    		   List<String> adjacencyList = territoryMap.get(mapKey);
	    		   
	    		   //check if player own any adjacent territory to one which already owns
	    		   for(String adjacentTerr : adjacencyList){
	    			   
	    			   String key = player + "-" + adjacentTerr + "-" + PlayerClass.terrCont.get(adjacentTerr);
	    			   
	    			   if(PlayerClass.playerInfo.containsKey(key)){
	    				   
	    				   fortifySet.add(territory + "-" +adjacentTerr);
	    			   }
	    			   
	    		   }//end for(String adjacentTerr : adjacencyList)
	    		   
	    	   }//end if(PlayerClass.playerInfo.get(playerInfoKey) >= 1)
	    	   
    	   }//end if(keyArray[0].equals(player) || keyArray == player){
    	   
       }//end for(String playerInfoKey : PlayerClass.playerInfo.keySet())
    	
	}//end method createFortifySet
	
	
	public static void randomFortification(String player){
		
		Random random = new Random();
		
		List<String> fortifyList = new ArrayList<String>(fortifySet);
		
		String randomPath = fortifyList.get(random.nextInt(fortifyList.size()));
		
		System.out.println("Path chosen:"+randomPath);
		
		String[] randomTerr = randomPath.split("-");
		
		String fromTerr = randomTerr[0];
		
		String toTerr = randomTerr[1];
		
		String fromCont = PlayerClass.terrCont.get(fromTerr);
		
		String toCont = PlayerClass.terrCont.get(toTerr);
		
		String from = player + "-" + fromTerr + "-" + fromCont;
		
		String to = player + "-" + toTerr + "-" + toCont;
		
		int fromArmies = PlayerClass.playerInfo.get(from);
		
		int toArmies = PlayerClass.playerInfo.get(to);
		
		int value = 0;
		
		if(fromArmies > 1){
			Random randomArmies = new Random();
			
			value = randomArmies.nextInt(fromArmies - 1) + 1; 
		}
		
		fromArmies = fromArmies - value;
		
		toArmies = toArmies + value;
		
		PlayerClass.playerInfo.put(from, fromArmies);
		
		PlayerClass.playerInfo.put(to, toArmies);
		
	}
	
	
	public static void main(String [] args){
		
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
		
		/*
		territoryMap.put("South America,Brazil", null);
		territoryMap.put("South America,Colombia", null);
		territoryMap.put("South America,Argentina", null);
		territoryMap.put("South America,Peru", null);
		
		territoryMap.put("Africa,Egypt", null);
		territoryMap.put("Africa,Zimbabwe", null);
		territoryMap.put("Africa,Libya", null);
		territoryMap.put("Africa,Sudan", null);
		territoryMap.put("Africa,South Africa", null);
		territoryMap.put("Africa,Ghana", null);
	*/
		
		
		int numberOfPlayers = 3;
		PlayerClass.startUpPhase(numberOfPlayers,territoryMap);
		
		ReinforcementClass.calculateReinforcement("1");
		ReinforcementClass.calculateReinforcement("2");
		ReinforcementClass.calculateReinforcement("3");
		
		System.out.println("----------------------------------------");
		ReinforcementClass.reinforceRandom("1");
		ReinforcementClass.reinforceRandom("2");
		ReinforcementClass.reinforceRandom("3");
		
		
		System.out.println(PlayerClass.playerInfo);
		
		createFortifySet("1",territoryMap);
		System.out.println("--------\nPlayer1 ::"+fortifySet);
		System.out.println("--------\n"+PlayerClass.playerInfo);
		
		randomFortification("1");
		System.out.println("--------\nAfter::"+PlayerClass.playerInfo);
		
		createFortifySet("2",territoryMap);
		System.out.println("--------\nPlayer2 ::"+fortifySet);
		System.out.println("--------\n"+PlayerClass.playerInfo);
		
		randomFortification("2");
		System.out.println("--------\nAfter::"+PlayerClass.playerInfo);
		
		createFortifySet("3",territoryMap);
		System.out.println("--------\nPlayer3 ::"+fortifySet);
		System.out.println("--------\n"+PlayerClass.playerInfo);
		

		randomFortification("3");
		System.out.println("--------\nAfter::"+PlayerClass.playerInfo);
	}
	
	
}



