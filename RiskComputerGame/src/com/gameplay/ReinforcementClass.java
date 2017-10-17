package com.gameplay;

import java.util.HashMap;
import java.util.List;

public class ReinforcementClass {

	public static HashMap<String,Integer> reinforcement = new HashMap<String,Integer>();
	public static HashMap<String,String> playerCards = new HashMap<String,String>();
	
	//public static int reinforcment;
	
	
	public static void calculateReinforcement(String player){
		
		int reinforcementArmies = 0;
		int playerTerr = 0;
		
		//calculate reinforcement armies by number of countries owned
		for(String plyrVal : PlayerClass.playerInfo.keySet()){
			String plyrInfo [] = plyrVal.split("-");
			if(plyrInfo[0].equals(player) || plyrInfo[0] == player){
				playerTerr ++ ; 
			}
		}
		
		
		if(playerTerr < 9 ){
			reinforcementArmies = reinforcementArmies + 3;
		}
		if(playerTerr >= 9){
			reinforcementArmies = reinforcementArmies + ((int) Math.floor((double)playerTerr/3));
		}
	
		
		//calculate reinforcement armies according to card values if any contained
		
		//calculate reinforcement armies if the player owns the whole continent
		
		reinforcement.put(player,reinforcementArmies);
		
		System.out.println("############################################");
		System.out.println(reinforcement);
	}
	
	
	public void reinforceRandom(String player){
		
		
		int reinforcementArmies = reinforcement.get(player);
		
		//loop until all reinforcement armies have been assigned
		while(reinforcementArmies != 0){
			
			//choose territory randomly to put armies into
			for(String playerInfoKey : PlayerClass.playerInfo.keySet()){
				
				String [] playerVals = playerInfoKey.split("-");
				if(playerVals[0].equals(player) || playerVals[0] == player){
					
					reinforcementArmies--;
				}
			}
			
			
		}
		
	}
	
	public static void main(String[] args){
		

		HashMap<String,List<String>> territoryMap = new HashMap<String,List<String>>(); 
			territoryMap.put("Asia,India", null);
			territoryMap.put("Asia,China", null);
			territoryMap.put("Asia,Pakistan", null);
			territoryMap.put("Asia,Japan", null);
			territoryMap.put("Asia,Afghanistan", null);
			territoryMap.put("Asia,Russia", null);
			territoryMap.put("Asia,Iran", null);
			territoryMap.put("Asia,Sri Lanka", null);
			 
			territoryMap.put("Europe,England", null);
			territoryMap.put("Europe,France", null);
			territoryMap.put("Europe,Spain", null);
			territoryMap.put("Europe,Germany", null);
			territoryMap.put("Europe,Italy", null);
			territoryMap.put("Europe,Ireland", null);
			territoryMap.put("Europe,Ukraine", null);
			territoryMap.put("Europe,Greece", null);
		
			territoryMap.put("North America,Mexico", null);
			territoryMap.put("North America,USA", null);
			territoryMap.put("North America,Canada", null);
			
			
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
		
			
			int numberOfPlayers = 3;
			PlayerClass.startUpPhase(numberOfPlayers,territoryMap);
			
			calculateReinforcement("1");
			calculateReinforcement("2");
			calculateReinforcement("3");
	}
}
